<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path;
	

				String actionUrl = basePath + "/manage/program_add";
				if (!"0".equals(request.getAttribute("program_id")) && !request.getAttribute("program_id").equals(0)) {
					actionUrl = basePath + "/manage/program_update";
				}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>新建Program</title>
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
		<head:head title="新建Program">
			
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
					<div class="container-fluid">
					<form id="addform" action="<%=actionUrl%>" method="post" enctype="multipart/form-data" class="form-horizontal">
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="program_name" class="control-label col-lg-4">名称:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.program_name" placeholder="请输入一个Program名称" id="program_name" value="${program.program_name}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="teach_way" class="col-lg-4 control-label">授课方式:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.teach_way" placeholder="请输入一个授课方式" id="teach_way" value="${program.teach_way}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="university_id" class="control-label col-lg-4">学校:</label>
								<div class="col-lg-8">
									<s:select list="%{universityList}" listKey="id" listValue="university_name" value="%{ program.university_id == null ? 0 : program.university_id }" headerKey="0" headerValue="--请选择--" theme="simple" name="program.university_id" id="university_id" cssClass="select form-control"/>
								</div>
							</div>
							<!-- <div class="form-group col-lg-4">
								<label for="specialty_id" class="col-lg-4 control-label">专业:</label>
								<div class="col-lg-8">
									<s:select list="%{specialtyList}" listKey="id" listValue="specialty_name" value="%{program.specialty == null ? 0 : program.specialty.id}" headerKey="0" headerValue="--请选择--" theme="simple" name="program.specialty.id" id="specialty_id" cssClass="select form-control"/>
								</div>
							</div> -->
							<div class="form-group col-lg-6">
								<label for="study_level_id" class="col-lg-4 control-label">学位等级:</label>
								<div class="col-lg-8">
									<s:select list="%{studyLevelList}" listKey="id" listValue="name" value="%{program.study_level_id}" headerKey="0" headerValue="--请选择--" theme="simple" name="program.study_level_id" id="study_level_id" cssClass="select form-control"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-4">
								<label for="length_of_schooling" class="control-label col-lg-4">学制:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.length_of_schooling" placeholder="请输入一个学制" id="length_of_schooling" value="${program.length_of_schooling}"/>
								</div>
							</div>
							<div class="form-group col-lg-8">
								<label for="length_of_schooling_desc" class="col-lg-4 control-label">学制描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.length_of_schooling_desc" placeholder="请输入一个学制描述" id="length_of_schooling_desc" value="${program.length_of_schooling_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="tuition" class="control-label col-lg-4">学费（最低）:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.tuition" placeholder="请输入" id="tuition" value="${program.tuition}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="tuition_top" class="col-lg-4 control-label">学费（最高）:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.tuition_top" placeholder="请输入" id="tuition_top" value="${program.tuition_top}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="scholarship_desc" class="col-lg-4 control-label">奖学金描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.scholarship_desc" placeholder="请输入" id="scholarship_desc" value="${program.scholarship_desc}"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="time_of_enrollment" class="control-label col-lg-4">入学时间:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.time_of_enrollment" placeholder="请输入入学时间" id="time_of_enrollment" value="${program.time_of_enrollment}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="address" class="col-lg-4 control-label">地址:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.address" placeholder="请输入地址" id="address" value="${program.address}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="phone" class="col-lg-4 control-label">电话:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.phone" placeholder="请输入电话" id="phone" value="${program.phone}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="email" class="col-lg-4 control-label">邮箱:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.email" placeholder="请输入邮箱" id="email" value="${program.email}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="course_setting" class="col-lg-4 control-label">课程设置:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.course_setting" placeholder="请输入一个课程设置" id="course_setting" value="${program.course_setting}">
								</div>
							</div>
							<div class="control-group col-lg-6">
								<label for="program_desc" class="col-lg-4 control-label">Program描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.program_desc" placeholder="请输入一个Program描述" id="program_desc" value="${program.program_desc}">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="score_totef" class="control-label col-lg-4">托福总分:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.score_totef" placeholder="请输入托福总分" id="score_totef" value="${program.score_totef}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="totef_single" class="col-lg-4 control-label">托福单项要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.totef_single" placeholder="请输入托福单项要求" id="totef_single" value="${program.totef_single}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="totef_desc" class="col-lg-4 control-label">托福要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.totef_desc" placeholder="请输入托福要求描述" id="totef_desc" value="${program.totef_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="score_ietls" class="control-label col-lg-4">雅思总分总分:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.score_ietls" placeholder="请输入雅思总分" id="score_ietls" value="${program.score_ietls}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="ietls_single" class="col-lg-4 control-label">雅思单项要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.ietls_single" placeholder="请输入雅思单项要求" id="ietls_single" value="${program.ietls_single}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="ietls_desc" class="col-lg-4 control-label">雅思要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.ietls_desc" placeholder="请输入雅思要求描述" id="ietls_desc" value="${program.ietls_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="score_gre" class="control-label col-lg-4">GRE总分:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.score_gre" placeholder="请输入GRE总分" id="score_gre" value="${program.score_gre}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="gre_single" class="col-lg-4 control-label">GRE单项要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gre_single" placeholder="请输入GRE单项要求" id="gre_single" value="${program.gre_single}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="gre_desc" class="col-lg-4 control-label">GRE要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gre_desc" placeholder="请输入GRE要求描述" id="gre_desc" value="${program.gre_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="score_gmat" class="control-label col-lg-4">GMAT总分:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.score_gmat" placeholder="请输入GMAT总分" id="score_gmat" value="${program.score_gmat}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="gmat_single" class="col-lg-4 control-label">GMAT单项要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gmat_single" placeholder="请输入GMAT单项要求" id="gmat_single" value="${program.gmat_single}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="gmat_desc" class="col-lg-4 control-label">GMAT要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gmat_desc" placeholder="请输入GMAT要求描述" id="gmat_desc" value="${program.gmat_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="score_gpa" class="control-label col-lg-4">GPA要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.score_gpa" placeholder="请输入GPA要求" id="score_gpa" value="${program.score_gpa}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="gpa_desc" class="col-lg-4 control-label">GPA要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gpa_desc" placeholder="请输入GPA要求描述" id="gpa_desc" value="${program.gpa_desc}"/>
								</div>
							</div>
							<div class="control-group col-lg-3">
								<label for="gre_sub" class="control-label col-lg-4">GRE SUB要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gre_sub" placeholder="请输入GRE SUB要求" id="gre_sub" value="${program.gre_sub}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="gre_sub_desc" class="col-lg-4 control-label">GRE SUB要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.gre_sub_desc" placeholder="请输入GRE SUB要求描述" id="gre_sub_desc" value="${program.gre_sub_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-4">
								<label for="gre_sub_desc" class="col-lg-4 control-label">是否提供语言课</label>
								<s:set var="mapIsLanguageScore" value="#{1:'提供',0:'不提供'}"></s:set>
								<s:iterator value="#mapIsLanguageScore">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="program.is_language_score==key || (program.is_language_score==null && key==0)">
											<input type="radio" name="program.is_language_score" value="<s:property value="key"/>" checked><s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="program.is_language_score" value="<s:property value="key"/>"> <s:property value="value"/>
										</s:else>
									</label>
								</s:iterator>
							</div>
							<div class="form-group col-lg-4">
								<label for="is_language_score_desc" class="col-lg-4 control-label">语言课程描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.is_language_score_desc" placeholder="请输入语言课程描述" id="is_language_score_desc" value="${program.is_language_score_desc}"/>
								</div>
							</div>
							<div class="control-group col-lg-4">
								<label for="last" class="control-label col-lg-4">LAST要求:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.last" placeholder="请输入LAST要求" id="last" value="${program.last}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="specialty_require" class="col-lg-4 control-label">是否专业背景要求</label>
								<s:set var="mapIsSpecialtyRequire" value="#{1:'要求',0:'不要求'}"></s:set>
								<s:iterator value="#mapIsSpecialtyRequire">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="program.specialty_require==key || (program.specialty_require==null && key==0)">
											<input type="radio" name="program.specialty_require" value="<s:property value="key"/>" checked><s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="program.specialty_require" value="<s:property value="key"/>"> <s:property value="value"/>
										</s:else>
									</label>
								</s:iterator>
							</div>
							<div class="form-group col-lg-3">
								<label for="specialty_require_desc" class="col-lg-4 control-label">专业要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.specialty_require_desc" placeholder="专业要求描述" id="specialty_require_desc" value="${program.specialty_require_desc}"/>
								</div>
							</div>
							<div class="control-group col-lg-3">
								<label for="work_experience_require" class="col-lg-4 control-label">工作经验要求</label>
								<s:set var="mapIsWorkExperienceRequire" value="#{1:'要求', 0:'不要求', -1:'preferred'}"></s:set>
								<s:iterator value="#mapIsWorkExperienceRequire">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="program.work_experience_require==key || (program.work_experience_require==null && key==0)">
											<input type="radio" name="program.work_experience_require" value="<s:property value="key"/>" checked><s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="program.work_experience_require" value="<s:property value="key"/>"> <s:property value="value"/>
										</s:else>
									</label>
								</s:iterator>
							</div>
							<div class="form-group col-lg-3">
								<label for="work_experience_desc" class="col-lg-4 control-label">工作经验要求描述:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.work_experience_desc" placeholder="工作经验要求描述" id="work_experience_desc" value="${program.work_experience_desc}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-3">
								<label for="time_of_autumn_end" class="control-label col-lg-4">秋季入学截止时间:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.time_of_autumn_end" placeholder="请输入秋季入学截止时间" id="time_of_autumn_end" value="${program.time_of_autumn_end}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="time_of_spring_end" class="col-lg-4 control-label">春季入学截止时间:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.time_of_spring_end" placeholder="请输入春季入学截止时间" id="time_of_spring_end" value="${program.time_of_spring_end}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="time_of_summer_end" class="col-lg-4 control-label">夏季入学截止时间:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.time_of_summer_end" placeholder="请输入夏季入学截止时间" id="time_of_summer_end" value="${program.time_of_summer_end}"/>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label for="time_of_winter_end" class="col-lg-4 control-label">冬季入学截止时间:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.time_of_winter_end" placeholder="请输入冬季入学截止时间" id="time_of_winter_end" value="${program.time_of_winter_end}"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="student_profile" class="control-label col-lg-4">秋季入往期录取统计:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.student_profile" placeholder="请输入往期录取统计" id="student_profile" value="${program.student_profile}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="specialty_link" class="col-lg-4 control-label">春季专业官网:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.specialty_link" placeholder="请输入专业官网" id="specialty_link" value="${program.specialty_link}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="apply_link" class="control-label col-lg-4">申请官网:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.apply_link" placeholder="请输入申请官网" id="apply_link" value="${program.apply_link}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="contact" class="col-lg-4 control-label">contact:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.contact" placeholder="请输入contact" id="contact" value="${program.contact}"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="control-group col-lg-6">
								<label for="enroll_require" class="control-label col-lg-4">enroll_require:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.enroll_require" placeholder="请输入enroll_require" id="enroll_require" value="${program.enroll_require}"/>
								</div>
							</div>
							<div class="form-group col-lg-6">
								<label for="official_websie" class="col-lg-4 control-label">official_websie:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="program.official_websie" placeholder="请输入official_websie" id="official_websie" value="${program.official_websie}"/>
								</div>
							</div>
						</div>
						
						
						<div class="control-group">
							<div class="offset2 span10">
						      	<input type="hidden" id="id" name="program.id" value="${program.id}"/>
								<button type="submit" class="btn btn-default" id="add_sub" onclick="return validateForm()">保 存</button>
						    </div>
						</div>
					</form>
					</div>
				</div>
				<!--Search End-->
			</body:body>	
		
		<script type="text/javascript">
			var validateForm = function(){
				
				return true;
			}
		</script>
  </body>
</html>
