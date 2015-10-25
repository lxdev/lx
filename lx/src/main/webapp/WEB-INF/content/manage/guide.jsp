<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>攻略列表</title>
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
		<head:head title="攻略列表">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table>
						<thead>
							<tr>
								<th>攻略名称</th>
								<th>国家</th>
								<th>专业类别</th>
								<th>专业名称</th>
								<th>是否自建专业</th>
								<th>操作</th>
							</tr>
						</thead>
						<s:iterator value="guides" var="o">
						<tr>
							<td>${o.guide_name}</td>
							<td>${o.country.name}</td>
							<td>
								<s:if test="#o.is_self_specialty==1">
									${o.guideSpecialty.parentSpecialty.specialty_name }
								</s:if><s:else>
									${o.specialty.parentSpecialty.specialty_name }
								</s:else>
							</td>
							<td>
								<s:if test="#o.is_self_specialty==1">
									${o.guideSpecialty.specialty_name }
								</s:if><s:else>
									${o.specialty.specialty_name }
								</s:else>
							</td>
							<td>
								<s:if test="%{#o.is_self_specialty==1}">自建</s:if>
								<s:else>系统</s:else>
							</td>
							<td><a href="<%=basePath%>/manage/guide_add?guide_id=${o.guide_id}">修改</a>&nbsp;<a href="<%=basePath%>/manage/guide_delete?guide_id=${o.guide_id}">删除</a></td>
						</tr>
						</s:iterator>
					</table>
					<br />
					<br />
					
					<a href="<%=basePath%>/manage/guide_add">新建攻略</a>
				</div>
				<!--Search End-->
			</body:body>	
  </body>
</html>
