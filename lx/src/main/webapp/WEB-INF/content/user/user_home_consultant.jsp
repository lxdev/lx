<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/home";
	Object user_type = request.getAttribute("users.user_type");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>留学芒果-<s:property value="full_name"/>的首页</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>
		<jc:plugin name="ask_css"/>
		<jc:plugin name="public_css" />
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
                    <!-- 用户数据内容 -->
                    <div class="aw-mod aw-user-detail-box">
                        <div class="mod-head">
                            <img src="http://wenda.bootcss.com/static/common/avatar-max-img.png" alt="Jonathan">
                            <span class="pull-right operate">
                                                            </span>
                            <h1>Jonathan </h1>
                            <p class="text-color-999"></p>
                            <p class="aw-user-flag">
                                                                                                                            </p>
                        </div>
                        <div class="mod-body">
                            <div class="meta">
                                <span><i class="icon icon-prestige"></i> 威望 : <em class="aw-text-color-green">0</em></span>
                                <span><i class="icon icon-score"></i> 积分 : <em class="aw-text-color-orange">1980</em></span>                                <span><i class="icon icon-agree"></i> 赞同 : <em class="aw-text-color-orange">0</em></span>
                                <span><i class="icon icon-thank"></i> 感谢 : <em class="aw-text-color-orange">0</em></span>
                            </div>
                            
                        </div>
                        <div class="mod-footer">
                            <ul class="nav nav-tabs aw-nav-tabs hidden-xs">
                                <li class="active"><a href="#overview" id="page_overview" data-toggle="tab">概述</a></li>
                                <li><a href="#questions" id="page_questions" data-toggle="tab">发问<span class="badge">1</span></a></li>
                                <li><a href="#answers" id="page_answers" data-toggle="tab">回复<span class="badge">0</span></a></li>
                                <li><a href="#articles" id="page_articles" data-toggle="tab">文章</a></li>
                                <li><a href="#focus" id="page_focus" data-toggle="tab">关注</a></li>
                                <li><a href="#actions" id="page_actions" data-toggle="tab">动态</a></li>
                                <li><a href="#detail" id="page_detail" data-toggle="tab">详细资料</a></li>
                                                            </ul>
                        </div>
                    </div>
                    <!-- end 用户数据内容 -->

                    <div class="aw-user-center-tab">
                        <div class="tab-content">
                            <div class="tab-pane active" id="overview">
                                <!-- 回复 -->
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3><a class="pull-right aw-more-content" href="javascript:;" onclick="$('#page_answers').click();">更多 »</a>回复</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-profile-answer-list">
                                                                                        <p class="padding10 text-center">没有内容</p>
                                                                                    </div>
                                    </div>
                                </div>
                                <!-- end 回复 -->

                                <!-- 发问 -->
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3><a class="pull-right aw-more-content" href="javascript:;" onclick="$('#page_questions').click();">更多 »</a>发问</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-profile-publish-list">
                                                                                                                                            <div class="aw-item">
                                                    <div class="mod-head">
                                                        <h4><a href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a></h4>
                                                    </div>
                                                    <div class="mod-body">
                                                        <span class="aw-border-radius-5 count pull-left"><i class="icon icon-reply"></i>0</span>
                                                        <p class="aw-hide-txt">218 次浏览 &nbsp;• 1 个关注 &nbsp; • 2015-03-27</p>
                                                    </div>
                                                </div>
                                                                                                                                    </div>
                                    </div>
                                </div>
                                <!-- end 发问 -->

                                <!-- 最新动态 -->
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3><a class="pull-right aw-more-content" href="javascript:;" onclick="$('#page_actions').click();">更多 »</a>动态</h3>
                                    </div>
                                    <div class="mod-body">
                                        <ul>
                                                                                        <p class="padding10 text-center">没有内容</p>
                                                                                    </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="questions">
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3>发问</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-profile-publish-list" id="contents_user_actions_questions">	
<div class="aw-item">
	<div class="aw-mod">
		<div class="mod-head">
			<h4 class="aw-hide-txt">
				<a href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>
			</h4>
		</div>
		<div class="mod-body">
			<span class="aw-border-radius-5 count pull-left"><i class="icon icon-reply"></i>0</span>
			<p class="text-color-999">218 次浏览 • 1 个关注</p>
			<p class="text-color-999">2015-03-27 17:54</p>
		</div>
	</div>
