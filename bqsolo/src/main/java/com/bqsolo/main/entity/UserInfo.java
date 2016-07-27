package com.bqsolo.main.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * @author deng
 *
 */
public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2792583355923128885L;
	private Integer id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private Byte gender;
	private String intro;
	private String phone;
	private Long birthday;
	private String address;
	private Integer score;
	private String regIp;
	private Long regTime;
	private Long lastTime;
	private String lastIp;
	private Integer loginCount;
	private Byte status;
	//静态常量
	/**有效状态*/
	public static final Byte STSAUS_TRUE=1;
	/**无效状态*/
	public static final Byte STSAUS_FALSE=0;
	
	
	public UserInfo() {
		super();
	}
	public UserInfo(String username, String password, String email,
			Byte gender, String regIp, Long regTime, Long lastTime,
			String lastIp) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.regIp = regIp;
		this.regTime = regTime;
		this.lastTime = lastTime;
		this.lastIp = lastIp;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public Long getRegTime() {
		return regTime;
	}
	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}
	public Long getLastTime() {
		return lastTime;
	}
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
