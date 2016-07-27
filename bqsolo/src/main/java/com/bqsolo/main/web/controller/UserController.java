package com.bqsolo.main.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bqsolo.main.entity.UserInfo;
import com.bqsolo.main.service.UserInfoService;
import com.bqsolo.main.staticVar.Constant;
import com.bqsolo.main.utils.CookieUtils;
import com.bqsolo.main.utils.DateUtils;
import com.bqsolo.main.utils.IpUtil;
import com.bqsolo.main.utils.StringUtils;

@Controller
@RequestMapping("user/")
public class UserController {
	private static Logger logger = 	Logger.getLogger(UserController.class);
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("login")
	public String login(@RequestParam("username")String name,@RequestParam("password")String pwd,String remember,HttpSession session,HttpServletRequest req,HttpServletResponse resp){
		String url = CookieUtils.getCookie(req, Constant.BEFORELOGINURL);
		//都为空跳转登录界面
		if(StringUtils.isEmpty(name) && StringUtils.isEmpty(pwd) ){
			return "redirect:/static/login.do";
		}
		if(StringUtils.isEmpty(name)){
			req.setAttribute("userName", "请输入用户名.");
			return "index/login";
		}
		if(StringUtils.isEmpty(pwd)){
			req.setAttribute("userPwd", "请输入用户密码.");
			return "index/login";
		}
		try {
			UserInfo login = userInfoService.login(name, pwd);
			if(login==null){//登陆失败
				req.setAttribute("user_Info", "登录失败！请检查输入.");
				return "index/login";
			}
			session.setAttribute(Constant.SESSIONUSER, login);
			if(!StringUtils.isEmpty(remember) && "1".equals(remember)){
				//添加记住我，注意安全策略，唯一安全字符串
			}
			if(StringUtils.isEmpty(url)){
				return "redirect:/static/index.do"; 
			}
			URI uri=new URI(url);
			return "redirect:"+uri.getPath();
		} catch (URISyntaxException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "redirect:/static/index.do";
	}
	@RequestMapping("register")
	public String register(UserInfo info,HttpSession session,HttpServletRequest req,String confirm_password,String captcha,String agreement){
		String sessionCaptcha=(String)session.getAttribute(Constant.SESSIONRANDOMIMG);
		String pwd=info.getPassword();
		if(StringUtils.isEmpty(captcha) ){
			return "redirect:/static/reg.do";
		}
		if(!captcha.equalsIgnoreCase(sessionCaptcha)){
			req.setAttribute("userCaptcha", "验证码错误.");
		}
		if(StringUtils.isEmpty(agreement) || !agreement.equals("1")){
			req.setAttribute("userAgreement", "未同意用户协议.");
		}
		if(StringUtils.isEmpty(confirm_password) || confirm_password.equals(info.getPassword())){
			req.setAttribute("user_confirm_password", "2次密码输入不一致.");
		}
		String url = CookieUtils.getCookie(req, Constant.BEFOREREGURL);
		Map<String, String> msg=new HashMap<String, String>();
		info.setRegIp(IpUtil.getRemoteHost(req));
		info.setRegTime(DateUtils.getTodayTime());
		try {
			//保存用户
			Integer save = userInfoService.save(info, msg);
			if(save>0){//注册成功保存session
				UserInfo login = userInfoService.login(info.getUsername(),pwd);
				session.setAttribute(Constant.SESSIONUSER, login);
			}else{
			if(msg.size()>0){
					Set<String> keySet = msg.keySet();
					Iterator<String> iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String next = iterator.next();
						req.setAttribute(next, msg.get(next));
					}
				}
				req.setAttribute("user_info", "注册失败！");
				return "index/reguser";
			}
			if(StringUtils.isEmpty(url)){
				return "redirect:/static/index.do";
			}
			URI uri=new URI(url);
			return "redirect:"+uri.getPath();
		} catch (URISyntaxException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "redirect:/static/index.do";
	}
	/**
	 * 用户退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/static/index.do";
	}
	
	/**
	 * 验证用户名是否被使用
	 * @param session
	 * @return
	 */
	@RequestMapping("checkUserName")
	public @ResponseBody boolean checkUserName(String username,HttpServletResponse resp){
		//ajax 跨越客户端使用jsonp
		//服务端添加头信息
		//resp.addHeader("Access-Control-Allow-Origin", "*");
		//resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		try {
			return userInfoService.checkName(username);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 验证邮箱是否被使用
	 * @param session
	 * @return
	 */
	@RequestMapping("checkUserEmail")
	public @ResponseBody boolean checkUserEmail(String email){
		try {
			return userInfoService.checkEmail(email);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return false;
	}


}
