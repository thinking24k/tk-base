package com.bqsolo.framework.domain;

import java.io.Serializable;




public class SessionUser implements Serializable/*extends UserEntity*/ {

	private static final long serialVersionUID = -8805104105914008992L;
	//是否上锁
	private Integer locked;
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	
	
	
}
