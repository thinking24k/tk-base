package com.bqsolo.main.staticVar;


/**
 * 存放需要使用的常量
 * @author deng
 *
 */
public class Constant {
	/** 登录的普通用户 */
	public static final String SESSIONUSER="sessionUser";
	/** 登录的Admin*/
	public static final String SESSIONADMIN ="sessionAdmin";
	/**登录前页面url*/
	public static final String BEFORELOGINURL="beforeLoginurl";
	/**注册前页面url*/
	public static final String BEFOREREGURL="beforeRegurl";
	/**注册邮件模板*/
	public static final String REGISTERFTL="registerl.ftl";
	/**session验证码文字*/
	public static String SESSIONRANDOMIMG="";
	
	/**项目根目录本地路径 classes目录;例如:/E:/yw_work/yistylenow/yistyle/target/classes/;*/
	public static String LOCALCLASSPATH="";
	
	//静态初始化
	static{
			String path=Constant.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			LOCALCLASSPATH=path.substring(0, path.indexOf("classes"))+"classes/";
	}
}