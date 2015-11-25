<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>新增自定义专业</title>
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
		
		$(document).ready(function(){
				//init_result_dialog();
				reloadparent();
				//reloadjob();
				
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"add_sub",debug:false,submitOnce:true,
        			onSuccess:function()
        			{	
        				jQuery('#addform').submit();
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);return false;}
        		});
				$("#specialty_name").formValidator({onShow:"请输入选项名",onFocus:"选项名至少1个字符,最多20个字符",onCorrect:""}).inputValidator({min:1,max:20,onError:"你输入的选项名错误,请确认"});
			});
		
			//部门
			function reloadparent()
			{
				jQuery.post('<s:url value="/orgstructure/department/list_department"/>',{'department.officeId':jQuery('#orgId').val()},
				        function(data)
				    	{
				    		var lists="";
				    		//lists+='<option value="0">---请选择---</option>';
				    		if(null!=data.list)
				    		jQuery.each(data.list, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
			    			});
			    			jQuery('#departmentId').html(lists);
			    			reloadjob();
				    	},
				 "json");	
			}
			
			//岗位
			function reloadjob()
			{
				jQuery.post('<s:url value="/orgstructure/job/list_job"/>',{'job.departmentId':jQuery('#departmentId').val()},
				        function(data)
				    	{
				    		var lists="";
				    		//lists+='<option value="0">---请选择---</option>';
				    		if(null!=data.list)
				    		jQuery.each(data.list, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
			    			});
			    			jQuery('#jobId').html(lists);
				    	},
				 "json");	
			}
		
		</script>
		
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="新增用户">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<%
				String actionUrl = basePath + "/manage/guide_specialty_add";
				if (!"0".equals(request.getAttribute("id")) && !request.getAttribute("id").equals(0)) {
					actionUrl = basePath + "/manage/guide_specialty_update";
				}
			%>
				<!--Search Begin-->
				<div>
					<s:actionmessage/>
					<h3>${message}</h3>
					<form id="addform" action="<%=actionUrl%>" method="post" enctype="multipart/form-data" >
					<table width="100%" class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">选项名:</td>
							<td><input type="text" class="txt_box_150" name="specialty_name" id="specialty_name" value="${specialty.specialty_name}"/></td>
							<td><div id="userNameTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">父选项:</td>
							<td>
								<s:select list="%{parentList}" listKey="id" listValue="specialty_name" value="%{ specialty.parentSpecialty == null ? 0 : specialty.parentSpecialty.id }" disabled="%{ (specialty.parentSpecialty == null && id != 0) ? true : false }" headerKey="0" headerValue="--请选择--" theme="simple" name="parent_id" id="parent_id" cssClass="select"/>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="hidden" id="id" name="id" value="${ id }"/>
								<input type="submit" value="保存" id="add_sub" class="btn_black_61" />
								
							</td>
						</tr>
					</table>
					</form>
				</div>
				<!--Search End-->
			</body:body>	
  </body>
</html>
