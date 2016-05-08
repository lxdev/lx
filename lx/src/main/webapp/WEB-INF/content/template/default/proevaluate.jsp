<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>专家评估</title>

	
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
	</head>
  	<body>
	    <s:action namespace="/user" name="head" executeResult="true"/>	    	   
	    <div class="clear"></div>
	    <div class="banner-1">
        <div class="evaluateregister"><div class="apply">仅限美国硕、博申请<a href="http://lxmango_com.mikecrm.com/uGAdxd"  target="_blank"  class="btn-1">立即报名</a></div></div>
    </div>
    <div class="banner-2">
        <h2>留学专家+大数据技术 帮你轻松锁定目标学校</h2>
        <div class="circle">
            <div class="c-1">
                <img src="../plugin/new/images/circle-1.png" />
                <p>定位准确率提升</p>
            </div>
            <div class="c-2">
                <img src="../plugin/new/images/circle-2.png" />
                <p>录取成功率提升</p>
            </div>
            <div class="c-3">
                <img src="../plugin/new/images/circle-3.png" />
                <p>申请效率提升</p>
            </div>
        </div>
    </div>
    <div class="banner-3">
        <h2>选校定位的这些坑 我们帮你避掉</h2>
        <div class="content">
            <div class="c-1">
                <img src="../plugin/new/images/banner-2-1.png" />
                <p>定位不准，申请失败</p>
                <p class="p-1">
                    定位太高或太低都有可能导致申请失败。定位太高有可能全军覆没，定位太低则会错过一些更好的学校。留学芒果由从业多年的专家提供服务，使用智能选校系统进行精准匹配，有效提高定位准确性和申请成功率。
                </p>
            </div>
            <div class="c-2">
                <img src="../plugin/new/images/banner-2-2.png" />
                <p>只看排名，选错学校</p>
                <p class="p-1">
                    排名只是择校的一个因素，并非排名高就一难录取，排名低就一定好申请。只看排名选学校，往往会导致学生在选校时做出错误的决定。相对于排名，留学芒果更关注一个Program的质量及真实的录取统计数据，从而选出那些真正适合的学校。
                </p>
            </div>
            <div class="c-3">
                <img src="../plugin/new/images/banner-2-3.png" />
                <p>中介推荐的保底学校</p>
                <p class="p-1">
                    有的中介机构会因为利益关系，故意引导学生选择一些比较差的保底学校。这类学校要么地理位置偏僻，要么录取中国学生很多，真正入学之后学生体验较差，会有很大的心理落差。留学芒果投入一年的时间开发了美国留学选校数据库，就是为了让学生更多的了解学校的真实情况，从而避免落入这样的陷阱。
                </p>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="banner-4">
        <div class="content">
            <h2>精准选校背后，是海量数据支持</h2>
            <table class="tbl-1">
                <tr>
                    <td><img src="../plugin/new/images/banner-3-2.png" /></td>
                    <td style="color:#28b2bb;font-size:24pt;">学校每年招收多少学生？录取率如何？平均GT成绩多少？毕业生就业率怎么样？你被录取几率有多大？</td>
                </tr>
                <tr>
                    <td><img src="../plugin/new/images/banner-3-3.png" /></td>
                    <td style="color:#28b2bb;font-size:24pt;">留学芒果选校数据库收录了美国360所大学，200多个专业，10000多个Program，5000份真实案例数据，并免费开放给我们的用户。运用大数据技术进行定位匹配，辅助选校决策，定位更精准。</td>
                </tr>
            </table>
            <div class="people-l"></div><div class="people-r"></div>
        </div>
    </div>
    <div class="banner-5">
        <div class="content">
            <h2>资深留美专家，1V1提供服务</h2>

            <table class="tbl-1">
                <tr>
                    <td><img src="../plugin/new/images/banner-4-1.png" /></td>
                    <td style="color:#fff;font-size:20pt;">我们只安排行业经验6年以上的资深美国留学顾问，为您提供一对一的选校定位服务。由留学专家用多年积累的经验，将您的背景信息和数据库中的数据进行人工匹配，为您筛选出最适合的10所学校，并逐一给出详尽的择校理由，可以保证服务的专业度和选校定位的准确性。</td>
                </tr>

            </table>
            <div class="clear"></div>
        </div>
    </div>
    <div class="banner-6">
        <div class="content">
            <h2>留学芒果美国硕博选校服务流程</h2>
            <table class="tbl-1">
                <tr>
                    <td valign="top" class="td-bg"><img src="../plugin/new/images/banner-5-2.png" /></td>
                    <td valign="top">
                        <h2>20分钟电话背景调研</h2>
                        <p>
                            留学芒果选校数据库收录了美国360所大学，200多个专业，10000多个Program，5000份真实案例数据，并免费开放给我们的用户。运用大数据技术进行定位匹配，辅助选校决策，定位更精准。
                        </p>
                    </td>
                </tr>
                <tr>
                    <td valign="top" class="td-bg"><img src="../plugin/new/images/banner-5-3.png" /></td>
                    <td valign="top">
                        <h2>精心筛选10所学校，完成15-20页书面定位选校报告</h2>
                        <p>
                            我们会在背景调研后的5个工作日内为您筛选出10所学校，量身定制一份15-20页的书面报告，附上每个学校的详细数据信息，并给出推荐理由。
                        </p>
                    </td>
                </tr>
                <tr>
                    <td valign="top"><img src="../plugin/new/images/banner-5-1.png" /></td>
                    <td valign="top">
                        <h2>30分钟电话讲解答疑</h2>
                        <p>
                            向您发送书面报告之后，您的顾问老师还会再和您做一次电话沟通，对选校报告的内容进行讲解，并解答您对于学校的疑问。
                        </p>
                    </td>
                </tr>
            </table>
            <div class="signup"><a href="http://lxmango_com.mikecrm.com/uGAdxd"  target="_blank"  class="btn-1">立即报名</a></div>
        </div>
    </div>

	    <s:action namespace="/user" name="foot" executeResult="true"/>
      	
	</body>
</html>
