package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.crm.impl.FollowBizImpl;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.university.University;
import net.lx.entity.user.User;
import net.lx.entity.user.UserExtend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * 登录页面
 * 
 * @author lxl
 * 
 */
@Namespace("/template")
public class EvaluateAction extends BaseAction implements ModelDriven<Evaluate>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2894667887964045550L;

	private Log log = LogFactory.getLog(EvaluateAction.class);

	@Autowired
	private IEvaluateBiz evaluateBiz;
	private Evaluate entity = new Evaluate();		//评价
	@Autowired
	private IUserExtendBiz userExtendBiz;
	private UserExtend userExtend = new UserExtend();
	
	private String message;
	private int id;
	
	@Action(value = "evaluate_detail", results = {
			@Result(name = "input", location = "/WEB-INF/content/university/evaluate_detail.jsp")})
	@Override
	public String execute() throws Exception {
		entity = evaluateBiz.findById(entity.getId());
		userExtend = userExtendBiz.findUserByUserId(entity.getEvaluate_from_user_id());
		return INPUT;
	}
	
	public Evaluate getEntity() {
		return entity;
	}

	public void setEntity(Evaluate entity) {
		this.entity = entity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public Evaluate getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public UserExtend getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtend userExtend) {
		this.userExtend = userExtend;
	}
}
