<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/login";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>留学芒果</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
    	<%--<jc:plugin name="hot_base_css" />
		<jc:plugin name="hot_base_js" />
		--%><jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_login"/>
		<script>
			$(function () {
				$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		        }) 
			});
		</script>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
		    	<li role="presentation" class="active"><a href="#div_student">学生登录</a></li>
		        <li role="presentation"><a href="#div_teacher">顾问登录</a></li>
		    </ul>
		    <div class="tab-content"> 
		      <div class="tab-pane active" id="div_student">
		      		<span style="color: red;">${error }</span>
		      	  <form id="formStudent" method="post" role="form" action="<%=actionUrl %>" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30">
		      	  	<input type="hidden" id="userType" name="userType" value="1"/>
		      	  	<h2 class="form-signin-heading">请学生登录</h2>
			        <div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
			            	<input type="text" class="form-control" id="username" name="username" placeholder="手机/邮箱/用户名">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
			            	<input type="password" class="form-control" id="password" name="password" placeholder="密码">
			            </div>
			          </div>
			        </div>
			        <div class="form-group" style="display:none">
			          <div class="col-md-12">
		             	<div class="checkbox control-wrapper">
		                	<label>
		                  		<input type="checkbox"> Remember me
	                		</label>
		              	</div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="submit" value="登录" class="btn btn-info">
			          		<a href="forgotpassword" class="text-right pull-right">忘记密码?</a>
			          	</div>
			          </div>
			        </div>
			        <hr/>
			        <div class="form-group">
			        	<div class="col-md-12">
			        		<label>Login with: </label>
			        		<div class="inline-block">
			        			<a href="#"><i class="fa fa-facebook-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-twitter-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-google-plus-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-tumblr-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-github-square login-with"></i></a>
			        		</div>		        		
			        	</div>
			        </div>
			      </form>
			      <div class="text-center">
			      	<a href="register_student" class="templatemo-create-new">注册 <i class="fa fa-arrow-circle-o-right"></i></a>	
			      </div>
		      </div> 
		      <div class="tab-pane" id="div_teacher">
		      	<span style="color: red;">${error }</span>
		      	<form id="formTeacher" name="formTeacher" method="post" action="<%=actionUrl %>" role="form" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30">
		      		<input type="hidden" id="userType" name="userType" value="2"/>
		      		<h2 class="form-signin-heading">请顾问登录</h2>
		      		<div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
			            	<input type="text" class="form-control" id="username" name="username" placeholder="手机/邮箱/用户名">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
			            	<input type="password" class="form-control" id="password" name="password" placeholder="密码">
			            </div>
			          </div>
			        </div>
			        <div class="form-group" style="display:none">
			          <div class="col-md-12">
		             	<div class="checkbox control-wrapper">
		                	<label>
		                  		<input type="checkbox"> Remember me
	                		</label>
		              	</div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="submit" value="登录" class="btn btn-info">
			          		<a href="user/forgotpassword" class="text-right pull-right">忘记密码?</a>
			          	</div>
			          </div>
			        </div>
			        <hr/>
			        <div class="form-group">
			        	<div class="col-md-12">
			        		<label>Login with: </label>
			        		<div class="inline-block">
			        			<a href="#"><i class="fa fa-facebook-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-twitter-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-google-plus-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-tumblr-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-github-square login-with"></i></a>
			        		</div>		        		
			        	</div>
			        </div>
				</form>
				<div class="text-center">
			      	<a href="register_teacher" class="templatemo-create-new">注册 <i class="fa fa-arrow-circle-o-right"></i></a>	
			    </div>
		      </div>
		    </div>
		</div>
	
		
	</body>
</html>
