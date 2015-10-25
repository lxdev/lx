package net.lx.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 政治代码表
 * 
 */
public class ConstantsPoliticalMap {

private static Map<String, Integer> politicalCodeMap=null;
	
	public static Integer getCode(String name){
		if(politicalCodeMap==null)
			init();
		return politicalCodeMap.get(name)==null?0:politicalCodeMap.get(name);
	}
	
	public static void init(){
		if(politicalCodeMap==null){
			politicalCodeMap = new HashMap<String, Integer>();
			politicalCodeMap.put("中共党员", 1);
			politicalCodeMap.put("中共预备党员", 2);
			politicalCodeMap.put("共青团员", 3);
			politicalCodeMap.put("民革会员", 4);
			politicalCodeMap.put("民盟盟员", 5);
			politicalCodeMap.put("民建会员", 6);
			politicalCodeMap.put("民进会员", 7);
			politicalCodeMap.put("农工党党员", 8);
			politicalCodeMap.put("致公党党员", 9);
			politicalCodeMap.put("九三学社社员", 10);
			politicalCodeMap.put("台盟盟员", 11);
			politicalCodeMap.put("无党派民主人士", 12);
			politicalCodeMap.put("群众", 13);

		}
	}
}
