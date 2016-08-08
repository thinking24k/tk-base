package com.tk.base.builder.service.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tk.base.builder.attribute.CommonAttribute;
import com.tk.base.builder.entity.ColumnEntity;
import com.tk.base.builder.entity.Level;
import com.tk.base.builder.entity.TableEntity;
import com.tk.base.builder.service.BuilderService;
import com.tk.base.builder.util.ConfigUtil;
import com.tk.base.builder.util.DateUtil;
import com.tk.base.builder.util.DbUtils;
import com.tk.base.builder.util.FileUtil;
import com.tk.base.builder.util.FreemarkUtil;
import com.tk.base.builder.util.LevelUtil;
import com.tk.base.builder.util.PdmReadUtil;
import com.tk.base.builder.util.SqlBuilder;
import com.tk.base.builder.util.StringUtil;
import com.tk.base.builder.util.ZipUtil;
@Service("builderServiceImpl")
public class BuilderServiceImpl implements BuilderService {
	private Logger logger=Logger.getLogger(BuilderServiceImpl.class);
	@Autowired
	private FreemarkUtil freemarkUtil;
	@Override
	public List<TableEntity> getAllTableBySql(){
		String url=ConfigUtil.getByKey(CommonAttribute.JDBC_URL);
		String name=ConfigUtil.getByKey(CommonAttribute.JDBC_USERNAME);
		String pwd=ConfigUtil.getByKey(CommonAttribute.jdbc_PASSWORD);
		DbUtils db=new DbUtils(); 
		Connection con = db.getCon(url,name,pwd);
		return db.getAllTables(con, ConfigUtil.getByKey(CommonAttribute.DATABASENAME));
	}
	@Override
	public List<Level>  getlevels(){
		return LevelUtil.levels;
	}

	@Override
	public Integer builderBySql(String tables) throws Exception {
		//获取配置
		//PropertiesUtil propertiesUtil = PropertiesUtil.getPropertiesUtil();
		//Properties properties = propertiesUtil.getPropertiesByRalPath(CommonAttribute.PROPERTIES_FILE_RALPATH, CommonAttribute.CONFIG_FILE_NAME);
		String url=ConfigUtil.getByKey(CommonAttribute.JDBC_URL);
		String name=ConfigUtil.getByKey(CommonAttribute.JDBC_USERNAME);
		String pwd=ConfigUtil.getByKey(CommonAttribute.jdbc_PASSWORD);
		DbUtils db=new DbUtils(); 
		Connection con =null;
		List<TableEntity> allTables = getAllTableBySql();
		List<TableEntity> findCheckTable = findCheckTable(tables, allTables);
		if(StringUtil.isEmpty(findCheckTable)){//数据库没有table
			return -1;
		}
		//删除也存在的目录
		new FileUtil().deleteFolder(ConfigUtil.getByKey(CommonAttribute.TEMP_PATH));
		for (TableEntity tableEntity : findCheckTable) {
			//获取列属性
			con = db.getCon(url,name,pwd);
			List<ColumnEntity> columnInfos = db.getColumnInfos(con,ConfigUtil.getByKey(CommonAttribute.DATABASENAME), tableEntity.getTableName());
			tableEntity.setColumns(columnInfos);
			create(tableEntity);//构建
		}
		//打包zip
		String inZipPath=ConfigUtil.getByKey(CommonAttribute.TEMP_PATH);//+ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME)+"/";
		String outZipPath=ConfigUtil.getByKey(CommonAttribute.ZIP_PATH);//+ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME)+".zip";
		logger.info("zip打包输入目录："+inZipPath);
		logger.info("zip打包输入目录："+outZipPath);
		new ZipUtil().doZip(inZipPath,outZipPath );
		return 1;
	}

