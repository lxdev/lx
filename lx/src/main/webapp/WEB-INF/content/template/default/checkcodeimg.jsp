<%@ page contentType="image/jpeg" import="net.lx.common.vcode.*"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	RandImgCreater rc = new RandImgCreater(response);
	String rand = rc.createRandImage();
	session.setAttribute("checkrand", rand.toLowerCase());
	out.clear();
	out = pageContext.pushBody();
%>
