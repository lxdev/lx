/*! 自定义函数方法 */

/**************/
/***** 收藏 ****/
/**************/
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
var collect = function(id){
	var url = '<s:url value="/university/program_collect"/>';
	var option = {'follow.source_id': id, 'follow.follow_type': 3};

	jQuery.post(url,option,
	        function(data)
	    	{
	    		var lists="";
	    		lists+=default_option;
	    		if(null!=data.list)
	    		jQuery.each(data.list, function()
    			{		    			
		    			 if(default_id==this.id)
		    			 	lists+="<option value='"+this.id+"' selected>"+this.specialty_name+"</option>";
		    			 else
		    			 	lists+="<option value='"+this.id+"'>"+this.specialty_name+"</option>";
    			});
    			jQuery('#specialty_id').html(lists);
	    	},
	 "json");
};


var login_box_show = function(){
	$(".popbg").show();
    $("#login_box").show();
};

/********************/
/***** 加载等待动画 *****/
/*******************/
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
/**********************/
/***** 下拉框 自动完成 *****/
/**********************/
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
			if(ui.item.id) {
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
			}
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
/*****************/
/***** 课程搜索 *****/
/*****************/
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
/* programs page option search init */
var reset_program_search_options = function(){
	var options = ["ranking", "area", "is_public_school", "time_of_enrollment", "totef", "ietls", "gre", "gmat"];

	for(var i = 0; i < options.length; i++) {
		//$("input[name='" + options[i] + "']:eq(0)").click();
		$("input[name='" + options[i] + "']").each(function(i){
			if(i == 0){
				this.checked = true;
			}
		});
		if(i <= 2) {
			$("#search_tr_" + options[i]).show();
		}
		$("#search_tr_" + options[i]).attr("data-choosed", "false");
	}
	$("#choose_td").html("");
	$("#page").val(0);
}
/* programs page search click */
var search_data_p_init = function(){
	reset_program_search_options();
	search_data_p();
}

