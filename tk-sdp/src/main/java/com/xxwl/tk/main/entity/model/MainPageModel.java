package com.xxwl.tk.main.entity.model;

import java.io.Serializable;
import java.util.List;

import com.xxwl.tk.main.entity.PicEntity;
/**
 * 
 * @ClassName: MainPageModel 
 * @Description: 首页聚合model
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年11月8日 
 *
 */
public class MainPageModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8946889304528167732L;
	//组id
	private Integer id;
	//组名
	private String groupname;
	//简写
	private String shorthand;
	//图片
	private List<PicEntity> result;

	
	public MainPageModel() {
		super();
	}

	public MainPageModel(Integer id, String groupname, String shorthand,
			List<PicEntity> result) {
		super();
		this.id = id;
		this.groupname = groupname;
		this.shorthand = shorthand;
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getShorthand() {
		return shorthand;
	}

	public void setShorthand(String shorthand) {
		this.shorthand = shorthand;
	}

	public List<PicEntity> getResult() {
		return result;
	}

	public void setResult(List<PicEntity> result) {
		this.result = result;
	}
	
}
