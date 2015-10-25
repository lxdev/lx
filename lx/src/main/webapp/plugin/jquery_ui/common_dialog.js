//打开弹出层
//divId	需要弹出层的ID
//title	弹出层标题
//widths 弹出层宽度
//heights 弹出层高度
function show(divId,title,widths,heights)
{
	$('#'+divId).dialog({
		bgiframe: true,
		autoOpen: false,
		width:widths,
		minWidth:widths,
		height: heights,
		minHeight:heights,
		modal: true,
		title:title
	});
	$('#'+divId).dialog('open');
}

//关闭弹出层
function closes(divId)
{
	$('#'+divId).dialog('close');
}


/*********************ajax操作公共返回结果*********************/
//初始化ajax结果弹出层
function init_result_dialog()
{
	var dialogdiv="<div id=\"showDialog\" style=\"display:none\">"+
					"<table class=\"add_table\">"+
						"<tr>"+
							"<td colspan=\"2\" align=\"center\">"+
								"<input type=\"button\" class=\"btn_black_61\" value=\"关闭\" onclick=\"closes('showDialog');\" />"+
							"</td>"+
						"</tr>"+
					"</table>"+
					"</div>";
	$("body").append(dialogdiv);
}

//弹出操作结果提示框
function alert_dialog(message)
{
	show('showDialog',message,160,130);
}

//弹出增删改操作默认结果提示框
function alert_default_dialog(type)
{
	if(0==type)
		alert_dialog("数据错误,无此数据!");
	if(1==type)
		alert_dialog("添加成功!");
	if(-1==type)
		alert_dialog("添加失败!");
	if(2==type)
		alert_dialog("修改成功!");
	if(-2==type)
		alert_dialog("修改失败!");
	if(3==type)
		alert_dialog("删除成功!");
	if(-3==type)
		alert_dialog("删除失败!");
	if(4==type)
		alert_dialog("保存成功!");
	if(-4==type)
		alert_dialog("保存失败!");
}




/*********************ajax操作公共返回结果*********************/