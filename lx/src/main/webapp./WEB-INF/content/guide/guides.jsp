<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title>搜攻略</title>
	
		<jc:plugin name="new_css" />
		<jc:plugin name="new_js" />
  </head>
  
  <body>
	<s:action namespace="/user" name="head" executeResult="true"/>
	<div class="strategytotal">
		共有<span id="pro_id"><s:property value="resultGuide.size()"/></span>个留学攻略
	</div>
	<div class="reslut-2 strategycontent">
		<div class="title">
            <ul id="cou_tabs">
                <s:iterator value="countrys">
		    		<li role="presentation" class='marginleft25 <s:if test="id==1">current</s:if>'>
		    			<a href="#country${id}">${name}</a>
		    		</li>
		        </s:iterator>
            </ul>
            <div class="clear">
            </div>
        </div>
        <div class="accuratesearch">
        	<div class="content">
        		<s:iterator value="countrys" id="cou">
        			<div class="tab-pane <s:if test="#cou.id==1">active</s:if>" id="country${cou.id}">
   					
			            <table class="table-1" cellpadding="0" cellspacing="0">
			            	<s:if test="1==#cou.id">
			                	<s:iterator value="specialtyCategorys" id="inner0">
			                	<tr>
			                		<td class="width-64 text-right">${inner0.specialty_name}：</td>
				                    <td>
				                    	<s:iterator value="specialtys" id="inner1">
											<s:iterator value="resultGuide" id="guideItem">
												<s:if test="#guideItem.is_self_specialty == 0">
													<s:if test="#guideItem.specialty_id == #inner1.id && #inner1.parent_id == #inner0.id">
														<!-- <span><a href="../template/guide_relation?guide_id=${guide_id}" class="button btn btn-default">${inner1.specialty_name}</a></span> -->
														<span><a href="../template/guide?guide_id=${guide_id}" class="button btn btn-default">${inner1.specialty_name}</a></span>
													</s:if>
												</s:if>
											</s:iterator>
					   					</s:iterator>
				                    </td>
				                </tr>
			                	</s:iterator>
			                </s:if>
			                <s:iterator value="guideSpecialtys" id="outer2">
					      		<s:if test="1==#cou.id">
					      			<s:if test="#outer2.parent_id==0">
						      			<tr>
			                    			<td class="width-64 text-right"><s:property value="#outer2.specialty_name"/>：</td>
						      				<td>
						      					<s:iterator value="guideSpecialtys" id="inner2">
						      						<s:if test="#inner2.parent_id==#outer2.id">
						      							<s:iterator value="resultGuide" id="guideItem">
						      							<s:if test="#guideItem.is_self_specialty == 1">
							      							<s:if test="#guideItem.specialty_id == #inner2.id">
							      								<span><a href="../template/guide_detail?guide_id=${guide_id}" class="button btn btn-default">${inner2.specialty_name}</a></span>
							      							</s:if>
							      							<s:elseif test="#guideItem.guideSpecialty.parent_id == #inner2.id">
						      									<span><a href="../template/guide_detail?guide_id=${guide_id}" class="button btn btn-default">${guideItem.guideSpecialty.specialty_name}</a></span>
						      								</s:elseif>
						      							</s:if>
						      							</s:iterator>
						      						</s:if>
						      					</s:iterator>
						      				</td>
						      		</s:if>
					      		</s:if>
				      		</s:iterator>
			            </table>
	            	</div>
	            </s:iterator>
	        </div>
        </div>
	</div>
	
	<div class="bottom">
        &copy; 2015 young Ltd All rights reserved.
    </div>
	
	<script>
			$(function () {
				//$('#tabs a:first').tab('show');//初始化显示哪个tab 
			    
		        $('#cou_tabs a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          //$(this).tab('show');//显示当前选中的链接及关联的content 
		          $(".marginleft25").removeClass("current");
		          $(this).parent().addClass("current");
		          
		          var current =  $(this).context.hash;
		          $(".tab-pane").hide();
		          $(current).show();
		        });
			});
	</script>
  </body>
</html>
