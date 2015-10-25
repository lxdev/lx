<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String actionUrl = basePath + "/university/evaluate";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>院校点评</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
			<s:action namespace="/user" name="left" executeResult="true"/>
			<div class="templatemo-content-wrapper">
        		<div class="templatemo-content">
        			<a href="../university/evaluate_wizard" class="btn btn-default" role="button">写评论</a>
        			<div class="panel-body">
               			<table class="table table-striped">
               				<thead>
		                        <tr>
		                          <th>院校</th>
		                          <th>评价内容</th>
		                          <th>时间</th>
		                          <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<s:iterator value="evaluateList">
		                    		<tr>
			                    		<td><s:property value="title"/></td>
			                    		<td><s:property value="body"/></td>
			                    		<td><s:property value="created_date"/></td>
			                    		<td>
			                    			<a class="btn btn-default" role="button" href="../university/evaluate_wizard?id=<s:property value="id"/>">编辑</a>
			                    			<button type="button" class="btn btn-danger" data-id='<s:property value="id"/>' onclick="user_remove(this)">删除</button>
			                    		</td>
			                    	</tr>
		                    	</s:iterator>
		                    </tbody>
               			</table>
					</div>
        		</div>
        	</div>
		</div>
   		
   	</div>
  </body>
</html>
