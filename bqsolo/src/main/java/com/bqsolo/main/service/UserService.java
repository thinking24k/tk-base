package com.bqsolo.main.service;

import com.bqsolo.framework.page.Criteria;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.framework.service.BaseService;
import com.bqsolo.main.entity.UserEntity;
/** 
* @ClassName: UserService 
* @Description: 本类是由代码生成器自动生成UserEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月01日
*  
*/ 

public interface UserService extends BaseService<UserEntity, Integer> {
	

	public  PageBean<UserEntity> pageQuery(Criteria<UserEntity> criteria);
	
	

}
