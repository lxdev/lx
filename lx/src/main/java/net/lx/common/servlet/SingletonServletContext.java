package net.lx.common.servlet;

import javax.servlet.ServletContext;

/**
 * 
 * @功能：单例ServletContext的对象
 * 
 */
public class SingletonServletContext {

	private static ServletContext servletContext = null;

	private SingletonServletContext() {
	}

	public static void init(ServletContext sc) {
		if (servletContext == null) {
			servletContext = sc;
		}
	}

	public static ServletContext newInstance() {
		return servletContext;
	}
}
