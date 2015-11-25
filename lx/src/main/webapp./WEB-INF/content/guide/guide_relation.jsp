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
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3" />
		<%----%>
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
  </head>
  <body>
		<s:action namespace="/user" name="head" executeResult="true"/>

      	<div class="daohang"><a href="">首页</a><span>></span><a href="">院校库</a><span>></span><a href="">${guideResult.country.name}</a></div>
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
		        
		        <div class="row">
					<div class="col-md-10">
						<span><h3>热门院校</h3></span>
						<ul class="nav nav-pills nav-stacked" style="max-width: 300px;" id="myTab">
							<s:iterator value="universityList" id="u">
								<a class="pr_rslt sr nrmtab" id="${u.id}" href="university?universityId=${u.id}">
									<div class="pr_hd">
										<div class="pr_lgo">
											<img src="<s:property value="logo_url"/>" class=" lazy-loaded">
											<h1>${u.university_name}/${u.english_name}</h1>
										</div>
										<div style="width:30%;float:left;margin-left: 50px;">
											<span>${u.country.name}</span>
											<span>浏览0</span>
											<span>评论0</span>
										</div>
										<div style="float:left;">
											<h3><span>综合排名 ${u.ranking_comprehensive}</span></h3>
										</div>
										<div style="float:right;">
											<button class="btn btn-info">收藏</button>
										</div>
									</div>
								</a>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<span><h3>热门课程</h3></span>
			      		<s:iterator value="programList" id="inner">
							<div class='pr_rslt sr nrmtab university_<s:property value="#inner.university_id"/>' style="display: none;">
							<a href="program?programId=<s:property value="#inner.university_id"/>">
								<div class="pr_hd" style="float:left;">
									<div class="pr_lgo" style="line-height: 50px !important;">
										<h2><s:property value="#inner.program_name"/></h2>
										<hgroup class="ft_tle"><span>0浏览量</span></hgroup>
									</div>
									<div>
										<span><s:property value="#inner.studyLevel.name"/></span>
										<span><s:property value="#inner.length_of_schooling"/></span>
										<span>$<s:property value="#inner.tuition"/> Total Program</span>
									</div>
								</div>
								<div style="float:right;">
									<a >收藏</a>
								</div>
							<a>
							</div>
						</s:iterator>
					</div>
				</div>
		        
		    </div>
		</div>
		
		        
		<%--<div class="container-fluid well">
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<h2><strong>${guideResult.country.name }</strong></h2>
				</div>
				<div class="col-md-2" style="margin-top:15px">
					<h4>${guideResult.specialty.specialty_name }${guideResult.guideSpecialty.specialty_name }</h4>
				</div>
				<div class="col-md-2" style="margin-top:25px">0人想申请</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<a href="../template/guide_detail?guide_id=${guide_id}">
						<h4>申请指南</h4>
					</a>
				</div>
				<div class="col-md-2">
					<a href="../template/universitys">
					<h4>院校推荐</h4>
					</a>
				</div>
				<div class="col-md-2">
					<a href="../template/programs">
					<h4>课程搜索</h4>
					</a>
				</div>
				<div class="col-md-2">
					<a href="../template/consultants">
					<h4>留学顾问</h4>
					</a>
				</div>
				<div class="col-md-2">
					<a href="../template/asks">
					<h4>专家问答</h4>
					</a>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-defaul">收藏</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10">
					<span><h3>热门院校</h3></span>
				<ul class="nav nav-pills nav-stacked" style="max-width: 300px;" id="myTab">
					<s:iterator value="universityList" id="u">
						<a class="pr_rslt sr nrmtab" id="${u.id}" href="university?universityId=${u.id}">
							<div class="pr_hd">
								<div class="pr_lgo">
									<img src="<s:property value="logo_url"/>" class=" lazy-loaded">
									<h1>${u.university_name}/${u.english_name}</h1>
								</div>
								<div style="width:30%;float:left;margin-left: 50px;">
									<span>${u.country.name}</span>
									<span>浏览0</span>
									<span>评论0</span>
								</div>
								<div style="float:left;">
									<h3><span>综合排名 ${u.ranking_comprehensive}</span></h3>
								</div>
								<div style="float:right;">
									<button class="btn btn-info">收藏</button>
								</div>
							</div>
						</a>
					</s:iterator>
				</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10">
					<span><h3>热门课程</h3></span>
		      		<s:iterator value="programList" id="inner">
						<div class='pr_rslt sr nrmtab university_<s:property value="#inner.university_id"/>' style="display: none;">
						<a href="program?programId=<s:property value="#inner.university_id"/>">
							<div class="pr_hd" style="float:left;">
								<div class="pr_lgo" style="line-height: 50px !important;">
									<h2><s:property value="#inner.program_name"/></h2>
									<hgroup class="ft_tle"><span>0浏览量</span></hgroup>
								</div>
								<div>
									<span><s:property value="#inner.studyLevel.name"/></span>
									<span><s:property value="#inner.length_of_schooling"/></span>
									<span>$<s:property value="#inner.tuition"/> Total Program</span>
								</div>
							</div>
							<div style="float:right;">
								<a >收藏</a>
							</div>
						<a>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>--%>
      
      	<div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
      
    </div>
</body>
</html>
