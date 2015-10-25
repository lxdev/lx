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
			h2 { color: #000000 !important; }
		</style>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
    	<div class="aw-container-wrap">
    		<div class="container">
		<div class="row">
			<div class="aw-content-wrap clearfix">
				<div class="col-sm-12 col-md-9 aw-main-content">
					<!-- 话题推荐bar -->
										<!-- 话题推荐bar -->
					<!-- 话题bar -->
					<%--<div class="aw-mod aw-topic-bar" id="question_topic_editor" data-type="question" data-id="250">
						<div class="tag-bar clearfix">
														<span class="topic-tag" data-id="247">
								<a href="http://wenda.bootcss.com/topic/HTML5" class="text">HTML5</a>
							</span>
														<span class="topic-tag" data-id="248">
								<a href="http://wenda.bootcss.com/topic/HTML" class="text">HTML</a>
							</span>
														<span class="topic-tag" data-id="249">
								<a href="http://wenda.bootcss.com/topic/%E5%93%8D%E5%BA%94%E5%BC%8FWeb%E8%AE%BE%E8%AE%A1" class="text">响应式Web设计</a>
							</span>
							
													</div>
					</div>
					--%><!-- end 话题bar -->
					<div class="aw-mod aw-question-detail aw-item">
						<div class="mod-head">
							<h1>
								<s:property value="entity.title" />							</h1>

													</div>
						<div class="mod-body">
							<div class="content markitup-box">
								<s:property value="entity.body"/>
								
															</div>
													</div>
						<div class="mod-footer">
							<div class="meta">
								<span class="text-color-999"><s:property value="entity.created_date"/></span>

								<a data-id="250" data-type="question" class="aw-add-comment text-color-999 " data-comment-count="0"><i class="icon icon-comment"></i>添加评论</a>

								
																
																<div class="pull-right more-operate">
									<a class="text-color-999 dropdown-toggle" data-toggle="dropdown">
										<i class="icon icon-share"></i>分享									</a>
									<div aria-labelledby="dropdownMenu" role="menu" class="aw-dropdown shareout pull-right">
										<ul class="aw-dropdown-list">
											<li><a onclick="AWS.User.share_out('tsina');"><i class="icon icon-weibo"></i> 新浪微博</a></li>
											<li><a onclick="AWS.User.share_out('qzone');"><i class="icon icon-qzone"></i> QZONE</a></li>
											<li><a onclick="AWS.User.share_out('weixin');"><i class="icon icon-wechat"></i> 微信</a></li>
										</ul>
									</div>

																	</div>
							</div>
						</div>
						<!-- 站内邀请 -->
						<div class="aw-invite-box hide" style="display: none;">
							<div class="mod-head clearfix">
								<div class="search-box pull-left">
									<input id="invite-input" class="form-control" type="text" placeholder="搜索你想邀请的人...">
									<div class="aw-dropdown">
										<p class="title">没有找到相关结果</p>
										<ul class="aw-dropdown-list"></ul>
									</div>
									<i class="icon icon-search"></i>
								</div>
								<div class="invite-list pull-left hide">
									已邀请:
																	</div>
							</div>
													</div>
						<!-- end 站内邀请 -->
						<!-- 相关链接 -->
						<div class="aw-question-related-box hide" style="display: none;">
							<form action="http://wenda.bootcss.com/publish/ajax/save_related_link/" method="post" onsubmit="return false" id="related_link_form">
								<div class="mod-head">
									<h2>与内容相关的链接</h2>
								</div>
								<div class="mod-body clearfix">
									<input type="hidden" name="item_id" value="250">
									<input type="text" class="form-control pull-left" name="link" value="http://">

									<a onclick="AWS.ajax_post($('#related_link_form'));" class="pull-left btn btn-success">提交</a>
								</div>
							</form>
						</div>
						<!-- end 相关链接 -->
					</div>

										<div class="aw-mod aw-question-comment">
						<div class="mod-head">
							<ul class="nav nav-tabs aw-nav-tabs active">
								
								<h2 class="hidden-xs"><s:property value="entity.ask_statis.total_reply" />个回复</h2>
							</ul>
						</div>
						<div class="mod-body aw-feed-list">
													</div>
						<div class="mod-footer">
															<div class="aw-load-more-content hide" id="load_uninterested_answers">
									<span class="text-color-999 aw-alert-box text-color-999" href="javascript:;" tabindex="-1" onclick="AWS.alert('被折叠的回复是被你或者被大多数用户认为没有帮助的回复');">为什么被折叠?</span>
									<a href="javascript:;" class="aw-load-more-content"><span class="hide_answers_count">0</span> 个回复被折叠</a>
								</div>

								<div class="hide aw-feed-list" id="uninterested_answers_list"></div>
													</div>

											</div>
										<!-- end 问题详细模块 -->

										<!-- 回复编辑器 -->
					<div class="aw-mod aw-replay-box">
						<a name="answer_form"></a>
						<p align="center">要回复问题请先<a href="../user/login">登录</a>或<a href="../user/register_student">注册</a></p>
					</div>
					<!-- end 回复编辑器 -->
									</div>
				<!-- 侧边栏 -->
				<div class="col-md-3 aw-side-bar hidden-xs hidden-sm">
					<!-- 发起人 -->
					<div class="aw-mod">
						<div class="mod-head">
							<h3>发起人</h3>
						</div>
						<div class="mod-body">
							<dl>
								<dt class="pull-left aw-border-radius-5">
									<a href="../template/user_home?id=<s:property value="entity.ask_user.user_id"/>"><img class="photo_32" alt="<s:property value="entity.ask_user.full_name"/>" src="<s:property value="entity.ask_user.photo_url"/>"></a>
								</dt>
								<dd class="pull-left">
									<a class="aw-user-name" href="../template/user_home?id=<s:property value="entity.ask_user_id"/>" data-id="900"><s:property value="entity.ask_user.full_name"/></a>
									<p></p>
								</dd>
							</dl>
						</div>
					</div>
					<!-- end 发起人 -->
					<!-- 相关问题 -->
					<div class="aw-mod">
						<%--<div class="mod-head">
							<h3>相关问题</h3>
						</div>
						<div class="mod-body font-size-12">
							<ul>
								<li><a href="http://wenda.bootcss.com/question/29">使用可视化布局生成的代码，网页和设计的不同</a></li>
							</ul>
						</div>--%>
					</div>
					<!-- end 相关问题 -->
					
					<!-- 问题状态 -->
					<div class="aw-mod question-status">
						<div class="mod-head">
							<h3>问题状态</h3>
						</div>
						<div class="mod-body">
							<ul>
								<li>浏览: <span class="aw-text-color-blue"><s:property value="entity.ask_statis.total_view"/></span></li>
								<li>关注: <span class="aw-text-color-blue"><s:property value="entity.ask_statis.total_attention"/></span> 人</li>
								<s:iterator value="entity.ask_replies">
									<s:if test="reply_type == 3">
									<li class="aw-border-radius-5" id="focus_users">
										<a href="../template/user_home?id=<s:property value="reply_user.user_id"/>">
											<img class="photo_25" src="<s:property value="reply_user.photo_url"/>" class="aw-user-name" data-id="900" alt="<s:property value="reply_user.full_name"/>"></a> </li>
									</s:if>
								</s:iterator>
								
							</ul>
						</div>
					</div>
					<!-- end 问题状态 -->
				</div>
				<!-- end 侧边栏 -->
			</div>
		</div>
	</div>
    	</div>
	
		<jc:plugin name="bootstrap_login"/>
		<script>
		</script>
	</body>
</html>
