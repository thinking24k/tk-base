package com.bqsolo.framework.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bqsolo.framework.json.JsonDateDeserializer;
import com.bqsolo.framework.json.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;


/** 
* @ClassName: BaseEntity 
* @Description: 数据库表基类
* @company 
* @author x
* @Email x
* @date x
*  
*/ 
@JsonInclude(Include.NON_NULL) 
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 8588831511975331228L;
	
	//创建者ID
	@Column(name = "creationuserid")
	protected Integer creationuserid;
	//创建人名称
	protected String creationusername; 
	
	//创建日期
	@Column(name = "creationdate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	protected Date creationdate;	
	
	//修改者ID
	@Column(name = "changeuserid")
	protected Integer changeuserid;
	//修改人名称
	
	protected String changeusername;
	//修改日期
	@Column(name = "changedate")
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	protected Date changedate;
	
	//删除标识  0:正常  1:删除
	@Column(name = "isvoid")	 
	protected Integer isvoid;


	public Integer getIsvoid() {
		if(null==isvoid){
			this.isvoid=0;//默认为0 
		}
		return isvoid;
	}

	public void setIsvoid(Integer isvoid) {
		this.isvoid = isvoid;
	}

	public Integer getCreationuserid() {
		return creationuserid;
	}

	public void setCreationuserid(Integer creationuserid) {
		this.creationuserid = creationuserid;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Integer getChangeuserid() {
		return changeuserid;
	}

	public void setChangeuserid(Integer changeuserid) {
		this.changeuserid = changeuserid;
	}

	public Date getChangedate() {
		return changedate;
	}

	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}

	public String getCreationusername() {
		return creationusername;
	}

	public void setCreationusername(String creationusername) {
		this.creationusername = creationusername;
	}

	public String getChangeusername() {
		return changeusername;
	}

	public void setChangeusername(String changeusername) {
		this.changeusername = changeusername;
	}
	
	
	


}