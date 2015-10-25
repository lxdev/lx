	//全局变量 
	var feebatchlsttotal=0// feebatchlsttotal列表的总条数
	var feebatchcount=0;
	var feepropertycount=0;
	var billreceivedwaycount=0;
	
	$(function(){
	
		loadFeeWay();
		
		//点击显示列表链接时，如果已加载列表就直接显示列表，否则Ajax加载对应列表
		$("#showfee_batch").click(function(){
			if($('#feebatchtable > tbody').html()==""&&$("#showfee_batch").html()=="显示"){
				loadFeeBatch();
			}
		});
		//点击显示列表链接时，如果已加载列表就直接显示列表，否则Ajax加载对应列表
		$("#showfee_subject").click(function(){
			if($('#feesubjecttable > tbody').html()==""&&$("#showfee_subject").html()=="显示"){
				loadFeeSubject();
			}
		});
		//点击显示列表链接时，如果已加载列表就直接显示列表，否则Ajax加载对应列表
		$("#showfee_property").click(function(){
			if($('#feepropertytable > tbody').html()==""&&$("#showfee_property").html()=="显示"){
				loadFeeProperty();
			}
		});
		//点击显示列表链接时，如果已加载列表就直接显示列表，否则Ajax加载对应列表
		$("#showbill_received_way").click(function(){
			if($('#billreceivedwaytable > tbody').html()==""&&$("#showbill_received_way").html()=="显示"){
				loadBillReceivedWay();
			}
		});
	});
	
