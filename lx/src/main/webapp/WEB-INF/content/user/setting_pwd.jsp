<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
  ///////////////////
  //用户自己维护和管理的个人页面
  ///////////////////
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	Object user_type = request.getAttribute("users.user_type");

	String actionUrl = basePath + "/user/post_set_pwd";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>留学芒果-个人中心-修改密码</title>
        <jc:plugin name="new_css" />
        <jc:plugin name="new_js" />
        
        <jc:plugin name="new_manage"/>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container-fluid">
			<div class="template-page-wrapper">
		    	<s:action namespace="/user" name="left" executeResult="true"/>
		
		      	<div class="cen_rnv">
		      		<span>${message}</span>
		      		<form id="formPwd" method="post" role="form" action="<%=actionUrl %>" accept-charset="UTF-8">
					<table>
						<tr>
							<th>原密码：</th>
							<td><input type="password" class="txt-4" id="pwdOld" name="pwdOld" placeholder="密码" /></td>
						</tr>
						<tr>
							<th>新密码：</th>
							<td><input type="password" class="txt-4" id="pwdNew" name="pwdNew" placeholder="新密码" /></td>
						</tr>
						<tr>
							<th>新密码：</th>
							<td><input type="password" class="txt-4" id="pwdNew2" name="pwdNew2" placeholder="再次输入新密码" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="button-2" value="提交" onclick="return validatePwdForm('formPwd')"/></td>
						</tr>
					</table>
					</form>
				</div>
				<div class="clear"></div>
		
		      <footer class="templatemo-footer">
		        <div class="templatemo-copyright">
		          <p>Copyright &copy; 2015 留学芒果  <a href="http://www.lxmango.com/" title="留学芒果" target="_blank"></a></p>
		        </div>
		      </footer>
		    </div>
		</div>
	
		<jc:plugin name="main_js" />
		<script type="text/javascript">
			// document.ready
		    $('#myTab a').click(function (e) {
		      e.preventDefault();
		      $(this).tab('show');
		    });
		
		    $('#loading-example-btn').click(function () {
		      var btn = $(this);
		      btn.button('loading');
		  });
		</script>
	</body>
</html>
