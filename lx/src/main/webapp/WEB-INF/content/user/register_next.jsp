<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/user/register_next";
	String userId = request.getParameter("userId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>留学芒果</title>
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_login"/>
		<script>
			$(function () {
				$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		        }) 
			});
			
			var default_option = "<option value='0'>---请选择专业---</option>";
			var getUniversity = function(country){
				var url = '<s:url value="register_university_list_by_country"/>';
				var option = { countryid: country };
				jQuery.post(url,option,
				        function(data)
				    	{
							if(data.message != ""){
								alert(data.message);
								return;
							}
							var lists="";
				    		lists+=default_option;
				    		if(null!=data.universityList)
				    		jQuery.each(data.universityList, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.university_name+"</option>";
			    			});
			    			jQuery('#university_id').html(lists);
				    		
							getSpecialty(country);
				    	},
				 "json");
			}
			var getSpecialty = function(country){
				var url = '<s:url value="register_specialty_list_by_country"/>';
				var option = { countryid: country };
				jQuery.post(url,option,
				        function(data)
				    	{
							if(data.message != ""){
								alert(data.message);
								return;
							}
							var lists="";
				    		lists+=default_option;
				    		if(null!=data.specialtyList)
				    		jQuery.each(data.specialtyList, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.specialty_name+"</option>";
			    			});
			    			jQuery('#specialty_id').html(lists);
				    		
				    	},
				 "json");
			}
			var countryChange = function(){
				var country = $("#country_id").val();
				if($.trim(country) == "" || country == 0){
					alert("请选择一个国家！");
					return;
				}
				getUniversity(country);
			}
			
		</script>
		<style>
			.control-wrapper { padding-left: 64px; }
			.center-block { display:block; margin-left: auto; margin-right: auto; }
			.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
color: #555;
cursor: default;
background-color: #fdad00;
border: 1px solid #ddd;
border-bottom-color: transparent;
}
		</style>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container">
		<div class="zctit">
		<h1>完成注册的最后一步</h1>
		<div class="kj">
		<div class="me"><img src="../plugin/new/images//img-3.jpg" /><br />我</div>
		<div class="w937">
