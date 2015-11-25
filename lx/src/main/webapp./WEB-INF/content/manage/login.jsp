<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/manage/login";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>平台</title>
		<jc:plugin name="login" />
		<!--<jc:plugin name="jquery" />
		<jc:plugin name="cookie" />
		<script type="text/javascript">  
			jQuery(document).ready(function() {  
			    jQuery("#username").val(jQuery.cookie('<%=CookieConstants.BROWSER_COOKIE_USER%>')); 
			}); 
		</script>
	--></head>
	<body>
		<form id="form1" name="form1" method="post" action="<%=actionUrl %>" style="margin: 0px;">
			<div id="contentuser">
				<div id="divhead"></div>
				<div id="divimg_1">
					<div id="login">
						<div id="banben">
							V1.0
						</div>
					</div>
					<div id="middinfo">
						<table>
							<span style="color: red;">${error }</span>
							<tr>
								<td class="lefttxt">用户名:</td>
								<td colspan="2"><input name="username" type="text" id="username" /></td>
							</tr>
							<tr>	
								<td class="lefttxt">密&nbsp;&nbsp;码:</td>
								<td colspan="2"><input name="password" type="password" id="password" /></td>
							</tr>
							<tr>
								<td class="lefttxt">验证码:</td>
								<td><input type="text" class="cztxt" id="checkrand" name="checkrand" /></td>
								<td><img src="<uu:url url="manage/vcode"/>" id="image" name="image" /></td>
							</tr>
						</table>
					
					</div>
					<div id="leftbtn" >
						<button value="登录" type="submit" class="btn">登&nbsp;录</button><button value="取消" class="btn">取&nbsp;消</button>
						
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
