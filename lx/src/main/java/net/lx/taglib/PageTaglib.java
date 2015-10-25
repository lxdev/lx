package net.lx.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.lx.common.Constants;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.properties.Config;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 分页标签类
 * 
 */
public class PageTaglib extends SimpleTagSupport {
	private static final ActionSupport actionSupport = new ActionSupport();
	// 插件编号
	private String pluginCode;
	// 列字符串
	private String columnsStr;
	// 查询数量的方法
	private String searchCountActionpath;
	// 查询集合的方法
	private String searchListActionpath;
	// 表格样式
	private String tableCss;
	// 每页行数
	private String pageSize;
	// 是否自定义行数
	private String isPageSize = "false";// 默认不启用

	// 总行数Action属性名称默认为result.recordCount
	private String recordCountAttribute;
	// 每页显示条数Action属性名称默认为result.pageSize
	private String pageSizeAttribute;
	// 是否分页属性
	private String pageAttribute;
	// 集合action属性名称默认：result.list
	private String listAttribute;
	// 是否显示序号
	private String isNumber="true";
	// 是否显示checkbox
	private String isChecked;
	// checkbox 值
	private String checkboxValue;
	// pageIndex Action属性名称默认为result.currentPageIndex
	private String currentPageIndexAttribute;
	// 查询参数
	private String params;
	// 删除
	private String delete;
	// 更新
	private String update;
	// 详情
	private String view;

	// 其他操作
	private String ontherOperating;
	// 其他操作宽度
	private String ontherOperatingWidth;
	// 自定义列得值
	private String customColumnValue;
	// 国际化资源文件名称
	private String il8nName;

	// 截取字符串默认值
	private String subStringLength;

	// 设置列宽列宽
	private String columnsWidth;

	// 操作宽度
	private String operatingWidth;
	// 是否分页
	private String isPage;

	// 存放列的宽度
	private Map<Integer, String> columnsMap = null;

	// 查询完count的回调函数
	private String countCallback;
	// 查询完list的回调函数
	private String listCallback;
	// 是否打开页面加载
	private String isonLoad = "true";
	// 是否加载时隐藏表格 true加载隐藏
	private String style = ""; // 未实现
	// 合并单元格
	private String mergeCells;
	// 标题栏
	private String titleBar;
	// 查询的form表单ID
	private String searchFormId;

	// 是否同包
	private String isPackage;

	private String isZebraCrossing = "true";// 斑马线
	
	private String customToolbarID;//自定义工具类spanID

	// 是否排序
	private String isOrder = "true";

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();

		// 动态生成查询数量方法名
		String functionCountName = "searchCount" + System.currentTimeMillis()
				+ pluginCode;
		// 动态生成查询列表方法名
		String functionListName = "searchList" + System.currentTimeMillis()
				+ pluginCode;
		// 分页层ID
		String pageDivId = "pager" + pluginCode;
		// table ID
		String tableId = "table" + pluginCode;
		String[] columns = columnsStr.split(";");

		// 索引集合
		String[] cellIndexs = null;
		if (mergeCells != null && !mergeCells.equals("")) {
			cellIndexs = mergeCells.split(";");
		}
		// 分页

		// 是否分页，默认分页
		if (isPage == null || isPage.equals("")) {
			isPage = "true";
		}

		if (isPage.equals("true")) {
			if (isPageSize != null && isPageSize.equals("true")) {
				// 分页条数
				if (pageSize == null || pageSize.equals("")) {
					pageSize = Config.getProperty("pagesize");
				}
			}else{
				pageSize = Config.getProperty("pagesize");
			}
			// 总行数Action属性名称默认为pageCount
			if (recordCountAttribute == null || recordCountAttribute.equals("")) {
				recordCountAttribute = "result.recordCount";
			}
			// 每页显示条数Action属性名称默认为pageSize
			if (pageSizeAttribute == null || pageSizeAttribute.equals("")) {
				pageSizeAttribute = "result.pageSize";
			}
			// 实际页码
			if (currentPageIndexAttribute == null
					|| currentPageIndexAttribute.equals("")) {
				currentPageIndexAttribute = "result.currentPageIndex";
			}
		}

		// 是否分页属性
		if (pageAttribute == null || pageAttribute.equals("")) {
			pageAttribute = "result.page";
		}

		// 集合action属性名称
		if (listAttribute == null || listAttribute.equals("")) {
			listAttribute = "result.list";
		}

		// checkbox的值
		if (checkboxValue == null || checkboxValue.equals("")) {
			checkboxValue = "id";
		}
		// 表格样式
		if (tableCss == null || tableCss.equals("")) {
			tableCss = "gv_table_2";
		}
		// 参数
		if (params == null || params.equals("")) {
			params = "\"random\": number";
		} else {
			params += ",\"random\": number";
		}

