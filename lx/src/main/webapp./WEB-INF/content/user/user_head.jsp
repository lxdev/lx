<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/login";
	String registerStudentActionUrl = basePath + "/user/register_student";
	String registerTeacherActionUrl = basePath + "/user/register_teacher";
%>


<div class="toparea">
    <div class="content">
        <div class="logo">
        	<a class="navbar-brand" href="../template/first">
            	<img src="../plugin/new/images/logo.png" />
            </a>
        </div>
        <div class="nav">
            <ul>
            	<li><a href="../template/programs">课程</a></li>
                <li><a href="../template/universitys">院校</a></li>
                <li><a href="../template/guides">攻略</a></li>
                <li><a href="../template/consultants">留学顾问</a></li>
            </ul>
        </div>
        <div class="rarea">
        	<s:if test="users!=null && users.user_id > 0">
        		<input type="hidden" id="current_user_id" value="<s:property value="users.user_id"/>"/>
        		<ul>
					<li>
						<a href="../user/home">您好：<s:property value="users.user_name"/></a></li>
					<li><a href="#" onclick="messageExit();">注销</a></li>
				</ul>
			</s:if>
			<s:else>
				<input type="hidden" id="current_user_id" value=""/>
        		<ul>
					<%--<li class="login"><a href="../user/login">登陆</a></li>
      				<li class="register"><a href="../user/register_student">注册</a></li>--%>
      				<li class="login">登录</li>
      				<li class="register">注册</li>
        		</ul>
			</s:else>
        </div>
    </div>
</div>

<div class="popbg" style="display:none"></div>
<div class="popbox" id="login_box" style="display:none">
    <div class="header"><div class="close"></div></div>
    <div class="content">
        <div class="tabs">
        	<ul id="login_tabs">
				<li class="current">学生登录</li>
				<li>顾问登录</li>
			</ul>
		</div>
        <div class="clear"></div>
        <div class="maincon tab-content tab-content-log paddingtop30"> 
			<div class="tab-pane active" id="div_login_student">
                <form id="formStudent" method="post" role="form" action="<%=actionUrl %>">
					<input type="hidden" id="userType" name="userType" value="1"/>
                    <table cellpadding="0" cellspacing="0" class="table-12">
	                   
	                    <tr>
	                    <td colspan="2"><input type="text" class="txt-4" id="username" name="username" placeholder="手机/邮箱/用户名" /></td>
	                    </tr>
	                    <tr>
	                    <td colspan="2"><input type="password" class="txt-4" id="password" name="password" placeholder="密码" /></td>
	                    </tr>
	                    <tr>
	                    <td colspan="2">
	                        <a href="" class="ftcol929292 ftsize14 floatright margintop10">忘记密码?</a>
	                        <input type="submit" class="button-2" value="登录" />&nbsp;&nbsp;&nbsp;<input type="checkbox" /><span class="ftcol929292 ftsize14">保持登录</span>
	                    </td>
	                    </tr>
                    </table>
                </form>
                <div class="line-3"></div>
                <%--<table cellpadding="0" cellspacing="0" class="table-12 margintop10">
                <tr>
                <td colspan="2"><span class="ftcol929292 ftsize12">用第三方账号直接登录</span></td>
                </tr>
                <tr>
                <td><input type="button" class="button-3" /></td><td align="right"><input type="button" class="button-4" /></td>
                </tr>
                </table>--%>
			</div>
			<div class="tab-pane" id="div_login_teacher">
				<form id="formTeacher" name="formTeacher" method="post" action="<%=actionUrl %>" role="form">
					<input type="hidden" id="userType" name="userType" value="2"/>
                    <table cellpadding="0" cellspacing="0" class="table-12">
                    
                    <tr>
                    <td colspan="2"><input type="text" class="txt-4" id="username" name="username" placeholder="手机/邮箱/用户名" /></td>
                    </tr>
                    <tr>
                    <td colspan="2"><input type="password" class="txt-4" id="password" name="password" placeholder="密码" /></td>
                    </tr>
                    <tr>
                    <td colspan="2">
                        <a href="" class="ftcol929292 ftsize14 floatright margintop10">忘记密码?</a>
                        <input type="submit" class="button-2" value="登录" />&nbsp;&nbsp;&nbsp;<input type="checkbox" /><span class="ftcol929292 ftsize14">保持登录</span>
                    </td>
                    </tr>
                    </table>
                </form>
                <div class="line-3"></div>
			</div>
        </div>
    </div>
