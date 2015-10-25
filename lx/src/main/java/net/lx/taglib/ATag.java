package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.common.Constants;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.properties.Config;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 超链接
 * 
 */
public class ATag extends SimpleTagSupport {
	private static final ActionSupport actionSupport = new ActionSupport();
	private String icon = "default";// 图表
	private String target = "main";
	private String href = "#";
	private String onclick = "";
	private String il8nName;
	private String text;// 文本

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		if (!href.equals("#")) {
			if (href.startsWith("/")) {
				href=Constants.WEB_PATH + href;
			} else {
				href=Constants.WEB_PATH + "/" + href;
			}
		}
		out.print("<img border=\"0px\" width=\"15\" height=\"15\" src=\""
				+ Constants.WEB_IMAGES
				+ Config.getProperty("html.body.head.images.path").replaceAll(
						Constants.PLACEHOLDER, icon) + "\">");
		out.print("<a target='" + target + "' href='" + href + "' onclick='"
				+ onclick + "'>" + this.getText(text) + "</a>");
	}

	private String getText(String str) {
		return ResourcesTool.getText(il8nName, str, actionSupport.getLocale());
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getIl8nName() {
		return il8nName;
	}

	public void setIl8nName(String il8nName) {
		this.il8nName = il8nName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
