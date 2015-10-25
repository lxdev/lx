package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.ask.IAskReplyBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskReply;
import net.lx.entity.user.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 点击头像 访问别人的首页
 * 
 * @author lxl
 * 
 */
@Namespace("/template")
public class UserHomeAction extends BaseAction implements ModelDriven<User>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4147944380029351173L;

	private Log log = LogFactory.getLog(UserHomeAction.class);

	@Autowired
	private UserBiz userBiz;
	private List<User> userList;
	
	
	private int id;
	private String user_name;
	private User entity = new User();
	
	@Autowired
	private IAskBiz askBiz;
	private List<Ask> askList = new ArrayList<Ask>();		//问题
	@Autowired
	private IAskReplyBiz replyBiz;
	private List<AskReply> replyList = new ArrayList<AskReply>();
	
	private String message;

	@Action(value = "user_home", results = {
			@Result(name = "student", location = "/WEB-INF/content/user/user_home.jsp"),
			@Result(name = "consultant", location = "/WEB-INF/content/user/user_home_consultant.jsp")})
	@Override
	public String execute() throws Exception {
		message = "";
		if(id > 0){
			setEntity(userBiz.findUserById(id));	//user info
		}else if(user_name != null && !user_name.equals("")){
			setEntity(userBiz.findUserByUserName(user_name));
		}
		if(entity.getUser_type() == Constants.USER_STUDENT){
			Ask _ask = new Ask();
			_ask.setNo_status(Constants.ASK_STATUS_DRAFT);
			_ask.setAsk_user_id(id);
			setAskList(askBiz.searchAsksByCondition(_ask));	//user ask list 
			AskReply _reply = new AskReply();
			_reply.setCreated_user_id(id);
			_reply.setReply_type(Constants.REPLY_REPLY);
			setReplyList(replyBiz.searchAskReplysByCondition(_reply));	//user reply list
			
			return "student";
		}
		else if(entity.getUser_type() == Constants.USER_CONSULTANT)
			return "consultant";
		return ERROR;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getEntity() {
		return entity;
	}

	public void setEntity(User entity) {
		this.entity = entity;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public List<Ask> getAskList() {
		return askList;
	}

	public void setAskList(List<Ask> askList) {
		this.askList = askList;
	}

	public List<AskReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<AskReply> replyList) {
		this.replyList = replyList;
	}


}
