package com.xxwl.tk.main.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.xxwl.tk.main.entity.PicEntity;
import com.xxwl.tk.main.dao.PicDao;
import com.xxwl.tk.main.dao.mapper.PicMapper;
import com.xxwl.tk.framework.dao.impl.GenericDAOImpl;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
/** 
* @ClassName: PicDaoImpl
* @Description: 本类是由代码生成器自动生成PicEntity持久层接口(DaoImpl)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 
@Repository
public class PicDaoImpl  extends GenericDAOImpl<PicEntity, Integer> implements PicDao{
	@Resource
	private PicMapper picMapper;

	@Override
	public BaseMapper<PicEntity, Integer> getBaseMapper() {
		return picMapper;
	}

}
