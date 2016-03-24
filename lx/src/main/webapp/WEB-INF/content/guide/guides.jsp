<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>搜攻略</title>
<jc:plugin name="auto_complete" />
<jc:plugin name="new_css" />
</head>

<body>
	<s:action namespace="/user" name="head" executeResult="true" />
	<div class="searcharea gl-search-bg">
		<div class="content">
			<div class="result">
				留学芒果共为您提供了 <span class="ftcolffbc00" id="programNum"><s:property value="resultGuide.size()" /></span> 个留学攻略。
			</div>
			<div class="conditions">
				<form id="scholarShipFormBean" name="scholar" action="guides"
					method="post"
					onsubmit="return validateSpecialtyForm('search_guide');">
					<input type="hidden" name="isFromFirst" value="true"
						id="isFromFirst" />
					<div class="condit-1 width-176">
						<s:select list="%{countrys}" listKey="id" listValue="name"
							onselect="%{countryId}" headerKey="0" headerValue="--请选择国家--"
							theme="simple" name="countryId" id="countryId" cssClass="txt-6" />
					</div>
					<div class="condit-1" >
						<input id="guide_specialty" name="guide_specialty"
							class="ui-autocomplete-input txt-1" placeholder="请输入专业名称"
							data-url="<s:url value="/template/guide_specialty_search"/>" /> 
							<input	type="hidden" id="guide_specialty_id" name="guide_specialty_id" />
					</div>
					<div class="condit-1" style="display:none">
					<input type="hidden" id="university_name_id" name="university_name_id"/>
					</div>
					<div class="condit-2">
						<td align="center"><input class="button-1" type="submit" value="搜索" /></td>
					</div>
				</form>
			</div>

		</div>
	</div>
	<div class="gl-kh">攻略在手，Offer无忧</div>
	<div class="gl-banner">
		<div class="row_1">
			<div class="l">
			<a href="guide?guide_id=13">
				<img src="../plugin/new/images/gl-1.jpg" />
				<div class="name">会计（Accounting）</div>
				</a>
			</div>
			<div class="r">
			<a href="guide?guide_id=26">
				<img src="../plugin/new/images/gl-2.jpg" />
				<div class="name">生物医学工程（B.E.）</div>
				</a>
			</div>
			
		</div>
		<div class="row_2">
			<div class="l">
			<a href="guide?guide_id=21">
				<img src="../plugin/new/images/gl-5.jpg" />
				<div class="name">教育（Education）</div>
				</a>
			</div>
			<div class="r">
				<table>
					<tr>
				
						<td style="width:380px;" align="right"><a href="guide?guide_id=8"><img
							src="../plugin/new/images/gl-4.jpg" />
							<div class="name">统计（Statistics）</div></a></td>
						<td style="width:389px;" align="right"><a href="guide?guide_id=15"><img
							src="../plugin/new/images/gl-3.jpg" />
							<div class="name">金融（Finance）</div></a></td>
							
					</tr>
					<tr>
						<td colspan="2"><a href="guide?guide_id=30"><img src="../plugin/new/images/gl-6.jpg" />
							<div class="name">建筑学（Architecture）</div></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="gl-container">
		<div class="header">
			<span class="country">美国(American)</span><span
				class="selected gradation">硕士</span><span class="gradation">博士</span><span
				class="gradation">主题</span>
		</div>
		<s:iterator value="specialtyCategorys" id="inner0">
			<div class="professionname">${inner0.specialty_name}</div>
			<table class="professionlist">
			<s:set var="indexColumn" value="0"/>
			<s:iterator value="specialtys" id="inner1">
				
			    <s:if test="#inner1.parent_id == #inner0.id">
					<s:iterator value="resultGuide" id="guideItem">
						<s:if test="#guideItem.specialty_id == #inner1.id && #guideItem.is_self_specialty == 0">																		
								<s:if test="#indexColumn%4==0">  
				                    <tr>  
				                </s:if>  						
									<td style="width:25%">
										<a 	href="../template/guide?guide_id=${guide_id}" >
											<span class="gl-gray">${inner1.specialty_name}</span>
											<span class="gl-light-gray">（${inner1.specialty_english_name}）</span>
										</a>
									</td>
								<s:if test="#indexColumn%4==3"> 
								</tr>	
								</s:if>	
								<s:set var="indexColumn" value="#indexColumn+1"/> 					
						</s:if>					
					</s:iterator>
				</s:if>
			</s:iterator>
			</table>
	</s:iterator>
	</div>
	<div style=" height: 100px;"></div>



	<%-- <div class="strategytotal">
		一共有<span id="pro_id"><s:property value="resultGuide.size()" />
		</span>个留学攻略
	</div>
	<div class="reslut-2 strategycontent">
		<div class="title">
			<ul id="cou_tabs">
				<s:iterator value="countrys">
					<li role="presentation"
						class='marginleft25 <s:if test="id==1">current</s:if>'><a
						href="#country${id}">${name}</a>
					</li>
				</s:iterator>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="accuratesearch">
			<div class="content">
				<s:iterator value="countrys" id="cou">
					<div class="tab-pane <s:if test="#cou.id==1">active</s:if>"
						id="country${cou.id}">

						<table class="table-1" cellpadding="0" cellspacing="0">
							<s:if test="1==#cou.id">
								<s:iterator value="specialtyCategorys" id="inner0">
									<tr>
										<td class="width-64 text-right">${inner0.specialty_name}：</td>
										<td><s:iterator value="specialtys" id="inner1">
												<s:iterator value="resultGuide" id="guideItem">
													<s:if test="#guideItem.is_self_specialty == 0">
														<s:if test="#guideItem.specialty_id == #inner1.id && #inner1.parent_id == #inner0.id">															
															<span>
															    <a 	href="../template/guide?guide_id=${guide_id}" 	class="button btn btn-default">${inner1.specialty_name}</a>
															</span>
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
											<td class="width-64 text-right"><s:property
													value="#outer2.specialty_name" />：</td>
											<td><s:iterator value="guideSpecialtys" id="inner2">
													<s:if test="#inner2.parent_id==#outer2.id">
														<s:iterator value="resultGuide" id="guideItem">
															<s:if test="#guideItem.is_self_specialty == 1">
																<s:if test="#guideItem.specialty_id == #inner2.id">
																	<span><a
																		href="../template/guide_detail?guide_id=${guide_id}"
																		class="button btn btn-default">${inner2.specialty_name}</a>
																	</span>
																</s:if>
																<s:elseif
																	test="#guideItem.guideSpecialty.parent_id == #inner2.id">
																	<span><a
																		href="../template/guide_detail?guide_id=${guide_id}"
																		class="button btn btn-default">${guideItem.guideSpecialty.specialty_name}</a>
																	</span>
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
 --%>
	<s:action namespace="/user" name="foot" executeResult="true" />
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
