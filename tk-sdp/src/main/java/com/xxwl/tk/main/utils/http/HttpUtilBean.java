package com.xxwl.tk.main.utils.http;

import java.util.HashMap;

/** 
* @ClassName: HttpTransferDTO 
* @Description: 封装/获取 通过HTTP传输的终端关键数据
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2014-12-15 
*  
*/ 
public class HttpUtilBean extends HashMap<String,String>{
	private static final long serialVersionUID = -4088177949593257229L;
	
	//常量
	public static final String HEADER_JESSIONID = "jsessionid";
	public static final String HEADER_APP_PATCH = "app_patch"; 
	public static final String HEADER_APP_VERSION = "app_version"; 
	public static final String HEADER_APP_DEVICE = "app_device_info";	
	public static final String RESPONSE_CONTENT = "response_content";
	public static final String RESPONSE_STATUSCODE = "response_statusCode";

	
	
	private String responseContent;

	
	/** 
	* @Title: setResponseContent 
	* @Description: 写入Response 返回的数据
	* @param responseContent  
	*/ 
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
	/** 
	* @Title: getResponseContent 
	* @Description: 读取Response 返回的数据
	* @return  
	*/ 
	public String getResponseContent() {
		return responseContent;
	}	
	
	

	/** 
	* @Title: setJsessionid 
	* @Description: 设置sessionId
	* @param jsessionid  
	*/ 
	public void setJsessionid(String jsessionid) {
		this.put(HEADER_JESSIONID, jsessionid);
	}

	/** 
	* @Title: getApppatch 
	* @Description: 设置App类型号.
	* eg：wechart
	* @return  
	*/ 	
	public void setApppatch(String apppatch) {
		this.put(HEADER_APP_PATCH, apppatch);
	}
	
	/** 
	* @Title: setAppversion 
	* @Description: 设置App Version号.
	* @param appversion
	*/ 
	public void setAppversion(String appversion) {
		this.put(HEADER_APP_VERSION, appversion);
	}
	
	/** 
	* @Title: setAppdeviceinfo 
	* @Description: 设置设备型号
	* @param appdeviceinfo  
	*/ 
	public void setAppdeviceinfo(String appdeviceinfo) {
		this.put(HEADER_APP_DEVICE, appdeviceinfo);
	}
	
	public String getJsessionid() {
		return this.get(HEADER_JESSIONID);
	}

	public String getApppatch() {
		return this.get(HEADER_APP_PATCH);
	}

	public String getAppversion() {
		return this.get(HEADER_APP_VERSION);
	}

	public String getAppdeviceinfo() {
		return this.get(HEADER_APP_DEVICE);
	}

}
