package net.lx.action.user;

import java.util.Date;

import javax.servlet.http.Cookie;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.common.CookieConstants;
import net.lx.common.enums.LoginErrorEnum;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.common.properties.Config;
import net.lx.common.vcode.RandMessageCodeCreater;
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
public class RegisterAction extends BaseAction 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5742552070632240092L;

	private Log log = LogFactory.getLog(RegisterAction.class);
	
	private String message;
	
	private String mobile, email, code, username, password, registerType;
	private int userType = 1;
	private int userId = 0;		//注册成功后的用户Id
	
	@Action(value = "register_student", results = {
			@Result(name = "input", location = "/WEB-INF/content/user/register.jsp"),
			@Result(name = "success", location = "register_next?userId=${userId}", params = { "encode", "true" }, type = "redirect"),
			@Result(name = "error", location = "/WEB-INF/content/user/register.jsp", type = "dispatcher")})
	@Override
	public String execute() throws Exception 
	{
		if (isGetRequest()) 
		{
			return INPUT;
		} 
		return checkAndSubmit();
	}
	@Action(value = "register_teacher", results = {
			@Result(name = "input", location = "/WEB-INF/content/user/register_teacher.jsp"),
			@Result(name = "success", location = "login", type = "redirect"),
			@Result(name = "error", location = "/WEB-INF/content/user/register_teacher.jsp", type = "dispatcher")})
	public String execute2() throws Exception 
	{
		if (isGetRequest()) 
		{
			return INPUT;
		} 
		return checkAndSubmit();
	}
	
	private String checkAndSubmit() throws Exception{
		if("mobile".equals(getRegisterType()) && ("".equals(mobile) || "".equals(code) || "".equals(username) || "".equals(password))){
			message = "用户信息不完整，请检查输入后再尝试！";
			return ERROR;
		}
		if("email".equals(getRegisterType()) && ("".equals(email) || "".equals(code) || "".equals(username) || "".equals(password))){
			message = "用户信息不完整，请检查输入后再尝试！";
			return ERROR;
		}
		
		if("mobile".equals(getRegisterType())){
			//1.1  验证 手机号和 验证码是否一致
			String systemCode = (String)request.getSession().getAttribute(CookieConstants.SESSION_MOBILE_CODE + mobile);
			if(!systemCode.equals(code)){
				message = "输入动态密码不正确！";
				return ERROR;
			}
			//1.2  验证手机号在平台是否存在
			User userTemp = userBiz.findUserByMobile(mobile);
			if(userTemp != null){
				message = "输入手机号已存在！";
				return ERROR;
			}
			//1.3  验证用户名在平台是否存在
			userTemp = userBiz.findUserByUserName(username);
			if(userTemp != null){
				message = "输入用户名已存在！";
				return ERROR;
			}
			//1.4  生成用户
			User user = new User();
			user.setUser_name(username);
			user.setUser_mobile(mobile);
			user.setPassword(password);
			user.setUser_type(getUserType());
			user.setStatus(1);
			user.setDelete_flag(0);
			user.setIs_manager(0);
			user.setCreated_user_id(-1);
			user.setUpdated_user_id(-1);
			user.setCreated_date(new Date());
			
			userBiz.createNew(user);
			userId = userBiz.findUserByMobile(mobile).getUser_id();
		}
		if("email".equals(getRegisterType())){

			userId = userBiz.findUserByEmail(email).getUser_id();
		}
		
		return SUCCESS;
	}
	
	/*
	 * 手机验证码生成
	 * @return
	 * @throws Exception
	 */
	@Action(value = "vcode", results = { @Result(name = "success", type = "json", params = {"contentType", "text/json" }) })
	public String createCode() throws Exception
	{
		message = "";
		if(mobile.equals("")){
			message = "手机号不符合！";
			return SUCCESS;
		}
		
		Object time_obj = request.getSession().getAttribute(CookieConstants.SESSION_MOBILE_CODE + mobile + "time");
		//if(time_obj == null || (new Date()).getHours()*3600 + (new Date()).getMinutes()*60 + (new Date()).getSeconds() - ((new Date((String)time_obj)).getHours()*3600 + (new Date((String)time_obj)).getMinutes()*60 + (new Date((String)time_obj)).getSeconds()) > 60){
		//	Date sTime = (new Date((String)time_obj));
			//int a = (new Date()).getHours()*3600 + (new Date()).getMinutes()*60 + (new Date()).getSeconds() - (Date.parse((String)time_obj).getHours()*3600 + ((Date)time_obj).getMinutes()*60 + ((Date)time_obj).getSeconds();
			//生成一个 Session变量
			String mobile_valid_code = RandMessageCodeCreater.codeRandom();
			//在后面注册时 进行校验用
			request.getSession().setAttribute(CookieConstants.SESSION_MOBILE_CODE + mobile, mobile_valid_code);
			request.getSession().setAttribute(CookieConstants.SESSION_MOBILE_CODE + mobile + "time", (new Date()).toString());
			RandMessageCodeCreater.sendMsg(mobile_valid_code, mobile);
		//}else
		//	message = "您点击太快了！";
		
		return SUCCESS;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisterType() {
		return registerType;
	}
	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
