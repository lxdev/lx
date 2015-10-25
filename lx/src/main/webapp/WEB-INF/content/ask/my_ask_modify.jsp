<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/ask/home";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>留学芒果</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>
		<jc:plugin name="select2"/>
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
    					编辑
    					<form class="form-horizontal" method="post" action="../ask/my_ask_modify" onsubmit="return valid_submit()">
    						<input type="hidden" name="entity.id" value="<s:property value="entity.id"/>"/>
							<div class="form-group">
								<label for="title" class="col-sm-2 control-label" placeholder="输入问题标题">标题</label>
								<div class="col-sm-10">
									<input type="input" class=form-control" id="title" name="entity.title" value="<s:property value="entity.title"/>"/>
								</div>
							</div>
							<div class="form-group">
								<label for="body" class="col-sm-2 control-label">内容</label>
								<div class="col-sm-10">
									<textarea rows="5" class="form-control" id="body" name="entity.body" placeholder="输入问题内容"><s:property value="entity.body"/></textarea>
								</div>
							</div>
							<div class="form-group form-horizontal">
								<label for="is_use_consultant" class="col-sm-2 control-label">是否指定顾问</label>
								<div class="col-sm-3">
									<label class="radio-inline">
										<input type="radio" name="entity.ask_type" id="c1" value="2" <s:if test="entity.ask_type==2 || entity.ask_type==null">checked</s:if> onclick="setConsultant(2)">不指定</label>
									</label>
									<label class="radio-inline">
										<input type="radio" name="entity.ask_type" id="c2" value="1" <s:if test="entity.ask_type==1">checked</s:if> onclick="setConsultant(1)">指定</label>
									</label>
								</div>
								<div class="col-sm-7 form-group" id="div_consultant" style="display:none;">
									<label for="entity.reply_user_id" class="control-label col-sm-2">选择顾问</label>
									<div class="col-sm-5">
										<select class="form-control margin-bottom-15 js-example-data-array" id="reply_user_id" name="entity.reply_user_id" value="<s:property value="entity.reply_user_id"/>">
										</select>
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-primary">保存</button>
							<a href="../ask/my_ask" class="buton btn btn-default" click="">取消</a>
						</form>
        			</div>
        		</div>
		    </div>
		</div>
	
		<script type="text/javascript">
			var valid_submit = function(){
				var parent = $("form");
				if($.trim($("#title", parent).val()) == ""){
					alert("请输入标题！");
					return false;
				}
				if($.trim($("#body", parent).val()) == ""){
					alert("请输入内容！");
					return false;
				}
				
				return true;
			}
			var loadUser = function(){
				//var data = [{ id: 0, text: 'enhancement' }, { id: 1, text: 'bug' }, { id: 2, text: 'duplicate' }, { id: 3, text: 'invalid' }, { id: 4, text: 'wontfix' }];
				var user_data = [];
				jQuery.post('<s:url value="/user/consultant_search"/>',{'full_name':''},
				        function(data)
				    	{
				    		var lists="";
				    		if(null!=data.userList)
				    		jQuery.each(data.userList, function()
			    			{		    			
				    			user_data.push({ id: this.user_id, text: this.full_name });
			    			});
							$(".js-example-data-array").select2({
							  data: user_data
							})
				    	},
				 "json");
			}
			$(function(){
				loadUser();
				if('<s:property value="entity.ask_type"/>' == '1')
					setConsultant(1);
				else
					setConsultant(2);
			});
			
			
			var setConsultant = function(val){
				if(val == 2){
					$("#div_consultant").hide();
				}else {
					$("#div_consultant").show();
				}
			}
		</script>
	</body>
</html>
