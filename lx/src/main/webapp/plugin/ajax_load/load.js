/**
 * 显示ajax加载
 */
function showAjaxLoad(){
	var loadingDiv="<div class=\"loading\" id=\"loading"+code+"\"><center><div class='loadingImg'></div></center></div>";
	if(jQuery("#loading").html()==null){
		 jQuery(loadingDiv).appendTo(jQuery(document.body));
	}
	jQuery("#loading").css({"display":"block"});
}
/**
 * 关闭ajax加载
 */
function closeAjaxLoad(){
	jQuery("#loading").css({"display":"none"});
}

/**
 * 显示ajax加载
 */
function showAjaxLoad(code){
	var loadingDiv="<div class=\"loading\" id=\"loading"+code+"\"><center><div class='loadingImg'></div></center></div>";
	if(jQuery("#loading"+code).html()==null){
		 jQuery(loadingDiv).appendTo(jQuery(document.body));
	}
	jQuery("#loading"+code).css({"display":"block"});
}
/**
 * 关闭ajax加载
 */
function closeAjaxLoad(code){
	jQuery("#loading"+code).css({"display":"none"});
}