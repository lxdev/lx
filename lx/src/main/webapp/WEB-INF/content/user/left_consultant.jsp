<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>

<div class="navbar-collapse collapse templatemo-sidebar">
  <ul class="templatemo-sidebar-menu">
    <li class="active"><a href="#"><i class="fa fa-home"></i>首页</a></li>
    <li class="sub">
      <a href="javascript:;">
        <i class="fa fa-database"></i>我的问答 <div class="pull-right"><span class="caret"></span></div>
      </a>
      <ul class="templatemo-submenu">
        <li><a href="../ask/my_ask">推送的问题</a></li>
        <li><a href="../crm/my_ask_follow">我得回答</a></li>
      </ul>
    </li>
    <li class="sub">
      <a href="javascript:;">
        <i class="fa fa-database"></i>我的点评 <div class="pull-right"><span class="caret"></span></div>
      </a>
      <ul class="templatemo-submenu">
        <li><a href="../consultant/my_evaluate">全部好评中评差评</a></li>
      </ul>
    </li>
    <li class="sub">
      <a href="javascript:;">
        <i class="fa fa-database"></i>我的学生 <div class="pull-right"><span class="caret"></span></div>
      </a>
      <ul class="templatemo-submenu">
        <li><a href="../consultant/my_evaluate">咨询当期往期</a></li>
      </ul>
    </li>
    <li class="sub">
    	<a href="maps.html"><i class="fa fa-map-marker"></i>服务<div class="pull-right"><span class="caret"></span></div></a>
    	<ul class="templatemo-submenu">
        <li><a href="#">定位评估</a></li>
        <li><a href="#">我得评估邀请我的评估报告</a></li>
        </ul>
    </li>
    <li class="sub">
    	<a href="maps.html"><i class="fa fa-map-marker"></i>选校管理<div class="pull-right"><span class="caret"></span></div></a>
        <ul class="templatemo-submenu">
        <li><a href="#">生产选校名单推送给我的学生</a></li>
        <li><a href="#">生产选校名单点选系统中项目</a></li>
      </ul>
    </li>
    <li><a href="tables.html"><i class="fa fa-users"></i><span class="badge pull-right">NEW</span>消息提醒</a></li>
    <li><a href="tables.html"><i class="fa fa-users"></i><span class="badge pull-right">NEW</span>交互</a></li>
    <li><a href=""><i class="fa fa-cubes"></i><span class="pull-right"></span>申请管理</a></li>
    <li class="sub">
    	<a href="javascript:;"><i class="fa fa-cog"></i>个人资料 <div class="pull-right"><span class="caret"></span></div>
    	</a>
    	<ul class="templatemo-submenu">
        <li><a href="../user/my_info">账户设置</a></li>
        <li><a href="#">更改密码</a></li>
        <li><a href="#">绑定手机</a></li>
        <li><a href="#">绑定邮箱</a></li>
      </ul>
    </li>
    <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
  </ul>
</div>
<script type="text/javascript">
	$(document).ready( function() {
		// sidebar menu click
		$('.templatemo-sidebar-menu li.sub a').click(function(){
			if($(this).parent().hasClass('open')) {
				$(this).parent().removeClass('open');
			} else {
				$(this).parent().addClass('open');
			}
		});  // sidebar menu click
	}); 
</script>
