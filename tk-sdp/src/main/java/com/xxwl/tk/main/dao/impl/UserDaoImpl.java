package com.xxwl.tk.main.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xxwl.tk.framework.dao.impl.GenericDAOImpl;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
import com.xxwl.tk.main.dao.UserDao;
import com.xxwl.tk.main.dao.mapper.UserMapper;
import com.xxwl.tk.main.entity.UserEntity;
/** 
* @ClassName: UserDaoImpl
* @Description: 本类是由代码生成器自动生成UserEntity持久层接口(DaoImpl)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月04日
*  
*/ 
@Repository
public class UserDaoImpl  extends GenericDAOImpl<UserEntity, Integer> implements UserDao{
	@Resource
	private UserMapper userMapper;

	@Override
	public BaseMapper<UserEntity, Integer> getBaseMapper() {
		return userMapper;
	}

}
