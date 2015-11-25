<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
	<head>
	    <title></title>
	    <jc:plugin name="jquery"/>
	    <jc:plugin name="loading"/>
	    <jc:plugin name="default"/>
    </head>
		
	<body >

	<div class=listbox style="margin-left: 8px;margin-top: 2px;">
			<div class=rightbox>
				<span style="float:left;">
					
					<img style="MARGIN-TOP: 3px; FLOAT: left; MARGIN-LEFT: 3px" border=0 src="<ui:img url='/images/icon_sy.gif'/>">
				</span>
				<!--<span style="float: right;"><a href="#" id="stiwch_a" class="turnOff"  style="padding-right: 20px;">全屏</a></span>
			--></div>
			<div class=boxline>
				<div class=main_jsmes>
					<div class=div_main>
						<iframe width="100%" height="655px"  frameborder="0" id="home_sch" name="home_sch" marginheight="0" marginwidth="0" src="<%=Constants.WEB_PATH %>/manage/school_calendar_view_home" target="_self" allowtransparency="true"></iframe>
					</div>
				</div>
			</div>
	</div>
  </body>
</html>
