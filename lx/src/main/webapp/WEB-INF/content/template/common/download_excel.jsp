<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="import.jsp" %>
<!--  jquery库 -->
<jc:plugin name="jquery" />
<!-- 等待控件 -->
<jc:plugin name="loading" />
<script type="text/javascript">
	$(document).ready(function(){
		//下载导出文件
		jQuery('#down_load_dialog').dialog({
			autoOpen:false,
			modal:true,
			draggable:false,
			resizable:false,
			title:'信息提示',
			buttons: {
				'关闭': function() { 
					jQuery(this).dialog("close"); 
				} 
			}
		});	
	});
	//下载地址回调函数
	function excel_export_callback(data){
		if(data.downloadUrl=='error'+EXPORT_EXCEL_ERROR_NO_0)
		{
			$("#down_load_url").html("导出错误");
			jQuery('#down_load_dialog').dialog("open");
		}else if(data.downloadUrl=='error'+EXPORT_EXCEL_ERROR_NO_1)
		{
			$("#down_load_url").html("资源达到最大连接数");
			jQuery('#down_load_dialog').dialog("open");
		}else if(data.downloadUrl=='error'+EXPORT_EXCEL_ERROR_NO_2)
		{
			$("#down_load_url").html("您已有正在下载的任务，请先等待下载完成后再执行新的下载任务<br>点击此处查看正在下载的任务：<a href='<uu:url url="base/task" />'>下载任务</a>");
			jQuery('#down_load_dialog').dialog("open");
		}else if(data.downloadUrl=='error'+EXPORT_EXCEL_ERROR_NO_3)
		{
			$("#down_load_url").html("您导出的结果为空");
			jQuery('#down_load_dialog').dialog("open");
		}else if(data.downloadUrl=='error'+EXPORT_EXCEL_ERROR_NO_4)
		{
			$("#down_load_url").html("您导出的结果数据量超过上限,上限为:"+data.dataMaxCount);
			jQuery('#down_load_dialog').dialog("open");
		}else
		{
			$("#down_load_url").html("<a href='"+WEB_ATTACHMENT+data.downloadUrl+"'>下载导出结果</a>");
			jQuery('#down_load_dialog').dialog("open");
		}
	}
	
</script>
<!-- 下载地址 -->
<div id="down_load_dialog" style="display:none">
	<div align="center" id="down_load_url">
		
	</div>
</div>