var set_university_search_options = function(){
	var options = ["ranking", "area", "is_public_school"];
	for(var i = 0; i < options.length; i++) {
		$("input[name='" + options[i] + "']").each(function(j, e){
			if(j > 0 && this.checked){
				choose_submit(options[i], this, true);
			}
		});
		//$("#search_tr_" + options[i]).show();
		//$("#search_tr_" + options[i]).attr("data-choosed", "false");
	}
	//$("#choose_td").html("");
	//$("#page").val(0);
}
/* programs page option search init */
var reset_university_search_options = function(){
	var options = ["ranking", "area", "is_public_school"];
	for(var i = 0; i < options.length; i++) {
		$("input[name='" + options[i] + "']").each(function(j){
			if(j == 0){
				this.checked = true;
			}
		});
		$("#search_tr_" + options[i]).show();
		$("#search_tr_" + options[i]).attr("data-choosed", "false");
	}
	$("#choose_td").html("");
	$("#page").val(0);
}
var search_data_u_init = function(){
	reset_university_search_options();
	search_data_u();
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
	
	var tempAreaName = $('input[name="area"]:checked').val() || "";
	var tempIsPublicSchool = $('input[name="is_public_school"]:checked').val() || -1;
	var tempTimeOfEnrollment = $('input[name="time_of_enrollment"]:checked').val() || ""
	
	var rankingBegin;
	var rankingEnd;
	var tempRanking = $('input[name="ranking"]:checked').val();
	if(tempRanking && tempRanking != '全部'){
		if(tempRanking == "200以后"){
			rankingBegin = 201;
			rankingEnd = 0;
		}else {
			var tempRankingArray = tempRanking.split('-');
			rankingBegin = tempRankingArray[0];
			if (tempRankingArray.length >= 2)
				rankingEnd = tempRankingArray[1];
		}
	}
	var scoreValueArray = [ { low: -1, high: null }, { low: -1, high: null }, { low: -1, high: null }, { low: -1, high: null } ];
	//var score_totef = -1, score_ietls = -1, score_gre = -1, score_gmat = -1;
	//var totefEnd, ietlsEnd, greEnd, gmatEnd;
	var scoreArray = [ "totef", "ietls", "gre", "gmat" ];
	for(var s = 0; s < scoreArray.length; s++){
		var tempValue = $("input[name='" + scoreArray[s] + "']:checked").val();
		if(tempValue && tempValue != '-1'){
			var tempArray = tempValue.split(',');
			scoreValueArray[s].low = tempArray[0];
			if(tempArray.length >= 2)
				scoreValueArray[s].high = tempArray[1];
		}
	}

	
	var option = {
			'condition.countryId': country_id
			, 'condition.study_level_id': study_level_id
			, 'condition.specialtyName': $("#program_specialty", parent).val()
			, 'condition.specialtyId': specialty_id
			, 'condition.universityName': $("#university_name", parent).val()
			, 'condition.university_id': $("#university_name_id", parent).val() || 0
			, 'condition.page_size': $("#page_size", parent).val()
			, 'condition.page': $("#page", parent).val()
			, 'condition.rankingBegin': rankingBegin
			, 'condition.rankingEnd': rankingEnd
			, 'condition.areaName': (tempAreaName == '全部' ? '' : tempAreaName)
			, 'condition.is_public_school': (tempIsPublicSchool == '全部' ? -1 : tempIsPublicSchool)
			, 'condition.score_totef': scoreValueArray[0].low
			, 'condition.score_ietls': scoreValueArray[1].low
			, 'condition.score_gre': scoreValueArray[2].low
			, 'condition.score_gmat': scoreValueArray[3].low
			, 'condition.totefEnd': scoreValueArray[0].high
            , 'condition.ietlsEnd': scoreValueArray[1].high
            , 'condition.greEnd': scoreValueArray[2].high
            , 'condition.gmatEnd': scoreValueArray[3].high
			, 'condition.time_of_enrollment': tempTimeOfEnrollment == '全部' ? '' : tempTimeOfEnrollment
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
			            lists += "					<a href='university?universityId=" + this.id + "'>" + this.university_name + "/" + this.english_name + "</a>";
			            lists += "				</div>";
			            lists += "				<div class='info'>";
			            lists += "					<span>" + this.country.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + (this.is_public_school == "1" ? "公立" : "私立") + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.area.city + "," + this.area.state + "&nbsp;&nbsp;&nbsp;&nbsp;"+ this.scale + "&nbsp;&nbsp;&nbsp;&nbsp;"
			            lists += "						<br/>" + this.browse_number + "浏览&nbsp;&nbsp;&nbsp;&nbsp;" + this.evaluate_number + "点评&nbsp;&nbsp;&nbsp;&nbsp;";
			            //lists += "					<span class='star'></span><span class='star2'></span><span class='star2'></span>";
			            lists += "					</span>"
			            lists += "				</div>";
			            lists += "				<div class='course' onclick='show_courses(" + this.id + ")'>";
			            lists += "					<span>共" + this.numProgram + "个课程</span><span class='open'></span>";
			            lists += "				</div>";
			            lists += "			</td>";
			            lists += "			<td valign='top' class='text-center' style='width:15%'>";
			            lists += "				<div class='ranking ftcolff6600'>";
			            //lists += "					<span>综合排名 " + this.ranking_comprehensive + "</span>";
						lists += "					<span>综合排名 " + (this.ranking_comprehensive == 9999 ? '无排名' : this.ranking_comprehensive) + "</span>";

						var tempIsExistRank = false;
						jQuery.each(data.resultSpecialtyRank, function()
				    			{
					            	if(_u.id == this.university_id){
					            		tempIsExistRank = true;
					            		//lists += "<span>" + $("#program_specialty", parent).val() + "排名：" + this.rank + "</span>";
					            		lists += "<span>专业排名：" + this.rank + "</span>";
					            	}
				    			});
						if(!tempIsExistRank){
							lists += "<span>专业排名：无排名</span>"
						}
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
	    			if($.trim(lists) == ""){
	    				alert("未收录相关课程!");
	    			}
	    		}
	    		if(data.universityNum != null && data.universityNum >= 0){
	    			set_page_result(data.universityNum, data.programNum);
	    		}
	    	},
	 "json");
}
/* 点击搜索出的院校，展开其下课程 */
function show_courses(type){
	var arrowcurrent=".arrow_" + type;
    var univername=$(arrowcurrent).parent().parent().find(".universityname a").html();
    $(".courselist-li").each(function (i) {
        if ($(this).find(".universityname a").html() == univername) {
        
            if (i == 0) {
                $("body").scrollTop(topareaHeight + searchareaHeight + accuratesearchHeight + 0 + titleHeight);
            }
            else {
                $("body").scrollTop(topareaHeight + searchareaHeight + accuratesearchHeight + 40 + titleHeight + courselistliHeight * i + 15 * (i - 1));
            }
        }
    });
    
    if ($(".university_" + type).is(":visible")) {
        $(".arrow_" + type).removeClass("close");
        $(".university_" + type).hide();
    } else {
        $(".morecourse li").hide();	                
        $(".arrow_" + type).addClass("close");
        $(".university_" + type).show();
    }
}

