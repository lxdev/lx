package net.lx.action.template;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.entity.user.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模板Action
 * 
 */
@Namespace("/template")
@Results( {
		@Result(name = "default_index", location = "/WEB-INF/content/template/default/index.jsp"),
		@Result(name = "default_head", location = "/WEB-INF/content/template/default/head.jsp"),
		@Result(name = "default_left", location = "/WEB-INF/content/template/default/left.jsp"),
		@Result(name = "default_foot", location = "/WEB-INF/content/template/default/foot.jsp"),
		@Result(name = "default_switch", location = "/WEB-INF/content/template/default/switch.jsp"),
		@Result(name = "default_checkcodeimg", location = "/WEB-INF/content/template/default/checkcodeimg.jsp"),
		@Result(name = "default_line", location = "/WEB-INF/content/template/default/line.jsp") })
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 6393611697621750302L;
	
	@Autowired
	private UserBiz userBiz;
	
	private final String INDEX = "index";
	private final String HEAD = "head";
	private final String LEFT = "left";
	private final String FOOT = "foot";
	private final String LINE = "line";
	private final String SWITCH = "switch";

	private String template = "default_";
	
	boolean flag=false;

	@Action(value = "index")
	public String index() 
	{
		try
		{
			User user=userBiz.findUserById(getUser().getUserId());
			Calendar a=Calendar.getInstance();
			a.setTime(user.getUpdate_password_time());
			a.add(Calendar.DAY_OF_MONTH,90);		
			Date s=a.getTime();
			Date n=new Date();
			if(s.before(n))
			{
				flag=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return template + INDEX;
	}

	@Action(value = "head")
	public String head() {
		return template + HEAD;
	}

	@Action(value = "left")
	public String left() {
		return template + LEFT;
	}

	@Action(value = "foot")
	public String foot() {
		return template + FOOT;
	}

	@Action(value = "line")
	public String line() {
		return template + LINE;
	}

	@Action(value = "switch")
	public String switchT() {
		return template + SWITCH;
	}

	@Action(value = "vcode")
	public String vCode() {
		return template + "checkcodeimg";
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean getFlag() {
		return flag;
	}
}
