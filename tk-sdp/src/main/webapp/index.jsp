<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	response.sendRedirect(basePath+"main/user.cmd/doselectbyid/1");
	/* response.sendRedirect(basePath+"WEB-INF/pages/index/main.jsp"); */
	//request.getRequestDispatcher("WEB-INF/pages/index/index.jsp").forward(request, response);
%> 

