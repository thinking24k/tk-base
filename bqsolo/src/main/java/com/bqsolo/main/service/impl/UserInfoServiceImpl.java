package com.bqsolo.main.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqsolo.assembly.md5andsha.MD5Util;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.main.dao.UserInfoDao;
import com.bqsolo.main.entity.UserInfo;
import com.bqsolo.main.service.UserInfoService;
import com.bqsolo.main.utils.Regex;
import com.bqsolo.main.utils.StringUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	/*@Autowired
	private MailSender  mailSender;*/

	@Override
	public UserInfo getByLoginName(String name) {
		
		return userInfoDao.getByLoginName(name);
	}

	@Override
	public UserInfo login(String name,String pwd) {
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(pwd)){
			return null;
		}
		UserInfo user =null;
		if(name.contains("@")){//email
			user= userInfoDao.getByEmail(name);
		}else{
			user=userInfoDao.getByLoginName(name);
		}
		if(user==null){
			return null;
		}
		pwd=MD5Util.md5Encode(pwd);
		if(pwd.equals(user.getPassword())){
			return user;
		}
		return null;
	}

	@Override
	public boolean checkName(String name) {
		return userInfoDao.getByLoginName(name)==null?false:true;
	}

	@Override
	public boolean checkEmail(String name) {
		
		return userInfoDao.getByEmail(name)==null?false:true;
	}

	@Override
	public UserInfo getByLoginNameOrEmail(String name, String email) {
		return userInfoDao.getByLoginNameOrEmail(name, email);
	}

	@Override
	public UserInfo getByEmail(String email) {
		// TODO Auto-generated method stub
		return userInfoDao.getByEmail(email);
	}

	@Override
	public PageBean<UserInfo> getByPage(PageBean<UserInfo> util) {
		/*int count = userInfoDao.getCount(util.getParameters());
		util.setRowCount(count);
		if(count>0){
			util.setData(userInfoDao.getByPage(util.getParameters()));
		}*/
		return null;
	}

	@Override
	public int getCount(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return userInfoDao.getCount(parm);
	}

	@Override
	public Integer updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserInfo(userInfo);
	}

	@Override
	public Integer updateUserPwd(Integer userId, String password) {
		// TODO Auto-generated method stub
		password=MD5Util.md5Encode(password);
		return userInfoDao.updateUserPwd(userId, password);
	}

	@Override
	public Integer save(UserInfo userInfo,Map<String, String> info) {
		if(StringUtils.isEmpty(userInfo)){
			info.put("user_info", "创建用户失败.");
			return -1;
		}
		if(StringUtils.isEmpty(userInfo.getUsername())){
			info.put("user_userName", "用户名为空.");
			return -1;
		}
		if(!Regex.regex(Regex.REGEXUSERNAME, userInfo.getUsername())){
			info.put("user_userName", "用户名2~14位汉字，数字或字母.");
			return -1;
		}
		if(this.checkName(userInfo.getUsername())){
			info.put("user_userName", "该用户名已经被使用.");
		}

		if(StringUtils.isEmpty(userInfo.getPassword())){
			info.put("user_password", "用户密码为空.");
			return -1;
		}
		if(!Regex.regex(Regex.REGEXUSERPWD, userInfo.getPassword())){
			info.put("user_password", "用户密码4~16位数字或字母.");
			return -1;
		}
		if(StringUtils.isEmpty(userInfo.getEmail())){
			info.put("user_email", "用户邮箱为空.");
			return -1;
		}
		if(!(0==userInfo.getGender()|| 1==userInfo.getGender())){
			info.put("user_gender", "用户性别不合法.");
			return -1;
		}
		userInfo.setPassword(MD5Util.md5Encode(userInfo.getPassword()));
		//没有昵称默认是登陆名
		if(StringUtils.isEmpty(userInfo.getNickname())){
			userInfo.setNickname(userInfo.getUsername());
		}
		//设置用户的可用状态
		userInfo.setStatus(UserInfo.STSAUS_TRUE);
		Integer save = userInfoDao.save(userInfo);
		if(save>0){
			if(userInfo.getEmail()==null ||userInfo.getEmail().trim().equals("")){
				Map<String, Object> temp=new  HashMap<String, Object>();
				temp.put("user.name", userInfo.getUsername());
				temp.put("user.regIp", userInfo.getRegIp());
				//mailSender.sendhtmlMail("dengyixiang_jy@126.com", userInfo.getEmail(), "衣style注册成功！", "register", temp);
			}
		}
		//密码
		return save;
	}

	@Override
	public Integer updateUserLoginInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserLoginInfo(userInfo);
	}

	@Override
	public PageBean<UserInfo> pageQuery(PageBean<UserInfo> page) {
		// TODO Auto-generated method stub
		return null;
	}

}
