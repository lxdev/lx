<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<title>导入Program
	</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!--  分页 -->
	<jc:plugin name="page" />

	<jc:plugin name="loading" />

	<jc:plugin name="ajax_upload" />

	<script type="text/javascript">
	var sex = -1;
	//创建学生信息导入
	function create_studentImportRecord_callback(data) {
		code = data.id
		//上传文件
		ajaxFileUpload(code);
	}
	//更新学生信息导入
	function update_studentImportRecord_callback(data) {
		$('#update').dialog("close");
		search001();
	}

	//删除学生信息导入
	function delete_studentImportRecord_callback(data) {
		$('#message_confirm').dialog("close");
		search001();
	}
	//查看更新内容
	function view_update_studentImportRecord_callback(data) {
		var studentImportRecord = data.studentImportRecord;
		$("#idI").val(studentImportRecord.id);
		$("#titleI").html(studentImportRecord.title);
		$("#cnameI").val(studentImportRecord.cname);
		$("#enameI").val(studentImportRecord.ename);
		$("#snameI").val(studentImportRecord.sname);
		$("#jnameI").val(studentImportRecord.jname);
		$("#statusI").val(customStatus(studentImportRecord.status));
		$("#valueI").val(studentImportRecord.value);
		$("#remarkI").val(studentImportRecord.remark);
		$('#update').dialog("open");
	}
	//查看
	function view_studentImportRecord_callback(data) {
		var studentImportRecord = data.studentImportRecord;
		$("#idI").val(studentImportRecord.id);
		$("#titleI").html(studentImportRecord.title);
		$("#completeRatioI").html(studentImportRecord.completeRatio);
		$("#assignmentTypeI").html(
				studentImportRecord.assignmentType == 1 ? "自动分配" : "手动分配");
		//$("#sourceIdI").html($("#sourceId").find("option[value="+studentImportRecord.sourceId+"]").text());
		$("#sourceIdI").html(
				studentImportRecord.sourceId.getStudentDatasource());

		$("#planedStudentCountI").html(studentImportRecord.planedStudentCount);
		$("#peopleTypeI").html(studentImportRecord.peopleType);
		$("#enrollmentSourceIdI").html(
				$("#source").find(
						"option[value="
								+ studentImportRecord.enrollmentSourceId + "]")
						.text());
		//$("#enrollmentWayIdI").html($("#way").find("option[value="+studentImportRecord.enrollmentWayId+"]").text());
		$("#enrollmentWayIdI").html(data.enrollmentWayName);
		$("#statusI")
				.html(studentImportRecord.studentSttaus.getStudentStatus());
		$("#channelIdI").html(data.channelName);
		$("#noteI").html(studentImportRecord.note);
		$("#uploadedFileI")
				.html(
						"<a href='<ua:attachment url='"+studentImportRecord.uploadedFile+"' />'>下载</a>");
		$("#successCountI").html(studentImportRecord.successCount);
		$("#errorCountI").html(studentImportRecord.errorCount);
		$("#errorLogI").html(studentImportRecord.errorLog);
		$("#fenpeiLogI").html(studentImportRecord.fenpeiLog);
		$('#view').dialog("open");
	}
	//禁用或启用
	function change_status_studentImportRecord_callback() {
		$('#message_enable_disenable').dialog("close");
		search001();
	}

	//招生途径和市场途径回调函数
	function wayAndSourceCallback(doc) {
		$("#source").html("");
		//$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source"));

		$(doc.enrollmentSources)
				.each(
						function() {
							if (this.id == WEB_STU_SOURCE_DEFAULT) {
								$(
										"<option selected='selected' value='" + this.id + "' title='" + this.name + "' >"
												+ this.name + "</option>")
										.appendTo($("#source"));
							} else {
								//$("<option value='" + this.id + "' title='" + this.name + "' >" + this.name + "</option>").appendTo($("#source"));
							}

						});
		//默认设置社招
		$("#source").val(WEB_STU_SOURCE_DEFAULT);

		$("#way").html("");
		var wayStr = "<option value='" + 0 + "'>请选择市场途径</option>";
		$.each(doc.enrollmentWaysMap, function(key, value) {
			if (key != "大客户" && key != "渠道" && key != "老带新" && key != "加盟"
					&& key != "共建") {
				wayStr += "<optgroup label='"+key+"'>";
				$(this).each(
						function() {
							wayStr += "<option value='" + this.id + "'>"
									+ this.name + "</option>";
						});
				wayStr += "</optgroup>";
			}
		});
		$("#way").html(wayStr);

	}
