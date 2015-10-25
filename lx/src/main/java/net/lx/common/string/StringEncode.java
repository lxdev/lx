package net.lx.common.string;

import java.io.UnsupportedEncodingException;

/**
 * 字符串操作
 * 
 */
public class StringEncode {

	static public String ToUTF8(String str, Boolean isFromUrl) throws UnsupportedEncodingException {
		String result = "";
		if(isFromUrl)
			result = java.net.URLDecoder.decode(str,"UTF-8");
		else{
			/*
			 * 对于页面未设置如下2个声明的，则需要这样转
			 * <%@ page contentType="text/html;charset=UTF-8"%> 
<% request.setCharacterEncoding("UTF-8"); %>
			 * */
			result = new String(str.getBytes("iso8859-1"),"utf-8");
		}
		
		return result;
	}

}
