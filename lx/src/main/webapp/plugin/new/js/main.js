/*! 自定义函数方法 */

/* collect */
var common_collect = function(id, type){
	/* 1 问题关注
	 * 2 院校收藏
	 * 3 课程收藏
	 * 4 攻略收藏
	 */
	if($("#current_user_id").val() != "" && parseInt($("#current_user_id").val()) > 0){
		
	}else {
		//alert("请先登录");
		login_box_show();
		return;
	}
	
	var url = '../crm/my_follow_add';
	var option = { 
		'entity.follow_type': type,
		'entity.source_id': id
	};
	
	jQuery.post(url, option,
	        function(data)
	    	{
	    		alert(data.message);
	    	},
	 "json");
};

var login_box_show = function(){
	$(".popbg").show();
    $("#login_box").show();
};



var open_loading = function(){
	var height = $(window).height();
	var width = $(window).width();
	var loading = "<div class='graydiv'>"
				+ "<div class='gray' style='height:" + height + "px'></div>"
				+ "<div class='loading' style='top:" + (height/2-70) + "px;left:" + (width/2-70) + "px'></div>"
				+ "</div>";
	jQuery('#result_ul').append(loading);
	//$("body").css("overflow-y", "hidden");
	//$("body").css("height", height + "px");
};
var close_loading = function(){
	jQuery('#result_ul').remove(".graydiv");
	//$("body").css("overflow-y", "auto");
	//$("body").css("height", "auto");
};

/* 输入下拉框 自动完成 */
function initAutoComplete(json, obj, obj_id) {
    //this.elementText = $("#program_specialty");
    this.elementText = obj;
    this.elementId = obj_id;
    this.elementId.val("");
    var _this = this;
    this.elementText.autocomplete({
        autoFocus: true,
        source: json,
//        delay: 500,
//        position: {
//        	my: "left top",
//            at: "left bottom",
//            of: _this.elementText,
//            offset: "5 10"
//        },
        minLength: 1,
        focus: function () {
            return false;
        },
        open: function (event, ui) {
            var b = 22;
        }//.context(this),
        .context,
        select: function (event, ui) {
        	_this.elementId.val(ui.item.id).trigger('change');
            //if ($('#' + this.options.name + "-text", this.elementId.prev()).val() == "") //This is prevent 2 or more ' this.elementId '
            //    this.element.next().val(ui.item.id).trigger('change');
        	_this.elementText.val(ui.item.name);
        	_this.elementId.attr("data-id", ui.item.id);
			//window.setTimeout(function(){
			//	if(_this.elementId.val() == undefined || _this.elementId.val() == "") {
			//		//_this.elementId.val(ui.item.id);
			//		$("#" + this.elementId.attr("name")).val(ui.item.id);
			//	}
			//}, 500);
            return false;
        }//.context(this)
        //.context
    })
    //.data("autocomplete")._renderItem = function (ul, item) {		/* jquery-ui-1.8 使用这个*/
    .data("uiAutocomplete")._renderItem = function (ul, item) {		/* jquery-ui-1.10 使用这个 */
    	
        var str1 = "<li class='item'></li>";
        return $(str1)
                .data("item.autocomplete", item)
                .append("<a href='javascript:void(0)' >" + '<span >' + item.name + '</span>' + "</a>")
                .appendTo(ul);
    };
};

