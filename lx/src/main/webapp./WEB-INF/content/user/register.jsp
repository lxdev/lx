<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/register_student";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>留学芒果</title>
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
    	<jc:plugin name="hot_base_css" />
		<jc:plugin name="hot_base_js" />
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_login"/>
		<script>
			$(function () {
				$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		        }) 
			});
			
			var createCode = function(){
				var mobile = $("#mobile").val();
				if($.trim(mobile) == ""){
					alert("请输入手机号！");
					return;
				}
				
				var url = '<s:url value="vcode"/>';
				var option = { mobile: mobile };
			
				jQuery.post(url,option,
				        function(data)
				    	{
							if(data.message != ""){
								alert(data.message);
								return;
							}
				    		$("#create_code").hide();
				    		$("#rest_second").show();
				    		setTimeGo();
				    	},
				 "json");
			}
			
			var timeOut = null;
			var setTimeGo = function(){
				timeOut = setInterval(function(){
					var _second = parseInt($("#rest_second").html());
					if(_second > 0){
						$("#rest_second").html(_second-1);
					}else {
						clearInterval(timeOut);
						timeOut = null;
						$("#create_code").show();
			    		$("#rest_second").hide();
					}
				}, 1000);
			}
		</script>
		<style>
			.control-wrapper { padding-left: 64px; }
		</style>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
		    	<li role="presentation" class="active"><a href="#div_mobile">手机注册</a></li>
		        <li role="presentation"><a href="#div_email">邮箱注册</a></li>
		    </ul>
		    <div class="tab-content">
		      <div class="tab-pane active" id="div_mobile">
		      	  <form id="formMobile" name="formMobile" method="post" role="form" action="<%=actionUrl %>" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30">
		      	  	<input type="hidden" id="registerType" name="registerType" value="mobile"/>
		      	  	<input type="hidden" id="userType" name="userType" value="1"/>
			        <div class="form-group">
			          <div class="col-xs-12">
			            <div class="control-wrapper">
			            	<label for="mobile" class="control-label fa-label">手机号</label>
			            	<input type="mobile" class="form-control" id="mobile" name="mobile" placeholder="手机号">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			        	<div class="col-xs-12">
				            <div class="control-wrapper">
				            	<label for="create_code" class="control-label fa-label"></label>
				            	<input type="button" class="btn btn-info" id="create_code" name="create_code" onclick="createCode()" value="免费获取动态密码">
				            	<span class="form-control" id="rest_second" name="rest_second" style="display:none;">60</span>
				            </div>
			          	</div>
			        </div>
			        <div class="form-group">
			          <div class="col-xs-12">
			            <div class="control-wrapper">
			            	<label for="code" class="control-label fa-label">动态密码</label>
			            	<input type="text" class="form-control" id="code" name="code" placeholder="动态密码">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-xs-12">
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label">用户名</label>
			            	<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label">密码</label>
			            	<input type="password" class="form-control" id="password" name="password" placeholder="密码">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="submit" value="注册" class="btn btn-info">
			          	</div>
			          </div>
			        </div>
			      </form>
			      <div class="text-center">
			      	<a href="login" class="templatemo-create-new">登录 <i class="fa fa-arrow-circle-o-right"></i></a>	
			      </div>
		      </div> 
		      <div class="tab-pane" id="div_email">
		      	<form id="formEmail" name="formEmail" method="post" action="<%=actionUrl %>" role="form" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30">
		      		<input type="hidden" name="registerType" id="registerType" value="email"/>
		      	  	<input type="hidden" name="userType" id="userType" value="1"/>
		      		<div class="form-group">
			          <div class="col-xs-12">
			            <div class="control-wrapper">
			            	<label for="email" class="control-label fa-label">邮箱</label>
			            	<input type="text" class="form-control" id="email" name="email" placeholder="邮箱">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label">用户名</label>
			            	<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label">密码</label>
			            	<input type="password" class="form-control" id="password" name="password" placeholder="密码">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="code" class="control-label fa-label">验证码</label>
			            	<input type="text" class="form-control" id="code" name="code" placeholder="验证码">
			            	<img src="<uu:url url="manage/vcode"/>" id="image" name="image" />
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="submit" value="注册" class="btn btn-info">
			          	</div>
			          </div>
			        </div>
				</form>
				<div class="text-center">
			      	<a href="login" class="templatemo-create-new">登录 <i class="fa fa-arrow-circle-o-right"></i></a>	
			    </div>
		      </div>
		      <div>
		      	<s:if test="message != ''">
		      		<span style=" color:red;"><s:property value="message"/></span>
		      	</s:if>
		      </div>
		    </div>
		</div>
		
	</body>
</html>