</script>
	<!-- 创建 -->
	<a:ajax
		parameters="$('#create_studentImportRecord_form').serializeObject()"
		successCallbackFunctions="create_studentImportRecord_callback"
		pluginCode="0001" urls="/crm/crm_student_import_record_create_admin" />
	<!-- 更新 -->
	<a:ajax
		parameters="$('#update_studentImportRecord_form').serializeObject()"
		successCallbackFunctions="update_studentImportRecord_callback"
		pluginCode="0002" urls="/crm/crm_student_import_record_update" />
	<!-- 查看更新内容 -->
	<a:ajax parameters="{'studentImportRecord.id':studentImportRecord_id}"
		successCallbackFunctions="view_update_studentImportRecord_callback"
		pluginCode="0003" urls="/crm/crm_sstudent_import_record_view" />
	<!-- 查看更新内容 -->
	<a:ajax parameters="{'studentImportRecord.id':studentImportRecord_id}"
		successCallbackFunctions="view_studentImportRecord_callback"
		pluginCode="0004" urls="/crm/crm_student_import_record_view" />
	<!-- 删除 -->
	<a:ajax parameters="{'studentImportRecord.id':studentImportRecord_id}"
		successCallbackFunctions="delete_studentImportRecord_callback"
		pluginCode="0005" urls="/crm/crm_student_import_record_delete" />

	<!--启用 -->
	<a:ajax
		parameters="{'studentImportRecord.id':studentImportRecord_id,'studentImportRecord.status':studentImportRecord_status}"
		successCallbackFunctions="change_status_studentImportRecord_callback"
		pluginCode="0008" urls="/crm/crm_student_import_record_change_status" />

	<a:ajax successCallbackFunctions="wayAndSourceCallback"
		pluginCode="0006" urls="/crm/student_way_list" isOnload="all" />
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
									var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 
									if ($("#title").val() == null
											|| $("#title").val() == "") {
										alert("请填写数据名称");
										return false;
									}

									if ($("#file").val() == null
											|| $("#file").val() == "") {
										alert("请选择上传文件");
										return false;
									}
									if ($("#file").val().indexOf(".xls") == -1) {
										alert("请上传.xls文件");
										return false;
									}
									//请选择学生来源
									if ($("#source").val() == "0") {
										$('#message_source').dialog("open");
										return false;
									} else {
										//市场途径
										if ($("#way").val() == "0") {
											$('#message_way').dialog("open");
											return false;
										} else {
											if ($('#source').val() == WEB_STU_SOURCE_DEFAULT) {

											} else {
												if ($("#channel").val() != null
														&& $("#channel").val() != "0") {

												} else {
													$('#message_hezuofang')
															.dialog("open");
													return false;
												}
											}
										}
									}
									//上传文件
									//ajaxFileUpload();
									if (code != null && code != "") {
										ajaxFileUpload(code);
									} else {
										ajax_0001_1();
									}
								},
								'取消' : function() {
									$(this).dialog("close");
								}
							}
						});

		$('#view').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '查看导入数据',
			width : 1024,
			height : 400
		});

		$('#message_confirm').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125

		});

		$('#message_enable_disenable').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125
		});

		$('#message_laodaixin').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125

		});
		$('#message_hezuofang').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125

		});

		$('#message_source').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125

		});
		$('#message_way').dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			resizable : false,
			title : '提示',
			width : 260,
			height : 125

		});

	});
	function deleteE(id) {
		$('#message_confirm').dialog({
			buttons : {
				'确定' : function() {
					studentImportRecord_id = id;
					ajax_0005_1();
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#message_confirm').dialog("open");
	}
	function updateE(id) {
		studentImportRecord_id = id;
		ajax_0003_1();
	}
	function getE(id) {
		studentImportRecord_id = id;
		ajax_0004_1();
	}
	//自定义状态列
	function customStatus(status) {
		switch (status) {
		case STATUS_SYS_INIT:
			return '<m:il8n key="public.status.system"/>';
		case STATUS_DISABLE:
			return '<m:il8n key="public.status.disable"/>';
		case STATUS_ENABLED:
			return '<m:il8n key="public.status.enable"/>';
		}
	}
	//自定义操作
	function customOperating(id, status) {
		var str = "";
		switch (status) {
		case STATUS_SYS_INIT:
			//当系统数据为内置状态时，隐藏删除操作
			isPageOperating(id, "001", "delete");
			//isPageOperating(id,"001","update");
			//str += '--';
			break;
		case STATUS_DISABLE:
			str += '<a href="#" onclick="isEnable(' + id + ',' + STATUS_ENABLED
					+ ')">导入</a>';
			break;
		case STATUS_ENABLED:
			//isPageOperating(id,"001","delete");
			//isPageOperating(id,"001","update");
			//str += '<a href="#" onclick="isEnable('+id+','+STATUS_DISABLE+')">禁用</a>';
			break;
		}

		return str;
	}

	//是否启用
	function isEnable(id, status) {

		$('#message_enable_disenable').dialog({
			buttons : {
				'确定' : function() {
					studentImportRecord_status = status;
					studentImportRecord_id = id;
					ajax_0008_1();
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#message_enable_disenable').dialog("open");
	}
	function createStudentImportRecord() {
		$('#create').dialog('open');
	}

	//上传excel
	function ajaxFileUpload(id) {
		$.ajaxFileUpload({
			url : '<uu:url url="/crm/crm_student_import_record_upload" />?id='
					+ id,//用于文件上传的服务器端请求地址
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
				search001();
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				alert(e);
				$('#create').dialog("close");
				search001();

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
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="CC数据导入" il8nName="crm">
			<html:a text="student.import.record.operating.import" il8nName="crm"
				onclick="createStudentImportRecord();" icon="add" />
		</head:head>

		<!--主体层开始 -->
		<body:body>
			<div id="message_hezuofang" style="display: none">
				<div>
					请选择合作方！
				</div>
			</div>
			<div id="message_source" style="display: none">
				<div>
					请选择招生途径！
				</div>
			</div>
			<div id="message_way" style="display: none">
				<div>
					请选择市场途径！
				</div>
			</div>
			<page:plugin pluginCode="001" il8nName="crm"
				searchListActionpath="crm_student_import_record_list"
				searchCountActionpath="crm_student_import_record_count"
				columnsStr="title;sourceId;successCount;errorCount;status;#public.operating"
				customColumnValue="1,sourceIdValue(sourceId);4,statusValue(id,status);5,customOperating(id,status)"
				pageSize="10" isNumber="true" checkboxValue="id"
				delete="json,deleteE,id" view="json,getE,id"
				params="'studentImportRecord.type':3" />
			<div id="message_confirm" style="display: none">
				<div>
					确定要删除吗？该操作不可逆！
				</div>
			</div>

			<div id="message_enable_disenable" style="display: none">
				<div>
					确定要执行该操作吗？该操作不可逆！
				</div>
			</div>

			

		</body:body>
	</body>

</html>