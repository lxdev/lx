<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>菜单栏</title>
		<jc:plugin name="layout" />
		<jc:plugin name="jquery" />
		<script type=text/javascript>
//等待dom元素加载完毕.
$(document).ready(function() {
	//getTree();
});

function inits() {
	$(".has_children").siblings().addClass("highlight").children("div").hide();
	$(".node_1").children("a").hide();
	$(".has_node").click(function() {
		if ($(this).parent().children("div").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("div").show().end() //将子节点的a元素显示出来并重新定位到上次操作的元素
					.siblings().removeClass("highlight") //获取元素的兄弟元素，并去掉他们的highlight类
					.children("div").hide(); //将兄弟元素下的a元素隐藏
		} else {
			$(".has_children").addClass("highlight").children("div").hide();
		}

		var aMenus = $(window.top.head.document.getElementById("tdMenu"))
				.children("a");
		for ( var i = 0; i < aMenus.length; i++) {

			if (this.id == aMenus[i].id) {
				$(window.top.head.document.getElementById(aMenus[i].id)).css( {
					"color" : "white"
				});
			} else {
				$(window.top.head.document.getElementById(aMenus[i].id)).css( {
					"color" : "black"
				});
			}
		}

	});
	$(".left_children").click(function() {

		if ($(this).parent().children("a").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").show(); //将兄弟元素下的a元素隐藏
			$(this).removeClass("left_round").addClass("show_node");
		} else {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").hide();
			$(this).removeClass("show_node").addClass("left_round");
		}
	});
	$(".has_node_1").click(function() {

		if ($(this).parent().children("a").is(":hidden")) {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").show(); //将兄弟元素下的a元素隐藏
			$(this).parent().children("#left_j").removeClass("left_round")
					.addClass("show_node");
		} else {
			$(this).parent().addClass("highlight") //为当前元素增加highlight类
					.children("a").hide();
			$(this).parent().children("#left_j").removeClass("show_node")
					.addClass("left_round");
		}
	});
	$("a").click(function() {
		$(".nochosea").removeClass("chosea");
		$(this).addClass("chosea");
	});
	setMeun();
}



function setMeun() {
	var n = $('#leftbar_left').find('input[type="hidden"]');
	var maxval = 19;
	for ( var i = 0; i < n.size(); i++) {
		var hidval = $('#leftbar_left').find('input[type="hidden"]').eq(i)
				.val();
		maxval = maxval > hidval ? hidval : maxval;
		var menu = document.getElementById("tab" + hidval);
		var img = "img_dark" + hidval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		var con = document.getElementById("con_tab" + hidval);
		con.style.display = "none";
	}
	if ("" != "" && "0" != "") {
		var menu = document.getElementById("tab");
		var con = document.getElementById("con_tab");
		var img = "img_light";
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		con.style.display = "";
		$('#').addClass("highlight").children("div").show();
	} else {
		var menu = document.getElementById("tab" + maxval);
		var con = document.getElementById("con_tab" + maxval);
		var img = "img_light" + maxval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		con.style.display = "";
	}
}
function setTab(name, cursel) {
	var n = $('#leftbar_left').find('input[type="hidden"]');
	for ( var i = 0; i < n.size(); i++) {
		var hidval = $('#leftbar_left').find('input[type="hidden"]').eq(i)
				.val();
		var menu = document.getElementById(name + hidval);
		var img = "img_dark" + hidval;
		menu.innerHTML = "<img src='" + WEB_PLUGINS
				+ "/plugin/base_css/images/menu/" + img + ".gif' />";
		var con = document.getElementById("con_" + name + hidval);/* con_two_1 */
		con.style.display = "none";
	}
	var menu = document.getElementById(name + cursel);/* two1 */
	var con = document.getElementById("con_" + name + cursel);/* con_two_1 */
	var img = "img_light" + cursel;
	menu.innerHTML = "<img src='" + WEB_PLUGINS
			+ "/plugin/base_css/images/menu/" + img + ".gif' />";
	con.style.display = "";
}
</script>
	</head>
	<body>
		<div id=leftbar>
			<!-- 子系统 -->
			<div id=leftbar_left style="border: 0px gray solid">
				
				<div id="tab18" class="leftcss" onclick="setTab(&quot;tab&quot;,18)">
					<img src="<ui:img url="/plugin/base_css/images/menu/img_dark_guide.gif"/>">
				</div>
			
			</div>
			<!-- 子系统容器 -->
			<div id=leftbar_right style="border: 0px yellow solid">
				<!-- 模块 -->
				<div id="menu" style="border: 0px yellow solid">

					<div id="60" class="has_children highlight">
						<span id="60_a" class="has_node">
							<img border="0" src="<ui:img url="/plugin/base_css/images/menu/item_2.gif"/>"></span>
						<div class="node_6" style="display: block;">
							<div class="left_noadd">&nbsp;</div>
							<div class="right_word">
								<a class="nochosea" href="<%=basePath %>/manage/guide_option" name="/manage/guide_option" target="main">选项设置</a>
							</div>
						</div>
						<div class="node_4" style="display: block;">
							<div class="left_noadd">&nbsp;</div>
							<div class="right_word">
								<a class="nochosea" href="<%=basePath %>/manage/guide_specialty" name="/manage/guide_specialty" target="main">自定义专业</a>
							</div>
						</div>
						<div class="node_4" style="display: block;">
							<div class="left_noadd">&nbsp;</div>
							<div class="right_word">
								<a class="nochosea" href="<%=basePath %>/manage/guide" name="/manage/guide" target="main">攻略设置</a>
							</div>
						</div>
						<div class="node_4" style="display: block;">
							<div class="left_noadd">&nbsp;</div>
							<div class="right_word">
								<a class="nochosea" href="<%=basePath %>/manage/specialty" name="/manage/specialty" target="main">系统专业</a>
							</div>
						</div>
						<div class="node_4" style="display: block;">
							<div class="left_noadd">&nbsp;</div>
							<div class="right_word">
								<a class="nochosea" href="<%=basePath %>/manage/program" name="/manage/program" target="main">Program管理</a>
							</div>
						</div>
					</div>
					

				</div>
			</div>
		</div>

 
	
	</body>
</html>