	@Override
	public void create(TableEntity tableEntity) throws Exception{
		Map<String, Object> ftlMap = getFtlMap(tableEntity);
		String[] needLeves = getNeedLeves(tableEntity);
		for (Level leve : LevelUtil.levels) {
			//判断是否需要生成该层次代码
			if(isIncludes(leve.getPackagez(), needLeves)){
				//设置包名 共有包+独立包
				tableEntity.setPackageName(ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE)+"."+leve.getPackagez());
				//设置类名 表名首字母大写+结束字符  :test+Dao
				//tableEntity.setClassName(StringUtil.firstCharToUpperCase(tableEntity.getTableName())+leve.getEndStr());

				String fileName = getFileName(tableEntity, leve);
				String filePath = getFilePath(leve,false);
				freemarkUtil.saveFilebyStr(leve.getFtlName(), ftlMap, filePath,fileName);
			}
		}

	}
	//获取需要生成的层次
	private String[] getNeedLeves(TableEntity tableEntity){
		//获取前台勾选，并处理
		String leveStr = tableEntity.getLeves();
		if(StringUtil.isEmpty(leveStr)){
			return null;
		}
		return leveStr.split(",");
	}
	//获取生成ftl需要的参数
	private Map<String, Object> getFtlMap(TableEntity tableEntity){
		//freemark 模板参数map
		Map<String, Object> tempMap=new HashMap<String, Object>();
		//实体类结束字符
		String entityEndStr = LevelUtil.levels.get(0).getEndStr();
		entityEndStr=entityEndStr==null?"":entityEndStr;
		//简单实体类名
		//如果表名不含有小写字母标示不是java命名规范，名称全部转为小写
		String simpleEntityClassName = getSimpleClassName(tableEntity.getTableName());
		//添加属性
		tempMap.put(CommonAttribute.FTL_PARAM_TABLE, tableEntity);
		tempMap.put(CommonAttribute.FTL_PARAM_UUID, UUID.randomUUID().getLeastSignificantBits());
		tempMap.put(CommonAttribute.FTL_PARAM_NOWDATE, DateUtil.getNowDate(DateUtil.DATA_FORMAT));
		tempMap.put(CommonAttribute.FTL_PARAM_PARNAME, StringUtil.firstCharToLowerCase(tableEntity.getClassName()+entityEndStr));
		tempMap.put(CommonAttribute.BASEPACKAGE,ConfigUtil.getByKey(CommonAttribute.COMMONPACKAGE) );//ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE)+"."+ConfigUtil.getByKey(CommonAttribute.MODULENAME)
		//tempMap.put(CommonAttribute.FTL_PARAM_UPDATE_COL_SQLSTR, getUpdateParamSql(tableEntity.getColumns()));
		//tempMap.put(CommonAttribute.FTL_INSERT_SQLSTR, getInsertSql(tableEntity));
		tempMap.put(CommonAttribute.FTL_ALLCOLUMN_SQLSTR, getAllColumn(tableEntity.getColumns()));
		//实体类名=表的类名+entityEndStr
		tempMap.put(CommonAttribute.FTL_PARAM_ENTITYNAME, tableEntity.getClassName()+entityEndStr);
		//类名首字母小写
		tempMap.put(CommonAttribute.FTL_PARAM_LOWERCASERFIRSTCHAR_CLASSNAME, StringUtil.firstCharToLowerCase(tableEntity.getClassName()));
		//平台
		tempMap.put(CommonAttribute.PLATFORM, ConfigUtil.getByKey(CommonAttribute.PLATFORM));
		//生成作者
		tempMap.put(CommonAttribute.BUILDERAUTHER, ConfigUtil.getByKey(CommonAttribute.BUILDERAUTHER));
		//作者邮箱
		tempMap.put(CommonAttribute.BUILDEREMAIL, ConfigUtil.getByKey(CommonAttribute.BUILDEREMAIL));
		//子模块名
		tempMap.put(CommonAttribute.MODULENAME, ConfigUtil.getByKey(CommonAttribute.MODULENAME));
		//简单实体类名
		tempMap.put(CommonAttribute.FTL_PARAM_SIMPLE_ENTITYNAME, simpleEntityClassName);
		//insert sql
		tempMap.put(CommonAttribute.FTL_INSERT_SQLSTR, getInsertSql(tableEntity));
		return tempMap;
	}
	//获取简单类名
	private String getSimpleClassName(String tempTableName) {
		if(!StringUtil.hasLowerChar(tempTableName)){
			tempTableName=tempTableName.toLowerCase();
		}
		//如果表名中带有-或
		String[] split = StringUtil.splitTableName(tempTableName);
		return split[split.length-1];
	}
	//得到文件名
	private String getFileName(TableEntity tableEntity,Level leve){
		//设置文件名 test+Dao+.+java=testDao.java
		StringBuilder sb=new StringBuilder();
		//web文件
		if(isIncludes(leve.getFileSuffix(), CommonAttribute.SRC_WEBAPP_IN_FILE)){
			sb.append(getSimpleClassName(tableEntity.getTableName()));
		}else {//java文件及resource文件
			sb.append(tableEntity.getClassName());
			if(!tableEntity.getClassName().endsWith(leve.getEndStr())){
				sb.append(leve.getEndStr());
			}
		}
		sb.append(".");
		sb.append(leve.getFileSuffix());
		return sb.toString();
	}
	//@Test
	public void t1(){
		//base项目根目录
		String str=CommonAttribute.ROOT_PATH;
		str=str.substring(0, str.lastIndexOf(ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME)));
		System.out.println(str);
	}
	/**
	 * 
	 * @Title: getLocalProjectPath 
	 * @Description: 使用本地生成代码时获取项目的根目录
	 * @return
	 */
	public String getLocalProjectPath(){
		//项目启动路径
		String rootPath=CommonAttribute.ROOT_PATH;
		//待生成代码的base项目名称
		String baseProjectName = ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME);
		//代码生成器父级项目名
		String mi_base=CommonAttribute.MI_BASE;
		//配置文件配置的属性
		String localPath = ConfigUtil.getByKey(CommonAttribute.LOCAL_PATH);
		//非auto 手动指定的路径
		if(!(StringUtil.isEmpty(localPath) || CommonAttribute.AUTO.equalsIgnoreCase(localPath))){
			//文件路径是否是/结尾
			if(!localPath.endsWith(CommonAttribute.FILE_SEPARATORCHAR)){
				localPath=localPath+CommonAttribute.FILE_SEPARATORCHAR;
			}
		}else  if(rootPath.contains(baseProjectName)){//如果项目路径中带有根项目名称，直接截取
			localPath=rootPath.substring(0, rootPath.lastIndexOf(baseProjectName));
		}else if(rootPath.contains(mi_base)){//svn项目标准目录
			localPath=rootPath.substring(0, rootPath.lastIndexOf(mi_base));
		}else{
			throw new RuntimeException("无法获取本地项目路径，请手动指定config.properties的local_path属性！");
		}
		//修改缓存中的值，下次直接取出
		ConfigUtil.put(CommonAttribute.LOCAL_PATH, localPath);
		return localPath;
	}
	/**
	 * 
	 * @Title: getFilePath 
	 * @Description: 得到文件路径存放;boolean isInProject决定生成的代码是放在临时目录还是直接放入项目根目录
	 * @param leve
	 * @param isInProject
	 * @return
	 */
	public String getFilePath(Level leve,boolean isInProject){
		//文件路径
		StringBuilder filePath=new StringBuilder();
		if(isInProject){//是否生成代码直接放入项目目录文件下
			filePath.append(getLocalProjectPath());
		}else{
			//临时目录
			filePath.append(ConfigUtil.getByKey(CommonAttribute.TEMP_PATH));
		}
		//路径分割符
		filePath.append(CommonAttribute.FILE_SEPARATORCHAR);
		if(isIncludes(leve.getFileSuffix(), CommonAttribute.SRC_WEBAPP_IN_FILE)){
			//根项admin web项目名
			filePath.append(ConfigUtil.getByKey(CommonAttribute.ADMINPROJECTNAME));
		}else{
			//根项目名
			filePath.append(ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME));
		}
		//路径分割符
		filePath.append(CommonAttribute.FILE_SEPARATORCHAR);
		//子项目名
		filePath.append(leve.getModuleProjectName());
		//判断是否是java文件
		if(isIncludes(leve.getFileSuffix(), CommonAttribute.SRC_JAVA_IN_FILE)){
			//追加java文件存放路径
			filePath.append(CommonAttribute.APPENDPATH_JAVA_FILE);
			filePath.append(ConfigUtil.getByKey(CommonAttribute.COMMONPACKAGE));
			/*filePath.append(ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE));
			//子模块
			filePath.append(".");
			filePath.append(ConfigUtil.getByKey(CommonAttribute.MODULENAME));*/
		}else if(isIncludes(leve.getFileSuffix(), CommonAttribute.SRC_RESOURCES_IN_FILE)){
			//追加RESOURCES文件存放路径
			filePath.append(CommonAttribute.APPENDPATH_RESOURCES_FILE);
		}else if(isIncludes(leve.getFileSuffix(), CommonAttribute.SRC_WEBAPP_IN_FILE)){
			//追加web文件存放路径
			filePath.append(CommonAttribute.APPENDPATH_WEBAPP_FILE);
		}
		//basePackage后追加.
		filePath.append(".");
		//basePackage后追加对应的package
		filePath.append(leve.getPackagez());
		//追加文件分割符
		filePath.append(CommonAttribute.FILE_SEPARATORCHAR);
		//校验文件路径并返回
		return checkPath(filePath.toString());
	}

	//校验文件路径
	private String checkPath(String path){
		if(StringUtil.isEmpty(path)){
			return path;
		}
		do {
			path=path.replace(".", "/");
			//替换文件路径中多余的 杠 和包分隔符.
			path=path.replace("//", "/");
			path=path.replace("\\/", "/");
			path=path.replace("/\\", "/");
		} while (path.contains("//"));

		return path;
	}
	//在某个数组是否包含某个字符
	private boolean isIncludes(String str,String [] strs){
		if(StringUtil.isEmpty(str) || StringUtil.isEmpty(strs)){
			return false;
		}
		//循环比对该字符是否是数组中的一员
		for (int i = 0; i < strs.length; i++) {
			//如果是返回true
			if(str.equals(strs[i])){
				return true;
			}
		}
		return false;
	}
	//查找table并赋值Leves,标明该表需要生成那些层次
	private List<TableEntity> findCheckTable(String tables,List<TableEntity> tableEntities){
		//临时存储需要生成的表
		List<TableEntity> tempList=new ArrayList<TableEntity>();
		if(StringUtil.isEmpty(tables)){//没有待生成的的table
			return null;
		}
		if(StringUtil.isEmpty(tableEntities)){//数据库没有table
			return null;
		}
		//得到有哪些表需要生成
		String [] tableStr=tables.split("@");
		for (String tempTableStr : tableStr) {
			//得到某表的那些层次需要生成
			String[] split = tempTableStr.split(":");
			for (TableEntity tableEntity : tableEntities) {
				//判断需要该表是否需要生成
				if(tableEntity.getTableName().equals(split[0])){
					//设置该表需要生成的层次结构
					tableEntity.setLeves(split[1]);
					//添加到临时集合中
					tempList.add(tableEntity);
				}
			}
		}
		return tempList;
	}
	@Override
	public List<TableEntity> getAllTableByPdm(InputStream inputStream) {
		Document doc = PdmReadUtil.getDoc(inputStream);
		return PdmReadUtil.parsePDM_VO(doc);
	}
	@Override
	public Integer builderByPdm(String tables, List<TableEntity> tableEntities) throws Exception {
		List<TableEntity> findCheckTable = findCheckTable(tables, tableEntities);
		if(StringUtil.isEmpty(findCheckTable)){//数据库没有table
			return -1;
		}
		//删除也存在的目录
		new FileUtil().deleteFolder(ConfigUtil.getByKey(CommonAttribute.TEMP_PATH));
		for (TableEntity tableEntity : findCheckTable) {
			create(tableEntity);//构建
		}
		//打包zip
		String inZipPath=ConfigUtil.getByKey(CommonAttribute.TEMP_PATH);//+ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME)+"/";
		String outZipPath=ConfigUtil.getByKey(CommonAttribute.ZIP_PATH);//+ConfigUtil.getByKey(CommonAttribute.BASEPROJECTNAME)+".zip";
		logger.info("zip打包输入目录："+inZipPath);
		logger.info("zip打包输入目录："+outZipPath);
		new ZipUtil().doZip(inZipPath,outZipPath );
		return 1;
	}
	//得到查询allColumn参数
	private String getAllColumn(List<ColumnEntity> columnEntity){
		if(StringUtil.isEmpty(columnEntity)){
			return "";
		}
		//拼接所有的列
		StringBuilder sb=new StringBuilder();
		for (ColumnEntity col : columnEntity) {
			sb.append(col.getColumnName());
			sb.append(",");
		}
		//如果字符的长度>1表示后面有,号需要截取
		if(sb.length()>0){
			return sb.substring(0, sb.length()-1);
		}
		return sb.toString();
	}
	/*//得到update set参数
	private String getUpdateParamSql(List<ColumnEntity> columnEntity){
		if(StringUtil.isEmpty(columnEntity)){
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for (ColumnEntity col : columnEntity) {
			if(!ColumnEntity.YES.equals(col.getIsPrimaryKey())){
				sb.append(col.getColumnName());
				sb.append("=#{");
				sb.append(col.getClassAttr());
				sb.append("},");
			}
		}
		if(sb.length()>0){
			return sb.substring(0, sb.length()-1);
		}
		return sb.toString();
	}*/

	//得到insert sql
	private String getInsertSql(TableEntity tableEntity){
		List<ColumnEntity> columns = tableEntity.getColumns();
		if(StringUtil.isEmpty(columns)){
			return "";
		}
		StringBuilder insertSql=new StringBuilder();
		insertSql.append(" insert into ");
		insertSql.append(tableEntity.getTableName());
		insertSql.append(" ( \n ");

		StringBuilder sbPar=new StringBuilder();
		StringBuilder sbVAl=new StringBuilder();
		for (ColumnEntity col : columns) {
			if(col.getColumnName().equals("isvoid")){
				continue;//isvoid 字段默认值
			}
			//不是主键
			if(!ColumnEntity.YES.equals(col.getIsPrimaryKey())){
				sbVAl.append(SqlBuilder.sqlAppendIF(col.getClassAttr(), false));
				sbPar.append(SqlBuilder.sqlAppendIF(col.getColumnName(), true));
			}else{
				//id是不是自增,需拼接id
				if(!ColumnEntity.YES.equals(col.getIsAutoIncrement())){
					sbVAl.append(SqlBuilder.sqlAppend(col.getClassAttr(), false));
					sbPar.append(SqlBuilder.sqlAppend(col.getColumnName(), true));
				}
			}
		}
		insertSql.append(sbPar.toString());
		insertSql.append(" \n isvoid) values ( \n");
		insertSql.append(sbVAl.toString());
		insertSql.append(" \n #{isvoid})");
		return insertSql.toString();
	}

	
	@Override
	public Integer builderAllBySql() throws Exception {
		String url=ConfigUtil.getByKey(CommonAttribute.JDBC_URL);
		String name=ConfigUtil.getByKey(CommonAttribute.JDBC_USERNAME);
		String pwd=ConfigUtil.getByKey(CommonAttribute.jdbc_PASSWORD);
		DbUtils db=new DbUtils(); 
		Connection con =null;
		List<TableEntity> allTables = getAllTableBySql();
		if(StringUtil.isEmpty(allTables)){//数据库没有table
			return -1;
		}
		for (TableEntity tableEntity : allTables) {
			//获取列属性
			con = db.getCon(url,name,pwd);
			List<ColumnEntity> columnInfos = db.getColumnInfos(con,ConfigUtil.getByKey(CommonAttribute.DATABASENAME) ,tableEntity.getTableName());
			tableEntity.setColumns(columnInfos);
			createNoIf(tableEntity);//构建
		}
		return 1;
	}
	@Override
	public Integer builderAllBySql(String ... tableName) throws Exception {
		if(StringUtil.isEmpty(tableName)){
			return -1;
		}
		//临时存储需要生成的表格名
		List<TableEntity> tempTables=getNeedCreateTable(getAllTableBySql(), tableName);

		String url=ConfigUtil.getByKey(CommonAttribute.JDBC_URL);
		String name=ConfigUtil.getByKey(CommonAttribute.JDBC_USERNAME);
		String pwd=ConfigUtil.getByKey(CommonAttribute.jdbc_PASSWORD);
		DbUtils db=new DbUtils(); 
		Connection con =null;
		for (TableEntity tableEntity : tempTables) {
			//获取列属性
			con = db.getCon(url,name,pwd);
			List<ColumnEntity> columnInfos = db.getColumnInfos(con,ConfigUtil.getByKey(CommonAttribute.DATABASENAME) ,tableEntity.getTableName());
			tableEntity.setColumns(columnInfos);
			createNoIf(tableEntity);//构建
		}
		return 1;
	}
	@Override
	public Integer builderAllByPdm(InputStream inputStream,String ... tableName) throws Exception {
		//临时存储需要生成的表格名
		List<TableEntity> allTableByPdm=getNeedCreateTable(this.getAllTableByPdm(inputStream), tableName);
		if(StringUtil.isEmpty(allTableByPdm)){//数据库没有table
			return -1;
		}
		for (TableEntity tableEntity : allTableByPdm) {
			createNoIf(tableEntity);//构建
		}
		return 1;
	}
	//获取需要生成的表,tableNames为空时表示全部生成
	private List<TableEntity> getNeedCreateTable(List<TableEntity> allTables,String[] tableNames){
		//临时存储需要生成的表格名
		List<TableEntity> tempTables=new ArrayList<TableEntity>();
		if(StringUtil.isEmpty(allTables)){//数据库没有table
			return null;
		}
		a:for (TableEntity te : allTables) {
			for (String tn:tableNames) {
				if(te.getTableName().equalsIgnoreCase(tn)){
					tempTables.add(te);
					continue a;
				}
			}
		}
		return tempTables;
	}
	//公共pacjkage
	//private static String CommonPackage=ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE)+"."+ConfigUtil.getByKey(CommonAttribute.MODULENAME)+"."+ConfigUtil.getByKey(CommonAttribute.PLATFORM);
	
	@Override
	public void createNoIf(TableEntity tableEntity) throws Exception{
		Map<String, Object> ftlMap = getFtlMap(tableEntity);
		for (Level leve : LevelUtil.levels) {
			//设置包名 共有包+独立包
			tableEntity.setPackageName(ConfigUtil.getByKey(CommonAttribute.COMMONPACKAGE)+"."+leve.getPackagez());
			//设置类名 表名首字母大写+结束字符  :test+Dao
			//tableEntity.setClassName(StringUtil.firstCharToUpperCase(tableEntity.getTableName())+leve.getEndStr());

			String fileName = getFileName(tableEntity, leve);
			String filePath = getFilePath(leve,true);
			freemarkUtil.saveFilebyStr(leve.getFtlName(), ftlMap, filePath,fileName);
			logger.info("生成成功（路径）: "+filePath+fileName);
		}
	}
	@Override
	public Integer builderAllByPdm(InputStream inputStream) throws Exception {
		//临时存储需要生成的表格名
		List<TableEntity> allTableByPdm=this.getAllTableByPdm(inputStream);
		if(StringUtil.isEmpty(allTableByPdm)){//数据库没有table
			return -1;
		}
		for (TableEntity tableEntity : allTableByPdm) {
			createNoIf(tableEntity);//构建
		}
		return 1;
	}
}
