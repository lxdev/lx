<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="../template/first">
				<img alt="留学芒果" src="">
			</a>
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        		<span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">院校 <span class="caret"></span></a>
        			<ul class="dropdown-menu" role="menu">
			            <li><a href="../template/programs">找课程</a></li>
			            <li class="divider"></li>
			            <li><a href="../template/universitys">找学校</a></li>
		          	</ul>
		        </li>
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">攻略 <span class="caret"></span></a>
        			<ul class="dropdown-menu" role="menu">
			            <li><a href="../template/guides">找攻略</a></li>
		          	</ul>
		        </li>
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">问答 <span class="caret"></span></a>
        			<ul class="dropdown-menu" role="menu">
			            <li><a href="../template/asks">问答</a></li>
		          	</ul>
		        </li>
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">留学顾问<span class="caret"></span></a>
        			<ul class="dropdown-menu" role="menu">
			            <li><a href="../template/consultants">找顾问</a></li>
		          	</ul>
		        </li>
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          				<i class="fa fa-user fa-2x"><span id="shortlistcount" class="caret"></span></i>
          			</a>
        			<ul class="dropdown-menu" role="menu">
			            <s:if test="users!=null && users.user_id > 0">
							<li>您好：<s:property value="users.user_name"/></li>
							<li><a href="#" onclick="messageExit();">注销</a></li>
						</s:if>
						<s:else>
							<li><a href="../user/login">登录</a></li>
							<li><a href="../user/register_student">注册学生</a></li>
							<li><a href="../user/register_teacher">注册顾问</a></li>
						</s:else>
		          	</ul>
		        </li>
      		</ul>
      	</div>
	</div>
</nav>

<script type="text/javascript">
	//退出
	function messageExit(){
		parent.location.href='<s:url value="/template/log_out" />';
		
	}
</script> 
