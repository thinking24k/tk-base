package com.bqsolo.framework.dao.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bqsolo.framework.attribute.DaoAttribute;
import com.bqsolo.framework.dao.GenericDAO;
import com.bqsolo.framework.dao.mapper.BaseMapper;
import com.bqsolo.framework.page.Criteria;
import com.bqsolo.framework.utils.EntityReflectUtil;

public class GenericDAOImpl<E , PK> implements GenericDAO<E, PK> {
	
	private static Logger logger = Logger.getLogger(GenericDAOImpl.class);
	
	private BaseMapper<E, PK> baseMapper;

	public BaseMapper<E, PK> getBaseMapper() {
		return baseMapper;
	}

	@Override
	public long getCount(Criteria<E> criteria) {
		return getBaseMapper().getCount(criteria);
	}

	@Override
	public List<E> queryForList(Criteria<E> criteria) {
		return getBaseMapper().queryForList(criteria);
	}

	@Override
	public List<E> queryForPageList(Criteria<E> criteria) {
		return getBaseMapper().queryForPageList(criteria);
	}

	@Override
	public E getById(PK pk) {
		return getBaseMapper().getById(pk);
	}

	@Override
	public List<E> getByIds(List<PK> pks) {
		return getBaseMapper().getByIds(pks);
	}

	@Override
	public Integer doAdd(E e) {
		autoPushBasicData(e, DaoAttribute.CREATIONUSERID, DaoAttribute.CREATIONDATE);
		return getBaseMapper().doAdd(e);
	}

	@Override
	public Integer doAddBatch(List<E> list) {
		return getBaseMapper().doAddBatch(list,list.get(0));
	}

	@Override
	public Integer doUpdate(E e) {
		return getBaseMapper().doUpdate(e);
	}

	@Override
	public Integer doDelete(PK pk) {
		return getBaseMapper().doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<PK> pks) {
		return getBaseMapper().doDeletes(pks);
	}

	@Override
	public Integer doRemove(E e) {
		return getBaseMapper().doRemove(e);
	}
	/**
	* <p>Title: autoPushBasicData</p>
	* <p>Description: 自动装配 创建/更新者 日期</p>
	* @param Entity
	* @param userFieldStr
	* @param dateFieldStr
	*/
	private void autoPushBasicData(E entity,String userFieldStr,String dateFieldStr){

		Field userIdField = EntityReflectUtil.getField(entity.getClass(),userFieldStr);
		Field dateField = EntityReflectUtil.getField(entity.getClass(),dateFieldStr);
		userIdField.setAccessible(true);
		dateField.setAccessible(true);
		try {
			if(userIdField!=null){
				if(userIdField.get(entity)==null){
					EntityReflectUtil.setFieldValue(userIdField,007, entity);
				}
			}	
			if(dateField!=null){
				if(dateField.get(entity)==null){
					EntityReflectUtil.setFieldValue(dateField,new Date(), entity);
				}
			}
		} catch (Exception e) {
			logger.warn("新增自动补充创建者，创建日期时，相关标识字段无存在:"+entity.getClass().getSimpleName(),e);			
		}
	}

}
