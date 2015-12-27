<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%> 
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://ogp.me/ns/fb#" class=" js svg">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>搜索课程</title>
		<meta name="description" content=""/>
		<meta name="keywords" content=""/>
		
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
		</style>
	</head>
	<body id="programs">
		<s:action namespace="/user" name="head" executeResult="true"/>

		<form id="form_programs_search" action="programs">
			<input type="hidden" id="ranking_value" value="<s:property value="ranking"/>"/>
			<input type="hidden" id="area_value" value="<s:property value="area"/>"/>
			<input type="hidden" id="is_public_school_value" value="<s:property value="is_public_school"/>"/>
			<input type="hidden" id="time_of_enrollment_value" value="<s:property value="time_of_enrollment"/>"/>
			<input type="hidden" id="totef_value" value="<s:property value="totef"/>"/>
			<input type="hidden" id="ietls_value" value="<s:property value="ietls"/>"/>
			<input type="hidden" id="gre_value" value="<s:property value="gre"/>"/>
			<input type="hidden" id="gmat_value" value="<s:property value="gmat"/>"/>
		<div class="searcharea">
			<div class="content">
	            <div class="result">
					共 <span class="ftcolffbc00" id="universityNum"><s:property value="universityNum"/></span> 所学校，提供 <span class="ftcolffbc00" id="programNum"><s:property value="programNum"/></span>个课程
	            </div>
	            <div class="conditions">
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
				        <input type="hidden" id="program_specialty_id" name="program_specialty_id" value="${program_specialty_id}"/>
	                </div>
	                <div class="condit-1">
						<input id="university_name" name="university_name" class="ui-autocomplete-input txt-1" placeholder="请输入学校名称（选填）" data-url="<s:url value="/template/university_search"/>" value="${university_name}"/>
				        <input type="hidden" id="university_name_id" name="university_name_id"/>
	                </div>
	                <div class="condit-2">
						<!-- <input type="hidden" id="page_size" name="condition.page_size" value="10"/>
						<input type="hidden" id="page" name="condition.page" value="${condition.page}"/>
						<input type="hidden" id="sort_by" name="condition.orderBy" value="D.total_browse DESC"/> -->
						<input type="hidden" id="page_size" name="page_size" value="10"/>
						<input type="hidden" id="page" name="page" value="${page}"/>
						<input type="hidden" id="orderBy" name="orderBy" value="${orderBy}"/>
						<input type="hidden" id="record_total" value="<s:property value="universityNum"/>"/>
						<input type="hidden" name="defaultval" value="输入专业名称* " id="defaultval" />
	                    <input class="button-1" type="button" value="搜索" onclick="search_data_p_init();"/>
	                </div>
	            </div>
			</div>
		</div>
		
	    <div class="accuratesearch">
        	<div class="title">精确搜索</div>
        	<div class="content">
        		<s:set var="map" value="#{0:0,1:30,31:50,51:100 }"></s:set>
				<%
					//从服务器传来值
					pageContext.setAttribute("ranking", "全部", PageContext.REQUEST_SCOPE);
					pageContext.setAttribute("area", "0", PageContext.REQUEST_SCOPE);
				%>
				<s:set var="mapRanking" value="#{'全部','1-30','1-50','1-100','50-100','101-200','200以后'}"></s:set>
				<s:set var="mapArea" value="#{'全部','东北部','西部','五大湖地区','南部','中部','其他'}"></s:set>
				<s:set var="mapIsPublicSchool" value="#{-1:'全部',1:'公立',0:'私立'}"></s:set>
				<s:set var="mapTimeOfEnrollment" value="#{'全部','秋季','春季','夏季','冬季'}"></s:set>
	            <table class="table-1 table-accuratesearch" cellpadding="0" cellspacing="0">
	                <tbody>
	                    <tr>
	                        <td class="width-97 text-right">已选条件：
	                        </td>
	                        <td id="choose_td" data-type="program">
	                        </td>
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
	                    	<td class="width-97 text-right">地区：</td>
		                    <td>
		                        <s:iterator value="#mapArea">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="area==key || (area==null && key=='全部')">
											<input type="radio" name="area" value="<s:property value="key"/>" checked onchange="choose_submit('area', this);"/>
										</s:if>
										<s:else>
											<input type="radio" name="area" value="<s:property value="key"/>" onchange="choose_submit('area', this);"/>
										</s:else>
										<s:property value="key"/>
									</label>
								</s:iterator>
		                    </td>
	                    </tr>
	                    <tr id="search_tr_is_public_school" data-name="学校属性：">
		                    <td class="width-97 text-right">学校属性：</td>
		                    <td>
		                        <s:iterator value="#mapIsPublicSchool">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="is_public_school==key || (is_public_school==null && key==-1)">
											<input type="radio" name="is_public_school" value="<s:property value="key"/>" checked onchange="choose_submit('is_public_school', this);"/>
										</s:if>
										<s:else>
											<input type="radio" name="is_public_school" value="<s:property value="key"/>" onchange="choose_submit('is_public_school', this);"/>
										</s:else>
										<s:property value="value"/>
									</label>
								</s:iterator>
		                    </td>
		                </tr>
	                    <tr id="search_tr_time_of_enrollment" data-name="入学时间：" style="display:none">
	                    	<td class="width-97 text-right">入学时间：</td>
		                    <td>
		                        <s:iterator value="#mapTimeOfEnrollment">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="time_of_enrollment==key || (time_of_enrollment==null && key=='全部')">
											<input type="radio" name="time_of_enrollment" value="<s:property value="key"/>" checked onchange="choose_submit('time_of_enrollment', this);"/>
										</s:if>
										<s:else>
											<input type="radio" name="time_of_enrollment" value="<s:property value="key"/>" onchange="choose_submit('time_of_enrollment', this);"/> 
										</s:else>
										<s:property value="key"/>
									</label>
								</s:iterator>
		                    </td>
	                    </tr>
	                    <tr id="search_tr_totef" data-name="托福要求：" style="display:none">
	                    	<td class="width-97 text-right">托福要求：</td>
		                    <td>
		                        <label class="checkbox-inline" style="padding-top:8px">
									<input type="radio" name="totef" value="-1" <s:if test="totef==null||totef==-1"> checked</s:if> onchange="choose_submit('totef', this);"/> 全部
								</label>
								<s:iterator value="scoreList">
									<s:if test="categoryType==1">
										<label class="checkbox-inline" style="padding-top:8px">
											<s:if test="totef==(scope_lower_int+','+scope_higher_int)">
												<input type="radio" name="totef" value="${scope_lower_int},${scope_higher_int}" checked onchange="choose_submit('totef', this);"/> 
											</s:if>
											<s:else>
												<input type="radio" name="totef" value="${scope_lower_int},${scope_higher_int}" onchange="choose_submit('totef', this);"/> 
											</s:else>
											<s:if test="scopeLower==-1">
												小于<s:property value="scope_higher_int"/>
											</s:if>
											<s:elseif test="scopeHigher==-1">
												大于<s:property value="scope_lower_int"/>
											</s:elseif>
											<s:else>
												<s:property value="scope_lower_int"/>-<s:property value="scope_higher_int"/>
											</s:else>
										</label>
									</s:if>
								</s:iterator>
		                    </td>
	                    </tr>
	                    <tr id="search_tr_ietls" data-name="雅思要求：" style="display:none">
	                    	<td class="width-97 text-right">雅思要求：</td>
		                    <td>
		                        <label class="checkbox-inline" style="padding-top:8px">
									<input type="radio" name="ietls" value="-1" <s:if test="ietls==null||ietls==-1"> checked</s:if> onchange="choose_submit('ietls', this);"/> 全部
								</label>
								<s:iterator value="scoreList">
									<s:if test="categoryType==2">
										<label class="checkbox-inline" style="padding-top:8px">
											<s:if test="ietls==(scopeLower+','+scopeHigher)">
												<input type="radio" name="ietls" value="${scopeLower},${scopeHigher}" checked onchange="choose_submit('ietls', this);"/> 
											</s:if>
											<s:else>
												<input type="radio" name="ietls" value="${scopeLower},${scopeHigher}" onchange="choose_submit('ietls', this);"/> 
											</s:else>
											<s:if test="scopeLower==-1">
												小于<s:property value="scopeHigher"/>
											</s:if>
											<s:elseif test="scopeHigher==-1">
												大于<s:property value="scopeLower"/>
											</s:elseif>
											<s:elseif test="scopeHigher == scopeLower"><s:property value="scopeLower"/></s:elseif>
											<s:else>
												<s:property value="scopeLower"/>-<s:property value="scopeHigher"/>
											</s:else>
										</label>
									</s:if>
								</s:iterator>
		                    </td>
	                    </tr>
	                    <tr id="search_tr_gre" data-name="GRE要求：" style="display:none">	
	                        <td class="width-97 text-right">GRE要求：</td>
		                    <td>
		                    	<label class="checkbox-inline" style="padding-top:8px">
									<input type="radio" name="gre" value="-1" <s:if test="gre==null||gre==-1"> checked</s:if> onchange="choose_submit('gre', this);"/> 全部
								</label>
								<s:iterator value="scoreList">
									<s:if test="categoryType==3">
										<label class="checkbox-inline" style="padding-top:8px">
											<s:if test="gre==(scope_lower_int+','+scope_higher_int)">
												<input type="radio" name="gre" value="${scope_lower_int},${scope_higher_int}" checked onchange="choose_submit('gre', this);"/> 
											</s:if>
											<s:else>
												<input type="radio" name="gre" value="${scope_lower_int},${scope_higher_int}" onchange="choose_submit('gre', this);"/> 
											</s:else>
											<s:if test="scopeLower==-1">
												小于<s:property value="scope_higher_int"/>
											</s:if>
											<s:elseif test="scopeHigher==-1">
												大于<s:property value="scope_lower_int"/>
											</s:elseif>
											<s:else>
												<s:property value="scope_lower_int"/>-<s:property value="scope_higher_int"/>
											</s:else>
										</label>
									</s:if>
								</s:iterator>
		                    </td>
	                    </tr>
	                    <tr id="search_tr_gmat" data-name="GMAT要求：" style="display:none">
	                        <td class="width-97 text-right">GMAT要求：</td>
		                    <td>
		                    	<label class="checkbox-inline" style="padding-top:8px">
									<input type="radio" name="gmat" value="-1" <s:if test="gmat==null||gmat==-1"> checked</s:if> onchange="choose_submit('gmat', this);"/> 全部
								</label>
								<s:iterator value="scoreList">
									<s:if test="categoryType==4">
										<label class="checkbox-inline" style="padding-top:8px">
											<s:if test="gmat==(scope_lower_int+','+scope_higher_int)">
												<input type="radio" name="gmat" value="${scope_lower_int},${scope_higher_int}" checked onchange="choose_submit('gmat', this);"/> 
											</s:if>
											<s:else>
												<input type="radio" name="gmat" value="${scope_lower_int},${scope_higher_int}" onchange="choose_submit('gmat', this);"/> 
											</s:else>
											<s:if test="scopeLower==-1">
												小于<s:property value="scope_higher_int"/>
											</s:if>
											<s:elseif test="scopeHigher==-1">
												大于<s:property value="scope_lower_int"/>
											</s:elseif>
											<s:else>
												<s:property value="scope_lower_int"/>-<s:property value="scope_higher_int"/>
											</s:else>
										</label>
									</s:if>
								</s:iterator>
		                    </td>
	                    </tr>
	                </tbody>
	            </table>
	            <div class="moresearch">
	                <span class="smore">更多搜索条件<i></i></span>
	            </div>
        	</div>
    	</div>

		</form>
	    
	    <div class="reslut-2">
	        <div class="title">
	            <ul id="sort_ul">
	                <li class="current" onclick="set_sort_p(this, 'hot')">热度</li>
	                <li onclick="set_sort_p(this, 'ranking')">排名</li>
	                <li onclick="set_sort_p(this, 'remark')">点评</li>
	            </ul>
	            <div class="clear">
	            </div>
	        </div>
	        <div class="content">
	            <ul id="result_ul" class="courselist-1">
	            	<s:iterator value="resultUniversity" id="outer">
	            		<li class="courselist-li">
		                    <table class="table-2" cellpadding="0" cellspacing="0">
		                        <tr>
		                            <td class="text-left ulogo" style="width:14%">
		                                <img src="<s:property value="logo_url"/>" />
		                            </td>
		                            <td valign="top">
		                                <div class="universityname">
		                                	<a href="university?universityId=<s:property value="id"/>" target="_blank"><s:property value="university_name"/>/<s:property value="english_name"/></a>
		                                	<span class="ranking">
		                                		<span>综合排名 
		                                			<s:if test="ranking_comprehensive == 9999">无排名</s:if>
		                                			<s:else><s:property value="ranking_comprehensive"/></s:else>
		                                		</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:if test="resultSpecialtyRankUniID.contains(id)">
                                                <s:iterator value="resultSpecialtyRank" id="spe_rank">
                                                    <s:if test="#spe_rank.university_id==#outer.id">
                                                        <span>专业排名：<s:property value="#spe_rank.rank"/></span>
                                                    </s:if>                                          
                                                </s:iterator>
											</s:if>
											<s:else>
												<span>专业排名：无排名</span>
											</s:else>
		                                	</span>
		                                </div>
		                                <div class="info">
		                                    <span>
		                                    	<s:property value="country.name"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:if test="is_public_school==1">公立</s:if><s:else>私立</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="area.city"/>,<s:property value="area.state"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="scale"/>&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<br/>
		                                    	<s:property value="browse_number"/>浏览&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<s:property value="evaluate_number"/>点评&nbsp;&nbsp;&nbsp;&nbsp;
		                                    	<%--<span class="star"></span><span class="star2"></span><span class="star2"></span>--%>
		                                    </span>
		                                </div>
		                                <div class="course" onclick="show_courses(<s:property value="id"/>)">
		                                    <span>共<s:property value="numProgram"/>个课程</span><span class="open arrow_<s:property value="id"/>"></span>
		                                </div>
		                            </td>
		                            <td valign="top" class="text-center" style="width:19%">
		                                
		                                <div class="text-center sc">
		                                    <img src="../plugin/new/images/sc.png" onclick="common_collect('<s:property value="id"/>', '2')"/>
		                                </div>
		                            </td>
		                        </tr>
		                    </table>
				            <div class="morecourse">
				            	<ul>
		                        	<s:iterator value="resultProgram" id="inner">
										<s:if test="#inner.university_id==#outer.id">
											<li class="university_<s:property value="#inner.university_id"/>" style="display: none;">
				                                <div class="ctitle ftcol333333">
				                                    <span class="sclink clicks"><a href="javascript:void(0);" onclick="common_collect(<s:property value="#inner.id"/>, '3')">收藏</a></span>
				                                    <a href="program?programId=<s:property value="#inner.id"/>" target="_blank">
				                                    	<span><s:property value="#inner.program_name"/></span>
				                                    </a>
				                                    <span>
														<s:property value="programStatistic.total_browse"/>浏览
													</span>
				                                </div>
				                                <div class="ccontent  ftcol333333">
				                                    <%--<span class="dblink">对比</span>--%>
				                                    <%--<s:property value="#inner.studyLevel.name"/>--%>
				                                   	 学制：<s:property value="#inner.length_of_schooling"/> 学费：$<s:property value="#inner.tuition"/>(Total Program)
				                                </div>
				                            </li>
										</s:if>
									</s:iterator>
		                        </ul>
		                    </div>
		                </li>
					</s:iterator>
	            </ul>
	            <div class="pagination pull-right" id="pager" data-type="p" ></div>
	        </div>
	    </div>
	    <s:action namespace="/user" name="foot" executeResult="true"/>
		
		<script type="text/javascript">
		$(window).scroll(function(){
			if($(window).scrollTop()>350){
				$(".searcharea .conditions").css({"width":"1000px","margin":"0px auto"});
				$(".searcharea .conditions").addClass("fixedcon");
				$(".searcharea .result").hide();
				$(".searcharea .content").css({"width":"100%","height":"81px","position":"fixed","top":"0px","z-index":"1000"});
				$(".searcharea .content").addClass("fixedcontent");
				$(".ui-autocomplete").css({"top":$(window).scrollTop()+61,"z-index":"1001"})
			}
			else{
			$(".searcharea .conditions").css({"width":"100%","margin":"0px auto"});
				$(".searcharea .conditions").removeClass("fixedcon");
				$(".searcharea .result").show();
				$(".searcharea .content").css({"width":"965px","height":"auto","position":"inherit","top":"inherit","z-index":"inherit"});
				$(".searcharea .content").removeClass("fixedcontent");
				$(".ui-autocomplete").css("top","238px")
			}
		})
		</script>
		
		<script type="text/javascript">
			var set_page_init = function(){
				//$("#page").val(0);
				$("#page_ul li").removeClass("active");
				$("#page_ul li:first").addClass("active");
			}
		</script>
	</body>
</html>