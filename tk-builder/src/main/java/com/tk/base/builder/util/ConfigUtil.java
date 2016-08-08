package com.tk.base.builder.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import com.tk.base.builder.attribute.CommonAttribute;
/**
 * 
 * @ClassName: ConfigUtil 
 * @Description: 缓存配置文件及操作配置文件工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月15日 
 *
 */
public class ConfigUtil {
	/**基本配置的缓存map*/
	public static Map<String, String> configs=new HashMap<String, String>();
	//静态加载
	static{
		PropertiesUtil propertiesUtil = PropertiesUtil.getPropertiesUtil();
		//Properties properties = propertiesUtil.getPropertiesByRalPath(CommonAttribute.CONFIG_FILE_PATH,CommonAttribute.CONFIG_FILE_NAME);
		Properties properties = propertiesUtil.getPropertiesByRalPath(CommonAttribute.PROPERTIES_FILE_RALPATH,CommonAttribute.CONFIG_FILE_NAME);
		load(properties);
		StringBuilder sb=new StringBuilder(ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE));
		if(!StringUtil.isEmpty(ConfigUtil.getByKey(CommonAttribute.PLATFORM))){
			sb.append(".");
			sb.append(ConfigUtil.getByKey(CommonAttribute.PLATFORM));
		}
		if(!StringUtil.isEmpty(ConfigUtil.getByKey(CommonAttribute.MODULENAME))){
			sb.append(".");
			sb.append(ConfigUtil.getByKey(CommonAttribute.MODULENAME));
		}
		
		configs.put(CommonAttribute.COMMONPACKAGE, sb.toString());
	}
	/**
	 * 
	 * @Title: getByKey 
	 * @Description: 根据key获取缓存集合中的值
	 * @param key 配置文件中的key
	 * @return String value
	 */
	public static  String getByKey(String key){
		return configs.get(key);
	}
	/**
	 * 
	 * @Title: put 
	 * @Description: 添加key-value
	 * @param key
	 * @param value
	 * @return
	 */
	public static  String put(String key,String value){
		return configs.put(key, value);
	}
	/**
	 * 
	 * @Title: load 
	 * @Description: 加载配置文件到内存中,方便使用
	 */
	public static void load(Properties properties){
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			configs.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
		}
	}
}