</div>
<div class="popbox" id="register_box" style="display:none">
    <div class="header"><div class="close"></div></div>
    <div class="content">
        <div class="tabs">
        	<ul id="register_tabs">
				<li class="current">学生注册</li>
				<li>顾问注册</li>
			</ul>
		</div>
        <div class="clear"></div>
        <div class="maincon tab-content tab-content-res paddingtop30">
			<div class="tab-pane active" id="div_register_student">
                <form id="formRegisterStudent" method="post" role="form" action="<%=registerStudentActionUrl %>">
					<input type="hidden" id="registerType" name="registerType" value="mobile"/>
		      	  	<input type="hidden" id="userType" name="userType" value="1"/>
                    <table cellpadding="0" cellspacing="0" class="table-12">
	                   
	                    <tr>
	                    <td colspan="2">
	                    	<input type="mobile" class="txt-4" id="mobile" name="mobile" placeholder="手机号">
	                    </td>
	                    </tr>
	                    <tr>
	                    	<td>
	                    		<input type="text" class="txt-8" id="code" name="code" placeholder="动态密码">
	                    	</td>
	                    	<td>
	                    		<input type="button" class="btn btn-info button-5" id="create_code" name="create_code" onclick="createCode(1)" value="免费获取动态密码">
				            	<span class="form-control" id="rest_second" name="rest_second" style="display:none;">60</span>
	                    	</td>
	                    	
	                    </tr>
	                    <tr>
	                    	<td colspan="2">
	                    		<input type="text" class="txt-4" id="username" name="username" placeholder="用户名">
	                    	</td>
	                    </tr>
	                    <tr>
	                    	<td colspan="2">
	                    		<input type="password" class="txt-4" id="password" name="password" placeholder="密码" />
	                    	</td>
	                    </tr>
	                    <tr>
	                    <td colspan="2">
	                        <input type="submit" class="button-2" value="注册" />
	                    </td>
	                    </tr>
                    </table>
                </form>
                <div class="line-3"></div>
                <s:if test="message != ''">
		      		<span style=" color:red;"><s:property value="message"/></span>
		      	</s:if>
			</div>
			<div class="tab-pane" id="div_register_teacher">
				<form id="formRegisterTeacher" name="formRegisterTeacher" method="post" action="<%=registerTeacherActionUrl %>" role="form">
					<input type="hidden" name="registerType" id="registerType" value="mobile"/>
		      	  	<input type="hidden" name="userType" id="userType" value="2"/>
                    <table cellpadding="0" cellspacing="0" class="table-12">
	                    
	                    <tr>
		                    <td colspan="2">
		                    	<input type="mobile" class="txt-4" id="mobile" name="mobile" placeholder="手机号">
		                    </td>
	                    </tr>
	                    <tr>
	                    	
	                    	<td>
	                    		<input type="text" class="txt-8" id="code" name="code" placeholder="动态密码">
	                    	</td>
	                    	<td>
	                    		<input type="button" class="btn btn-info button-5" id="create_code" name="create_code" onclick="createCode(2)" value="免费获取动态密码">
				            	<span class="form-control" id="rest_second" name="rest_second" style="display:none;">60</span>
	                    	</td>
	                    </tr>
	                    <tr>
	                    	<td colspan="2">
	                    		<input type="text" class="txt-4" id="username" name="username" placeholder="用户名">
	                    	</td>
	                    </tr>
	                    <tr>
	                    <td colspan="2"><input type="password" class="txt-4" id="password" name="password" placeholder="密码" /></td>
	                    </tr>
	                    <tr>
		                    <td colspan="2">
		                        <input type="submit" class="button-2" value="注册" />
		                    </td>
	                    </tr>
                    </table>
                </form>
                <div class="line-3"></div>
			</div>
        </div>
    </div>
