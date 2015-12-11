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
    					<li class="current linebg" style="margin-left:54px"><span class="course" onclick="show_tab(1);">课程</span></li>
    					<li><span class="university" onclick="show_tab(2);">院校</span></li>
    					<li class="linebg"><span class="strategy" onclick="show_tab(3);">攻略</span></li>
    				</ul>
    			</div>
    			<div class="content">
    				<div id="search_course">
	    				<form id="homecoursesearch" name="searchpanel" action="programs" method="post" accept-charset="UTF-8">
							<input type="hidden" name="isFromFirst" value="true" id="isFromFirst" />
						    <table cellpadding="0" cellspacing="0" class="table-11">
							    <tr>
							    	<td>
							    		<!-- 控件name 必须和 获取到的变量完全一样，才能 使用 onselect -->
							    		<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{countryId}" theme="simple" name="countryId" id="countryId" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h1')"/>
							    	</td>
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
							    <tr>
							    	<td>
							    		<input id="university_name" name="university_name" class="ui-autocomplete-input txt-5" placeholder="请输入学校名称（选填）" data-url="<s:url value="/template/university_search"/>"/>
				                        <input type="hidden" id="university_name_id" name="university_name_id"/>
							    	</td>
							    </tr>
							    <tr>
							    	<td align="center"><input class="button-1" type="submit" value="搜索" onclick="return validateCourseSearchForm('homecoursesearch')" /></td>
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
							    		<!-- 控件name 必须和 获取到的变量完全一样，才能 使用 onselect -->
							    		<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{unicountryStyleId}" theme="simple" id="unicountryStyleId" name="unicountryStyleId" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h3');defaultMessages('searchpodcollege','defaultunival');makeDisableCollege('searchpodcollege','unicountryStyleId');clearErrorMessagesforHomeDrop('locationList_h3');"/>
									</td>
							    </tr>
								<tr>
									<td>
										<input id="university_name" name="university_name" class="ui-autocomplete-input txt-5" placeholder="请输入学校名称" data-url="<s:url value="/template/university_search"/>"/>
				                        <input type="hidden" id="university_name_id" name="university_name_id"/>
									</td>
								</tr>
								
							    <tr>
							    	<td align="center"><input class="button-1" type="submit" value="搜索" onclick="return validateCollegeSearchForm('homecollegesearch')" /></td>
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
							    		<!-- 控件name 必须和 获取到的变量完全一样，才能 使用 onselect -->
							    		<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" onselect="%{studynat}" theme="simple" id="studynat" name="studynat" cssClass="txt-3" onchange="setValue_AMW(this,'locationList_h8');defaultMessages('searchpodcollege','defaultunival');makeDisableCollege('searchpodcollege','stdlvid');clearErrorMessagesforHomeDrop('locationList_h8');"/>
									</td>
							    </tr>
							    <tr>
							    	<td>
							    		<input id="guide_specialty" name="guide_specialty" class="ui-autocomplete-input txt-5" placeholder="请输入专业名称" data-url="<s:url value="/template/guide_specialty_search"/>"/>
				                        <input type="hidden" id="guide_specialty_id" name="guide_specialty_id"/>
							    	</td>
							    </tr>
								<tr>
							    	<td align="center"><input class="button-1" type="submit" value="搜索" /></td>
							    </tr>
							</table>
						</form>
					</div>
			    </div>
			</div>
		</div>
		
		<s:action namespace="/user" name="foot" executeResult="true"/>
		
		<script type="text/javascript">
		$(function($){
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
			
		</script>
	</body>
</html>
