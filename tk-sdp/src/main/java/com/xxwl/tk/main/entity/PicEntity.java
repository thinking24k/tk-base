package com.xxwl.tk.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.xxwl.tk.framework.json.JsonDateDeserializer;
import com.xxwl.tk.framework.json.JsonDateSerializer;
import com.xxwl.tk.framework.domain.BaseEntity;
/** 
* @ClassName: PicEntity 
* @Description: 本类是由代码生成器自动生成PicEntity实体对象(Entity)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 
@Entity(name = "PicEntity")
@Table(name = "pic")
public class PicEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  -6449605932800944480L;


	//主键
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)	
	private Integer id;
	//逻辑名称(bqsolo.top.{userid}[5位随机数])
	@Column(name = "logicname")
	private String logicname;
	//标题
	@Column(name = "title")
	private String title;
	//地址
	@Column(name = "path")
	private String path;
	//权重（风格）1-10 2位小数
	@Column(name = "priority")
	private Double priority;
	//标签 多个用，号
	@Column(name = "lable")
	private String lable;
	//文件md5
	@Column(name = "MD5")
	private String mD5;
	//点击
	@Column(name = "click")
	private Integer click;
	//组
	@Column(name = "gruopid")
	private Integer gruopid;
	
	/**主键*/
	public Integer getId() {
		return id;
	}
	/**主键*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**逻辑名称(bqsolo.top.{userid}[5位随机数])*/
	public String getLogicname() {
		return logicname;
	}
	/**逻辑名称(bqsolo.top.{userid}[5位随机数])*/
	public void setLogicname(String logicname) {
		this.logicname = logicname;
	}
	/**标题*/
	public String getTitle() {
		return title;
	}
	/**标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**地址*/
	public String getPath() {
		return path;
	}
	/**地址*/
	public void setPath(String path) {
		this.path = path;
	}
	/**权重（风格）1-10 2位小数*/
	public Double getPriority() {
		return priority;
	}
	/**权重（风格）1-10 2位小数*/
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	/**标签 多个用，号*/
	public String getLable() {
		return lable;
	}
	/**标签 多个用，号*/
	public void setLable(String lable) {
		this.lable = lable;
	}
	/**点击*/
	public Integer getClick() {
		return click;
	}
	/**点击*/
	public void setClick(Integer click) {
		this.click = click;
	}
	/**组*/
	public Integer getGruopid() {
		return gruopid;
	}
	/**组*/
	public void setGruopid(Integer gruopid) {
		this.gruopid = gruopid;
	}
	
	public String getMD5() {
		return mD5;
	}
	public void setMD5(String mD5) {
		this.mD5 = mD5;
	}
	@Override
	public String toString() {
		return "PicEntity [id=" + id + ", logicname=" + logicname + ", title="
				+ title + ", path=" + path + ", priority=" + priority
				+ ", lable=" + lable + ", mD5=" + mD5 + ", click=" + click
				+ ", gruopid=" + gruopid + "]";
	}

}