//更多选项
$(".moresearch").click(function(){
    if($(".moresearch .smore").hasClass("close")){
        $(".moresearch .smore").removeClass("close");
        $(".table-accuratesearch tr").each(function(i){
            if(i>3 && $(this).attr("data-choosed") != "true"){
                $(this).hide();
            }
        });
    }
    else{
        $(".moresearch .smore").addClass("close");
        $(".table-accuratesearch tr").each(function(i){
            if(i>2 && $(this).attr("data-choosed") != "true"){
                $(this).show();
            }
        });
    }
})

//选项选中效果
var choose_cancel = function(obj){
	var dataType = $(obj).attr("data-type");
	//var dataId = $(obj).attr("data-id");
	
	//$("input[name='" + dataType + "']").each(function(e, i){
	$("input[name='" + dataType + "']").each(function(i){
		if(i == 0){
			this.checked = true;
		}
	});
	$("#search_tr_" + dataType).show();
	$("#search_tr_" + dataType).attr("data-choosed", "false");
	
	$(obj).prev().remove();
	$(obj).remove();

	var chooseParent = $("#choose_td");
	if(chooseParent.attr("data-type") == "program")
		search_data_p();
	else if(chooseParent.attr("data-type") == "university")
		search_data_u();
}
/**
 * 精确查找
 * @param type	如 ranking, area 等
 * @param obj	点击或设置的对象 如 checkbox
 * @param is_not_search		true则不执行查找
 * @returns
 */
var choose_submit = function(type, obj, is_not_search){
	var chooseParent = $("#choose_td");
	var optionParent = $(obj).parent().parent().parent();
	var option =  "<i class='crumbs-arrow'>&gt;</i>";
	var option_value = $(obj)[0].nextSibling.nodeValue;
	option += "<a onclick='choose_cancel(this)' data-type='" + type + "' class='ss-item'><b>" + optionParent.attr("data-name") + "</b><em>" + option_value + "</em><i></i></a>";
	chooseParent.append(option);
	optionParent.hide();
	optionParent.attr("data-choosed", "true");

	if(!is_not_search){
		if(chooseParent.attr("data-type") == "program")
			search_data_p();
		else if(chooseParent.attr("data-type") == "university")
			search_data_u();
	}
}

