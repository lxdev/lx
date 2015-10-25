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
public class AjaxTag extends SimpleTagSupport {

	// 插件编号
	private String pluginCode;
	private String urls;// 请求路径集合
	private String successCallbackFunctions;// 成功回调函数集合
	private String errorCallbackFunctions;// 错误回调函数
	private String parameters;// 参数集合
	private String dataTypes;// 数据类型集合eg：json
	private String requestTypes;// 请求类型post ,get
	private String isOnload;// 是否自动加载 all,1,2,3.....
	private String traditional;// 可以传数组，序列化比如：{foo: ["bar", "baz"]}
	// 将被序列化成foo[]=bar&foo[]=baz，而不是序列化成以前的foo=bar&foo=baz。

	private static final String functionPrefix = "ajax";
	// 是否显示班马线
	private String isZebraCrossing = "true";

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		String[] urlStrings = null;
		String[] successCallbackFunctionStrings = null;
		String[] errorCallbackFunctionStrings = null;
		String[] parameterStrings = null;
		String[] dataTypeStrings = null;
		String[] requestTypeStrings = null;
		String[] traditionals = null;

		String[] isOnloadStrs = null;

		if (traditional != null && !traditional.equals("")) {
			traditionals = traditional.split(";");
		} else {
			// out.print("plugin urls error......");
		}

		if (urls != null && !urls.equals("")) {
			urlStrings = urls.split(";");
		} else {
			// out.print("plugin urls error......");
		}

		if (successCallbackFunctions != null
				&& !successCallbackFunctions.equals("")) {
			successCallbackFunctionStrings = successCallbackFunctions
					.split(";");
		} else {
			// out.print("plugin successCallbackFunctions error......");
		}

		if (errorCallbackFunctions != null
				&& !errorCallbackFunctions.equals("")) {
			errorCallbackFunctionStrings = errorCallbackFunctions.split(";");
		} else {
			// out.print("plugin errorCallbackFunctions error......");
		}

		if (parameters != null && !parameters.equals("")) {
			parameterStrings = parameters.split(";");
		} else {
			// out.print("plugin parameters error......");
		}

		if (dataTypes != null && !dataTypes.equals("")) {
			dataTypeStrings = dataTypes.split(";");
		} else {
			// out.print("plugin dataTypes error......");
		}

		if (requestTypes != null && !requestTypes.equals("")) {
			requestTypeStrings = requestTypes.split(";");
		} else {
			// out.print("plugin requestTypes error......");
		}

		if (isOnload != null && !isOnload.equals("")) {
			isOnloadStrs = isOnload.split(";");
		} else {
			// out.print("plugin requestTypes error......");
		}

