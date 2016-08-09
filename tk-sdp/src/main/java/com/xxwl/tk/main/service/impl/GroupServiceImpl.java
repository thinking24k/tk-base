package com.xxwl.tk.main.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.dao.GroupDao;
import com.xxwl.tk.main.entity.GroupEntity;
import com.xxwl.tk.main.service.GroupService;
/** 
* @ClassName: GroupService 
* @Description: 本类是由代码生成器自动生成GroupEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/
@Service
public class GroupServiceImpl implements GroupService {
	
	private static Logger logger = Logger.getLogger(GroupServiceImpl.class);
	
	@Resource
	private GroupDao groupDao;

	@Override
	public long getCount(Criteria<GroupEntity> criteria) {
		if(null == criteria){
			return 0L;
		}
		return groupDao.getCount(criteria);
	}

	@Override
	public List<GroupEntity> queryForList(Criteria<GroupEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return groupDao.queryForList(criteria);
	}

	@Override
	public List<GroupEntity> queryForPageList(Criteria<GroupEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return groupDao.queryForPageList(criteria);
	}

	@Override
	public GroupEntity getById(Integer pk) {
		if(null == pk){
			return null;
		}
		return groupDao.getById(pk);
	}

	@Override
	public List<GroupEntity> getByIds(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return groupDao.getByIds(pks);
	}

	@Override
	public Integer doAdd(GroupEntity group) {
		if(null == group){
			return null;
		}
		return groupDao.doAdd(group);
	}

	@Override
	public Integer doAddBatch(List<GroupEntity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return groupDao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(GroupEntity group) {
		if(null == group){
			return null;
		}
		return groupDao.doUpdate(group);
	}

	@Override
	public Integer doDelete(Integer pk) {
		if(null == pk){
			return null;
		}
		return groupDao.doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return groupDao.doDeletes(pks);
	}

	@Override
	public Integer doRemove(Integer id) {
		if(null == id){
			return null;
		}
		GroupEntity group=new GroupEntity();
		group.setId(id);
		return groupDao.doRemove(group);
	}

	@Override
	public PageBean<GroupEntity> pageQuery(Criteria<GroupEntity> criteria) {
		if(null == criteria){
			return null;
		}
		//获取总数
		long rowCount = groupDao.getCount(criteria);
		if(0 == rowCount ){
			return criteria.getPageBean();
		}
		criteria.getPageBean().setRowCount(rowCount);
		//获取列表
		List<GroupEntity> list = groupDao.queryForPageList(criteria);
		criteria.getPageBean().setData(list);
		return criteria.getPageBean();
	}
	
}
