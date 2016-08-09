<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	response.sendRedirect(basePath+"index.html");
	/* response.sendRedirect(basePath+"WEB-INF/pages/index/main.jsp"); */
	//request.getRequestDispatcher("WEB-INF/pages/index/index.jsp").forward(request, response);
%> 

