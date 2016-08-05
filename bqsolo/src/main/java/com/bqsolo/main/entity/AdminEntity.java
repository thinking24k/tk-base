package com.bqsolo.main.entity;

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
import com.bqsolo.framework.json.JsonDateDeserializer;
import com.bqsolo.framework.json.JsonDateSerializer;
import com.bqsolo.framework.domain.BaseEntity;
/** 
* @ClassName: AdminEntity 
* @Description: 本类是由代码生成器自动生成AdminEntity实体对象(Entity)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月05日
*  
*/ 
@Entity(name = "AdminEntity")
@Table(name = "admin")
public class AdminEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  -7759851498989261707L;


	//
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)	
	private Integer id;
	//用户名
	@Column(name = "username")
	private String username;
	//用户密码
	@Column(name = "userpass")
	private String userpass;
	//用户邮箱
	@Column(name = "useremail")
	private String useremail;
	//登陆时间
	@Column(name = "logintime")
	private java.util.Date logintime;
	//登陆ip
	@Column(name = "loginip")
	private String loginip;
	
	/***/
	public Integer getId() {
		return id;
	}
	/***/
	public void setId(Integer id) {
		this.id = id;
	}
	/**用户名*/
	public String getUsername() {
		return username;
	}
	/**用户名*/
	public void setUsername(String username) {
		this.username = username;
	}
	/**用户密码*/
	public String getUserpass() {
		return userpass;
	}
	/**用户密码*/
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	/**用户邮箱*/
	public String getUseremail() {
		return useremail;
	}
	/**用户邮箱*/
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	/**登陆时间*/
	public java.util.Date getLogintime() {
		return logintime;
	}
	/**登陆时间*/
	public void setLogintime(java.util.Date logintime) {
		this.logintime = logintime;
	}
	/**登陆ip*/
	public String getLoginip() {
		return loginip;
	}
	/**登陆ip*/
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

}
