  //加载院校招生批次及其对应层次和专业
  function ajax_load_academy_level_and_major(data){
  	var list='';
	if(data.lstrltbool){
		if(data.academyEnrollBatchList.length>0){
			$.each(data.academyEnrollBatchList, function(){
				var levelsize=(this.academyLevelList.length)>0?(this.academyLevelList.length):1;
				
				list+='<tr>';
				list+='<td rowspan="'+levelsize+'" width="25%">';
				list+='<table border="0" cellpadding="0" cellspacing="0" class="noborder">';
				list+='<tr>';
				list+='<td align="center" width="60%">';
				list+=this.registerName;
				list+='</td>';
				list+='<td width="40%">';
				list+='<div style="display: inline-block; padding-left: 30px; width: 50px; vertical-align: middle" >';
				list+='<a href="javascript:void(0);" onclick="installBaseLevel('+this.id+','+"'"+this.registerName+"'"+')">设置层次</a><br/>';
				list+='<a href="javascript:void(0);" onclick="getPresentBatchId('+this.id+')">复制层次</a>';
				list+='</div>';
				list+='</td>';
				list+='</tr>';
				list+='</table>';
				list+='</td>';
			 	if(this.academyLevelList.length>0){
			 		for(var i=0;i<this.academyLevelList.length;i++){
			 			if(i>0){
			 				list+='<tr>';
			 			}	
			 			list+='<td width="25%">';
			 			if(this.academyLevelList[i].level!=null){
				 			list+='<table border="0" cellpadding="0" cellspacing="0" class="noborder">';
							list+='<tr>';
							list+='<td align="center" width="60%">';
							list+=this.academyLevelList[i].level.name;
							list+='</td>';
							list+='<td width="40%">';
							list+='<div style="display: inline-block; padding-left: 30px; width: 50px; vertical-align: middle" >';
							list+='<a href="javascript:void(0);" onclick="installMajor('+this.academyLevelList[i].id+','+"'"+this.academyLevelList[i].level.name+"'"+')">设置专业</a><br/>';
							list+='<a href="javascript:void(0);" onclick="getPresentLevelis('+this.academyLevelList[i].levelId+','+"'"+this.academyLevelList[i].id+"'"+','+"'"+this.id+"'"+','+"'"+this.academyLevelList[i].level.name+"'"+')">复制专业</a>';
							list+='</div>';
							list+='</td>';
							list+='</tr>';
							list+='</table>';
			 			}else{
			 				list+="";
			 			}
			 			list+='</td>';
			 			list+='<td width="50%">';
			 			list+=this.academyLevelList[i].academyMajorNames;
			 			list+='</td>';
			 		}
			 	}
				list+='</tr>';
			 });
		}else{
			list+='<tr class=error>';
			list+='<td colspan="3" align="center">';
			list+='<div>暂时没有相关信息</div>';
			list+='</td>';
			list+='</tr>';
		}
		$('#levelandmajortable > tbody').html(list);
		
	}else{
		show('error_Msg','信息提示',400,130);
	}	
 }
  
  //加载层次复制需要的院校批次  同时弹出复制添加层	  
  function ajax_load_copy_academy_batch(data){
	if(data.lstrltbool)
  		{
			$('#copy_academy_level_table',document).hide();
  			$("#copy_academy_batch").empty();
  			if(data.academyBatchlist!=null&&data.academyBatchlist.length>0){
  				$("#copy_academy_batch").append('<option value="0">--请选择招生批次--</option>');
  				$.each(data.academyBatchlist, function(){
					$("#copy_academy_batch").append('<option value="'+this.id+'">'+this.registerName+'</option>'); 
				});
				show('copy_academy_level_div','复制层次',350,250);
				//获取对象并加载清空内容
				var ui = jQuery('#copy_academy_level_div');
				jQuery('#copyAcademyLevelForm', ui)[0].reset();
  			}else{
  				$("#copy_academy_batch").append('<option value="0">--暂无相关数据--</option>'); 
  			}
  			
  		}else{
  			show('error_Msg','信息提示',400,130);
 		}
  }  
	  
  //加载院校全部院校层次,已设置过的置灰为不可选,未设置的可选
  function ajax_load_copy_academy_level(data){
  	 if($("#copy_academy_batch").val()>=1){	
  		$('#copy_academy_level_table',document).show();
		list='';
		if(data.lstrltbool){
   			if(data.academylevellist!=null&&data.academylevellist.length>0){
   				$('#copy_academy_level_table > thead').show();
   				$('#copy_academy_level_table > tfoot').show();
   				$.each(data.academylevellist,function(){
   					list+='<tr>';
   					list+='<td>';
   					list+=this.levelName;
   					list+='</td>';
   					list+='<td>';
   					if(data.academylevelarray!=null&&data.academylevelarray.length>0){
	  					 //data.academylevelarray存放是所有当前院校层次对应的基础设置下的层次ID，jQuery.inArray(value, array)==>确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )。
	   					 if(jQuery.inArray(this.levelId,data.academylevelarray)>=0){ 
	   					 	list+='<div class="checkbox_style"><input type="checkbox" value="'+this.levelId+'" disabled="disabled" checked="checked"/>(该层次已设置)</div>';
	   					 }else{
	   					 	list+='<div class="checkbox_style"><input class="level_checkbox" type="checkbox" value="'+this.levelId+'"/></div>';
	   					 }
    				}else{
    					 list+='<div class="checkbox_style"><input class="level_checkbox" type="checkbox" value="'+this.levelId+'"/></div>';
    			    }
   					list+='</td>';
   					list+='</tr>'; 
   				});
   			}else{
   				$('#copy_academy_level_table > thead').hide();
   				$('#copy_academy_level_table > tfoot').hide();
   				list+='<tr>';
   				list+='<td colspan="2">';
   				list+='<div>暂时没有可复制的层次信息</div>';
   				list+='</td>';
   				list+='</tr>';
   			}
   				$('#copy_academy_level_table > tbody').html(list);
   				show('copy_academy_level_div','复制层次',400,350);
				//获取对象并加载清空内容
				var ui = jQuery('#copy_academy_level_div');
				jQuery('#copyAcademyLevelForm', ui)[0].reset();
		}else{
			closes('copy_academy_level_div');
			show('error_Msg','信息提示:',400,130);
   		}
	  }else{
		  $('#copy_academy_level_table',document).hide();
	  }
  }
	  
  //复制层次和专业
  function ajax_save_copy_academy_level_major(data){
		closes('copy_academy_level_div');
		if(data.copyrltbool){
			show('success_Msg','信息提示:',400,130); 
		}else{
			show('error_Msg','信息提示:',400,130);
		}
		ajax_001_1(); 
   } 
  
  //加载所有基础数据--层次
  function ajax_install_level(data){
	list='';
	tablelist='';
	var count=0;	
	if(data.lstrltbool){
		tablelist+='<tr>';
		tablelist+='<th>';
		tablelist+='<div style="font-weight:bold;">当前院校招生批次:'+banchname+'</div>';
		tablelist+='</th>';
		tablelist+='<th>'; 
		tablelist+='<div class="checkbox_style"><input type="checkbox" id="check_all_level" onclick="checkAllOrCancel(\'addAcademyLevelForm\',\'check_all_level\')"/>&nbsp;全选</div></a></a>';
		tablelist+='</th>';
		tablelist+='</tr>';
		if(data.levellist!=null&&data.levellist.length>0){
			$.each(data.levellist,function(){
				 if((count+1)%6==1){
				 	list+='<tr>';
				 }
				 list+='<td>';
				 if(data.academylevelarray!=null&&data.academylevelarray.length>0){
				 	//data.academylevelarray存放是所有当前院校层次对应的基础设置下的层次ID，jQuery.inArray(value, array)==>确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )。
				 if(jQuery.inArray(this.id,data.academylevelarray)>=0){
				 	list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'" checked="checked"/>&nbsp;'+this.name+'</div>';
				 }else{
				 	list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'"/>&nbsp;'+this.name+'</div>';
				 }
			}else{
				 	list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'"/>&nbsp;'+this.name+'</div>';
		    }
				 list+='</td>';
				 if((count+1)%6==0){
				 	list+='</tr>';
				 }
				 count++;
			});
			$('#academy_level_table > tbody').html(list);
			$('#top_level_table > thead').html(tablelist);
			show('add_academy_Level_div','添加层次',650,300);
			//获取对象并加载清空内容
			var ui = jQuery('#add_academy_Level_div');
			jQuery('#addAcademyLevelForm', ui)[0].reset();
		}else{
			show('error_for_academy_Level','信息提示:',400,130);
		}
	}else{
		show('error_Msg','信息提示:',400,130);
	}
 }
  
  //批量添加院校Level
  function ajax_save_academy_level(data){
	closes('add_academy_Level_div');
	if(data.addrltbool){ 
       show('success_Msg','信息提示:',300,130); 
	}else{
		show('error_Msg','信息提示:',300,130); 
	}
	ajax_001_1();
  }
  
  //加载所有院校基础数据--专业
  function ajax_install_academy_major(data){
	list='';
	tablelist='';
	var count=0;	
	if(data.lstrltbool){
		tablelist+='<tr>';
		tablelist+='<th>';
		tablelist+='<div style="font-weight:bold;">层次名称:'+levelname+'</div>';
		tablelist+='</th>';
		tablelist+='<th>'; 
		tablelist+='<div class="checkbox_style"><input type="checkbox" id="check_all_major" onclick="checkAllOrCancel(\'addAcademyMajorForm\',\'check_all_major\')"/>&nbsp;全选</div></a></a>';
		tablelist+='</th>';
		tablelist+='</tr>';
		if(data.majorlist!=null&&data.majorlist.length>0){
			$.each(data.majorlist,function(){
				 //if((count+1)%3==1){
				 if((count+1)%2==1){
				 	list+='<tr>';
				 }
				 list+='<td>';
				 if(data.academymajorarray!=null&&data.academymajorarray.length>0){
				 	//data.academymajorarray存放所有当前院校层次对应的专业Id，jQuery.inArray(value, array)==>确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )。
				 if(jQuery.inArray(this.id,data.academymajorarray)>=0){
				 	list+='<input type="checkbox" value="'+this.id+'" checked="checked" class="checkbox_style"/>&nbsp;'+this.name+'('+this.code+')';
				 }else{
				 	list+='<input type="checkbox" value="'+this.id+'" class="checkbox_style"/>&nbsp;'+this.name+'('+this.code+')';
				 }
			}else{
				 	list+='<input type="checkbox" value="'+this.id+'" class="checkbox_style"/>&nbsp;'+this.name+'('+this.code+')';
		    }
				 list+='</td>';
				 //if((count+1)%3==0){
				 if((count+1)%2==0){
				 	list+='</tr>';
				 }
				 count++;
			});
			$('#academy_major_table > tbody').html(list);
			$('#top_major_table > thead').html(tablelist);
			show('add_academy_major_div','添加专业',800,320);
			//获取对象并加载清空内容
			var ui = jQuery('#add_academy_major_div');
			jQuery('#addAcademyMajorForm', ui)[0].reset();
		}else{
			show('error_for_academy_Major','信息提示:',400,130);
		}
	}else{
		show('error_Msg','信息提示:',400,130);
	}	
  }
  
  //批量添加院校major
  function ajax_save_academy_major(data){
	  closes('add_academy_major_div');
	  if(data.isback){
		  $('#major_have_students').html('<b>专业：'+data.majorNames+'下有学生，不能删除！</b>');
		  show('major_have_students','信息提示:',400,130); 
	  }
	  else if(data.addrltbool){
	    show('success_Msg','信息提示:',400,130); 
	  }else{
		show('error_Msg','信息提示:',400,130); 
	  }
	  ajax_001_1();
  }
  
  //加载专业复制需要的院校批次  同时弹出复制专业添加层	
  function ajax_load_copy_major_academy_batch(data){
	  $('#copy_academy_major_table',document).hide();
	  $('#contentdiv',document).hide();
	  $("#copy_major_academy_batch").empty();
	  $("#copy_major_academy_level").empty();
	  $("#copy_major_academy_batch").append('<option value="0">--请选择批次--</option>');
	  $("#copy_major_academy_level").append('<option value="0">--请选择层次--</option>');
		if(data.lstrltbool){
  			if(data.academyBatchlist!=null&&data.academyBatchlist.length>0){
  				$.each(data.academyBatchlist, function(){
					$("#copy_major_academy_batch").append('<option value="'+this.id+'">'+this.registerName+'</option>'); 
				});
				show('copy_academy_major_div','复制专业',400,300);
				//获取对象并加载清空内容
				var ui = jQuery('#copy_academy_major_div');
				jQuery('#copyAcademyMajorForm', ui)[0].reset();
  			}else{
  				$("#copy_major_academy_batch").append('<option value="0">--暂无相关数据--</option>'); 
  			}
  			
  		}else{
  			show('error_Msg','信息提示',400,130);
 		}
  }  
  
  //加载复制专业时，某招生批次对应的所有层次(不包含当前层次)
  function ajax_load_level_by_batch(data){
	  $('#copy_academy_major_table',document).hide();
	  $('#contentdiv',document).hide();
	  $("#copy_major_academy_level").empty();
	  $("#copy_major_academy_level").append('<option value="0">--请选择层次--</option>');
	  if(data.lstrltbool){
			if($("#copy_major_academy_batch").val()>0){
				if(data.academylevellist!=null&&data.academylevellist.length>0){
					$.each(data.academylevellist, function(){
					   $("#copy_major_academy_level").append('<option value="'+this.id+'">'+this.levelName+'</option>'); 
					});
				}else{
					$("#copy_major_academy_level").empty();
					$("#copy_major_academy_level").append('<option value="0">--暂无相关数据--</option>'); 
				}
			}
		}else{
			show('error_Msg','信息提示',400,130);
		}
  }

  //加载 复制专业功能需要的专业列表
  function ajax_load_major_by_batch_and_level(data){
	  if($("#copy_major_academy_batch").val()>0&&$("#copy_major_academy_level").val()>0){
			list='';  
			if(data.lstrltbool){
	   			if(data.academymajorlist!=null&&data.academymajorlist.length>0){
	   				$('#copy_academy_major_table > thead',document).show();
	   				$('#copy_academy_major_table > tfoot',document).show();
	   				$('#contentdiv',document).show();
	   				$.each(data.academymajorlist,function(){
	   					list+='<tr>';
	   					list+='<td>';
	   					list+=this.majorName+'('+this.majorCode+')';
	   					list+='</td>';
	   					list+='<td align="center">';
	   					if(data.academymajorarray!=null&&data.academymajorarray.length>0){
		  					 //jQuery.inArray(value, array)==>确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )。
		   					 if(jQuery.inArray(this.majorId,data.academymajorarray)>=0){ 
		   					 	list+='<div class="checkbox_style" align="center"><input type="checkbox" value="'+this.majorId+'" disabled="disabled" checked="checked"/></div>';
		   					 }else{
		   					 	list+='<div class="checkbox_style" align="center"><input class="major_checkbox" type="checkbox" value="'+this.majorId+'"/></div>';
		   					 }
	    				}else{
	    					 list+='<div class="checkbox_style"><input class="major_checkbox" type="checkbox" value="'+this.majorId+'"/></div>';
	    			    }
	   					list+='</td>'; 
	   					list+='</tr>'; 
	   				});
	   			}else{
	   				$('#copy_academy_major_table > thead',document).hide();
	   				$('#copy_academy_major_table > tfoot',document).hide();
	   				$('#contentdiv',document).hide();
	   				list+='<tr>';
	   				list+='<td align="center" colspan="2">';
	   				list+='<div>暂时没有可复制的专业信息</div>';
	   				list+='</td>';
	   				list+='</tr>';
	   			}
	   			  $('#copy_academy_major_table > tbody').html(list);
	   			  $('#copy_academy_major_table',document).show();
			}else{
				closes('copy_academy_major_div');
				show('error_Msg','信息提示:',400,130);
	   		}
	  }else{
		  $('#copy_academy_major_table',document).hide();
			$('#contentdiv',document).hide();
	  }	  
  }
  
  //复制专业
  function ajax_copy_major(data){
	closes('copy_academy_major_div');
	if(data.copyrltbool){
		show('success_Msg','信息提示:',400,130); 
	}else{
		show('error_Msg','信息提示:',400,130);
	}
	ajax_001_1(); 
  }
	 