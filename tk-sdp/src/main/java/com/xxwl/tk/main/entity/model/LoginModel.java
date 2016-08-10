package com.xxwl.tk.main.entity.model;
/**
 * 
 * @ClassName: LoginModel 
 * @Description: 登陆模型
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年8月10日 
 *
 */
public class LoginModel {
	private String username;
	private String pwd;
	private String authcode;
	private Integer remberme;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public Integer getRemberme() {
		return remberme;
	}
	public void setRemberme(Integer remberme) {
		this.remberme = remberme;
	}
	
	
}