/*排序点击*/
var set_sort_p = function(obj, sort_type){
	var parent = $("#programs");
	var order;
	if(sort_type){
		$("#sort_ul li", parent).removeClass("current");
		$(obj).addClass("current");
		if(sort_type == 'hot')	//热度
			order = " D.total_browse DESC ";
		else if(sort_type == 'ranking')	//排名
			order = " B.ranking_comprehensive ASC ";
		else if(sort_type == 'remark')	//点评
			order = " C.evaluate_number DESC ";
	}
	$("#sort_by", parent).val(order);
	
	search_data_p(true);
}
/*搜索数据*/
var search_data_p = function(is_not_check){
	var url = '../template/json_programs';
	var parent = $("#programs");

	var country_id = $("#countryId", parent).val();
	var study_level_id = $("#studyLevelId", parent).val();
	var specialty_id = $("#program_specialty_id", parent).val() || $("#program_specialty_id", parent).attr("data-id");
	if(!is_not_check){
		if(country_id <= 0){
			alert("请选择一个国家！");
			return;
		}
		if(study_level_id <= 0){
			alert("请选择一个学位！");
			return;
		}
		if($.trim(specialty_id) == "" || $.trim(specialty_id) == "0"){
			alert("专业不存在！");
			return;
		}
	}
	
	open_loading();
	
	var tempAreaName = $('input[name="area"]:checked').val();
	var tempIsPublicSchool = $('input[name="is_public_school"]:checked').val();
	
	var rankingBegin;
	var rankingEnd;
	var tempRanking = $('input[name="ranking"]:checked').val();
	if(tempRanking != '全部'){
		var tempRankingArray = tempRanking.split('-');
		rankingBegin = tempRankingArray[0];
		if(tempRankingArray.length >= 2)
			rankingEnd = tempRankingArray[1];
	}
	var score_totef = -1;
	var totefEnd;
	var tempTotef = $('input[name="totef"]:checked').val();
	if(tempTotef != '-1'){
		var tempTotefArray = tempTotef.split(',');
		score_totef = tempRankingArray[0];
		if(tempTotefArray.length >= 2)
			totefEnd = tempTotefArray[1];
	}
	
	var option = {
			'condition.countryId': country_id
			, 'condition.study_level_id': study_level_id
			, 'condition.specialtyName': $("#program_specialty", parent).val()
			, 'condition.specialtyId': specialty_id
			, 'condition.universityName': $("#university_name", parent).val()
			, 'condition.page_size': $("#page_size", parent).val()
			, 'condition.page': $("#page", parent).val()
			, 'condition.rankingBegin': rankingBegin
			, 'condition.rankingEnd': rankingEnd
			, 'condition.areaName': (tempAreaName == '全部' ? '' : tempAreaName)
			, 'condition.is_public_school': (tempIsPublicSchool == '全部' ? -1 : tempIsPublicSchool)
			, 'condition.score_totef': score_totef
			, 'condition.score_ietls': -1
			, 'condition.score_gre': -1
			, 'condition.score_gmat': -1
			, 'condition.totefEnd': totefEnd
			, 'condition.orderBy': $("#sort_by", parent).val()
	};
	
	jQuery.post(url, option,
	        function(data)
	    	{
	    		var lists="";
	    		if(null!=data.resultProgram){
		    		jQuery.each(data.resultUniversity, function()
	    			{
		    			var _u = this;
		    			lists += "<li>";
		    			lists += "	<table class='table-2' cellpadding='0' cellspacing='0'>";
		    			lists += "		<tr>";
		    			lists += "			<td class='text-center ulogo' style='width:20%'>";
		    			lists += "				<img src='" + this.logo_url + "'/>";
		    			lists += "			</td>";
		    			lists += "			<td valign='top'>";
			            lists += "				<div class='universityname'>";
			            lists += "					<a href='university?universityId='" + this.id + "'>" + this.university_name + "/" + this.english_name + "</a>";
			            lists += "				</div>";
			            lists += "				<div class='info'>";
			            lists += "					<span>" + this.country.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.browse_number + "浏览&nbsp;&nbsp;&nbsp;&nbsp;" + this.evaluate_number + "点评</span>&nbsp;&nbsp;&nbsp;&nbsp;";
			            //lists += "					<span class='star'></span><span class='star2'></span><span class='star2'></span>";
			            lists += "				</div>";
			            lists += "				<div class='course' onclick='show_courses(" + this.id + ")'>";
			            lists += "					<span>共" + this.numProgram + "个课程</span><span class='open'></span>";
			            lists += "				</div>";
			            lists += "			</td>";
			            lists += "			<td valign='top' class='text-center' style='width:15%'>";
			            lists += "				<div class='ranking ftcolff6600'>";
			            lists += "					<span>综合排名 " + this.ranking_comprehensive + "</span>";
			            jQuery.each(data.resultSpecialtyRank, function()
				    			{
					            	if(_u.id == this.university_id){
					            		//lists += "<span>" + $("#program_specialty", parent).val() + "排名：" + this.rank + "</span>";
					            		lists += "<span>专业排名：" + this.rank + "</span>";
					            	}
				    			});
			            lists += "				</div>";
			            lists += "				<div class='text-center sc'>";
			            lists += "					<img src='../plugin/new/images/sc.png' onclick='common_collect(" + this.id + ", 2)'/>";
			            lists += "				</div>";
			            lists += "			</td>";
			            lists += "		</tr>";
			            lists += "</table>";
			            
			            lists += "<div class='morecourse'>";
			            lists += "<ul>";
			            jQuery.each(data.resultProgram, function()
		    			{
			            	if(_u.id == this.university_id){
			            		
			            		lists += "<li class='university_" + this.university_id + "' style='display: none;'>";
			            		lists += "    <div class='ctitle ftcol333333'>";
			            		lists += "        <span class='sclink clicks'><a href='javascript:void(0);' onclick='collect(" + this.id + ")'>收藏</a></span>";
			            		lists += "        <a href='program?programId=" + this.id + "'>";
			            		lists += "        	<span>" + this.program_name + "</span>";
			            		lists += "        </a>";
			            		lists += "        <span>0浏览</span>";
			            		lists += "    </div>";
			            		lists += "    <div class='ccontent  ftcol333333'>";
			            		//lists += "        <span class='dblink'>对比</span>";
			            		lists += "         " + this.length_of_schooling + " $" + this.tuition + " Total Program";
			            		lists += "    </div>";
			            		lists += "</li>";
			            		
			            	}
		    			});
			            lists += "</ul>";
			            lists += "</div>";
			            
			            lists += "</li>";
	    			});
	    			jQuery('#result_ul').html(lists);
	    			close_loading();
	    		}
	    		if(data.universityNum != null && data.universityNum >= 0){
	    			set_page_result(data.universityNum, data.programNum);
	    		}
	    	},
	 "json");
}
/*根据查询结果，重新设置总记录及页数*/
var set_page_result = function(totalUNum, totalPNum){
	jQuery('#programNum').html(totalPNum);
	jQuery('#universityNum').html(totalUNum);
	jQuery('#record_total').html(totalUNum);
	jQuery('#record_total').val(totalUNum);
//	if(totalUNum > 10){
//		$(".less_disable").show();
//	}else {
//		$(".less_disable").hide();
//	}
	var page_size = parseInt( $("#page_size").val() );
	var pageNum = parseInt( $("#record_total").val() );
	var pageCount = pageNum <= 0 ? 0 : pageNum % page_size == 0 ? pageNum / page_size : parseInt( pageNum / page_size ) + 1;
	$("#pager").pager({ pagenumber: parseInt($("#page").val()), pagecount: pageCount, buttonClickCallback: PageClick });
}
var PageClick = function(pageclickednumber) {
	var page_size = parseInt( $("#page_size").val() );
	var pageNum = parseInt( $("#record_total").val() );
	var pageCount = pageNum <= 0 ? 0 : pageNum % page_size == 0 ? pageNum / page_size : parseInt( pageNum / page_size ) + 1;
    $("#pager").pager({ pagenumber: parseInt( pageclickednumber ), pagecount: pageCount, buttonClickCallback: PageClick });
    
    $("#page").val(pageclickednumber);	//暂存，当前是第几页
    
    if( $("#pager").attr("data-type") == "p" ){
    	search_data_p(true);
    }else if( $("#pager").attr("data-type") == "u" ){
    	search_data(-1);
    }
    
}
/*翻页，每页点击*/
//$("#programs #page_ul li a").each(function (i) {
//    $(this).click(function () {
//        $("#page_ul li").removeClass("active");
//        $(this).parent().addClass("active");
//        
//        var current_ul_number = parseInt($("#page_ul").attr("data-value"))
//        
//        var temp = $(this).html();
//        if(temp == "»"){
//        	if( current_ul_number * 5 * parseInt($("#page_size").val()) >= parseInt($("#record_total").val()) )
//        		return;
//        	current_ul_number += 1;
//        	$("#page_ul li a .num").each(function (i) {
//        		var oldNum = parseInt($(this).html());
//        		$(this).html(oldNum * current_ul_number);
//        	})
//        	$("#page_ul").attr("data-value", current_ul_number);
//        }else if(temp == "«"){
//        	if(current_ul_number <= 0)
//        		return;
//        	current_ul_number -= 1;
//        	$("#page_ul li a .num").each(function (i) {
//        		var oldNum = parseInt($(this).html());
//        		$(this).html(oldNum * current_ul_number);
//        	})
//        	$("#page_ul").attr("data-value", current_ul_number);
//        }else {
//        	temp = $(".num", $(this)).html();
//            var page = parseInt(temp);
//            
//            $("#page").val(page-1);
//            
//            search_data_p(-1);
//        }
//        
//    });
//});

