$(function(){
	initTable();
	//生成代码按钮事件
	$("#btn_generate").click(function(){
		var length=$("input:checked").length;
		if(!length || length==0){
			alert("请至少选择一个table中的任何一个层次！")
			return ;
		}
		var param=getGenerateTableAndLevels();
		builder(param);
	});

});

//basePath
var basePath=document.getElementById("basePath").value;
//表格tbody层次html
var tbody_comm="",levelsNum=0;

//初始化表格
function initTable(){
	$.ajax({
		url:basePath+"builderController/getlevels.do",
		data:{},
		dataType:"json",
		type:"post",
		success:function(data){
			if(!data){
				alert("获取数据出错了！")
			}else{
				var thead="";levelsNum=data.length;
				thead+="<thead><tr><th>编号</th><th>表名</th>";
				for(var i=0;i<data.length;i++){
					thead+="<th><label class=\"checkbox-inline\"><input type=\"checkbox\" checked=\"checked\" value=\""+data[i].packagez+"\" name=\""+data[i].packagez+"\" onclick=\"checkAll(this)\" /> 全选</label></th>";
					tbody_comm+="<td> <label class=\"checkbox-inline\"><input type=\"checkbox\" checked=\"checked\" value=\""+data[i].packagez+"\" name=\""+data[i].packagez+"\" onclick=\"checkNoFirst(this)\" />"+data[i].packagez+"</label></td>";
				}
				thead+="</tr></thead>";
				$("#table_div_show>table").append(thead);

				showTables();//显示所有的数据库表
			}
		},error:function(error){
			alert(error);
		}
	})
}
//显示所有的数据库表
function showTables(){
	var urlz=basePath+"builderController/getAllTableBySql.do";
	if(select && select==1){//标示是否是pdm页面跳转
		urlz=basePath+"builderController/getAllTableByPdm.do";
	}
	$.ajax({
		url:urlz,
		data:{},
		dataType:"json",
		type:"post",
		success:function(data){
			if(!data){
				alert("获取数据出错了！")
			}else{
				appendTableTbody(data);
			}
		},error:function(error){
			alert(error);
		}
	})
}
//构建表格的tbody
function appendTableTbody(data){
	$("#table_div_show>table>tbody").html("");
	var tbody="<tbody>";
	//for(var m=0;m<5;m++){//test-----
	for(var i=0;i<data.length;i++){
		tbody+="<tr>";
		tbody+="<td>"+(i+1)+"</td><td>"+data[i].tableName+"</td>";
		tbody+=tbody_comm;
		tbody+="</tr>";
	}
	//}
	tbody+="</tbody>";
	$("#table_div_show>table").append(tbody);
}
//获取需要生成的表格和层次
function getGenerateTableAndLevels(){
	var data="";
	$("#table_div_show>table>tbody>tr").each(function(index){

		var checkLevels="";
		//拼接需要生成的层次
		$(this).find("td").each(function(index){
			var tempCheck=$(this).find("input[type=checkbox]:checked").val();
			if(tempCheck){
				checkLevels+=tempCheck;
				checkLevels+=",";
			}
		});
		if(checkLevels){//如果最少有一个层次被选中才添加该表名
			checkLevels=checkLevels.substring(0,checkLevels.lastIndexOf(","));
			data+=$(this).find("td:eq(1)").html();//表名
			data+=":";
			data+=checkLevels;
			data+="@";
		}
	});
	return data;
}
//生成代码
function builder(param){
	var urlz=basePath+"builderController/builderBySql.do";
	if(select && select==1){//标示是否是pdm页面跳转
		urlz=basePath+"builderController/builderByPdm.do";
	}
	$.ajax({
		url:urlz,
		data:{"tables":param},
		dataType:"json",
		type:"post",
		success:function(data){
			if(!data){
				alert("获取数据出错了！");
			}else if(data<0){
				alert("生成失败！");
			}else if(data>0){
				alert("生成成功，现在开始下载.....！");
				download();
			}
		},error:function(error){
			alert(error);
		}
	})
}
//文件下载
function download(){
	window.location=basePath+"builderController/download.do";
}
//选中其他checkbox
function checkAll(obj){
	var name=$(obj).attr("name");
	if($(obj).prop("checked")) {
		//未选中
		$("input[name='"+name+"']:gt(0)").prop('checked', true);
	} else {
		$("input[name='"+name+"']:gt(0)").prop('checked', false);
	}
}
//其他checkbox没有选中，去掉全选checkbox的徐选中状态
function checkNoFirst(obj){
	var name=$(obj).attr("name");
	var checkedNotInFirstLength=$("input[name='"+name+"']:gt(0):checked").length;
	var notInFirstLength=$("input[name='"+name+"']:gt(0)").length;

	if(notInFirstLength && notInFirstLength>0){
		//出第一个checkbox外不是全部选中
		if(checkedNotInFirstLength !=notInFirstLength){
			$("input[name='"+name+"']:eq(0)").prop('checked', false);
		}else{//是
			$("input[name='"+name+"']:eq(0)").prop('checked', true);
		}
	}

}

