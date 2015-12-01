<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
  ///////////////////
  //用户自己维护和管理的个人页面
  ///////////////////
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/home";
	Object user_type = request.getAttribute("users.user_type");

    String user_name = request.getAttribute("users.full_name") == null ? request.getAttribute("users.user_name").toString() : request.getAttribute("users.full_name").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>留学芒果-个人中心</title>
		<!--<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>-->

        <jc:plugin name="new_css" />
        <jc:plugin name="new_js" />
        
        <jc:plugin name="new_manage"/>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container-fluid">
			<div class="template-page-wrapper">
    			<s:action namespace="/user" name="left" executeResult="true"/>

      			<div class="cen_rnv">
					<span class="mb">收藏</span>
					<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#F2F2F2" class="tb">
					  <tr class="tytop">
					    <td width="18%">学校</td>
					    <td width="18%">课程</td>
					    <td width="18%">收藏时间</td>
					    <td width="18%">操作</td>
					    </tr>
					  <tr>
					    <td class="tybx">XX学校</td>
					    <td class="tybx">XX学校</td>
					    <td class="tybx">2015</td>
					    <td class="tybx"> 
					      <input type="submit" name="Submit" value="我要申请"  class="tybot" /> <input type="submit" name="Submit" value="取消收藏" class="tywbot" />
					    </td>
					  </tr>
					  <tr>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					  </tr>
					  <tr>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					  </tr>
					  <tr >
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					    <td class="tybx">&nbsp;</td>
					  </tr>
					</table>
				</div>
				<div class="clear"></div>


      <!-- Modal -->
      <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="z-index:1050;">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" id="myModalLabel">您确认要注销吗？</h4>
            </div>
            <div class="modal-footer">
              <a href="<s:url value="/template/log_out" />" class="btn btn-primary">是</a>
              <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
            </div>
          </div>
        </div>
      </div>
      <footer class="templatemo-footer">
        <div class="templatemo-copyright">
          <p>Copyright &copy; 2015 留学芒果  <a href="http://www.lxmango.com/" title="留学芒果" target="_blank"></a></p>
        </div>
      </footer>
    </div>
		</div>
	
		<script type="text/javascript">
			// Line chart
		    var randomScalingFactor = function(){ 
				return Math.round(Math.random()*100)
			};
		    var lineChartData = {
		      labels : ["January","February","March","April","May","June","July"],
		      datasets : [
		      {
		        label: "My First dataset",
		        fillColor : "rgba(220,220,220,0.2)",
		        strokeColor : "rgba(220,220,220,1)",
		        pointColor : "rgba(220,220,220,1)",
		        pointStrokeColor : "#fff",
		        pointHighlightFill : "#fff",
		        pointHighlightStroke : "rgba(220,220,220,1)",
		        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
		      },
		      {
		        label: "My Second dataset",
		        fillColor : "rgba(151,187,205,0.2)",
		        strokeColor : "rgba(151,187,205,1)",
		        pointColor : "rgba(151,187,205,1)",
		        pointStrokeColor : "#fff",
		        pointHighlightFill : "#fff",
		        pointHighlightStroke : "rgba(151,187,205,1)",
		        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
		      }
		      ]
		
		    }
		
		    window.onload = function(){
		      var ctx_line = document.getElementById("templatemo-line-chart").getContext("2d");
		      window.myLine = new Chart(ctx_line).Line(lineChartData, {
		        responsive: true
		      });
		    };
		
		    $('#myTab a').click(function (e) {
		      e.preventDefault();
		      $(this).tab('show');
		    });
		
		    $('#loading-example-btn').click(function () {
		      var btn = $(this);
		      btn.button('loading');
		  	});
		</script>
	</body>
</html>
