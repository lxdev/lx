package net.lx.action.user;

import java.util.Date;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.md5.PwdCoder;
import net.lx.entity.user.User;

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
public class SettingAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542045862938063130L;
	private Log log = LogFactory.getLog(SettingAction.class);
	
	@Autowired
	private UserBiz userBiz;
	
	private User condition;
	private String message = "";
	private String pwdOld;
	private String pwdNew;
	
	@Action(results = { @Result(name = "success", location = "/WEB-INF/content/user/setting.jsp") })
	public String head() throws Exception {
		setCurrentUsers();
		
		return SUCCESS;
	}
	
	@Action(value="set_pwd", results = {
			@Result(name = "success", location = "/WEB-INF/content/user/setting_pwd.jsp")
	})
	public String pwd() throws Exception {
		setCurrentUsers();
		
		return SUCCESS;
	}
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "post_set_pwd", results = { 
			@Result(name = "success", type = "redirect", location = "home"), 
			@Result(name = "error", type = "redirect", params = { "encode", "true" }, location = "set_pwd?message=${message}") 
	})
	public String post_pwd() throws Exception
	{
		setCurrentUsers();
		setMessage("");
		if(!pwdOld.equals("") && !pwdNew.equals("")){
			if(PwdCoder.getMD5(pwdOld).equals(users.getPassword())){
				//condition.
				users.setPassword(PwdCoder.getMD5(pwdNew));
				users.setUpdate_password_time(new Date());
				users.setUpdated_date(new Date());
				users.setUpdated_user_id(users.getUser_id());
				userBiz.modify(users);
				
				return SUCCESS;
			}else {
				setMessage("原密码不正确!");
				return ERROR;
			}
		}else {
			setMessage("输入密码为空!");
			return ERROR;
		}
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getCondition() {
		return condition;
	}

	public void setCondition(User condition) {
		this.condition = condition;
	}

	public String getPwdOld() {
		return pwdOld;
	}

	public void setPwdOld(String pwdOld) {
		this.pwdOld = pwdOld;
	}

	public String getPwdNew() {
		return pwdNew;
	}

	public void setPwdNew(String pwdNew) {
		this.pwdNew = pwdNew;
	}

}
