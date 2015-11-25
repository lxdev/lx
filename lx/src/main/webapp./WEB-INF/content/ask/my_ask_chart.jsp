<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/ask/home";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>留学芒果</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>
		<jc:plugin name="font-awesome"/>
		<style>
			.widget-container { min-height: 320px; background: white; box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1); }
			.chat { padding-bottom: 52px; }
			.widget-container.scrollable { position: relative; height: 400px; padding-top: 50px; }
			.widget-container .heading {background: rgba(255, 255, 255, 0.94);height: 50px;padding: 15px 15px;color: #007aff;font-size: 15px;width: 100%;font-weight: 400;margin: 0;}
			.widget-container.scrollable .heading {position: absolute;top: 0;left: 0;z-index: 10;}
			
			.chat .widget-content {padding-right: 20px;background-color: white;}
			.widget-container.scrollable .widget-content {height: 100%;position: relative;overflow-y: auto;-webkit-overflow-scrolling: touch;}
			ul, ol {margin-top: 0;margin-bottom: 10px;}
			.chat .widget-content ul {list-style: none;padding: 0;}
			.chat .widget-content ul li {margin-bottom: 14px;padding-left: 40px;padding-right: 80px;position: relative;}
			.chat .widget-content ul li .bubble {padding: 12px 15px;background: #e5e5ea;border-radius: 15px;position: relative;}
			.chat .widget-content ul li .bubble .user-name {font-size: 1.1em;margin-bottom: 8px;display: inline-block;}
			.chat .widget-content ul li .bubble p.message {font-size: 0.95em;margin-bottom: 12px;color: #333333;}
			p {margin: 0 0 15px;}
			.chat .widget-content ul li .bubble .time {font-size: 0.8em;color: #888888;margin-bottom: 0;}
			
			.chat .widget-content ul li {margin-bottom: 14px;padding-left: 40px;padding-right: 80px;position: relative;}
			.chat .widget-content ul li.current-user {padding-right: 40px;padding-left: 80px;}
			.chat .widget-content ul li.current-user .bubble {background: #178efe;}
			.chat .widget-content ul li.current-user p, .chat .widget-content ul li.current-user a {color: white !important;}
			.chat .widget-content ul li.current-user .time {opacity: 0.75;}
		</style>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container-fluid">
			<div class="template-page-wrapper">
				<s:action namespace="/user" name="left" executeResult="true"/>
				<div class="templatemo-content-wrapper">
        			<div class="templatemo-content">
    					<div class="panel panel-primary">
    						<div class="col-md-6">
					            <div class="widget-container scrollable chat" style="height: 427px;">
					              <div class="heading">
					                <i class="icon-comments"></i>问题：<i class="icon-smile pull-right"></i>
					                <span>标题:</span><s:property value="entity.title"/>
					                <span>内容:</span><s:property value="entity.body"/>
					              </div>
					              <div class="widget-content padded">
					                <ul id="ul_messages">
					                	<s:iterator value="myAskMessageList">
					                		<li <s:if test="send_user_id==users.user_id">class="current-user"</s:if> >
							                    <img width="30" height="30" src="images/avatar-male.jpg">
							                    <div class="bubble">
							                      <a class="user-name" href="#"><s:property value="send_user。full_name"/></a>
							                      <p class="message">
							                      	<s:property value="body"/>
							                      </p>
							                      <p class="time">
							                        <strong><s:property value="send_date"/></strong>
							                      </p>
							                    </div>
							                </li>
					                	</s:iterator>
					                  <%--<li>
					                    <img width="30" height="30" src="images/avatar-male.jpg">
					                    <div class="bubble">
					                      <a class="user-name" href="#">管理员</a>
					                      <p class="message">
					                          有些人离开了之后才发现，离开的人是自己的最爱。——《东邪西毒》
					                      </p>
					                      <p class="time">
					                        <strong>今天 </strong>下午 3:53
					                      </p>
					                    </div>
					                  </li>
					                  <li class="current-user">
					                    <img width="30" height="30" src="images/avatar-female.jpg">
					                    <div class="bubble">
					                      <a class="user-name" href="#">王晓</a>
					                      <p class="message">
					                          尽管你一脸不真诚，但我听着很高兴。——《梦想照进现实》
					                      </p>
					                      <p class="time">
					                        <strong>今天 </strong>下午 3:53
					                      </p>
					                    </div>
					                  </li>
					                  <li>
					                    <img width="30" height="30" src="images/avatar-male.jpg">
					                    <div class="bubble">
					                      <a class="user-name" href="#">管理员</a>
					                      <p class="message">
					                          眼泪是我的望远镜。透过它，我见到天堂。——《爱的太迟》
					                      </p>
					                      <p class="time">
					                        <strong>今天 </strong>下午 3:53
					                      </p>
					                    </div>
					                  </li>
					                  <li>
					                    <img width="30" height="30" src="images/avatar-male.jpg">
					                    <div class="bubble">
					                      <a class="user-name" href="#">管理员</a>
					                      <p class="message">
					                          那些流泪的人真的是为了爱吗？ ——《盛夏光年》
					                      </p>
					                      <p class="time">
					                        <strong>今天 </strong>下午 3:53
					                      </p>
					                    </div>
					                  </li>--%>
					                </ul>
					              </div>
					              <div class="post-message">
					                <input class="form-control" placeholder="请输入您需要发送的信息…" type="text" id="content">
					                <input type="submit" value="发送" onclick="send()">
					              </div>
					            </div>
					    	</div>
    					</div>
        			</div>
        		</div>
		    </div>
		</div>
	
		<script type="text/javascript">
			var remove = function(obj){
				var id = $(obj).attr("data-id");
				alert(id);
				return;
				var url = '<s:url value="/ask/my_ask_remove"/>';
	            var option = { id: id };
	            jQuery.post(url,option,
	                    function(data)
	                    {
	                        if(data.message != ""){
	                            alert(data.message);
	                            return;
	                        }
	                        alert("删除成功！");
	                        $(obj).parents("tr").remove();
	                    },
	             "json");
			}
			var send = function(){
				if($.trim($("#content").val()) == ""){
					alert("请输入内容！");
					return;
				}
				jQuery.post('<s:url value="/ask/chart_send"/>',{ 'askMessage.ask_id': <s:property value="entity.id"/>, 'askMessage.body':$("#content").val()},
				        function(data)
				    	{
				    		var lists="";
				    		if(null!=data.askMessage){
				    			var msgHtml = '<li class="current-user">' +
			                    '<img width="30" height="30" src="images/avatar-female.jpg">' +
			                    '<div class="bubble">' + 
			                      '<a class="user-name" href="#">' + data.askMessage.send_user.full_name + '</a>' +
			                      '<p class="message">' + data.askMessage.body + '</p>' +
			                      '<p class="time"><strong>' + data.askMessage.send_date + '</strong></p>' +
			                    '</div>' +
			                  	'</li>';
				    			$("#ul_messages").append(msgHtml);
				    		}
				    	},
				 "json");
			}
		</script>
	</body>
</html>
