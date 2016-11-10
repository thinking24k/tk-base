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
* @ClassName: GroupEntity 
* @Description: 本类是由代码生成器自动生成GroupEntity实体对象(Entity)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 
@Entity(name = "GroupEntity")
@Table(name = "group")
public class GroupEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  -8227868773183233491L;


	//主键
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)	
	private Integer id;
	//组名
	@Column(name = "groupname")
	private String groupname;
	//简写
	@Column(name = "shorthand")
	private String shorthand;
	
	/**主键*/
	public Integer getId() {
		return id;
	}
	/**主键*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**组名*/
	public String getGroupname() {
		return groupname;
	}
	/**组名*/
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	/**简写*/
	public String getShorthand() {
		return shorthand;
	}
	/**简写*/
	public void setShorthand(String shorthand) {
		this.shorthand = shorthand;
	}

}
