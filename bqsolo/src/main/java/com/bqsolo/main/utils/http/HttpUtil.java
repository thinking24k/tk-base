
package com.bqsolo.main.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.bqsolo.main.utils.StringUtils;


/** 
* @ClassName: HttpUtil 
* @Description: HTTP 传输 工具类
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2014-12-8 
*  
*/ 
public class HttpUtil {
	protected static final Logger logger = Logger.getLogger(HttpUtil.class);
	public static final String RESPONSE_SESSIONID = "JSESSIONID";
	public static final String RESPONSE_CONTENT = "CONTENT";
	public static final String HEADER_TOKEN = "token";


	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数
	 * @return URL 所代表远程资源的响应结果
	 * @throws IOException
	 */
	@Deprecated
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
	@Deprecated
	public static String sendGet(String strURL, String params,String jsessionid)
			throws IOException {
		HttpUtilBean httpUtilBean = new HttpUtilBean();
		httpUtilBean.setJsessionid(jsessionid);
		sendRequest(strURL, params,null, httpUtilBean);
		return httpUtilBean.getResponseContent();
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
	@Deprecated
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
	@Deprecated
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
	@Deprecated
	public static String sendPost(String strURL,String getParams,String postParams,String jsessionid) throws IOException{
		HttpUtilBean httpUtilBean = new HttpUtilBean();
		httpUtilBean.setJsessionid(jsessionid);
		sendRequest(strURL, getParams, postParams, httpUtilBean);
		return httpUtilBean.getResponseContent();
	}

	
	/** 
	* @Title: Http 请求关键方法 
	* @Description: 发送Get/Post 请求. 并反馈Response信息
	* @param strURL URL地址
	* @param getParams Get方式参数
	* @param postParams 以Json格式请求的表单主体
	* @param httpUtilBean 请求的信息,包括Header,初始的JsessionID
	* @throws IOException  
	*/ 
	public static HttpUtilBean sendRequest(String strURL,String getParams,String postParams) throws IOException{
		return sendRequest(strURL, getParams, postParams,null);
	}
	/**
	* <p>Title: sendHttpsRequest</p>
	* <p>Description: 以 HTTPS 发送Get 请求. 并反馈Response信息 </p></p>
	* @param strURL
	* @return
	* @throws IOException
	*/
	public static HttpUtilBean sendHttpsRequest(String strURL) throws IOException{
		return sendRequest( strURL, null, null, null,true );
	}
	public static HttpUtilBean sendHttpsRequest(String strURL,String postParams) throws IOException{
		return sendRequest( strURL, null, postParams, null,true );
	}
	
	/**
	* <p>Title: sendRequestByHttps</p>
	* <p>Description:以 HTTPS 发送Get/Post 请求. 并反馈Response信息 </p>
	* @param strURL
	* @param getParams
	* @param postParams
	* @param httpUtilBean
	* @return
	* @throws IOException
	*/
	public static HttpUtilBean sendHttpsRequest(String strURL,String getParams,String postParams,HttpUtilBean httpUtilBean) throws IOException{
		return sendRequest( strURL, getParams, postParams, httpUtilBean,true );
	}
	public static HttpUtilBean sendRequest(String strURL,String getParams,String postParams,HttpUtilBean httpUtilBean) throws IOException{
		return sendRequest( strURL, getParams, postParams, httpUtilBean,false );
	}
	/** 
	* @Title: Http 请求关键方法 
	* @Description: 发送Get/Post 请求. 并反馈Response信息
	* @param strURL URL地址
	* @param getParams Get方式参数
	* @param postParams 以Json格式请求的表单主体
	* @param httpUtilBean 请求的信息,包括Header,初始的JsessionID
	* @return httpUtilBean 返回的SessionID，主体信息及状态码等
	* @throws IOException  
	*/ 
	public static HttpUtilBean sendRequest(String strURL,String getParams,String postParams,HttpUtilBean httpUtilBean,boolean isHttps) throws IOException{
		if(httpUtilBean==null){
			httpUtilBean = new HttpUtilBean();
		}
		String response_content = null;
		Map<String,String> responseData = new HashMap<String, String>();
		String urlNameString = strURL;

	    if(StringUtils.isNotEmpty(getParams)){
	    	urlNameString += "?" + getParams;
	    }
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpRequestBase httpMethod;
	    if(isHttps){
	    	httpclient = HttpClientConnectionManager.getSSLInstance(httpclient);
	    	if(!StringUtils.isEmpty(postParams)){
	    		httpMethod = HttpClientConnectionManager.getPostMethod(urlNameString);
	    	}else{
	    		httpMethod = HttpClientConnectionManager.getGetMethod(urlNameString);
	    	}
	    }else{
		    //以Post方法提交
		    if(!StringUtils.isEmpty(postParams)){
		    	httpMethod = new HttpPost(urlNameString);
			    if(postParams!=null){
			    	((HttpPost)httpMethod).setEntity(new StringEntity(postParams,"utf-8"));  
			    }	    	
		    }
		    //以Get方法提交
		    else{
		    	httpMethod = new HttpGet(urlNameString);
		    }	    	
	    }
		try {			
			//添加http头信息  
		    if(httpUtilBean!=null&&!isHttps){
		    	httpMethod.addHeader("Content-Type", "application/json");
			    initHttpHeader(httpMethod,httpUtilBean);
		    }
		    HttpResponse response;
		    response = httpclient.execute(httpMethod); 
		    //检验状态码，如果成功接收数据  
		    int code = response.getStatusLine().getStatusCode();  
		    responseData.put(HttpUtilBean.RESPONSE_STATUSCODE, String.valueOf(code));
		    if (code == 200) {
		    	String response_sessionid = praseSessionId(response);
		    	response_content = EntityUtils.toString(response.getEntity());  
		    	//重写JsessionID
		    	httpUtilBean.setJsessionid(response_sessionid);		    	
		    	//重写Response Content
		    	httpUtilBean.setResponseContent(response_content);
		        
		    } 		    
	    }catch (IOException e) {
			logger.error("发送POST请求出现异常！" + e);
			throw e;
	    }finally{
	    	httpMethod.releaseConnection();
	    }
		return httpUtilBean;
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

		logger.info("req.getContentType():" + request.getContentType());
		logger.info("req.getCharacterEncoding():"+ request.getCharacterEncoding());

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
			logger.info("App request Json String:" + result);
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().print(jsonData);
        logger.info("response data { " + jsonData + "}");
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
    

    
    
	private static String praseSessionId(HttpResponse response){
		//获取Sessionid
	   String sessionId = null;
 	   Header header = response.getFirstHeader("Set-Cookie");
 	   if(header!=null){
	           String cookieValue = header.getValue();
	           logger.info("cookie value:"+cookieValue);
	           if(StringUtils.isNotEmpty(cookieValue)){
	        	   sessionId=cookieValue.substring(0, cookieValue.indexOf(";")); 
	           }		    		   
 	   }
     //获取sessionid完成		
 	   if(StringUtils.isNotEmpty(sessionId)&&sessionId.indexOf("=")>-1){
 		  sessionId = sessionId.substring(sessionId.indexOf("=") + 1);
 	   }
 	   return sessionId;
	}  
	


	/** 
	* @Title: initHttpHeader 
	* @Description: 初始化Http Header
	* @param httpMethod
	* @param httpUtilBean  
	*/ 
	private static void initHttpHeader(HttpRequestBase httpMethod,HttpUtilBean httpUtilBean){
		if(httpUtilBean==null){
			return;
		}
		//1.处理 JsessionID
		String jsessionId = httpUtilBean.getJsessionid();
		if(StringUtils.isNotEmpty(jsessionId)){
			jsessionId= "JSESSIONID="+jsessionId;
			//httpMethod.setHeader("Cookie",jsessionId);
			httpMethod.addHeader("Cookie",jsessionId);
			httpMethod.setHeader("Token",jsessionId);
					
		}
		//2.处理设备相关信息
//		httpMethod.setHeader(HttpUtilBean.HEADER_APP_PATCH,httpUtilBean.getApppatch());	
//		httpMethod.setHeader(HttpUtilBean.HEADER_APP_VERSION,httpUtilBean.getAppversion());	
//		httpMethod.setHeader(HttpUtilBean.HEADER_APP_DEVICE,httpUtilBean.getAppdeviceinfo());	
		
		//3.处理其他信息
		if(!httpUtilBean.isEmpty()){
		    Iterator<Entry<String, String>> entryKeyIterator = httpUtilBean.entrySet().iterator();  
	           while (entryKeyIterator.hasNext()) {  
	                Entry<String, String> e = entryKeyIterator.next();  
	                httpMethod.setHeader(e.getKey(), e.getValue());
	        }  			
		}
	}

}
