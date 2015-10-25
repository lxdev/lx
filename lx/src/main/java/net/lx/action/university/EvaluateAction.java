package net.lx.action.university;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.CookieConstants;
import net.lx.common.enums.LoginErrorEnum;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.common.properties.Config;
import net.lx.entity.dic.Country;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.user.User;
import net.lx.model.base.AuthenticationTicket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
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
@Namespace("/university")
@ParentPackage("json-default")
public class EvaluateAction extends BaseAction 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3306517852740857695L;

	private Log log = LogFactory.getLog(EvaluateAction.class);
	
	@Autowired
	private UserBiz userBiz;
	private User users;
	
	private String message;
	
	@Autowired
	private IEvaluateBiz evaluateBiz;// country业务接口
	private List<Evaluate> evaluateList = new ArrayList<Evaluate>();
	
	@Action(value = "evaluate", results = {
			@Result(name = "input", location = "/WEB-INF/content/university/evaluate.jsp")
	})
	@Override
	public String execute() throws Exception 
	{
		setUsers(userBiz.findUserById(getUser().getUserId()));
		Evaluate condition = new Evaluate();
		condition.setEvaluate_type(Constants.EVALUATE_TYPE_UNIVERSITY);
		condition.setEvaluate_from_user_id(users.getUser_id());
		setEvaluateList(evaluateBiz.searchEvaluatesByCondition(condition));
			
		return INPUT;
	}
	
	//--------------------------------
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public List<Evaluate> getEvaluateList() {
		return evaluateList;
	}

	public void setEvaluateList(List<Evaluate> evaluateList) {
		this.evaluateList = evaluateList;
	}

	
}
