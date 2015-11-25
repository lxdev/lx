<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>

<div class="navbar-collapse collapse templatemo-sidebar">
  <ul class="templatemo-sidebar-menu">
    <li class="active"><a href="#"><i class="fa fa-home"></i>首页</a></li>
    <li class="sub">
      <a href="javascript:;">
        <i class="fa fa-database"></i> 问答 <div class="pull-right"><span class="caret"></span></div>
      </a>
      <ul class="templatemo-submenu">
        <li><a href="../ask/my_ask">我的问题</a></li>
        <li><a href="../crm/my_ask_follow">我关注的问题</a></li>
      </ul>
    </li>
    <li class="sub">
      <a href="javascript:;">
        <i class="fa fa-database"></i> 点评 <div class="pull-right"><span class="caret"></span></div>
      </a>
      <ul class="templatemo-submenu">
        <li><a href="../university/evaluate">我点评的学校</a></li>
        <li><a href="#">我点评的顾问、机构</a></li>
      </ul>
    </li>
    <li class="sub">
    	<a href="maps.html"><i class="fa fa-map-marker"></i>服务<div class="pull-right"><span class="caret"></span></div></a>
    	<ul class="templatemo-submenu">
        <li><a href="#">我咨询过的顾问</a></li>
        <li><a href="#">收到的背景评估</a></li>
        <li><a href="#">服务订单</a></li>
        <li><a href="#">服务进程管理</a></li>
      </ul>
    </li>
    <li><a href="tables.html"><i class="fa fa-users"></i><span class="badge pull-right">NEW</span>消息提醒</a></li>
    <li><a href="tables.html"><i class="fa fa-users"></i><span class="badge pull-right">NEW</span>交互</a></li>
    <li class="sub">
    	<a href="javascript:;"><i class="fa fa-users"></i>收藏 <div class="pull-right"><span class="caret"></span></div></a>
    	<ul class="templatemo-submenu">
    		<li><a href="#"><span class="badge pull-right">42</span>收藏学校</a></li>
    		<li><a href="#"><span class="badge pull-right">42</span>收藏项目</a></li>
    		<li><a href="#"><span class="badge pull-right">42</span>收藏攻略</a></li>
    	</ul>
    </li>
    <li><a href=""><i class="fa fa-cubes"></i><span class="pull-right"></span>申请管理</a></li>
    <li class="sub">
    	<a href="javascript:;"><i class="fa fa-cog"></i>个人资料 <div class="pull-right"><span class="caret"></span></div>
    	</a>
    	<ul class="templatemo-submenu">
        <li><a href="#">基本资料</a></li>
        <li><a href="#">个人履历</a></li>
      </ul>
    </li>
    <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>注销</a></li>
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
