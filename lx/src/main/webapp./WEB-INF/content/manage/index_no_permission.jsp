<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提示页面</title>
<style rel="stylesheet">
/*top*/
body{margin:0 auto; background:#fff; font-size:12px; font-family:simsun; font-weight:normal;}
img { border:none;}
ul, ol, li, p, dl, dt, dd{margin:0; padding:0; list-style:none;}
.font_red{color:#f00; font-size:12px; }
.font_gray{color:#333;}
a.font_gray:link, a.font_gray:visited, a.font_gray:active{color:#333; text-decoration:underline; font-size:12px;}
a.font_gray:hover{color:#f00;}
.clear{clear:both;}


/* content */
.full{width:560px; margin:0 auto;}
.full dl.error{width:560px;  background:url(<%=Constants.WEB_PATH %>/images/pic_contbg.gif) 0 0 repeat-y; border-bottom:1px solid #ebebeb; margin:100px 0 10px;}
.full dl.error dt{height:44px; line-height:44px;  background:url(<%=Constants.WEB_PATH %>/images/pic_bg.gif) -20px -18px no-repeat; text-align:left; padding:0 15px; vertical-align:center;}
.full dl.error dt img{padding-top:13px; width:167px; height:20px;  background:url(<%=Constants.WEB_PATH %>/images/pic_bg.gif) -20px -309px no-repeat;}
.full dl.error dd{text-align:center;}
.full dl.error dd span.error404{padding:50px 30px 40px 0; display:inline-block;}
.full dl.error dd span.error404 img{width:203px; height:76px;  background:url(<%=Constants.WEB_PATH %>/images/pic_bg.gif) -176px -130px no-repeat;}
.full dl.error dd span.errortext{padding:85px 0 40px 0; display:inline-block;}
.full dl.error dd span.errortext img{width:122px; height:39px;  background:url(<%=Constants.WEB_PATH %>/images/pic_bg.gif) -426px -130px no-repeat;}
.full dl.error dd span.btn_back{padding:0 0 50px 0; display:inline-block;}
.full dl.error dd span.btn_back img{width:122px; height:42px;  background:url(<%=Constants.WEB_PATH %>/images/pic_bg.gif) -432px -179px no-repeat;}
/******/
</style>


</head>
<body>
	<div class="full">
		<dl class="error">
			<dt><h1>错误提示</h1></dt>
			<dd>
				<span class="error404"><h2><%=request.getAttribute("PERMISSION_ERROR") %></h2></span>
				
			</dd>
			<dd>
				<span class="btn_back"><a href="javascript:history.go(-1);">返回</a></span>
			</dd>
		</dl>

	</div>
</body>
</html>
