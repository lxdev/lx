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
		<title>忘记密码</title>
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
		</script>
	</head>
	<body>
		<div class="container-fluid">
    		<jsp:include page="../template/default/hot_head.jsp" />
    	</div>
		<div class="container">
			<div class="col-md-12">
			<h1 class="margin-bottom-15">Password Reset</h1>
			<form class="form-horizontal templatemo-forgot-password-form templatemo-container" role="form" action="#" method="post">	
				<div class="form-group">
		          <div class="col-md-12">
		          	Please enter your email address that you registered in our website.
		          </div>
		        </div>		
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="text" class="form-control" id="email" placeholder="Your email">	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="submit" value="Submit" class="btn btn-danger">
                    <br><br>
                    <a href="login-1.html">Login One</a> |
                    <a href="login-2.html">Login Two</a>
		          </div>
		        </div>
		      </form>
		</div>
		</div>
	
		
	</body>
</html>
