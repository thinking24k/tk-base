package com.scrt.mi.generator.util;

import java.util.Set;

/**
 * 
 * @ClassName: WildcardEngine 
 * @Description: 配置通配引擎
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年10月22日 
 *
 */
public class WildcardEngine {
	//通配规则开始字符
	private static final String  WILDCARD_START_CHAR="${";
	//通配规则结束字符
	private static final String  WILDCARD_END_CHAR="}";
	//通配的key list
	private static Set<String> wildcardKey=null ;
	//静态初始化
	static{
		 wildcardKey = ConfigUtil.configs.keySet();
	}
	/**
	 * 
	 * @Title: doWildcard 
	 * @Description: 进行通配处理
	 * @param param
	 * @return
	 */
	public static String doWildcard(String param){
		//判断空
		if(StringUtil.isEmpty(param)){
			return param;
		}
		if(StringUtil.isEmpty(wildcardKey)){
			return param;
		}
		for (String string : wildcardKey) {
			String replaceChar=WILDCARD_START_CHAR+string+WILDCARD_END_CHAR;
			if(param.contains(string)){
				param=param.replace(replaceChar, ConfigUtil.getByKey(string));
			}
		}
		return param;
	}
	
	
}
