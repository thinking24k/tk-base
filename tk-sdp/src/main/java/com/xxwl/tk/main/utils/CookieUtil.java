package com.xxwl.tk.main.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie 工具类
 * @author deng
 *
 */
public class CookieUtil {
	
	/**
	 * 获取 req中指定名字的cookie
	 * @param req HttpServletRequest
	 * @param name cookie的名字
	 * @return 找打返回对应cookie的values 否则返回null
	 */
	public static String getCookie(HttpServletRequest req,String name){
		Cookie[] cookies = req.getCookies();
		if(StringUtil.isEmpty(cookies)||StringUtil.isEmpty(name)){
			return null;
		}
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		return null;
	}
	/**
	 * 添加cook
	 * @param resp 
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse resp,String name,String value){
		resp.addCookie(new Cookie(name, value));
	}
	

}
