<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String actionUrl = basePath + "/template/evaluate_detail";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>点评详情</title>
	<jc:plugin name="jquery_new"/>
	<jc:plugin name="jquery_ui"/>
	<jc:plugin name="bootstrap3"/>
	<jc:plugin name="bootstrap_main"/>
  </head>
  
  <body>
    <div class="container-fluid">
    	<s:action namespace="/user" name="head" executeResult="true"/>
   	</div>
   	<div class="container-fluid">
   		<div class="template-page-wrapper">
			<div class="templatemo-content-wrapper">
        		<div class="templatemo-content">
        			<div class="row">
        				<div class="title">
        					<s:property value="userExtend.university.university_name"/>
        				</div>
        			</div>
        			<div class="row">
        				<div class="col-md-4">
        					<div class="aw-item active">
								<img class="photo" src="<s:property value="entity.evaluate_from_user.photo_url"/>" alt=""/>
								<p>
									<a href="../template/user_home?id=<s:property value="entity.evaluate_from_user.user_id"/>" class="aw-user-name"><s:property value="entity.evaluate_from_user.full_name"/></a>
								</p>
								<span>学校：<s:property value="userExtend.university.university_name"/></span><br/>
								<span>专业：<s:property value="userExtend.specialty.specialty_name"/></span><br/>
								<span>学位：<s:property value="userExtend.study_level.name"/></span><br/>
								<span>时间：<s:property value="userExtend.graduate_date"/></span><br/>
								<br/>
								<span>教学质量：<s:property value="entity.evaluateExtendUniversity.option_quality"/>分</span><br/>
								<span>教授水平：<s:property value="entity.evaluateExtendUniversity.option_professor"/>分</span><br/>
								<span>当地声望：<s:property value="entity.evaluateExtendUniversity.option_reputation"/>分</span><br/>
								<span>校园环境：<s:property value="entity.evaluateExtendUniversity.option_environment"/>分</span><br/>
								<span>课余生活：<s:property value="entity.evaluateExtendUniversity.option_outclass"/>分</span><br/>
								<span>治安状况：<s:property value="entity.evaluateExtendUniversity.option_security"/>分</span><br/>
								<span>就业情况：<s:property value="entity.evaluateExtendUniversity.option_job"/>分</span><br/>
								<span>性价比：<s:property value="entity.evaluateExtendUniversity.option_cost"/>分</span>
							</div>
        				</div>
        				<div class="col-md-8">
        					<div class="row">
        						<span><s:property value="entity.evaluate_content"/></span>
        					</div>
        					<div class="row">
        						<div class="col-md-2">
        							<input type="button" class="btn btn-default" value="有用"/>
        						</div>
        						<div class="col-md-8">
        							<span>200有用</span>
        							<span>300看过</span>
        						</div>
        						<div class="col-md-2">
        							<input type="button" class="btn btn-default" value="评论(2)"/>
        						</div>
        					</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>			
  	</div>
  </body>
</html>
