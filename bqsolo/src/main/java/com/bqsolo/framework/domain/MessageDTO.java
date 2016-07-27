

package com.bqsolo.framework.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/** 
 * @ClassName: MessageDTO 
 * @Description: 最终返回对象
 * @company 
 * @author x
 * @param <T>
 * @Email x
 * @date x
 *  
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MessageDTO<T> implements Serializable {
	private static final long serialVersionUID = -8046344992108513426L;
	public static final int STATUS_SUCCESS = 1;
	public static final int STATUS_ERROR = 0;
	private int status;
    private String message;
    private String msgKey;
    private T content;
    
    @JsonCreator
    public MessageDTO(@JsonProperty("content") T content) {
    	this.content = content;
    }   
    

    public MessageDTO() {

    }      
    
    public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

}