</div>
</div>
                                    </div>
                                    <div class="mod-footer">
                                        <!-- 加载更多内容 -->
                                        <a class="aw-load-more-content" id="bp_user_actions_questions_more" data-page="1">
                                            <span>更多</span>
                                        </a>
                                        <!-- end 加载更多内容 -->
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="answers">
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3>回复</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-profile-answer-list" id="contents_user_actions_answers"><p style="padding: 15px 0" align="center">没有内容</p></div>
                                    </div>
                                    <div class="mod-footer">
                                        <!-- 加载更多内容 -->
                                        <a class="aw-load-more-content disabled" id="bp_user_actions_answers_more" data-page="0">
                                            <span>没有更多了</span>
                                        </a>
                                        <!-- end 加载更多内容 -->
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="articles">
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3>文章</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-profile-publish-list" id="contents_user_actions_articles"><p style="padding: 15px 0" align="center">没有内容</p></div>
                                    </div>
                                    <div class="mod-footer">
                                        <!-- 加载更多内容 -->
                                        <a class="aw-load-more-content disabled" id="bp_user_actions_articles_more" data-page="0">
                                            <span>没有更多了</span>
                                        </a>
                                        <!-- end 加载更多内容 -->
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="focus">
                                <!-- 自定义切换 -->
                                <div class="aw-mod">
                                    <div class="aw-tabs text-center">
                                        <ul>
                                            <li class="active"><a>关注的人</a></li>
                                            <li><a>关注者</a></li>
                                            <li><a>关注的话题</a></li>
                                        </ul>
                                    </div>
                                    <div class="mod-body">
                                        <div class="aw-tab-content">
                                            <div class="aw-mod aw-user-center-follow-mod">
                                                <div class="mod-body">
                                                    <ul id="contents_user_follows" class="clearfix"><p style="padding: 15px 0" align="center">没有内容</p></ul>
                                                </div>
                                                <div class="mod-footer">
                                                    <!-- 加载更多内容 -->
                                                    <a class="aw-load-more-content disabled" id="bp_user_follows_more" data-page="0">
                                                        <span>没有更多了</span>
                                                    </a>
                                                    <!-- end 加载更多内容 -->
                                                </div>
                                            </div>
                                            <div class="aw-mod aw-user-center-follow-mod hide">
                                                <div class="mod-body">
                                                    <ul class="clearfix" id="contents_user_fans"><p style="padding: 15px 0" align="center">没有内容</p></ul>
                                                </div>
                                                <div class="mod-footer">
                                                    <!-- 加载更多内容 -->
                                                    <a class="aw-load-more-content disabled" id="bp_user_fans_more" data-page="0">
                                                        <span>没有更多了</span>
                                                    </a>
                                                    <!-- end 加载更多内容 -->
                                                </div>
                                            </div>
                                            <div class="aw-mod aw-user-center-follow-mod hide">
                                                <div class="mod-body">
                                                    <ul id="contents_user_topics" class="clearfix"><li>
	<div class="mod-head">
		<a class="aw-topic-img pull-left aw-border-radius-5" data-id="247" href="http://wenda.bootcss.com/topic/HTML5">
			<img src="http://wenda.bootcss.com/static/common/topic-mid-img.png" alt="HTML5">
		</a>
		<p><a class="aw-topic-name" data-id="247" href="http://wenda.bootcss.com/topic/HTML5"><span>HTML5</span></a></p>
	</div>
	<div class="mod-footer">
		<p class="aw-user-center-follow-meta">
			1 个讨论			 • 
			1 个关注		</p>
	</div>
</li>
<li>
	<div class="mod-head">
		<a class="aw-topic-img pull-left aw-border-radius-5" data-id="248" href="http://wenda.bootcss.com/topic/HTML">
			<img src="http://wenda.bootcss.com/static/common/topic-mid-img.png" alt="HTML">
		</a>
		<p><a class="aw-topic-name" data-id="248" href="http://wenda.bootcss.com/topic/HTML"><span>HTML</span></a></p>
	</div>
	<div class="mod-footer">
		<p class="aw-user-center-follow-meta">
			1 个讨论			 • 
			1 个关注		</p>
	</div>
</li>
<li>
	<div class="mod-head">
		<a class="aw-topic-img pull-left aw-border-radius-5" data-id="249" href="http://wenda.bootcss.com/topic/%E5%93%8D%E5%BA%94%E5%BC%8FWeb%E8%AE%BE%E8%AE%A1">
			<img src="http://wenda.bootcss.com/static/common/topic-mid-img.png" alt="响应式Web设计">
		</a>
		<p><a class="aw-topic-name" data-id="249" href="http://wenda.bootcss.com/topic/%E5%93%8D%E5%BA%94%E5%BC%8FWeb%E8%AE%BE%E8%AE%A1"><span>响应式Web设计</span></a></p>
	</div>
	<div class="mod-footer">
		<p class="aw-user-center-follow-meta">
			1 个讨论			 • 
			1 个关注		</p>
	</div>
</li>
			
</ul>
                                                </div>
                                                <div class="mod-footer">
                                                    <!-- 加载更多内容 -->
                                                    <a class="aw-load-more-content" id="bp_user_topics_more" data-page="1">
                                                        <span>更多</span>
                                                    </a>
                                                    <!-- end 加载更多内容 -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end 自定义切换 -->
                            </div>
                            <div class="tab-pane" id="actions">
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3>最新动态</h3>
                                    </div>
                                    <div class="mod-body">
                                        <div id="contents_user_actions"><div class="aw-item">
	<p>
		<span class="pull-right text-color-999">2015-03-27</span>
		<em class="pull-left"><a href="http://wenda.bootcss.com/people/900" class="aw-user-name" data-id="900">Jonathan</a> 发起了问题:</em>
		
		<a class="aw-hide-txt" href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>

	</p>
