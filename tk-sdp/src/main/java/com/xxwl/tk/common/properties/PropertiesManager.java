package com.xxwl.tk.common.properties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.utils.StringUtil;



/**
 * 
 * @ClassName: PropertiesManager 
 * @Description: 配置文件公共读取
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年10月25日 
 *
 */
public class PropertiesManager {
	// properties file
	protected static final String MI_PROPERTIES_FILE = "ds.properties";
	protected static final String MESSAGE_PROPERTIES_FILE = "message.properties";
	protected static final String EXCEPTION_PROPERTIES_FILE = "exception.properties";

	// properties Name
	public static final String MI_PROPERTIES = "";
	public static final String MESSAGE_PROPERTIES = "messageProperties";
	public static final String EXCEPTION_PROPERTIES = "exceptionProperties";	

	// properties
	protected static final Properties miProperties = new Properties();
	protected static final Properties messageProperties = new Properties();
	protected static final Properties exceptionProperties = new Properties();
	protected static final Properties wechatProperties = new Properties();
	
	private static final String DEFAULT_FOLDER = "properties/";
	
	private static Logger log = Logger.getLogger(PropertiesManager.class);

	static {
		loadProperties(MI_PROPERTIES_FILE,miProperties);
		loadProperties(MESSAGE_PROPERTIES_FILE,messageProperties);
		loadProperties(EXCEPTION_PROPERTIES_FILE,exceptionProperties);
	}
	
	//*******************************************************************************
	// to initialize the Olive properties.
	private static void loadProperties(String propertieFile,Properties propertie) {
		InputStream properties = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(DEFAULT_FOLDER+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+propertieFile);
		if (null != properties) {
			try {
				BufferedReader bf = new BufferedReader(new InputStreamReader(properties, "utf-8"));
				propertie.load(bf);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			log.error("PropertiesManager ERROR - can not load  properties:"+propertie);
		}
	}

	// the public interface for clients to retrieve the properties.
	public static String getVmtooPropertyAsString(String key) {
		if(null != key && miProperties.containsKey(key)) {
			return miProperties.getProperty(key);
		}
		
		return null;
	}

	
	
	public static String getMessage(String propertieType,String key){
		return getMessage(propertieType,key,null);
	}
	

	/** 
	* @Title: getMsg 
	* @Description: 直接获取Message信息
	* @param propertieType
	* @param msgKey
	* @param msgKeyValue
	* @return  
	*/ 
	public static String getMsg(String msgKey,Object... msgKeyValue){
		String message = null;
		message = PropertiesManager.getMessage(PropertiesManager.MESSAGE_PROPERTIES, msgKey,msgKeyValue);
		if(StringUtil.isEmpty(message)){
			message = PropertiesManager.getMessage(PropertiesManager.EXCEPTION_PROPERTIES, msgKey,msgKeyValue);
		}
		return message;
	}
	
	
	
	
	public static String getMessage(String propertieType,String key,Object... keyValues){
		Properties properties = getTargetProperties(propertieType);
		String realMsg = null;
		if(StringUtil.isNotEmpty(key)&&properties.containsKey(key)) {
			realMsg =  properties.getProperty(key);
			if(StringUtil.isNotEmpty(realMsg)&&keyValues!=null){
				realMsg = MessageFormat.format(realMsg, keyValues);
			}
			
		}
		return realMsg;
	}
	
	private static Properties getTargetProperties(String propertieType){
		Properties properties = null;
		switch(propertieType){
		case EXCEPTION_PROPERTIES:properties = exceptionProperties;break;
		case MESSAGE_PROPERTIES:properties = messageProperties;break;
		case MI_PROPERTIES:properties = miProperties;break;
		}
		return properties;		
	}

}