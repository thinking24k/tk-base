package com.xxwl.tk.main.dao;

import com.xxwl.tk.framework.dao.GenericDAO;
import com.xxwl.tk.main.entity.UserEntity;
/** 
* @ClassName: UserDao
* @Description: 本类是由代码生成器自动生成UserEntity持久层接口(Dao)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月04日
*  
*/ 
public interface UserDao  extends GenericDAO<UserEntity, Integer>{

	UserEntity queryUser(UserEntity user);


}