package net.lx.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.common.Constants;
import net.lx.common.properties.Config;

/**
 * js插件
 * 
 */
public class JCPluginsTag extends SimpleTagSupport {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		try {
			// 判断是否存在该插件
			String strPlugins = Config.getProperty(name);
			if (strPlugins != null && !strPlugins.equals("")) {
				String[] plugins = strPlugins.split(";");
				for (String plugin : plugins) {

					if (plugin.endsWith("js")) {
						out.print(Config.getProperty("javascript.templete")
								.replaceAll(Constants.PLACEHOLDER,
										Constants.WEB_PLUGINS + plugin));
					} else if (plugin.endsWith("css")) {
						out.print(Config.getProperty("styles.templete")
								.replaceAll(Constants.PLACEHOLDER,
										Constants.WEB_PLUGINS + plugin));
					}

				}
			} else {
				out.print("error");
			}

		} catch (Exception e) {
			out.print("error");
			e.printStackTrace();
		}

	}
}
