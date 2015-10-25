package net.lx.action.manage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.file.FileUtil;
import net.lx.common.il8n.ResourcesTool;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.user.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 添加用户
 * @author Jack
 *
 */
@Namespace("/manage")
@Component
public class GuideOptionAction extends BaseAction implements ModelDriven<GuideOption> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5904705893638642357L;

	@Autowired
	private IGuideOptionBiz optionBiz;

	private GuideOption option;
	private File files;                           //文件 
	private String filesFileName;                 //原始文件名称
	
	private String savePath;                      //服务器路径
	
	private boolean results=false;
	
	private List<GuideOption> parentList = new ArrayList<GuideOption>();

	private List<GuideOption> options;
	private int id;
	private String option_name;
	private int parent_id;
	private String message;
	
	@Action(value = "guide_option", results = { @Result(name = "success", location = "/WEB-INF/content/manage/guide_option.jsp") })
	public String GuideOptions() throws Exception {
		options = optionBiz.findAll();
		return "success";
	}

	@Action(value = "guide_option_add", results = { @Result(name = "success", location = "/WEB-INF/content/manage/guide_option_add.jsp") })
	public String GuideOptionAdd() {
		try
		{
			if(isGetRequest())
			{
				message = "新建选项";
				parentList = optionBiz.findAllParent();
				if(id != 0)
					option = optionBiz.findById(id);
				else
					option = new GuideOption();
				return INPUT;
			}
			//if(null!=files)
				//options.setPhoto_url(uploadFile(filesFileName));
			option = new GuideOption();
			option.setCreated_user_id(getUser().getUserId());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			option.setCreated_date(sdf.parse("2011-01-01 00:00:00"));
			option.setOption_name(option_name);
			option.setParent_id(parent_id);
			results = optionBiz.createNew(option);
			if(results)
				addActionMessage(ResourcesTool.getText("admin","add.success"));
			else
				addActionMessage(ResourcesTool.getText("admin", "username.repeat.error"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}

	@Action(value = "guide_option_update", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide_option?message=${message}") })
	public String GuideOptionUpdate() {
		try {
			option = new GuideOption();
			option.setId(id);
			option.setOption_name(option_name);
			option.setParent_id(parent_id);
			GuideOption temp = optionBiz.findById(id);
			if((temp.getParent_id() == 0 && option.getParent_id() != 0) || (temp.getParent_id() != 0 && option.getParent_id() == 0))
				message = "修改选项'" + option.getOption_name() + "'失败";
			else{
				option.setCreated_date(temp.getCreated_date());
				option.setCreated_user_id(temp.getCreated_user_id());
				optionBiz.modify(option);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "修改选项'" + option.getOption_name() + "'成功";
		return "success";
	}

	@Action(value = "guide_option_delete", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide_option?message=${message}") })
	public String GuideOptionDelete() {
		try {
			message = "";
			if(id != 0){
				GuideOption temp = optionBiz.findById(id);
				if(temp.getParent_id() > 0){
					optionBiz.deleteById(id);
					message = "删除选项'" + id + "'成功";
				}else
					message = "删除选项失败，不允许删除父选项！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "删除选项'" + id + "'失败";
		}
		return "success";
	}
	
	public String execute()
	{
		try
		{
			if(isGetRequest())
			{
				parentList = optionBiz.findAllParent();
				
				return INPUT;
			}
			//if(null!=files)
				//options.setPhoto_url(uploadFile(filesFileName));
			option.setCreated_user_id(getUser().getUserId());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			option.setCreated_date(new Date());
			results = optionBiz.createNew(option);
			if(results)
				addActionMessage(ResourcesTool.getText("admin","add.success"));
			else
				addActionMessage(ResourcesTool.getText("admin", "username.repeat.error"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	/**
	 * 附件上传_返回
	 * @param name 实际文件名
	 * @return	存储的相对路径
	 */
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("admin","uploadpath"));
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	public GuideOption getModel() {
		return option;
	}

	public GuideOption getOption() {
		return option;
	}
	public void setOption(GuideOption option) {
		this.option = option;
	}
	
	public boolean getResults() {
		return results;
	}

	public List<GuideOption> getParentList() {
		return parentList;
	}

	public void setParentList(List<GuideOption> parentList) {
		this.parentList = parentList;
	}

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getOption_name(){
		return option_name;
	}
	public void setOption_name(String option_name){
		this.option_name = option_name;
	}
	public int getParent_id(){
		return parent_id;
	}
	public void setParent_id(int parent_id){
		this.parent_id = parent_id;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<GuideOption> getOptions() {
		return options;
	}
	public void setOptions(List<GuideOption> options) {
		this.options = options;
	}

//	public File getFiles() {
//		return files;
//	}
//
//	public void setFiles(File files) {
//		this.files = files;
//	}
//
//	public String getFilesFileName() {
//		return filesFileName;
//	}
//
//	public void setFilesFileName(String filesFileName) {
//		this.filesFileName = filesFileName;
//	}
	
}
