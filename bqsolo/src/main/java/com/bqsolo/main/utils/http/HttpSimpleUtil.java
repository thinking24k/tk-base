package com.bqsolo.main.utils.http;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bqsolo.main.utils.StringUtil;


/**
 * @ClassName: HttpRequestUtil
 * @Description: 
 * <p>Send HttpRequest/Response to target on Java server side.
 * @author xuefeng.gao
 * @email ourjava@qq.com
 * @date 2014-10-29
 */
public class HttpSimpleUtil {
	protected static final Logger log = Logger.getLogger("HttpRequestUtil");
	

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数
	 * @return URL 所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String sendGet(String strURL, String params)
			throws IOException {
		return sendGet(strURL,params,null);
	}
	
	
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数
	 * @param jsessionid 当前sessionid
	 * @return URL 所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String sendGet(String strURL, String params,String jsessionid)
			throws IOException {
		String result = "";
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		try {
			String urlNameString = strURL;
		    if(StringUtil.isNotEmpty(jsessionid)){
		    	urlNameString+=";jsessionid="+jsessionid;
		    }
		    if(StringUtil.isNotEmpty(params)){
		    	urlNameString += "?" + params;
		    }
			URL url = new URL(urlNameString);
			//1.Open the connection between origin and destination side.
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15000);
		    conn.setRequestMethod("GET");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				conn.connect();
				Map<String, List<String>> map = conn.getHeaderFields();
				for (String key : map.keySet()) {
					System.out.println(key + "--->" + map.get(key));
				}
				reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				reader.close();
				conn.disconnect();
			} else {
				result = "发送GET请求，无法请求到服务器";
				log.warning(result);
			}

		} catch (IOException e) {
			e.printStackTrace();
			log.severe("发送GET请求出现异常！" + e);
			throw e;
		}
		// 使用finally块来关闭输入流
		finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result;
	}

	/** 
	* @Title: sendPost 
	* @Description: 向指定 URL 发送POST方法的请求
	* @param strURL 发送请求的 URL
	* @param getParams get请求参数
	* @param postParams post请求参数
	* @throws IOException  
	* @return String   
	*/
	public static String sendPost(String strURL, String getParams,String postParams) throws IOException{
		return sendPost(strURL,getParams,postParams,null);
	}
	
	/** 
	* @Title: sendPost 
	* @Description: 向指定 URL 发送POST方法的请求
	* @param strURL 发送请求的 URL
	* @param getParams get请求参数
	* @param postParams post请求参数
	* @throws IOException  
	* @return String   
	*/
	public static String sendPost(String strURL, String postParams) throws IOException{
		return sendPost(strURL,null,postParams,null);
	}	

	/** 
	* @Title: sendPost 
	* @Description: 向指定 URL 发送POST方法的请求
	* @param strURL 发送请求的 URL
	* @param getParams get请求参数
	* @param postParams post请求参数
	* @param jsessionid 当前sessionid
	* @throws IOException  
	* @return String   
	*/
	public static String sendPost(String strURL, String getParams,String postParams,String jsessionid)
			throws IOException {
		String result = null;
		OutputStreamWriter out = null;
		InputStream is = null;
		HttpURLConnection conn = null; 
		try {
			String urlNameString = strURL;
		    if(StringUtil.isNotEmpty(jsessionid)){
		    	urlNameString+=";jsessionid="+jsessionid;
		    }
		    if(StringUtil.isNotEmpty(getParams)){
		    	urlNameString += "?" + getParams;
		    }			
			URL url = new URL(urlNameString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("POST"); 
			//1.Define the Accept Pattern & Content type.
			//Content type : application/x-www-form-urlencoded  or application/json
			conn.setRequestProperty("Accept", "application/json"); 
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			//2.Define the encoding style.Note: UTF-8 support CN.
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.append(postParams);
			out.flush();
			out.close();
			int length = (int) conn.getContentLength();
			//获取Sessionid
	           String cookieValue=conn.getHeaderField("Set-Cookie");
	           String sessionId = "";
	           System.out.println("cookie value:"+cookieValue);
	           if(StringUtil.isNotEmpty(cookieValue)){
	        	   sessionId=cookieValue.substring(0, cookieValue.indexOf(";")); 
	           }
	        //获取sessionid完成
			is = conn.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				result = new String(data, "UTF-8"); 
				log.info(result);
				return result;
			}
		} catch (IOException e) {			
			e.printStackTrace();
			log.severe("发送POST请求出现异常！" + e);
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
			if (is != null) {
				is.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result; 
	}

	/** 
	* @Title: getJsonStrFromRequest 
	* @Description: 处理接收终端请求的JSON数据
	* @param request
	* @throws UnsupportedEncodingException  
	* @return String   
	*/ 
	public static String getJsonStrFromRequest(HttpServletRequest request)
			throws UnsupportedEncodingException {

		log.fine("req.getContentType():" + request.getContentType());
		log.fine("req.getCharacterEncoding():"+ request.getCharacterEncoding());

		/* 读取数据 */
		request.setCharacterEncoding("UTF-8"); // 避免中文乱码 POST方式提交
		String result = null;
		InputStream inputStream = null;
		BufferedReader br = null;
		try {
			inputStream = request.getInputStream();
			br = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			//br.close();
			result = sb.toString();
			log.fine("App request Json String:" + result);
			
		} catch (IOException e) {
			e.printStackTrace();
			log.warning(e.getMessage());
		}
		return result;
	}


	/** 
	* @Title: responseJsonData 
	* @Description: 将目标数据 Response 返回给终端
	* @param jsonData
	* @param response
	* @throws IOException  
	* @return void   
	*/ 
	public static void responseJsonData(String jsonData,
			HttpServletResponse response) throws IOException  {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(jsonData);
        log.fine("response data { " + jsonData + "}");
	} 	
	
    /** 
    * @Title: responseJsonData 
    * @Description: 将集合转换为JSON 字符串并返回终端
    * @param objects 集合
    * @param clazz 
    * @param response
    * @throws IOException  
    * @return void   
    */ 
	public static <T> void responseJsonData(List<T> objects, Class<?> clazz, HttpServletResponse response) throws IOException {
        String jsonData = JsonUtil.convertList2Json(objects, clazz);
        responseJsonData(jsonData,response);
    }
    
    /** 
    * @Title: responseJsonData 
    * @Description: 将对象转换为JSON 字符串并返回终端
    * @param object
    * @param clazz
    * @param response
    * @throws IOException  
    * @return void   
    */ 
	public static void responseJsonData(Object object, Class<?> clazz, HttpServletResponse response) throws IOException {
        String jsonData = JsonUtil.convertObject2Json(object, clazz);
        responseJsonData(jsonData,response);
    }
    
    

    /** 
    * @Title: readFromHttpRequest 
    * @Description: 直接将request请求的信息转换为目标对象
    * @param request Http请求
    * @param clazz 目标对象类
    * @return Object  目标对象
    */ 
    public static Object readFromHttpRequest(HttpServletRequest request, Class<?> clazz) {
        try {
            return JsonUtil.readFromStream(request.getInputStream(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }	
    
    
    
    public static void main(String[] args) {
        String s;
		try {
			//发送 GET 请求
			s = HttpSimpleUtil.sendGet("http://localhost:8080/sdp/admin/user.json", "key=123&v=456");
			System.out.println(s);
	        //发送 POST 请求
	        String sr=HttpSimpleUtil.sendPost("http://localhost:8080/sdp/admin/user.json", "{\"key\":\"111\",\"v\":\"222\"}");
	        System.out.println(sr);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

    }	
	
}
