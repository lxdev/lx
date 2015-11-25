<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>自定义专业列表</title>
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
		<head:head title="自定义专业列表">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table>
						<thead>
							<tr>
								<th>专业名称</th>
								<th>父选项</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<s:iterator value="specialtys" var="o">
						<tr>
							<td>${o.specialty_name}</td>
							<td>
								<s:if test="#{o.parentSpecialty!=null}">无</s:if>
								<s:else>${o.parentSpecialty.specialty_name}</s:else>
							</td>
							<td>${o.created_date}</td>
							<td><a href="<%=basePath%>/manage/guide_specialty_add?id=${o.id}">修改</a>&nbsp;<a href="<%=basePath%>/manage/guide_specialty_delete?id=${o.id}">删除</a></td>
						</tr>
						</s:iterator>
					</table>
					<br />
					<br />
					
					<a href="<%=basePath%>/manage/guide_specialty_add">创建选项类别</a>
				</div>
				<!--Search End-->
			</body:body>	
  </body>
</html>
