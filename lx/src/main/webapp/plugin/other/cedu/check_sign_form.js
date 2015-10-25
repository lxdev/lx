function bg(cn){
	return document.getElementById(cn);
}
function bgnm(cn){
	return document.getElementsByName(cn);
}
function bgvl(cn){
	return document.getElementById(cn).value;
}
function checkSignForm(){
	var j=0;

	
	if(bgvl('userName')==''){
		alert("[姓名]为必填项！");
		bg('userName').focus();
		return false;		
	}
  	String.prototype.lenB  = function(){return   this.replace(/[^\x00-\xff]/g,"**").length;}  
 	 var str = bgvl('userName'); 
	if(str.lenB()<3){
		alert("[姓名]长度必须大于2！");
		bg('userName').focus();
		return false;		
	}	
	if(checkCharCnMethod(bg('userName'))){
		//return true;
	}else{
		alert("请输入中文！");
		bg('userName').focus();
		return false;
	}

	for(var i=0;i<bgnm('student.gender').length;i++){
		if(bgnm('student.gender')[i].checked){
			j=1;
		}
	}if(j==0){
		alert("请选择性别！");
		return false;
	}
 
	if(bgvl('cityName')=='0'){
		alert("请选择所在地！");
		bg('cityName').focus();
		return false;		
	}
	if(bgvl('mobile')==''&&bgvl('phone')==''){
		alert("手机号码或座机号码至少填写一项！");
		bg('mobile').focus();
		return false;		
	}	
	if(bgvl('mobile')!=''){
		if(bgvl('mobile').length!=11){
			alert("请您输入正确的11位手机号码！");
			bg('mobile').focus();
			return false
		}	
	}	
	if(bgvl('phone')!=''){
	 
		if(bgvl('phone').length<8){
			alert("请您输入正确的座机号码,最少8位！");
			bg('phone').focus();
			return false
		}	
	}		
	j=0;
	for(var i=0;i<bgnm('student.degree').length;i++){
		if(bgnm('student.degree')[i].checked){
			j=1;
		}
	}if(j==0){
		alert("请选择学历程度！");
		return false;
	}		
	if(bgvl('email')!=''){
	
		if(!checkUserEmail(bgvl('email'))){
			alert("请您输入正确的电子邮件！");
			bg('email').focus();
			return false
		}	
	}	
	if(bgvl('postalCode')!=''){
		if(!checkZipMethod(bg('postalCode'))){
			alert("请您输入正确的邮政编码！");
			bg('postalCode').focus();
			return false
		}	
	}
	if(bgvl('certificateType')=='101'){
		if(checkIdcard(bgvl('certificateCode'))!='验证通过'){
			alert(checkIdcard(bgvl('certificateCode')));
			bg('certificateCode').focus();
			return false;	
		}
	}
	return true;
}
function checkPhoneMethod2(obj){
	return (/^(((\()?\d{2,4}(\))?[-(\s)*]){0,2})?(\d{7,8})$/).test(obj.value);
}	
function checkPhoneMethod(obj){
	//alert(obj.value+(obj.value).match(/([0-9]{2})+-([0-9]{4})+-([0-9]{4})+/))
	return (/^([0][0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})$/).test(obj.value);
	//return (obj.value).match(/([0-9]{2})+-([0-9]{4})+-([0-9]{4})+/);
} 	
function checkZipMethod(obj){
	return (/^[1-9]\d{5}(?!\d)$/).test(obj.value);
} 
function checkMobileMethod(obj){
	return (/^(?:13\d|15\d|15[89])-?\d{5}(\d{3}|\*{3})$/).test(obj.value);
}
	function checkUserEmail(str){
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
 		return reg.test(str);
}
function checkCharCnMethod(obj){
	var cnchar=/^[\u4e00-\u9fa5]*$/g;
	if(obj.value.match(cnchar)==null){
		return false;
	}
	else{
		return true;
	}
	
}

function checkIdcard(idcard){
var Errors=new Array(
"验证通过",
"身份证号码位数不对!",
"身份证号码出生日期超出范围或含有非法字符!",
"身份证号码校验错误!",
"身份证地区非法!"
);
var area= {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
var idcard,Y,JYM;
var S,M;
var idcard_array = new Array();
idcard_array = idcard.split("");
//地区检验
if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
//身份号码位数及格式检验
switch(idcard.length){
case 15:
if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
} else {
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
}
if(ereg.test(idcard)) return Errors[0];
else return Errors[2];
break;
case 18:
//18位身份号码检测
// 出生日期的合法性检查 
//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2] [0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])| (04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
} else {
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
}
if(ereg.test(idcard)){//测试出生日期的合法性
//计算校验位
S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
+ parseInt(idcard_array[7]) * 1 
+ parseInt(idcard_array[8]) * 6
+ parseInt(idcard_array[9]) * 3 ;
Y = S % 11;
M = "F";
JYM = "10X98765432";
M = JYM.substr(Y,1);//判断校验位
if(M == idcard_array[17]) return Errors[0]; // 检测ID的校验位
else return Errors[3];
}
else return Errors[2];
break;
default:
return Errors[1];
break;
}
}