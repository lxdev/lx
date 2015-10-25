var util = util || {};

util.select = {
	initOption: function(selecter, data, value, tipOption, keyName, textName){
		var target = jQuery(selecter);
		if(!target || target.size()==0)
			target = jQuery('<select/>');
		
		target.empty();
		
		if(!keyName)
			keyName = 'id';
		if(!textName)
			textName = 'name';
		
		if(tipOption){
			jQuery('<option/>').attr('value', tipOption.value).append(tipOption.text).appendTo(target);
		}
		for(var i=0; i<data.length; i++){
			jQuery('<option/>').attr('value', data[i][keyName]).append(data[i][textName]).appendTo(target);
		}
		
		target.val(value);
		
		return target;
	},
	selectAt: function(ele, index){
		jQuery(ele).each(function(){
			this.selectedIndex = index;
		});
	},
	textAt: function(ele, index){
		var text;
		jQuery(ele).each(function(_index){
			if(_index==index){
				text = jQuery(this).text();
				return false;
			}
		});
		
		return text;
	}
};



//输入select控件ID自动绑定值
//data：数据源
//sid：select控件ID
//selectid：选中默认值
function initSelectOptions(data,sid,selectid) {
	var options = "";
	for ( var i = 0; i < data.length; i++) 
	{
		var value=data[i].id;
		options+='<option value="'+value+'"';
		if(value==selectid)
		{
			options+=' selected="selected"';
		}
		options+='>'+data[i].name+'</option>';
	}
	$('#' + sid).html(options);
}

function initSelectOptionsForClass(data,classname,selectid) {
	var options = "";
	for ( var i = 0; i < data.length; i++) 
	{
		var value=data[i].id;
		options+='<option value="'+value+'"';
		if(value==selectid)
		{
			options+=' selected="selected"';
		}
		options+='>'+data[i].name+'</option>';
	}
	$('.' + classname).html(options);
}