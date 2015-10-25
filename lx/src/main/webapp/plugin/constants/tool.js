 /**
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423     
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04     
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04     
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04     
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18     
*/       
Date.prototype.pattern=function(fmt) {        
		var o = {        
				    "M+" : this.getMonth()+1, //月份        
				    "d+" : this.getDate(), //日        
				    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
				    "H+" : this.getHours(), //小时        
				    "m+" : this.getMinutes(), //分        
				    "s+" : this.getSeconds(), //秒        
				    "q+" : Math.floor((this.getMonth()+3)/3), //季度        
				    "S" : this.getMilliseconds() //毫秒        
		};        
	    var week = {        
				    "0" : "\u65e5",        
				    "1" : "\u4e00",        
				    "2" : "\u4e8c",        
				    "3" : "\u4e09",        
				    "4" : "\u56db",        
				    "5" : "\u4e94",        
				    "6" : "\u516d"       
		};        
				    if(/(y+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
				    }        
				    if(/(E+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
				    }        
				    for(var k in o){        
				        if(new RegExp("("+ k +")").test(fmt)){        
				            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
				        }        
				    }        
				    return fmt;
}       
/**
 * 获取每月的天数
 * @param {Object} year
 * @param {Object} month
 */
function getDate(year,month){
	var day = 0;
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
							|| month == 10 || month == 12) {
						day = 31;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
						day = 30;
			}
			if (month == 2) {
					if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
							day = 29;
					} else {
							day = 28;
					}
			}
		return day;
}
//实现js   replaceAll方法
String.prototype.replaceAll = function(s1,s2) { 
    		return this.replace(new RegExp(s1,"gm"),s2); 
}

//字符串格式化为yyyy-MM-dd
String.prototype.toDate = function() { 
    		return this.substring(0,10); 
}
//获取当月第一天
function getFirstDay(){
	var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth()+1;
    if (month<10){
        month = "0"+month;
    }
   return year+"-"+month+"-"+"01";
}

//获取当年第一天
function getYearFirstDay(){
	var myDate = new Date();
    var year = myDate.getFullYear();
   return year+"-01-01";
}

/**
 * 转换钱
 * @return  保留两位小数的钱
 */
String.prototype.toMoney=function(){
		if(this!=null&&this!=""){
			//有小数点
			if(this.indexOf(".")!=-1){
				//小数点前面
				var qian=this.substring(0,this.indexOf("."));
				//小数点后面
				var hou=this.substring(this.indexOf(".")+1);
				
				if(hou!=null&&hou!=""){
					if(hou.length==1){//一位
						return this+"0";
					}else if(hou.length==2){//两位
						return this;
					}else if(hou.length>2){//超过两位四舍五入
						
						if(parseInt(hou.substring(2,3))>=5){
							hou=parseInt(hou.substring(0,2))+1;
							if(hou==100){
								qian=parseInt(qian)+1;
								hou="00";
							}
							if((hou+"").length==1){
								hou="0"+hou;
							}
						}else{
							hou=hou.substring(0,2);
						}
						return qian+"."+hou;
					}
				}else{
					return qian+".00";
				}
			}else{//没有小数点
				return this+".00";
			}
		}else{
			return "";
		}
		
	}
/**
 * 把form元素转换为json
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
        	 
            o[this.name] = this.value || '';
           
        }
    });
   
    return o;
}


$.fn.serializeObjectParame = function(o) {
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
        	 
            o[this.name] = this.value || '';
        }
    });
    return o;
}

$.fn.serializeString = function() {
    var o = "";
    var a = this.serializeArray();
    $.each(a, function() {
        o+="'"+this.name+"':"+this.value+",";
    });
    o=o.substring(0,o.lastIndexOf(","));
    return o;
}
/**
 * 是否显示隐藏增删改查操作
 * @param {Object} id   实体ID
 * @param {Object} pluginCode  page插件编号
 * @param {Object} type   类型   view,update,delete,checkbox
 */
