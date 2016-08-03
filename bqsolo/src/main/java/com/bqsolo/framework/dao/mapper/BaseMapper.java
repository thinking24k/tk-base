package com.bqsolo.framework.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bqsolo.framework.page.Criteria;

public interface BaseMapper<E, PK> {
	  public  long getCount(Map<String, Object> criteria);

	  public  List<E> queryForList(Map<String, Object> criteria);

	  public  List<E> queryForPageList(Map<String, Object> criteria);
	  
	  public  E getById(PK  id);
	  
	  public  List<E> getByIds(List<PK>  list);
	  
	  public Integer doAdd(E e);
	  
	  public Integer doAddBatch(@Param("list")List<E> list, @Param("e")E e);
	  
	  public Integer doUpdate(E e);
	  
	  public Integer doDelete(PK id);
	  
	  public Integer doDeletes(List<PK> list);
	  
	  public Integer doRemove(E e);
}
