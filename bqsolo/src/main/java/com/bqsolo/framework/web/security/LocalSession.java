package com.bqsolo.framework.web.security;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bqsolo.framework.attribute.CommonAttribute;
import com.bqsolo.framework.attribute.TerminalAttribute;
import com.bqsolo.framework.domain.SessionUser;
import com.bqsolo.framework.domain.context.TerminalContext;




/**
 * 
 * @ClassName: LocalSession 
 * @Description: 用于替代Http Session
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年8月5日 
 *
 */
public class LocalSession {
	
	/**
	 * 空TerminalContext对象
	 */
	public static final TerminalContext EMPTY_TERMINAL_CONTEXT =  new TerminalContext();
	
	private static final ThreadLocal SESSION_MAP = new ThreadLocal(); 
	/** 日志 */
	private static Logger logger = Logger.getLogger(LocalSession.class);

	/**
	 * @Title: setAttribute
	 * @Description: 将信息置入Session中
	 * @param o
	 * @param attributeName
	 */
	public static void setAttribute(Object key, Object value) {
		
	}

	/**
	 * @Title: getAttribute
	 * @Description: 获取Session中的attribute
	 * @param key
	 */
	public static Object getAttribute(Object key) {
		Map map = (Map) SESSION_MAP.get(); 
        return map.get(key);  
	}

	/**
	 * @Title: removeAttribute
	 * @Description: 删除Session中的Attriubte
	 * @param key
	 */
	public static void removeAttribute(Object key) {


	}

	/**
	 * @Title: getSession
	 * @Description: 获取当前Session
	 * @return
	 */
	public static HttpSession getSession() {


		return null;
	}

	/**
	 * @Title: getCurrentUserInfo
	 * @Description: 获取当前用户
	 * @return
	 */
	public static SessionUser getCurrentUserInfo() {

		
		return null;
	}

	/**
	 * @Title: getCurrentAppContext
	 * @Description: 获取当前终端上下文
	 * @return
	 * @nullSafe
	 */
	public static TerminalContext getCurrentTerminal() {
		TerminalContext terminalContext = null;
		
		try {
			
			terminalContext = (TerminalContext) getAttribute(CommonAttribute.TERMINAL_CONTEXT);
			
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		if(terminalContext==null){
			terminalContext = EMPTY_TERMINAL_CONTEXT;
		}
		return terminalContext;
	}
	/** 
	* @Title: isTargetTerminal 
	* @Description: 判断是否目标终端
	* @param terminalCode
	* @return  
	*/ 
	public static boolean isTargetTerminal(String terminalCode){
		TerminalContext terminalContext = getCurrentTerminal();
		if(terminalCode.equalsIgnoreCase(terminalContext.getTerminalCode())){
			return true;
		}else{
			return false;
		}
	}

	
	/** 
	* @Title: isTargetTerminal 
	* @Description: 获取当前终端Code
	* @param terminalCode
	* @return  
	*/ 
	public static String getCurrentTerminalCode(){
		TerminalContext terminalContext = getCurrentTerminal();
		if(StringUtils.isEmpty(terminalContext.getTerminalCode())){
			return TerminalAttribute.TERMINAL_CODE_WEB;
		}else{
			return terminalContext.getTerminalCode();
		}
	}
	

}
