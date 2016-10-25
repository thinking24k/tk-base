package com.xxwl.tk.attachment.utils;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.xxwl.tk.common.properties.PropertiesManager;
import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.utils.StringUtil;
import com.xxwl.tk.main.web.controller.PicController;

/**
 * 
 * @ClassName: AttachmentUtil 
 * @Description: 附件上传模块controller工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月28日 
 *
 */
public class AttachmentUtil {
	//日志
	private static Logger logger = Logger.getLogger(AttachmentUtil.class);
	/**文件保存位置*/
	private static String saveAddress="";
	/**文件web访问地址*/
	private static String webAddress="";
	/**富文本编辑器文件保存路径*/
	private static String uEditorSavePath="";
	/**文件主目录*/
	public static String baseFloder="";
	/**富文本编辑器文件保存文件夹*/
	public static String uEditorFloder="";
	/**web-info*/
	public static String WEB_INFO="/WEB-INF";
	
	

	//加载
	static{
		baseFloder=getBaseFloder();
		uEditorFloder=getUEditorFloder();
	}

	/**
	 * 
	 * @Title: doFileValidation 
	 * @Description: 验证文件大小及文件类型 通过返回true;
	 * @param allowSuffix
	 * @param allowMaxFileSize
	 * @param file
	 * @param response
	 * @return
	 */
	public static boolean doFileValidation(String allowSuffix,Double allowMaxFileSize,MultipartFile file,HttpServletResponse response){
		if(!(doAllowSuffix(allowSuffix, file)&&doAllowMaxFileSizeValidation(allowMaxFileSize, file.getSize()))){
			//this.response(false, "", response,MessageAttribute.ATTACHMENT_FILE_VALIDATION_SUC);
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @Title: doAllowSuffix 
	 * @Description: 检验文件类型
	 * @param allowSuffix
	 * @param file
	 * @return
	 */
	public static boolean doAllowSuffix(String allowSuffix, MultipartFile file) {
		//如果文件限制类型为空返回false
		if(StringUtil.isEmpty(allowSuffix)){
			return false;
		}
		String fileName=file.getOriginalFilename();
		if(StringUtil.isEmpty(fileName)){
			return false;
		}
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		//获取允许的文件
		String[] fileSuffixs = allowSuffix.split(CommonAttribute.STR_SPLIT_CHAR);
		for (String suffix : fileSuffixs) {
			if(fileSuffix.equalsIgnoreCase(suffix.trim())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Title: doAllowMaxFileSizeValidation 
	 * @Description: 检验文件大小
	 * @param allowMaxFileSize
	 * @param fileSize
	 * @return
	 */
	public static boolean doAllowMaxFileSizeValidation(Double allowMaxFileSize,long fileSize){
		//校验文件大小
		if(!StringUtil.isEmpty(allowMaxFileSize)){
			//文件限制大小为0kb，不符合逻辑
			if( allowMaxFileSize<=0 || fileSize<=0 ){
				return false;
			}else{
				Double maxFileByte=allowMaxFileSize*1024.0;
				double fileByte = Double.parseDouble(String.valueOf(fileSize));
				//文件大小大于限制大小
				if(maxFileByte.doubleValue()<fileByte){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * @Title: getBeginSavePathByCache 
	 * @Description: 获取文件本地保存完整路径,没有读取过重新生成，有直接返回之前读取过的地址
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getBeginSavePathByCache(HttpServletRequest request)  {
		if(StringUtil.isEmpty(saveAddress)){
			return saveAddress=getBeginSavePath(request);
		}
		return saveAddress;
	}
	/**
	 * 
	 * @Title: getUEditorSavePathByCache 
	 * @Description: 获取UEditor问价保存路径
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getUEditorSavePathByCache(HttpServletRequest request)  {
		if(StringUtil.isEmpty(uEditorSavePath)){
			return uEditorSavePath=getBeginSavePathByCache(request)+uEditorFloder;
		}
		return uEditorSavePath;
	}
	/**
	 * 
	 * @Title: getBeginSavePath 
	 * @Description: 获取文件本地保存完整路径
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 *///路径获取只有放在controller 应为service获取不到web的项目名和webapp
	public static String getBeginSavePath(HttpServletRequest request) {
		//读取配置文件保存地址
		String saveAddress = PropertiesManager.getVmtooPropertyAsString(CommonAttribute.ATTACHMENT_SAVEADDRESS);
		//如果配置为空或为auto自动获取项目webapp目录
		if(StringUtil.isEmpty(saveAddress)||CommonAttribute.ATTACHMENT_DEFAULT_VAL.equalsIgnoreCase(saveAddress)){
			//获取项目本地地址
			String path="";
			try {
				path = PicController.class.getClassLoader().getResource("").toURI().getPath();
			} catch (URISyntaxException e) {
				logger.error(e);
				e.printStackTrace();
			}
			//如果request 为空 且是web容器中运行的项目则通过web-info进行获取容器目录
			if(StringUtil.isEmpty(request) && path.contains(WEB_INFO)){
				//截取项目本地地址截取获取webapp目录
				saveAddress=path.substring(0,path.indexOf(WEB_INFO));
				saveAddress=saveAddress.substring(0,saveAddress.lastIndexOf(CommonAttribute.PATH_DEFAULT_SPLIT_CHAR));
			}else{
				//获取项目名
				String contextPath = request.getContextPath();
				//截取项目本地地址截取获取webapp目录
				saveAddress=path.substring(0,path.indexOf(contextPath));
			}
		}
		//保存地址
		String baseFloer = getBaseFloder();
		//检验返回文件完整路经
		return FileUtil.checkPath(saveAddress+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+baseFloer);
	}

	/**
	 * 
	 * @Title: getBeginWebPathByCache 
	 * @Description: 获取文件web访问完整路径,没有读取过重新生成，有直接返回之前读取过的地址
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getBeginWebPathByCache(HttpServletRequest request){
		if(StringUtil.isEmpty(webAddress)){
			return getBeginWebPath(request);
		}
		return webAddress;
	}
	/**
	 * 
	 * @Title: getBeginWebPath 
	 * @Description: 获取文件web访问完整路径
	 * @param request
	 * @return
	 */
	//	public String getfullWebPath(String filePath,HttpServletRequest request){
	public static String getBeginWebPath(HttpServletRequest request){
		//读取配置文件保存地址
		String basePath = PropertiesManager.getVmtooPropertyAsString(CommonAttribute.ATTACHMENT_WEBADDRESS);
		//如果配置为空或为auto自动获取项目webapp目录
		if(StringUtil.isEmpty(basePath)||CommonAttribute.ATTACHMENT_DEFAULT_VAL.equalsIgnoreCase(basePath)){
			//项目路经
			//String path = request.getContextPath();
			//basePath
			StringBuffer sb=new StringBuffer();
			sb.append(request.getScheme());
			sb.append("://");
			sb.append(request.getServerName());
			sb.append(":");
			sb.append(request.getServerPort());
			//sb.append(path);
			sb.append(CommonAttribute.PATH_DEFAULT_SPLIT_CHAR);
			basePath = sb.toString();
		}
		//路径是否是以/结尾
		if(!basePath.endsWith(CommonAttribute.PATH_DEFAULT_SPLIT_CHAR)){
			//没有就+ / 
			basePath+=CommonAttribute.PATH_DEFAULT_SPLIT_CHAR;
		}
		//保存地址
		String baseFloer = getBaseFloder();
		//拼接web完整访问文件的url
		return basePath+baseFloer;
	}

	/**
	 * 
	 * @Title: getBaseFloder 
	 * @Description: 获取文件主目录
	 * @return
	 */
	public static String  getBaseFloder(){
		return PropertiesManager.getVmtooPropertyAsString(CommonAttribute.ATTACHMENT_BASEFLODER);
	}
	/**
	 * 
	 * @Title: getBaseFloder 
	 * @Description: 获取文件主目录
	 * @return
	 */
	public static String  getUEditorFloder(){
		return PropertiesManager.getVmtooPropertyAsString(CommonAttribute.ATTACHMENT_UEDITOR);
	}
	
	/**
	 * 
	 * @Title: getShareFloder 
	 * @Description: 获取分享文件主目录
	 * @return
	 */
	public static String  getShareFloder(){
		return PropertiesManager.getVmtooPropertyAsString(CommonAttribute.ATTACHMENT_SHARE);
	}
}
