package net.lx.action.ask;

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
@Namespace("/ask")
@ParentPackage("json-default")
public class AskAction extends BaseAction implements ModelDriven<Ask>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7070816574905716435L;

	private Log log = LogFactory.getLog(AskAction.class);

	@Autowired
	private IAskBiz askBiz;
	private List<Ask> myAsks;		//我的问题
	
	private Ask entity = new Ask();		//某一个问题

	@Autowired
	private IAskMessageBiz askMessageBiz;
	private List<AskMessage> myAskMessageList;	//某一个问题的聊天记录
	private AskMessage askMessage = new AskMessage();	//提交聊天内容 及 返回聊天对象
	@Autowired
	private IAskSessionBiz askSessionBiz;
	
	private String message;
	
	@Action(value = "my_ask", results = {
			@Result(name = "input", location = "/WEB-INF/content/ask/my_ask.jsp")})
	@Override
	public String execute() throws Exception 
	{
		setCurrentUsers();
		Ask entity = new Ask();
		entity.setAsk_user_id(users.getUser_id());
		myAsks = askBiz.searchAsksByCondition(entity);
		log.info( String.format("我的问题数={0}", myAsks.size()) );
		return INPUT;
	}
	
	@Action(value = "my_ask_modify", results = { 
			@Result(name = "input", location = "/WEB-INF/content/ask/my_ask_modify.jsp"),
			@Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "my_ask")
			})
	public String modify() throws Exception{
		setCurrentUsers();
		if (isGetRequest()){
			if(entity.getId() != null && entity.getId() > 0)
				entity = askBiz.findById(entity.getId());
			return INPUT;
		}
		if(users == null)
			setCurrentUsers();
		if(entity.getId() != null && entity.getId() > 0){
			AskMessage condition = new AskMessage();
			condition.setAsk_id(entity.getId());
			if(askMessageBiz.searchByCondition(condition).size() > 0){
				message = "已存在聊天，不允许修改问题！";
				return ERROR;
			}
			Ask old = askBiz.findById(entity.getId());
			old.setAsk_type(entity.getAsk_type());
			old.setStatus(1);
			old.setTitle(entity.getTitle());
			old.setBody(entity.getBody());
			if(entity.getAsk_type() == 2)
				old.setReply_user_id(0);
			askBiz.modify(old);
		}else if(entity.getAsk_type() != 0){
			Date now = new Date();
			entity.setAsk_user_id(users.getUser_id());
			entity.setStatus(1);
			entity.setPayed_date(now);
			entity.setCreated_date(now);
			entity.setCreated_user_id(users.getUser_id());
			if(entity.getAsk_type() == 2)
				entity.setReply_user_id(0);
			askBiz.createNew(entity);
		}
		return SUCCESS;
	}
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "my_ask_remove", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String remove() throws Exception
	{
		message ="";
		if(entity != null && entity.getId() > 0){
			Ask f = askBiz.findById(entity.getId());
			if(f != null && f.getCreated_user_id() == users.getUser_id()){
				if(f.getStatus() == Constants.ASK_STATUS_DRAFT || f.getStatus() == Constants.ASK_STATUS_WAIT){
					askBiz.deleteById(entity.getId());
				}else
					message = "问题已有人回答了，无法删除！";
			}else
				message = "问题不是本人的，无法删除！";
		}
		
		return SUCCESS;
	}
	

	@Action(value = "my_ask_chart", results = { 
			@Result(name = "input", location = "/WEB-INF/content/ask/my_ask_chart.jsp"),
			@Result(name = "success", type = "json", params = {"contentType", "text/json" })
			})
	public String chart() throws Exception{
		setCurrentUsers();
		if (isGetRequest()){
			
			if(entity != null && entity.getId() > 0){
				entity = askBiz.findById(entity.getId());
				AskMessage _msg = new AskMessage();
				_msg.setAsk_id(entity.getId());
				myAskMessageList = askMessageBiz.searchByCondition(_msg);
			}
			
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	@Action(value = "chart_send", results = { @Result(name="success", type="json", params = {"contentType", "text/json"}) })
	public String chart_send() throws Exception{
		setCurrentUsers();
		if(askMessage.getBody() != null && !askMessage.getBody().equals("") && askMessage.getAsk_id() > 0){
			Integer replyUserId = 0;
			if(entity.getId() == null || entity.getId() <= 0){
				entity = askBiz.findById(askMessage.getAsk_id());
			}
			if(!users.getUser_id().equals(entity.getAsk_user_id())){	//当前用户！=提问人
				if(entity.getReply_user_id() != null && entity.getReply_user_id() != 0){	//已指定回复人
					if(!users.getUser_id().equals(entity.getReply_user_id())){	//当前用户！=指定回复人
						return ERROR;
					}
				}else if(users.getUser_type() == Constants.USER_CONSULTANT){
					replyUserId = users.getUser_id();
					entity.setReply_user_id(replyUserId);
					askBiz.modify(entity);
				}
			}
			Integer sessionId = 0;
			AskSession sessionCondition = new AskSession();
			sessionCondition.setAsk_id(askMessage.getAsk_id());
			List<AskSession> _sessions = askSessionBiz.searchByCondition(sessionCondition);
			if(_sessions.size() <= 0){
				sessionCondition.setAsk_user_id(entity.getAsk_user_id());
				sessionCondition.setReply_user_id(entity.getReply_user_id());
				askSessionBiz.createNew(sessionCondition);
				sessionId = askSessionBiz.searchByCondition(sessionCondition).get(0).getId();
			}else{
				sessionCondition = _sessions.get(0);
				if(replyUserId > 0){
					if(sessionCondition.getReply_user_id() == null || sessionCondition.getReply_user_id() == 0)
						sessionCondition.setReply_user_id(replyUserId);
					askSessionBiz.modify(sessionCondition);
				}
				sessionId = sessionCondition.getId();
			}
			askMessage.setMessage_type(1);	//1 文字
			askMessage.setSend_date(new Date());
			askMessage.setSend_user_id(users.getUser_id());
			askMessage.setSend_status(1);	//1 有效
			askMessage.setBody_length(askMessage.getBody().length());
			askMessage.setSession_id(sessionId);
			askMessage.setSend_user(users);
			askMessageBiz.createNew(askMessage);
		}
		return SUCCESS;
	}

	public List<Ask> getMyAsks() {
		return myAsks;
	}

	public void setMyAsks(List<Ask> myAsks) {
		this.myAsks = myAsks;
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

	public List<AskMessage> getMyAskMessageList() {
		return myAskMessageList;
	}

	public void setMyAskMessageList(List<AskMessage> myAskMessageList) {
		this.myAskMessageList = myAskMessageList;
	}

	@Override
	public Ask getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public AskMessage getAskMessage() {
		return askMessage;
	}

	public void setAskMessage(AskMessage askMessage) {
		this.askMessage = askMessage;
	}


}
