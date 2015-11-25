<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">    
    <jc:plugin name="style"/>
</head>  
<body id="switchPage">
	<a id="stiwch" class="turnOff" href="<s:url value="/template/switch" />#"></a>
	<SCRIPT type=text/javascript>
			var toggle = document.getElementById('stiwch');
			toggle.onclick = function() {
				if (top.document.getElementById) {
					if (this.className == 'turnOff') {
						top.document.getElementById('mainCont').cols = "0,10,*";
						this.className = 'turnOn';
					}
					else {
						top.document.getElementById('mainCont').cols = "200,10,*";
						this.className = 'turnOff';
					}
				}
			};
		</SCRIPT>
</body>
</html>
