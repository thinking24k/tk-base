package com.bqsolo.main.service;

import java.util.Map;

import com.bqsolo.framework.page.PageBean;
import com.bqsolo.main.entity.UserInfo;
/**
 * 用户service
 * @author deng
 *
 */
public interface UserInfoService {
	/**
	 * 根据登录名获取用户
	 * @param name
	 * @return
	 */
	public UserInfo getByLoginName(String name);
	/**
	 * 登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	public UserInfo login(String name,String pwd);
	/**
	 * 验证用户名重复
	 * @param name
	 * @return
	 */
	public boolean checkName(String name);
	/**
	 * 验证邮箱重复
	 * @param name
	 * @return
	 */
	public boolean checkEmail(String name);
	
	/**
	 * 根据用户名或邮箱查询
	 * @param name
	 * @return
	 */
	public UserInfo getByLoginNameOrEmail(String name,String email);
	/**
	 * 根据用户邮箱获取用户
	 * @param email
	 * @return
	 */
	public UserInfo getByEmail(String email);
	/**
	 * 分页查询
	 * @param parm
	 * @return
	 */
	public PageBean<UserInfo> getByPage(PageBean<UserInfo> util);
	/**
	 * 查询总数
	 * @param parm
	 * @return
	 */
	public int getCount(Map<String, Object> parm);
	/**
	 * 更新用户所用信息
	 * @param userInfo
	 * @return
	 */
	public Integer updateUserInfo(UserInfo userInfo);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param pwd
	 * @return
	 */
	public Integer updateUserPwd(Integer userId,String password);
	/**
	 * 保存用户信息
	 * @param userInfo
	 * @return
	 */
	public Integer save(UserInfo userInfo, Map<String, String> info);
	
	
	/**
	 * 更新用户登录log信息
	 * @param userInfo
	 * @return
	 */
	public Integer updateUserLoginInfo(UserInfo userInfo);
	

	public  PageBean<UserInfo> pageQuery(PageBean<UserInfo> page);

}
