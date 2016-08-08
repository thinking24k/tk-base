package com.xxwl.tk.main.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符工具类
 * @author deng
 *
 */
public class StringUtil {
	/**
	 * 判断该字符串是否是空字符串
	 * @param str 字符参数
	 * @return 是空返回true
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.trim().equals(""))
			return true;
		return false;
	}
	/**
	 * 判断该数值对象是否是空或是0
	 * @param str 字符参数
	 * @return 是空返回true
	 */
	public static boolean isEmpty(Integer integer){
		if(integer==null||integer.equals(0))
			return true;
		return false;
	}
	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection){
		if(collection==null||collection.size()==0)
			return true;
		return false;
	}
	/**
	 * 判断map是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map){
		if(map==null)
			return true;
		return false;
	}
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null)
			return true;
		return false;
	}
	/**
	 * 判断多个字符是否为空
	 * @param str 多个字符参数
	 * @return 一个为空都为空，返回true
	 */
	public static boolean isEmpty(String... str){
		if(str==null)
			return true;
		for (int i = 0; i < str.length; i++) {
			if(str[i]==null||str[i].trim().equals(""))
				return true;
		}
		return false;
	}
	/**
	 * 判断数组是否为空
	 * @param array 数组对象
	 * @return 数组为空或长度为0返回true
	 */
	public static boolean isEmpty(Object [] array){
		if(array==null ||array.length==0)
			return true;
		return false;
	}
	/**
	 * 
	* @Title: isNotEmpty 
	* @Description: 非空验证
	* @param str
	* @return
	 */
	public static boolean isNotEmpty(String ... str) {
		return !isEmpty(str);
	}
}
