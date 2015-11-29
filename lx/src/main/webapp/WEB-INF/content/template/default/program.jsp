<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>课程</title>
    <!--<jc:plugin name="hot_base_css" />
	  jquery库 
	<jc:plugin name="jquery_new" />
	<jc:plugin name="hot_base_js" />
	<jc:plugin name="bootstrap3" />-->
	
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
	</head>
  	<body>
	    <s:action namespace="/user" name="head" executeResult="true"/>
	    
	    <div class="daohang"><a href="">首页</a><span>></span><a href="">院校库</a><span>></span><a href=""><s:property value="resultProgram.university.country.name"/></a></div>
	    <div class="clear"></div>
	    
	    <div class="reslut-2">
	        <div class="content">
	            <ul>
	                <li>
	                    <table class="table-2" cellpadding="0" cellspacing="0">
	                        <tr>
	                            <td class="ulogo-2">
	                                <!-- <img src="../plugin/new/images/img-2.jpg" /> -->
	                                <img src="<s:property value="resultProgram.university.logo_url"/>"/>
	                            </td>
	                            <td valign="top">
	                                <h1 class="paddingbottom15"><s:property value="resultProgram.program_name"/></h1>
	                                <div class="info">
	                                	<span>
	                                		<a href="university?universityId=<s:property value="resultProgram.university.id"/>" target="_blank">
	                                			<s:property value="resultProgram.university.university_name"/>
	                                		</a>
	                                	</span>
	                                </div>
	                                <div class="info">
	                                    <span>
	                                    	<s:property value="resultProgram.university.country.name"/>
	                                    </span>
	                                	<span>
	                                		<s:if test="resultProgram.university.is_public_school==1">公立</s:if><s:else>私立</s:else>
	                                	</span>
	                                	&nbsp;&nbsp;
	                                	<s:property value="resultProgram.university.area.city"/>,<s:property value="resultProgram.university.area.state"/>
	                               	</div>
	                                <div class="info">
										<s:property value="programStatistic.total_browse"/>浏览&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="universityStatistic.total_evaluate"/>点评
	                                    <!-- <span class="star"></span>
	                                    <span class="star2"></span>
	                                    <span class="star2"></span> -->
	                                </div>
	                                <div>
	                                	<s:property value="resultProgram.time_of_enrollment"/>
	                                </div>
	                            </td>
	                            <td valign="top" class="text-center">
	                                <div class="ranking ftcolff6600">
	                                    <span>综合排名 <s:property value="resultProgram.university.ranking_comprehensive"/></span>  </div>
	                                <div class="text-center sc">
	                                    <img src="../plugin/new/images/sc2.png" onclick="common_collect('<s:property value="resultProgram.id"/>', '3')"/>&nbsp;&nbsp;
	                                    <a href="<s:property value="resultProgram.specialty_link"/>" target="_blank"><img src="../plugin/new/images/gwkc.png" /></a>
	                                </div>
                                	<s:if test="resultProgram.course_setting != null">
                                		<div>
								            <table cellpadding="0" cellspacing="0">
								            	<tr>
								            		<td valign="top">
								            			<s:if test="resultProgram.course_setting.contains('http://')">
								            				<a href="<s:property value="resultProgram.course_setting"/>" target="_blank">课程设置</a>
								            			</s:if>
								            		</td>
								            	</tr>
								            </table>
							            </div>
						            </s:if>
	                            </td>
	                        </tr>
	                    </table>
	                </li>
	            </ul>
	        </div>
	    </div>
	    <div class="reslut-2">
	    	<div class="title-2">
	            <ul class="selres">
	            	<li class="current">项目简介</li>
	            	<li>录取要求</li>
	            	<s:if test="resultProgram.student_profile != null">
	            		<li>录取统计</li>
	            	</s:if>
	            	
	            </ul>
	            <div class="clear">
	            </div>
	        </div>
	        <div class="content">
	        	<table class="table-3" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" class="width-150">
		                   <h2 class="icon-3">学制</h2>
		                </td> 
						<td valign="top">
							<s:property value="resultProgram.teach_way"/><br>
		            		<s:property value="resultProgram.length_of_schooling"/><br>
	      					<s:property value="resultProgram.length_of_schooling_desc" escape="false"/>
						</td>
					</tr>
					<tr>
						<td valign="top" class="width-150">
		                    <h2 class="icon-4">学费</h2>
		                </td>
						<td valign="top">
                            $<s:property value="resultProgram.tuition"/>
                            <s:if test="resultProgram.tuition_top==0 || resultProgram.tuition_top==null"></s:if>
                            <s:else>--$
                                <s:property value="resultProgram.tuition_top"/>
                            </s:else>
                            <br/>(Total Program，只包括整个项目所需学费，不含生活费)
                        </td>
					</tr>
		            <tr>
		                <td valign="top" class="width-150">
		                	<h2 class="icon-6">联系方式</h2>
		                </td>
						<td valign="top">
		            		<s:property value="resultProgram.address" escapeHtml="0"/><br/>
	      					<s:property value="resultProgram.phone"/><br/>
	      					<s:property value="resultProgram.email"/>
						</td>
		            </tr>
	            </table>
	        	<table class="table-3" cellpadding="0" cellspacing="0" style="display:none">
					<tr>
						<td valign="top" class="width-150">
		                   <h2 class="icon-7">截止日期</h2>
		                </td> 
						<td valign="top">
							<s:if test="resultProgram.time_of_spring_end != ''">
								春季入学：<s:property value="resultProgram.time_of_spring_end"/><br/>
							</s:if>
							<s:if test="resultProgram.time_of_summer_end != ''">
								夏季入学：<s:property value="resultProgram.time_of_summer_end"/><br />
							</s:if>
							<s:if test="resultProgram.time_of_autumn_end != ''">
								秋季入学：<s:property value="resultProgram.time_of_autumn_end"/><br />
							</s:if>
							<s:if test="resultProgram.time_of_winter_end != ''">
								冬季入学：<s:property value="resultProgram.time_of_winter_end"/>
							</s:if>
						</td>
					</tr>
					<tr>
						<td class="width-150">
		                	<h2 class="icon-10">专业背景</h2>
		                </td>
						<td valign="top">
							<s:if test="resultProgram.specialty_require==1">
								<s:property value="resultProgram.specialty_require_desc" escape="false"/>
							</s:if>
	   						<s:else>无专业背景要求
								<br/><s:property value="resultProgram.specialty_require_desc" escape="false"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td class="width-150">
		                <h2 class="icon-9">工作经验</h2>
		                </td>
						<td valign="top">
							<s:if test="resultProgram.work_experience_require==1">
								<s:property value="resultProgram.work_experience_desc" escape="false"/>
							</s:if>
	   						<s:else>无工作经验要求
								<br/><s:property value="resultProgram.work_experience_desc" escape="false"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td valign="top" class="width-150">
		                    <h2 class="icon-8">成绩要求</h2>
		                </td>
						<td valign="top">
							<table>
								<tr>
									<th>GPA</th>
									<td>
										<s:if test="resultProgram.score_gpa==0">无最低要求</s:if>
                                            <s:else>
                                                <s:property value="resultProgram.score_totef"/>
                                            </s:else>
                                            <s:if test="resultProgram.gpa_desc != null && resultProgram.gpa_desc != ''">
                                                <br/><s:property value="resultProgram.gpa_desc" escape="false"/>
                                            </s:if>
                                            <br><s:if test="resultProgram.lsat != null && resultProgram.lsat != ''">
                                            <s:property value="resultProgram.lsat"/>
                                        </s:if>
									</td>
								</tr>
								<tr>
									<th>托福</th>
									<td>
                                    	<s:if test="resultProgram.score_totef==0">托福总分：无最低要求</s:if>
                                            <s:elseif test="resultProgram.score_totef==-1">不接受托福成绩</s:elseif>
                                            <s:elseif test="resultProgram.score_totef==-2">无需提供托福成绩</s:elseif>
                                            <s:else>托福总分：<s:property value="resultProgram.score_totef"/></s:else>
                                        <s:if test="resultProgram.totef_single != null && resultProgram.totef_single != '' && resultProgram.score_totef!=-1">
                                            <br><s:if test="resultProgram.totef_single == 0">
                                                托福单项：无最低要求
                                            </s:if>
                                            <s:else>
                                                托福单项：<s:property value="resultProgram.totef_single" escape="false"/>
                                            </s:else>
                                        </s:if>
										<s:if test="resultProgram.totef_desc != null && resultProgram.totef_desc != ''">
											<br><s:property value="resultProgram.totef_desc" escape="false"/>
										</s:if>
									</td>
								</tr>
								<tr>
									<th>雅思</th>
									<td>
                                        <br><s:if test="resultProgram.score_ietls==0">雅思总分：无最低要求</s:if>
                                            <s:elseif test="resultProgram.score_ietls==-1">不接受雅思成绩</s:elseif>
                                            <s:elseif test="resultProgram.score_itels==-2">无需提供雅思成绩</s:elseif>
                                            <s:else>雅思总分：<s:property value="resultProgram.score_ietls"/></s:else>
                                        <s:if test="resultProgram.ietls_single != null && resultProgram.ietls_single != '' && resultProgram.score_ietls != -1">
                                            <br><s:if test="resultProgram.ietls_single == 0">
                                                雅思单项：无最低要求
                                            </s:if>
                                            <s:else>
                                                雅思单项：<s:property value="resultProgram.ietls_single" escape="false"/>
                                            </s:else>
                                        </s:if>
										<s:if test="resultProgram.ietls_desc != null && resultProgram.ietls_desc != ''">
											<br><s:property value="resultProgram.ietls_desc"/>
										</s:if>
									</td>
								</tr>
								<tr>
									<th>有条件录取</th>
									<td>
										<s:if test="resultProgram.is_language_score == 1">
											提供有条件录取
                                        </s:if>
										<s:else>不提供有条件录取</s:else>
										<br/><s:property value="resultProgram.is_language_score_desc" escape="false"/>
									</td>
								</tr>
								<tr>
									<th>GRE</th>
									<td>
                                        <br><s:if test="resultProgram.score_gre==0">GRE总分：无最低要求</s:if>
                                            <s:elseif test="resultProgram.score_re==-1">不接受GRE成绩</s:elseif>
                                            <s:elseif test="resultProgram.score_gre==-2">无需提供GRE成绩</s:elseif>
                                            <s:else>GRE总分：<s:property value="resultProgram.score_gre"/></s:else>
                                        <s:if test="resultProgram.gre_single != null && resultProgram.gre_single != '' && resultProgram.score_gre != -1">
                                            <br><s:if test="resultProgram.gre_single == 0">
                                                GRE单项：无最低要求
                                            </s:if>
                                            <s:else>
                                                GRE单项：<s:property value="resultProgram.gre_single" escape="false"/>
                                            </s:else>
                                        </s:if>
										<s:if test="resultProgram.gre_desc != null && resultProgram.gre_desc != ''">
											<br><s:property value="resultProgram.gre_desc"/>
										</s:if>
									</td>
								</tr>
								<s:if test="resultProgram.gre_sub_desc != null && resultProgram.gre_sub_desc != ''">
								<tr>
									<th>GRE Sub</th>
									<td>
										<s:property value="resultProgram.gre_sub_desc" escape="false"/>
									</td>
								</tr>
								</s:if>
								<tr>
									<th>GMAT</th>
									<td>
										<s:if test="resultProgram.score_gmat==0">GMAT总分：无最低要求</s:if>
                                            <s:elseif test="resultProgram.score_gmat==-1">不接受GMAT成绩</s:elseif>
                                            <s:elseif test="resultProgram.score_gmat==-2">无需提供GMAT成绩</s:elseif>
                                            <s:else>GMAT总分：<s:property value="resultProgram.score_gmat"/></s:else>
                                        <s:if test="resultProgram.gmat_single != null && resultProgram.gmat_single != '' && resultProgram.score_gmat != -1">
                                            <br><s:if test="resultProgram.gmat_single == 0">
                                                GMAT单项：无最低要求
                                            </s:if>
                                            <s:else>
                                                GMAT单项：<s:property value="resultProgram.gmat_single" escape="false"/>
                                            </s:else>
                                        </s:if>
                                        <s:if test="resultProgram.gmat_desc != null && resultProgram.gmat_desc != ''">
                                            <br><s:property value="resultProgram.gmat_desc" escape="false"/>
                                        </s:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<s:if test="resultProgram.student_profile != null">
					<table class="table-3" cellpadding="0" cellspacing="0" style="display:none">
		        		<tr>
		        			<td valign="top">
		        				<s:property value="resultProgram.student_profile" escape="false"/>
		        			</td>
		        		</tr>
		        	</table>
	        	</s:if>
			</div>
		</div>
	    <div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
    
    
      	<!-- <div class="container-fluid well">
      		<div class="row">
      			<div class="col-md-2 col-md-offset-2">
      				<h4><strong>其他课程推荐</strong></h4>
      			</div>
      			<div class="col-md-8"></div>
      		</div>
      		<s:iterator value="similarPrograms" status="p">
				<a href="program?programId=${id}">
      			<div class="row">
						<div class="col-md-3 col-md-offset-2">
							<h2>计算机科学222</h2>
						</div>
						<div class="col-md-3">
		          		    <hgroup class="ft_tle"><span>500浏览量</span></hgroup>
						</div>
						<div class="col-md-4">
							<button class="btn btn-info"><i class="icon-white glyphicon glyphicon-heart"></i> 收藏</button>
						</div>
				</div>
				<div class="row">
						<div class="col-md-1 col-md-offset-2">
		          		    <span><s:property value="#studyLevel.name"/></span>
		          		</div>
						<div class="col-md-1">
		          		    <span><s:property value="#university.name"/></span>
		          		</div>
		          		<div class="col-md-1">
		          		    <span><s:property value="#length_of_schooling"/></span>
		          		</div>
		          		<div class="col-md-3">
		          			<span>$<s:property value="#tuition"/> Total Program</span>
						</div>
						<div class="col-md-4">
							<button class="btn btn-info"><i class="icon-white glyphicon glyphicon-ok"></i> 对比</button>
						</div>
				</div>
				</a>
      		</s:iterator>
      	</div> -->
      	
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
        </script>
      	
	</body>
</html>
