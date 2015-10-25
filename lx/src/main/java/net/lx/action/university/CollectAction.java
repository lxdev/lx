package net.lx.action.university;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideSpecialty;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Json数据返回
 * @author Jack
 *
 */ 
@Namespace("/university")
@ParentPackage("json-default")
public class CollectAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7756182353587401909L;
	@Autowired
	private IFollowBiz followBiz;
	private Follow follow = new Follow();
	private Boolean result = false;
	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="program_collect", 
			results={
				@Result(name = "success", type = "json", params={"contentType",  "text/json"} )
	})
	public String execute()throws Exception
	{
		if(follow.getSource_id() > 0){
			follow.setCreated_date(new Date());
			follow.setCreated_user_id(users.getUser_id());
			int followId = followBiz.createNew(follow);
			if(followId > 0)
				result = true;
			else
				result = false;
		}
		return SUCCESS;
	}

	public Follow getFollow() {
		return follow;
	}

	public void setFollow(Follow follow) {
		this.follow = follow;
	}	
}