function isPageOperating(id,pluginCode,type){
	setTimeout('$("#"+'+id+'+"_"+"'+pluginCode+'"+"_"+"'+type+'").css({"display":"none"})',10);
}
//截取字符串
String.prototype.toSubString=function(l){
	if(this!=null&&this!=""){
		if(this.length>l){
			return this.substring(0,l)+"...";
		}else{
			return this;
	    }
    }else{
		    return "";
	}
}
/**
 * 获取性别
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getSex=function(){
	if(this!=null){
		if(this==1){
			return "男";
		}else if(this==0){
			return "女";
		}else{
			return "--";
		}
    }else{
		    return "--";
	}
}
/**
 * 缴费方式
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getPaymentWay=function(){
	if(this!=null){
		switch(this+""){
			case 3+"":
				return "第三方支付";
			case 4+"":
				return "现金汇总部";
			case 5+"":
				return "现金汇院校";
			case 6+"":
				return "POS直汇总部";
			case 7+"":
				return "POS直汇院校";
			case 8+"":
				return "网银总部";
			case 9+"":
				return "网银院校";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
	
}

/**
 * 层次
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getLevel=function(){
	if(this!=null){
		switch(this+""){
			case LEVEL_GZ+"":
				return "高起专";
			case LEVEL_GB+"":
				return "高起本";
			case LEVEL_ZB+"":
				return "专升本";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
	
}
/**
 * 缴费单状态
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getPaymentStatus=function(){
	if(this!=null){
		switch(this+""){
		
			case PAYMENT_STATUS_TUI_FEI_SUCCESS+"":
				return "已成功退费";
			case PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI+"":
				return "可以直接退费";
			case PAYMENT_STATUS_YI_TUI_FEI_QUE_REN+"":
				return "退费审批通过已确认";
			case PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+"":
				return "退费审批通过待确认";
			case PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI+"":
				return "退费审批失败";
			case PAYMENT_STATUS_TUI_FEI_SHEN_PI_TONG_GUO+"":
				return "已退费确认";
			case PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN+"":
				return "已申请退费";
			case PAYMENT_STATUS_YI_TUI_FEI+"":
				return "已退费";	
			case PAYMENT_STATUS_ZUO_FEI+"":
				return "已作废";
			case PAYMENT_STATUS_YI_KAI_DAN+"":
				return "已开单";
			case PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+"":
				return "已收费确认";
			case PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN+"":
				return "已填汇款单";
			case PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU+"":
				return "已汇款总部";
			case PAYMENT_STATUS_ZONG_BU_QUE_REN+"":
				return "总部确认";
			case PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN+"":
				return "已填打款单";
			case PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN+"":
				return "已确认待汇款";
			case PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO+"":
				return "已打款院校";
			case PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN+"":
				return "已填返款单";
			case PAYMENT_STATUS_FAN_KUAN_QUE_REN+"":
				return "返款确认";
			case PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN+"":
				return "已填招生返款";
			case PAYMENT_STATUS_QU_DAO_YI_SHENG_HE+"":
				return "渠道已审核";	
			case PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE+"":
				//return "商务已审核";2012-05-27
				return "行政已审核";
			case PAYMENT_STATUS_CAI_WU_YI_SHEN_HE+"":
				//return "财务已审核";
				return "商务已审核";
			case PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO+"":
				return "已汇款";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
	
}
/**
 * 获取学生状态名称
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getStudentStatus=function(){
	if(this!=null){
		switch(this+""){
			case STU_CALL_STATUS_YU_BAO_MING+"":
				return "预报名";
			case STU_CALL_STATUS_YI_DAO_RU+"":
				return "已导入未分配";
			case STU_CALL_STATUS_YI_FENG_PEI+"":
				return "已分配";
			case STU_CALL_STATUS_WU_YI_YUAN+"":
				return "无意愿(学习中心)";
			case STU_CALL_STATUS_GENG_JIN_ZHONG+"":
				return "跟进中";
			case STU_CALL_STATUS_YI_DAO_MING+"":
				return "已报名未缴费";
			case STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+"":
				return "已报名";
			case STU_CALL_STATUS_YI_CE_SHI+"":
				return "已测试";
			case STU_CALL_STATUS_JIAN_KONG_ZAI_SHENG_QIN+"":
				return "监控再申请";
			case STU_CALL_STATUS_JIAN_KONG_YI_CHENG_GONG+"":
				return "已监控已成功";
			case STU_CALL_STATUS_JIAN_KONG_WEI_CHENG_GONG+"":
				return "已监控未成功";
			case STU_CALL_STATUS_YI_FU_HE+"":
				return "已复核";
			case STU_CALL_STATUS_YI_LU_QU+"":
				return "已录取";
			case STU_CALL_STATUS_YI_QU_DE_XUE_JI+"":
				return "已取得学籍";
			case STU_CALL_STATUS_KE_CHENG_JING_XIU+"":
				return "课程进修";
			case STU_CALL_STATUS_YI_FU_XUE+"":
				return "已复学";
			case STU_CALL_STATUS_YI_XIU_XUE+"":
				return "已休学";
			case STU_CALL_STATUS_YI_BI_YI+"":
				return "已毕业";
			case STU_CALL_STATUS_YI_TUI_XUE+"":
				return "已退学";
			case STU_CALL_STATUS_QU_XIAO_XUE_JI+"":
				return "取消学籍";
			case STU_CALL_STATUS_YI_JIAO_XUE_FEI+"":
				return "已缴学费";
			case STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+"":
				return "无意愿(呼叫中心)";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
}

/**
 * 获取学生数据来源
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getStudentDatasource=function(){
	if(this!=null){
		switch(this+""){
			case STU_SOURCE_LC+"":
				return "学习中心";
			case STU_SOURCE_NP+"":
				return "网络报名";
			case STU_SOURCE_CC+"":
				return "呼叫中心";
			case STU_SOURCE_HS+"":
				return "历史数据";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
}

/**
 * 监控状态
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getStudentJianKongZhuangTai=function(){
	if(this!=null){
		switch(this+""){
			case STU_MONITOR_STATUS_WEI_JIAN_KONG+"":
				return "未监控";
			case STU_MONITOR_STATUS_JIAN_KONG_ZAI_SHENG_QIN+"":
				return "监控再申请";
			case STU_MONITOR_STATUS_JIAN_KONG_YI_CHENG_GONG+"":
				return "已监控已成功";
			case STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG+"":
				return "已监控未成功";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
}
/**
 * 获取学生呼叫等级
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getCallGrade=function(){
	if(this!=null){
		switch(this+""){
			case CALL_GRADE_A+"":
				return "A级";
			case CALL_GRADE_B+"":
				return "B级";
			case CALL_GRADE_C+"":
				return "C级";
			case CALL_GRADE_D+"":
				return "D级";
			case CALL_GRADE_E+"":
				return "E级";
			case CALL_GRADE_F+"":
				return "F级";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
}


/**
 * 获取学生学历
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getStuDegree=function(){
	if(this!=null){
		switch(this+""){
			case 1+"":
				return "大学本科及以上";
			case 2+"":
				return "大学专科";
			case 3+"":
				return "高中或中专";
			case 4+"":
				return "高中以下";
			default:
				return "未知";
		}
    }else{
		    return "未知";
	}
}
/**
 * 退费后续流程  总部、院校退中心明细的状态
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Number.prototype.getRefundBranchStatus=function(){
	if(this!=null){
		switch(this+""){
			case REFUND_BRANCH_STATUS_WEI_HUI_KUAN+"":
				//return "学习中心退费未确认";
				return "未汇款";
			case REFUND_BRANCH_STATUS_YI_TIAN_HUI_KUAN_DAN+"":
				//return "中心收退费未确认";
				return "已汇款中心";
			case REFUND_BRANCH_STATUS_YI_HUI_KUAN_QUE_REN+"":
				//return "学生退费未确认";
				return "中心已收款";
			case REFUND_BRANCH_STATUS_YI_CHENG_GONG_HUI_KUAN+"":
				//return "学生退费已确认";
				return "学生成功退费";
			default:
				return "--";
		}
    }else{
		    return "--";
	}
	
}
/**
 * 等到某个条件成立时执行某个函数
 * @param con 条件，代码段/函数，应返回一个boolean值
 * @param job 在条件成立时要执行的任务
 * @param interval 检查条件的时间间隔
 */
