package com.bqsolo.main.service;



import com.bqsolo.framework.dao.GenericDAO;
import com.bqsolo.framework.page.Criteria;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.main.entity.AdminEntity;

public interface AdminService extends GenericDAO<AdminEntity, Integer> {
	

	public  PageBean<AdminEntity> pageQuery(Criteria<AdminEntity> criteria);
	
	

}
