package net.lx.action.manage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.common.il8n.ResourcesTool;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideSpecialty;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 添加
 * @author Jack
 *
 */
@Namespace("/manage")
@Component
public class GuideSpecialtyAction extends BaseAction implements ModelDriven<GuideSpecialty> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6557099154736229600L;

	@Autowired
	private IGuideSpecialtyBiz specialtyBiz;
	
	private GuideSpecialty specialty;
	
	private boolean results=false;
	
	private List<GuideSpecialty> parentList = new ArrayList<GuideSpecialty>();
	
	private List<GuideSpecialty> specialtys;
	private int id;
	private String specialty_name;
	private int parent_id;
	private String message;
	
	/*
	 * 返回列表
	 */
	@Action(value = "guide_specialty", results = { @Result(name = "success", location = "/WEB-INF/content/manage/guide_specialty.jsp") })
	public String GuideSpecialtys() throws Exception {
		specialtys = specialtyBiz.findAll();
		return "success";
	}

	@Action(value = "guide_specialty_add", results = { @Result(name = "success", location = "/WEB-INF/content/manage/guide_specialty_add.jsp") })
	public String GuideOptionAdd() {
		try
		{
			if(isGetRequest())
			{
				message = "新建自定义专业";
				parentList = specialtyBiz.findAllParent();
				if(id != 0)
					specialty = specialtyBiz.findById(id);
				else
					specialty = new GuideSpecialty();
				return INPUT;
			}
			specialty = new GuideSpecialty();
			specialty.setCreated_user_id(getUser().getUserId());
			//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//specialty.setCreated_date(sdf.parse("2011-01-01 00:00:00"));
			specialty.setCreated_date(new Date());
			specialty.setSpecialty_name(specialty_name);
			specialty.setParent_id(parent_id);
			results = specialtyBiz.createNew(specialty);
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

	@Action(value = "guide_specialty_update", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide_specialty?message=${message}") })
	public String GuideSpecialtyUpdate() {
		try {
			specialty = new GuideSpecialty();
			specialty.setId(id);
			specialty.setSpecialty_name(specialty_name);
			specialty.setParent_id(parent_id);
			GuideSpecialty temp = specialtyBiz.findById(id);
			if((temp.getParent_id() == 0 && specialty.getParent_id() != 0) || (temp.getParent_id() != 0 && specialty.getParent_id() == 0))
				message = "修改自定义专业'" + specialty.getSpecialty_name() + "'失败";
			else{
				specialty.setCreated_date(temp.getCreated_date());
				specialty.setCreated_user_id(temp.getCreated_user_id());
				specialtyBiz.modify(specialty);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "修改自定义专业'" + specialty.getSpecialty_name() + "'成功";
		return "success";
	}

	@Action(value = "guide_specialty_delete", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide_specialty?message=${message}") })
	public String GuideSpecialtyDelete() {
		try {
			message = "";
			if(id != 0){
				GuideSpecialty temp = specialtyBiz.findById(id);
				//if(temp.getParent_id() > 0){
					specialtyBiz.deleteById(id);
					message = "删除自定义专业'" + id + "'成功";
				//}else
				//	message = "删除自定义专业失败，不允许删除父专业！";
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
				parentList = specialtyBiz.findAllParent();
				
				return INPUT;
			}
			specialty.setCreated_user_id(getUser().getUserId());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			specialty.setCreated_date(sdf.parse("2011-01-01 00:00:00"));
			results=specialtyBiz.createNew(specialty);
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
	
	public GuideSpecialty getModel() {
		return specialty;
	}

	public GuideSpecialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(GuideSpecialty specialty) {
		this.specialty = specialty;
	}

	public boolean getResults() {
		return results;
	}

	public List<GuideSpecialty> getParentList() {
		return parentList;
	}

	public void setParentList(List<GuideSpecialty> parentList) {
		this.parentList = parentList;
	}

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getSpecialty_name(){
		return specialty_name;
	}
	public void setSpecialty_name(String specialty_name){
		this.specialty_name = specialty_name;
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

	public List<GuideSpecialty> getSpecialtys() {
		return specialtys;
	}
	public void setSpecialtys(List<GuideSpecialty> specialtys) {
		this.specialtys = specialtys;
	}
}
