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
* @ClassName: UserEntity 
* @Description: 本类是由代码生成器自动生成UserEntity实体对象(Entity)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年07月28日
*  
*/ 
@Entity(name = "UserEntity")
@Table(name = "user")
public class UserEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  -5383635283483119182L;
	

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEntity(Integer id, String nickname, String email, String mobile,
			String password, String img, Double priority, Integer sex,
			Integer logintime) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.img = img;
		this.priority = priority;
		this.sex = sex;
		this.logintime = logintime;
	}
	//主键
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)	
	private Integer id;
	//用户名
	@Column(name = "nickname")
	private String nickname;
	//用户邮箱
	@Column(name = "email")
	private String email;
	//用户手机
	@Column(name = "mobile")
	private String mobile;
	//密码
	@Column(name = "password")
	private String password;
	//用户头像
	@Column(name = "img")
	private String img;
	//权重（风格）1-10 2位小数
	@Column(name = "priority")
	private Double priority;
	//性别
	@Column(name = "sex")
	private Integer sex;
	//登陆时间
	@Column(name = "logintime")
	private Integer logintime;
	
	/**主键*/
	public Integer getId() {
		return id;
	}
	/**主键*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**用户名*/
	public String getNickname() {
		return nickname;
	}
	/**用户名*/
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**用户邮箱*/
	public String getEmail() {
		return email;
	}
	/**用户邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}
	/**用户手机*/
	public String getMobile() {
		return mobile;
	}
	/**用户手机*/
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**密码*/
	public String getPassword() {
		return password;
	}
	/**密码*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**用户头像*/
	public String getImg() {
		return img;
	}
	/**用户头像*/
	public void setImg(String img) {
		this.img = img;
	}
	/**权重（风格）1-10 2位小数*/
	public Double getPriority() {
		return priority;
	}
	/**权重（风格）1-10 2位小数*/
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	/**性别*/
	public Integer getSex() {
		return sex;
	}
	/**性别*/
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**登陆时间*/
	public Integer getLogintime() {
		return logintime;
	}
	/**登陆时间*/
	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}

}
