package com.xxwl.tk.main.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.dao.PicDao;
import com.xxwl.tk.main.entity.PicEntity;
import com.xxwl.tk.main.service.PicService;
/** 
* @ClassName: PicService 
* @Description: 本类是由代码生成器自动生成PicEntity逻辑层(Service)
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/
@Service
public class PicServiceImpl implements PicService {
	
	private static Logger logger = Logger.getLogger(PicServiceImpl.class);
	
	@Resource
	private PicDao picDao;

	@Override
	public long getCount(Criteria<PicEntity> criteria) {
		if(null == criteria){
			return 0L;
		}
		return picDao.getCount(criteria);
	}

	@Override
	public List<PicEntity> queryForList(Criteria<PicEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return picDao.queryForList(criteria);
	}

	@Override
	public List<PicEntity> queryForPageList(Criteria<PicEntity> criteria) {
		if(null == criteria){
			return null;
		}
		return picDao.queryForPageList(criteria);
	}

	@Override
	public PicEntity getById(Integer pk) {
		if(null == pk){
			return null;
		}
		return picDao.getById(pk);
	}

	@Override
	public List<PicEntity> getByIds(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return picDao.getByIds(pks);
	}

	@Override
	public Integer doAdd(PicEntity pic) {
		if(null == pic){
			return null;
		}
		return picDao.doAdd(pic);
	}

	@Override
	public Integer doAddBatch(List<PicEntity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return picDao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(PicEntity pic) {
		if(null == pic){
			return null;
		}
		return picDao.doUpdate(pic);
	}

	@Override
	public Integer doDelete(Integer pk) {
		if(null == pk){
			return null;
		}
		return picDao.doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return picDao.doDeletes(pks);
	}

	@Override
	public Integer doRemove(Integer id) {
		if(null == id){
			return null;
		}
		PicEntity pic=new PicEntity();
		pic.setId(id);
		return picDao.doRemove(pic);
	}

	@Override
	public PageBean<PicEntity> pageQuery(Criteria<PicEntity> criteria) {
		if(null == criteria){
			return null;
		}
		//获取总数
		long rowCount = picDao.getCount(criteria);
		if(0 == rowCount ){
			return criteria.getPageBean();
		}
		criteria.getPageBean().setRowCount(rowCount);
		//获取列表
		List<PicEntity> list = picDao.queryForPageList(criteria);
		criteria.getPageBean().setData(list);
		return criteria.getPageBean();
	}
	
}
