package com.bqsolo.main.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.bqsolo.main.entity.AdminEntity;
import com.bqsolo.main.dao.AdminDao;
import com.bqsolo.main.dao.mapper.AdminMapper;
import com.bqsolo.framework.dao.impl.GenericDAOImpl;
import com.bqsolo.framework.dao.mapper.BaseMapper;
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
