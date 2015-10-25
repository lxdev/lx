package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.lx.common.properties.Config;

public class BodyContentTag extends BodyTagSupport {
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(Config.getProperty("html.body.start"));
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
				pageContext.getOut().write(Config.getProperty("html.body.end"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}
}
