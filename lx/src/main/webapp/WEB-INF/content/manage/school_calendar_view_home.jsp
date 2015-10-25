<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院历视图</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<jc:plugin name="tab" />
		<!-- 日程插件-->
		<jc:plugin name="schedule" />
		<script type='text/javascript'>
				//查询年视图
				function getYearView(s,e){
					$.ajax({
						<c:if test="${id==0}">
									url: 'calendarlist',
									dataType: 'json',
									data: {
										'start': s,
								         'end': e,
								         'random': Math.random()
									},
						</c:if>
						<c:if test="${id!=0}">
						
									url: 'calendarlist_id',
									dataType: 'json',
									data: {
										'start': s,
								         'end': e,
								         'id': ${id},
								         'random': Math.random()
									},
						</c:if>
									success: function(doc) {
										
										$(doc.schedules).each(function() {
											
											var startT=new Date(this.start.replace(/-/g,"/"));
											var endT = new Date(this.end.replace(/-/g,"/"));
											var yS=startT.getFullYear();
											var mS=(startT.getMonth()+1);
											var dS=startT.getDate();
											
											
											var yE=endT.getFullYear();
											var mE=(endT.getMonth()+1);
											var dE=endT.getDate();
											var hE=endT.getHours();
											var minE=endT.getMinutes();
											var ssE=endT.getMilliseconds();
											
											//alert(yS+"_"+mS+"_"+dS);
											//alert(yE+"_"+mE+"_"+dE);
											
											
											if(yS==yE&&mS==mE&&dS==dE){
												
												var lid="li_"+yS+"_"+mS+"_"+dS;
												if($("#"+lid)!=null){
													$("#"+lid).css({"background":"#FF99FF"});
												}
											}else if(yS==yE&&mS==mE&&dS!=dE){
											  //是否全天日程
											  if(this.allDay){//不是全天
			
												for(var i=0;i<((dE-dS)+((hE==0&&minE==0&&ssE==0) ?1:0));i++){
													
													var lid="li_"+yS+"_"+mS+"_"+(dS+i);
													
													if($("#"+lid)!=null){
														$("#"+lid).css({"background":"#FF99FF"});
													}
												}
											  }else{
											  	for(var i=0;i<=((dE-dS)-((hE==0&&minE==0&&ssE==0) ?1:0));i++){
													
													var lid="li_"+yS+"_"+mS+"_"+(dS+i);
													if($("#"+lid)!=null){
														$("#"+lid).css({"background":"#FF99FF"});
													}
												}
											  }
											}else if(yS==yE&&mS!=mE){
												var months=0;
													months=mE-mS;
													
													var monthData=new Array();
													for(var y=0,m=mS;y<=months;y++,m++){
														monthData[y]=m;
														
													}
													
													//获取时间间隔数
													var days=parseInt(Math.abs(startT - endT) / 1000 / 60 / 60 /24);
													//月数组
													var dayData =Array();
													
													//获取开始时间所在月的天数
													var sMonthDays=getDate(yS,mS);
													//获取结束时间所在月的天数
													var eMonthDays=getDate(yE,mE);	
													
													for(var y=0;y<monthData.length;y++){
														if(monthData[y]==mS){
															
															for(var x=0;x<=(sMonthDays-dS);x++){
																var lid="li_"+yS+"_"+(mS)+"_"+(dS+x);
																if($("#"+lid)!=null){
																	$("#"+lid).css({"background":"#FF99FF"});
																}
															}
														}else if(monthData[y]==mE){
															for(var x=0;x<=dE;x++){
																var lid="li_"+yE+"_"+(mE)+"_"+(x);
																
																if($("#"+lid)!=null){
																	$("#"+lid).css({"background":"#FF99FF"});
																}
															}
														}else{
															var mDays=getDate(yS,monthData[y]);
															
															for(var x=0;x<=mDays;x++){
																var lid="li_"+yS+"_"+(monthData[y])+"_"+(x);
																if($("#"+lid)!=null){
																	$("#"+lid).css({"background":"#FF99FF"});
																}
															}
														}
													}
											}else{
												//间隔年数
												var years=yE-yS;
												//间隔月数
												var months=0;
													months=((mE+12*years)-mS);
													//离12月还有几个月
													/*if(years!=0){
														months=12-mS;
													}else{
														months=mE-mS;
													}*/
													
												//获取时间间隔数
												var days=parseInt(Math.abs(startT - endT) / 1000 / 60 / 60 /24);
												
												//alert(months);
												
												//月数组
												var monthData =Array();
												
												//获取开始时间所在月的天数
												var sMonthDays=getDate(yS,mS);
												
												//获取结束时间所在月的天数
												var eMonthDays=getDate(yE,mE);
												
												for(var i=0,m=mS;i<=months;i++,m++){
													
													if(m==mS){//开始
														for(var x=0;x<=(sMonthDays-dS);x++){
															var lid="li_"+yS+"_"+(mS)+"_"+(dS+x);
															if($("#"+lid)!=null){
																$("#"+lid).css({"background":"#FF99FF"});
															}
														}
													}else if((yS+(parseInt(m/12))==yE)&&m%12==mE){//结束
														for(var x=0;x<=dE;x++){
															var lid="li_"+yE+"_"+(mE)+"_"+(x);
															
															if($("#"+lid)!=null){
																$("#"+lid).css({"background":"#FF99FF"});
															}
														}
														
													}else{
														var mDays=0;
														
														if(m>12){
															
															if(m%12==0){
																mDays=getDate((yS+(parseInt(m/12)))-1,m%12==0?12:m%12);
																
															}else{
																mDays=getDate(yS+(parseInt(m/12)),m%12==0?12:m%12);
																
															}
															
														}else{
															mDays=getDate(yS,m);
														}
														
														for(var x=1;x<=mDays;x++){
															var lid="";
															
															if(m>12){
																
																if(m%12==0){
																	lid="li_"+((yS+(parseInt(m/12)))-1)+"_"+(m%12==0?12:m%12)+"_"+(x);
																}else{
																	lid="li_"+(yS+(parseInt(m/12)))+"_"+(m%12==0?12:m%12)+"_"+(x);
																}
																
															}else{
																lid="li_"+yS+"_"+m+"_"+(x);
															}
															
															if($("#"+lid)!=null){
																$("#"+lid).css({"background":"#FF99FF"});
															}
														}
													}
													
													
												}
												
												
												
											}
											
										});
													               
									}
								});
				}
				//jquery-ui
				$(function(){		
					$('#dialog').dialog({
						showTitle:false,
						draggable:false,
						autoOpen:false,
						modal:true,
						title:'院历详情',
						width: 520,
						height: 320
						
					});
				});
				//获取院历按钮模板
				function getTem(text){
					return '<span class="fc-button-inner"><span class="fc-button-content">'+text+'</span><span class="fc-button-effect"><span></span></span></span>';
				}
				//去往具体时间
				function goToDate(year,month,day){
					$('#calendar').fullCalendar( 'gotoDate', year,month-1,day);
					$('#calendar').fullCalendar( 'changeView', "agendaDay" );
				}
				//跳转视图
				function gotoD(year,month){
					$('#calendar').fullCalendar( 'gotoDate', year,(month-1));
					$('#calendar').fullCalendar( 'changeView', "month" );
				}
				//查看院历
				function clickSchoolCalendar(id){
					$.post('<s:url value="findcalendar"/>',{"s.id":id},
					        	
								function(data){
								
									if(data.schedule!=null){
											//院校ID
											$.post('<s:url value="findacademy"/>',{"id":data.schedule.schoolId},
												function(data){
													$("#schoolName").html(data.academy.name);
												}
											,"json");
										
														//标题
											$("#title").html(data.schedule.title);
														//位置
											$("#location").html(data.schedule.location==''?'无':data.schedule.location);
														//是否全天
														
												
											
											
											if(data.schedule.isAllDay==1){
												$("#allDay").attr('checked',true);
												
											}else{
												$("#allDay").attr('checked',false);
												
											}
											$("#allDay").attr('disabled',true);
													
											if(data.schedule.isAllDay!=1){
												
												//开始时间
												$("#starttime").html(data.schedule.startDate);
												//结束时间
											    $("#endtime").html(data.schedule.endDate);
											    
											    
											}else{
												//开始时间
												$("#starttime").html(data.schedule.startDate);
												//结束时间
											    $("#endtime").html(data.schedule.endDate);
											    
											}	
													
											//重复类型
											switch(data.schedule.repeatType){
												case CAL_REPEAT_NONE:// 无
													$("#repeat").html("无");
													$("#endTr").css({"display":"none"});
													
													break;
												case CAL_REPEAT_DAY:// 每天
													$("#endTr").css({"display":"table-row"});
													$("#repeat").html("每天");
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													
													break;
												case CAL_REPEAT_WEEK:// 每周
													$("#repeat").html("每周");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
												case CAL_REPEAT_MONTH:// 每月
													$("#repeat").html("每月");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
												case CAL_REPEAT_YEAR:// 每年
													$("#repeat").html("每年");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
											}
											
														//颜色
											$("#color").css({'background-color':data.schedule.color});
														//邀请人
											$("#invite").html(data.schedule.invitee==''?'无':data.schedule.invitee);
														//备注
											$("#remark").html(data.schedule.content==''?'无':data.schedule.content);
											
											$('#dialog').dialog('open');
									}else{//院历被别人删除
										alert("院历已经被删除！");
									}
								}
							,"json");
				}
				$(document).ready(function() {
					$('#calendar').fullCalendar({
						//theme:true,//样式
						header: {
							left: 'prevYear,prev,today,next,nextYear',
							center: 'title',
							right: 'year,yearDetails,month,basicWeek,basicDay,agendaWeek,agendaDay'
						},
						defaultView:"year",
						weekends:true,//布尔类型, 默认为true, 标识是否显示周六和周日的列. 
						editable: false,
						selectable: false,
						selectHelper: false,
						allDayText:'今天的任务',
						axisFormat:'tth时',
						timeFormat:'tth时(mm分)至{tth时(mm分)}',
						buttonText:{
							 prev:     '昨天',
							 next:     '明天',
							 prevYear: '去年',	
							 nextYear: '明年',
							 today:    '今天',
							 month:    '月',
							 week:     '周',
							 basicWeek:'周',
							 agendaWeek:'周(详)',
							 basicDay:'日',
							 agendaDay:'日(详)',
							 year:'年',
							 yearDetails:'年(详)'
						},
						
					    eventClick: function(calEvent, jsEvent, view) {//单击院历
					    	
					        $.post('<s:url value="findcalendar"/>',{"s.id":calEvent.id},
					        	
								function(data){
								
									if(data.schedule!=null){
											//院校ID
											$.post('<s:url value="findacademy"/>',{"id":data.schedule.schoolId},
												function(data){
													$("#schoolName").html(data.academy.name);
												}
											,"json");
										
														//标题
											$("#title").html(data.schedule.title);
														//位置
											$("#location").html(data.schedule.location==''?'无':data.schedule.location);
														//是否全天
														
												
											
											
											if(data.schedule.isAllDay==1){
												$("#allDay").attr('checked',true);
												
											}else{
												$("#allDay").attr('checked',false);
												
											}
											$("#allDay").attr('disabled',true);
											//if(data.schedule.isAllDay!=1){
												
												//开始时间
												//$("#starttime").html(calEvent.start.pattern(DATE_TIME_FORMAT));
												//结束时间
											   // $("#endtime").html(calEvent.end.pattern(DATE_TIME_FORMAT));
											    
											    
											//}else{
												///开始时间
												//$("#starttime").html(calEvent.start.pattern("yyyy-MM-dd"));
												$("#starttime").html(data.schedule.startDate);
												
												//结束时间
											   // $("#endtime").html(calEvent.start.pattern("yyyy-MM-dd"));
												$("#endtime").html(data.schedule.endDate);
											    
											//}	
													
											//重复类型
											switch(data.schedule.repeatType){
												case CAL_REPEAT_NONE:// 无
													$("#repeat").html("无");
													$("#endTr").css({"display":"none"});
													
													break;
												case CAL_REPEAT_DAY:// 每天
													$("#endTr").css({"display":"table-row"});
													$("#repeat").html("每天");
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													
													break;
												case CAL_REPEAT_WEEK:// 每周
													$("#repeat").html("每周");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
												case CAL_REPEAT_MONTH:// 每月
													$("#repeat").html("每月");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
												case CAL_REPEAT_YEAR:// 每年
													$("#repeat").html("每年");
													$("#endTr").css({"display":"table-row"});
													if(data.schedule.repeatEndType==CAL_REPEAT_END_NEVER){
														$("#repeatInfo").html("永不");
													}else{
														$("#repeatInfo").html("于日期 "+data.schedule.repeatEndDate);
													}
													break;
											}
											
														//颜色
											$("#color").css({'background-color':data.schedule.color});
														//邀请人
											$("#invite").html(data.schedule.invitee==''?'无':data.schedule.invitee);
														//备注
											$("#remark").html(data.schedule.content==''?'无':data.schedule.content);
											
											$('#dialog').dialog('open');
									}else{//院历被别人删除
										alert("院历已经被删除！");
									}
								}
							,"json");
					        return false ;
					
					    },
						viewDisplay:function(view){
							// var $ifm=$(parent.document.body).find("iframe[id=home_sch]");
							 //alert($(parent.document.body).find("iframe[id=home_sch]").height());
							 //if($.browser.msie&&$.browser.version=="6.0"&&$ifm[0].scrollHeight>$(parent.document.body).find("iframe[id=home_sch]").height()){
							//	 $(parent.document.body).find("iframe[id=home_sch]").css("overflowY","scroll");
							 // }
							//切换视图
							if(view.name=="year"){//年
								//本年
								$(".fc-button-today").html(getTem("今年"));
								$(".fc-button-prev").html(getTem("上月"));
								$(".fc-button-next").html(getTem("下月"));
							}else if(view.name=="month"){//月
								$(".fc-button-today").html(getTem("本月"));
								$(".fc-button-prev").html(getTem("上月"));
								$(".fc-button-next").html(getTem("下月"));
							}else if(view.name=="basicWeek"||view.name=="agendaWeek"){//周
								$(".fc-button-today").html(getTem("本周"));
								$(".fc-button-prev").html(getTem("上周"));
								$(".fc-button-next").html(getTem("下周"));
							}else if(view.name=="basicDay"||view.name=="agendaDay"){//天
								$(".fc-button-today").html(getTem("今天"));
								$(".fc-button-prev").html(getTem("昨天"));
								$(".fc-button-next").html(getTem("明天"));
							}
							
							//获取院历数据
							if(view.name=="year"){
							
								//设置最小高度
								//$(".main").css({"min-height":"180px"});
								
								$("#yearViewDiv table tbody tr").css({"display":""});
								//alert(view.title);
								var yStr=view.title.substring(0,view.title.indexOf('年'));
								var startDateTime=yStr+"-01-01 00:00:00";
								var endDateTime=yStr+"-12-31 23:59:59";
								getYearView(startDateTime,endDateTime);
								//修复年视图不兼容问题
								$("td").removeClass("fc-state-highlight");
								
							}else if(view.name=="yearDetails"){
							
								//修复年视图不兼容问题
								$(".fc-border-separate tbody tr").css({'display':''});
								$.ajax({
									<c:if test="${id==0}">
										url: 'calendarlist',
										dataType: 'json',
										data: {
											'start': (view.start).pattern(DATE_TIME_FORMAT),
									         'end': (view.end).pattern(DATE_TIME_FORMAT),
									         'random': Math.random()
										},
									</c:if>
									<c:if test="${id!=0}">
									
												url: 'calendarlist_id',
												dataType: 'json',
												data: {
													'start': (view.start).pattern(DATE_TIME_FORMAT),
									                'end': (view.end).pattern(DATE_TIME_FORMAT),
											         'id': ${id},
											         'random': Math.random()
												},
									</c:if>
								       
								            success: function(doc) {
								            	var yStr=view.title.substring(0,view.title.indexOf('年'));
								            	//清空
								            	for(var i=1;i<=12;i++){
								            		$(".dt_"+yStr+"_"+i).html("");
								            		
								            	}
								            	
								                $(doc.schedules).each(function(){
								                	var st=this.start.replace("-","#").replace("-","##");
								                	var et=this.end.replace("-","#").replace("-","##");
								                	var sy=st.substring(0,st.indexOf("#"));
								                	var sm=parseInt(st.substring(st.indexOf("#")+1,st.indexOf("##")),10);
								                	
								                	var ey=et.substring(0,et.indexOf("#"));
								                	var em=parseInt(et.substring(et.indexOf("#")+1,et.indexOf("##")),10);
								                	//alert(yStr);
								                	//if(sy==yStr&&ey==yStr&&sy==ey&&sm==em){
								                	
								                	if(sy==yStr||ey==yStr){
								                		var repeatStr="";
								                		//重复类型
															switch(this.repeatType){
																case -1:// 无
																	repeatStr+="不重复";
																	break;
																case 1:// 每天
																	repeatStr+="每天重复";
																	if(this.repeatEndType==-1){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																case 2:// 每周
																	repeatStr+="每周重复";
																	if(this.repeatEndType==-1){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
																case 3:// 每月
																	repeatStr+="每月重复";
																	if(this.repeatEndType==-1){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
																case 4:// 每年
																	repeatStr+="每年重复";
																	if(this.repeatEndType==-1){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
															}
									                		var year=new Date(this.start.replace(/-/g,'/')).pattern('yyyy');
									                		var month=new Date(this.start.replace(/-/g,'/')).pattern('M');
									                		//var gotoStr="gotoD("+year+","+month+")";
									                	
									                		
									                		
									                		var gotoStr="clickSchoolCalendar("+this.id+")";
									                		
									                		if($(".dt_"+yStr+"_"+sm+"_"+this.id).html()==null){
										                		$(".dt_"+yStr+"_"+sm).append((
										                		"<dl class='"+"dt_"+yStr+"_"+sm+"_"+this.id+"' onclick='"+gotoStr+"' style='cursor: pointer;background-color:"+this.color+";border-color:"+this.color+"'>["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]["
										                		+new Date(this.start.replace(/-/g,"/")).pattern("MM月dd日")+"-"+new Date(this.end.replace(/-/g,"/")).pattern("MM月dd日")+"]"
										                		+"["+repeatStr+"]"
										                		+(this.location==""?"":("[地点："+this.location+"]"))
										                		+(this.invitee==""?"":("[参加人:"+this.invitee+"]"))
										                		+this.title+"</dl>").replaceAll("null","无"));
									                		}
									                		
									                		if($(".dt_"+yStr+"_"+em+"_"+this.id).html()==null){
									                			$(".dt_"+yStr+"_"+em).append((
												                		"<dl class='"+"dt_"+yStr+"_"+em+"_"+this.id+"' onclick='"+gotoStr+"' style='cursor: pointer;background-color:"+this.color+";border-color:"+this.color+"'>["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]["
												                		+new Date(this.start.replace(/-/g,"/")).pattern("MM月dd日")+"-"+new Date(this.end.replace(/-/g,"/")).pattern("MM月dd日")+"]"
												                		+"["+repeatStr+"]"
												                		+(this.location==""?"":("[地点："+this.location+"]"))
												                		+(this.invitee==""?"":("[参加人:"+this.invitee+"]"))
												                		+this.title+"</dl>").replaceAll("null","无"));
									                		}
									                		
								                	}
								                	
								                		/*id:this.id,  //id
														title: this.title,//标题
														start: this.start,//开始时间
														end: this.end,//结束时间
														allDay: this.allDay==0?false:true,//是否为全天院历
														color:this.color//院历北京颜色*/
								                
								                });
								                
								            }
						         });
							}else{								
								$.ajax({
									<c:if test="${id==0}">
										url: 'calendarlist',
										dataType: 'json',
										data: {
											'start': (view.start).pattern(DATE_TIME_FORMAT),
								             'end': (view.end).pattern(DATE_TIME_FORMAT),
									         'random': Math.random()
										},
									</c:if>
									<c:if test="${id!=0}">
									
												url: 'calendarlist_id',
												dataType: 'json',
												data: {
													'start': (view.start).pattern(DATE_TIME_FORMAT),
								                	'end': (view.end).pattern(DATE_TIME_FORMAT),
											         'id': ${id},
											         'random': Math.random()
												},
									</c:if>
								           
								            success: function(doc) {
								            
								            	 $(doc.schedules).each(function(){
								                		$('#calendar').fullCalendar( 'removeEvents',this.id);
								                		
								                });
								                $(doc.schedules).each(function(){
								                		//$('#calendar').fullCalendar( 'removeEvents',this.id);
								                		//增加院历到视图当中
														$('#calendar').fullCalendar('renderEvent',{
																id:this.id,  //id
																title: "["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]"+this.title,//标题
																start: this.start,//开始时间
																end: this.end,//结束时间
																allDay: this.allDay==0?false:true,//是否为全天院历
																color:this.color//院历北京颜色
															},
															false // make the event "stick"
														);
								                
								                });
								                
								            }
						         });
							}
							
						}
					});
					
				});
				
		</script>
		<style type='text/css'>
			#calendar {
				width: 100%;
				margin: 0 auto;
			}

		</style>
	</head>
	<body>
		<c:if test="${id!=0}">
				<!--头部层开始 -->
				<head:head title="院校名称：">
					<html:a text="返回" icon="return" onclick="history.go(-1);"/>
				</head:head>
		</c:if>
		<!--主体层开始 -->
		<body:body>
			<div id='calendar'></div>
				<div id="dialog" style="display:none">
					<table  width="100%" border="0" cellpadding="2" cellspacing="0" >
						<tbody>
							<tr>
								<td width="70px" align="right">院校：</td>
								<td align="left">
										<span id="schoolName"></span>
								</td>
							</tr>
							<tr>
								<td width="70px" align="right">标题：</td>
								<td align="left">
									
									<span id="title"></span>
								</td>
							</tr>
							<tr>
								<td width="70px" align="right">位置：</td>
								<td align="left">
									<span id="location"></span>
								</td>
							</tr>
							<tr>
								<td width="70px" align="right">全天：</td>
								<td align="left">
									<input id="allDay"  type="checkbox" />
								</td>
							</tr>
							<tr>
								<td width="70px" align="right">从</td>
								<td align="left">
									<span id="starttime"></span>
								</td>
							</tr>
							<tr>
								<td width="70px" align="right">到</td>
								<td align="left">
									<span id="endtime"></span>
								</td>
							</tr>
							
							<tr>
								<td width="70px" align="right">重复</td>
								<td align="left">
									
									<span id="repeat"></span>
								</td>
							</tr>
							
							<tr id="endTr">
								<td width="70px" align="right">结束</td>
								<td align="left">
									<span id="repeatInfo"></span>
								</td>
							</tr>
							
							<tr>
								<td width="70px" align="right">颜色：</td>
								<td align="left">
									  <div id="color" style="width: 50px;">&nbsp;&nbsp;</div>
								</td>
							</tr>
						
								<td width="70px" align="right">邀请人：</td>
								<td align="left">
									
									
									<div id="invite"></div>
								</td>
							</tr>
							<tr>
								<td width="70px" align="right" valign="top">备注：</td>
								<td align="left">
									
									
									<div id="remark"></div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</body:body>
	</body>

</html>