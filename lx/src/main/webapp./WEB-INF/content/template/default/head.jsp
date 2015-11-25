<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title></title>
	    <jc:plugin name="jquery"/>
	    <jc:plugin name="default"/>
		<jc:plugin name="time"/> 
		<jc:plugin name="loading" />
		<style type="text/css">
			img{border-width: 0px;}
			.righttxt_state{font-family:'宋体'; font-size:10; color:#FF0000;}
			.leftimg{padding:0;margin:0;float:left;height:12px;line-height:12px;cursor:hand;}
			.righttxt{padding:3px;margin:0;float:left;height:12px;line-height:12px; font:12px; cursor:hand;}
			.righttxt1{padding:3px;margin:0;float:left;height:12px;line-height:12px; font:12px; cursor:hand;background-color:yellow}
			.message_right{float:right;line-height:390px;width:100%; font:12px; font-style:bold; text-align:left;padding:1px;list-style:none;}
			#message_img{float:left;width:20%;}	
		</style>
		<script type="text/javascript">
			 //退出
			function messageExit(){
				parent.location.href='<s:url value="/template/manage_log_out" />';
				
			}
			function push(){
				jQuery.ajax({
				  	url: "<%=Constants.WEB_PATH %>/timing/push_server",
				    success: function(data,textStatus) { 
				   	 	//alert(textStatus);
					}
				});
			}
			$(document).ready(function(){
				//10分
				//ajax_100_1();
				setInterval(ajax_100_1,1000*60*5);
			});
			function task_count_callback(data)
			{
			}
		</script>
</head>
	
	<body style="overflow: hidden;">
  		<div id="container" style="width:100%;">
  			<div id="header-admin">
	  			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
	  				<tr>
			  			<td width="460" align="left">
			  				<h1>留学芒果</h1>
			  			</td>
			  			<td>
			  				<div class="title_home"></div>
			  			</td>
			  			<td width="80%" align="right" valign="top"> 	
							<div id="headDiv" style="width: 100%; margin-top:3px;text-align: right;">
								<div id="headDivFullName" style="float: right;text-align: right;width: 100%;margin-right: 2px;margin-top: 5px;font-size: 12px; color:white;">
		                			 您好， <b>
		                			${users.first_name }
		                			</b><br />
									<font id="clock" style="font-size: 12px; color:white"></font>
									<font id="relogin"></font>
								</div>
							</div>
			  			</td>
			  			<td width="72" align="left" valign="top">
			  				<div id="headDiv" style="width: 100px;margin-top:2px;text-align: left;">
								<div id="headDivExit" style="float: left;text-align: right;width: 100%;margin-right: 5px;margin-top: 2px;cursor: hand;">
									<a href="#" onclick="messageExit();" target="main">
										<img border="0" style="cursor:hand;" src="<s:url value="/plugin/base_css/images/logout.gif" />" />
									</a>
								</div>
								
							</div>
			  			</td>
	  				</tr>
	  		</table>
	   </div>
	   <div class="header-title-admin" align="right">
	   <div style="float:left;width:92%">
	    <table width="100%">
	    	<tr>
	    		<td id="visitorChatPanl" align="left" style="width:180px; padding-left: 20px;">
	    		</td>
	    		<td id="messagePanl" align="left" style="padding: 2px;" id="tdMenu">
	    			<iframe id="weather_iframe" name="weather_iframe" scrolling="no" frameborder="0" src="http://m.weather.com.cn/m/firefox_home_2011/" style="height:20px; width:420px;background-color: yellow;padding-left: 15px" allowtransparency="true"> </iframe>
	    		</td>
	    	</tr>
	    </table>
	  </div>
	    <div class="header_menu" style="width:8%"><a class="menu" href="<%=Constants.WEB_PATH %>/template/default/main" target="main">主界面</a></div>
	   </div>
	   <div class="clear"></div>
   </div>    
  </body>
</html>
