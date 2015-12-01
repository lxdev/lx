package net.lx.action.manage;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.string.StringEncode;
import net.lx.entity.university.Specialty;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ParentPackage("struts-default")
@Namespace("/manage")
@Component
public class SpecialtyAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ISpecialtyBiz specialtyBiz;

	private boolean results=false;
	
	//攻略列表
	private List<Specialty> specialtys;
	private List<Specialty> parentSpecialtys;
	private List<Specialty> rootSpecialtys;
	//攻略内容
	private Specialty specialty;
	private int specialty_id;
	
	private String message;
	
	
	@Action(value = "specialty", results = { @Result(name = "success", location = "/WEB-INF/content/manage/specialty.jsp") })
	public String Guides() throws Exception {
		specialtys = specialtyBiz.findAll();
		if(message != null && !message.equals("")){
			//message = StringEncode.ToUTF8(message, false);
		}
		return "success";
	}

	@Action(value = "specialty_add", results = { 
			@Result(name = "input", location = "/WEB-INF/content/manage/specialty_add.jsp"),
			@Result(name = "success", location = "specialty?message=${message}", type = "redirect")
	})
	public String SpecialtyAdd() {
		try
		{
			if(isGetRequest())
			{
				setMessage("新建专业");
				setParentSpecialtys(specialtyBiz.findAllParent());
				setRootSpecialtys(specialtyBiz.findAllRoot());
				
				if(getSpecialty_id() != 0)
					setSpecialty(specialtyBiz.findById(getSpecialty_id()));
				else{
					setSpecialty(new Specialty());
					specialty.setDepth(0);
					specialty.setParent_id(0);
					specialty.setId(0);
				}
				return INPUT;
			}
			results = saveSpecialty();
			if(results)
				setMessage(ResourcesTool.getText("admin","add.success"));
			else
				setMessage(ResourcesTool.getText("admin", "username.repeat.error"));
			addActionMessage(getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	private Boolean saveSpecialty() throws Exception{
		//guide 
//		specialty = new Specialty();
//		specialty.setSpecialty_name(specialty_name)
//		guide.setGuide_id(guide_id);
//		guide.setGuide_name(guide_name);
//		guide.setCountry_id(country_id);
//		guide.setSpecialty_id(specialty_id);
//		guide.setIs_self_specialty(is_self_specialty);
		specialty.setParent_id(specialty.getParent_id() == null ? 0 : specialty.getParent_id());
		if(getSpecialty().getId() != 0){
			specialtyBiz.modify(getSpecialty());
			setSpecialty_id(getSpecialty().getId());
		}
		else{
			//判断名字是否重复
			//
			setSpecialty_id(specialtyBiz.createNew(getSpecialty()));
		}
		if(getSpecialty_id() == 0)
			return false;
		//option content list
		
		return true;
	}

	@Action(value = "specialty_update", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "specialty?message=${message}") })
	public String GuideUpdate() {
		try {
			saveSpecialty();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMessage("修改攻略'" + getSpecialty().getSpecialty_name() + "'成功");
		return "success";
	}

	@Action(value = "specialty_delete", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "specialty?message=${message}") })
	public String GuideDelete() {
		try {
			setMessage("");
			if(getSpecialty_id() != 0){
				Integer _count = specialtyBiz.getChildCount(getSpecialty_id());
				if(_count == 0){
					specialtyBiz.deleteById(getSpecialty_id());
					setMessage("删除专业成功");
				}else{
					setMessage("此专业还有子专业存在，无法删除！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMessage("删除专业失败");
		}
		return "success";
	}

	public List<Specialty> getSpecialtys() {
		return specialtys;
	}

	public void setSpecialtys(List<Specialty> specialtys) {
		this.specialtys = specialtys;
	}

	public List<Specialty> getParentSpecialtys() {
		return parentSpecialtys;
	}

	public void setParentSpecialtys(List<Specialty> parentSpecialtys) {
		this.parentSpecialtys = parentSpecialtys;
	}

	public List<Specialty> getRootSpecialtys() {
		return rootSpecialtys;
	}

	public void setRootSpecialtys(List<Specialty> rootSpecialtys) {
		this.rootSpecialtys = rootSpecialtys;
	}

	public int getSpecialty_id() {
		return specialty_id;
	}

	public void setSpecialty_id(int specialty_id) {
		this.specialty_id = specialty_id;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
