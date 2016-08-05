package com.bqsolo.main.service;

import com.bqsolo.framework.service.BaseService;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.framework.page.Criteria;
import com.bqsolo.main.entity.AdminEntity;
/** 
* @ClassName: AdminService 
* @Description: 本类是由代码生成器自动生成AdminEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月05日
*  
*/ 

public interface AdminService extends BaseService<AdminEntity, Integer> {
	

	public  PageBean<AdminEntity> pageQuery(Criteria<AdminEntity> criteria);
	
	

}
