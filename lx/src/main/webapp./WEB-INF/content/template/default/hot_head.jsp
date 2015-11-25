<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/import.jsp"%>
<script type="text/javascript">
	//退出
			function messageExit(){
				parent.location.href='<s:url value="/template/log_out" />';
				
			}
</script> 
<div id="wrapper">
<!-- logo start here -->
<div class="logo">
<!-- <a href="http://www.hotcoursesabroad.com"><img src='http://images2.content-hca.com/hca-cont/img/sitelogo/new/com_logo.png' alt="Hotcourses International" title="Hotcourses International"></a> </div> -->
<a href="/">
	<%-- <img src='<ui:img url="images/hot/com_logo.png"/>' alt="Hotcourses International" title="Hotcourses International"> --%>
	留学芒果
</a> </div>
<!-- logo end here -->
<nav class="nav">
<!-- search and login start here -->
<ul class="nav_flevel mb_sch">

<li id="desktop2" onmouseover="closetopsearchdiv('topSearchpodForm')">
<a href="javascript:void(0)" class="lnk"><i class="fa fa-user fa-2x"><span id="shortlistcount"></span></i></a>
<div class="megamenu five">
<div class="mg_left">
<ul class="tpbrd pd0">
<s:if test="users!=null && users.user_id > 0">
	<li>您好：<s:property value="users.user_name"/></li>
	<li><a href="#" onclick="messageExit();">注销</a></li>
</s:if>
<s:else>
	<li><a href="../user/login">登录/注册</a></li>
</s:else>
</ul>
</div>
</div>
</li>
<li class="icn" id="classadd2" style="display:none">
<a href="javascript:void(0)" class="lnk" id="inerdevice1" onclick="toptoggle('tabb2','classadd2','tabb1','tabb3');"><i class="fa fa-user fa-2x"><span id="shortlistcountmobile"></span></i></a>
<div class="megamenu five" id="tabb2" style="display:none">
<div class="mg_left">
<ul class="tpbrd pd0">
<li><a href="../user/login">登录/注册</a></li>
</ul>
</div>
</div>
</li>
</ul>
<!-- search and login end here -->
<div class="slad">

<!-- mobile version -->
<ul class="nav_flevel sm mb_block" >
<li class="" id="desktop3">
<a href="#" class="lnk"><i class="fa fa-bars fa-2x"></i></a>
</li>
<li class="" id="classadd3">
<a href="#" class="lnk" onclick="toptoggle('tabb3','classadd3','tabb1','tabb2');"><i class="fa fa-bars fa-2x"></i></a>
</li>
</ul>
<!-- mobile version end -->

<ul class="nav_flevel mb_nav mb_none" id="tabb3">
<!-- search start here -->
<li class="" onmouseover="closetopsearchdiv('topSearchpodForm')">
<a href="#" id="linkarrow1" class="lnk" onclick="opencloseinnerdiv('idin1','idin2','idin3','linkarrow1','linkarrow2','linkarrow3')">
	院校<i class="fa fa-angle-down"></i></a>
<!-- dropdown start here -->
<div class="megamenu two" id="idin1">
<!-- mega menu left content start here -->
<div class="mg_all">
<!-- mg_lst start here -->
<div class="mg_lst">
<h3 id="show1" class="tpbrd" onclick="javascript:toggle('sub1','show1');">
	<a href="../template/programs">找课程  </a>
</h3>
</div>
<!-- mg_lst end here -->
<div class="mg_lst mb20">
<h3 id="show2" onclick="javascript:toggle('sub2','show2');">
	 <a href="../template/universitys">找学校 </a>
</h3>
</div>
</div>
<!-- mega menu left content end here -->
<!-- dropdown end here -->
</li>
<!-- search end here -->
<!-- advice and guidance start here -->
<li class="" onmouseover="closetopsearchdiv('topSearchpodForm')">
<a href="#" class="lnk" id="linkarrow2" onclick="opencloseinnerdiv('idin2','idin1','idin3','linkarrow2','linkarrow1','linkarrow3')">
	攻略 <i class="fa fa-angle-down"></i></a>
<!-- mega menu two start here -->
<div class="megamenu two" id="idin2">
<div class="mg_all">
<!-- mg_lst start here -->
<div onclick="javascript:toggle('sub10','show10');" id="show10">
<div class="mg_lst mb20">
<h3 id="show7" class="tpbrd" onclick="javascript:toggle('sub7','show7');">
	<a href="../template/guides">找攻略</a>
</h3>
</div>
</div>
</div>
</div>
<!-- mega menu two end here -->
</li>
<!-- advice and guidance end here -->
<li class="" onmouseover="closetopsearchdiv('topSearchpodForm')">
<a href="#" class="lnk" id="linkarrow3" onclick="opencloseinnerdiv('idin3','idin1','idin2','linkarrow3','linkarrow1','linkarrow2')">
	问答<i class="fa fa-angle-down"></i></a>
<!-- megamenu start here -->
<div class="megamenu two" id="idin3">
<div class="mg_all">
<!-- mg_lst start here -->
<div onclick="javascript:toggle('sub10','show10');" id="show10">
<div class="mg_lst mb20">
<h3 class="tpbrd" onclick="javascript:toggle('sub7','show7');">
	<a href="../template/asks" class="mb_none">问答 </a>
</h3>
</div>
</div>
<!-- mg_lst end here -->
</div>
</div>
<!-- megamenu end here -->
</li>
<li class="" onmouseover="closetopsearchdiv('topSearchpodForm')">
<a href="#" class="lnk" id="linkarrow3" onclick="opencloseinnerdiv('idin3','idin1','idin2','linkarrow3','linkarrow1','linkarrow2')">
	留学顾问<i class="fa fa-angle-down"></i></a>
<!-- megamenu start here -->
<div class="megamenu two" id="idin3">
<div class="mg_all">
<!-- mg_lst start here -->
<div onclick="javascript:toggle('sub10','show10');" id="show10">
<div class="mg_lst mb20">
<h3 class="tpbrd">
	<a href="../template/consultants" class="mb_none">搜顾问 </a>
</h3>
</div>
</div>
<!-- mg_lst end here -->
</div>
</div>
<!-- megamenu end here -->
</li>
</ul>
</div>
</nav>
</div>