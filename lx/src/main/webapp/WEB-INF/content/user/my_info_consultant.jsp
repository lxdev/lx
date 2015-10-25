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
		<title>留学芒果</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>

        <jc:plugin name="new_css" />
        <jc:plugin name="new_js" />
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
        		<!-- start -->
        		<div class="row">
		            <div class="col-md-12">
		              <form role="form" id="templatemo-preferences-form">
		                <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="firstName" class="control-label">姓名</label>
		                    <input type="text" class="form-control" id="firstName" value="<s:property value="users.full_name"/>">                  
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="lastName" class="control-label">Last Name</label>
		                    <input type="text" class="form-control" id="lastName" value="Henry">                 
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
		                    <label>Username</label>
		                    <p class="form-control-static" id="username">@admin</p>
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label>Email address</label>
		                    <p class="form-control-static" id="email">admin@company.com</p>
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="currentPassword">Current Password</label>
		                    <input type="password" class="form-control" id="currentPassword" value="********" disabled>  
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  </div>
		                </div>
		                
		                <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="password_1">New Password</label>
		                    <input type="password" class="form-control" id="password_1" placeholder="New Password">  
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="password_2">Confirm New Password</label>
		                    <input type="password" class="form-control" id="password_2" placeholder="Confirm New Password">  
		                  </div>
		                </div>
		                
		                <div class="has-success has-feedback">
		                  	<div class="row">
		                   		<div class="col-md-12 margin-bottom-15">
				                    <label class="control-label" for="inputSuccess">Input with success</label>
				                    <input type="text" class="form-control" id="inputSuccess">
				                    <span class="fa fa-check form-control-feedback"></span>
		                  		</div> 
		                	</div>
		              	</div>
						<div class="row has-warning has-feedback">
						   <div class="col-md-12 margin-bottom-15">
						     <label class="control-label" for="inputWarning">Input with warning</label>
						     <input type="text" class="form-control" id="inputWarning">
						     <span class="fa fa-warning form-control-feedback"></span>
						   </div>
						</div>
		              	<div class="row has-error has-feedback">
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="control-label" for="inputError">Input with error</label>
			                  <input type="text" class="form-control" id="inputError">
			                  <span class="fa fa-times form-control-feedback"></span>
			                </div>
		              	</div>
		              	<div class="row">
			                <div class="col-md-12 margin-bottom-15">
			                  <label for="notes">Notes</label>
			                  <textarea class="form-control" rows="3" id="notes"></textarea>
			                </div>
		              	</div>
		              	<div class="row">
			                <div class="col-md-6 margin-bottom-15">
			                  <label for="singleSelect">Single selection control</label>
			                  <select class="form-control margin-bottom-15" id="singleSelect">
			                    <option>HTML</option>
			                    <option>CSS</option>
			                    <option>JavaScript</option>
			                    <option>jQuery</option>
			                    <option>Bootstrap</option>
			                  </select>
			                  <label for="multipleSelect">Multiple selection control</label>
			                  <select multiple class="form-control" id="multipleSelect">
			                    <option>Charts</option>
			                    <option>Graphs</option>
			                    <option>Forms</option>
			                    <option>Icons</option>
			                    <option>Responsive</option>
			                  </select>  
			                </div>
		                	<div class="col-md-6">
								<div class="checkbox">
								  <label>
								    <input type="checkbox" value="">
								    Email me when new memeber sign up.
								  </label>
								</div>
								<div class="checkbox disabled margin-bottom-15">
								  <label>
								    <input type="checkbox" value="" disabled>
								    Never email me.
								  </label>
								</div>
								<div class="radio margin-bottom-15">
								  <label>
								    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
								    HTML format
								  </label>
								</div>
								<div class="radio">
								  <label>
								    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
								    Plain text
								  </label>
								</div>
								<div class="radio disabled">
								  <label>
								    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
								    Rich text
								  </label>
								</div>                 
		                	</div>                            
		              	</div>                
		              	<div class="row">
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="checkbox-inline">
			                    <input type="checkbox" id="inlineCheckbox1" value="option1" checked> Server status
			                  </label>
			                  <label class="checkbox-inline">
			                    <input type="checkbox" id="inlineCheckbox2" value="option2" checked> Memember status
			                  </label>
			                  <label class="checkbox-inline">
			                    <input type="checkbox" id="inlineCheckbox3" value="option3" checked> Expired members
			                  </label>                      
			                </div>
		              	</div>
		              	<div class="row">
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="radio-inline">
			                    <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" checked> Bootstrap
			                  </label>
			                  <label class="radio-inline">
			                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Foundation
			                  </label>
			                  <label class="radio-inline">
			                    <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> Skeleton
			                  </label>                    
			                </div>                  
		              	</div>
		              	<div class="row">
			                <div class="col-md-12 margin-bottom-30">
			                  <label for="exampleInputFile">File input</label>
			                  <input type="file" id="exampleInputFile">
			                  <p class="help-block">You can upload file here.</p>  
			                </div>                  
		              	</div>
		              	<div class="row templatemo-form-buttons">
			                <div class="col-md-12">
			                  <button type="submit" class="btn btn-primary">Update</button>
			                  <button type="reset" class="btn btn-default">Reset</button>    
			                </div>
		              	</div>
		            </form>
		        </div>
        		<!-- end -->
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
  });
		</script>
	</body>
</html>
