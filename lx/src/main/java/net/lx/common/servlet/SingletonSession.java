package net.lx.common.servlet;

import javax.servlet.http.HttpSession;

/**
 * 单例Session的对象
 * 
 */
public class SingletonSession {

	private static HttpSession session = null;

	private SingletonSession() {
	}

	public static void init(HttpSession session_) {
		if (session == null) {
			session = session_;
		}
	}

	public static HttpSession newInstance() {
		return session;
	}
}
