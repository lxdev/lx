<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%> 
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://ogp.me/ns/fb#" class=" js svg">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>搜索院校</title>
		<meta name="description" content="Compare postgraduate computer courses and degree programs in USA. Study a postgraduate degree in computer .">
		<meta name="keywords" content="">
	
		<jc:plugin name="new_css" />
		<jc:plugin name="auto_complete"/>
		<jc:plugin name="pager_css_js"/>
		<jc:plugin name="hot_base_js" />
		<style>

.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover, .pagination>.active>span:hover, .pagination>.active>a:focus, .pagination>.active>span:focus {
z-index: 2;
color: #fff;
cursor: default;
background-color: #428bca;
border-color: #428bca;
}
.pagination>li>a, .pagination>li>span {
position: relative;
float: left;
padding: 6px 12px;
margin-left: -1px;
line-height: 1.42857143;
color: #428bca;
text-decoration: none;
background-color: #fff;
border: 1px solid #ddd;
}
.sr-only {
position: absolute;
width: 1px;
height: 1px;
padding: 0;
margin: -1px;
overflow: hidden;
clip: rect(0,0,0,0);
border: 0;
}

.hide {
	display:none;
}
.show {
	display:block;
}
		</style>
	</head>
	<body>
		<s:action namespace="/user" name="head" executeResult="true"/>

		<form id="form_universitys_search" action="universitys">
			<input type="hidden" id="ranking_value" value="<s:property value="ranking"/>"/>
			<input type="hidden" id="area_value" value="<s:property value="area"/>"/>
			<input type="hidden" id="is_public_school_value" value="<s:property value="is_public_school"/>"/>

		<div class="searcharea">
			<div class="content" style="width: 849px">

	            <div class="result">
					共 <span class="ftcolffbc00" id="universityNum"><s:property value="universityNum"/></span> 所学校
	            </div>
	            <div class="conditions">
	                <div class="condit-1">
						<s:if test="%{unicountryStyleId!=null && unicountryStyleId != 0}">
	                    	<s:select list="%{countryList}" listKey="id" listValue="name" onselect="%{unicountryStyleId}" headerKey="0" headerValue="--请选择国家--" theme="simple" name="unicountryStyleId" id="unicountryStyleId" cssClass="txt-1"/>
						</s:if>
						<s:else>
							<s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" theme="simple" name="unicountryStyleId" id="unicountryStyleId" cssClass="txt-1"/>
						</s:else>
	                </div>
	                <div class="condit-1" style="width:495px">
	                    <s:if test="%{university_name!=null}">
							<%--<s:textfield id="college" name="college" value="%{college}" cssClass="txt-1" onfocus="clearDefaultText(this,'输入院校名称* ')" onblur="setDefaultText(this,'输入院校名称* ')" onkeyup="clearErrorMessagesforHomeText('ctitle');ajax_showOptions(this,'getCountriesByLetters',event, 'null','category')" onclick="ajax_options_hide()" onchange="clearajaxtextvalue('courseTitle_hidden')"></s:textfield>--%>
							<s:textfield id="university_name" name="university_name" value="%{university_name}" cssClass="ui-autocomplete-input txt-3" placeholder="输入院校名称* " data-url="/template/university_search"></s:textfield>
						</s:if>
						<s:else>
							<%--<input type="text" id="college" name="college" value="输入院校名称* " class="txt-1" onfocus="clearDefaultText(this,'输入院校名称* ')" onblur="setDefaultText(this,'输入院校名称* ')" onkeyup="clearErrorMessagesforHomeText('ctitle');ajax_showOptions(this,'getCountriesByLetters',event, 'null','category')" onclick="ajax_options_hide()" onchange="clearajaxtextvalue('courseTitle_hidden')"/>--%>
							<input id="university_name" name="university_name" class="ui-autocomplete-input txt-3" placeholder="输入院校名称* " data-url="<s:url value="/template/university_search"/>"/>
						</s:else>
				        <input type="hidden" id="university_name_id" name="university_name_id" value="${university_name_id}"/>
	                </div>
	                <div class="condit-2">
						<!-- <input type="hidden" id="page_size" name="condition.page_size" value="10"/>
						<input type="hidden" id="page" name="condition.page" value="${condition.page}"/>
						<input type="hidden" id="sort_by" name="condition.orderBy" value="D.total_browse DESC"/> -->
						<input type="hidden" id="page_size" name="page_size" value="10"/>
						<input type="hidden" id="page" name="page" value="${page}"/>
						<input type="hidden" id="orderBy" name="orderBy" value="${orderBy}"/>
						<input type="hidden" id="record_total" value="<s:property value="universityNum"/>"/>
						<!-- <input type="hidden" name="defaultval" value="输入院校名称* " id="defaultval" /> -->
	                    <input class="button-1" type="button" value="搜索" onclick="search_data_u_init();"/>
	                </div>
	            </div>
			</div>
		</div>
		
		<div class="accuratesearch">
	        <div class="title"> 精确搜索</div>
	        <div class="content">
	        	
				<%
					//从服务器传来值
					pageContext.setAttribute("ranking", "全部", PageContext.REQUEST_SCOPE);
					pageContext.setAttribute("area", "0", PageContext.REQUEST_SCOPE);
				%>
				<s:set var="mapRanking" value="#{'全部','1-30','31-50','51-100','101-200','200以后'}"></s:set>
				<s:set var="mapArea" value="#{'全部','东北部','西部','五大湖地区','南部','中部','其他'}"></s:set>
				<s:set var="mapTuition" value="#{'全部','1-10000','10001-20000','20001-30000','30001-'}"></s:set>
				<s:set var="mapIsPublicSchool" value="#{-1:'全部', 1:'公立', 0:'私立'}"></s:set>
				
	            <table class="table-1 table-accuratesearch" cellpadding="0" cellspacing="0">
					<tr>
						<td class="width-97 text-right">已选条件：
						</td>
						<td id="choose_td" data-type="university"></td>
					</tr>
	                <tr id="search_tr_ranking" data-name="综合排名：">
	                    <td class="width-97 text-right">综合排名：</td>
	                    <td>
							<s:iterator value="#mapRanking">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="ranking==key || (ranking==null && key=='全部')">
										<input type="radio" name="ranking" value="<s:property value="key"/>" checked onchange="choose_submit('ranking', this);"/>
									</s:if>
									<s:else>
										<input type="radio" name="ranking" value="<s:property value="key"/>" onchange="choose_submit('ranking', this);"/>
									</s:else>
									<s:property value="key"/>
				   				</label>
							</s:iterator>
	                    </td>
	                </tr>
	                <tr id="search_tr_area" data-name="地区：">
	                    <td class="width-64 text-right">地区：</td>
	                    <td>
	                        <s:iterator value="#mapArea">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="area==key || (area==null && key=='全部')">
										<input type="radio" name="area" value="<s:property value="key"/>" checked onchange="choose_submit('area', this);">
									</s:if>
									<s:else>
										<input type="radio" name="area" value="<s:property value="key"/>" onchange="choose_submit('area', this);">
									</s:else>
									<s:property value="key"/>
								</label>
							</s:iterator>
			
	                    </td>
	                </tr>
	                <tr id="search_tr_is_public_school" data-name="学校属性：">
	                    <td class="width-64 text-right">学校属性：</td>
	                    <td>
	                        <s:iterator value="#mapIsPublicSchool">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="is_public_school==key || (is_public_school==null && key=='-1')">
										<input type="radio" name="is_public_school" value="<s:property value="key"/>" checked onchange="choose_submit('is_public_school', this);">
									</s:if>
									<s:else>
										<input type="radio" name="is_public_school" value="<s:property value="key"/>" onchange="choose_submit('is_public_school', this);">
									</s:else>
									<s:property value="value"/>
								</label>
							</s:iterator>
	                    </td>
	                </tr>
	            </table>
	        </div>
	    </div>

		</form>

		<div class="reslut-2">
	        <div class="title">
	            <ul id="sort_ul">
	                <li class="marginleft25 current" onclick="set_sort_u(this, 'hot')">热度</li>
	                <li onclick="set_sort_u(this, 'ranking')">排名</li>
	                <li onclick="set_sort_u(this, 'remark')">点评</li>
	            </ul>
	            <div class="clear"></div>
	        </div>
	        <div class="content">
	            <ul id="result_ul" class="courselist-1">
	            	<s:iterator value="resultUniversity">
	            		<li class="courselist-li">
		                    <table class="table-2" cellpadding="0" cellspacing="0">
		                        <tr>
		                            <td class="text-left ulogo" style="width:14%">
		                                <img src="<s:property value="logo_url"/>" />
		                            </td>
		                            <td valign="top">
		                                <div class="universityname">
		                                	<a href="university?universityId=<s:property value="id"/>"><s:property value="university_name"/>/<s:property value="english_name"/></a>
		                                	<span class="ranking"><span>综合排名 <s:property value="ranking_comprehensive"/></span></span>
		                                </div>
		                                <div class="info">
		                                    <span>
		                                    	<s:property value="country.name"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:if test="is_public_school==1">公立</s:if><s:else>私立</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="area.city"/>,<s:property value="area.state"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="scale"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<br/>
		                                    	<s:property value="browse_number"/>浏览&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="evaluate_number"/>点评
		                                    </span>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    <!-- <span class="star"></span><span class="star2"></span><span class="star2"></span> -->
										</div>
		                            </td>
		                            <td valign="top" class="text-center" style="width:19%">
		                                
		                                <div class="text-center sc">
		                                    <img src="../plugin/new/images/sc.png" onclick="common_collect('<s:property value="id"/>', '2')"/>
		                                </div>
		                            </td>
		                        </tr>
		                    </table>
		                </li>
					</s:iterator>
				</ul>
	            <div class="pagination pull-right" id="pager" data-type="u"></div>
	        </div>
	    </div>
	    <s:action namespace="/user" name="foot" executeResult="true"/>
		<script type="text/javascript">
			
			var set_page_init = function(){
				//$("#page").val(0);
				$("#page_ul li").removeClass("active");
				$("#page_ul li:first").addClass("active");
			}
	        
		</script>
	</body>
</html>