package com.bqsolo.framework.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bqsolo.framework.page.Criteria;

public interface BaseMapper<E, PK> {
	  public  long getCount(E e);

	  public  List<E> queryForList(Criteria<E> criteria);

	  public  List<E> queryForPageList(Criteria<E> criteria);
	  
	  public  E getById(PK  id);
	  
	  public  List<E> getByIds(List<PK>  list);
	  
	  public Integer doAdd(E e);
	  
	  public Integer doAddBatch(List<E> list, E e);
	  
	  public Integer doUpdate(E e);
	  
	  public Integer doDelete(PK id);
	  
	  public Integer doDeletes(List<PK> list);
	  
	  public Integer doRemove(E e);
}
