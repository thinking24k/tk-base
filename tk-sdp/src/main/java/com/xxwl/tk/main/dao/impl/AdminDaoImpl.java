package com.xxwl.tk.main.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xxwl.tk.framework.dao.impl.GenericDAOImpl;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
import com.xxwl.tk.main.dao.AdminDao;
import com.xxwl.tk.main.dao.mapper.AdminMapper;
import com.xxwl.tk.main.entity.AdminEntity;
/** 
* @ClassName: AdminDaoImpl
* @Description: 本类是由代码生成器自动生成AdminEntity持久层接口(DaoImpl)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月05日
*  
*/ 
@Repository
public class AdminDaoImpl  extends GenericDAOImpl<AdminEntity, Integer> implements AdminDao{
	@Resource
	private AdminMapper adminMapper;

	@Override
	public BaseMapper<AdminEntity, Integer> getBaseMapper() {
		return adminMapper;
	}

}
