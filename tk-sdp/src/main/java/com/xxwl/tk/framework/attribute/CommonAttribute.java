package com.xxwl.tk.framework.attribute;


/**
 * 存放需要使用的常量
 * @author deng
 *
 */
public class CommonAttribute {
	
	/** 数据有效 */
	public static final Integer ISVOID_TRUE=1;
	/** 数据无效 */
	public static final Integer ISVOID_FALSE=0;
	
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
	/** 当前Terminal Context信息 */
	public static final String TERMINAL_CONTEXT ="TERMINAL_CONTEXT";
	/**session验证码文字*/
	public static String SESSIONRANDOMIMG="";
	/** 邮件标示符 */
	public static final String EMAIL_FLAG ="@";
	/** 附件保存位置 */
	public static final String ATTACHMENT_SAVEADDRESS = "attachment.saveAddress";
	/** 根文件夹 */
	public static final String ATTACHMENT_BASEFLODER = "attachment.baseFloder";
	/** web访问的地址 */
	public static final String ATTACHMENT_WEBADDRESS = "attachment.webAddress";
	/** 富文本保存路径 */
	public static final String ATTACHMENT_UEDITOR = "attachment.UEditor";
	/**分享附件保存路径**/
	public static final String ATTACHMENT_SHARE = "attachment.share";
	/** 固定的默认路径分割符 */
	public static final String PATH_DEFAULT_SPLIT_CHAR = "/";
	/** 参数分割字符 */
	public static final String STR_SPLIT_CHAR = ",";
	/** 获取文件开始路经默认方式 */
	public static final String ATTACHMENT_DEFAULT_VAL = "auto";
	
	
	
	/**项目根目录本地路径 classes目录;例如:/E:/yw_work/yistylenow/yistyle/target/classes/;*/
	public static String LOCALCLASSPATH="";
	
	//静态初始化
	static{
			String path=CommonAttribute.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			LOCALCLASSPATH=path.substring(0, path.indexOf("classes"))+"classes/";
	}
}