package com.xxwl.tk.main.service;

import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.framework.service.BaseService;
import com.xxwl.tk.main.entity.UserEntity;
/** 
* @ClassName: UserService 
* @Description: 本类是由代码生成器自动生成UserEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月04日
*  
*/ 

public interface UserService extends BaseService<UserEntity, Integer> {
	

	public  PageBean<UserEntity> pageQuery(Criteria<UserEntity> criteria);
	
	

}
