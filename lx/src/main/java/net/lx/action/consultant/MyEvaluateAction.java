package net.lx.action.consultant;

import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.user.UserBiz;
import net.lx.entity.ask.Ask;
import net.lx.entity.user.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录页面
 * 
 * @author lxl
 * 
 */
@Namespace("/consultant")
@ParentPackage("json-default")
public class MyEvaluateAction extends BaseAction 
{

	private Log log = LogFactory.getLog(MyEvaluateAction.class);
	
	@Autowired
	private UserBiz userBiz;
	private User users;

	@Autowired
	private IAskBiz askBiz;
	private List<Ask> myAsks;		//我的问题
	
	
	private int id;
	private Ask entity;		//某一个问题
	
	private String message;
	
	@Action(value = "my_evaluate", results = {
			@Result(name = "input", location = "/WEB-INF/content/consultant/my_evaluate.jsp")})
	@Override
	public String execute() throws Exception 
	{
		setUsers(userBiz.findUserById(getUser().getUserId()));
		//学生   我对顾问的评价
		//顾问 我对。。的评价
		return INPUT;
	}
	
	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public List<Ask> getMyAsks() {
		return myAsks;
	}

	public void setMyAsks(List<Ask> myAsks) {
		this.myAsks = myAsks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ask getEntity() {
		return entity;
	}

	public void setEntity(Ask entity) {
		this.entity = entity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
