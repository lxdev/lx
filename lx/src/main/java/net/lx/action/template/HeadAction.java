package net.lx.action.template;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.entity.user.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 头页面
 * @author Jack
 *
 */
public class HeadAction extends BaseAction 
{
	private static final long serialVersionUID = 2930257746852131926L;

	@Autowired
	private UserBiz userBiz;
	
	private User users;

	@Action(results = { @Result(name = "toheadjsp", location = "/WEB-INF/content/template/default/head.jsp")
	})
	public String execute()throws Exception
	{
		users=userBiz.findUserById(getUser().getUserId());
		return "toheadjsp";
	}

	public User getUsers() {
		return users;
	}
}
