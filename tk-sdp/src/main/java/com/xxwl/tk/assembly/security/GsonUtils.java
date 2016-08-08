package com.xxwl.tk.assembly.security;
import com.google.gson.Gson;

/**
 * gson工具类
 * 
 */
/**
 * @author deng
 *
 */
public class GsonUtils {

	/**
	 * 编码
	 * 
	 * @param src
	 *            类对象
	 * @return json格式
	 */
	public static String encrypt(Object src) {

		Gson gson = new Gson();
		String str = gson.toJson(src);
		return str;
	}

	/**
	 * 解码
	 * 
	 * @param src
	 *            json格式
	 * @param c
	 *            转换的类
	 * @return 类对象
	 */
	public static Object decrypt(String src, Class<?> c) {

		Gson gson = new Gson();
		return gson.fromJson(src, c);
	}

}
