package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.action.BaseAction;

/**
 * 资源文件操作标签
 * 
 */
public class ResourcesTag extends SimpleTagSupport {

	// 键
	private String key;
	// 资源文件名称
	private String il8nName;

	private BaseAction base = new BaseAction();

	public ResourcesTag() {
		
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		base.setIl8nName(il8nName);
		out.print(base.getText(key));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIl8nName() {
		return il8nName;
	}

	public void setIl8nName(String il8nName) {
		this.il8nName = il8nName;
	}

}
