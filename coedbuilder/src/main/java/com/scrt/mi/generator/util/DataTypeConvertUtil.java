package com.scrt.mi.generator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.scrt.mi.generator.attribute.CommonAttribute;


/**
 * 
  * @ClassName: DataTypeConvertUtil 
  * @Description: 数据类型转换工具类，主要用于把数据库类型转换为java类型[类型分为sqlType,javaType,jdbcType]
  * @company 
  * @author yixiang.deng
  * @Email 553067271@qq.com
  * @date 2015年7月2日 
  *
 */
public class DataTypeConvertUtil {
	/**默认java类型*/
	public static final String DEFAULT_JAVA_TYPE="Object";
	/**默认sql类型*/
	public static final String DEFAULT_SQL_TYPE="VARCHAR";
	
	/**数据类型对应集合 key为sql类型(均为全大写) val为java类型*/
	public static Map<String, String> dataTypes=new HashMap<String, String>();
	/**数据类型对应集合 key为java基本数据类型(均为全小写) val为java包装类型*/
	public static Map<String, String> jdbcTypes=new HashMap<String, String>();
	//静态加载
	static{
		initJdbcType();
		PropertiesUtil propertiesUtil = PropertiesUtil.getPropertiesUtil();
		Properties properties = propertiesUtil.getPropertiesByRalPath(CommonAttribute.PROPERTIES_FILE_RALPATH,CommonAttribute.DTATYPE_FILE_NAME);
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			dataTypes.put(entry.getKey().toString().toUpperCase(),String.valueOf(entry.getValue()));
		}
	}
	/**
	 * 
	 * @Title: convertDataType 
	 * @Description: 根据sql类型获取java类型
	 * @param sqlType 数据库字段类型
	 * @return String 如com.long.String
	 */
	public static String convertDataType(String sqlType){
		if(StringUtil.isEmpty(sqlType)){
			return DEFAULT_JAVA_TYPE;
		}
		String javaType = dataTypes.get(sqlType.toUpperCase());
		return StringUtil.isEmpty(javaType)?DEFAULT_JAVA_TYPE:javaType;
	}
	/**
	 * 
	 * @Title: toJAVAPackageClass 
	 * @Description: TODO
	 * @param sqlType
	 * @return
	 */
	public static String convertJdbcType(String sqlType){
		if(StringUtil.isEmpty(sqlType)){
			return DEFAULT_SQL_TYPE;
		}
		//取得对应的包装类
		String jdbcType = jdbcTypes.get(sqlType.toLowerCase());
		//没有取到对应的jdbcType返回传入的类型
		return StringUtil.isEmpty(jdbcType)?sqlType:jdbcType;
	}
	//初始化java基本数据类型对应包装类
	private static void initJdbcType(){
		jdbcTypes.put("int", "Integer");
		jdbcTypes.put("datetime", "TIMESTAMP");
		jdbcTypes.put("text", "LONGVARCHAR");
	}
}
