package com.scrt.mi.generator.entity;

import java.io.Serializable;
import java.util.List;

import com.scrt.mi.generator.util.StringUtil;

/**
 * 
 * @ClassName: TableEntity 
 * @Description: 表实体
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月2日 
 *
 */
public class TableEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4517268718780400993L;
	
	private String tableName;//表名
	private String className;//类名
	private String packageName;//包名
	private String fileName;//保存的文件名
	//private String type;//类型dao,controll,service,serviceImpl,xml
	private String leves;
	private String annotations;//注释
	private List<ColumnEntity> columns;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		if(StringUtil.isEmpty(className)){
			//定义临时保存className
			StringBuilder tempClassName=new StringBuilder();
			String tempTable=this.tableName;
			if(tempTable!=null){//判断表名不为空
				//如果表名不含有小写字母标示不是java命名规范，名称全部转为小写
				if(!StringUtil.hasLowerChar(tempTable)){
					tempTable=tempTable.toLowerCase();
				}
				//如果表名中带有-或
				String[] split = StringUtil.splitTableName(tempTable);
				for (int j = 0; j < split.length; j++) {
					//循环拼接并把 特殊分割符后的首字母转换为大写
					tempClassName.append(StringUtil.firstCharToUpperCase(split[j]));
				}
				if(tempClassName.length()>0){//如果tempClassName
					this.className=tempClassName.toString();
				}else{
					this.className=StringUtil.firstCharToUpperCase(tempTable);
				}
			}
		}
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLeves() {
		return leves;
	}
	public void setLeves(String leves) {
		this.leves = leves;
	}
	public List<ColumnEntity> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}
	public String getAnnotations() {
		return annotations==null?"":annotations;
	}
	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}

}
