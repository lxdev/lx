<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>系统专业列表</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<jc:plugin name="bootstrap3" />
		<script type="text/javascript">
			
		</script>
		<style type="text/css">
			table {
				border-collapse: collapse;
				border: none;
			}
			th, td {
				border: 1px solid #999;width: 200px;
			}
		</style>
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="专业列表">
		
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table>
						<thead>
							<tr>
								<th>专业名称</th>
								<th>专业属性</th>
								<th>专业中文名</th>
								<th>父专业名称</th>
								<th>专业描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<s:iterator value="specialtys" var="o">
						<tr>
							<td>${o.specialty_english_name}</td>
							<td>${o.specialty_attr}</td>
							<td>${o.specialty_name}</td>
							<td>${o.parentSpecialty.specialty_name}</td>
							<td>${o.specialty_desc}</td>
							<td>
								<a href="<%=basePath%>/manage/specialty_add?specialty_id=${o.id}">修改</a>&nbsp;
								<a href="javascript:return false;" onclick="checkDelete(${o.id})">删除</a>
							</td>
						</tr>
						</s:iterator>
					</table>
					<br />
					<br />
					
					<a href="<%=basePath%>/manage/specialty_add">新建专业</a>
<%
if (request.getParameter("message") != null) {
%>
<script type="text/javascript">
$.alert({ msg:"${message}"});
</script>
<%
}
%>
				</div>
				<!--Search End-->
			</body:body>
		<script type="text/javascript">
			var checkDelete = function(id){
				var btn = "\"<a href='<%=basePath%>/manage/specialty_delete?specialty_id=" + id + "'>确定</a>\"";
				var isdel=$.confirm({msg:"您确定要删除吗？", confirmText:"确定", confirm: function(){
					window.location.href = "<%=basePath%>/manage/specialty_delete?specialty_id=" + id;
				}});
			}
		</script>
  </body>
</html>