</div>
<div class="aw-item">
	<p>
		<span class="pull-right text-color-999">2015-03-27</span>
		<em class="pull-left"><a href="http://wenda.bootcss.com/people/900" class="aw-user-name" data-id="900">Jonathan</a> 将该问题添加到 <a href="http://wenda.bootcss.com/topic/HTML5" class="aw-topic-name" data-id="247">HTML5</a> 话题:</em>
		
		<a class="aw-hide-txt" href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>

	</p>
</div>
<div class="aw-item">
	<p>
		<span class="pull-right text-color-999">2015-03-27</span>
		<em class="pull-left"><a href="http://wenda.bootcss.com/people/900" class="aw-user-name" data-id="900">Jonathan</a> 将该问题添加到 <a href="http://wenda.bootcss.com/topic/HTML" class="aw-topic-name" data-id="248">HTML</a> 话题:</em>
		
		<a class="aw-hide-txt" href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>

	</p>
</div>
<div class="aw-item">
	<p>
		<span class="pull-right text-color-999">2015-03-27</span>
		<em class="pull-left"><a href="http://wenda.bootcss.com/people/900" class="aw-user-name" data-id="900">Jonathan</a> 将该问题添加到 <a href="http://wenda.bootcss.com/topic/%E5%93%8D%E5%BA%94%E5%BC%8FWeb%E8%AE%BE%E8%AE%A1" class="aw-topic-name" data-id="249">响应式Web设计</a> 话题:</em>
		
		<a class="aw-hide-txt" href="http://wenda.bootcss.com/question/250">响应式布局 [翻译]－Treehouse 免费课程 </a>

	</p>
</div>
</div>
                                    </div>
                                    <div class="mod-footer">
                                        <!-- 加载更多内容 -->
                                        <a class="aw-load-more-content" id="bp_user_actions_more" data-page="1">
                                            <span>更多</span>
                                        </a>
                                        <!-- end 加载更多内容 -->
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="detail">
                                <div class="aw-mod">
                                    <div class="mod-head">
                                        <h3>详细资料</h3>
                                    </div>
                                    <div class="mod-body aw-user-center-details">
                                                                            <dl>
                                        <dt><span>个人成就:</span></dt>
                                        <dd>
                                            <p class="meta">
                                                <span><i class="icon icon-prestige"></i>威望: <em class="aw-text-color-green">0</em></span>
                                                                                                <span><i class="icon icon-score"></i>积分: <em class="aw-text-color-orange">1980</em></span>
                                                                                                <span><i class="icon icon-agree"></i>赞同: <em class="aw-text-color-orange">0</em></span>
                                                <span><i class="icon icon-thank"></i>感谢: <em class="aw-text-color-orange">0</em></span>
                                            </p>
                                        </dd>
                                    </dl>
                                    
                                    
                                    
                                                                        <dl>
                                        <dt><span>最后活跃:</span></dt>
                                        <dd>2015-03-27 23:59</dd>
                                    </dl>
                                    
                                                                        </div>
                                </div>
                            </div>
                                                    </div>
                    </div>
                </div>

                <!-- 侧边栏 -->
                <div class="col-sm-12 col-md-3 aw-side-bar">
                    <div class="aw-mod people-following">
                        <div class="mod-body">
                            <a onclick="$('#page_focus').click();$('#focus .aw-tabs ul li').eq(0).click();$.scrollTo($('#focus').offset()['top'], 600, {queue:true})" class="pull-right font-size-12">更多 »</a>
                            <span>
                                关注 <em class="aw-text-color-blue">0</em> 人                            </span>
                                                    </div>
                    </div>
                    <div class="aw-mod people-following">
                        <div class="mod-body">
                            <a onclick="$('#page_focus').click();$('#focus .aw-tabs ul li').eq(1).click();$.scrollTo($('#focus').offset()['top'], 600, {queue:true})" class="pull-right font-size-12">更多 »</a>
                            <span>
                                被 <em class="aw-text-color-blue">0</em> 人关注                            </span>

                                                    </div>
                        
                    </div>
                    <div class="aw-mod people-following">
                        <div class="mod-body">
                            关注 <em class="aw-text-color-blue">3</em> 话题                                                        <div class="aw-topic-bar">
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
                                                    </div>
                    </div>
                    <div class="aw-mod">
                        <div class="mod-body">
                            <span class="aw-text-color-666">
                                主页访问量 : 57 次访问                            </span>
                        </div>
                    </div>
                </div>
                <!-- end 侧边栏 -->
            </div>
        </div>
    </div>
</div>
	</body>
</html>
