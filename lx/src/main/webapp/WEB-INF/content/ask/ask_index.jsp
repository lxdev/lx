<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/ask/home";
	String _head = basePath + "/user/head";
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
		<jc:plugin name="ask_css"/>
		<jc:plugin name="public_css" />
		<style>
			.aw-content-wrap {
				margin: 0 15px;
				background-color: #fff;
				border: 1px solid #e6e6e6;
				border-radius: 4px;
			}
			.aw-main-content {
				padding: 0 0 20px;
				border-right: 1px solid #e6e6e6;
			}
			.aw-nav-tabs h2 {
				position: absolute;
				top: 16px;
				left: 20px;
				margin: 0px;
				font-size: 20px;
			}
			.aw-nav-tabs.active > li {
				float: right;
			}
			.aw-nav-tabs > li {
				margin: 0px 4px 0px 0px;
				font-size: 14px;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
		    	<li role="presentation" class="active"><a href="#div_student">问答首页</a></li>
		        <li role="presentation"><a href="#div_teacher">咨询专家</a></li>
		    </ul>
		    <div class="tab-content"> 
		      <div class="tab-pane active" id="div_student">
		      	<div class="container">
		      		<div class="row">
		      			<div class="clearfix aw-content-wrap">
		      				<div class="col-sm-12 col-md-9 aw-main-content">
		      					<!-- 新消息通知 -->
		      					<div class="aw-mod aw-notification-box hide" id="index_notification">
									<div class="mod-head common-head">
										<h2>
											<span class="pull-right">
												<a href="http://wenda.bootcss.com/setting/privacy/#notifications" class="text-color-999"><i class="icon icon-setting"></i> 通知设置</a>
											</span>
											<i class="icon icon-bell"></i>新通知<em class="badge badge-important" name="notification_unread_num"></em>
										</h2>
									</div>
									<div class="mod-body">
										<ul id="notification_list"></ul>
									</div>
									<div class="mod-footer clearfix">
										<a href="javascript:;" onclick="AWS.Message.read_notification(false, 0, false);" class="pull-left btn btn-mini btn-gray">我知道了</a>
										<a href="http://wenda.bootcss.com/notifications/" class="pull-right btn btn-mini btn-success">查看所有</a>
									</div>
								</div>
								<!-- end 新消息通知 -->
								<!-- Tab切换 -->
								<ul class="nav nav-tabs aw-nav-tabs active hidden-xs">
									<%--<li><a href="http://wenda.bootcss.com/sort_type-unresponsive">等待回复</a></li>
									<li><a href="http://wenda.bootcss.com/sort_type-hot__day-7" id="sort_control_hot">热门</a></li>
									<li><a href="http://wenda.bootcss.com/is_recommend-1">推荐</a></li>
									--%><li class="active"><a href="">最新</a></li>
			
									<h2 class="hidden-xs"><i class="icon icon-list"></i> 问答首页</h2>
								</ul>
								<!-- end Tab切换 -->
								<div class="aw-mod aw-explore-list">
									<div class="mod-body">
										<div class="aw-common-list">
											<%--<div class="aw-item recomend" data-topic-id="235,">
												<a class="aw-user-name hidden-xs" data-id="1" href="http://wenda.bootcss.com/people/admin" rel="nofollow"><img src="http://wenda.bootcss.com/uploads/avatar/000/00/00/01_avatar_max.jpg" alt=""></a>	
												<div class="aw-question-content">
													<h4>
																	<a href="http://wenda.bootcss.com/question/239">Bootstrap v3原创视频教程上线，回帖参与赢更多HTML5技术免费学习机会</a>
																</h4>
															<div class="pull-right hidden-xs contribute">
														<span class="pull-right text-color-999">贡献</span>	    
													    		    <a class="aw-user-name" data-id="903" href="http://wenda.bootcss.com/people/Rico" rel="nofollow"><img src="http://wenda.bootcss.com/static/common/avatar-mid-img.png" alt=""></a>
													    		    <a class="aw-user-name" data-id="892" href="http://wenda.bootcss.com/people/snbing" rel="nofollow"><img src="http://wenda.bootcss.com/static/common/avatar-mid-img.png" alt=""></a>
													    		</div>
															
													<p>
																	
																				<a href="http://wenda.bootcss.com/people/Rico" class="aw-user-name" data-id="903">Rico</a> 
															<span class="text-color-999">回复了问题 • 10 人关注 • 12 个回复 • 262 次浏览 • 1 天前				</span>
																				<span class="text-color-999 related-topic hide"> •  来自相关话题</span>
													</p>
												</div>
											</div>
											<div class="aw-item active" data-topic-id="247,248,249,">
												<a class="aw-user-name hidden-xs" data-id="900" href="http://wenda.bootcss.com/people/Jonathan" rel="nofollow"><img src="http://wenda.bootcss.com/static/common/avatar-max-img.png" alt=""></a>	
												<div class="aw-question-content">
													<h4>
																	<a href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>
																</h4>
															<a href="http://wenda.bootcss.com/question/250#!answer_form" class="pull-right text-color-999">回复</a>
															
													<p>
																	
																								<a href="http://wenda.bootcss.com/people/Jonathan" class="aw-user-name">Jonathan</a> 
															<span class="text-color-999">发起了问题 • 1 人关注 • 0 个回复 • 55 次浏览 • 2 天前 
															</span>
																				<span class="text-color-999 related-topic hide"> •  来自相关话题</span>
													</p>
													
														</div>
											</div>
											<div class="aw-item active" data-topic-id="237,">
												<a class="aw-user-name hidden-xs" data-id="870" href="http://wenda.bootcss.com/people/cwhz" rel="nofollow"><img src="http://wenda.bootcss.com/static/common/avatar-max-img.png" alt=""></a>	
												<div class="aw-question-content">
													<h4>
																	<a href="http://wenda.bootcss.com/question/241">这是一个测试</a>
																</h4>
															<a href="http://wenda.bootcss.com/question/241#!answer_form" class="pull-right text-color-999">回复</a>
															
													<p>
																	
																								<a href="http://wenda.bootcss.com/people/cwhz" class="aw-user-name">cwhz</a> 
															<span class="text-color-999">发起了问题 • 1 人关注 • 0 个回复 • 40 次浏览 • 6 天前 
															</span>
																				<span class="text-color-999 related-topic hide"> •  来自相关话题</span>
													</p>
													
														</div>
											</div>--%>
											<s:iterator value="askList">
												<div class="aw-item active" data-top-id="<s:property value="id"/>">
													<a class="aw-user-name hidden-xs" data-id="870" href="http://wenda.bootcss.com/people/cwhz" rel="nofollow">
														<img class="photo" src="<s:property value="ask_user.photo_url"/>" alt=""/>
													</a>
													<div class="aw-question-content">
														<h4><a href="../template/ask_detail?id=<s:property value="id"/>"><s:property value="title"/></a></h4>
														<s:if test="users != null && users.user_type == 2 && (reply_user_id == null || reply_user_id == 0 || reply_user_id == users.user_id)">
															<a href="../ask/my_ask_chart?id=<s:property value="id"/>" class="pull-right text-color-999">回复</a>
														</s:if>
														<p>
															<s:if test="reply_user != null"></s:if>
															<a href="../template/user_home?id=<s:property value="ask_user.user_id"/>" class="aw-user-name"><s:property value="ask_user.full_name"/></a> 
															<span class="text-color-999">
																发起了问题 • 
																<s:property value="ask_statis.total_attention"/>人关注 • 
																<s:property value="ask_statis.total_view"/>次浏览 • 
																<s:property value="created_date_ago"/>
															</span>
															<span class="text-color-999 related-topic hide"> •  来自相关话题</span>
														</p>
													</div>
												</div>
											</s:iterator>
										</div>
									</div>
									<%--<div class="mod-footer">
										<div class="page-control">
											<ul class="pagination pull-right">
												<li class="active"><a href="javascript:;">1</a></li>
												<li><a href="http://wenda.bootcss.com/sort_type-new__day-0__is_recommend-0__page-2">2</a></li>
												<li><a href="http://wenda.bootcss.com/sort_type-new__day-0__is_recommend-0__page-3">3</a></li>
												<li><a href="http://wenda.bootcss.com/sort_type-new__day-0__is_recommend-0__page-4">4</a></li>
												<li><a href="http://wenda.bootcss.com/sort_type-new__day-0__is_recommend-0__page-2">&gt;</a></li>
												<li><a href="http://wenda.bootcss.com/sort_type-new__day-0__is_recommend-0__page-13">&gt;&gt;</a></li>
											</ul>
										</div>
									</div>
								--%></div>
		      				</div>
		      				<!-- 侧边栏 -->
		      				<%--<div class="col-sm-12 col-md-3 aw-side-bar hidden-xs hidden-sm">
								<div class="aw-mod">
									<div class="mod-head">
										<h3>专题</h3>
									</div>
									<div class="mod-body">
										<ul>
															<li><a href="http://wenda.bootcss.com/feature/liepin">猎聘</a></li>
													</ul>
									</div>
								</div>
								<div class="aw-mod aw-text-align-justify">
									<div class="mod-head">
										<a href="http://wenda.bootcss.com/topic/channel-hot" class="pull-right">更多 &gt;</a>
										<h3>热门话题</h3>
									</div>
									<div class="mod-body">
															<dl>
												<dt class="pull-left aw-border-radius-5">
													<a href="http://wenda.bootcss.com/topic/测试"><img alt="" src="http://wenda.bootcss.com/static/common/topic-mid-img.png"></a>
												</dt>
												<dd class="pull-left">
													<p class="clearfix">
														<span class="topic-tag" data-id="237">
															<a href="http://wenda.bootcss.com/topic/测试" class="text">测试</a>
														</span>
													</p>
													<p><b>2</b> 个问题, <b>1</b> 人关注</p>
												</dd>
											</dl>
													<dl>
												<dt class="pull-left aw-border-radius-5">
													<a href="http://wenda.bootcss.com/topic/bootstrap"><img alt="" src="http://wenda.bootcss.com/static/common/topic-mid-img.png"></a>
												</dt>
												<dd class="pull-left">
													<p class="clearfix">
														<span class="topic-tag" data-id="5">
															<a href="http://wenda.bootcss.com/topic/bootstrap" class="text">bootstrap</a>
														</span>
													</p>
													<p><b>22</b> 个问题, <b>15</b> 人关注</p>
												</dd>
											</dl>
													<dl>
												<dt class="pull-left aw-border-radius-5">
													<a href="http://wenda.bootcss.com/topic/响应式Web设计"><img alt="" src="http://wenda.bootcss.com/static/common/topic-mid-img.png"></a>
												</dt>
												<dd class="pull-left">
													<p class="clearfix">
														<span class="topic-tag" data-id="249">
															<a href="http://wenda.bootcss.com/topic/响应式Web设计" class="text">响应式Web设计</a>
														</span>
													</p>
													<p><b>1</b> 个问题, <b>1</b> 人关注</p>
												</dd>
											</dl>
													<dl>
												<dt class="pull-left aw-border-radius-5">
													<a href="http://wenda.bootcss.com/topic/HTML"><img alt="" src="http://wenda.bootcss.com/static/common/topic-mid-img.png"></a>
												</dt>
												<dd class="pull-left">
													<p class="clearfix">
														<span class="topic-tag" data-id="248">
															<a href="http://wenda.bootcss.com/topic/HTML" class="text">HTML</a>
														</span>
													</p>
													<p><b>1</b> 个问题, <b>1</b> 人关注</p>
												</dd>
											</dl>
													<dl>
												<dt class="pull-left aw-border-radius-5">
													<a href="http://wenda.bootcss.com/topic/HTML5"><img alt="" src="http://wenda.bootcss.com/static/common/topic-mid-img.png"></a>
												</dt>
												<dd class="pull-left">
													<p class="clearfix">
														<span class="topic-tag" data-id="247">
															<a href="http://wenda.bootcss.com/topic/HTML5" class="text">HTML5</a>
														</span>
													</p>
													<p><b>1</b> 个问题, <b>1</b> 人关注</p>
												</dd>
											</dl>
													</div>
								</div>
								<div class="aw-mod aw-text-align-justify">
									<div class="mod-head">
										<a href="http://wenda.bootcss.com/people/" class="pull-right">更多 &gt;</a>
										<h3>热门用户</h3>
									</div>
									<div class="mod-body">
													
										<dl>
											<dt class="pull-left aw-border-radius-5">
												<a href="http://wenda.bootcss.com/people/zhoumeng780"><img alt="" src="http://wenda.bootcss.com/static/common/avatar-mid-img.png"></a>
											</dt>
											<dd class="pull-left">
												<a href="http://wenda.bootcss.com/people/zhoumeng780" data-id="879" class="aw-user-name">zhoumeng780						</a>
												<p class="signature"></p>
												<p><b>2</b> 个问题, <b>0</b> 次赞同</p>
											</dd>
										</dl>
											
										<dl>
											<dt class="pull-left aw-border-radius-5">
												<a href="http://wenda.bootcss.com/people/%E6%B5%AE%E7%99%BD"><img alt="" src="http://wenda.bootcss.com/static/common/avatar-mid-img.png"></a>
											</dt>
											<dd class="pull-left">
												<a href="http://wenda.bootcss.com/people/%E6%B5%AE%E7%99%BD" data-id="891" class="aw-user-name">浮白						</a>
												<p class="signature"></p>
												<p><b>2</b> 个问题, <b>0</b> 次赞同</p>
											</dd>
										</dl>
											
										<dl>
											<dt class="pull-left aw-border-radius-5">
												<a href="http://wenda.bootcss.com/people/Lover1nLynn"><img alt="" src="http://wenda.bootcss.com/static/common/avatar-mid-img.png"></a>
											</dt>
											<dd class="pull-left">
												<a href="http://wenda.bootcss.com/people/Lover1nLynn" data-id="887" class="aw-user-name">Lover1nLynn						</a>
												<p class="signature"></p>
												<p><b>1</b> 个问题, <b>0</b> 次赞同</p>
											</dd>
										</dl>
											
										<dl>
											<dt class="pull-left aw-border-radius-5">
												<a href="http://wenda.bootcss.com/people/oranzxc"><img alt="" src="http://wenda.bootcss.com/uploads/avatar/000/00/08/44_avatar_mid.jpg"></a>
											</dt>
											<dd class="pull-left">
												<a href="http://wenda.bootcss.com/people/oranzxc" data-id="844" class="aw-user-name">oranzxc						</a>
												<p class="signature"></p>
												<p><b>1</b> 个问题, <b>0</b> 次赞同</p>
											</dd>
										</dl>
											
										<dl>
											<dt class="pull-left aw-border-radius-5">
												<a href="http://wenda.bootcss.com/people/siuyanyan"><img alt="" src="http://wenda.bootcss.com/static/common/avatar-mid-img.png"></a>
											</dt>
											<dd class="pull-left">
												<a href="http://wenda.bootcss.com/people/siuyanyan" data-id="890" class="aw-user-name">siuyanyan						</a>
												<p class="signature"></p>
												<p><b>1</b> 个问题, <b>0</b> 次赞同</p>
											</dd>
										</dl>
													</div>
								</div>				
							</div>
		      				--%><!-- end 侧边栏 -->
		      			</div>
		      		</div>
		      	</div>
		      </div>
		      <div class="tab-pane" id="div_teacher">
		      		咨询专家
		      </div>
		    </div>
		</div>
	
		<jc:plugin name="bootstrap_login"/>
		<script>
			$(function () {
				$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		        }) 
			});
		</script>
	</body>
</html>
