package net.lx.action.template;

import net.lx.action.BaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * 左侧菜单
 * @author Jack
 *
 */
public class LeftAction extends BaseAction 
{
	private static final long serialVersionUID = -5988769831463060931L;
	
	@Action(results = { @Result(name = "toleftjsp", location = "/WEB-INF/content/template/default/left.jsp")
	})
	public String execute()
	{
		return "toleftjsp";
	}
}
