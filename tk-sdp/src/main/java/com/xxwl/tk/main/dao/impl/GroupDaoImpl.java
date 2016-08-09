package com.xxwl.tk.main.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.xxwl.tk.main.entity.GroupEntity;
import com.xxwl.tk.main.dao.GroupDao;
import com.xxwl.tk.main.dao.mapper.GroupMapper;
import com.xxwl.tk.framework.dao.impl.GenericDAOImpl;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
/** 
* @ClassName: GroupDaoImpl
* @Description: 本类是由代码生成器自动生成GroupEntity持久层接口(DaoImpl)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 
@Repository
public class GroupDaoImpl  extends GenericDAOImpl<GroupEntity, Integer> implements GroupDao{
	@Resource
	private GroupMapper groupMapper;

	@Override
	public BaseMapper<GroupEntity, Integer> getBaseMapper() {
		return groupMapper;
	}

}
