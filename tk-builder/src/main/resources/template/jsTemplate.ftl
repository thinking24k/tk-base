$package('${moduleName}.${simpleEntityName}');
${moduleName}.${simpleEntityName} = function(){
	var _box = null;
	var _this = {
		config:{
  			event:{
  				/*add : function(){
					_box.handler.add(function(){
					});//调用add方法
				},
				save : function(){
					_box.handler.save(function(data){
						
					});
				},
				edit:function(){
					
					_box.handler.edit(function(result){
					});
				},
				view:function(){
					_box.handler.view(function(result){
					});
				}*/
  			},
  			dataGrid:{
  				title:'${tableEntity.annotations}',
	   			rownumbers:true,
	   			pagination:true,
	   			singleSelect: false,
				multiSort:false,
	   			columns:[[
					{field:'id',title:'id',checkbox:true},
					<#list tableEntity.columns as col>
					<#if col.isPrimaryKey?exists && 0 == col.isPrimaryKey >
					{field:'${col.classAttr}',title:'${col.columnAnnotations?default('-')}',width:"10%",align:'center',sortable:false},
					</#if>
					</#list>
					/*{field:'sub${moduleName}',title:'子菜单',width:265,align:'center',formatter:function(value,row,index){
						return "<a href='#' onclick='${moduleName}.${simpleEntityName}.toList("+row.id+")'>子菜单管理("+row.subMenuCount+")</a>";
					}*/
				]],
				toolbar:[
					{id:'btnadd',btnType:'add',permission:'${moduleName}:${simpleEntityName}:doadd'},
					{id:'btnedit',btnType:'edit',permission:'${moduleName}:${simpleEntityName}:doupdate'},
					{id:'btndelete',btnType:'del',permission:'${moduleName}:${simpleEntityName}:dodel'},
					{id:'btnremove',btnType:'remove',permission:'${moduleName}:${simpleEntityName}:doremove'},
					{id:'btnview',text:'查看明细',iconCls:'icon-search',btnType:'view',permission:'${moduleName}:${simpleEntityName}:doview'}
					]
			}
		},
		init:function(){
			_box = new DataGrid(_this.config); 
			_box.init();
		},
		box:function(){
			return _box;
		}
	};
	return _this;
}();
//box 获取 ： ${moduleName}.${simpleEntityName}.box().form.search.resetForm();
$(function(){
	${moduleName}.${simpleEntityName}.init();
	$("#searchForm").removeClass("data-hiden");
	$("#editForm").removeClass("data-hiden");
});		