package com.xxwl.tk.framework.service;

import java.util.List;

import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;

public interface BaseService<E, PK> {

	
	  public  long getCount(Criteria<E> criteria);

	  public  List<E> queryForList(Criteria<E> criteria);

	  public  List<E> queryForPageList(Criteria<E> criteria);
	  
	  public  E getById(PK  id);
	  
	  public  List<E> getByIds(List<PK>  ids);
	  
	  public Integer doAdd(E e);
	  
	  public Integer doAddBatch(List<E> list);
	  
	  public Integer doUpdate(E e);
	  
	  public Integer doDelete(PK id);
	  
	  public Integer doDeletes(List<PK> ids);
	  
	  public Integer doRemove(PK id);
	  
	  public  PageBean<E> pageQuery(Criteria<E> criteria);
	
}

