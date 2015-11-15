<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>顾问</title>
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
  	</head>
  	<body class="consultant">
		<s:action namespace="/user" name="head" executeResult="true"/>
		
		<div class="wrap">
    		<div class="reslut-2">
        		<div class="content">
		            <table class="table-13" cellpadding="0" cellspacing="0">
			            <tr>
			            <td class="width-135"><img src="images/img-3.jpg" /></td>
			            <td valign="top"  class="width-776">
			            <h3><s:property value="consultantResult.user.display_name"/> <s:property value="consultantResult.introduce"/></h3>
			            <p class="personallabel">个人标签：
			            	<%--<span>香港</span><span>在职英国</span><span>法国研究生</span>
			            	--%><s:generator val="consultantResult.goodat_specialtys" separator="," id="tab_spe">
			            	<s:iterator value="#request.tab_spe">
			            		<span><s:property value="name"/></span>
			            	</s:iterator>
			            	</s:generator>
			            </p>
			            <p class="empiricalvalue">经验值：0</p>
			            </td>
			            <td class="links text-center">
			            	<a href="进入咨询"><img src="images/consult.png" /></a>
			            	<a href="进入电话"><img src="images/phone.png" /></a>
			            </td>
			            </tr>
		            </table>
		        </div>
		    </div>
     
     		<div class="detailinfo">
	            <div class="l">
	                <table cellpadding="0" cellspacing="0" class="table-14">
		                <tr>
		                <td>
		                <span class=" ftcol333333 ftsize16"><s:property value="consultantResult.user.full_name"/></span><span class="ftcolac9daa ftsize14 paddingl10">ID:<s:property value="consultantResult.user.id"/></span>
		                </td>
		                </tr>
		                <tr>
		                <td>
		                <span class="ftcolff9941 ftsize14">5.0分</span>
		                <span class="ftcolac9daa ftsize14 paddingl10">(<s:property value="consultantEvaluateList.size()"/>条评价)</span>
		                <span class="stars"></span>
		                </td>
		                </tr>
		                <tr>
		                <td class="paddingtop10">
		                <span class="course">2<br />课程</span>
		                <span class="students">18<br />学生</span>
		                <span class="duration">29<br />教学时长</span>
		                <span class="praise">100%<br />好评率</span>
		                </td>
		                </tr>
		                <tr>
		                <td>
		                <ul>
		                <li class="sfrz">身份认证</li><li class="zyrz">专业认证</li>
		                <li class="xlrz">学历认证</li><li class="jlnx">8年教龄</li>
		                </ul>
		                </td>
		                </tr>
		                <tr>
		                <td>
		                <span class="nlp">NLP教练技术</span>
		                <span class="yygqjs">运用共情技术</span>
		                </td>
		                </tr>
	                </table>
	            </div>
	            <div class="r">
		            <ul class="tabs"><li class="current">个人履历</li><li>客户评价</li><li>问答量</li><li>详细介绍</li><li>照片/视频</li></ul>
		            <div class="con-1">
		            <h2>基本信息</h2>
		            </div>
		            <div class="con-1" style="display:none">
		            <h2>客户评价</h2>
		            </div>
		            <div class="con-1" style="display:none">
		            <h2>问答量</h2>
		            </div>
		            <div class="con-1" style="display:none">
		            <h2>详细介绍</h2>
		            </div>
		            <div class="con-1" style="display:none">
		            <h2>照片/视频</h2>
		            </div>
	            </div>
            </div>
		</div>
		
		<script type="text/javascript">
			$(".tabs li").each(function (i) {
				$(this).click(function () {
					$(".tabs li").removeClass("current");
					$(this).addClass("current");
					$(".con-1").hide();
					$(".con-1").eq(i).show();
				});
			});
        </script>
	</body>
</html>