		// 是否显示标题栏
		if (titleBar != null && !titleBar.equals("")) {
			out.println("<table class=\"gv_table_2\">");
			out.println("<tr>");
			out.println("<th style=\"width:20px;\"><img src=\""
					+ Constants.WEB_IMAGES
					+ "/images/title_menu/title_left.gif\" /></th>");
			out.println("<th style=\"text-align:left; font-weight:bold;\">"
					+ this.getText(titleBar) + "</th>");
			out.println("</tr>");
			out.println("</table>");
		}
		// 是否同包
		if (isPackage != null && isPackage.equals("false")) {
			if (isPage.equals("true")) {
				searchCountActionpath = (searchCountActionpath.startsWith("/") ? Constants.WEB_PATH
						: (Constants.WEB_PATH + "/"))
						+ searchCountActionpath;
				searchListActionpath = (searchListActionpath.startsWith("/") ? Constants.WEB_PATH
						: (Constants.WEB_PATH + "/"))
						+ searchListActionpath;
			} else {
				searchListActionpath = (searchListActionpath.startsWith("/") ? Constants.WEB_PATH
						: (Constants.WEB_PATH + "/"))
						+ searchListActionpath;
			}

		}

		// 1.生成表格，以及page层
		out.println("<table id=\"" + tableId + "\" class=\"" + tableCss
				+ "\" border=\"0\" style=\"" + style
				+ "\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		out.println("<thead>");
		out.println("<tr>");
		// 显示序号
		if (isNumber != null && !isNumber.equals("") && isNumber.equals("true")) {
			out.println("<th width=\"40px\" align=\"center\">"
					+ getText("public.number") + "</th>");
		}
		// 显示checkBox
		String checkboxClass = "class" + System.currentTimeMillis()
				+ pluginCode;
		if (isChecked != null && !isChecked.equals("")
				&& isChecked.equals("true")) {
			out.println("<th width=\"20px\" align=\"center\"><input id=\"checkbox"
					+ pluginCode
					+ "\" type='checkbox' onclick=\"allChecked"
					+ pluginCode + "(this)\"/></th>");
		}
		// 操作
		if (delete != null || update != null || view != null) {
			out.println("<th width=\""
					+ ((operatingWidth != null && !operatingWidth.equals("")) ? operatingWidth
							: "70px") + "\" align=\"center\">"
					+ getText("public.operating") + "</th>");
		}

		// for (String column : columns) {
		String cw = null;
		for (int i = 0; i < columns.length; i++) {
			cw = getColumnsWidth(i);
			if (cw != null) {
				out.println("<th width=\""
						+ cw
						+ "\" class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i])
						+ pluginCode
						+ "\" align=\"center\">"
						+ getText(columns[i])
						+ "<img class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i])
						+ pluginCode
						+ "asc"
						+ "\" style=\"display:none;\" src=\""
						+ Constants.WEB_IMAGES
						+ "/images/title_menu/icon_order_asc.gif"
						+ "\" /><img class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i]) + pluginCode + "desc"
						+ "\" style=\"display:none;\" src=\""
						+ Constants.WEB_IMAGES
						+ "/images/title_menu/icon_order_desc.gif"
						+ "\" /></th>");
			} else {
				out.println("<th align=\"center\" class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i])
						+ pluginCode
						+ "\">"
						+ getText(columns[i])
						+ "<img class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i])
						+ pluginCode
						+ "asc"
						+ "\" style=\"display:none;\" src=\""
						+ Constants.WEB_IMAGES
						+ "/images/title_menu/icon_order_asc.gif"
						+ "\" /><img class=\""
						+ (columns[i].startsWith("#") ? columns[i].substring(1)
								: columns[i]) + pluginCode + "desc"
						+ "\" style=\"display:none;\" src=\""
						+ Constants.WEB_IMAGES
						+ "/images/title_menu/icon_order_desc.gif"
						+ "\" /></th>");
			}
		}

		// 其他操作
		if (ontherOperating != null) {
			out.println("<th width=\"" + ontherOperatingWidth
					+ "\" align=\"center\">" + getText("public.operating")
					+ "</th>");
		}

		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody></tbody>");
		out.println("</table>");
		// 分页
		if (isPage.equals("true")) {
			// 页码
			out.println("<div class=\"pager2\" id=\"" + pageDivId + "\"></div>");
		}
		// 加载中需要显示的层
		// 增加绝对定位 (为了兼容ie6) edited by dongminghao
		out.print("<div class=\"loading\" "
				+ " id=\"loading" + pluginCode
				+ "\"><center><img src='" + Constants.WEB_PATH
				+ "/plugin/page/images/loading.gif'/></center></div> ");
		// 2.生成动态的javascript

		out.println("<script type=\"text/javascript\">");

		//out.println("var pluginCode='" + pluginCode + "';");

		// 是否排序
		if (isOrder.equals("true")) {
			// 全局变量
			// 列的数组
			out.println("var columnsStrArray" + pluginCode + "=new Array()");
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") == -1) {
					if (columns[i].startsWith("#")) {
						out.println("columnsStrArray" + pluginCode + "[" + i
								+ "] = \"" + columns[i].substring(1) + "\";");
					} else {
						out.println("columnsStrArray" + pluginCode + "[" + i
								+ "] = \"" + columns[i] + "\";");
					}
				}
			}

