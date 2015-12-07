package net.lx.action.user;

import java.util.Date;

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
import org.apache.struts2.convention.annotation.ParentPackage;
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
@ParentPackage("json-default")
public class HomeAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028574059500662528L;

	private Log log = LogFactory.getLog(HomeAction.class);
	
	@Action(value="head", results = { @Result(name = "success", location = "/WEB-INF/content/user/user_head.jsp") })
	public String head() throws Exception {
		setCurrentUsers();
		
		return SUCCESS;
	}

	@Action(value="foot", results = { @Result(name = "success", location = "/WEB-INF/content/user/user_foot.jsp") })
	public String foot() throws Exception {
		setCurrentUsers();
		
		return SUCCESS;
	}
	
	@Action(value="left", results = {
			@Result(name = "default_left", location = "/WEB-INF/content/user/left_student.jsp"),
			@Result(name = "consultant_left", location = "/WEB-INF/content/user/left_consultant.jsp"),
			@Result(name = "error", location="/WEB-INF/content/user/left_consultant.jsp")
	})
	public String left() throws Exception {
		setCurrentUsers();
		if(users != null){
			if(users.getUser_type() == Constants.USER_STUDENT)
				return "default_left";
			else if(users.getUser_type() == Constants.USER_CONSULTANT)
				return "consultant_left";
		}
		
		return ERROR;
	}
	
	@Action(value = "home", results = {
			@Result(name = "input", location = "/WEB-INF/content/user/home.jsp")})
	@Override
	public String execute() throws Exception 
	{
		setUsers(userBiz.findUserById(getUser().getUserId()));
		if (isGetRequest()) 
		{
			return INPUT;
		} 
		return INPUT;
	}

}
