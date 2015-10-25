package net.lx.action.user;

import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.IUserExtendConsultantBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.entity.ask.Ask;
import net.lx.entity.user.User;
import net.lx.entity.user.UserExtend;
import net.lx.entity.user.UserExtendConsultant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询 用户列表
 * 
 * @author lxl
 * 
 */
@Namespace("/user")
@ParentPackage("json-default")
public class UserAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1426623736745064371L;

	private Log log = LogFactory.getLog(UserAction.class);

	@Autowired
	private UserBiz userBiz;
	private List<User> userList;
	
	@Autowired
	private IUserExtendBiz userExtendBiz;
	private UserExtend userExtend = new UserExtend();
	@Autowired
	private IUserExtendConsultantBiz userExtendConsultantBiz;
	private UserExtendConsultant userExtendConsultant = new UserExtendConsultant();
	
	private int id;
	private User entity;
	
	private String full_name;
	
	private String message;
	
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "consultant_search", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String consultant_search() throws Exception
	{
		message ="";
		User condition = new User();
		if(!full_name.equals(""))
			condition.setFull_name(full_name);
		condition.setUser_type(Constants.USER_CONSULTANT);
		userList = userBiz.findUsersByCondition(condition);
		
		
		return SUCCESS;
	}

	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_search", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String student_search() throws Exception
	{
		message ="";
		User condition = new User();
		if(!full_name.equals(""))
			condition.setFull_name(full_name);
		condition.setUser_type(Constants.USER_STUDENT);
		userList = userBiz.findUsersByCondition(condition);
		
		return SUCCESS;
	}
	

	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value="my_info", results = { 
			@Result(name = "input1", location = "/WEB-INF/content/user/my_info_student.jsp"),
			@Result(name = "input2", location = "/WEB-INF/content/user/my_info_consultant.jsp") })
	public String my_info() throws Exception
	{
		setCurrentUsers();
		
		log.info( String.format("用户id={0}", users.getUser_id()) );
		
		if(users.getUser_type() == 1){
			userExtend = userExtendBiz.findUserByUserId(users.getUser_id());
			return "input1";
		}else if(users.getUser_type() == 2){
			userExtendConsultant = userExtendConsultantBiz.findUserConsultantByUserId(users.getUser_id());
			return "input2";
		}else
			return null;
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

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public UserExtend getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtend userExtend) {
		this.userExtend = userExtend;
	}

	public UserExtendConsultant getUserExtendConsultant() {
		return userExtendConsultant;
	}

	public void setUserExtendConsultant(UserExtendConsultant userExtendConsultant) {
		this.userExtendConsultant = userExtendConsultant;
	}


}