			// 排序字段
			out.println("var order" + pluginCode + "=null;");
			out.println("var sort" + pluginCode + "=null;");

			// 排序方法
			out.println("function columnSort" + pluginCode + "(o){");

			out.println("if(order" + pluginCode + "==o){");

			out.println("if(sort" + pluginCode + "=='asc'){");
			out.println("sort" + pluginCode + "='desc';");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'asc').css({'display':'none'});");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'desc').css({'display':''});");
			out.println("}else{");
			out.println("sort" + pluginCode + "='asc';");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'asc').css({'display':''});");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'desc').css({'display':'none'});");
			out.println("}");
			out.println("}else{");
			out.println("order" + pluginCode + "=o;");
			out.println("sort" + pluginCode + "='asc';");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'asc').css({'display':''});");
			out.println("$('.'+o+'" + pluginCode
					+ "'+'desc').css({'display':'none'});");
			out.println("}");

			out.println("for (i=0;i<columnsStrArray" + pluginCode
					+ ".length;i++){");
			out.println("var cs=$('.'+columnsStrArray" + pluginCode + "[i]+'"
					+ pluginCode + "');");
			out.println("if(cs.attr('class')!=(o+'" + pluginCode + "')){");
			out.println("$('.'+cs.attr('class')+'asc').css({'display':'none'});");
			out.println("$('.'+cs.attr('class')+'desc').css({'display':'none'});");
			out.println("}");
			out.println("}");

			// 刷新
			out.println("search" + pluginCode + "();");
			out.println("}");

		}

		// 是否合并单元格
		if (mergeCells != null && !mergeCells.equals("")) {
			cellIndexs = mergeCells.split(";");
			out.println("function mergeCells" + pluginCode + "(){");
			out.println("if(!checktable('" + tableId + "')){");
			out.println("return false;");
			out.println("}else{");
			for (String s : cellIndexs) {
				out.println("coltogather($('#" + tableId + " tr')," + s + ");");
			}
			out.println("}");

			out.println("}");
		}

		// 生成checkbox选中方法
		if (isChecked != null && !isChecked.equals("")
				&& isChecked.equals("true")) {
			out.println("function allChecked" + pluginCode + "(ch){");
			out.println("if(ch.checked==true){");
			out.println("for(var i=0;i<$(\"." + checkboxClass
					+ "\").length;i++){");
			out.println("");

			out.println("$(\"." + checkboxClass + "\")[i].checked=true;");
			out.println("}");
			out.println("}else{");
			out.println("for(var i=0;i<$(\"." + checkboxClass
					+ "\").length;i++){");
			out.println("$(\"." + checkboxClass + "\")[i].checked=false;");
			out.println("}");
			out.println("}}");

			out.println("function getCheckedValues" + pluginCode + "(){");
			out.println("var str='';");
			out.println("$(\"." + checkboxClass + "\").each(function(){");
			out.println(" if(this.checked==true&&this.style.display!='none'){");
			out.println(" str+=$(this).val()+',';");
			out.println(" }");
			out.println(" })");
			out.println(" if(str!=null&&str!=''){");

			out.println("str= str.substring(0,str.lastIndexOf(\",\"))");

			out.println(" }");
			out.println("return str;");
			out.println("}");
		}

		// 把所有null值都替换为''
		out.println("String.prototype.replaceAll = function(s1,s2) { return this.replace(new RegExp(s1,\"gm\"),s2);}");

		// 分页
		if (isPage.equals("true")) {
			// 重新加载
			out.println("function search" + pluginCode + "(){");
			out.println("$.cookie('cookie_page_" + pluginCode + "',null);");
			// 显示分页层
			if (isonLoad.equals("false")) {
				out.println("$(\"#" + pageDivId
						+ "\").css({\"display\":\"\"});");
			}
			out.println(functionCountName + "();");
			out.println("}");

		} else {
			// 重新加载
			out.println("function refresh" + pluginCode + "(){");

			out.println("$.cookie('cookie_page_" + pluginCode + "',null);");
			out.println(functionListName + "();");
			out.println("}");
		}
		// 分页
		if (isPage.equals("true")) {
			// 刷新
			out.println("function refresh" + pluginCode + "(){");
			// 显示分页层
			if (isonLoad.equals("false")) {
				out.println("$(\"#" + pageDivId
						+ "\").css({\"display\":\"\"});");
			}
			out.println(functionCountName + "();");
			out.println("}");

		} else {
			// 刷新
			out.println("function search" + pluginCode + "(){");
			out.println(functionListName + "();");
			out.println("}");
		}

		// 分页
		if (isPage.equals("true")) {
			// 打开页面加载
			if (isonLoad.equals("true")) {
				// 查询数量,以及删除cookie
				out.println("$().ready(function(){$.cookie('cookie_page_"
						+ pluginCode + "',null);" + functionCountName
						+ "();});");
			} else {
				// 加载,以及萨hn出cookie
				out.println("$().ready(function(){$.cookie('cookie_page_"
						+ pluginCode + "',null);$(\"#loading" + pluginCode
						+ "\").css({\"display\":\"none\"});$(\"#" + pageDivId
						+ "\").css({\"display\":\"none\"});});");
				out.println("$('#" + tableId + " > tbody').empty();");
				out.println("$('#" + tableId
						+ " > tbody').html('<tr><td colspan=\""
						+ (columns.length + 4) + "\" align=\"center\">"
						+ getText("public.no.data") + "</td></tr>');");

			}
		} else {
			// 打开页面加载
			if (isonLoad.equals("true")) {
				// 查询集合
				out.println("$().ready(function(){$(\"#loading" + pluginCode
						+ "\").css({\"display\":\"\"});" + functionListName
						+ "();});");
			} else {
				// 加载
				out.println("$().ready(function(){$(\"#loading" + pluginCode
						+ "\").css({\"display\":\"none\"});$(\"#" + pageDivId
						+ "\").css({\"display\":\"none\"});});");
				out.println("$('#" + tableId + " > tbody').empty();");
				out.println("$('#" + tableId
						+ " > tbody').html('<tr><td colspan=\""
						+ (columns.length + 4) + "\" align=\"center\">"
						+ getText("public.no.data") + "</td></tr>');");
			}
		}
		// 分页
		if (isPage.equals("true")) {
			//全局pageSize变量
			out.println("var psize"+pluginCode+" = " + pageSize + ";");

			// 创建方法(查询数量)
			out.println("function " + functionCountName + "(){");
			// 加载

			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"\"});");
			// out
			// .println("$(\"#"
			// + "loading"
			// + pluginCode
			// + "\").ajaxStart(function(){$(this).html(\"<center><img src='"
			// + Constants.WEB_PATH
			// + "/plugin/page/images/loading.gif'/></center>\");});");

			out.println("var pagerObj=$(\"#" + pageDivId + "\");");
			//out.println("var psize = " + pageSize + ";");
			out.println("var pcount =\"\";");
			out.println("var parms = getParams" + pluginCode + "(psize"+pluginCode+",1);");
			out.println("$.ajax({data:parms,type:'post',dataType:'json',url:'"
					+ searchCountActionpath + "',");
			out.println("success:function(data){");
			out.println("pcount = data." + recordCountAttribute + ";");
			// 根据总页数和当前页码判断是否超出了最大页码  edited by dongminghao
			out.println("var pageCount = 0;");
			out.println("if(pcount>0 && psize"+pluginCode+">0){");
			out.println("pageCount = (pcount / psize"+pluginCode+" <= 0) ? 1 : (parseInt(pcount / psize"+pluginCode+") + ((pcount % psize"+pluginCode+" > 0) ? 1 : 0));");
			// 超出页码则返回当前最大页码  edited by dongminghao
			out.println("if($.cookie('cookie_page_" + pluginCode + "')!=null && pageCount>0 && $.cookie('cookie_page_" + pluginCode + "')>pageCount){");
			out.println("$.cookie('cookie_page_" + pluginCode + "',pageCount);");
			out.println("}");
			out.println("}");
			// 回调函数
			if (countCallback != null && !countCallback.equals("")) {
				out.println(countCallback + pluginCode + "(data);");
			}

			out.println("if(pcount==0){");
			// 隐藏加载
			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"none\"});");
			out.println("$('#" + tableId + " > tbody').empty();");
			out.println("$('#" + tableId
					+ " > tbody').html('<tr><td colspan=\""
					+ (columns.length + 4) + "\" align=\"center\">"
					+ getText("public.no.data") + "</td></tr>');");
			out.println("pagerObj.pagefoot({pagesize: psize"+pluginCode+",count: 0,css: \"pager2\",paging: null,pluginCode:\""+pluginCode+"\"});");
			out.println("}else{");

			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"\"});");

			out.println("if($.cookie('cookie_page_" + pluginCode + "')==null){");
			out.println(functionListName + "(psize"+pluginCode+", 1,true);");
			out.println("}else{");
			// 获取cookie的页码数
			out.println(functionListName + "(psize"+pluginCode+", $.cookie('cookie_page_"
					+ pluginCode + "'),true);");
			out.println("}");

			out.println("pagerObj.pagefoot({customToolbarID:'"+customToolbarID+"',pagesize: psize"+pluginCode+",count: parseInt(pcount),css: \"pager2\",pluginCode:\""+pluginCode+"\",paging: function(page) {");
			// 显示加载
			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"\"});");
			// out
			// .println("$(\"#"
			// + "loading"
			// + pluginCode
			// + "\").ajaxStart(function(){$(this).html(\"<center><img src='"
			// + Constants.WEB_PATH
			// + "/plugin/page/images/loading.gif'/></center>\");});");

			// COOKIE纪录页码数
			out.println("$.cookie('cookie_page_" + pluginCode + "',page);");

			out.println(functionListName + "(psize"+pluginCode+", page);}});");

			out.println("}}");

			out.println(",error:function(XMLHttpRequest, textStatus, errorThrown){");

			// 关闭加载
			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"none\"});");
			
			out.print("if(XMLHttpRequest.getResponseHeader('SESSION_ERROR')!=null){");
