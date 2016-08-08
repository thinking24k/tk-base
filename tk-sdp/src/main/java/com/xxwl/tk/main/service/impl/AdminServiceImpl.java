package com.xxwl.tk.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.dao.AdminDao;
import com.xxwl.tk.main.entity.AdminEntity;
import com.xxwl.tk.main.service.AdminService;
/** 
* @ClassName: AdminService 
* @Description: 本类是由代码生成器自动生成AdminEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月05日
*  
*/
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
	public Integer doAdd(AdminEntity admin) {
		if(null == admin){
			return null;
		}
		return adminDao.doAdd(admin);
	}

	@Override
	public Integer doAddBatch(List<AdminEntity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return adminDao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(AdminEntity admin) {
		if(null == admin){
			return null;
		}
		return adminDao.doUpdate(admin);
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
	public Integer doRemove(Integer id) {
		if(null == id){
			return null;
		}
		AdminEntity admin=new AdminEntity();
		admin.setId(id);
		return adminDao.doRemove(admin);
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
