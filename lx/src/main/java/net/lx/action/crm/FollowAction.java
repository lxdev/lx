package net.lx.action.crm;

import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.crm.impl.FollowBizImpl;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.university.University;
import net.lx.entity.user.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关注问题、收藏院校、课程、攻略
 * 
 * @author lxl
 * 
 */
@Namespace("/crm")
@ParentPackage("json-default")
public class FollowAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1304739961453961313L;

	private Log log = LogFactory.getLog(FollowAction.class);

	@Autowired
	private UserBiz userBiz;
	@Autowired
	private IFollowBiz followBiz;
	private List<Follow> myFollows;	//我关注的问题
	
	private int id;		//某一个follow的 id，用于删除时使用
	private Follow entity = new Follow();	//关注实体，用于添加时传参
	private String message;
	
	@Action(value = "my_ask_follow", results = {@Result(name = "input", location = "/WEB-INF/content/ask/my_follow.jsp")})
	public String my_follow() throws Exception
	{
		setUsers(userBiz.findUserById(getUser().getUserId()));
		Follow entity = new Follow();
		entity.setFollow_type(Constants.FOLLOW_ASK);
		entity.setFollow_user_id(users.getUser_id());
		myFollows = followBiz.searchByCondition(entity);
		return INPUT;
	}

	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "my_follow_remove", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String follow_remove() throws Exception
	{
		int currentUserId = getUser().getUserId();
		if(id > 0){
			Follow f = followBiz.findById(id);
			if(f != null && f.getCreated_user_id() == users.getUser_id()){
				followBiz.deleteById(id);
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 对问题 加关注： 参数为  { entity.follow_type: 1, entity.source_id:  }
	 * 收藏
	 * @return
	 * @throws Exception
	 */
	@Action(value = "my_follow_add", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String follow_add() throws Exception
	{
		int currentUserId = getUser().getUserId();
		if(entity != null && currentUserId > 0){
			if(entity.getId() != null && entity.getId() > 0){
				Follow old = followBiz.findById(entity.getId());
				if(currentUserId == old.getFollow_user_id())
					followBiz.modify(entity);
			}else{
				entity.setCreated_user_id(currentUserId);
				entity.setCreated_date(new Date());
				
				entity.setFollow_user_id(currentUserId);
				if(followBiz.searchByCondition(entity).size() <= 0)
					followBiz.createNew(entity);
			}
			message = entity.getFollow_type() == 1 ? "关注成功" : "收藏成功";
		}else
			message = entity.getFollow_type() == 1 ? "关注失败" : "收藏失败";
		return SUCCESS;
	}

	public List<Follow> getMyFollows() {
		return myFollows;
	}

	public void setMyFollows(List<Follow> myFollows) {
		this.myFollows = myFollows;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Follow getEntity() {
		return entity;
	}

	public void setEntity(Follow entity) {
		this.entity = entity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
