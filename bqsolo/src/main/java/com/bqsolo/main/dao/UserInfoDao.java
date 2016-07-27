package com.bqsolo.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bqsolo.main.entity.UserInfo;
/**
 * 用户dao
 * @author deng
 *
 */
public interface UserInfoDao {
	/**
	 * 根据登录名获取用户
	 * @param name
	 * @return
	 */
	public UserInfo getByLoginName(String name);
	/**
	 * 根据用户名或邮箱查询
	 * @param name
	 * @return
	 */
	public UserInfo getByLoginNameOrEmail(@Param("name")String name,@Param("email")String email);
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
	public List<UserInfo> getByPage(Map<String, Object> parm);
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
	public Integer updateUserPwd(@Param("userId")Integer userId,@Param("pwd")String password);
	/**
	 * 保存用户信息
	 * @param userInfo
	 * @return
	 */
	public Integer save(UserInfo userInfo);
	
	
	/**
	 * 更新用户登录log信息
	 * @param userInfo
	 * @return
	 */
	public Integer updateUserLoginInfo(UserInfo userInfo);

}
