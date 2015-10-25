<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../template/common/import.jsp" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path;
	String actionUrl = basePath + "/manage/program";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>Program列表</title>
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
		<!--  分页 -->
		<jc:plugin name="page" />
	
		<jc:plugin name="loading" />
	
		<jc:plugin name="ajax_upload" />
	
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
		<head:head title="Program列表">
			<html:a text="新建Program" href="manage/program_add" icon="add" />
			<html:a text="导入Program" il8nName="crm" onclick="createProgramImportRecord();" icon="add" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div class="container-fluid">
				<form id="addform" action="<%=actionUrl%>" method="post" enctype="multipart/form-data" class="form-control form-horizontal" style="height:auto !important;">
					<div class="row">
						<div class="form-group col-lg-6">
							<label for="countryId" class="col-lg-2 control-label">国家:</label>
							<div class="col-lg-4">
								<s:select list="%{countryList}" listKey="id" listValue="name" theme="simple" name="program.countryId" id="countryId" cssClass="select"/>
							</div>
						</div>
						<div class="form-group col-lg-6">
							<label for="studyLevelId" class="col-lg-2 control-label">学位:</label>
							<div class="col-lg-4">
								<s:select list="%{studyLevelList}" listKey="id" listValue="name" theme="simple" name="program.study_level_id" id="studyLevelId" cssClass="select"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-6">
							<label for="program_name" class="col-sm-2 col-lg-2 control-label">名称:</label>
							<div class="col-sm-4 col-lg-4">
								<input type="text" class="txt_box_150" name="program.program_name" id="program_name" value="${program.program_name}"/>
							</div>
						</div>
						
						<input type="hidden" id="specialty_id" name="program.specialtyId" value="0"/>
						<input type="hidden" id="page_size" name="program.page_size" value="20"/>
						<input type="hidden" id="page" name="program.page" value="${program.page}"/>
						<input type="hidden" id="total_record" name="total_record" value="${total_record}"/>
						
						<div class="form-group col-lg-6">
							<div class="col-sm-6 col-lg-6">
								<input type="submit" id="form_search" value="查询" class="btn_black_61" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-2">
							<input type="button" onclick="go_to(0)" value="首页"/>
						</div>
						<div class="form-group col-lg-2">
							<input type="button" onclick="go_to(-1)" value="上一页"/>
						</div>
						<div class="form-group col-lg-2">
							<input type="text" id="go_x" value="" class="txt_box_50"/>
							<input type="button" onclick="go_to('x')" value="去"/>
						</div>
						<div class="form-group col-lg-2">
							<input type="button" onclick="go_to(+1)" value="下一页"/>
						</div>
						<div class="form-group col-lg-2">
							<input type="button" onclick="go_to('e')" value="末页"/>
						</div>
						<div class="form-group col-lg-2">
							共${total_record}条记录，当前第${program.page}页
						</div>
					</div>
				</form>
			</div>
				<!--Search Begin-->
				<div class="container-fluid">
					<table class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>名称</th>
								<th>院校</th>
								<th>时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<s:iterator value="programs" var="o">
						<tr>
							<td>${o.id}</td>
							<td>${o.program_name}</td>
							<td>${o.university.university_name}</td>
							<td></td>
							<td>
								<a href="<%=basePath%>/manage/program_add?program_id=${o.id}">修改</a>&nbsp;
								<a href="javascript:return false;" onclick="checkDelete(${o.id})">删除</a>
							</td>
						</tr>
						</s:iterator>
					</table>
					<br />
					<br />
					
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
				<div id="create" style="display: none">
					<form id="create_programImportRecord_form">

						<table class="add_table" border="0" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right">
									<span>*</span>上传文件：
								</td>
								<td width="220px">
									<input class="txt_box_200" type="file" name="file" id="file" />
	
								</td>
								<td align="left">
									<a
										href="<ua:attachment url="/upload/program_import_template.xls" />">下载模板</a>
									<span>注:请上传.xls文件(不超过10M).</span>
								</td>
	
							</tr>
						</table>
					</form>
				</div>
				<div id="view" style="display: none">
					<input type="hidden" id="idI" name="studentImportRecord.id" value="" />
					<table class="add_table" border=0>
						<tr>
							<td class="lable_100" align="right">
								名称：
							</td>
							<td >
								<font id="titleI"></font>
							</td>
							<td align="right" class="lable_100">
								数据来源：
							</td>
							<td>
								<font id="sourceIdI"></font>
							</td>
						</tr>
						<tr>
							<td align="right" >
								导入成功条数：
							</td>
							<td>
								<font id="successCountI"></font>
							</td>
							<td align="right">
								导入失败条数：
							</td>
							<td>
								<font id="errorCountI"></font>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								市场途径：
							</td>
							<td>
								<font id="enrollmentWayIdI"></font>
							</td>
							<td align="right">
								招生途径：
							</td>
							<td>
								<font id="enrollmentSourceIdI"></font>
							</td>
						</tr>
	
						<tr>
							<td align="right">
								上传文件：
							</td>
							<td >
								<font id="uploadedFileI"></font>
	
							</td>
							<td align="right">
								导入学生状态：
							</td>
							<td>
								<font id="statusI"></font>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								错误日志：
							</td>
							<td colspan="3">
								<font id="errorLogI"></font>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								分配日志：
							</td>
							<td colspan="3">
								<textarea id="fenpeiLogI" rows="10" cols="130"></textarea>
	
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								备注：
							</td>
							<td colspan="3">
								<font id="noteI"></font>
							</td>
						</tr>
					</table>
	
				</div>
			</body:body>
		
		<script type="text/javascript">
			var checkDelete = function(id){
				var btn = "\"<a href='<%=basePath%>/manage/program_delete?program_id=" + id + "'>确定</a>\"";
				var isdel=$.confirm({msg:"您确定要删除吗？", confirmText:"确定", confirm: function(){
					window.location.href = "<%=basePath%>/manage/program_delete?program_id=" + id;
					$("#form_search").click();
				}});
			}
			
			var go_to = function(page){
				var max = 50;
				var _page = parseInt($("#page").val());
				if(page == "0")
					$("#page").val(0);
				else if(page == "-1" && _page > 0)
					$("#page").val(_page-1);
				else if(page == "+1" && _page < max)
					$("#page").val(_page+1);
				else if(page == "x"){
					_page = parseInt($("#go_x").val());
					if(_page >= 0 && _page < max)
					$("#page").val(_page);
				}else if(page == "e"){
					var _total = parseInt($("#total_record").val());
					if(_total > 0)
						$("#page").val(parseInt(_total/20));
					else
						return false;
				}
				$("#form_search").click();
			}
		</script>
		
		
		<script type="text/javascript">
			var sex = -1;
			//创建学生信息导入
			function create_programImportRecord_callback(data) {
				code = data.id
				//上传文件
				ajaxFileUpload(code);
			}
		</script>
		<!-- 创建 -->
		<a:ajax
			parameters="$('#create_programImportRecord_form').serializeObject()"
			successCallbackFunctions="create_programImportRecord_callback"
			pluginCode="0001" urls="/crm/crm_student_import_record_create_admin" />
		<script type="text/javascript">
			//学生信息导入ID
			var studentImportRecord_id = -1;
			//学生信息导入状态
			var studentImportRecord_status = -1;
		
			var sex = -1;
		
			var code = "";
		
			$(function() {
		
				$('#create')
						.dialog(
								{
									autoOpen : false,
									modal : true,
									draggable : false,
									resizable : false,
									title : '导入数据',
									width : 800,
									height : 355,
									buttons : {
										'保存' : function() {
											if ($("#file").val() == null
													|| $("#file").val() == "") {
												alert("请选择上传文件");
												return false;
											}
											
											//上传文件
											ajaxFileUpload();
											/*if (code != null && code != "") {
												ajaxFileUpload(code);
											} else {
												ajax_0001_1();
											}*/
										},
										'取消' : function() {
											$(this).dialog("close");
										}
									}
								});
			});
			
			function createProgramImportRecord() {
				$('#create').dialog('open');
			}
		
			//上传excel
			function ajaxFileUpload(id) {
				$.ajaxFileUpload({
					url : '<uu:url url="/manage/program_import_upload" />',// + '?program.countryId=' + $("#countryId").val() + '&program.study_level_id=' + $("#studyLevelId").val(),//用于文件上传的服务器端请求地址
					secureuri : false,//一般设置为false
					fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
					dataType : 'json',//返回值类型 一般设置为json
					success : function(data, status) //服务器成功响应处理函数
					{
						alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
		
						if (typeof (data.error) != 'undefined') {
							if (data.error != '') {
								alert(data.error);
							} else {
								alert(data.message);
							}
						}
						$('#create').dialog("close");
						//search001();
					},
					error : function(data, status, e)//服务器响应失败处理函数
					{
						alert(e);
						$('#create').dialog("close");
						//search001();
		
					}
				})
		
				return false;
		
			}
			function sourceIdValue(sourceId) {
				return sourceId.getStudentDatasource();
			}
			function statusValue(id, status) {
				if (status == STATUS_ENABLED) {
					isPageOperating(id, "001", "delete");
				}
				return status == STATUS_ENABLED ? "已导入" : "未导入";
			}
			function assignmentTypeValue(assignmentType) {
				return assignmentType == 1 ? "自动分配" : "手动分配";
			}
			function operatingValue(id, name) {
				return '<input type="radio" id="'+name+'" name="stidentId_radio" value="'+id+'" />';
			}
		</script>
  </body>
</html>
