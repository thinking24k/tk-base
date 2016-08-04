package com.bqsolo.framework.conttroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bqsolo.framework.domain.MessageDTO;

/** 
* @ClassName: BaseController 
* @Description: 基础控制器
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2015年6月1日 
*  
*/ 
public abstract class BaseController {
    /** 日志 */
	private static Logger logger = Logger.getLogger(BaseController.class) ;  
	
	@Autowired  
	protected  HttpServletRequest request;	
	@Autowired  
	protected  HttpServletResponse response;    
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 * 
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}	    
	
	    
	protected <T> MessageDTO<T> responseData(boolean status,T content,String message){
		return new MessageDTO<T>(status?MessageDTO.STATUS_SUCCESS:MessageDTO.STATUS_ERROR, content, message);
	}
	/**
	 *    
	 * @Title: doNullValidation 
	 * @Description: 空校验
	 * @param obj
	 * @return
	 */
	protected boolean doNullValidation(Object obj) {
		boolean status = true;
		// 1.空对象校验
		if (null == obj) {
			status = false;
		}
		return status;
	}
    
}