</div>
   <script type="text/javascript">
   $("#register_tabs li").each(function(i){
   	$(this).click(function(){
   		$("#register_tabs li").removeClass("current");
   		$(this).addClass("current");	
   		$(".tab-content-res .tab-pane").hide();
   		$(".tab-content-res .tab-pane").eq(i).show();
   	})
   })
   $("#login_tabs li").each(function(i){
   	$(this).click(function(){
   		$("#login_tabs li").removeClass("current");
   		$(this).addClass("current");	
   		$(".tab-content-log .tab-pane").hide();
   		$(".tab-content-log .tab-pane").eq(i).show();
   	})
   })
   
   
       $(".rarea .login").click(function () {
           $(".popbg").show();
           $("#login_box").show();
       })
       $(".rarea .register").click(function () {
           $(".popbg").show();
           $("#register_box").show();
       })
       $(".popbox .close").click(function () {
           $(".popbg").hide();
           $("#login_box").hide();
           $("#register_box").hide();
       })
       var WindowHeight = $(window).height();
       var WindowWidth = $(window).width();
       $(".popbox").css({ "left": (WindowWidth - 440) / 2, "top": (WindowHeight - 491) / 2 });
       $(".popbg").css("height",$(document).height());

	   $(function () {
		   $(".popbg").hide();
           $("#login_box").hide();
           $("#register_box").hide();

		   $('#login_tabs a').click(function (e) {
			   e.preventDefault();//阻止a链接的跳转行为
			   //$(this).tab('show');//显示当前选中的链接及关联的content
			   var current =  $(this).context.hash;
			   if(current == "#div_login_student"){
				   $("#div_login_student").show();
				   $("#div_login_teacher").hide();
			   }else {
				   $("#div_login_student").hide();
				   $("#div_login_teacher").show();
			   }
		   });
		   $('#register_tabs a').click(function (e) {
			   e.preventDefault();//阻止a链接的跳转行为
			   //$(this).tab('show');//显示当前选中的链接及关联的content
			   var current =  $(this).context.hash;
			   if(current == "#div_register_student"){
				   $("#div_register_student").show();
				   $("#div_register_teacher").hide();
			   }else {
				   $("#div_register_student").hide();
				   $("#div_register_teacher").show();
			   }
		   });
		   
			$("#div_login_student").show();
			$("#div_login_teacher").hide();
			$("#div_register_student").show();
			$("#div_register_teacher").hide();
	   });
	   
	   var createCode = function(userType){
		   var parentForm = userType == 1 ? $("#formRegisterStudent") : $("#formRegisterTeacher");
			var mobile = $("#mobile", parentForm).val();
			if($.trim(mobile) == ""){
				alert("请输入手机号！");
				return;
			}
			
			var url = '<s:url value="../user/vcode"/>';
			var option = { mobile: mobile };
		
			jQuery.post(url,option,
			        function(data)
			    	{
						if(data.message != ""){
							alert(data.message);
							return;
						}
			    		$("#create_code", parentForm).hide();
			    		$("#rest_second", parentForm).show();
			    		setTimeGo();
			    	},
			 "json");
		}
		
		var timeOut = null;
		var setTimeGo = function(userType){
			var parentForm = userType == 1 ? $("#formRegisterStudent") : $("#formRegisterTeacher");
			timeOut = setInterval(function(){
				var _second = parseInt($("#rest_second", parentForm).html());
				if(_second > 0){
					$("#rest_second", parentForm).html(_second-1);
				}else {
					clearInterval(timeOut);
					timeOut = null;
					$("#create_code", parentForm).show();
		    		$("#rest_second", parentForm).hide();
				}
			}, 1000);
		}

	//退出
	function messageExit(){
		parent.location.href='<s:url value="/template/log_out" />';
	}
</script> 
