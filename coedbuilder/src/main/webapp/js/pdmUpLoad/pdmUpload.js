$(function(){
	$("#pdmUploadModal span.messageError").html("");
	//显示弹框
	$('#pdmUploadModal').modal('show');
});
//验证文件是否是pdm
function checkFileType(obj){
	var val=$(obj).val();
	if(!val || val.substring(val.lastIndexOf(".")+1,val.length)!="pdm"){
		$("#pdmUploadModal span.messageError").css({"color":"red"});
		$("#pdmUploadModal span.messageError").html("请选择正确的pdm格式文件！");
		$("#pdmUploadModal :submit").attr("disabled",true);
	}else{
		$("#pdmUploadModal span.messageError").css({"color":"green"});
		$("#pdmUploadModal span.messageError").html("文件格式正确.");
		$("#pdmUploadModal :submit").attr("disabled",false);
	}
}
