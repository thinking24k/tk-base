package com.xxwl.tk.framework.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.attribute.TerminalAttribute;
import com.xxwl.tk.framework.domain.context.TerminalContext;
import com.xxwl.tk.framework.utils.StringUtil;
import com.xxwl.tk.framework.web.security.LocalSession;

public class SDPIntercepter implements HandlerInterceptor {

	private Logger log = Logger.getLogger(SDPIntercepter.class);



	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		printReqInfo(request);
		
		loadTerminalContext(request);
		//没有TerminalCode
		if(StringUtil.isEmpty(request.getHeader(TerminalAttribute.TERMINAL_CODE))){
			//跳转首页
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			//return false;
		}
		return true;
	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	/**
	 * 
	 * @Title: printReqInfo 
	 * @Description: TODO
	 * @param request
	 */
	private void printReqInfo(HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			log.debug("==============APP 终端请求信息==============");
			log.debug("RemoteHost:" + request.getRemoteHost());
			log.debug("Host: " + request.getHeader("Host"));
			log.debug("URI:" + request.getRequestURI());
			log.debug("Path Info: " + request.getPathInfo());
			log.debug("Query String: " + request.getQueryString());
			log.debug("Accept: " + request.getHeader("Accept"));

			log.debug("Referer : " + request.getHeader("Referer"));
			log.debug("Accept-Language : "
					+ request.getHeader("Accept-Language"));
			log.debug("Content-Type : " + request.getHeader("Content-Type"));
			log.debug("Accept-Encoding : "
					+ request.getHeader("Accept-Encoding"));
			log.debug("User-Agent : " + request.getHeader("User-Agent"));
			log.debug("Connection : " + request.getHeader("Connection"));
			log.debug("Cookie : " + request.getHeader("Cookie"));
			log.debug("TerminalCode : "
					+ request.getHeader(TerminalAttribute.TERMINAL_CODE));
			log.debug("TerminalVersion : "
					+ request.getHeader(TerminalAttribute.TERMINAL_VERSION));
			log.debug("Protocol: " + request.getProtocol());
			log.debug("Scheme: " + request.getScheme());
			log.debug("Server Name: " + request.getServerName());
			log.debug("Server Port: " + request.getServerPort());
			log.debug("Protocol: " + request.getProtocol());
			log.debug("Remote Addr: " + request.getRemoteAddr());
			log.debug("Remote Host: " + request.getRemoteHost());
			log.debug("Character Encoding: " + request.getCharacterEncoding());
			log.debug("Content Length: " + request.getContentLength());
			log.debug("Content Type: " + request.getContentType());
			log.debug("Auth Type: " + request.getAuthType());
			log.debug("HTTP Method: " + request.getMethod());
			log.debug("Path Trans: " + request.getPathTranslated());
			log.debug("Remote User: " + request.getRemoteUser());
			log.debug("Session Id: " + request.getRequestedSessionId());
			log.debug("Request URI: " + request.getRequestURI());
			log.debug("Servlet Path: " + request.getServletPath());
			log.debug("=============================================");
		}
	}

	/**
	 * @Description: 载入APP(终端)上下文
	 * @param request
	 */
	private void loadTerminalContext(HttpServletRequest request) {
		TerminalContext terminalContext = LocalSession.getCurrentTerminal();
		//同一个session只拦截一次
		if (terminalContext != LocalSession.EMPTY_TERMINAL_CONTEXT) {
			return;
		}
		String terminalVersion = request.getHeader(TerminalAttribute.TERMINAL_VERSION);
		String terminalCode = request.getHeader(TerminalAttribute.TERMINAL_CODE);

		terminalContext = new TerminalContext();
		terminalContext.setTerminalCode(terminalCode);
		terminalContext.setTerminalVersion(terminalVersion);

		/*测试用*/
		if(StringUtil.isEmpty(terminalCode)){
			 terminalContext.setTerminalCode(TerminalAttribute.TERMINAL_CODE_WEB);
			// terminalContext.setTerminalVersion("1.52.32");
		}
		LocalSession.setAttribute(CommonAttribute.TERMINAL_CONTEXT, terminalContext);

	}

}
