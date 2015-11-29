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
		<jc:plugin name="bootstrap_main"/>

        <jc:plugin name="new_css" />
        <jc:plugin name="new_js" />-->

        <jc:plugin name="new_manage"/>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container-fluid">
	<div class="template-page-wrapper">
    	<s:action namespace="/user" name="left" executeResult="true"/>
    	<%--<jsp:include page="../user/left_consultant.jsp" />--%>
      <!--/.navbar-collapse -->

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
        <div class="templatemo-content" style="display:none">
          <ol class="breadcrumb">
            <li><a href="index.html">Admin Panel</a></li>
            <li><a href="#">Dashboard</a></li>
            <li class="active">Overview</li>
            <li><a href="sign-in.html">Sign In Form</a></li>
          </ol>
          <h1>Dashboard</h1>
          <p>Dashboard is <a rel="nofollow" href="http://www.cssmoban.com/preview/templatemo_415_dashboard">free admin template</a> for everyone. Credits go to <a href="http://chartjs.org">Chart JS</a>, <a href="http://getbootstrap.com">Bootstrap</a>, and <a href="http://jqvmap.com">JQVMap</a>. templatemo provides <a href="#">free website templates</a> that can be used for any purpose. Morbi id nisi enim. Ut congue interdum pharetra facilisi. Aenean consectetur pellentesque mauris nec ornare. Nam tortor just, condimentum.</p>

          <div class="margin-bottom-30">
            <div class="row">
              <div class="col-md-12">
                <ul class="nav nav-pills">
                  <li class="active"><a href="#">Home <span class="badge">42</span></a></li>
                  <li class="active"><a href="#">Profile <span class="badge">126</span></a></li>
                  <li class="active"><a href="#">Messages <span class="badge">14</span></a></li>
                </ul>          
              </div>
            </div>
          </div>         

          <div class="row">
            <div class="col-md-6">
              <div class="templatemo-alerts">
                <div class="row">
                  <div class="col-md-12">
                    <div class="alert alert-success alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <strong>Success!</strong> Maecenas non lorem sed elit molestie tincidunt.
                    </div>
                    <div class="alert alert-info alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      Sed in molestie lectus. Curabitur non est neque. Maecenas id luctus ligula.
                    </div>
                    <div class="alert alert-warning alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      Vestibulum tincidunt libero urna, ut dignissim purus accumsan nec.
                    </div>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      Mauris dignissim ante eu arcu ultricies, at sodales orci aliquet.
                    </div>            
                  </div>  
                </div>            
              </div>              
            </div>
            <div class="col-md-6">
              <div class="templatemo-progress">
                <div class="list-group">
                  <a href="#" class="list-group-item active">
                    <h4 class="list-group-item-heading">Latest Data</h4>
                    <p class="list-group-item-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vel adipiscing quam. Maecenas vehicula rhoncus magna, vitae lacinia sem auctor a. Vestibulum aliquam, nisl nec luctus volutpat, turpis orci posuere arcu, eget consectetur quam nunc ac arcu.</p>
                  </a>
                  <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">Older Data</h4>
                    <p class="list-group-item-text">Maecenas in facilisis nisi. Proin gravida nunc vel justo vestibulum, quis adipiscing velit faucibus. Sed a hendrerit orci. Nunc ut bibendum eros, at varius urna. Integer cursus condimentum dui vitae sagittis. Curabitur at vehicula nunc. Praesent at sem non nisl pellentesque placerat.</p>
                  </a>
                </div>
                <div class="progress">
                  <div class="progress-bar progress-bar-success" style="width: 35%">
                    <span class="sr-only">35% Complete (success)</span>
                  </div>
                  <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
                    <span class="sr-only">20% Complete (warning)</span>
                  </div>
                  <div class="progress-bar progress-bar-danger" style="width: 10%">
                    <span class="sr-only">10% Complete (danger)</span>
                  </div>
                </div>
                <div class="progress">
                  <div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
                    <span class="sr-only">45% Complete</span>
                  </div>
                </div>
              </div> 
            </div>
          </div>
          <div class="templatemo-panels">
            <div class="row">
              <div class="col-md-6 col-sm-6 margin-bottom-30">
                <div class="panel panel-success">
                  <div class="panel-heading">Data Visualization</div>
                  <canvas id="templatemo-line-chart" height="120" width="500"></canvas>
                </div> 
                <span class="btn btn-success"><a href="data-visualization.html">More Charts</a></span>
              </div>   
              <div class="col-md-6 col-sm-6 margin-bottom-30">
                <div class="panel panel-primary">
                  <div class="panel-heading">User Table</div>
                  <div class="panel-body">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Username</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>John</td>
                          <td>Smith</td>
                          <td>@js</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Bill</td>
                          <td>Jones</td>
                          <td>@bj</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Marry</td>
                          <td>James</td>
                          <td>@mj</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <span class="btn btn-primary"><a href="tables.html">See Tables</a></span>
              </div>       
            </div>
            <div class="row">
              <div class="col-md-6 col-sm-6">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
                  <li class="active"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
                  <li><a href="#profile" role="tab" data-toggle="tab">Profile</a></li>
                  <li><a href="#messages" role="tab" data-toggle="tab">Messages</a></li>
                  <li><a href="#settings" role="tab" data-toggle="tab">Settings</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                  <div class="tab-pane fade in active" id="home">
                    <ul class="list-group">
                      <li class="list-group-item"><input type="checkbox" name="" value=""> Suspendisse dapibus sodales</li>
                      <li class="list-group-item"><input type="checkbox" name="" value=""> Proin mattis ex vitae</li>
                      <li class="list-group-item"><input type="checkbox" name="" value=""> Aenean euismod dui vel</li>
                      <li class="list-group-item"><input type="checkbox" name="" value=""> Vivamus dictum posuere odio</li>
                      <li class="list-group-item"><input type="checkbox" name="" value=""> Morbi convallis sed nisi suscipit</li>
                    </ul>
                  </div>
                  <div class="tab-pane fade" id="profile">
                    <ul class="list-group">
                      <li class="list-group-item">
                        <span class="badge">33</span>
                        Vivamus dictum posuere odio
                      </li>
                      <li class="list-group-item">
                        <span class="badge">9</span>
                        Dapibus ac facilisis in
                      </li>
                      <li class="list-group-item">
                        <span class="badge">0</span>
                        Morbi convallis sed nisi suscipit
                      </li>
                      <li class="list-group-item">
                        <span class="badge">14</span>
                        Cras justo odio
                      </li>
                      <li class="list-group-item">
                        <span class="badge">2</span>
                        Vestibulum at eros
                      </li>
                    </ul>
                  </div>
                  <div class="tab-pane fade" id="messages">
                    <div class="list-group">
                      <a href="#" class="list-group-item active">
                        Morbi convallis sed nisi suscipit
                      </a>
                      <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                      <a href="#" class="list-group-item">Morbi leo risus</a>
                      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                      <a href="#" class="list-group-item">Vestibulum at eros</a>
                    </div>
                  </div>
                  <div class="tab-pane fade" id="settings">
                    <div class="list-group">
                      <a href="#" class="list-group-item disabled">
                        Vivamus dictum posuere odio
                      </a>
                      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                      <a href="#" class="list-group-item">Vestibulum at eros</a>
                      <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                      <a href="#" class="list-group-item">Morbi leo risus</a>
                    </div>
                  </div>
                </div> <!-- tab-content --> 
              </div> 
              <div class="col-md-6 col-sm-6">
                <div class="panel-group" id="accordion">
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                          Accordion Item 1
                        </a>
                      </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                      <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                          Accordion Item 2
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                      <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                          Accordion Item 3
                        </a>
                      </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                      <div class="panel-body">
                        <button type="button" id="loading-example-btn" data-loading-text="Loading..." class="btn btn-primary">
                          Click here
                        </button> to load.
                      </div>
                    </div>
                  </div>
                </div>
              </div>          
            </div> 
          </div>    
          </div>
        </div>
      </div>
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
		/*$(function () {
			$('#tabs a:first').tab('show');//初始化显示哪个tab 
		    
	        $('#tabs a').click(function (e) { 
	          e.preventDefault();//阻止a链接的跳转行为 
	          $(this).tab('show');//显示当前选中的链接及关联的content 
	        }) 
		});*/
		// document.ready
	// Line chart
    var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
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
      // $.ajax(...).always(function () {
      //   btn.button('reset');
      // });
  });
		</script>
	</body>
</html>
