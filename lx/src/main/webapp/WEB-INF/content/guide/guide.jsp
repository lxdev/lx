<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title>攻略</title>
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
		
  	</head>
  	<body>
		<s:action namespace="/user" name="head" executeResult="true"/>
    
    	<div class="daohang"><a href="">首页</a><span>></span><a href="">院校库</a><span>></span><a href="../template/guides">${guideResult.country.name}</a></div>
    	<div class="clear"></div>
    	<div class="strategydetail">
        	<div class="title">
        		<h3 class=" ftsize18"><span class="ftcolfe8332">5300</span>人想申请</h3>
        		<span class=" ftsize33 ftcol4190bc paddingl20">${guideResult.country.name}</span>
        		<span class="ftcol999999 ftsize25 paddingl20">${guideResult.guide_name}</span>
        	</div>
    		<div class="reslut-2">
	    		<div class="title-2">
		            <ul class="selres">
		            	<li class="current">申请指南</li>
		            	<li><a href="../template/universitys">院校推荐</a></li>
		            	<li><a href="../template/programs">课程搜索</a></li>
		            	<li><a href="../template/consultants">留学顾问</a></li>
		            	<li><a href="../template/asks">专家问答</a></li>
		            </ul>
		            <div class="collect"><span onclick="common_collect('<s:property value="guideResult.id"/>', '4')">收藏</span></div>
		            <div class="clear">
		            </div>
		        </div>
	    		<div class="content">
					<div class="stradetailcon con-1">
			            <div class="l">
			            <ul id="myTab">
			            
			            	<s:iterator value="options" id="outer">
								<s:if test="#outer.parent_id==0">
									<li class="current">
                                        <a href="#content_${outer.id }">
                                            <h2 class="ranking">${outer.option_name }</h2>
                                        </a>
										<s:iterator value="options" id="inner">
											<s:if test="#inner.parent_id==#outer.id">
													<a href="#content_${outer.id}_${inner.id}">
														<div class="sublink">${inner.option_name }</div>
													</a>
											</s:if>
										</s:iterator>
									</li>
								</s:if>
							</s:iterator>
			            
				            <%--<li>
				            <h2 class="professintro">专业介绍</h2>
				            <div class="sublink">院系设置</div>
				            <div class="sublink">学位设置</div>
				            <div class="sublink">专业方向</div>
				            </li>
				            <li>
				            <h2 class="careerpros">就业前景</h2>
				            <div class="sublink">国内</div>
				            <div class="sublink">国外</div>
				            </li>
				            <li>
				            <h2 class="applicrequ">申请要求</h2>
				            <div class="sublink">平均成绩</div>
				            <div class="sublink">托福考试</div>
				            <div class="sublink">雅思考试</div>
				            <div class="sublink">GRE考试</div>
				            <div class="sublink">GMAT考试</div>
				            <div class="sublink">工作经验</div>
				            <div class="sublink">专业背景</div>
				            </li>
				            <li>
				            <h2 class="applicmaterials">申请材料</h2>
				            <div class="sublink">文书</div>
				            <div class="sublink">成绩单</div>
				            <div class="sublink">存款证明</div>
				            <div class="sublink">在读证明</div>
				            </li>
				            <li>
				            <h2 class="applicprocess">申请流程</h2>
				            <div class="sublink">截止日期</div>
				            <div class="sublink">填写网申</div>
				            <div class="sublink">邮寄材料</div>
				            <div class="sublink">套磁</div>
				            <div class="sublink">面试</div>
				            </li>--%>
			            </ul>
			            </div>
			            <div class="r">
			            
			            	<s:iterator value="options" id="outer2">
								<s:if test="#outer2.parent_id==0">
									<div class="option_item" id="content_${outer2.id }">
										<s:iterator value="options" id="inner2">
											<s:if test="#inner2.parent_id==#outer2.id">
												<s:iterator value="guideResult.optionContents" id="oc">
													<s:if test="#oc.option_id==#inner2.id">
														<div id="content_${outer2.id}_${inner2.id}">
															<span>${ inner2.option_name }</span>
															<s:if test="#oc.option_content != ''">
                                                                <p>${oc.option_content }</p>
															</s:if>
															<s:else>
																<p>暂无内容</p>
															</s:else>
														</div>
													</s:if>
												</s:iterator>
											</s:if>
										</s:iterator>
									</div>
								</s:if>
							</s:iterator>
			            </div>
					</div>
		            <table class="stradetailcon" cellpadding="0" cellspacing="0" style="display:none">
		                        
		            </table>   
		            <table class="stradetailcon" cellpadding="0" cellspacing="0" style="display:none">
		                        
		            </table>
		            <table class="stradetailcon" cellpadding="0" cellspacing="0" style="display:none">
		                        
		            </table>
	        	</div>
	        	<div class="clear"></div>
	    	</div>
    	</div>
    	
	    <div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
	    <script type="text/javascript">
	
	        $(".stradetailcon .l li").mouseenter(function () {
	            $(".stradetailcon .l li").removeClass("current");
	            $(this).addClass("current");
	        })
	        $("#myTab li").each(function (i) {
	            $(this).click(function () {
	                $("#myTab li").removeClass("current");
	                $("#myTab li .sublink").hide();
	                $(this).find(".sublink").show();
	                $(this).addClass("current");
	                $(".r .option_item").hide();
	                $(".r .option_item").eq(i).show();
	            });
	        });
		</script>
    
	</body>
</html>