function wait4(con, job, interval)
{
	if(!interval) interval = 333;
	
	var timer;
	
	var callback = function()
	{
		var stop;
		if((typeof(con) == 'string') || (con instanceof String))
		{
			stop = eval(con);
		}
		else
		{
			stop = con();
		}
		
		if(!stop) return;
		
		clearInterval(timer);
		job();
	};
	
	timer = setInterval(callback, interval);
}

/**
 * 重复执行任务(job)直到条件成立
 * @param con 条件，代码段/函数，应返回一个boolean值
 * @param job 将被重复执行任务
 * @param interval 重复执行的时间间隔
 */
function doUntil(con, job, interval)
{
	if(!interval) interval = 333;
	
	var timer;
	
	var callback = function()
	{
		var stop;
		if(typeof(con) == 'string' || con instanceof String)
		{
			stop = eval(con);
		}
		else
		{
			stop = con();
		}
		
		if(stop)
		{
			clearInterval(timer);
			return;
		}
		
		job();
	};
	
	timer = setInterval(callback, interval);
}

/****** SimpleTimer BEGIN ********/
/**
 * 一个简单的计时器
 * @param tickCallback 在给定时间间隔时要触发的函数，当前SimpleTimer实例会作为第一个参数
 * @param time 计时器总运行时间
 * @param interval 触发tickCallback的间隔，默认为1000毫秒
 */
