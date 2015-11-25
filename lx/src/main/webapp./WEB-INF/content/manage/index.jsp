<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title>留学芒果</title> 
	    <jc:plugin name="jquery"/>
	    <style>
	       html {scrollbar-face-color: #BFD9F9; 
	       		 scrollbar-highlight-color: #BFD9F9; 
	       		 scrollbar-shadow-color: #BFD9F9; 
	       		 scrollbar-3dlight-color: #000000; 
	       		 scrollbar-arrow-color: #000000; 
	       		 scrollbar-track-color: #ffffff;
	       		 scrollbar-darkshadow-color: #000000}
	    </style>
	</head>
<frameset rows="87,*,28" frameborder="NO" framespacing="0" id="rootf"> 
  <frame name="head" id="headf" src="<%=Constants.WEB_PATH %>/manage/head" scrolling="no" noresize="noresize" style="height: 100px;"/>
  <frameset cols="199,10,*" frameborder="NO" framespacing="0" id="mainCont"> 
    <frame name="left" src="<%=Constants.WEB_PATH %>/manage/left" scrolling="yes" style="overflow-x:hidden;" noresize="noresize" name="left"/>
	<frame src="<%=Constants.WEB_PATH %>/manage/switch" scrolling="no" noresize="noresize" name="switch" />
     <frameset rows="16,*" frameborder="NO" framespacing="0" id="mainCont" >
	 	<frame src="<%=Constants.WEB_PATH %>/manage/line"" scrolling="no" noresize="noresize" name="right1" />
	 	<frame src="<%=Constants.WEB_PATH %>/manage/main" scrolling="auto" noresize="noresize" name="main" />
	 </frameset>
  </frameset>
  <frame src="<%=Constants.WEB_PATH %>/manage/foot" scrolling="no" noresize="noresize" style="height: 100px;"/>
</frameset>
<noframes>
<body bgcolor="#fff" text="#000">
	您的浏览器不支持frames
	//20110325登录后，左侧默认隐藏，默认右边区域最大化，增加鼠标事件，鼠标进过switch的时候切换，这样保证右边区域正常情况下都是最大化
	//20110428登录后，自动隐藏功能取消，恢复手动模式
</body>
</noframes>
</html>
