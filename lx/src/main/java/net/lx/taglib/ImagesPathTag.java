package net.lx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.common.Constants;

/**
 * 图片路径
 * 
 */
public class ImagesPathTag extends SimpleTagSupport {

	private String url;

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		if (url.startsWith("/")) {
			out.print(Constants.WEB_IMAGES+url);
		}else{
			out.print(Constants.WEB_IMAGES+"/"+url);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
