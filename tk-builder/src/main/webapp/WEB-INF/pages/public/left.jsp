<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
             <c:if test="${select == null || select == 0}">
            	<li class="active"><a href="${basePath}index.jsp">连接数据库生成 </a></li>
            	<li><a href="${basePath}builderController/toPdmUpload.do">上传pdm文件生成</a></li>
            </c:if>
            <c:if test="${select == 1}">
            	<li ><a href="${basePath}index.jsp">连接数据库生成</a></li>
            	<li class="active"><a href="${basePath}builderController/toPdmUpload.do">上传pdm文件生成</a></li>
            </c:if>
          </ul>
        
</div>