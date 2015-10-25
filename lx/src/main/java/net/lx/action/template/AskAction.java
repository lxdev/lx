package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.crm.impl.FollowBizImpl;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
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
@Namespace("/template")
public class AskAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8503401938154631647L;

	private Log log = LogFactory.getLog(AskAction.class);

	@Autowired
	private IAskBiz askBiz;
	private List<Ask> askList = new ArrayList<Ask>();		//问题
	
	private Ask entity = new Ask();
	
	private String message;
	private int id;
	
	@Action(value = "asks", results = {
			@Result(name = "input", location = "/WEB-INF/content/ask/ask_index.jsp")})
	@Override
	public String execute() throws Exception {
		setCurrentUsers();
		Ask entity = new Ask();
		entity.setNo_status(Constants.ASK_STATUS_DRAFT);
		setAskList(askBiz.searchAsksByCondition(entity));
		log.info( String.format("总问题数={0}", getAskList().size()) );
		return INPUT;
	}


	@Action(value = "ask_detail", results = {
			@Result(name = "input", location = "/WEB-INF/content/ask/ask_detail.jsp")})
	public String ask_detail() throws Exception {
		setCurrentUsers();
		entity = askBiz.findById(id);
		return INPUT;
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


	public List<Ask> getAskList() {
		return askList;
	}


	public void setAskList(List<Ask> askList) {
		this.askList = askList;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