		if (urlStrings.length != successCallbackFunctionStrings.length) {
			out.print("Does not match the number of parameters......");
		}
		out.print("<script type=\"text/javascript\">");
		String funStrings = "";
		String sDateString = "";
		for (int i = 0; i < urlStrings.length; i++) {
			sDateString = "div" + pluginCode + i;
			if (isOnload != null && !isOnload.equals("")
					&& isOnload.equals("all")) {
				funStrings += (functionPrefix + "_" + pluginCode + "_"
						+ (i + 1) + "();\n");
			} else {
				if (isOnloadStrs != null) {
					for (int j = 0; j < isOnloadStrs.length; j++) {
						if (Integer.parseInt(isOnloadStrs[j]) == (i + 1)) {
							funStrings += (functionPrefix + "_" + pluginCode
									+ "_" + (i + 1) + "();\n");
						}
					}
				}

			}
			out.print("function " + functionPrefix + "_" + pluginCode + "_"
					+ (i + 1) + "(){");
			out.print("showAjaxLoad(\"" + sDateString + "\");");
			out.print("jQuery.ajax({");
			// 参数
			if (parameterStrings != null && parameterStrings[i] != null
					&& !parameterStrings[i].equals("")
					&& !parameterStrings[i].equals("null")) {
				out.print("data:" + parameterStrings[i] + ",");
			}

			// 序列化
			if (traditionals != null && traditionals[i] != null
					&& !traditionals[i].equals("")
					&& !traditionals[i].equals("null")) {
				out.print("traditional:" + traditionals[i] + ",");
			}
			
			
			//设置为 false 将不会从浏览器缓存中加载请求信息。
			out.print("cache:false,");
			
			
			

			// 请求方式
			if (requestTypeStrings != null && requestTypeStrings[i] != null
					&& !requestTypeStrings[i].equals("")
					&& !requestTypeStrings[i].equals("null")) {
				out.print("type:\"" + requestTypeStrings[i] + "\",");
			} else {
				out.print("type:\"post\",");
			}
			// 返回的数据类型
			if (dataTypeStrings != null && dataTypeStrings[i] != null
					&& !dataTypeStrings[i].equals("")
					&& !dataTypeStrings[i].equals("null")) {
				out.print("dataType:\"" + dataTypeStrings[i] + "\",");
			} else {
				out.print("dataType:\"json\",");
			}
			// 请求url
			if (urlStrings != null) {
				out.print("url:\""
						+ (urlStrings[i].startsWith("/") ? Constants.WEB_PATH
								: (Constants.WEB_PATH + "/")) + urlStrings[i]
						+ "\",");
			}
			
			
			out.print("success:function(data){");
			
			out.print("closeAjaxLoad(\"" + sDateString + "\");");
			if (successCallbackFunctionStrings != null
					&& successCallbackFunctionStrings[i] != null
					&& !successCallbackFunctionStrings[i].equals("")
					&& !successCallbackFunctionStrings[i].equals("null")) {
				out.print(successCallbackFunctionStrings[i] + "(data);");
				
				// 显示斑马线
				if (isZebraCrossing != null && isZebraCrossing.equals("true")) {
					// 加载斑马线
					out.print("zebraCrossing();");
				}
			}
			out.print("}");
			
			
			out.print(",error:function(XMLHttpRequest, textStatus, errorThrown){");
			out.print("closeAjaxLoad(\"" + sDateString + "\");");
			
			out.print("if(XMLHttpRequest.getResponseHeader('SESSION_ERROR')!=null){");
//			out.print("if(confirm('会话失效，确认重新登录！')){");
//			out.print("parent.location.href=XMLHttpRequest.getResponseHeader('LOGIN_PATH');");
//			out.print("}");
			out.print("}");
			
			if (errorCallbackFunctionStrings != null
					&& errorCallbackFunctionStrings[i] != null
					&& !errorCallbackFunctionStrings[i].equals("")
					&& !errorCallbackFunctionStrings[i].equals("null")) {
				out.print(errorCallbackFunctionStrings[i] + "(XMLHttpRequest, textStatus, errorThrown);");

			}
			out.print("}");
			
			
			
			out.print("});");
			out.print("}");
		}
		if (isOnload != null && !isOnload.equals("")) {
			out.print("jQuery(function(){" + funStrings + "});");
		}
		out.print("</script>");
	}

	public String getPluginCode() {
		return pluginCode;
	}

	public void setPluginCode(String pluginCode) {
		this.pluginCode = pluginCode;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getSuccessCallbackFunctions() {
		return successCallbackFunctions;
	}

	public void setSuccessCallbackFunctions(String successCallbackFunctions) {
		this.successCallbackFunctions = successCallbackFunctions;
	}

	public String getErrorCallbackFunctions() {
		return errorCallbackFunctions;
	}

	public void setErrorCallbackFunctions(String errorCallbackFunctions) {
		this.errorCallbackFunctions = errorCallbackFunctions;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(String dataTypes) {
		this.dataTypes = dataTypes;
	}

	public String getRequestTypes() {
		return requestTypes;
	}

	public void setRequestTypes(String requestTypes) {
		this.requestTypes = requestTypes;
	}

	public String getIsOnload() {
		return isOnload;
	}

	public void setIsOnload(String isOnload) {
		this.isOnload = isOnload;
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}

	public String getIsZebraCrossing() {
		return isZebraCrossing;
	}

	public void setIsZebraCrossing(String isZebraCrossing) {
		this.isZebraCrossing = isZebraCrossing;
	}

}
