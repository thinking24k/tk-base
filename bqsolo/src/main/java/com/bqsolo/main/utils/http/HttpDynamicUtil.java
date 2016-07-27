

package com.bqsolo.main.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bqsolo.main.utils.StringUtils;



/** 
 * @ClassName: HttpDynamicUtil 
 * @Description: TODO
 * @company 
 * @author xuefeng.gao
 * @Email ourjava@qq.com
 * @date 2014-12-2 
 *  
 */
public class HttpDynamicUtil extends HttpUtil{
	private static Logger log= Logger.getLogger(HttpDynamicUtil.class);
	private String sessionId;
	private URL url;
	private HttpURLConnection conn;
	private String responseResult;
	private String strUrl;
	private String strGetParams;
	private String strPostParams;

	
	public HttpDynamicUtil(String sessionId){
		this.sessionId = sessionId;
	}
	
	public HttpDynamicUtil(){

	}	
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public HttpURLConnection getConn() {
		return conn;
	}
	public void setConn(HttpURLConnection conn) {
		this.conn = conn;
	}
	public String getResponseResult() {
		return responseResult;
	}
	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}
	public String getStrUrl() {
		return strUrl;
	}
	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}
	public String getStrGetParams() {
		return strGetParams;
	}
	public void setStrGetParams(String strGetParams) {
		this.strGetParams = strGetParams;
	}
	public String getStrPostParams() {
		return strPostParams;
	}
	public void setStrPostParams(String strPostParams) {
		this.strPostParams = strPostParams;
	}
	
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数
	 * @param jsessionid 当前sessionid
	 * @return URL 所代表远程资源的响应结果
	 * @throws IOException
	 */
	public  String sendGetRequest(String strURL, String params)
			throws IOException {
		String result = "";
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		try {
			String urlNameString = strURL;
		    if(StringUtils.isNotEmpty(this.getSessionId())){
		    	urlNameString+=";jsessionid="+this.getSessionId();
		    }
		    if(StringUtils.isNotEmpty(params)){
		    	urlNameString += "?" + params;
		    }
			URL url = new URL(urlNameString);
			//1.Open the connection between origin and destination side.
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15000);
		    conn.setRequestMethod("GET");  

		    conn.setRequestProperty("APP_PATCH","ANDROID_CUSTOMER_PATCH");
		    conn.setRequestProperty("APP_VERSION","V1.0.0.1");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				conn.connect();
				praseSessionId(conn);
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
				log.warn(result);
			}

		} catch (IOException e) {
			e.printStackTrace();
			log.error("发送GET请求出现异常！" + e);
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
	public  String sendPostRequest(String strURL, String postParams) throws IOException{
		return sendPostRequest(strURL,null,postParams);
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
	public  String sendPostRequest(String strURL, String getParams,String postParams)
			throws IOException {
	    conn.setRequestProperty("app_patch","ANDROID_CUSTOMER_PATCH");
	    conn.setRequestProperty("app_version","V1.0.0.1");
	    conn.setRequestProperty("app_prod_info", "SANSUMG N9007");		
		
		String result = null;
		OutputStreamWriter out = null;
		InputStream is = null;
		HttpURLConnection conn = null; 
		try {
			String urlNameString = strURL;
		    if(StringUtils.isNotEmpty(this.getSessionId())){
		    	urlNameString+=";jsessionid="+this.getSessionId();
		    }
		    if(StringUtils.isNotEmpty(getParams)){
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
			praseSessionId(conn);
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
			log.error("发送POST请求出现异常！" + e);
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

	private void praseSessionId(HttpURLConnection conn){
		String sessionId=null; 
		if(conn!=null){
	        String cookieValue=conn.getHeaderField("Set-Cookie");
	        System.out.println("cookie value:"+cookieValue);
	        if(StringUtils.isNotEmpty(cookieValue)){
	     	   sessionId=cookieValue.substring(0, cookieValue.indexOf(";")); 
	     	   if(StringUtils.isNotEmpty(sessionId)&&sessionId.indexOf("=")>-1 ){
	     		  sessionId=sessionId.substring(sessionId.indexOf("=")+1); 
	     	   }
	        }			
		}
		this.setSessionId(sessionId);
	}
	
	
}
