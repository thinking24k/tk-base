package com.tk.base.builder.entity;

import com.tk.base.builder.attribute.CommonAttribute;
import com.tk.base.builder.util.ConfigUtil;
import com.tk.base.builder.util.StringUtil;
import com.tk.base.builder.util.WildcardEngine;

/**
 * 
 * @ClassName: Level 
 * @Description: 生成代码层次配置实体类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月3日 
 *
 */
public class Level {
	
	private String packagez;
	private String endStr;
	private String ftlName;
	private String fileSuffix;
	private String moduleProjectName;
	
	public Level() {
		super();
	}
	public Level(String packagez, String endStr, String ftlName,
			String fileSuffix) {
		super();
		this.packagez = packagez;
		this.endStr = endStr;
		this.ftlName = ftlName;
		this.fileSuffix = fileSuffix;
	}
	
	public Level(String packagez, String endStr, String ftlName,
			String fileSuffix, String moduleProjectName) {
		super();
		this.packagez = packagez;
		this.endStr = endStr;
		this.ftlName = ftlName;
		this.fileSuffix = fileSuffix;
		this.moduleProjectName = moduleProjectName;
	}
	public String getPackagez() {
		//为空直接返回
		if(StringUtil.isEmpty(packagez)){
			return packagez;
		}
		/*//通配字符
		String moduleName="${"+CommonAttribute.MODULENAME+"}";
		String platform="${"+CommonAttribute.MODULENAME+"}";
		String commonPackage="${"+CommonAttribute.COMMONPACKAGE+"}";
		String basePackage="${"+CommonAttribute.BASEPACKAGE+"}";
		if(packagez.contains(moduleName) ){
			packagez=packagez.replace(moduleName, ConfigUtil.getByKey(CommonAttribute.MODULENAME));
		}
		if(packagez.contains(platform) ){
			packagez=packagez.replace(platform, ConfigUtil.getByKey(CommonAttribute.PLATFORM));
		}
		if(packagez.contains(commonPackage) ){
			packagez=packagez.replace(commonPackage, ConfigUtil.getByKey(CommonAttribute.COMMONPACKAGE));
		}
		if(packagez.contains(basePackage) ){
			packagez=packagez.replace(basePackage, ConfigUtil.getByKey(CommonAttribute.BASEPACKAGE));
		}*/
		return WildcardEngine.doWildcard(packagez);
	}
	public void setPackagez(String packagez) {
		this.packagez = packagez;
	}
	public String getEndStr() {
		return endStr;
	}
	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	public String getFtlName() {
		return ftlName;
	}
	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public String getModuleProjectName() {
		return moduleProjectName;
	}
	public void setModuleProjectName(String moduleProjectName) {
		this.moduleProjectName = moduleProjectName;
	}
	
}
