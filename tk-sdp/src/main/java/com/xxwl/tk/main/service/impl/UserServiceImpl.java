package com.xxwl.tk.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.dao.UserDao;
import com.xxwl.tk.main.entity.UserEntity;
import com.xxwl.tk.main.service.UserService;
/** 
* @ClassName: UserService 
* @Description: 本类是由代码生成器自动生成UserEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月04日
*  
*/
@Service
public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao userDao;

	@Override
	public long getCount(Criteria<UserEntity> criteria) {
		if(null == criteria){
			return 0L;
		}
		return userDao.getCount(criteria);
	}

	@Override
	public List<UserEntity> queryForList(Criteria<UserEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return userDao.queryForList(criteria);
	}

	@Override
	public List<UserEntity> queryForPageList(Criteria<UserEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return userDao.queryForPageList(criteria);
	}

	@Override
	public UserEntity getById(Integer pk) {
		if(null == pk){
			return null;
		}
		return userDao.getById(pk);
	}

	@Override
	public List<UserEntity> getByIds(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return userDao.getByIds(pks);
	}

	@Override
	public Integer doAdd(UserEntity user) {
		if(null == user){
			return null;
		}
		return userDao.doAdd(user);
	}

	@Override
	public Integer doAddBatch(List<UserEntity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return userDao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(UserEntity user) {
		if(null == user){
			return null;
		}
		return userDao.doUpdate(user);
	}

	@Override
	public Integer doDelete(Integer pk) {
		if(null == pk){
			return null;
		}
		return userDao.doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return userDao.doDeletes(pks);
	}

	@Override
	public Integer doRemove(Integer id) {
		if(null == id){
			return null;
		}
		UserEntity user=new UserEntity();
		user.setId(id);
		return userDao.doRemove(user);
	}

	@Override
	public PageBean<UserEntity> pageQuery(Criteria<UserEntity> criteria) {
		if(null == criteria){
			return null;
		}
		//获取总数
		long rowCount = userDao.getCount(criteria);
		if(0 == rowCount ){
			return criteria.getPageBean();
		}
		criteria.getPageBean().setRowCount(rowCount);
		//获取列表
		List<UserEntity> list = userDao.queryForPageList(criteria);
		criteria.getPageBean().setData(list);
		return criteria.getPageBean();
	}

	
	
}
