package net.lx.action.follow;

import java.util.ArrayList;
import java.util.List;


import net.lx.action.BaseAction;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
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
 * 收藏
 * 
 * @author lxl
 * 
 */
@Namespace("/follow")
@ParentPackage("json-default")
public class FollowAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3306517852740857695L;

	private Log log = LogFactory.getLog(FollowAction.class);
	
	private String message;
	
	@Autowired
	private IFollowBiz followBiz;// country业务接口
	private List<Follow> followList = new ArrayList<Follow>();
	private int followType = 0;
	private String followName = "";
	private int followId;

	@Action(value = "course", results = { @Result(name = "input", location = "/WEB-INF/content/follow/index.jsp") })
	@Override
	public String execute() throws Exception 
	{
		//setUsers(userBiz.findUserById(getUser().getUserId()));
		setCurrentUsers();
		Follow condition = new Follow();
		condition.setFollow_type(Constants.FOLLOW_PROGRAM);
		condition.setFollow_user_id(users.getUser_id());
		setFollowList(followBiz.searchByCondition(condition));
		setFollowType(Constants.FOLLOW_PROGRAM);
		setFollowName("课程");
		return INPUT;
	}
	
	@Action(value = "university", results = { @Result(name = "input", location = "/WEB-INF/content/follow/index.jsp") })
	public String university() throws Exception 
	{
		//setUsers(userBiz.findUserById(getUser().getUserId()));
		setCurrentUsers();
		Follow condition = new Follow();
		condition.setFollow_type(Constants.FOLLOW_UNIVERSITY);
		condition.setFollow_user_id(users.getUser_id());
		setFollowList(followBiz.searchByCondition(condition));
		setFollowType(Constants.FOLLOW_UNIVERSITY);
		setFollowName("院校");
		return INPUT;
	}

	@Action(value = "guide", results = { @Result(name = "input", location = "/WEB-INF/content/follow/index.jsp") })
	public String guide() throws Exception 
	{
		//setUsers(userBiz.findUserById(getUser().getUserId()));
		setCurrentUsers();
		Follow condition = new Follow();
		condition.setFollow_type(Constants.FOLLOW_GUIDE);
		condition.setFollow_user_id(users.getUser_id());
		setFollowList(followBiz.searchByCondition(condition));
		setFollowType(Constants.FOLLOW_GUIDE);
		setFollowName("攻略");
		return INPUT;
	}

	@Action(value = "ask", results = { @Result(name = "input", location = "/WEB-INF/content/follow/index.jsp") })
	public String ask() throws Exception 
	{
		//setUsers(userBiz.findUserById(getUser().getUserId()));
		setCurrentUsers();
		Follow condition = new Follow();
		condition.setFollow_type(Constants.FOLLOW_ASK);
		condition.setFollow_user_id(users.getUser_id());
		setFollowList(followBiz.searchByCondition(condition));
		setFollowType(Constants.FOLLOW_ASK);
		setFollowName("问题");
		return INPUT;
	}

	@Action(value = "consultant", results = { @Result(name = "input", location = "/WEB-INF/content/follow/index.jsp") })
	public String consultant() throws Exception 
	{
		//setUsers(userBiz.findUserById(getUser().getUserId()));
		setCurrentUsers();
		Follow condition = new Follow();
		condition.setFollow_type(Constants.FOLLOW_CONSULTANT);
		condition.setFollow_user_id(users.getUser_id());
		setFollowList(followBiz.searchByCondition(condition));
		setFollowType(Constants.FOLLOW_CONSULTANT);
		setFollowName("顾问");
		return INPUT;
	}

	/**
	 * 取消follow
	 * @return
	 * @throws Exception
	 */
	@Action(value="follow_cancel",
			results={
				@Result(name = "success", type = "json", params={"contentType",  "text/json"} )
	})
	public String cancel()throws Exception
	{
		//Follow f = followBiz.findById(followId);
		//if(f != null){
			followBiz.deleteById(followId);
		//}
		return SUCCESS;
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

	public List<Follow> getFollowList() {
		return followList;
	}

	public void setFollowList(List<Follow> followList) {
		this.followList = followList;
	}

	public int getFollowType() {
		return followType;
	}

	public void setFollowType(int followType) {
		this.followType = followType;
	}

	public String getFollowName() {
		return followName;
	}

	public void setFollowName(String followName) {
		this.followName = followName;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}
}
