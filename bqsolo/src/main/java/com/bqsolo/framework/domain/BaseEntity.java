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
	//删除标识默认不删除为1
	private static final Integer DEFAULT_ISVOID = 1;
	
	//创建者ID
	@Column(name = "cuid")
	protected Integer cuid;
	//创建人名称
	protected String cuname; 
	
	//创建日期
	@Column(name = "cdate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	protected Date cdate;	
	
	//修改者ID
	@Column(name = "upuid")
	protected Integer upuid;
	
	//修改人名称
	protected String upuname;
	
	//修改日期
	@Column(name = "update")
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	protected Date update;
	
	//删除标识  1:正常  0:删除
	@Column(name = "isvoid")	 
	protected Integer isvoid=DEFAULT_ISVOID;//默认为0 

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public String getCuname() {
		return cuname;
	}

	public void setCuname(String cuname) {
		this.cuname = cuname;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Integer getUpuid() {
		return upuid;
	}

	public void setUpuid(Integer upuid) {
		this.upuid = upuid;
	}

	public String getUpuname() {
		return upuname;
	}

	public void setUpuname(String upuname) {
		this.upuname = upuname;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Integer getIsvoid() {
		return isvoid;
	}

	public void setIsvoid(Integer isvoid) {
		this.isvoid = isvoid;
	}
	
}