package com.bqsolo.main.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.bqsolo.main.entity.UserEntity;
import com.bqsolo.main.dao.UserDao;
import com.bqsolo.main.dao.mapper.UserMapper;
import com.bqsolo.framework.dao.impl.GenericDAOImpl;
import com.bqsolo.framework.dao.mapper.BaseMapper;
/** 
* @ClassName: UserDaoImpl
* @Description: 本类是由代码生成器自动生成UserEntity持久层接口(DaoImpl)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月01日
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