function SimpleTimer(tickCallback, time, interval)
{
	if(!interval || interval<0) interval = 1000;

	this.interval = interval;
	this.tickCount = 0;
	this.time = time;
	this.elapsedTime = 0;
	
	var _this = this;
	
	this._timer = setInterval(
			function(){
				tickCallback(_this);
				if(_this.isLastTick()) clearInterval(_this._timer);
				_this.tickCount++;
				_this.elapsedTime += _this.interval;
			},
			interval
	);
}

/* 是否为最后一次Tick */
SimpleTimer.prototype.isLastTick = function(){
	return this.elaspedTime >= this.time || this.elapsedTime+this.interval>this.time;
};

/**
 *  延长计时器
 *  @param time 将要延长的时间
 *  @param interval 在延长的时间内Tick的时间间隔，默认为上一次设置的间隔
 */
SimpleTimer.prototype.delay = function(){
	if(arguments.length==1){
		this.tickCount += (arguments[0]/this.interval);
	} else if(arguments.length==2) {
		this.tickCount += (arguments[0]/arguments[1]);
		this.interval = arguments[1];
	} else {
		return;
	}
	
	this.time += arguments[0];
};

SimpleTimer.prototype.stop = function(){
	clearInterval(this._timer);
};
/****** SimpleTimer END ********/


/**
 * 滤去JSON中的空值
 * @param {Object} json
 * @return {TypeName}
 */
function filterBlankOfJSON(json)
{
	var p;
	
	for(p in json){
		if(!json[p] || typeof(json[p])=='number' && isNaN(json[p])) delete json[p];
		if((typeof(json[p])=='array' || json[p] instanceof Array) && json[p].length==0)
			delete json[p];
	}
	
	return json;
}
//隔行变色
function zebraCrossing(){
	
	jQuery(".gv_table_2").each(function(){
		if($(this).find("thead").length==1){
			$(this).find("tbody >tr:odd").addClass('odd');
			$(this).find("tbody >tr:even").addClass('even');
		}
	});
}
//处理小数点
function dealwithmoney(money)
{
	if(!((/^\d{1,10}(\.\d{1,2})?$/).test(money)))
	{
		if(!((/^\d{1,10}(\.\d{1,2})?$/).test(money+0)))
		{
			return -1;
		}
		return money+0;
	}
	return money;
}
//处理正负数的小数点问题
function dealwithnumber(money)
{
	if(money=="-")
	{
		return 0;
	}
	if(!((/^[-+]?[0-9]+(\.[0-9]+)?$/).test(money)))
	{
		if(!((/^[-+]?[0-9]+(\.[0-9]+)?$/).test(money+0)))
		{
			return "--";
		}
		return money+0;
	}
	return money;
}

jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件
	return this.each(function() {
		var that;
		$('tbody > tr', this).each(function(row) {
				$('td:eq(' + colIdx + ')', this).filter(':visible').each(
					function(col) {
						if (that != null&& $(this).html() == $(that).html()) {
								rowspan = $(that).attr("rowSpan");
								if (rowspan == undefined) {
										$(that).attr("rowSpan",1);
										rowspan = $(that).attr("rowSpan");
								}
								rowspan = Number(rowspan) + 1;
								$(that).attr("rowSpan",rowspan);
								$(this).hide();
						} else {
							that = this;
						}
					});
		});
	});
}
 //函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格  
 //参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data   
 //参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。  
 function _w_table_rowspan(_w_table_id,_w_table_colnum){  
     _w_table_firsttd = "";  
     _w_table_currenttd = "";  
     _w_table_SpanNum = 0;  
     _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");  
     _w_table_Obj.each(function(i){  
         if(i==0){  
             _w_table_firsttd = $(this);  
             _w_table_SpanNum = 1;  
         }else{  
             _w_table_currenttd = $(this);  
             if(_w_table_firsttd.text()==_w_table_currenttd.text()){  
                 _w_table_SpanNum++;  
                 _w_table_currenttd.hide(); //remove();  
                 _w_table_firsttd.attr("rowSpan",_w_table_SpanNum);  
             }else{  
                 _w_table_firsttd = $(this);  
                 _w_table_SpanNum = 1;  
             }  
         }  
     });   
 }  
   
 //函数说明：合并指定表格（表格id为_w_table_id）指定行（行数为_w_table_rownum）的相同文本的相邻单元格  
 //参数说明：_w_table_id 为需要进行合并单元格的表格id。如在HTMl中指定表格 id="data" ，此参数应为 #data   
 //参数说明：_w_table_rownum 为需要合并单元格的所在行。其参数形式请参考jQuery中nth-child的参数。  
 //          如果为数字，则从最左边第一行为1开始算起。  
 //          "even" 表示偶数行  
 //          "odd" 表示奇数行  
 //          "3n+1" 表示的行数为1、4、7、10.  
 //参数说明：_w_table_maxcolnum 为指定行中单元格对应的最大列数，列数大于这个数值的单元格将不进行比较合并。  
 //          此参数可以为空，为空则指定行的所有单元格要进行比较合并。  
 function _w_table_colspan(_w_table_id,_w_table_rownum,_w_table_maxcolnum){  
     if(_w_table_maxcolnum == void 0){_w_table_maxcolnum=0;}  
     _w_table_firsttd = "";  
     _w_table_currenttd = "";  
     _w_table_SpanNum = 0;  
     $(_w_table_id + " tr:nth-child(" + _w_table_rownum + ")").each(function(i){  
         _w_table_Obj = $(this).children();  
         _w_table_Obj.each(function(i){  
             if(i==0){  
                 _w_table_firsttd = $(this);  
                 _w_table_SpanNum = 1;  
             }else if((_w_table_maxcolnum>0)&&(i>_w_table_maxcolnum)){  
                 return "";  
             }else{  
                 _w_table_currenttd = $(this);  
                 if(_w_table_firsttd.text()==_w_table_currenttd.text()){  
                     _w_table_SpanNum++;  
                     _w_table_currenttd.hide(); //remove();  
                     _w_table_firsttd.attr("colSpan",_w_table_SpanNum);  
                 }else{  
                     _w_table_firsttd = $(this);  
                     _w_table_SpanNum = 1;  
                 }  
             }  
         });  
     });  
}  

 /*
 Auto-growing textareas; technique ripped from Facebook
 (Textarea need set style "overflow:hidden" under IE)
 */
 (function($) {
 function times(string, number) {
   for (var i = 0, r = ''; i < number; i ++) r += string;
   return r;
 }

 $.fn.autogrow = function(options) {
   this.filter('textarea').each(function() {
     this.timeoutId = null;
     //var $this = $(this), minHeight = $this.height();
     var $this = $(this), minHeight = 100;
     var shadow = $('<div></div>').css({
       position:   'absolute',
       wordWrap:   'break-word',
       top:        0,
       left:       -9999,
       display:    'none',
       width:      $this.width(),
       fontSize:   $this.css('fontSize'),
       fontFamily: $this.css('fontFamily'),
       lineHeight: $this.css('lineHeight')
     }).appendTo(document.body);

     var update = function() {
       var val = this.value.replace(/</g, '&lt;')
         .replace(/>/g, '&gt;')
         .replace(/&/g, '&amp;')
         .replace(/\n$/, '<br/>&nbsp;')
         .replace(/\n/g, '<br/>')
         .replace(/ {2,}/g, function(space) { return times('&nbsp;', space.length -1) + ' ' });
       shadow.html(val);
       $(this).css('height', Math.max(shadow.height(), minHeight));
     }
     
     var updateTimeout = function() {
       clearTimeout(this.timeoutId);
       var that = this;
       this.timeoutId = setTimeout(function(){ update.apply(that); }, 100);
     };

     $(this).change(update).keyup(updateTimeout).keydown(updateTimeout);
     update.apply(this);
   });
   return this;
 }
 })(jQuery);

jQuery(document).ready(function(){
	  $("textarea").autogrow();
	  if($.browser){
		  if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()){
			  $("html").css("overflowY","scroll");
		  }
	  }else {
		  if($.support.msie&&$("html")[0].scrollHeight>$("html").height()){
			  $("html").css("overflowY","scroll");
		  }
	  }
	
});
