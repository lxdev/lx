<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="cn" xml:lang="cn" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>留学芒果-搜索顾问</title>
		
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
	</head>
	<body>
		<s:action namespace="/user" name="head" executeResult="true"/>
		<div class="searcharea">
	        <div class="content">
	            <div class="conditions paddingtop42">
	                <div class="condit-1">
	                    <s:select list="%{countryList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择国家--" theme="simple" name="country" id="country" cssClass="txt-1"/>
	                </div>
	                <div class="condit-1">
	                    <s:select list="%{specialtyList}" listKey="id" listValue="specialty_name" headerKey="0" headerValue="--请选择专业--" theme="simple" name="specialty" id="specialty" cssClass="txt-1"/>
	                </div>
	                <div class="condit-1">
	                    <s:select list="%{studyLevelList}" listKey="id" listValue="name" headerKey="0" headerValue="--请选择学位--" theme="simple" name="studylevel" id="studylevel" cssClass="txt-1"/>
	                </div>
	                <div class="condit-2">
	                    <input class="button-1" type="button" value="搜索" onclick="query_data()"/></div>
	            </div>
	            <div class="clear">
	            </div>
	            <div class="conditions-2">
<s:set var="mapCity" value="#{-1:'全部',1:'北京',2:'上海'}"></s:set>
<s:set var="mapConsultantType" value="#{-1:'全部',1:'全职',2:'在校生',3:'其他'}"></s:set>
<s:set var="mapServiceType" value="#{-1:'全部',1:'全程服务', 2:'Diy辅导', 3:'WORK SHOP', 41:'文书写作', 42:'选校服务', 43:'面试辅导', 44:'签证服务'}"></s:set>
	                <table class="table-4" cellpadding="0" cellspacing="0">
	                    <tr>
	                        <td class="width-105 text-right">
	                            <span class="ftsty-1">所在城市：</span>
	                        </td>
	                        <td>
	                            <s:iterator value="#mapCity">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="key==-1">
											<input type="radio" name="city" value="<s:property value="key"/>" checked onchange="query_data();"/> <s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="city" value="<s:property value="key"/>" onchange="query_data()"/> <s:property value="value"/>
										</s:else>
					   				</label>
								</s:iterator>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="width-105 text-right">
	                            <span class="ftsty-1">顾问类型：</span>
	                        </td>
	                        <td>
	                            <s:iterator value="#mapConsultantType">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="key==-1">
											<input type="radio" name="consultant_type" value="<s:property value="key"/>" checked onchange="query_data();"/> <s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="consultant_type" value="<s:property value="key"/>" onchange="query_data()"/> <s:property value="value"/>
										</s:else>
					   				</label>
								</s:iterator>
							</td>								
	                    </tr>
	                    <tr>
	                        <td class="width-105 text-right">
	                            <span class="ftsty-1">服务类型：</span>
	                        </td>
	                        <td>
	                            <s:iterator value="#mapServiceType">
									<label class="checkbox-inline" style="padding-top:8px">
										<s:if test="key==-1">
											<input type="radio" name="service_type" value="<s:property value="key"/>" checked onchange="query_data();"/> <s:property value="value"/>
										</s:if>
										<s:else>
											<input type="radio" name="service_type" value="<s:property value="key"/>" onchange="query_data()"/> <s:property value="value"/>
										</s:else>
					   				</label>
								</s:iterator>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	    </div>
		
		<div class="reslut-2">
	        <div class="title">
	            <ul>
	                <li class="marginleft25 current">热度</li><li>排名</li><li>点评</li></ul>
	            <div class="clear">
	            </div>
	        </div>
	        <div class="content">
	        
	        	<s:iterator value="consultantList">
	        		<table class="table-5" cellpadding="0" cellspacing="0">
			            <tr>
			            <td class="width-135"><img src="<s:property value="logo_url"/>" /></td>
			            <td valign="top" class="width-776">
			            <h3>
			            	<a class="pr_rslt sr nrmtab" id="<s:property value="id"/>" href="/template/consultant_detail?id=<s:property value="id"/>">
			            		<s:property value="user.full_name"/>
			            	</a>
			            </h3>
			            <p class="personallabel">个人标签：
			            	<s:generator val="goodat_specialtys" separator="," id="iter1">
			            	<s:iterator value="#request.iter1">
			            		<span><s:property value="name"/></span>
			            	</s:iterator>
			            	</s:generator>
			            </p>
			            <p class="empiricalvalue">经验值：0</p>
			            </td>
			            <td class="links text-center">
			            <img src="../plugin/new/images/consult.png" />
			            <img src="../plugin/new/images/phone.png" />
			            </td>
			            </tr>
		            </table>
		        	<div class="line"></div>
				</s:iterator>
	        </div>
	    </div>
	    <div class="bottom">
	        &copy; 2014 young Ltd All rights reserved.
	    </div>
		
        <script type="text/javascript">
			$(function () {
				$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		        })
			});

			var setConsultantItem = function(item){
				var content = "";
				
				
				$("#consultant_result").after(content);
			}

			var query_data = function(){
				
				var options = { 
						'condition.conCountryId': $("#country").val(), 
						'condition.conSpecialtyId': $("#specialty").val(), 
						'condition.conStudyLevelId': $("#studylevel").val(),
						//'condition.city': $("")
						};

				jQuery.post('<s:url value="/template/consultants"/>', options,
				        function(data)
				    	{
				    		var lists="";
				    		if(null!=data.userList)
				    		jQuery.each(data.consultantList, function()
			    			{
				    			setConsultantItem(this);
			    			});
				    		$("#headid").show();
							$("#consultant_num").html(data.consultantList.length);
				    	},
				 "json");
			}
		</script>
	</body>
	
</html>