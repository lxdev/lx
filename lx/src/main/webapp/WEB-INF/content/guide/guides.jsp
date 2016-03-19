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
  <div class="searcharea gl-search-bg">
        <div class="content">
            <div class="result">
                共 <span class="ftcolffbc00" id="universityNum">203</span> 所学校，提供 <span class="ftcolffbc00" id="programNum">5229</span>个课程
            </div>
            <div class="conditions">
                <div class="condit-1 width-176">
                    <select name="countryId" id="countryId" class="txt-6" onselect="1">
                        <option value="0">--请选择国家--</option>
                        <option value="1" selected="selected">美国</option>


                    </select>

                </div>
                <!--<div class="condit-1 width-157">
                    <select name="studyLevelId" id="studyLevelId" class="txt-7" onselect="1">
                        <option value="0">--请选择学位--</option>
                        <option value="1" selected="selected">硕士</option>
                        <option value="3">博士</option>


                    </select>

                </div>-->
                <div class="condit-1">


                    <input id="program_specialty" name="program_specialty" class="ui-autocomplete-input txt-1" placeholder="输入专业名称* " data-url="/template/specialty_search">

                    <input type="hidden" id="program_specialty_id" name="program_specialty_id" value="">
                </div>
                <div class="condit-1">
                    <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input id="university_name" name="university_name" class="ui-autocomplete-input txt-1" placeholder="请输入学校名称（选填）" data-url="/template/university_search" value="" autocomplete="off">
                    <input type="hidden" id="university_name_id" name="university_name_id" value="">
                </div>
                <div class="condit-2">
                    <!-- <input type="hidden" id="page_size" name="condition.page_size" value="10"/>
                    <input type="hidden" id="page" name="condition.page" value="0"/>
                    <input type="hidden" id="sort_by" name="condition.orderBy" value="D.total_browse DESC"/> -->
                    <input type="hidden" id="page_size" name="page_size" value="10">
                    <input type="hidden" id="page" name="page" value="1">
                    <input type="hidden" id="orderBy" name="orderBy" value="">
                    <input type="hidden" id="record_total" value="203">
                    <input type="hidden" name="defaultval" value="输入专业名称* " id="defaultval">
                    <input class="button-1" type="button" value="搜索" onclick="search_data_p_init();">
                </div>
            </div>
        </div>
    </div>
    <div class="gl-kh">攻略在手，Offer无忧</div>
    <div class="gl-banner">
        <div class="row_1">
            <div class="l">
                <img src="../plugin/new/images/gl-1.jpg" />
                <div class="name">商学（Business）</div>
            </div>
            <div class="r">
                <img src="../plugin/new/images/gl-2.jpg" />
                <div class="name">工程（Engineering）</div>
            </div>
        </div>
        <div class="row_2">
            <div class="l">
                <img src="../plugin/new/images/gl-5.jpg" />
                <div class="name">主题攻略</div>
            </div>
            <div class="r">
                <table>
                    <tr>
                        <td style="width:380px;" align="right"><img src="../plugin/new/images/gl-4.jpg" /><div class="name">理学（Science）</div></td>
                        <td style="width:389px;" align="right"><img src="../plugin/new/images/gl-3.jpg" /><div class="name">人文社科（H&S）</div></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <img src="../plugin/new/images/gl-6.jpg" /><div class="name">艺术（Arts）</div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="gl-container">
        <div class="header"><span class="country">美国(American)</span><span class="selected gradation">硕士</span><span class="gradation">博士</span><span class="gradation">主题</span></div>
        <div class="professionname">商学（Business）</div>
        <table class="professionlist">
            <tr>
                <td style="width:25%"><span class="gl-gray">会计</span><span class="gl-light-gray">（accounting）</span></td>
                <td style="width:25%"><span class="gl-gray">市场营销</span><span class="gl-light-gray">（Marketing）</span></td>
                <td style="width:25%"><span class="gl-gray">管理信息系统</span><span class="gl-light-gray">（ERP）</span></td>
                <td style="width:25%"><span class="gl-gray">金融</span><span class="gl-light-gray">（finance）</span></td>
            </tr>
            <tr>
                <td style="width:25%"><span class="gl-gray">会计</span><span class="gl-light-gray">（accounting）</span></td>
                <td style="width:25%"><span class="gl-gray">市场营销</span><span class="gl-light-gray">（Marketing）</span></td>
                <td style="width:25%"><span class="gl-gray">管理信息系统</span><span class="gl-light-gray">（ERP）</span></td>
                <td style="width:25%"><span class="gl-gray">金融</span><span class="gl-light-gray">（finance）</span></td>
            </tr>
            <tr>
                <td style="width:25%"><span class="gl-gray">会计</span><span class="gl-light-gray">（accounting）</span></td>
                <td style="width:25%"><span class="gl-gray">市场营销</span><span class="gl-light-gray">（Marketing）</span></td>
                <td style="width:25%"><span class="gl-gray">管理信息系统</span><span class="gl-light-gray">（ERP）</span></td>
                <td style="width:25%"><span class="gl-gray">金融</span><span class="gl-light-gray">（finance）</span></td>
            </tr>
        </table>
    </div>
  
  
	
	<div class="strategytotal">
		一共有<span id="pro_id"><s:property value="resultGuide.size()"/></span>个留学攻略
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
	
	<s:action namespace="/user" name="foot" executeResult="true"/>
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
		        $(".gradation").click(function(){
					$(".gradation").removeClass("selected");
					$(this).addClass("selected");
				})
			});
	</script>
  </body>
</html>
