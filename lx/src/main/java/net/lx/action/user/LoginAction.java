package net.lx.action.user;

import javax.servlet.http.Cookie;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
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
 * @author lxl
 * 
 */
@Namespace("/user")
public class LoginAction extends BaseAction 
{
	private static final long serialVersionUID = -4186074065546374674L;
	
	private Log log = LogFactory.getLog(LoginAction.class);
	
	private String checkrand,password,username;
	private Integer userType = 1;

	@Action(results = {
			//@Result(name = "input", location = "/WEB-INF/content/template/default/first.jsp", type = "dispatcher"),
			@Result(name = "input", location = "/template/first?show_login=1", type = "redirect"),
			@Result(name = "success", location = "/user/home", type = "redirect")})
	@Override
	public String execute() throws Exception 
	{
		if (isGetRequest()) 
		{
			return INPUT;
		} 

		User user = null;
		user = userBiz.findUserByUserName(username);
		if(null==user)
		{
			user = userBiz.findUserByMobile(username);
			if(null == user){
				user = userBiz.findUserByEmail(username);
				if(null == user){
					returnError(LoginErrorEnum.NO_USER);
					log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" no user!");
				}
			}
		} else {
			if(PwdCoder.getMD5(password).equals(user.getPassword()))
			{
				if(UserEnum.StatusStop.value()==user.getStatus())
				{
					returnError(LoginErrorEnum.LOCKED);
					log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user locked!");
				}
				else
				{
					createSession(user);
					//TODO 初始化权限
					return SUCCESS;
				}
			}
			else {
				returnError(LoginErrorEnum.PASSWORD_ERROR);
				log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user password error!");
			}
		}

		return INPUT;
	}
	
	/**
	 * 加载错误提示
	 * @param loginErrorEnum
	 */
	private void returnError(LoginErrorEnum loginErrorEnum)
	{
		Object _error = null;
		switch(loginErrorEnum)
		{
			case CHECK_RAND:
				_error = getText("msg.error.login.checkrand");
				break;			
			case NO_USER:
				_error = getText("msg.error.login.username");
				break;
			case PASSWORD_ERROR:
				_error = getText("msg.error.login.password");
				break;
			case LOCKED:
				_error = getText("msg.error.login.state");
				break;
			default:break;
		}
		request.setAttribute("error", _error);
		request.getSession(false).setAttribute("loginError", _error);
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
