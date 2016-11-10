package com.xxwl.tk.main.service;

import java.util.List;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.xxwl.tk.framework.service.BaseService;
import com.xxwl.tk.main.entity.PicEntity;
import com.xxwl.tk.main.entity.model.MainPageModel;
/** 
* @ClassName: PicService 
* @Description: 本类是由代码生成器自动生成PicEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 

public interface PicService extends BaseService<PicEntity, Integer> {
	/**
	 * 
	 * @Title: queryMainPageData 
	 * @Description: 查询首页信息
	 * @param u 用户可为空，不为空按用户相关度推荐查询
	 * @return
	 */
	List<MainPageModel> queryMainPageData(User u);
	

	
	

}
