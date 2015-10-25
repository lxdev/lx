package net.lx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.lx.biz.user.UserBiz;
import net.lx.common.CookieConstants;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.properties.Config;
import net.lx.common.servlet.SingletonServletContext;
import net.lx.common.servlet.SingletonSession;
import net.lx.entity.user.User;
import net.lx.init.BootStrap;
import net.lx.init.SysBootStrap;
import net.lx.model.base.AuthenticationTicket;
import net.lx.model.base.SessionUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 启动拦截器类
 * 
 */
public class InitFilter implements Filter {
	private final Log log = LogFactory.getLog(InitFilter.class);
	private FilterConfig mConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(true);
		SingletonSession.init(session);
		// TODO:登录校验
		// TODO:权限校验
		// 获取地址
		String nowURl = request.getServletPath();
		// 获取session用户
		AuthenticationTicket user = (AuthenticationTicket) session.getAttribute("userTicket");
		SessionUser.setSessionUser(user);
		if(nowURl.startsWith("/lx")){
			response.sendRedirect(request.getContextPath() + nowURl.substring(5));
			return;
		}
		
		// 跳转到首页
		if (nowURl.indexOf("manage/login") > 0 && user != null) {
			response.sendRedirect(request.getContextPath() + "/manage/index");
			return;
		}
		// 跳转到首页
		if (nowURl.indexOf("user/login") > 0 && user != null) {
			response.sendRedirect(request.getContextPath() + "/user/home");
			return;
		}

		// 退出
		if (nowURl.indexOf("manage_log_out") > 0) {
			session.invalidate();
			//是否开启cookie
			if(Config.getBoolProperty("isturned.cookie")){
				//删除cookie
				removeCookie(request,response);
			}
			response.sendRedirect(request.getContextPath() + "/manage/login");
			return;
		}
		if (nowURl.indexOf("log_out") > 0) {
			session.invalidate();
			//是否开启cookie
			if(Config.getBoolProperty("isturned.cookie")){
				//删除cookie
				removeCookie(request,response);
			}
			response.sendRedirect(request.getContextPath() + "/user/login");
			return;
		}
		// 报名接口
		if (nowURl.endsWith("css")
				|| nowURl.endsWith("js")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		} else {
			// 重新登录
			boolean verification = verificationUser(user,request);
			if (verification) {
				//首页
				//if(nowURl.indexOf("template/index") > 0){
				if(nowURl.indexOf("template/") > 0 && nowURl.indexOf("template/index") < 0){
					filterChain.doFilter(servletRequest, servletResponse);
				}
				else if (nowURl.indexOf("login") > 0 || nowURl.indexOf("register") > 0 || nowURl.indexOf("vcode") > 0
						|| nowURl.endsWith("gif") || nowURl.endsWith("jpg")
						|| nowURl.endsWith("png")
						|| nowURl.endsWith("index.jsp")) {
					// 系统登录
					// mConfig.getServletContext().getRequestDispatcher(
					// Config.getProperty("login.path")).forward(request,
					// response);
					filterChain.doFilter(servletRequest, servletResponse);
				} else {
//					filterChain.doFilter(servletRequest, servletResponse);
					// 会话丢失
					response.setHeader("SESSION_ERROR", "NO_SESSION");
//					response.setHeader("LOGIN_PATH", request.getContextPath() + "/template/login");
					if(nowURl.indexOf("manage/") > 0)
						response.setHeader("LOGIN_PATH", request.getContextPath() + "/manage/login");
					else 
						response.setHeader("LOGIN_PATH", request.getContextPath() + "/user/login");
					
					request.setAttribute("SESSION_ERROR", ResourcesTool
							.getText("global", "msg.error.no.session"));
					mConfig.getServletContext()
							.getRequestDispatcher(
									Config.getProperty("nosession.error.path"))
							.forward(request, response);
					return;
				}

			} else {
				// 验证权限
				// System.out.println("权限验证通过！");
				
				if (true) {
					filterChain.doFilter(servletRequest, servletResponse);
				} else {
					response.setHeader("PERMISSION_ERROR",
							"NO_PERMISSION_ERROR");
					request.setAttribute("PERMISSION_ERROR", ResourcesTool
							.getText("global", "msg.error.no.permission"));
					mConfig.getServletContext()
							.getRequestDispatcher(
									Config.getProperty("permission.error.path"))
							.forward(request, response);
				}
			}
		}
	}
	//检测用户
	private boolean verificationUser(AuthenticationTicket user,HttpServletRequest request){
		//是否开启cookie
		if(Config.getBoolProperty("isturned.cookie")){
			if(user==null){
				//cookie
				Cookie[] cookies = request.getCookies();  
		        if (cookies != null && cookies.length > 0) { 
		        	String userName=null;
		        	String userPass=null;
		        	for (int i = 0; i < cookies.length; i++) {  
		                Cookie cookie = cookies[i];  
		                // 用户名
		                if (CookieConstants.BROWSER_COOKIE_USER.equals(cookie.getName())) {  
		                	userName = cookie.getValue().trim();  
		                }  
		                // 密码
		                if (CookieConstants.BROWSER_COOKIE_PASSWORD.equals(cookie.getName())) {  
		                	userPass = cookie.getValue().trim();  
		                }  
		            }
		        	
		        	if(userName!=null&&!userName.equals("")&&userPass!=null&&!userPass.equals("")){
		        		User user_=null;
		        		log.info("读取用户["+userName+"]cookie....");
		        		WebApplicationContext w = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		        		UserBiz userBiz=(UserBiz)w.getBean("userBizImpl");
		        		try {
							user_=userBiz.findUserByUserName(userName);
							if(user_!=null&&user_.getPassword().equals(userPass)){
								//创建session 
								log.info("验证["+userName+"]成功...");
				        		createSession(user_,request);
								return false;
							}else{
								log.info("["+userName+"]用户不存在或密码验证错误....");
							}
						} catch (Exception e) {
							log.error("获取cookie错误;错误信息为:"+e.getMessage());
						}
			        }else{
			        	log.info("没有用户cookie...");
			        }
		        	
		        	return true;
		        }
			}
			return false;
		}else{
			return user==null;
		}
	}
	/**
	 * 创建用户会话
	 * @param user
	 */
	private void createSession(User user,HttpServletRequest request)
	{
		AuthenticationTicket ticket = new AuthenticationTicket();
		ticket.setUserId(user.getUser_id());
		ticket.setFullName(user.getFull_name());
		ticket.setUserName(user.getUser_name());
		ticket.setType(user.getUser_type());
		request.getSession().setAttribute(CookieConstants.SESSION_USER,ticket);
	}
	
	/**
	 * 删除cookie
	 * @param cookieingUser
	 */
    private void removeCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie cookieUserPass = new Cookie(CookieConstants.BROWSER_COOKIE_PASSWORD,  null);  
        cookieUserPass.setMaxAge(CookieConstants.BROWSER_COOKIE_MAX_AGE);  
        cookieUserPass.setPath(request.getContextPath());  
        response.addCookie(cookieUserPass);
    }  

	public void init(FilterConfig arg0) throws ServletException {
		mConfig = arg0;
		// 全局，单例的ServletContext对象初始化
		SingletonServletContext.init(arg0.getServletContext());
		// 初始化系统数据
		WebApplicationContext w = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		//系统数据初始化
		SysBootStrap sysBootStrap = (SysBootStrap) w.getBean("sysBootStrap");
		sysBootStrap.init();
		//用户自定义初始化
		BootStrap bootStrap = (BootStrap) w.getBean("bootStrap");
		bootStrap.init();
		

	}
}
