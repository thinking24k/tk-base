package com.xxwl.tk.framework.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页工具类
 * @author deng
 *
 * @param <E>
 */
public class PageBean<E>  implements Serializable{

	private static final long serialVersionUID = -3078934592802686545L;

	private long rowCount;   // 总行数
	private List<E> data=Collections.emptyList();// 数据列表
	private long pageIndex=1;   // 页码
	private int  pageSize=10; // 页大小
	private long pageTotal;  // 总页数
	@SuppressWarnings("unused")
	private long dateNo;  	   // 数据库查询数据开始

	public PageBean() {
		super();
	}

	public PageBean(long pageIndex, int pageSize) {
		this.setPageIndex(pageIndex);
		this.setPageSize(pageSize);
	}



	/**   
	 * @Title: getPageTotal   
	 * @Description: 获取总页数
	 * @return int 总页数
	 */
	public long getPageTotal(){
		if (rowCount == 0) {
			pageTotal = 0;
		} else {
			pageTotal = rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		}
		return pageTotal;
	}



	/**   
	 * @Title: reviseIndexAndSize   
	 * @Description: 修正页码
	 * @param pageIndex 页码
	 */
	public void reviseIndex(long pageIndex){
		if (pageIndex <= 0) {
			this.pageIndex = 1;
		} else {
			this.pageIndex = pageIndex;
		}

		if (this.pageIndex > this.pageTotal) {
			this.pageIndex = this.pageTotal;
		} 

		if (this.pageIndex <= 0) {
			this.pageIndex = 1;
		}
	}

	public long getRowCount(){
		return rowCount;
	}

	public void setRowCount(long rowCount){
		this.rowCount = rowCount;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public long getPageIndex(){
		return pageIndex;
	}

	public void setPageIndex(long pageIndex){
		if(pageIndex <= 0){
			this.pageIndex=1;
		}else{
			this.pageIndex = pageIndex;
		}
	}

	public long getPageSize(){
		return pageSize;
	}



	public void setPageSize(int pageSize){
		if(pageSize<=0){
			this.pageSize=10;
		}else{
			this.pageSize = pageSize;
		}
	}

	public long getDateNo() {
		return (pageIndex-1)*pageSize;
	}

}
