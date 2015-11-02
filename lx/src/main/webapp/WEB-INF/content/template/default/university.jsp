<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>院校</title>
		<jc:plugin name="new_css" />
		<jc:plugin name="auto_complete"/>
		<jc:plugin name="pager_css_js"/>
		<%--<jc:plugin name="new_js" />--%>
		<jc:plugin name="hot_base_js" />
  	</head>
  	<body class="undetail">
		<s:action namespace="/user" name="head" executeResult="true"/>
		
		<div class="daohang"><a href="../template/first">首页</a><span>></span><a href="../template/universitys">院校库</a><span>></span><a href=""><s:property value="university.country.name" /></a></div>
    	<div class="clear"></div>
		
		<div class="reslut-2">
	        <div class="content">
	            <ul>
	                <li>
	                    <table class="table-6" cellpadding="0" cellspacing="0">
	                        <tr>
	                            <td class="ulogo-2">
	                                <img src="<s:property value="university.logo_url"/>" />
	                            </td>
	                            <td valign="top">
	                                <h1 class="paddingbottom15">
	                                	<s:property value="university.university_name" />/<s:property value="university.english_name" />
	                                </h1>
	                                <div class="info">
	                                    <span><s:property value="university.country.name" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="universityStatistic.total_browse"/>浏览&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="universityStatistic.total_evaluate"/>点评</span></div>
	                               
	                            </td>
	                            <td valign="top" class="text-center">
	                                <div class="ranking ftcolff6600">
	                                    <span>综合排名<s:property value="university.ranking_comprehensive" /></span>  
	                                </div>
	                                <div class="text-center sc">
	                                    <img src="../plugin/new/images/sc2.png" onclick="common_collect('<s:property value="university.id"/>', '2')"/>&nbsp;&nbsp;
	                                    <a href="<s:property value="university.website"/>"><img src="../plugin/new/images/gwkc.png" /></a>
	                                </div>
	                            </td>
	                        </tr>
	                    </table>
	                </li>
	            </ul>
	        </div>
	    </div>
	    
	    <div class="allinfo">
	        <ul>
	        <li class="dlwz">
		        <h4>地理位置</h4>
		        <h3><s:if test="university.area.isCity==1">城市</s:if><s:else>非城市</s:else></h3>
		        <h3 class="ftcol49b2a4"><s:property value="university.area.city"/>,<s:property value="university.area.state"/></h3>
	        </li>
	        <li class="xxgm">
		        <h4>学校规模</h4>
		        <h3 class="ftcol49b2a4 paddingtop10"><s:property value="university.scale"/></h3>
		        <h3 class="ftsize14 paddingtop15">本科人数</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.num_undergraduate"/></h3>
		        <h3 class="ftsize14 paddingtop15">研究生人数</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.num_graduate"/></h3>
	        </li>
	        <li class="gaik">
		        <h4>概况</h4>
		        <h3 class="ftsize14 paddingtop15">成立时间</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.establishing"/></h3>
		        <h3 class="ftsize14 paddingtop15">学校属性</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:if test="university.is_public_school==1">公立</s:if><s:else>私立</s:else></h3>
	        </li>
	        <li class="marginright0 sq">
		        <h4>录取数据</h4>
		        <h3 class="ftsize14 paddingtop15">录取率</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.rate_enrollment"/></h3>
		        <h3 class="ftsize14 paddingtop15">获取奖学金比例</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.rate_scholarship"/></h3>
	        </li>
	        <li class="nvbl">
		        <h4>男女比例</h4>
		        <table cellpadding="0" cellspacing="0" class="table-7">
		        <tr>
		        <td align="center">
		        <h1 class="ftcolde1674 ftsize33"><s:property value="university.rate_girl"/></h1>
		        <h1 class="ftcolde1674">女</h1>
		        </td>
		        <td align="center">
		        <h1 class="ftcol49b2a4 ftsize33"><s:property value="university.rate_boy"/></h1>
		        <h1 class="ftcol49b2a4">男</h1>
		        </td>
		        </tr>
		        </table>
	        </li>
	        <li class="gjxsbl">
	        	<h4>国际学生比例</h4>
	        	<h3 class="ftcol49b2a4 ftsize33 paddingtop38"><s:property value="university.rate_international"/></h3>
	        </li>
	        <li class="jiaoxue">
	        	<h4>教学</h4>
	        	<h3 class="ftsize14 paddingtop15">教授人数</h3>
	        	<h3 class="ftcol49b2a4 ftsize25"><s:property value="university.num_professor"/></h3>
		        <table cellpadding="0" cellspacing="0" class="table-8">
		        <tr>
		        <td align="center">
		        <h3 class="ftsize14 ftcolfff paddingtop15">学生和教授比例</h3>
		        <h3 class="ftcol49b2a4 ftsize25"><s:property value="university.rate_student"/>：<s:property value="university.rate_professor"/></h3>
		        </tr>
		        </table>
	        </li>
	        <li class="marginright0 contact">
	        	<h4>联系方式</h4>
		        <table cellpadding="0" cellspacing="0" class="table-9" style="width:281px;">
			        <tr>
			        <td align="left">
			        <h3 class="ftsize14 ftcolfff paddingtop15">电话：<span class="ftcol49b2a4"><s:property value="university.phone"/></span></h3>
			        </td>
			        </tr>
			        <tr>
			        <td align="left">
					<table>
					<tr>
					<td>
					<h3 class="ftsize14 ftcolfff paddingtop15">网站：</h3>
					</td>
					<td>
					<h3 class="ftsize14 ftcolfff paddingtop15"><span class="ftcol49b2a4"><a href="<s:property value="university.website.toLowerCase()"/>" class="ftcol49b2a4" target="_blank"><s:property value="university.website.toLowerCase()"/></a></span></h3>
					</td>
					</tr>
					</table>
			        
			        </td>
			        </tr>
			        <tr>
			        <td align="left">
			        <h3 class="ftsize14 ftcolfff paddingtop15">邮箱：<span class="ftcol49b2a4"><s:property value="university.email.toLowerCase()"/></span></h3>
			        </td>
			        </tr>
			        <tr>
			        <td>
			        </td>
			        </tr>
			        <tr>
			        <td>
			        </td>
			        </tr>
		        </table>
	        </li>
	        </ul>
	         <div class="clear"></div>
	    </div>
	    
	    <div class="clear"></div>
	    <div class="reslut-2" id="programs">
	    	<%--<div class="conditions">
	    		<div class="title"><h1 class="">查找课程</h1></div>
                <div class="condit-1">
                    <s:select list="%{countryList}" listKey="id" listValue="name" onselect="%{countryId}" headerKey="0" headerValue="--请选择国家--" theme="simple" name="nationId" id="nationId" cssClass="txt-1" onchange="setValue_AMW(this,'locationList_sr1');"/>		
                </div>
                <div class="condit-1">
                    <s:select list="%{studyLevelList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择学历--" theme="simple" name="studyLevelId" id="studyLevelId" cssClass="txt-1" onchange="setValue_AMW(this,'locationList_sr2');"/>
                </div>
                <div class="condit-1">
                    <s:textfield id="ctitle" name="courseTitle" value="" cssClass="grey selBx" cssStyle="width:70%" onfocus="clearDefaultText(this,'输入专业名称* ')" onblur="setDefaultText(this,'输入专业名称* ')" onkeyup="clearErrorMessagesforHomeText('ctitle');ajax_showOptions(this,'getCountriesByLetters',event, 'null','category')" onclick="ajax_options_hide()" onchange="clearajaxtextvalue('courseTitle_hidden')"></s:textfield>
                </div>
                <div class="condit-2">
                    <input class="button-1" type="button" value="搜索" onclick="loadRefineResult(&#39;search&#39;,&#39;Next&#39;);" /></div>
            </div>--%>
            <div class="conditions searchcourse">
            	<div class="condit-1"><h1>查找课程</h1></div>
                <div class="condit-1 width-176">
                    <s:select list="%{countryList}" listKey="id" listValue="name" onselect="%{countryId}" headerKey="0" headerValue="--请选择国家--" theme="simple" name="countryId" id="countryId" cssClass="txt-6"/>
                </div>
                <div class="condit-1 width-157">
                    <s:select list="%{studyLevelList}" listKey="id" listValue="name" onselect="%{studyLevelId}" headerKey="0" headerValue="--请选择学位--" theme="simple" name="studyLevelId" id="studyLevelId" cssClass="txt-7"/>
                </div>
                <div class="condit-1">
                    <s:if test="%{program_specialty!=null}">
						<s:textfield id="program_specialty" name="program_specialty" value="%{program_specialty}" cssClass="ui-autocomplete-input txt-1" placeholder="输入专业名称* " data-url="../template/specialty_search"></s:textfield>
					</s:if>
					<s:else>
						<input id="program_specialty" name="program_specialty" class="ui-autocomplete-input txt-1" placeholder="输入专业名称* " data-url="<s:url value="/template/specialty_search"/>"/>
					</s:else>
			        <input type="hidden" id="program_specialty_id" name="program_specialty_id"/>
                </div>
                <div class="condit-2">
                	<input type="hidden" id="university_name_id" name="university_name_id" value="${university.id}"/>
					<input type="hidden" id="page_size" name="condition.page_size" value="10"/>
					<input type="hidden" id="page" name="condition.page" value="${condition.page}"/>
					<input type="hidden" id="sort_by" name="condition.orderBy" value="D.total_browse DESC"/>
					<input type="hidden" id="record_total" value="1"/>
					<input type="hidden" name="defaultval" value="输入专业名称* " id="defaultval" />
                    <input class="button-1" type="button" value="搜索" onclick="search_data_p();"/>
                </div>
            </div>
            <div class="content">
	            <ul id="result_ul" class="courselist-1">
	            </ul>
	            <div class="pagination pull-right" id="pager" data-type="p" ></div>
	        </div>
            
            <%--<div class="row ui-sortable" id="programs_result"></div>--%>
	    </div>
	    <div class="reslut-2 comment" style="display:none;">
	        <div class="title">
	            <ul>
	                <li class="marginleft25 current">校友评论</li>
	                <li><span class="write"><a href="../university/evaluate_wizard">写评论</a></span></li>
	            </ul>
	            <div class="clear">
	            </div>
	        </div>
	        <div class="content">
	        	<s:iterator value="university.evaluateList" var="evaluate">
	        	
	        		<table cellpadding="0" cellspacing="0" class="table-10">
			            <tr>
			            <td class="width-73"><img src="<s:property value="#evaluate.evaluate_from_user.photo_url"/>" /></td>
			            <td>
			            <h3 class="ftcol607484">
			            	<a href="../template/evaluate_detail?id=<s:property value="#evaluate.id"/>">
			            		<s:property value="#evaluate.evaluate_title"/>
			            	</a>
			            </h3>
			            <h3 class="ftcola1a1a1 ftsize12 paddingtop10">
			            	<s:if test="#evaluate.evaluate_from_user != null">
								  发起人:<a href="../template/user_home?id=<s:property value="#evaluate.evaluate_from_user.user_id"/>" class="aw-user-name">
									<s:property value="#evaluate.evaluate_from_user.full_name"/>
								</a>
							</s:if>
							2015-06-02 09:49
			            </h3>
			            <h3 class="ftcol747474 ftsize14 paddingtop10">环球时报讯 近期热播的电视剧《虎妈猫爸》已然迅速聚集了关于“早教”、“..</h3>
			            </td>
			            </tr>
		            </table>
		            <div class="line-2"></div>
		            
				</s:iterator>
	        </div>
	    </div>
	    </div>
	    <div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
		<jc:plugin name="main_js" />
	    <script type="text/javascript">
	        $(".selres li").each(function (i) {
	            $(this).click(function () {
	                $(".selres li").removeClass("current");
	                $(this).addClass("current");
	                $(".reslut-2 .table-3").hide();
	                $(".reslut-2 .table-3").eq(i).show();
	            });
	        });
	        

			var topareaHeight = $(".toparea").height();
		    var searchareaHeight = $(".searcharea").height();
		    var accuratesearchHeight = $(".accuratesearch").height();
		    var titleHeight = $(".reslut-2 .title").height();
		    var courselistliHeight = $(".courselist-1 .courselist-li").height();
	        
	        $(function(){
				var obj = $("#btnSearchCourse");
				obj.bind("click", function(){  
 					var url = "ajax_programs";
 					var specialty = $("#ctitle").val();
					var studyLevelId = $("#studyLevelId").val();
					var countryId = $("#nationId").val();
					var url="ajax_programs";
      				var jsonProgram={specialty:specialty,studyLevelId:studyLevelId,countryId:countryId};//JSON对象  
      				var programStr=JSON.stringify(jsonProgram);//将JSON对象转变成JSON格式的字符串  
      				
 		            $.ajax({  
 		                type:'post',  
 		                url:url,
 		                dataType: 'json',
 		                data: ({json:programStr}),
 		                success:function(response){
 		                	$("#programs_result").html("");
 		                	$.each(response.resultProgram,function(i,pro){
 		                        var _program = $(
									"<div class='wells'>" 
 		                        		+ "<a href='program?programId=" + pro.id + "''>" 
									+ 	"<div class='col-md-8'>"
									+		"<h2>" + pro.name + "</h2>"
									+ 		"<hgroup class='ft-tle'><span>500浏览量</span></hgroup><br>"
									+		"<span>" + pro.studyLevel.name + "</span><span>" + pro.lengthOfSchooling + "</span><span>" + pro.tuition + "</span>"
									+ 	"</div>"
									+	"<div class='col-md-4'>"
									+		"<button class='btn btn-info'><i class='icon-white icon-heart'></i>收藏</button><br>"			
									+		"<button class='btn btn-info'><i class='icon-white icon-heart'></i>对比</button><br>"			
									+	"</div>"
 		                        		+ "</a>" 
     									+ "</div>" 
 		                        );
 		                        $("#programs_result").append(_program);  
 		                    })
 		                },
 		              	error: function (response) {
 		              		alert(response.responseText);
 		              	}
 		            })
 		        });
			});
		</script>

	</body>
</html>
