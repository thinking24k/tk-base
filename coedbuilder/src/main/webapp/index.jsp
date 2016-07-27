<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
rd.forward(request, response);   
%>
