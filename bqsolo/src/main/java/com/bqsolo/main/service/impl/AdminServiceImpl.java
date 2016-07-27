package com.bqsolo.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bqsolo.framework.page.Criteria;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.main.dao.AdminDao;
import com.bqsolo.main.entity.AdminEntity;
import com.bqsolo.main.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);
	
	@Resource
	private AdminDao adminDao;

	@Override
	public long getCount(Criteria<AdminEntity> criteria) {
		if(null == criteria){
			return 0L;
		}
		return adminDao.getCount(criteria);
	}

	@Override
	public List<AdminEntity> queryForList(Criteria<AdminEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return adminDao.queryForList(criteria);
	}

	@Override
	public List<AdminEntity> queryForPageList(Criteria<AdminEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return adminDao.queryForPageList(criteria);
	}

	@Override
	public AdminEntity getById(Integer pk) {
		if(null == pk){
			return null;
		}
		return adminDao.getById(pk);
	}

	@Override
	public List<AdminEntity> getByIds(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return adminDao.getByIds(pks);
	}

	@Override
	public Integer doAdd(AdminEntity e) {
		if(null == e){
			return null;
		}
		return adminDao.doAdd(e);
	}

	@Override
	public Integer doAddBatch(List<AdminEntity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return adminDao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(AdminEntity e) {
		if(null == e){
			return null;
		}
		return adminDao.doUpdate(e);
	}

	@Override
	public Integer doDelete(Integer pk) {
		if(null == pk){
			return null;
		}
		return adminDao.doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return adminDao.doDeletes(pks);
	}

	@Override
	public Integer doRemove(AdminEntity e) {
		if(null == e){
			return null;
		}
		return adminDao.doRemove(e);
	}

	@Override
	public PageBean<AdminEntity> pageQuery(Criteria<AdminEntity> criteria) {
		if(null == criteria){
			return null;
		}
		//获取总数
		long rowCount = adminDao.getCount(criteria);
		if(0 == rowCount ){
			return criteria.getPageBean();
		}
		criteria.getPageBean().setRowCount(rowCount);
		//获取列表
		List<AdminEntity> list = adminDao.queryForPageList(criteria);
		criteria.getPageBean().setData(list);
		return criteria.getPageBean();
	}
	

	
}
