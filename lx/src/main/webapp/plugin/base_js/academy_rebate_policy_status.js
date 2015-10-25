/* 院校返款政策状态 */

function get_academy_rebate_policy_status()
{
	var data = [
		{id:1, name:'待审批'},
		{id:2, name:'审批未通过'},
		{id:3, name:'启用'},
		{id:4, name:'停用'}
	];
	
	if(arguments.length==1){
		var _i;
		for(_i=0; _i<data.length; _i++){
			var entry = data[_i];
			if(entry.id==arguments[0]){
				return entry.name;
			}
		}
		alert('after iterator rebate policy list '+_i);
	}
	
	return data;
}

