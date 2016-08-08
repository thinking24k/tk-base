package com.xxwl.tk.framework.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.xxwl.tk.framework.json.JsonDateDeserializer;
import com.xxwl.tk.framework.json.JsonDateSerializer;


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
	
	
	
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseEntity(Integer cuid, Date cdate, Integer isvoid) {
		super();
		this.cuid = cuid;
		this.cdate = cdate;
		this.isvoid = isvoid;
	}
	

	public BaseEntity(Integer changeuid, Date changedate) {
		super();
		this.changeuid = changeuid;
		this.changedate = changedate;
	}


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
	@Column(name = "changeuid")
	protected Integer changeuid;
	
	//修改人名称
	protected String upuname;
	
	//修改日期
	@Column(name = "changedate")
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	protected Date changedate;
	
	//删除标识  1:正常  0:删除
	@Column(name = "isvoid")	 
	protected Integer isvoid=DEFAULT_ISVOID;//默认为1



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

	public Integer getChangeuid() {
		return changeuid;
	}

	public void setChangeuid(Integer changeuid) {
		this.changeuid = changeuid;
	}

	public String getUpuname() {
		return upuname;
	}

	public void setUpuname(String upuname) {
		this.upuname = upuname;
	}

	public Date getChangedate() {
		return changedate;
	}

	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}

	public Integer getIsvoid() {
		return isvoid;
	}

	public void setIsvoid(Integer isvoid) {
		this.isvoid = isvoid;
	}

	
}