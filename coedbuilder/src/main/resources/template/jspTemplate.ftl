<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/common/resource.jsp" %>
  
  </head>
<body class="easyui-layout" >
		<!-- Search panel start -->
		<div class="ui-search-panel" region="north" style="padding:1.5%;height:90px;overflow:hidden;" title="查询条件" data-options="iconCls:'icon-search',border:true" style="padding:7px;overflow:hidden;">
			<form id="searchForm" class="data-hiden">
				<input class="hidden" id='search_parentId' name="parentId">
				<p class="ui-fields">
					<#list tableEntity.columns as col>
						<label class="ui-label">${col.columnAnnotations?default('-')}：</label> <input name="${col.classAttr}" class="easyui-box ui-text" style="width:100px;">
					</#list>
				</p>
					<a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					<a id="btn-reset" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</form>
	    </div> 
	    <!--  Search panel end -->
	
	    <!-- DataList  -->
		<div region="center" border="false" >
			<table id="data-list"></table>
		</div>
	<!-- Edit Win&From -->
    <div id="edit-win" class="easyui-dialog" title="填写基本信息" data-options="closed:true,iconCls:'icon-save',modal:true"  style="width:450px;height:500px;overflow: auto;">  
     	<form id="editForm" class="ui-form data-hiden" method="post"> 
     		<!-- 隐藏文本框 -->
     		 <#list tableEntity.columns as col>
		          <#if col.isPrimaryKey?exists && 1 == col.isPrimaryKey >
		              <input class="hidden" name="${col.classAttr}">
			      </#if>
		     </#list>
     		 
    		 <table class="collapse" width="100%;" border="1" bordercolor="#BFBDBD">
            		
            		 <#list tableEntity.columns as col>
		               <#if col.isPrimaryKey?exists && 0 == col.isPrimaryKey >
		               <#if col.columnName !='creationuserid' && col.columnName !='creationdate' && col.columnName !='changeuserid' && col.columnName !='changedate' && col.columnName !='isvoid' >
				           <tr>
		               			<td class="jl">
		               				<label>${col.columnAnnotations?default('-')}<#if col.isNull?exists && 1 == col.isNull ><span class='require-span'>*</span></#if>:</label>
		               			</td>
		               			<td class="oh">
		                        	<input class="easyui-validatebox" type="text" name="${col.classAttr}"
		                        	 style="width:230px;" data-options="<#if col.isNull?exists && 1 == col.isNull >required:true,</#if><#if col.datasize?exists>  validType: 'length[1, ${col.datasize}]'</#if>">
		                		</td>
	            			</tr>
			           </#if>
			           </#if>
		           </#list>
            	
            </table> 
     	</form>
  	 </div>
  <script type="text/javascript" src="<c:url value="/js/${moduleName}/${simpleEntityName}.js"/>"></script>
  </body>
</html>