//			out.print("if(confirm('会话失效，确认重新登录！')){");
//			out.print("parent.location.href=XMLHttpRequest.getResponseHeader('LOGIN_PATH');");
//			out.print("}");
			out.print("return;");
			out.print("}");
			
			
			out.println("if(textStatus==0){alert('"
					+ this.getText("msg.error.server.not.start")
					+ "');return;}");

//			out.println("alert('" + this.getText("system.error")
//					+ "'+'error code:'+textStatus);");

			out.println("}");

			out.println("});");
			out.println("}");
		}
		// 分页
		if (isPage.equals("true")) {
			// 参数
			out.println("function getParams" + pluginCode + "(psize"+pluginCode+", pindex) {");
			out.println("var number = Math.random();");
			out.println("var params = {\"" + pageAttribute + "\":true,\""
					+ pageSizeAttribute + "\": parseInt(psize"+pluginCode+"),\""
					+ currentPageIndexAttribute + "\": parseInt(pindex),"
					+ params + "};");

			// 表单ID
			if (searchFormId != null && !searchFormId.equals("")) {
				out.println("params=$('#" + searchFormId
						+ "').serializeObjectParame(params);");
			}

			// 是否排序
			if (isOrder.equals("true")) {

				out.println("if(order" + pluginCode + "!=null){");
				out.println("params['result.order']=order" + pluginCode);
				out.println("if(sort" + pluginCode + "!=null){");
				out.println("params['result.sort']=sort" + pluginCode);
				out.println("}");
				out.println("}");
			}

			out.println("return params;");
			out.println("}");
		} else {
			// 参数
			out.println("function getParams" + pluginCode + "(psize"+pluginCode+", pindex) {");
			out.println("var number = Math.random();");
			out.println("var params = {\"" + pageAttribute + "\":false,"
					+ params + "};");

			// 表单ID
			if (searchFormId != null && !searchFormId.equals("")) {
				out.println("params=$('#" + searchFormId
						+ "').serializeObjectParame(params);");
			}
			// 是否排序
			if (isOrder.equals("true")) {

				out.println("if(order" + pluginCode + "!=null){");
				out.println("params['result.order']=order" + pluginCode);
				out.println("if(sort" + pluginCode + "!=null){");
				out.println("params['result.sort']=sort" + pluginCode);
				out.println("}");
				out.println("}");
			}
			out.println("return params;");
			out.println("}");
		}

		// 分页
		if (isPage.equals("true")) {
			// 查询列表
			out.println("function " + functionListName + "(psize"+pluginCode+", pindex){");
			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"\"});");
			out.println("var params = getParams" + pluginCode
					+ "(psize"+pluginCode+", pindex);");

		} else {
			// 查询列表
			out.println("function " + functionListName + "(){");
			out.println("$(\"#loading" + pluginCode
					+ "\").css({\"display\":\"\"});");
			out.println("var params = getParams" + pluginCode + "();");
		}

		out.println("$.ajax({data:params,type:'post',dataType:'json',url:'"
				+ searchListActionpath + "',");

		out.println("success:function(data){");

		// 隐藏加载
		out.println("$(\"#loading" + pluginCode
				+ "\").css({\"display\":\"none\"});");
		// 取消全选
		out.println("$('#checkbox" + pluginCode + "').attr('checked',false);");

		// 集合数据是否为空
		out.println("if(data." + listAttribute + "==null||data."
				+ listAttribute + ".length==0){");
		out.println("$('#" + tableId + " > tbody').empty();");
		out.println("$('#" + tableId + " > tbody').html('<tr><td colspan=\""
				+ (columns.length + 4) + "\" align=\"center\">"
				+ getText("public.no.data") + "</td></tr>');");
		out.println("return false;");
		out.println("}");
		out.println("var lists = '';");

		out.println("$.each(data." + listAttribute + ", function(index){");

		out.println("lists+='<tr>';");

		// 显示序号
		if (isNumber != null && !isNumber.equals("") && isNumber.equals("true")) {
			out.println("lists+='<td align=\"center\">'+(index+1)+'</td>';");
		}
		// 显示checkBox
		if (isChecked != null && !isChecked.equals("")
				&& isChecked.equals("true")) {
			out.println("lists+='<td align=\"center\"><input id=\"'+this."
					+ checkboxValue + "+'" + "_" + pluginCode + "_checkbox"
					+ "'+'\" type=\"checkbox\" " + "name=\"checkbox"
					+ pluginCode + "\"" + " class=\"" + checkboxClass
					+ "\" value=\"'+this." + checkboxValue + "+'\" /></td>';");

		}
		// 显示操作
		String operatingStr = "";

		if (view != null) {
			String[] views = view.split(",");
			if ("http".equals(views[0])) {
				operatingStr += "<a id=\"'+this."
						+ views[3]
						+ "+'"
						+ "_"
						+ pluginCode
						+ "_view"
						+ "'+'\" target=\""
						+ views[4]
						+ "\" href=\""
						+ (views[1].startsWith("/") ? Constants.WEB_PATH
								: (Constants.WEB_PATH + "/"))
						+ views[1]
						+ "?"
						+ views[2]
						+ "="
						+ "'+this."
						+ views[3]
						+ "+'"
						+ "\"><img class=\"img_icon\" title=\""
						+ getText("public.view")
						+ "\" src=\""
						+ Constants.WEB_PATH
						+ "/plugin/base_css/images/operating/icon_view.gif\"></a>";
			} else if ("json".equals(views[0])) {
				operatingStr += "<a href=\"#\" id=\"'+this."
						+ views[2]
						+ "+'"
						+ "_"
						+ pluginCode
						+ "_view"
						+ "'+'\" onclick=\""
						+ views[1]
						+ "('+this."
						+ views[2]
						+ "+')\"><img class=\"img_icon\" title=\""
						+ getText("public.view")
						+ "\" src=\""
						+ Constants.WEB_PATH
						+ "/plugin/base_css/images/operating/icon_view.gif\"></a>";
			}
		}
		if (update != null) {
			String[] uodates = update.split(",");
			if ("http".equals(uodates[0])) {
				operatingStr += "<a id=\"'+this."
						+ uodates[3]
						+ "+'"
						+ "_"
						+ pluginCode
						+ "_update"
						+ "'+'\" target=\""
						+ uodates[4]
						+ "\" href=\""
						+ (uodates[1].startsWith("/") ? Constants.WEB_PATH
								: (Constants.WEB_PATH + "/"))
						+ uodates[1]
						+ "?"
						+ uodates[2]
						+ "="
						+ "'+this."
						+ uodates[3]
						+ "+'"
						+ "\"><img class=\"img_icon\" title=\""
						+ getText("public.edit")
						+ "\" src=\""
						+ Constants.WEB_PATH
						+ "/plugin/base_css/images/operating/icon_edit.gif\"></a>";
			} else if ("json".equals(uodates[0])) {
				operatingStr += "<a href=\"#\" id=\"'+this."
						+ uodates[2]
						+ "+'"
						+ "_"
						+ pluginCode
						+ "_update"
						+ "'+'\" onclick=\""
						+ uodates[1]
						+ "('+this."
						+ uodates[2]
						+ "+')\"><img class=\"img_icon\" title=\""
						+ getText("public.edit")
						+ "\" src=\""
						+ Constants.WEB_PATH
						+ "/plugin/base_css/images/operating/icon_edit.gif\"></a>";
			}
		}
		if (delete != null) {
			String[] deletes = delete.split(",");
			if ("json".equals(deletes[0])) {
				operatingStr += "<a href=\"#\" id=\"'+this."
						+ deletes[2]
						+ "+'"
						+ "_"
						+ pluginCode
						+ "_delete"
						+ "'+'\" onclick=\""
						+ deletes[1]
						+ "('+this."
						+ deletes[2]
						+ "+')\"><img class=\"img_icon\" title=\""
						+ getText("public.delete")
						+ "\" src=\""
						+ Constants.WEB_PATH
						+ "/plugin/base_css/images/operating/icon_del.gif\"></a>";
			}
		}
		if (operatingStr != null && !operatingStr.equals("")) {
			out.println("lists+='<td align=\"left\" style=\"padding-left:5px;\">"
					+ operatingStr + "</td>';");
		}

		// 显示列值

		for (int i = 0; i < columns.length; i++) {
			if (columns[i].indexOf("#") != -1) {
				out.println("lists+='<td align=\"center\">'+" + createCustom(i)
						+ "+'</td>';");
			} else {
				if (!createCustom(i).equals("''")) {
					out.println("lists+='<td align=\"center\">'+"
							+ createCustom(i) + "+'</td>';");
				} else {
					// out.println("lists+='<td align=\"center\">'+(this."
					// + columns[i] + "==null?this." + columns[i]
					// + ":(this." + columns[i]
					// + "+'').toSubString())+'</td>';");
					if (subStringLength == null || subStringLength.equals("")) {
						out.println("lists+='<td align=\"center\"><div class=\"divTips\" title='+this."
								+ columns[i]
								+ "+'>'+(this."
								+ columns[i]
								+ "==null?'--'"
								+ ":(this."
								+ columns[i]
								+ "+'').toSubString("
								+ Config.getProperty("substring")
								+ "))+'</div></td>';");
					} else if (subStringLength.equals("-1")) {
						out.println("lists+='<td align=\"center\"><div class=\"divTips\" title='+this."
								+ columns[i]
								+ "+'>'+(this."
								+ columns[i]
								+ "==null?'--'"
								+ ":(this."
								+ columns[i]
								+ "+''))+'</div></td>';");
					} else {
						out.println("lists+='<td align=\"center\"><div class=\"divTips\" title='+this."
								+ columns[i]
								+ "+'>'+(this."
								+ columns[i]
								+ "==null?'--'"
								+ ":(this."
								+ columns[i]
								+ "+'').toSubString("
								+ subStringLength
								+ "))+'</div></td>';");
					}

				}

			}
		}
		// 其他操作
		if (ontherOperating != null) {
			String ontherOperatingStr = "";

			String[] ontherOperatings = ontherOperating.split(";");
			for (int i = 0; i < ontherOperatings.length; i++) {
				String str = ontherOperatings[i];
				String[] strs = str.split(",");
				if ("http".equals(strs[0])) {
					ontherOperatingStr += "&nbsp;<a target=\"" + strs[4]
							+ "\" href=\"" + strs[1] + "?" + strs[2] + "="
							+ "'+this." + strs[3] + "+'" + "\">"
							+ getText(strs[5]) + "</a>";
				} else if ("json".equals(strs[0])) {
					ontherOperatingStr += "&nbsp;<a href=\"#\" onclick=\""
							+ strs[1] + "('+this." + strs[2] + "+')\">"
							+ getText(strs[3]) + "</a>";
				}
			}
			out.println("lists+='<td align=\"center\">" + ontherOperatingStr
					+ "</td>';");
		}

		out.println("lists+='</tr>';");
		out.println("});");
		out.println("$('#" + tableId + " > tbody').empty();");
		out.println("$('#" + tableId
				+ " > tbody').html(lists.replaceAll(\"null\",\"\"));");

		// 显示tips
		out.println("$(\".divTips\").tip();");

		// 显示能排序的列
		// 是否排序
		if (isOrder.equals("true")) {
			out.println("for (i=0;i<columnsStrArray" + pluginCode
					+ ".length;i++){");
			out.println("if(data.result.orderColumns.indexOf(columnsStrArray"
					+ pluginCode + "[i]+\",\")>0){");
			out.println("$('.'+columnsStrArray" + pluginCode + "[i]+'"
					+ pluginCode + "').html(\"<a href='#' onclick=columnSort"
					+ pluginCode + "('\"+columnsStrArray" + pluginCode
					+ "[i]+\"')>\"+$('.'+columnsStrArray" + pluginCode
					+ "[i]+'" + pluginCode + "').html()+\"</a>\");");
			out.println("}");
			out.println("}");
		}

		// 回调函数
		if (listCallback != null && !listCallback.equals("")) {
			out.println(listCallback + pluginCode + "(data);");
		}

		// 合并单元格
		if (mergeCells != null && !mergeCells.equals("")) {
			out.println("mergeCells" + pluginCode + "();");
		}

		// 显示斑马线
		if (isZebraCrossing != null && isZebraCrossing.equals("true")) {
			// 加载斑马线
			out.println("zebraCrossing()");
		}

		out.println("}");

		out.println(",error:function(XMLHttpRequest, textStatus, errorThrown){");
		// 关闭加载
		out.println("$(\"#loading" + pluginCode
				+ "\").css({\"display\":\"none\"});");
		
		out.print("if(XMLHttpRequest.getResponseHeader('SESSION_ERROR')!=null){");
		out.print("if(confirm('会话失效，确认重新登录！')){");
		out.print("parent.location.href=XMLHttpRequest.getResponseHeader('LOGIN_PATH');");
		out.print("}");
		out.print("return;");
		out.print("}");
		
		out.println("if(textStatus==0){alert('"
				+ this.getText("msg.error.server.not.start") + "');return;}");
		out.println("alert(textStatus);");
		// out.println("alert('" + this.getText("system.error")
		// + "'+'error code:'+datas.status);");

		out.println("}");

		out.println("});");
		out.println("}");
		out.println("</script>");
	}

	public String getText(String str) {
		return ResourcesTool.getText(il8nName, str, actionSupport.getLocale());
	}

	private String createParameter(String parameter, String type) {
		if (type.equals("http")) {

		}
		return "";
	}

	private String createParameterValue(String parameterValue, String type) {
		if (type.equals("http")) {

		}
		return "";
	}

	public String getColumnsStr() {
		return columnsStr;
	}

	public void setColumnsStr(String columnsStr) {
		this.columnsStr = columnsStr;
	}

	public String getSearchCountActionpath() {
		return searchCountActionpath;
	}

	public void setSearchCountActionpath(String searchCountActionpath) {
		this.searchCountActionpath = searchCountActionpath;
	}

	public String getSearchListActionpath() {
		return searchListActionpath;
	}

	public void setSearchListActionpath(String searchListActionpath) {
		this.searchListActionpath = searchListActionpath;
	}

	public String getTableCss() {
		return tableCss;
	}

	public void setTableCss(String tableCss) {
		this.tableCss = tableCss;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getRecordCountAttribute() {
		return recordCountAttribute;
	}

	public void setRecordCountAttribute(String recordCountAttribute) {
		this.recordCountAttribute = recordCountAttribute;
	}

	public String getListAttribute() {
		return listAttribute;
	}

	public void setListAttribute(String listAttribute) {
		this.listAttribute = listAttribute;
	}

	public String getCurrentPageIndexAttribute() {
		return currentPageIndexAttribute;
	}

	public void setCurrentPageIndexAttribute(String currentPageIndexAttribute) {
		this.currentPageIndexAttribute = currentPageIndexAttribute;
	}

	public String getIsNumber() {
		return isNumber;
	}

	public void setIsNumber(String isNumber) {
		this.isNumber = isNumber;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCheckboxValue() {
		return checkboxValue;
	}

	public void setCheckboxValue(String checkboxValue) {
		this.checkboxValue = checkboxValue;
	}

	public String getPageSizeAttribute() {
		return pageSizeAttribute;
	}

	public void setPageSizeAttribute(String pageSizeAttribute) {
		this.pageSizeAttribute = pageSizeAttribute;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getOntherOperating() {
		return ontherOperating;
	}

	public void setOntherOperating(String ontherOperating) {
		this.ontherOperating = ontherOperating;
	}

	public String getOntherOperatingWidth() {
		return ontherOperatingWidth;
	}

	public void setOntherOperatingWidth(String ontherOperatingWidth) {
		this.ontherOperatingWidth = ontherOperatingWidth;
	}

	public String getPluginCode() {
		return pluginCode;
	}

	public void setPluginCode(String pluginCode) {
		this.pluginCode = pluginCode;
	}

	public String getCustomColumnValue() {
		return customColumnValue;
	}

	public void setCustomColumnValue(String customColumnValue) {
		this.customColumnValue = customColumnValue;
	}

	private String createCustom(int i) {
		String[] customColumnValues = null;
		if (customColumnValue != null && !customColumnValue.equals("")) {
			customColumnValues = customColumnValue.split(";");
		} else {
			return "''";
		}
		String returnFunction = "''";
		for (String s : customColumnValues) {
			String index = s.substring(0, s.indexOf(","));
			String functionName = s.substring(s.indexOf(",") + 1);
			String function = functionName.substring(0,
					functionName.indexOf("("));
			String param = functionName.substring(
					functionName.indexOf("(") + 1, functionName.indexOf(")"));
			if (index.equals(i + "")) {
				if (param != null && !param.equals("")) {
					String params = "";
					for (String p : param.split(",")) {
						params += ("this." + p) + ",";
					}
					params = params.substring(0, params.lastIndexOf(","));

					returnFunction = (function + "(" + params + ")");
				} else {
					returnFunction = (function + "()");
				}
				break;
			} else {
				continue;
			}
		}
		return returnFunction;
	}

	/**
	 * 获取列宽
	 * 
	 * @param index
	 * @return
	 */
	public String getColumnsWidth(int index) {
		if (columnsMap != null && columnsMap.size() != 0) {
			return columnsMap.get(index);
		}
		return null;
	}

	public String getIl8nName() {
		return il8nName;
	}

	public void setIl8nName(String il8nName) {
		this.il8nName = il8nName;
	}

	public String getSubStringLength() {
		return subStringLength;
	}

	public void setSubStringLength(String subStringLength) {
		this.subStringLength = subStringLength;
	}

	public String getColumnsWidth() {
		return columnsWidth;
	}

	public void setColumnsWidth(String columnsWidth) {
		String wstr = columnsWidth;
		if (wstr != null && !"".equals(wstr)) {
			columnsMap = new HashMap<Integer, String>();
			String[] ws = wstr.split("]");
			for (String s : ws) {
				columnsMap.put(
						Integer.parseInt(s.substring(1, s.indexOf(","))),
						s.substring(s.indexOf(",") + 1));
			}
		}
		this.columnsWidth = columnsWidth;
	}

	public String getOperatingWidth() {
		return operatingWidth;
	}

	public void setOperatingWidth(String operatingWidth) {
		this.operatingWidth = operatingWidth;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public String getPageAttribute() {
		return pageAttribute;
	}

	public void setPageAttribute(String pageAttribute) {
		this.pageAttribute = pageAttribute;
	}

	public String getCountCallback() {
		return countCallback;
	}

	public void setCountCallback(String countCallback) {
		this.countCallback = countCallback;
	}

	public String getListCallback() {
		return listCallback;
	}

	public void setListCallback(String listCallback) {
		this.listCallback = listCallback;
	}

	public String getIsonLoad() {
		return isonLoad;
	}

	public void setIsonLoad(String isonLoad) {
		this.isonLoad = isonLoad;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getMergeCells() {
		return mergeCells;
	}

	public void setMergeCells(String mergeCells) {
		this.mergeCells = mergeCells;
	}

	public String getTitleBar() {
		return titleBar;
	}

	public void setTitleBar(String titleBar) {
		this.titleBar = titleBar;
	}

	public String getSearchFormId() {
		return searchFormId;
	}

	public void setSearchFormId(String searchFormId) {
		this.searchFormId = searchFormId;
	}

	public String getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(String isPackage) {
		this.isPackage = isPackage;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public String getIsZebraCrossing() {
		return isZebraCrossing;
	}

	public void setIsZebraCrossing(String isZebraCrossing) {
		this.isZebraCrossing = isZebraCrossing;
	}

	public String getIsPageSize() {
		return isPageSize;
	}

	public void setIsPageSize(String isPageSize) {
		this.isPageSize = isPageSize;
	}

	public String getCustomToolbarID() {
		return customToolbarID;
	}

	public void setCustomToolbarID(String customToolbarID) {
		this.customToolbarID = customToolbarID;
	}

	
}