<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="row">
						
						<div class="col-lg-12">
			<ul class="nav nav-tabs" role="tablist" id="tabs">
		    	<li role="presentation" class="active"><a href="#div_senior">国内高中生</a></li>
		        <li role="presentation"><a href="#div_undergraduate">国内本科生</a></li>
		        <li role="presentation"><a href="#div_graduate">国内研究生</a></li>
		    	<li role="presentation"><a href="#div_job">在职申请人</a></li>
		        <li role="presentation"><a href="#div_parent">学生的家长</a></li>
		        <li role="presentation"><a href="#div_abroad">海外留学生</a></li>
		    </ul>
						</div>
					</div>
				</div>
				<div class="col-lg-3"></div>
			</div>
			<div class="tab-content">
		      <s:set var="mapType" value="#{2:'本科转学',1:'硕士',3:'博士'}"></s:set>
		      <s:set var="mapType1" value="#{1:'硕士',3:'博士'}"></s:set>
		      <s:set var="mapType2" value="#{2:'本科',1:'硕士',3:'博士'}"></s:set>
		      <s:set var="mapType3" value="#{4:'高中',2:'本科',1:'硕士',3:'博士'}"></s:set>
		      <div class="tab-pane active" id="div_senior">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="1"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table  class="registertype">
				  <tr>
				  <td  width="110">
				  我要出国读 
				  </td>
				  <td>
				  <span style="color:red">本科</span>
				  </td>
				  </tr>
				  <tr>
				  <td>
				  高中毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  </table>
				  	
					
				  </div>
				  <div class="form-group" style="text-align:center"><button type="submit" class="btn btn-default" style="width:130px">完成</button></div>
				</form>
		      </div>
		      <div class="tab-pane" id="div_undergraduate">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="2"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table  class="registertype">
				  <tr>
				  <td  width="110">
				  我要出国读
				  </td>
				  <td>
				  <s:select list="#mapType" headerKey="0" headerValue="--请选择--" theme="simple" name="study_level_id" id="study_level_id" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td>
				  大学毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  </table>
				  	 
					
				  </div>
				  <div class="form-group" style="text-align:center"><button type="submit" class="btn btn-default" style="width:130px">完成</button></div>
				</form>
		      </div>
		      <div class="tab-pane" id="div_graduate">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="3"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table  class="registertype">
				  <tr>
				  <td  width="110">
				  我要出国读
				  </td>
				  <td>
				  <s:select list="#mapType1" headerKey="0" headerValue="--请选择--" theme="simple" name="study_level_id" id="study_level_id" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td>
				  硕士毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  </table>
				  	 
					
				  </div>
				  <div class="form-group" style="text-align:center"><button type="submit" class="btn btn-default" style="width:130px">完成</button></div>
				</form>
		      </div>
		      <div class="tab-pane" id="div_job">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="4"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table  class="registertype">
				  <tr>
				  <td  width="110">
				  我要出国读 
				  </td>
				  <td>
				  <s:select list="#mapType1" headerKey="0" headerValue="--请选择--" theme="simple" name="study_level_id" id="study_level_id" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td>
				  本科毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  </table>
		      		
		      		
		      		</div>
				  	<div class="form-group" style="text-align:center"><button type="submit" class="btn btn-default" style="width:130px">完成</button></div>
				</form>
		      </div>
		      <div class="tab-pane" id="div_parent">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="5"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table class="registertype">
				  <tr>
				  <td width="110">
				  孩子要出国读
				  </td>
				  <td>
				  <s:select list="#mapType2" headerKey="0" headerValue="--请选择--" theme="simple" name="study_level_id" id="study_level_id" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td>
				  本科毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  </table>
		      		 
		      		
		      		</div>
				  
				  <div class="form-group" style="text-align:center">
				  <button type="submit" class="btn btn-default" style="width:130px">完成</button>
				  </div>
				</form>
		      </div>
		      <div class="tab-pane" id="div_abroad">
		      	<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" action="<%=actionUrl %>">
		      		<input type="hidden" id="user_type" name="user_type" value="6"/>
		      		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
				  <div class="form-group">
				  <table  class="registertype">
				  <tr>
				  <td  width="110" align="right">
				  我在 
				  </td>
				  <td>
				  <s:select list="countryList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="country_id" id="country_id" cssClass="form-control" onchange="countryChange()"/>
				  </td>
				  </tr>
				  <tr>
				  <td align="right">
				  读 
				  </td>
				  <td>
				  <s:select list="#mapType3" headerKey="0" headerValue="--请选择--" theme="simple" name="study_level_id" id="study_level_id" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td align="right">
				  毕业时间
				  </td>
				  <td>
				  <s:select list="graduateDateList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--" theme="simple" name="graduate_date" id="graduate_date" cssClass="form-control"/>
				  </td>
				  </tr>
				  <tr>
				  <td align="right">
				  学校 
				  </td>
				  <td>
				  <select id="university_id" name="university_id" class="form-control">
				  <option value="0">---请选择学校---</option>
					</select>
				  </td>
				  </tr>
				  <tr>
				  <td align="right">
				  专业 
				  </td>
				  <td>
				  <select id="specialty_id" name="specialty_id" class="form-control">
						<option value="0">---请选择专业---</option>
					</select>
				  </td>
				  </tr>
				  </table>
			      	 
			      	
			      	
			      	
						
			      	
			      	
				  </div>
				  <div class="form-group" style="text-align:center"><button type="submit" class="btn btn-default" style="width:130px">完成</button></div>
				</form>
		      </div>
		      
		</div>
 
<!---->
</div>
		</div>
		</div>
			
			
			
			
		    
		
	</body>
</html>
