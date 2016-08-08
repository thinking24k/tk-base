package com.tk.base.builder.attribute;
/**
* @ClassName: CommonAttribute 
* @Description: 系统基本常量, 表现层请求常量等
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2015年6月30日 
*
 */
public class CommonAttribute {
	/**基本配置文件相对路径*/
	public static final String  PROPERTIES_FILE_RALPATH="properties/";
	/**系统配置文件名*/
	public static final String  CONFIG_FILE_NAME="config.properties";
	/**类型映射配置*/
	public static final String  DTATYPE_FILE_NAME="dataTypeConvert.properties";
	
	/**基本常量*/
	public static final String  AUTO="AUTO";
	/**代码生成器上级项目*/
	public static final String  MI_BASE="mi-base";
	
	/**JDBC jdbc_driver (key)*/
	public static final String  JDBC_DRIVER="jdbc_driver";
	/**JDBC jdbc_url (key)*/
	public static final String  JDBC_URL="jdbc_url";
	/**JDBC jdbc_username (key)*/
	public static final String  JDBC_USERNAME="jdbc_username";
	/**JDBC jdbc_password (key)*/
	public static final String  jdbc_PASSWORD="jdbc_password";
	/**JDBC databaseName (key)*/
	public static final String  DATABASENAME="databaseName";
	
	/**程序中共有包：commonPackage=BASEPACKAGE+PLATFORM+MODULENAME*/
	public static final String  COMMONPACKAGE="commonPackage";
	/**基础包*/
	public static final String  BASEPACKAGE="basePackage";
	/**平台*/
	public static final String  PLATFORM="platform";
	/**模块名*/
	public static final String  MODULENAME="moduleName";
	/**生成代码临时存放位置(key)*/
	public static final String TEMP_PATH="temp_path";
	/**压缩文件临时存放位置 (key)*/
	public static final String ZIP_PATH="zip_path";
	/**#项目本地根目录（mi_base上级目录）*/
	public static final String LOCAL_PATH="local_path";
	
	/**路径分割符号*/
	public static final String  FILE_SEPARATORCHAR="/";
	
	/**实体类包路径 (key)*/
	public static final String  PACKAGE="package";
	/**实体类名的后缀 (key)*/
	public static final String  ENDSTR="endStr";
	/**实体类模板文件名 (key)*/
	public static final String  FTLNAME="ftlName";
	/**文件后缀(key)*/
	public static final String  FILESUFFIX="fileSuffix";
	/**根项目名(key)*/
	public static final String  BASEPROJECTNAME="baseProjectName";
	/**后台管理web*/
	public static final String  ADMINPROJECTNAME="adminProjectName";
	/**子项目名(key)*/
	public static final String  MODUALPROJECTNAME="moduleProjectName";
	/**生成作者*/
	public static final String  BUILDERAUTHER="builderAuthor";
	/**作者邮箱*/
	public static final String  BUILDEREMAIL="builderEmail";
	
	
	
	/**freemarker 参数*/
	public static final String  FTL_PARAM_TABLE="tableEntity";
	/**freemarker 参数*/
	public static final String  FTL_PARAM_UUID="serialVersionUID";
	/**freemarker 参数*/
	public static final String  FTL_PARAM_NOWDATE="nowDate";
	/**freemarker 参数*/
	public static final String  FTL_PARAM_UPDATE_COL_SQLSTR="UPDATEPARAMSQL";
	/**freemarker 参数*/
	public static final String  FTL_INSERT_SQLSTR="insertSqlMaker";
	/**freemarker 参数*/
	public static final String  FTL_ALLCOLUMN_SQLSTR="allColumn";
	/**freemarker 参数*/
	public static final String  FTL_PARAM_ENTITYNAME="entityName";
	/**freemarker 参数*/
	public static final String  FTL_PARAM_PARNAME="entityParName";
	/**freemarker 参数 --类名首字母小写*/
	public static final String  FTL_PARAM_LOWERCASERFIRSTCHAR_CLASSNAME="lowercaseClassName";
	/**freemarker 参数 --子模块名*/
	//public static final String  FTL_PARAM_SUBMODULE="subModule";
	/**freemarker 参数 --简单的实体类名全小写 如：MiDemo => demo */
	public static final String  FTL_PARAM_SIMPLE_ENTITYNAME="simpleEntityName";
	
	
	/**数据库表名分割字符 如：mi_test*/
	public static final String[]  TABLE_CHARS={"_","-"};
	/**项目根目录 如：*/
	public static String ROOT_PATH = System.getProperty("user.dir");
	/**系统文件分割符 如：linux=/ windows=\ */
	public static String SEPARATOR = System.getProperty("file.separator");
	
	
	/**java目录下存放的文件类型*/
	public static final String  SRC_JAVA_IN_FILE[] = {"java","class"};
	/**resources目录下存放的文件类型*/
	public static final String  SRC_RESOURCES_IN_FILE[] = {"xml","ftl","properties"};
	/**webapp目录下存放的文件类型*/
	public static final String  SRC_WEBAPP_IN_FILE[]={"jsp","js","css","html"};
	
	/**保存java文件项目后默认追加src/main/java*/
	public static final String  APPENDPATH_JAVA_FILE="/src/main/java/";
	/**保存resources文件项目后默认追加src/main/resources*/
	public static final String  APPENDPATH_RESOURCES_FILE="/src/main/resources/";
	/**保存web文件项目后默认追加src/main/webapp*/
	public static final String  APPENDPATH_WEBAPP_FILE="/src/main/webapp/";
	
	
	

}
