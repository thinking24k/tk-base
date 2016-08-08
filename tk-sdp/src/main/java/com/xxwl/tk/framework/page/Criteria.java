package com.xxwl.tk.framework.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/** 
* @ClassName: Criteria 
* @Description: 查询条件基类，所有查询条件集成此类
* @company 
* @author x
* @Email x
* @date x
*  
*/ 
public class Criteria<E> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4127089270201263191L;
	/**
     * 排序方向-升序
     */
    public static final String SORT_DIRECTION_ASC = "ASC";
    /**
     * 排序方向-降序
     */
    public static final String SORT_DIRECTION_DESC = "DESC";
    
    protected static final String PAGE_LIMIT="limit";
    protected static final String PAGE_START="start";
    /**
     * 排序参数
     * key：属性字段，value：排序方向-asc or desc
     * @see Criteria#SORT_DIRECTION_ASC
     * @see Criteria#SORT_DIRECTION_DESC
     */
    private LinkedHashMap<String, String> sortItemMap;

    /**                                                                                  
     * 扩展属性字段
     */
    private Map<String, Object> extFields;
    
    /**
     * 分页对象
     */
    private PageBean<E> pageBean;
    /**
     * 实体对象
     */
    private E param;
    
    
    
    public PageBean<E> getPageBean() {
    	if(null == pageBean){
    		return new PageBean<E>();
    	}
		return pageBean;
	}

	public void setPageBean(PageBean<E> pageBean) {
		this.pageBean = pageBean;
	}

	

	public E getParam() {
		return param;
	}

	public void setParam(E param) {
		this.param = param;
	}

	/**
     * 添加扩展字段
     *
     * @param fieldName  字段名称
     * @param filedValue 字段值
     */
    public void addExtField(String fieldName, Object filedValue) {
        if (extFields == null) {
            extFields = new HashMap<String, Object>();
        }
        extFields.put(fieldName, filedValue);
    }

    public Map<String, Object> getExtFields() {
        return extFields;
    }

    public void setExtFields(Map<String, Object> extFields) {
        this.extFields = extFields;
    }


    public LinkedHashMap<String, String> getSortItemMap() {
        return sortItemMap;
    }

    public void setSortItemMap(LinkedHashMap<String, String> sortItemMap) {
        this.sortItemMap = sortItemMap;
    }
    
    public void addSortItem(String fieldName,String sortType){
    	if(sortItemMap==null){
    		sortItemMap = new LinkedHashMap<String, String>();
    	}
    	if(!SORT_DIRECTION_ASC.equalsIgnoreCase(sortType) && !SORT_DIRECTION_DESC.equalsIgnoreCase(sortType)){
    		sortType = SORT_DIRECTION_ASC;
    	}
    	sortItemMap.put(fieldName, sortType);
    }
    
    
    
}