$(document).ready(function () {
    $(".ui-autocomplete-input").keyup(function () {
        var _term = $(this).val();
        var auto_obj = $(this);//.attr("id")
        var auto_obj_id = $(this).next();
        var url = auto_obj.attr("data-url");// + '?term=' + _term;
        initAutoComplete(url, auto_obj, auto_obj_id);
    });
});

/* 验证 首页 课程 输入 */
function validateCourseSearchForm() {
	
	if($("#countryId").val() == "" || $("#countryId").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	if($("#studyLevelId").val() == "" || $("#studyLevelId").val() == "0"){
		alert("请选择一个学历");
		return false;
	}
	$("#program_specialty_id").val( $("#program_specialty_id").val() || $("#program_specialty_id").attr("data-id") );
	if($("#program_specialty_id").val() == "" || $("#program_specialty_id").val() == "0"){
		alert("请选择一个专业");
		return false;
	}
	return true;
}
/* 验证 首页 攻略 输入 */
function validateSpecialtyForm() {

	if($("#studynat").val() == "" || $("#studynat").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	$("#guide_specialty_id").val( $("#guide_specialty_id").val() || $("#guide_specialty_id").attr("data-id") );
	if($("#guide_specialty_id").val() == "" || $("#guide_specialty_id").val() == "0"){
		alert("请选择一个专业");
		return false;
	}
	return true;
}
/* 验证 首页 院校 输入 */
function validateCollegeSearchForm() {

	if($("#unicountryStyleId").val() == "" || $("#unicountryStyleId").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	$("#university_name_id").val( $("#university_name_id").val() || $("#university_name_id").attr("data-id") );
	if($("#university_name_id").val() == "" || $("#university_name_id").val() == "0"){
		alert("请选择一个院校");
		return false;
	}
	return true;
}