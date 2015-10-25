package net.lx.action.timing;

import net.lx.action.BaseAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("json-default")
public class JsonIndexAction extends BaseAction {
	private final Log log = LogFactory.getLog(JsonIndexAction.class);
	
	@Action(value = "push_server", results = { @Result(name = "success", type = "json", params = {"contentType", "text/json" }) })
	public String pushServer() throws Exception {
		log.info(super.getUser().getFullName()+"定时请求服务器....");
		return SUCCESS;
	}
}
