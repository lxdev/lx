<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<% response.setHeader("Pragma","No-cache");//HTTP 1.1 
	response.setHeader("Cache-Control","no-cache");//HTTP 1.0 
	response.setHeader("Expires","0");//防止被proxy		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>留学芒果</title>
		<meta name="description" content="留学平台" />
		<meta name="keywords" content="出国, 网络, 留学, 芒果" />

		<jc:plugin name="new_css" />
		<jc:plugin name="auto_complete"/>
		<!-- <jc:plugin name="new_js" />
		<jc:plugin name="hot_base_js" />
		<jc:plugin name="hot_extend_js" />-->
		
	</head>
	<body class="searchbody">
		<s:action namespace="/user" name="head" executeResult="true"/>
		<div class="mainwrap">
			<div class="mainarea">
				<div class="title">
    				<ul id="cond_tabs">
    					<li class="current linebg"><span class="course" onclick="show_tab(1);">课程</span></li>
    					<li><div class="linebg"></div><span class="university" onclick="show_tab(2);">院校</span></li>
    					<li class="linebg"><span class="strategy" onclick="show_tab(3);">攻略</span></li>
    				</ul>
    			</div>
    			<div class="content">
    				<div id="search_course">
	    				<form id="homecoursesearch" name="searchpanel" action="programs" method="post" accept-charset="UTF-8">
							<input type="hidden" name="isFromFirst" value="true" id="isFromFirst" />
						    <table cellpadding="0" cellspacing="0" class="table-11">
							    <tr>
							    	<td><s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{countryId}" theme="simple" name="countryId" id="countryId" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h1')"/></td>
							    </tr>
							    <tr>
							    	<td><s:select list="%{studyLevelList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择学历--" onselect="%{studyLevelId}" theme="simple" name="studyLevelId" id="studyLevelId" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h2');defaultMessages('ctitle','defaultval');clearErrorMessagesforHomeDrop('locationList_h2');"/></td>
							    </tr>
							    <tr>
							    	<td>
				                        <input id="program_specialty" name="program_specialty" class="ui-autocomplete-input txt-5" placeholder="请输入专业名称" data-url="<s:url value="/template/specialty_search"/>"/>
				                        <input type="hidden" id="program_specialty_id" name="program_specialty_id"/>
							    	</td>
							    </tr>
							    <%--<tr>
							    	<td>
							    		<select class="form-control margin-bottom-15 select" id="specialtyId" name="specialtyId" value=""></select>
									</td>
							    </tr>--%>
							    <tr>
							    	<td>
							    		<input id="university_name" name="university_name" class="ui-autocomplete-input txt-5" placeholder="请输入学校名称（选填）" data-url="<s:url value="/template/university_search"/>"/>
				                        <input type="hidden" id="university_name_id" name="university_name_id"/>
							    	</td>
							    </tr>
							    <tr>
							    	<td align="right"><input class="button-1" type="submit" value="搜索" onclick="return validateCourseSearchForm('homecoursesearch')" /></td>
							    </tr>
						    </table>
						</form>
					</div>
					<div id="search_university">
						<form id="homecollegesearch" name="collegeSearchPanel" action="universitys" method="get" accept-charset="UTF-8">
							<input type="hidden" name="defaultunival" value="请输入学校名称，如：哈佛大学" id="defaultunival" />
							<input type="hidden" name="isFromFirst" value="true" id="isFromFirst" />
							<table cellpadding="0" cellspacing="0" class="table-11">
							    <tr>
							    	<td>
							    		<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{countryId2}" theme="simple" id="unicountryStyleId" name="unicountryStyleId" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h3');defaultMessages('searchpodcollege','defaultunival');makeDisableCollege('searchpodcollege','unicountryStyleId');clearErrorMessagesforHomeDrop('locationList_h3');"/>
									</td>
							    </tr>
								<tr>
									<td>
										<%--<select class="form-control margin-bottom-15 select" id="universityId" name="universityId" value=""></select>--%>
										<input id="university_name" name="university_name" class="ui-autocomplete-input txt-5" placeholder="院校" data-url="<s:url value="/template/university_search"/>"/>
				                        <input type="hidden" id="university_name_id" name="university_name_id"/>
									</td>
								</tr>
								<tr>
							    	<td></td>
							    </tr>
							    <tr>
							    	<td align="right"><input class="button-1" type="submit" value="搜索" onclick="return validateCollegeSearchForm('homecollegesearch')" /></td>
							    </tr>
							</table>
						</form>
					</div>
					<div id="search_guide">
						<form id="scholarShipFormBean" name="scholar" action="guides" method="post" onsubmit="return validateSpecialtyForm('search_guide');">
							<input type="hidden" name="isFromFirst" value="true" id="isFromFirst" />
							<table cellpadding="0" cellspacing="0" class="table-11">
							    <tr>
							    	<td>
							    		<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{countryId3}" theme="simple" id="studynat" name="scholarCountryName" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h8');defaultMessages('searchpodcollege','defaultunival');makeDisableCollege('searchpodcollege','stdlvid');clearErrorMessagesforHomeDrop('locationList_h8');"/>
									</td>
							    </tr>
							    <tr>
							    	<td>
							    		<%--<select class="form-control margin-bottom-15 select" id="selectedCat" name="selectedCat" value=""></select>
							    		--%><input id="guide_specialty" name="guide_specialty" class="ui-autocomplete-input txt-5" placeholder="专业" data-url="<s:url value="/template/guide_specialty_search"/>"/>
				                        <input type="hidden" id="guide_specialty_id" name="guide_specialty_id"/>
							    	</td>
							    </tr>
								<tr>
							    	<td align="right"><input class="button-1" type="submit" value="搜索" /></td>
							    </tr>
							</table>
						</form>
					</div>
			    </div>
			</div>
		</div>
		<div class="bottom-2">
        	&copy; 2014 young Ltd All rights reserved.
    	</div>
		<p class="stick" style="display: block;" id="back-top">
			<a href="#top"></a>
		</p>
		
		<%--<jc:plugin name="hot_base_js"/>--%>
		<jc:plugin name="main_js" />
		<script type="text/javascript">
		$(function($){
			/*var user_data = [{ id: -1, text: '请选择专业' }];
			$("#specialtyId").select2({
				language: 'zh-CN',
				placeholder: '请选择一个专业*',
				  ajax: {
				    url: '<s:url value="/template/specialty_search"/>',
				    cache: "true",
				    processResults: function (data, page) {
				        // parse the results into the format expected by Select2.
				        // since we are using custom formatting functions we do not need to
				        // alter the remote JSON data
				        var user_data = [];
				        if(null!=data.specialtyList)
				    		jQuery.each(data.specialtyList, function()
			    			{
				    			user_data.push({ id: this.id, text: this.specialty_name });
			    			});
				        return {
				          results: user_data
				        };
				      }
				  }
			});

			$("#selectedCat").select2({
				language: 'zh-CN',
				placeholder: '请选择一个专业*',
				  ajax: {
				    url: '<s:url value="/template/guide_specialty_search"/>',
				    cache: "true",
				    processResults: function (data, page) {
				        // parse the results into the format expected by Select2.
				        // since we are using custom formatting functions we do not need to
				        // alter the remote JSON data
				        var user_data = [];
				        if(null!=data.guideList)
				    		jQuery.each(data.guideList, function()
			    			{
				    			user_data.push({ id: this.guide_id, text: this.guide_name });
			    			});
				        return {
				          results: user_data
				        };
				      }
				  }
			});

			$("#universityId").select2({
				language: 'zh-CN',
				placeholder: '请选择一个学校*',
				  ajax: {
				    url: '<s:url value="/template/university_search"/>',
				    cache: "true",
				    processResults: function (data, page) {
				        // parse the results into the format expected by Select2.
				        // since we are using custom formatting functions we do not need to
				        // alter the remote JSON data
				        var user_data = [];
				        if(null!=data.universityList)
				    		jQuery.each(data.universityList, function()
			    			{
				    			user_data.push({ id: this.id, text: this.university_name });
			    			});
				        return {
				          results: user_data
				        };
				      }
				  }
			});

			if( $(".select2-selection--single").length >= 1 ){
				$(".select2-selection--single").addClass("selBx");
				$(".select2-selection--single").removeClass("select2-selection--single");

				$(".select2-selection__rendered").addClass("hgt_hn");

				$(".select2-selection__arrow").addClass("arw");

				$(".select2-container--default").css("width", "420px");
			}
			*/
			show_tab(1);
		});
		
		var show_tab = function(type){
			$("#cond_tabs li").removeClass("current");
			$("#cond_tabs li").eq(type-1).addClass("current");
			
			$("#search_course").hide();
			$("#search_university").hide();
			$("#search_guide").hide();
			switch(type){
			case 1:
				$("#search_course").show();
				break;
			case 2:
				$("#search_university").show();
				break;
			case 3:
				$("#search_guide").show();
				break;
			}
		}

			/*var elementText = $("#ctitle");
			var elementId = $("#specialtyId");
			function initAutoComplete(json) {
		          elementText.autocomplete({
		              autoFocus: true,
		              source: json,
		              position: {
		                  my: "left top",
		                  at: "left bottom",
		                  of: elementText,
		                  offset: "5 10"
		              },
		              minLength: 1,
		              focus: function () {
		                  return false;
		              },
		              open: function (event, ui) {
		                  var b = 22;
		              }.context,
		              select: function (event, ui) {
		            	  elementId.val(ui.item.value).trigger('change');
		                  elementText.val(ui.item.label);
		                  return false;
		              }
		          }).data("autocomplete")._renderItem = function (ul, item) {
		              var str1 = "<li class='item-" + item.value + "'></li>";
		              return $(str1)
		                      .data("item.autocomplete", item)
		                      .append("<a href='javascript:void(0)' >" + '<span >' + item.label + '</span>' + "</a>")
		                      .appendTo(ul);
		          };
		      };*/

		      $(document).ready(function () {
		    	  /*jQuery.post('<s:url value="/template/specialty_search"/>', {'term': ''},
					function(data)
					    	{
					    		if(null!=data.specialtyList){
					    			var dataList = [];
					    			for(var i = 0; i < data.specialtyList.length; i++){
					    				dataList.push({ value: data.specialtyList[i].id, label: data.specialtyList[i].specialty_name });
					    			}
					    			initAutoComplete(dataList);
					    		}
					    	},
					 "json");*/
		    	  //elementText.keyup(function () {
		          //    var _term = elementText.val();
		          //    var url = '<s:url value="/template/specialty_search"/>';
		          //    initAutoComplete(url);
		          //});
		      });

			
		</script>
	</body>
</html>
