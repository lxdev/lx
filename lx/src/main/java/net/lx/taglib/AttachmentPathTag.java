package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.common.Constants;

/**
 * 附件路径
 * 
 */
public class AttachmentPathTag extends SimpleTagSupport {

	private String url;

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		if (url.startsWith("/")) {
			out.print(Constants.WEB_ATTACHMENT+url);
		}else{
			out.print(Constants.WEB_ATTACHMENT+"/"+url);
		}
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
