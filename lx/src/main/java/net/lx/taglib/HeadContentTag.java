package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.opensymphony.xwork2.ActionSupport;

import net.lx.common.Constants;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.properties.Config;

/**
 * 头部
 * 
 */
public class HeadContentTag extends BodyTagSupport {
	private static final ActionSupport actionSupport = new ActionSupport();
	private String title;
	private String il8nName;

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(
					Config.getProperty("html.body.head.start").replaceAll(Constants.PLACEHOLDER,this.getText(title)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		if (bodyContent != null) {
			try {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
//				pageContext.getOut().write("<img border=\"0px\" width=\"15\" height=\"15\" src=\""
//						+ Constants.WEB_IMAGES
//						+ Config.getProperty("html.body.head.images.path").replaceAll(
//								Constants.PLACEHOLDER, "up") + "\">");
//				pageContext.getOut().write("<a id='stiwch_a' class='turnOff' href='#'>" + this.getText("fullscreen") + "</a>");
				pageContext.getOut().write(
						Config.getProperty("html.body.head.end"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}

	private String getText(String str) {
		return ResourcesTool.getText(il8nName, str, actionSupport.getLocale());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIl8nName() {
		return il8nName;
	}

	public void setIl8nName(String il8nName) {
		this.il8nName = il8nName;
	}

}
