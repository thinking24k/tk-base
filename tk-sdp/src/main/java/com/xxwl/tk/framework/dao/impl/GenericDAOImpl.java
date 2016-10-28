package com.xxwl.tk.framework.dao.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.attribute.DaoAttribute;
import com.xxwl.tk.framework.dao.GenericDAO;
import com.xxwl.tk.framework.dao.mapper.BaseMapper;
import com.xxwl.tk.framework.domain.BaseEntity;
import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.utils.EntityReflectUtil;
import com.xxwl.tk.framework.utils.MapUtil;

public class GenericDAOImpl<E extends BaseEntity , PK> implements GenericDAO<E, PK> {
	
	private static Logger logger = Logger.getLogger(GenericDAOImpl.class);
	
	private BaseMapper<E, PK> baseMapper;
	//子类重写提供独立的mapper
	public BaseMapper<E, PK> getBaseMapper() {
		return baseMapper;
	}

	@Override
	public long getCount(Criteria<E> criteria) {
		Map<String, Object> temp = doMapperMap(criteria);
		return getBaseMapper().getCount(temp);
	}

	@Override
	public List<E> queryForList(Criteria<E> criteria) {
		Map<String, Object> temp = doMapperMap(criteria);
		return getBaseMapper().queryForList(temp);
	}
	//把分页条件转换为map参数
	private Map<String, Object> doMapperMap(Criteria<E> criteria) {
		Map<String, Object> temp=MapUtil.objToMap(criteria.getParam());
		temp.put(DaoAttribute.QUERYFORLIST_SORTMAPNAME, criteria.getSortItemMap());
		if(null !=criteria.getExtFields()){
			temp.putAll(criteria.getExtFields());
		}
		return temp;
	}

	@Override
	public List<E> queryForPageList(Criteria<E> criteria) {
		Map<String, Object> temp = doMapperMap(criteria);
		if(null !=criteria.getPageBean()){
			temp.put(DaoAttribute.QUERYFORLIST_PAGEBEAN, criteria.getPageBean());
		}
		return getBaseMapper().queryForPageList(temp);
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
		autoAddinfo(e);
		return getBaseMapper().doAdd(e);
	}

	@Override
	public Integer doAddBatch(List<E> list) {
		return getBaseMapper().doAddBatch(list,list.get(0));
	}

	@Override
	public Integer doUpdate(E e) {
		autoChangeinfo(e);
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
		autoChangeinfo(e);
		return getBaseMapper().doRemove(e);
	}
	//自动装配新增属性
	private void autoAddinfo(E e){
		e.setCuid(1000);
		e.setCdate(new Date());
		e.setIsvoid(CommonAttribute.ISVOID_TRUE);
	}
	//自动装配修改属性
	private void autoChangeinfo(E e){
		e.setChangeuid(1000);
		e.setChangedate(new Date());
	}
	/**
	* <p>Title: autoPushBasicData</p>
	* <p>Description: 自动装配 创建/更新者 日期</p>
	* @param Entity
	* @param userFieldStr
	* @param dateFieldStr
	*/
	@SuppressWarnings("unused")
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
