<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path;
	

				String actionUrl = basePath + "/manage/guide_add";
				if (!"0".equals(request.getAttribute("guide_id")) && !request.getAttribute("guide_id").equals(0)) {
					actionUrl = basePath + "/manage/guide_update";
				}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>新建攻略</title>
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
		<script type="text/javascript">
			var default_option = "<option value='0'>---请选择专业---</option>";
			var default_direction = "<option value='0'>---请选择专业方向---</option>";
			$(document).ready(function(){
				showSpecialty();
				if("<s:property value='guide.specialty.id'/>" != "")
					reloadSpecialty(1, "<s:property value='guide.specialty.id'/>");
				else if("<s:property value='guide.guideSpecialty.id'/>" != ""){
					if("<s:property value='guide.guideSpecialty.depth'/>" == "2")
						reloadSpecialty(2, "<s:property value='guide.guideSpecialty.parent_id'/>");
					reloadSpecialty(1, "<s:property value='guide.guideSpecialty.id'/>");
				}
				else
					reloadSpecialty(1);
			});
		
			//岗位
			function reloadSpecialty(depth, default_id)
			{
				var parent_id = jQuery('#parentSpecialty_id').val();
				parent_id = depth == 2 ? jQuery('#specialty_id').val() : parent_id;
				var url = '<s:url value="/manage/list_specialty"/>';
				var option = {'specialty.country_id':jQuery('#country_id').val(),'specialty.parent_id':parent_id};
				if($("input[name='is_self_specialty']:checked").val() == "1"){
					url = '<s:url value="/manage/list_guide_specialty"/>';
					parent_id = jQuery('#parentGuideSpecialty_id').val();
					option = {'guideSpecialty.parent_id':parent_id, 'guideSpecialty.depth':depth};
				}
				if(parent_id == 0)
					return;
			
				jQuery.post(url,option,
				        function(data)
				    	{
				    		var lists="";
				    		lists+=default_option;
				    		if(null!=data.list)
				    		jQuery.each(data.list, function()
			    			{		    			
	     		    			 if(default_id==this.id)
	     		    			 	lists+="<option value='"+this.id+"' selected>"+this.specialty_name+"</option>";
	     		    			 else
	     		    			 	lists+="<option value='"+this.id+"'>"+this.specialty_name+"</option>";
			    			});
			    			if(depth == 1)
			    				jQuery('#specialty_id').html(lists);
			    			else if(depth == 2)
			    				jQuery('#direction_specialty_id').html(lists);
				    	},
				 "json");
			}
		
			function showSpecialty(){
				if($("input[name='is_self_specialty']:checked").val() == "0"){
					$("#parentSpecialty_id").show();
					$("#parentGuideSpecialty_id").hide();
				}else{
					$("#parentSpecialty_id").hide();
					$("#parentGuideSpecialty_id").show();
				}
				$("#specialty_id").html(default_option);
				$("#direction_specialty_id").html(default_direction);
			}
			
			var validateGuideForm = function(){
				if($.trim($("#guide_name").val()) == ""){
					alert("攻略名不能为空！");
					return false;
				}
				if($("#country_id").val() == "0"){
					alert("请选一个国家！");
					return false;
				}
				if($("#specialty_id").val() == "0"){
					alert("请选一个专业！");
					return false;
				}
				
				
				
				return true;
			}
		</script>
		
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="新建攻略">
			
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
							<label for="guide_name" class="span2 control-label">攻略名:</label>
							<div class="span8">
								<input type="text" class="form-control" name="guide_name" placeholder="请输入一个攻略名称" id="guide_name" value="${guide.guide_name}">
							</div>
							<div class="span2">
								<div id="guide_nameTip" class="user_form_validator"></div>
							</div>
						</div>
						<div class="control-group">
							<label for="country_id" class="span2 control-label">国家:</label>
							<div class="span8">
								<s:select list="%{countrys}" listKey="id" listValue="name" value="%{ guide.country_id == null ? 0 : guide.country_id }" theme="simple" name="country_id" id="country_id" cssClass="select form-control" onchange="showSpecialty();"/>
							</div>
							<div class="span2"></div>
						</div>
						<div class="control-group">
							<label for="is_self_specialty" class="span2 control-label">选择所属专业:</label>
							<div class="span3">
								使用自定义专业：
								<s:set var="mapIsSelfSpecialty" value="#{0:'否',1:'是'}"></s:set>
								<s:iterator value="#mapIsSelfSpecialty">
									<label>
										<s:if test="guide.is_self_specialty==key">
											<input type="radio" name="is_self_specialty" value="<s:property value="key"/>" checked onchange="showSpecialty();"> <s:property value="value"/>
										</s:if><s:else>
											<input type="radio" name="is_self_specialty" value="<s:property value="key"/>" onchange="showSpecialty();"> <s:property value="value"/>
										</s:else>
									</label>
								</s:iterator>
							</div>
							<div class="span3">
								<s:select list="%{parentSpecialtys}" listKey="id" listValue="specialty_name" value="%{ guide.Specialty == null ? 0 : guide.Specialty.parent_id }" headerKey="0" headerValue="--请选择--" theme="simple" name="parentSpecialty_id" id="parentSpecialty_id" cssClass="select form-control" onchange='reloadSpecialty(1)'/>
								<s:select list="%{parentGuideSpecialtys}" listKey="id" listValue="specialty_name" value="%{ guide.GuideSpecialty == null ? 0 : guide.GuideSpecialty.parent_id }" headerKey="0" headerValue="--请选择--" theme="simple" name="parentGuideSpecialty_id" id="parentGuideSpecialty_id" cssClass="select form-control" onchange='reloadSpecialty(1)'/>
							</div>
							<div class="span2">
								<select id="specialty_id" name="specialty_id" class="form-control" onchange='reloadSpecialty(2)'>
									<option value="0">---请选择专业---</option>
								</select>
							</div>
							<div class="span2">
								<select id="direction_specialty_id" name="direction_specialty_id" class="form-control">
									<option value="0">---请选择专业方向---</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="span2 control-label">填入选项内容:</label>
							<div class="span10">
								<s:iterator value="guideOptions" id="outer">
									<s:if test="parent_id==0">
										<div class="control-group">
											<label class="span2 control-label"><s:property value="option_name"/></label>
											<div class="span7">
												<s:iterator value="guideOptions" id="inner">
													<s:if test="#inner.parent_id==#outer.id">
														<div class="control-group">
															<label class="span3 control-label">${inner.option_name}</label>
															<div class="span4">
																<textarea rows="4" style="height: auto !important;width:100%" id="option.${inner.id }" name="option.${inner.id }"><s:iterator value="guide.optionContents" id="innerContent"><s:if test="#innerContent.option_id==#inner.id">${innerContent.option_content}</s:if></s:iterator></textarea>
															</div>
														</div>
													</s:if>
												</s:iterator>
											</div>
										</div>
									</s:if>
								</s:iterator>
							</div>
						</div>
						<div class="control-group">
							<div class="offset2 span10">
						      	<input type="hidden" id="guide_id" name="guide_id" value="${guide_id}"/>
								<button type="submit" class="btn btn-default" id="add_sub" onclick="return validateGuideForm()">保 存</button>
						    </div>
						</div>
					</form>
					</div>
				</div>
				<!--Search End-->
			</body:body>	
  </body>
</html>
