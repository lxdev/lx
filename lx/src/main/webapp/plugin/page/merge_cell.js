/**
  * willcheck:要进行处理的表格对象(或者行的集合即可)
  * colnum:要进行合并单元格的列
  */
function coltogather(willcheck,colnum){ 
	var alltext = [],togotherNum = [],oldnum = [],id=1,lasttext=null,rmflag=1; 
	//逐列的数据进行扫描,得到里面的不重复的数据,以及各个数据的数目,然后将依据此结果进行设置rowspan属性
	willcheck.each(function(){
	      var _rmnum = this.getAttribute('rmnum');
		  if(!_rmnum)_rmnum=0;
	      var trdom= jQuery('td:eq('+(colnum-_rmnum)+')',this)
		  var text = jQuery(trdom).text();  
		  //如果上一行记录文本为空,说明是第一行
		  if(lasttext==null) {
			   lasttext = text;
		  }else {
			  //如果当前行和上一行记录一样,说明要进行合并,合并的单元格数目就加1
			  if(lasttext!=text){  
				   togotherNum.push(id);
				   lasttext = text;
				   id = 1;
			  } else{
			      id++;
			  }
		  }
		  
	 });   
	 togotherNum.push(id); 
	 //复制allnum数组中的数据到oldnum数组
	 jQuery.each(togotherNum, function(i, n){
         oldnum[i] =n;
     }); 
     var index = 0,len = togotherNum.length;
	 //逐行进行处理,设置指定列的rowspan属性,以及将要合并的单元格remove!
	 willcheck.each(function() {
				// 得到一个属性表示该行已经被移除了几个td
				var _rmnum = this.getAttribute('rmnum');
				if (!_rmnum)
					_rmnum = 0;
				var tddom = jQuery('td:eq(' + (colnum - _rmnum) + ')', this)
				if (togotherNum[index] == oldnum[index]) {
					tddom.attr('rowSpan', togotherNum[index]);
					if(togotherNum[index]>1)
					  togotherNum[index] = togotherNum[index] - 1;
					 else
					   index++;
				} else { 
					if (togotherNum[index] == 0) {
						index++;
					    tddom.attr('rowSpan', togotherNum[index]);
					} else {
				    	tddom.remove(); 
						if(--togotherNum[index]==0){
							index++;
						}
					}
					// 移除了td之后,要在tr里面添加一个属性标示已经移除的td的数目
					if (_rmnum == 0) {
						jQuery(this).attr('rmnum', 1);
					} else {
						jQuery(this).attr('rmnum', 1 + _rmnum * 1);
					}
				}
			});   
	 //清空数组
	 alltext = null;
	 togotherNum = null;
	 oldnum = null; 
}


function isinarr(arr,str){
  for(var i=arr.length-1;i>=0;i-- ){
    if(arr[i]==str)
	{return i;
	}
  }
  return -1;
}

function checktable(id){
  var tdnum=0;
  $('#'+id+' tr').each(function(){
	if(tdnum==0){
	   tdnum = $('td',this).size();
	}else{ 
	   if(tdnum!=$('td',this).size()){
	        tdnum = -1;
			return false;
	   }
	}
  });
  if(tdnum>0)
     return true;
  return false;
}