<%@ page language="java"  pageEncoding="utf-8"%>
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
		
		<div class="searcharea">
			<div class="content" style="width: 849px">
	            <div class="result">
					共 <span class="ftcolffbc00" id="universityNum"><s:property value="universityNum"/></span> 所学校
	            </div>
	            <div class="conditions">
	                <div class="condit-1">
	                    <s:select list="%{countryList}" listKey="id" listValue="name" onselect="%{unicountryStyleId}" headerKey="0" headerValue="--请选择国家--" theme="simple" name="unicountryStyleId" id="unicountryStyleId" cssClass="txt-1"/>
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
						<input type="hidden" id="page_size" name="condition.page_size" value="10"/>
						<input type="hidden" id="page" name="condition.page" value="${condition.page}"/>
						<input type="hidden" id="sort_by" name="condition.orderBy" value="D.total_browse DESC"/>
						<input type="hidden" id="record_total" value="<s:property value="universityNum"/>"/>
						<input type="hidden" name="defaultval" value="输入院校名称* " id="defaultval" />
	                    <!--<input class="button-1" type="button" value="搜索" onclick="loadRefineResult(&#39;search&#39;,&#39;Next&#39;);"/>-->
	                    <input class="button-1" type="button" value="搜索" onclick="search_data();"/>
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
				<s:set var="mapRanking" value="#{'全部','1-30','31-50','51-100'}"></s:set>
				<s:set var="mapArea" value="#{'全部','东北部','西部','五大湖地区','南部','中部','其他'}"></s:set>
				<s:set var="mapTuition" value="#{'全部','1-10000','10001-20000','20001-30000','30001-'}"></s:set>
				<s:set var="mapIsPublicSchool" value="#{-1:'全部', 1:'公立', 0:'私立'}"></s:set>
				
	            <table class="table-1 table-accuratesearch" cellpadding="0" cellspacing="0">
	                <tr>
	                    <td class="width-97 text-right">综合排名：</td>
	                    <td>
							<s:iterator value="#mapRanking">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="ranking==key || (ranking==null && key=='全部')">
										<input type="radio" name="ranking" value="<s:property value="key"/>" checked onchange="search_data();"> <s:property value="key"/>
									</s:if>
									<s:else>
										<input type="radio" name="ranking" value="<s:property value="key"/>" onchange="search_data();"> <s:property value="key"/>
									</s:else>
				   				</label>
							</s:iterator>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="width-64 text-right">地区：</td>
	                    <td>
	                        <s:iterator value="#mapArea">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="area==key || (area==null && key=='全部')">
										<input type="radio" name="area" value="<s:property value="key"/>" checked onchange="search_data();"> <s:property value="key"/>
									</s:if>
									<s:else>
										<input type="radio" name="area" value="<s:property value="key"/>" onchange="search_data();"> <s:property value="key"/>
									</s:else>
								</label>
							</s:iterator>
			
	                    </td>
	                </tr>
	                <tr>
	                    <td class="width-64 text-right">公私立：</td>
	                    <td>
	                        <s:iterator value="#mapIsPublicSchool">
								<label class="checkbox-inline" style="padding-top:8px">
									<s:if test="is_public_school==key || (is_public_school==null && key=='-1')">
										<input type="radio" name="is_public_school" value="<s:property value="key"/>" checked onchange="search_data();"> <s:property value="value"/>
									</s:if>
									<s:else>
										<input type="radio" name="is_public_school" value="<s:property value="key"/>" onchange="search_data();"> <s:property value="value"/>
									</s:else>
								</label>
							</s:iterator>
	                    </td>
	                </tr>
	            </table>
	        </div>
	    </div>

		<div class="reslut-2">
	        <div class="title">
	            <ul id="sort_ul">
	                <li class="marginleft25 current" onclick="set_sort(this, 'hot')">热度</li>
	                <li onclick="set_sort(this, 'ranking')">排名</li>
	                <li onclick="set_sort(this, 'remark')">点评</li>
	            </ul>
	            <div class="clear"></div>
	        </div>
	        <div class="content">
	            <ul id="result_ul">
	            	<s:iterator value="resultUniversity">
	            		<li>
		                    <table class="table-2" cellpadding="0" cellspacing="0">
		                        <tr>
		                            <td class="text-left ulogo" style="width:14%">
		                                <img src="<s:property value="logo_url"/>" />
		                            </td>
		                            <td valign="top">
		                                <div class="universityname">
		                                	<a href="university?universityId=<s:property value="id"/>"><s:property value="university_name"/>/<s:property value="english_name"/></a>
		                                </div>
		                                <div class="info">
		                                    <span><s:property value="country.name"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="browse_number"/>浏览&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="evaluate_number"/>点评</span>&nbsp;&nbsp;&nbsp;&nbsp;<span
		                                        class="star"></span><span class="star2"></span><span class="star2"></span>
										</div>
		                            </td>
		                            <td valign="top" class="text-center" style="width:19%">
		                                <div class="ranking ftcolff6600">
		                                    <span>综合排名 <s:property value="ranking_comprehensive"/></span>
		                                </div>
		                                <div class="text-center sc">
		                                    <img src="../plugin/new/images/sc.png" onclick="common_collect('<s:property value="id"/>', '2')"/>
		                                </div>
		                            </td>
		                        </tr>
		                    </table>
		                </li>
					</s:iterator>
	            </ul>
	            <%--<ul class="pagination pull-right" id="page_ul" data-value="1">
	            	<s:if test="universityNum > 10">
		                <li class="disabled less_disable"><a href="#">«</a></li>
		                <li class="active"><a href="#"><span class="num">1</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable"><a href="#"><span class="num">2</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable"><a href="#"><span class="num">3</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable"><a href="#"><span class="num">4</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable"><a href="#"><span class="num">5</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable"><a href="#">»</a></li>
	                </s:if>
	                <s:else>
		                <li class="disabled less_disable hide"><a href="#">«</a></li>
		                <li class="active hide"><a href="#"><span class="num">1</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable hide"><a href="#"><span class="num">2</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable hide"><a href="#"><span class="num">3</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable hide"><a href="#"><span class="num">4</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable hide"><a href="#"><span class="num">5</span> <span class="sr-only">(current)</span></a></li>
		                <li class="less_disable hide"><a href="#">»</a></li>
	                </s:else>
	            </ul>--%>
	            <div class="pagination pull-right" id="pager" data-type="u" ></div>
	        </div>
	    </div>
	    <div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
		
		<jc:plugin name="main_js" />
		<script type="text/javascript">
			/*排序点击*/
			var set_sort = function(obj, sort_type){
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
				
				search_data();
			}
			/*搜索数据*/
			var search_data = function(type){
				var universityName = ($("#university_name").val() == $("#defaultval").val() ? '' : $("#university_name").val());
				if($.trim(universityName) == ""){
					$("#university_name_id").val("");
					$("#university_name_id").attr("data-id", "");
				}
				var universityId = $("#university_name_id").val() || $("#university_name_id").attr("data-id");
				if(universityId != null && parseInt(universityId) > 0){
					window.location.href="university?universityId=" + universityId;
					return;
				}
				if(type == -1){
					
				}else {
					set_page_init();
				}
				
				var url = '<s:url value="/template/json_universitys"/>';
				
				var rankingBegin;
				var rankingEnd;
				var tempRanking = $('input[name="ranking"]:checked').val();
				if(tempRanking != '全部'){
					var tempRankingArray = tempRanking.split('-');
					rankingBegin = tempRankingArray[0];
					if(tempRankingArray.length >= 2)
						rankingEnd = tempRankingArray[1];
				}
				var tempAreaName = $('input[name="area"]:checked').val();
				var tempIsPublic = $('input[name="is_public_school"]:checked').val();
				
				var option = {
						'condition.country_id': $("#unicountryStyleId").val()
						, 'condition.university_name': universityName
						, 'condition.id': universityId
						, 'condition.page_size': $("#page_size").val()
						, 'condition.page': $("#page").val()
						, 'condition.rankingBegin': rankingBegin
						, 'condition.rankingEnd': rankingEnd
						, 'condition.areaName': (tempAreaName == '全部' ? '' : tempAreaName)
						, 'condition.is_public_school': tempIsPublic
						, 'condition.orderBy': $("#sort_by").val()
				};
				
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
						            lists += "<span>" + this.country.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.browse_number + "浏览&nbsp;&nbsp;&nbsp;&nbsp;" + this.evaluate_number + "点评</span>&nbsp;&nbsp;&nbsp;&nbsp;";
						            lists += "<span class='star'></span><span class='star2'></span><span class='star2'></span>";
						            lists += "</div>";
						            lists += "</td>";
						            lists += "<td valign='top' class='text-center' style='width:15%'>";
						            lists += "<div class='ranking ftcolff6600'>";
						            lists += "<span>综合排名 " + this.ranking_comprehensiveNew + "</span>";
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
				    		}
				    		if(data.universityNum != null && data.universityNum >= 0){
				    			set_page_result(data.universityNum);
				    		}
				    	},
				 "json");
			}
			
			var set_page_init = function(){
				$("#page").val(0);
				$("#page_ul li").removeClass("active");
				$("#page_ul li:first").addClass("active");
			}
	        
			$(document).ready(function() {
				var page_size = parseInt( $("#page_size").val() );
				var pageNum = parseInt( $("#record_total").val() );
				var pageCount = pageNum <= 0 ? 0 : pageNum % page_size == 0 ? pageNum / page_size : parseInt( pageNum / page_size ) + 1;
	            $("#pager").pager({ pagenumber: 1, pagecount: pageCount, buttonClickCallback: PageClick });
	        });
		</script>
	</body>
</html>