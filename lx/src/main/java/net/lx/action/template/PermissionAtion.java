package net.lx.action.template;

import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.ask.IAskMessageBiz;
import net.lx.biz.ask.IAskSessionBiz;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.crm.impl.FollowBizImpl;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.properties.Config;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.ask.AskSession;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideSpecialty;
import net.lx.entity.user.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 登录页面
 * 
 * @author lxl
 * 
 */
@Namespace("/template")
public class PermissionAtion extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4765674269413430162L;

	private Log log = LogFactory.getLog(PermissionAtion.class);
	
	private String message;
	
	@Action(value = "no_permission", results = {
			@Result(name = "input", location = "/WEB-INF/content/template/default/no_permission.jsp")})
	@Override
	public String execute() throws Exception 
	{
		message = "对不起，您没有此功能的操作权限";
		response.setHeader("PERMISSION_ERROR",
				"NO_PERMISSION_ERROR");
		request.setAttribute("PERMISSION_ERROR", ResourcesTool
				.getText("global", "msg.error.no.permission"));
		//mConfig.getServletContext().getRequestDispatcher(Config.getProperty("permission.error.path")).forward(request, response);
		return INPUT;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
