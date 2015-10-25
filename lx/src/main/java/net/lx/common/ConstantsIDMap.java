package net.lx.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份证代码表
 * 
 */
public class ConstantsIDMap {

private static Map<String, Integer> idCodeMap=null;// 身份证代码表
private static Map<Integer, String> idCodeMap2=null;// 身份证代码表(key:typeId value:类型)
	
	public static Integer getCode(String name){
		if(idCodeMap==null)
			init();
		return idCodeMap.get(name)==null?0:idCodeMap.get(name);
	}
	
	public static String getCode(Integer id){
		if(idCodeMap2==null)
			init();
		return idCodeMap2.get(id)==null?"未知":idCodeMap2.get(id);
	}
	
	public static void init(){
		if(idCodeMap==null){
			idCodeMap = new HashMap<String, Integer>();
			idCodeMap.put("身份证", Constants.CERTIFICATE_TYPE_ID);
			idCodeMap.put("驾照", Constants.CERTIFICATE_TYPE_DRIVER_ID);
			idCodeMap.put("士官证", Constants.CERTIFICATE_TYPE_NCO_ID);
		}
		if(idCodeMap2==null){
			idCodeMap2 = new HashMap<Integer, String>();
			idCodeMap2.put(Constants.CERTIFICATE_TYPE_ID,"身份证");
			idCodeMap2.put(Constants.CERTIFICATE_TYPE_DRIVER_ID,"驾照");
			idCodeMap2.put(Constants.CERTIFICATE_TYPE_NCO_ID,"士官证");
		}
	}
}
