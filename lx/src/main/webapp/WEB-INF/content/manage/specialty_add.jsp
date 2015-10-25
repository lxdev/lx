<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path;
	

				String actionUrl = basePath + "/manage/specialty_add";
				if (!"0".equals(request.getAttribute("specialty_id")) && !request.getAttribute("specialty_id").equals(0)) {
					actionUrl = basePath + "/manage/specialty_update";
				}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>新建专业</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<jc:plugin name="bootstrap3" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
  </head>
  <body>
   		<!--头部层开始 -->
		<head:head title="新建专业">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div class="container-fluid well">
					<div class="row">
		      			<div class="col-md-12">
							<s:actionmessage/>
		      			</div>
		      		</div>
					<div class="row">
		      			<div class="col-md-12">
							<h3>${message}</h3>
		      			</div>
		      		</div>
					<div class="row">
					<form id="addform" action="<%=actionUrl%>" method="post" enctype="multipart/form-data" class="form-horizontal">
						<div class="control-group">
							<label for="specialty.specialty_name" class="span2 control-label">专业名:</label>
							<div class="span8">
								<input type="text" class="form-control" name="specialty.specialty_name" placeholder="请输入一个专业名称" id="specialty_name" value="${specialty.specialty_name}">
							</div>
							<div class="span2">
								<div id="guide_nameTip" class="user_form_validator"></div>
							</div>
						</div>
						<div class="control-group">
							<label for="specialty.specialty_english_name" class="span2 control-label">专业英文名:</label>
							<div class="span8">
								<input type="text" class="form-control" name="specialty.specialty_english_name" placeholder="请输入一个专业英文名称" id="specialty_english_name" value="${specialty.specialty_english_name}">
							</div>
							<div class="span2">
								<div id="guide_nameTip" class="user_form_validator"></div>
							</div>
						</div>
						<div class="control-group">
							<label for="specialty.depth" class="span2 control-label">选择父专业:</label>
							<div class="span3">
								专业级别：
								<s:set var="mapSpecialtyDepth" value="#{0:'分类',1:'专业',2:'方向'}"></s:set>
								<s:iterator value="#mapSpecialtyDepth">
									<label>
										<input type="radio" name="specialty.depth" value="<s:property value="key"/>" <s:if test="specialty.depth==key">checked</s:if> onchange="showSpecialty('<s:property value="key"/>');"> <s:property value="value"/>
									</label>
								</s:iterator>
							</div>
							<div class="span3">
								<s:select list="%{rootSpecialtys}" listKey="id" listValue="specialty_name" value="%{ specialty.parentSpecialty == null ? 0 : specialty.parentSpecialty.id }" headerKey="0" headerValue="--请选择--" theme="simple" name="specialty.parent_id" id="rootSpecialty" cssClass="select form-control"/>
								<s:select list="%{parentSpecialtys}" listKey="id" listValue="specialty_name" value="%{ specialty.parentSpecialty == null ? 0 : specialty.parentSpecialty.id }" headerKey="0" headerValue="--请选择--" theme="simple" name="specialty.parent_id" id="parentSpecialty" cssClass="select form-control"/>
							</div>
						</div>
						<div class="control-group">
							<label for="specialty.specialty_attr" class="span2 control-label">专业关键字:</label>
							<div class="span8">
								<input type="text" class="form-control" name="specialty.specialty_attr" placeholder="请输入一个专业关键字" id="specialty.specialty_attr" value="${specialty.specialty_attr}">
							</div>
							<div class="span2">
								<div id="guide_nameTip" class="user_form_validator"></div>
							</div>
						</div>
						<div class="control-group">
							<label for="specialty.specialty_desc" class="span2 control-label">专业描述:</label>
							<div class="span8">
								<input type="text" class="form-control" name="specialty.specialty_desc" placeholder="请输入一个专业描述" id="specialty.specialty_desc" value="${specialty.specialty_desc}">
							</div>
							<div class="span2">
								<div id="guide_nameTip" class="user_form_validator"></div>
							</div>
						</div>
						<div class="control-group">
							<div class="offset2 span10">
						      	<input type="hidden" id="specialty.id" name="specialty.id" value="${specialty.id}"/>
								<button type="submit" class="btn btn-default" id="add_sub" onclick="return validateForm()">保 存</button>
						    </div>
						</div>
					</form>
					</div>
				</div>
				<!--Search End-->
			</body:body>	
		
		<script type="text/javascript">
			var default_option = "<option value='0'>---请选择专业---</option>";
			$(document).ready(function(){
				if("<s:property value='specialty.depth'/>" == "1"){
					showSpecialty('1')
				}else if("<s:property value='specialty.depth'/>" == "2"){
					showSpecialty('2')
				}else
					showSpecialty('0');
			});
		
			function showSpecialty(depth){
				if(depth == '0'){
					$("#rootSpecialty").hide();
					$("#parentSpecialty").hide();
				}else if(depth == '1'){
					$("#rootSpecialty").show();
					$("#parentSpecialty").hide();
				}else if(depth == '2'){
					$("#rootSpecialty").hide();
					$("#parentSpecialty").show();
				}
			}
			
			var validateForm = function(){
				if($.trim($("#specialty_name").val()) == ""){
					alert("专业名不能为空！");
					return false;
				}
				if($("#specialty_english_name").val() == ""){
					alert("专业英文名不能为空！");
					return false;
				}
				if($("#rootSpecialty").css("display") == "none"){
					$("#rootSpecialty").remove();
				}
				if($("#parentSpecialty").css("display") == "none"){
					$("#parentSpecialty").remove();
				}
				
				return true;
			}
		</script>
  </body>
</html>