//-----------------------------------------------------------------缴费方式--------------------------------------------------------------	
	//加载缴费方式信息
	function loadFeeWay(){
		$.post(WEB_PATH+'/basesetting/basedict/list_base_dict_flag',{basedicttype:BASEDICT_STYLE_FEEWAY},
			function(data){
				var list='';
				if(data.lstrltbool){
					if(data.basedictlst.length>0){
						$.each(data.basedictlst, function(){
							list+='<tr>';
							list+='<td align="center" width="60%">';
							list+=this.name;
							list+='</td>';
						    list+='<td align="center" width="40%">';
							list+=this.code;
							list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="2" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#feewaytable > tbody').html(list);
					//显示斑马线
					zebraCrossing();
				}else{
					alert("数据异常，请稍后尝试操作");
				}				
			},"json");
	}
	
//-----------------------------------------------------------------缴费次数--------------------------------------------------------------
	//加载缴费次数信息
	function loadFeeBatch(){
		$.post('listshowfeebatch',
			function(data){
				var list='';
				if(data.feebatchrltbool){
					if(data.feebatchlst.length>0){
						$.each(data.feebatchlst, function(){
							list+='<tr>';
							list+='<td align="center">';
							list+='<div id="fbnow'+this.id+'">';
							list+=this.name;
							list+='</div>';
							list+='<div id="fbclick'+this.id+'" style="display:none;">';
							list+='<input id="fbname'+this.id+'" value=""/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="fbnow'+this.id+'">';
							list+=this.code;
							list+='</div>';
							list+='<div id="fbclick'+this.id+'" style="display:none;">';
							list+='<input id="fbcode'+this.id+'" value=""/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="fbnow'+this.id+'">';
							list+=this.startTime.substring(5,10);
							list+='</div>';
							list+='<div id="fbclick'+this.id+'" style="display:none;">';
							list+='<input id="startdate'+this.id+'" value="" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'+"'MM-dd'"+'})"/>';
							list+='</div>';
							list+='</td>';
							list+='<td align="center">';
							list+='<div id="fbnow'+this.id+'">';
							list+=this.endTime.substring(5,10);
							list+='</div>';
							list+='<div id="fbclick'+this.id+'" style="display:none;">';
							list+='<input id="enddate'+this.id+'" value="" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'+"'MM-dd'"+'})"/>';
							list+='</div>';
							list+='</td>';
							list+='<td align="center">';
							list+='<div id="fbnow'+this.id+'">';
							list+='第'+this.belongYear+'年';
							list+='<input id="belongyearinput'+this.id+'" value="'+this.belongYear+'" type="hidden"/>';
							list+='</div>';
							list+='<div id="fbclick'+this.id+'" style="display:none;">';
							//动态添加所属年份
							
							list+='<select id="belongyear'+this.id+'">';
							if(data.feebatchlst.length<=1){
								list+='<option value="1">第1年</option>';
							}else if(data.feebatchlst.length>1){
								for(var i=0;i<data.feebatchlst.length;i++){
									list+='<option value="'+(i+1)+'">第'+(i+1)+'年</option>';
								}
							}
							list+='</select>';
											
							
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
						    list+='<a href="#" id="updatefeebatch'+this.id+'" onclick="changeFeeBatchBtnValue('+this.id+')">修改</a>';
						    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletefeebatch'+this.id+'" onclick="deleteOrCancelFeeBatch('+this.id+')">删除</a>';
						    list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="6" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#feebatchtable > tbody').html(list);
					//显示斑马线
					zebraCrossing();
				}else{
					alert("数据异常，请稍后尝试操作");
				}	
				feebatchlsttotal=data.feebatchlst.length;
			},"json");
	}
	
	//增加一行输入框，用于填写缴费次数信息
	function addFeeBatch(){
			$('#feebatchtable > tbody tr.error').remove();
			var list='';
			var selectlist="";
			
			if($('#feebatchtable > tfoot').html()==""){
					feebatchcount=++feebatchcount;
					list=$('#feebatchtable > tfoot').html();
					list+='<tr id="feebatchtr'+feebatchcount+'">';
				    list+='<td align="center">'; 
				    list+='<input id="feebatchname'+feebatchcount+'" class="txt_box_150" />';
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='<input id="feebatchcode'+feebatchcount+'" class="txt_box_150" />';
				    list+='</td>'; 
				    list+='<td align="center">';
				    list+='<input id="starttime'+feebatchcount+'" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'+"'MM-dd'"+'})"/>';
				    list+='</td>';
				    list+='<td align="center">';
					list+='<input id="endtime'+feebatchcount+'" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'+"'MM-dd'"+'})"/>';
					list+='</td>';
					list+='<td align="center">';
					//动态添加所属年份
					selectlist+='<select id="feebatchbelongyear'+feebatchcount+'">';
					if((feebatchlsttotal+1)<=1){
						selectlist+='<option value="1">第1年</option>';
					}else if((feebatchlsttotal+1)>1){
						for(var i=0;i<(feebatchlsttotal+1);i++){
							selectlist+='<option value="'+(i+1)+'">第'+(i+1)+'年</option>';
						}
					}
					selectlist+='</select>';
					
					list+=selectlist;	
					list+='</td>';
				    list+='<td align="center">';  
				    list+='<a href="#" onclick="saveFeeBatch('+feebatchcount+')">保存</a>';	 
				    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelFeeBatch('+feebatchcount+')">取消</a>'; 
				    list+='</td>';
				    list+='</tr>';
				    $('#feebatchtable > tfoot').html(list);
				  //显示斑马线
					zebraCrossing();
			}
	}
	
	//取消一行尚未保存的输入框
	function cancelFeeBatch(feebatchcount){
		$("#feebatchtr"+feebatchcount).remove();
	}
	
	//保存当前一行输入的缴费次数信息
	function saveFeeBatch(feebatchcount){
		if($("#feebatchname"+feebatchcount).val()==null||$("#feebatchname"+feebatchcount).val()==""||$.trim($("#feebatchname"+feebatchcount).val())==""){
			alert("请录入完整数据");
		}else if($("#feebatchcode"+feebatchcount).val()==null||$("#feebatchcode"+feebatchcount).val()==""||$.trim($("#feebatchcode"+feebatchcount).val())==""){
			alert("请录入完整数据");
		}else if($("#starttime"+feebatchcount).val()==null||$("#starttime"+feebatchcount).val()==""||$.trim($("#starttime"+feebatchcount).val())==""){
			alert("请录入完整数据");
		}else if($("#endtime"+feebatchcount).val()==null||$("#endtime"+feebatchcount).val()==""||$.trim($("#endtime"+feebatchcount).val())==""){
			alert("请录入完整数据");
		}else{
			$.post('addfinanceproperty', {'feebatch.name':$("#feebatchname"+feebatchcount).val(),'feebatch.code':$("#feebatchcode"+feebatchcount).val(),
					'feebatch.startTime':'2000-'+$("#starttime"+feebatchcount).val(),'feebatch.endTime':'2000-'+$("#endtime"+feebatchcount).val(),
					'feebatch.belongYear':$("#feebatchbelongyear"+feebatchcount).val()},
		  	function(data) 
		  	{
		  		if(data.addrltbool)
		  		{
		  			$("#feebatchtr"+feebatchcount).remove();
		  			loadFeeBatch();
		  		}else{
		  			alert("数据异常，请稍后尝试添加操作");
		  			loadFeeBatch();
		  		}
		  		
		  	},"json");  
		}
	}
	
	//切换是否可以修改信息,保存修改信息(缴费次数)
			function changeFeeBatchBtnValue(id){
				var updatebtn=$('#updatefeebatch'+id).html();//获取保存/修改对象
				var deletebtn=$('#deletefeebatch'+id).html();//获取取消/删除对象
				if(updatebtn=="修改"){  	
					//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
					$('#fbname'+id).val($("div[id='fbnow"+id+"']:nth(0)").html());
					$('#fbcode'+id).val($("div[id='fbnow"+id+"']:nth(1)").html());
					$('#startdate'+id).val($("div[id='fbnow"+id+"']:nth(2)").html());
					$('#enddate'+id).val($("div[id='fbnow"+id+"']:nth(3)").html());
					$('#belongyear'+id).val($("div[id='fbnow"+id+"']:nth(4) input[id='belongyearinput"+id+"']").val());
					$('#updatefeebatch'+id).html("保存");
					$('#deletefeebatch'+id).html("取消");
					$("div[id='fbnow"+id+"']").hide();
					$("div[id='fbclick"+id+"']").show();
				}else{
					if($("#fbname"+id).val()==null||$("#fbname"+id).val()==""||$.trim($("#fbname"+id).val())==""){
		    			alert("请录入完整数据");
		    		}else if($("#fbcode"+id).val()==null||$("#fbcode"+id).val()==""||$.trim($("#fbcode"+id).val())==""){
		    			alert("请录入完整数据");
		    		}else{
		    			$.post('updatefinanceproperty', {feebatchid:id,'feebatch.name':$("#fbname"+id).val(),'feebatch.code':$("#fbcode"+id).val(),
		    											'feebatch.startTime':'2000-'+$('#startdate'+id).val(),'feebatch.endTime':'2000-'+$('#enddate'+id).val(),
		    											'feebatch.belongYear':$('#belongyear'+id).val()},
					  	function(data) 
					  	{
					  		if(data.updaterltbool)
					  		{
					  			loadFeeBatch();
					  		}else{
					  			alert("数据异常，请稍后尝试修改操作");
					  			loadFeeBatch();
					  		}
					  		
					  	},"json");  
					  	
					$('#updatefeebatch'+id).html("修改");
					$('#deletefeebatch'+id).html("删除");
					$("div[id='fbnow"+id+"']").show();
					$("div[id='fbclick"+id+"']").hide();
		    		}
				}
			}
			
	//删除当前选定的一条缴费次数或是取消当前选定缴费次数的可编辑状态
	function deleteOrCancelFeeBatch(id){
		var deletebtn=$('#deletefeebatch'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatefeebatch'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatefeebatch'+id).html("修改");
			$('#deletefeebatch'+id).html("删除");
			$("div[id='fbnow"+id+"']").show();
			$("div[id='fbclick"+id+"']").hide();
		}else{
			var isdel=confirm("您确定要删除吗？");
			if(isdel){
			
			$.post('deletefinancepropertystatus', {feebatchid:id},
			  	function(data) 
			  	{
			  		if(data.delrltbool)
			  		{
			  			loadFeeBatch();
			  		}else{
			  			alert("数据异常，请稍后尝试删除操作");
			  			loadFeeBatch();
			  		}
			  		
			  	},"json");  
			 	
			}
		}
	}
	
//--------------------------------------------费用科目设置------------------------------------------------------------------------
	
	//加载费用科目信息
	function loadFeeSubject(){
		$.post('listshowfeesubjectbydeleteflag',
			function(data){
				var list='';
				if(data.feesubjectrltbool){
					if(data.feesubjectlst.length>0){
						$.each(data.feesubjectlst, function(){
							list+='<tr>';
							list+='<td align="center">';
							list+='<div id="fsnow'+this.id+'">';
							list+=this.name;
							list+='</div>';
							list+='<div id="fsclick'+this.id+'" style="display:none;">';
							list+='<input id="fsname'+this.id+'" value="" maxlength="32"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="fsnow'+this.id+'">';
							if(this.code==null||this.code==""){
								list+='';
							}else{
								list+=this.code;
							}
							list+='</div>';
							list+='<div id="fsclick'+this.id+'" style="display:none;">';
							list+='<input id="fscode'+this.id+'" value="" disabled="disabled"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="fsnow'+this.id+'">';
							if(this.batchType==1){
								list+='招生阶段';	
							}else if(this.batchType==0){
								list+='非招生阶段';
							}
							list+='<input id="batchtypeinput'+this.id+'" value="'+this.batchType+'" type="hidden"/>';
							list+='</div>';
							list+='<div id="fsclick'+this.id+'" style="display:none;">';
							list+='<select id="batchtype'+this.id+'"><option value="1">招生阶段</option><option value="0">非招生阶段</option></select>';
							list+='</div>';
							list+='</td>';
							
							list+='<td align="center">';
							list+='<div id="fsnow'+this.id+'">';
							if(this.isMultiplePayment==1){
								list+='多次';	
							}else if(this.isMultiplePayment==0){
								list+='单次';
							}
							list+='<input id="ismultiplepaymentinput'+this.id+'" value="'+this.isMultiplePayment+'" type="hidden"/>';
							list+='</div>';
							list+='<div id="fsclick'+this.id+'" style="display:none;">';
							list+='<select id="ismultiplepayment'+this.id+'"><option value="1">多次</option><option value="0">单次</option></select>';
							list+='</div>';
							list+='</td>';
								
						    list+='<td align="center">';
						    if(this.id>INT_FEESUBJECT_ID){
							    list+='<a href="#" id="updatefeesubject'+this.id+'" onclick="changeFeeSubjectBtnValue('+this.id+')">修改</a>';
							    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletefeesubject'+this.id+'" onclick="deleteOrCancelFeeSubject('+this.id+')">删除</a>';
						    }else{
						    	list+='--';
						    }
						    list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="4" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#feesubjecttable > tbody').html(list);
					//显示斑马线
					zebraCrossing();
				}else{
					show('error_Msg','信息提示:',320,100);
				}	
			},"json");
	}
	//增加一行输入框，用于填写费用科目信息
	function addFeeSubject(){
			$('#feesubjecttable > tbody tr.error').remove();
			var list='';
			
			if($('#feesubjecttable > tfoot').html()==""){
					list=$('#feesubjecttable > tfoot').html();
					list+='<tr id="feesubjecttr">';
				    list+='<td align="center">'; 
				    list+='<input id="feesubjectname" class="txt_box_150" maxlength="32"/>';
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='';
				    list+='</td>'; 
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='<select id="feesubjectbatchtype"><option value="1">招生阶段</option><option value="0">非招生阶段</option></select>';
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='<select id="feesubjectismultiplepayment"><option value="1">多次</option><option value="0">单次</option></select>';
				    list+='</td>'; 
				    list+='<td align="center">';  
				    list+='<a href="#" onclick="saveFeeSubject()">保存</a>';	 
				    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelFeeSubject()">取消</a>'; 
				    list+='</td>';
				    list+='</tr>';
				    $('#feesubjecttable > tfoot').html(list);
				  //显示斑马线
					zebraCrossing();
			}
	}
	
	//取消一行尚未保存的输入框(费用科目)
	function cancelFeeSubject(){
		$("#feesubjecttr").remove();
	}
	
	//保存当前一行输入的费用科目信息
	function saveFeeSubject(feesubjectcount){
		if($("#feesubjectname").val()==null||$("#feesubjectname").val()==""||$.trim($("#feesubjectname").val())==""){
			show('fill_in_info_error','信息提示:',250,100); 
		}else{
			$.post('addfinanceproperty', {'feesubject.name':$("#feesubjectname").val(),
										  'feesubject.batchType':$("#feesubjectbatchtype").val(),
										  'feesubject.isMultiplePayment':$("#feesubjectismultiplepayment").val()},
		  	function(data) 
		  	{
			    if(!data.addrltbool){
					show('error_Msg','信息提示:',320,100);
				}else if(data.errormsg){
					$("#feesubjecttr").remove();
		  			show('success_Msg','信息提示:',250,100); 
		  		}else{
		  			show('add_error_Msg','信息提示:',320,100);
		  		}
		  		loadFeeSubject();
		  	},"json");  
		}
	}
	
	//删除当前选定的一条费用科目或是取消当前选定费用科目的可编辑状态
	function deleteOrCancelFeeSubject(id){
		var deletebtn=$('#deletefeesubject'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatefeesubject'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatefeesubject'+id).html("修改");
			$('#deletefeesubject'+id).html("删除");
			$("div[id='fsnow"+id+"']").show();
			$("div[id='fsclick"+id+"']").hide();
		}else{
			var isdel=confirm("您确定要删除吗？");
			if(isdel){
			
			$.post('deletefinancepropertystatus', {feesubjectid:id},
			  	function(data) 
			  	{
			  		if(data.delrltbool)
			  		{
			  			show('success_Msg','信息提示:',250,100); 
			  		}else{
			  			show('error_Msg','信息提示:',320,100);
			  		}
			  		loadFeeSubject();
			  	},"json");  
			 	
			}
		}
	}
	
	//切换是否可以修改信息,保存修改信息(费用科目)
	function changeFeeSubjectBtnValue(id){
		var updatebtn=$('#updatefeesubject'+id).html();//获取保存/修改对象
		var deletebtn=$('#deletefeesubject'+id).html();//获取取消/删除对象
		if(updatebtn=="修改"){  	
			//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
			$('#fsname'+id).val($("div[id='fsnow"+id+"']:nth(0)").html());
			$('#fscode'+id).val($("div[id='fsnow"+id+"']:nth(1)").html());
			$('#batchtype'+id).val($("div[id='fsnow"+id+"']:nth(2) input[id='batchtypeinput"+id+"']").val());
			$('#ismultiplepayment'+id).val($("div[id='fsnow"+id+"']:nth(3) input[id='ismultiplepaymentinput"+id+"']").val());
			$('#updatefeesubject'+id).html("保存");
			$('#deletefeesubject'+id).html("取消");
			$("div[id='fsnow"+id+"']").hide();
			$("div[id='fsclick"+id+"']").show();
		}else{
			if($("#fsname"+id).val()==null||$("#fsname"+id).val()==""||$.trim($("#fsname"+id).val())==""){
				show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			$.post('updatefinanceproperty', {feesubjectid:id,'feesubject.name':$("#fsname"+id).val(),
    											'feesubject.batchType':$('#batchtype'+id).val(),
    											'feesubject.isMultiplePayment':$("#ismultiplepayment"+id).val()},
			  	function(data) 
			  	{
					if(!data.updaterltbool){
						show('error_Msg','信息提示:',320,100);
					}else if(data.sameinfobool){
						show('success_Msg','信息提示:',250,100);
			  		}else{
			  			show('add_error_Msg','信息提示:',320,100);
			  		}
			  		loadFeeSubject();
			  	},"json");  
			  	
			$('#updatefeesubject'+id).html("修改");
			$('#deletefeesubject'+id).html("删除");
			$("div[id='fsnow"+id+"']").show();
			$("div[id='fsclick"+id+"']").hide();
    		}
		}
	}

//---------------------------------------------------费用性质--------------------------------------------------------------------------------------
	
	//加载费用性质信息
	function loadFeeProperty(){
		$.post(WEB_PATH+'/basesetting/basedict/list_base_dict_flag',{basedicttype:BASEDICT_STYLE_FEEPROPERTY},
			function(data){
				var list='';
				if(data.lstrltbool){
					if(data.basedictlst.length>0){
						$.each(data.basedictlst, function(){
							list+='<tr>';
							list+='<td align="center">';
							list+='<div id="fpnow'+this.id+'">';
							list+=this.name;
							list+='</div>';
							list+='<div id="fpclick'+this.id+'" style="display:none;">';
							list+='<input id="fpname'+this.id+'" value="" maxlength="128"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="fpnow'+this.id+'">';
							if(this.code==null||this.code==""){
								list+='';
							}else{
								list+=this.code;
							}
							list+='</div>';
							list+='<div id="fpclick'+this.id+'" style="display:none;">';
							list+='<input id="fpcode'+this.id+'" value="" disabled="disabled"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
						    list+='<a href="#" id="updatefeeproperty'+this.id+'" onclick="changeFeePropertyBtnValue('+this.id+')">修改</a>';
						    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletefeeproperty'+this.id+'" onclick="deleteOrCancelFeeProperty('+this.id+')">删除</a>';
						    list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="3" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#feepropertytable > tbody').html(list);
					//显示斑马线
					zebraCrossing();
				}else{
					show('error_Msg','信息提示:',320,100);
				}	
			},"json");
	}
	
	//增加一行输入框，用于填写费用性质信息
	function addFeeProperty(){
			$('#feepropertytable > tbody tr.error').remove();
			var list='';
			
			if($('#feepropertytable > tfoot').html()==""){
					list=$('#feepropertytable > tfoot').html();
					list+='<tr id="feepropertytr">';
				    list+='<td align="center">'; 
				    list+='<input id="feepropertyname" class="txt_box_150" maxlength="128"/>';
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='';
				    list+='</td>'; 
				    list+='</td>'; 
				    list+='<td align="center">';  
				    list+='<a href="#" onclick="saveFeeProperty()">保存</a>';	 
				    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelFeeProperty()">取消</a>'; 
				    list+='</td>';
				    list+='</tr>';
				    $('#feepropertytable > tfoot').html(list);
				  //显示斑马线
					zebraCrossing();
			}
	}
	
	//取消一行尚未保存的输入框（费用性质）
	function cancelFeeProperty(feesubjectcount){
		$("#feepropertytr").remove();
	}
	
	//保存当前一行输入的费用性质信息
	function saveFeeProperty(feepropertycount){
		if($("#feepropertyname").val()==null||$("#feepropertyname").val()==""||$.trim($("#feepropertyname").val())==""){
			show('fill_in_info_error','信息提示:',250,100); 
		}else{
			$.post(WEB_PATH+'/basesetting/basedict/add_base_dict', {'basedict.name':$("#feepropertyname").val(),
												 'basedict.type':BASEDICT_STYLE_FEEPROPERTY},
		  	function(data) 
		  	{
			   if(!data.addrltbool){
					show('error_Msg','信息提示:',320,100);
				}else if(data.errormsg){
					$("#feepropertytr").remove();
		  			show('success_Msg','信息提示:',250,100); 
		  		}else{
		  			show('add_error_Msg','信息提示:',320,100);
		  		}
			   loadFeeProperty();	
		  	},"json");  
		}
	}
	
	//删除当前选定的一条费用性质或是取消当前选定费用性质的可编辑状态
	function deleteOrCancelFeeProperty(id){
		var deletebtn=$('#deletefeeproperty'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatefeeproperty'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatefeeproperty'+id).html("修改");
			$('#deletefeeproperty'+id).html("删除");
			$("div[id='fpnow"+id+"']").show();
			$("div[id='fpclick"+id+"']").hide();
		}else{
			var isdel=confirm("您确定要删除吗？");
			if(isdel){
			
			$.post(WEB_PATH+'/basesetting/basedict/delete_base_dict', {basedictid:id},
			  	function(data) 
			  	{
					if(data.delrltbool){
			  			show('success_Msg','信息提示:',250,100); 
			  		}else{
			  			cancelEnrollmentWay();
			  			show('error_Msg','信息提示:',320,100);
			  		}
			  		loadFeeProperty();
			  	},"json");  
			 	
			}
		}
	}
	
	//切换是否可以修改信息,保存修改信息(费用性质)
	function changeFeePropertyBtnValue(id){
		var updatebtn=$('#updatefeeproperty'+id).html();//获取保存/修改对象
		var deletebtn=$('#deletefeeproperty'+id).html();//获取取消/删除对象
		if(updatebtn=="修改"){  	
			//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
			$('#fpname'+id).val($("div[id='fpnow"+id+"']:nth(0)").html());
			$('#fpcode'+id).val($("div[id='fpnow"+id+"']:nth(1)").html());
			$('#updatefeeproperty'+id).html("保存");
			$('#deletefeeproperty'+id).html("取消");
			$("div[id='fpnow"+id+"']").hide();
			$("div[id='fpclick"+id+"']").show();
		}else{
			if($("#fpname"+id).val()==null||$("#fpname"+id).val()==""||$.trim($("#fpname"+id).val())==""){
				show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			$.post(WEB_PATH+'/basesetting/basedict/update_base_dict', {basedictid:id,'basedict.name':$("#fpname"+id).val()},
			  	function(data) 
			  	{
    				if(!data.updaterltbool){
    					show('error_Msg','信息提示:',320,100);
    				}else if(data.sameinfobool){
    					show('success_Msg','信息提示:',250,100);
    		  		}else{
    		  			show('add_error_Msg','信息提示:',320,100);
    		  		}
			  		loadFeeProperty();
			  	},"json");  
			  	
			$('#updatefeeproperty'+id).html("修改");
			$('#deletefeeproperty'+id).html("删除");
			$("div[id='fpnow"+id+"']").show();
			$("div[id='fpclick"+id+"']").hide();
    		}
		}
	}
	
//---------------------------------------------------------其他应收类型设置-----------------------------------------------------------------------
	
	//加载其他应收类型信息
	function loadBillReceivedWay(){
		$.post(WEB_PATH+'/basesetting/basedict/list_base_dict_flag',{basedicttype:BASEDICT_STYLE_BILLRECEIVEDWAY},
			function(data){
				var list='';
				if(data.lstrltbool){
					if(data.basedictlst.length>0){
						$.each(data.basedictlst, function(){
							list+='<tr>';
							list+='<td align="center">';
							list+='<div id="brwnow'+this.id+'">';
							list+=this.name;
							list+='</div>';
							list+='<div id="brwclick'+this.id+'" style="display:none;">';
							list+='<input id="brwname'+this.id+'" value="" maxlength="128"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
							list+='<div id="brwnow'+this.id+'">';
							if(this.code==null||this.code==""){
								list+='';
							}else{
								list+=this.code;
							}
							list+='</div>';
							list+='<div id="brwclick'+this.id+'" style="display:none;">';
							list+='<input id="brwcode'+this.id+'" value="" disabled="disabled"/>';
							list+='</div>';
							list+='</td>';
						    list+='<td align="center">';
						    if(this.id>INIT_BASE_DICT_ID){
						    	 list+='<a href="#" id="updatebillreceivedway'+this.id+'" onclick="changeBillReceivedWayBtnValue('+this.id+')">修改</a>';
								 list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deletebillreceivedway'+this.id+'" onclick="deleteOrCancelBillReceivedWay('+this.id+')">删除</a>';
						    }else{
						    	list+='--';
						    }
						    list+='</td>';
						    list+='</tr>';
				 });
					}else{
						list+='<tr class=error>';
						list+='<td colspan="3" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
					}
					$('#billreceivedwaytable > tbody').html(list);
					//显示斑马线
					zebraCrossing();
				}else{
					show('error_Msg','信息提示:',320,100);
				}	
			},"json");
	}
	
	//增加一行输入框，用于填写其他应收类型信息
	function addBillReceivedWay(){
			$('#billreceivedwaytable > tbody tr.error').remove();
			var list='';
			
			if($('#billreceivedwaytable > tfoot').html()==""){
					list=$('#billreceivedwaytable > tfoot').html();
					list+='<tr id="billreceivedwaytr">';
				    list+='<td align="center">'; 
				    list+='<input id="billreceivedwayname" class="txt_box_150" maxlength="128" />';
				    list+='</td>'; 
				    list+='<td align="center">'; 
				    list+='';
				    list+='</td>'; 
				    list+='</td>'; 
				    list+='<td align="center">';  
				    list+='<a href="#" onclick="saveBillReceivedWay()">保存</a>';	 
				    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelBillReceivedWay()">取消</a>'; 
				    list+='</td>';
				    list+='</tr>';
				    $('#billreceivedwaytable > tfoot').html(list);
				  //显示斑马线
					zebraCrossing();
			}
	}
	
	//取消一行尚未保存的输入框（其他应收类型）
	function cancelBillReceivedWay(feesubjectcount){
		$("#billreceivedwaytr").remove();
	}
	
	//保存当前一行输入的其他应收类型信息
	function saveBillReceivedWay(billreceivedwaycount){
		if($("#billreceivedwayname").val()==null||$("#billreceivedwayname").val()==""||$.trim($("#billreceivedwayname").val())==""){
			show('fill_in_info_error','信息提示:',250,100); 
		}else{
			$.post(WEB_PATH+'/basesetting/basedict/add_base_dict', {'basedict.name':$("#billreceivedwayname").val(),'basedict.type':BASEDICT_STYLE_BILLRECEIVEDWAY},
		  	function(data) 
		  	{
				if(!data.addrltbool){
					show('error_Msg','信息提示:',320,100);
				}else if(data.errormsg){
					$("#billreceivedwaytr").remove();
		  			show('success_Msg','信息提示:',250,100); 
		  		}else{
		  			show('add_error_Msg','信息提示:',320,100);
		  		}
		  		loadBillReceivedWay();
		  	},"json");  
		}
	}
	
	//删除当前选定的一条其他应收类型或是取消当前选定其他应收类型的可编辑状态
	function deleteOrCancelBillReceivedWay(id){
		var deletebtn=$('#deletebillreceivedway'+id).html();//获取取消/删除对象
		var updatebtn=$('#updatebillreceivedway'+id).html();//获取保存/修改对象
		
		if(deletebtn=="取消"){
			$('#updatebillreceivedway'+id).html("修改");
			$('#deletebillreceivedway'+id).html("删除");
			$("div[id='brwnow"+id+"']").show();
			$("div[id='brwclick"+id+"']").hide();
		}else{
			var isdel=confirm("您确定要删除吗？");
			if(isdel){
			
			$.post(WEB_PATH+'/basesetting/basedict/delete_base_dict', {basedictid:id},
				  	function(data) 
				  	{
					if(data.delrltbool){
			  			show('success_Msg','信息提示:',250,100); 
			  		}else{
			  			cancelEnrollmentWay();
			  			show('error_Msg','信息提示:',320,100);
			  		}
			  		loadBillReceivedWay();
			  		
			  	},"json");  
			 	
			}
		}
	}
	
	//切换是否可以修改信息,保存修改信息(其他应收类型)
	function changeBillReceivedWayBtnValue(id){
		var updatebtn=$('#updatebillreceivedway'+id).html();//获取保存/修改对象
		var deletebtn=$('#deletebillreceivedway'+id).html();//获取取消/删除对象
		if(updatebtn=="修改"){  	
			//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值
			$('#brwname'+id).val($("div[id='brwnow"+id+"']:nth(0)").html());
			$('#brwcode'+id).val($("div[id='brwnow"+id+"']:nth(1)").html());
			$('#updatebillreceivedway'+id).html("保存");
			$('#deletebillreceivedway'+id).html("取消");
			$("div[id='brwnow"+id+"']").hide();
			$("div[id='brwclick"+id+"']").show();
		}else{
			if($("#brwname"+id).val()==null||$("#brwname"+id).val()==""||$.trim($("#brwname"+id).val())==""){
				show('fill_in_info_error','信息提示:',250,100); 
    		}else{
    			$.post(WEB_PATH+'/basesetting/basedict/update_base_dict', {basedictid:id,'basedict.name':$("#brwname"+id).val()},
			  	function(data) 
			  	{
    				if(!data.updaterltbool){
    					show('error_Msg','信息提示:',320,100);
    				}else if(data.sameinfobool){
    					show('success_Msg','信息提示:',250,100);
    		  		}else{
    		  			show('add_error_Msg','信息提示:',320,100);
    		  		}
			  		loadBillReceivedWay();
			  	},"json");  
			  	
			$('#updatebillreceivedway'+id).html("修改");
			$('#deletebillreceivedway'+id).html("删除");
			$("div[id='brwnow"+id+"']").show();
			$("div[id='brwclick"+id+"']").hide();
    		}
		}
	}