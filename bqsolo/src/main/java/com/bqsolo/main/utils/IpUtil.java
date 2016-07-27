package com.bqsolo.main.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip
 * @author deng
 *
 */
public class IpUtil {
	/**
	 * 获取客服端ip
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request){ 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip; 
	}
}
