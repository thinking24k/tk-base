package com.tk.base.builder.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: PropertiesUtil 
 * @Description: 配置文件[Properties]工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月1日 
 *
 */
public class PropertiesUtil {
	private static Logger logger=Logger.getLogger(PropertiesUtil.class);
	//私有构造方法
	private PropertiesUtil(){};
	private static PropertiesUtil propertiesUtil=new PropertiesUtil();//创建对象
	//使用饿汉式
	public static PropertiesUtil getPropertiesUtil(){
		return propertiesUtil;
	}

	/**
	 * 
	 * @Title: getPropertiesByRalPath 
	 * @Description: 根据相对路径获取Properties
	 * @param ralPath 相对于类加载路径
	 * @param fileName 文件名
	 * @return Properties
	 */
	public Properties getPropertiesByRalPath(String ralPath,String fileName){
		String path="";
		try {
			//获取类加载路径
			path = PropertiesUtil.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("读取配置文件："+path+ralPath+fileName);
		return getProperties(path+ralPath+fileName);
	}
	/**
	 * 
	 * @Title: getProperties 
	 * @Description: 根据本地路径获取Properties
	 * @param local 本地文件路径+文件名
	 * @return Properties
	 */
	public  Properties getProperties(String local){
		Properties ps=new Properties();
		FileInputStream in=null;
		try {
			//FileInputStream isr= new FileInputStream("src/db.properties");
			in=new FileInputStream(local);
			ps.load(in);
			return ps; 
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally{
			if(null!=in){
				try{
					in.close();
				}
				catch(IOException e){
					logger.error("FileInputStream关闭失败："+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	

}
