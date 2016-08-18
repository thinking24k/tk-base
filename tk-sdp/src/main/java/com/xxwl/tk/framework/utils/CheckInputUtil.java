package com.xxwl.tk.framework.utils;
/**
 * 验证页面输入
 * @author deng
 *
 */
public class CheckInputUtil {
	
	static char [] otherSymbol={'=','>','<','~','@','%'};
	/**
	 * 验证是否含有制定集合中的特殊符号
	 * @param str
	 * @return 包含返回true否则返回false
	 */
	public static boolean hasOtherSymbol(String str){
		char[] array = str.toCharArray();
		for (char c : array) {
			for (char b : array) {
				if(c==b){
					return true;
				}
			}
		}
		return false;
	}
	
	
}
