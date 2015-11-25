<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
    <title>攻略</title>
	<jc:plugin name="jquery_new"/>
	<jc:plugin name="jquery_ui"/>
	<jc:plugin name="bootstrap3" />
	<style>
		.nav { float:left !important; }
	</style>
	<script> 
      $(function () { 
        $('#myTab a:first').tab('show');//初始化显示哪个tab 
      
        $('#myTab a').click(function (e) { 
          e.preventDefault();//阻止a链接的跳转行为 
          $(this).tab('show');//显示当前选中的链接及关联的content 
        }) 
      }) 
    </script>
  </head>
  <body>
    <div id="base" class="">

      	<div class="container-fluid">
      		<s:action namespace="/user" name="head" executeResult="true"/>
      	</div>
      	
		<div class="container-fluid well">
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<h2><strong>${guideResult.country.name }</strong></h2>
				</div>
				<div class="col-md-2" style="margin-top:15px">
					<h4>${guideResult.guide_name }</h4>
				</div>
				<div class="col-md-2" style="margin-top:25px">0人想申请</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<h4>申请指南</h4>
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
			</div>
			<div class="row">
				<div class="col-md-10">
				<ul class="nav nav-pills nav-stacked" style="max-width: 300px;" id="myTab">
					<s:iterator value="options" id="outer">
						<s:if test="#outer.parent_id==0">
							<li role="presentation">
							<a href="#content_${outer.id }">
								<h3>${outer.option_name }</h3><br/>
								<s:iterator value="options" id="inner">
									<s:if test="#inner.parent_id==#outer.id">
										<h4>${inner.option_name }</h4>
									</s:if>
								</s:iterator>
							</a>
							</li>
						</s:if>
					</s:iterator>
				</ul>
				<div class="tab-content">
					<s:iterator value="options" id="outer2">
						<s:if test="#outer2.parent_id==0">
							<div class="tab-pane" id="content_${outer2.id }">
								<s:iterator value="options" id="inner2">
									<s:if test="#inner2.parent_id==#outer2.id">
										<s:iterator value="guideResult.optionContents" id="oc">
											<s:if test="#oc.option_id==#inner2.id">
												<pre>${oc.option_content }</pre>
											</s:if>
										</s:iterator>
									</s:if>
								</s:iterator>
							</div>
						</s:if>
					</s:iterator>
				</div>
				</div>
			</div>
		</div>
      
    </div>
</body>
</html>
