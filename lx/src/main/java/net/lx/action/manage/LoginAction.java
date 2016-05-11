package net.lx.action.manage;

import javax.servlet.http.Cookie;


import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.CookieConstants;
import net.lx.common.enums.LoginErrorEnum;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.common.properties.Config;
import net.lx.entity.user.User;
import net.lx.model.base.AuthenticationTicket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录页面
 * 
 * @author lixl
 * 
 */
@Namespace("/manage")
public class LoginAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3088893425424413623L;
	
	private Log log = LogFactory.getLog(LoginAction.class);
	
	@Autowired
	private UserBiz userBiz;
	
	
	private String checkrand,password,username;
	
	@Action(results = {
			@Result(name = "input", location = "/WEB-INF/content/manage/login.jsp"),
			@Result(name = "success", location = "/manage/index", type = "redirect")})
	@Override
	public String execute() throws Exception 
	{
		if (isGetRequest()) 
		{
			return INPUT;
		} 
		if(checkrand.toLowerCase().equals(session.getAttribute("checkrand")))
		{
			User user=userBiz.findUserByUserName(username);
			if(password != null){
				String pwd = PwdCoder.getMD5(password);
				log.info(pwd);
			}
			if(null==user)
			{
				returnError(LoginErrorEnum.NO_USER);
				log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" no user!");
			}
//			else if(password.equals(user.getPassword()))
			else if(PwdCoder.getMD5(password).equals(user.getPassword()))
			//else if(true)
			{
				if(UserEnum.StatusStop.value()==user.getStatus())
				{
					returnError(LoginErrorEnum.LOCKED);
					log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user locked!");
				}
				else if(user.getUser_type() == 3)
				{
					createSession(user);
					//TODO 初始化权限
					return SUCCESS;
				}else {

					returnError(LoginErrorEnum.NOT_MANAGER);
					log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user not manager!");
				}
			}
			else {
				returnError(LoginErrorEnum.PASSWORD_ERROR);
				log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user password error!");
			}
		}
		else {
			returnError(LoginErrorEnum.CHECK_RAND);
			log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" verification code error!");
		}
		return INPUT;
	}
	
	/**
	 * 加载错误提示
	 * @param loginErrorEnum
	 */
	private void returnError(LoginErrorEnum loginErrorEnum)
	{
		switch(loginErrorEnum)
		{
			case CHECK_RAND:
				request.setAttribute("error",getText("msg.error.login.checkrand"));
				break;			
			case NO_USER:
				request.setAttribute("error",getText("msg.error.login.username"));
				break;
			case PASSWORD_ERROR:
				request.setAttribute("error",getText("msg.error.login.password"));
				break;
			case LOCKED:
				request.setAttribute("error",getText("msg.error.login.state"));
				break;
			case NOT_MANAGER:
				request.setAttribute("error",getText("msg.error.login.not.manager"));
			default:break;
		}
	}
	
	

	public void setCheckrand(String checkrand) {
		this.checkrand = checkrand;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