/*****************/
/***** 院校搜索 *****/
/*****************/
/*排序点击*/
var set_sort_u = function(obj, sort_type){
	var order;
	if(sort_type){
		$("#sort_ul li").removeClass("current");
		$(obj).addClass("current");
		if(sort_type == 'hot')	//热度
			order = " D.total_browse DESC ";
		else if(sort_type == 'ranking')	//排名
			order = " A.ranking_comprehensive ASC ";
		else if(sort_type == 'remark')	//点评
			order = " C.evaluate_number DESC ";
	}
	$("#sort_by").val(order);
	
	search_data_u();
}
/*搜索数据*/
var search_data_u = function(type){
	var universityName = ($("#university_name").val() == $("#defaultval").val() ? '' : $("#university_name").val());
	if($.trim(universityName) == ""){
		$("#university_name_id").val("");
		$("#university_name_id").attr("data-id", "");
	}
	var universityId = $("#university_name_id").val() || $("#university_name_id").attr("data-id");
	if(universityId != null && parseInt(universityId) > 0){
		clear_university();
		//window.location.href="university?universityId=" + universityId;
		window.open("university?universityId=" + universityId);
		return;
	}
	if(type == -1){
		
	}else {
		set_page_init();
	}
	//open_loading();
	
	var url = '../template/json_universitys';
	
	var rankingBegin;
	var rankingEnd;
	var tempRanking = $('input[name="ranking"]:checked').val();
	if(tempRanking != '全部'){
		if(tempRanking == "200以后"){
			rankingBegin = 201;
			rankingEnd = 0;
		}else {
			var tempRankingArray = tempRanking.split('-');
			rankingBegin = tempRankingArray[0];
			if (tempRankingArray.length >= 2)
				rankingEnd = tempRankingArray[1];
		}
	}
	//var tempAreaName = $('input[name="area"]:checked').val();
	var tempIsPublic = $('input[name="is_public_school"]:checked').val();
	
	var option = {
			'condition.country_id': $("#unicountryStyleId").val()
			, 'condition.university_name': universityName
			, 'condition.id': universityId
			, 'condition.page_size': $("#page_size").val()
			, 'condition.page': $("#page").val()
			, 'condition.rankingBegin': rankingBegin
			, 'condition.rankingEnd': rankingEnd
			//, 'condition.areaName': (tempAreaName == '全部' ? '' : tempAreaName)
			, 'condition.is_public_school': tempIsPublic
			, 'condition.orderBy': $("#sort_by").val()
	};

	//2015-11-22 将查询提交方式改为页面刷新
	$("#country_id").val($("#unicountryStyleId").val());
	$("#university_name").val(universityName);
	$("#rankingBegin").val(rankingBegin);
	$("#rankingEnd").val(rankingEnd);
	//$("#areaName").val(tempAreaName == '全部' ? '' : tempAreaName);
	$("#is_public_school").val(tempIsPublic);

	//var hash = "#search";
	//for(var a in option){
	//	hash += "&" + a + "=" + option[a];
	//}
	//window.location.href = window.location.origin + window.location.pathname + hash;
	//window.location.reload();
	$("#form_universitys_search").submit();
	return ;

	jQuery.post(url, option,
	        function(data)
	    	{
	    		var lists="";
	    		if(null!=data.resultUniversity){
		    		jQuery.each(data.resultUniversity, function()
	    			{
		    			lists += "<li>";
		    			lists += "<table class='table-2' cellpadding='0' cellspacing='0'>";
		    			lists += "<tr>";
		    			lists += "<td class='text-center ulogo' style='width:20%'>";
		    			lists += "<img src='" + this.logo_url + "'/>";
		    			lists += "</td>";
		    			lists += "<td valign='top'>";
			            lists += "<div class='universityname'>";
			            lists += "<a href='university?universityId=" + this.id + "'>" + this.university_name + "/" + this.english_name + "</a>";
			            lists += "</div>";
			            lists += "<div class='info'>";
			            lists += "					<span>" + this.country.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + (this.is_public_school == "1" ? "公立" : "私立") + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.area.city + "," + this.area.state + "&nbsp;&nbsp;&nbsp;&nbsp;"+ this.scale + "&nbsp;&nbsp;&nbsp;&nbsp;"
			            lists += "						<br/>" + this.browse_number + "浏览&nbsp;&nbsp;&nbsp;&nbsp;" + this.evaluate_number + "点评&nbsp;&nbsp;&nbsp;&nbsp;";
			            //lists += "					<span class='star'></span><span class='star2'></span><span class='star2'></span>";
			            lists += "					</span>"
			            lists += "</div>";
			            lists += "</td>";
			            lists += "<td valign='top' class='text-center' style='width:15%'>";
			            lists += "<div class='ranking ftcolff6600'>";
			            //lists += "<span>综合排名 " + this.ranking_comprehensiveNew + "</span>";
						lists += "<span>综合排名 " + (this.ranking_comprehensive == 9999 ? '无排名' : this.ranking_comprehensive) + "</span>";
			            lists += "</div>";
			            lists += "<div class='text-center sc'>";
			            lists += "<img src='../plugin/new/images/sc.png' onclick='common_collect(" + this.id + ", 2)'/>";
			            lists += "</div>";
			            lists += "</td>";
			            lists += "</tr>";
			            lists += "</table>";
			            lists += "</li>";
	    			});
	    			jQuery('#result_ul').html(lists);
	    			close_loading();
	    			if($.trim(lists) == ""){
	    				alert("未收录相关院校!");
	    			}
	    		}
	    		if(data.universityNum != null && data.universityNum >= 0){
	    			set_page_result(data.universityNum);
	    		}

				clear_university();
	    	},
	 "json");
}
var clear_university = function(){
	if($.trim($("#university_name").val()) != ""){
		$("#university_name").val("");
		$("#university_name_id").val("");
		$("#university_name_id").attr("data-id", "");
	}
}
/**/
/***************/
/***** 翻页 *****/
/***************/
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
    	search_data_u(-1);
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
function validateSpecialtyForm(formName) {
	var form = $("#" + formName);
	if($("#studynat", form).val() == "" || $("#studynat", form).val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	//$("#guide_specialty_id", form).val( $("#guide_specialty_id", form).val() || $("#guide_specialty_id", form).attr("data-id") );
	var guideId = $("#guide_specialty_id", form).val() || $("#guide_specialty_id", form).attr("data-id");
	if(guideId != "" && guideId != "0" && guideId != 0){
		//alert("请选择一个专业");
		window.open("guide?guide_id=" + guideId);
		return false;
	}
	return true;
}
/* 验证 首页 院校 输入 */
function validateCollegeSearchForm(formName) {
	var form = $("#" + formName);
	if($("#unicountryStyleId", form).val() == "" || $("#unicountryStyleId", form).val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	//$("#university_name_id", form).val( $("#university_name_id", form).val() || $("#university_name_id", form).attr("data-id") );
	var universityId = $("#university_name_id", form).val() || $("#university_name_id", form).attr("data-id");
	if(universityId != "" && universityId != "0" && universityId != 0){
		window.open("university?universityId=" + universityId);
		return false;
	}
	return true;
}
