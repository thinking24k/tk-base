package com.bqsolo.framework.dao;

import java.util.List;

import com.bqsolo.framework.page.Criteria;


public  interface GenericDAO<E, PK>{
	
	  public  long getCount(Criteria<E> criteria);

	  public  List<E> queryForList(Criteria<E> criteria);

	  public  List<E> queryForPageList(Criteria<E> criteria);
	  
	  public  E getById(PK  pk);
	  
	  public  List<E> getByIds(List<PK>  pks);
	  
	  public Integer doAdd(E e);
	  
	  public Integer doAddBatch(List<E> list);
	  
	  public Integer doUpdate(E e);
	  
	  public Integer doDelete(PK pk);
	  
	  public Integer doDeletes(List<PK> pks);
	  
	  public Integer doRemove(E e);
	
}
