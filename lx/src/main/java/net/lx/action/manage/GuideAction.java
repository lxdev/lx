package net.lx.action.manage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.biz.guide.IGuideOptionContentBiz;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.common.il8n.ResourcesTool;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideOptionContent;
import net.lx.entity.guide.GuideSpecialty;
import net.lx.entity.university.Specialty;

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
public class GuideAction extends BaseAction implements ModelDriven<Guide> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5904705893638642357L;

	@Autowired
	private IGuideBiz guideBiz;
	@Autowired
	private IGuideOptionContentBiz guideOptionContentBiz;
	@Autowired
	private ICountryBiz countryBiz;
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	@Autowired
	private IGuideOptionBiz guideOptionBiz;
	@Autowired
	private IGuideSpecialtyBiz guideSpecialtyBiz;

	private boolean results=false;
	
	//攻略列表
	private List<Guide> guides;
	//攻略内容
	private Guide guide;
	private int guide_id;
	private String guide_name;
	private String message;
	private int country_id;
	private int specialty_id;
	private int direction_specialty_id;
	private int is_self_specialty;
	private List<GuideOptionContent> optionContents;
	//list
	private List<Country> countrys;
	private List<Specialty> parentSpecialtys;
	private List<GuideSpecialty> parentGuideSpecialtys;
	private List<GuideOption> guideOptions;	//父 子 混合
	
	@Action(value = "guide", results = { @Result(name = "success", location = "/WEB-INF/content/manage/guide.jsp") })
	public String Guides() throws Exception {
		guides = guideBiz.findAll();
		return "success";
	}

	@Action(value = "guide_add", results = { 
			@Result(name = "input", location = "/WEB-INF/content/manage/guide_add.jsp"),
			@Result(name = "success", location = "guide?message=${message}", type = "redirect")
	})
	public String GuideAdd() {
		try
		{
			if(isGetRequest())
			{
				message = "新建攻略";
				
				countrys = countryBiz.findAll();
				parentSpecialtys = specialtyBiz.findAllRoot();
				parentGuideSpecialtys = guideSpecialtyBiz.findAllParent();
				guideOptions = guideOptionBiz.findAll();
				
				if(guide_id != 0)
					guide = guideBiz.findById(guide_id);
				else{
					guide = new Guide();
					guide.setIs_self_specialty(0);
				}
				return INPUT;
			}
			results = saveGuide();
			if(results)
				message = ResourcesTool.getText("admin","add.success");
			else
				message = ResourcesTool.getText("admin", "username.repeat.error");
			addActionMessage(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	private Boolean saveGuide() throws Exception{
		//guide 
		guide = new Guide();
		guide.setGuide_id(guide_id);
		guide.setGuide_name(guide_name);
		guide.setCountry_id(country_id);
		if(direction_specialty_id > 0)
			guide.setSpecialty_id(direction_specialty_id);
		else
			guide.setSpecialty_id(specialty_id);
		guide.setIs_self_specialty(is_self_specialty);
		if(guide_id != 0)
			guideBiz.modify(guide);
		else{
			//判断名字是否重复
			//
			guide_id = guideBiz.createNew(guide);
		}
		if(guide_id == 0)
			return false;
		//option content list
		//JSON to Object
		guideOptions = guideOptionBiz.findAll();
		optionContents = new ArrayList<GuideOptionContent>();
		for(GuideOption item : guideOptions){
			if(item.getParent_id() != 0){
				 String content = request.getParameter("option." + item.getId().toString());
				 //if(!"".equals(content)){
				 if(content != null){
					 GuideOptionContent goc = new GuideOptionContent();
					 goc.setOption_id(item.getId());
					 goc.setOption_content(content.trim());
					 goc.setGuide_id(guide_id);
					 optionContents.add(goc);
				 }
			}
		}
		
		guideOptionContentBiz.saveAllByGuide(optionContents);
		return true;
	}

	@Action(value = "guide_update", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide?message=${message}") })
	public String GuideUpdate() {
		try {
			saveGuide();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "修改攻略'" + guide.getGuide_name() + "'成功";
		return "success";
	}

	@Action(value = "guide_delete", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "guide?message=${message}") })
	public String GuideDelete() {
		try {
			message = "";
			if(guide_id != 0){
				guideBiz.deleteById(guide_id);
				message = "删除攻略成功";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "删除攻略失败";
		}
		return "success";
	}
	
	public String execute()
	{
		try
		{
			if(isGetRequest())
			{
				return INPUT;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	
	public Guide getModel() {
		return guide;
	}

	public Guide getGuide() {
		return guide;
	}
	public void setGuide(Guide guide) {
		this.guide = guide;
	}
	
	public boolean getResults() {
		return results;
	}

	public int getGuide_id(){
		return guide_id;
	}
	public void setGuide_id(int guide_id){
		this.guide_id = guide_id;
	}
	
	public String getGuide_name(){
		return guide_name;
	}
	public void setGuide_name(String guide_name){
		this.guide_name = guide_name;
	}

	public int getCountry_id(){
		return country_id;
	}
	public void setCountry_id(int country_id){
		this.country_id = country_id;
	}

	public int getSpecialty_id(){
		return specialty_id;
	}
	public void setSpecialty_id(int specialty_id){
		this.specialty_id = specialty_id;
	}

	public int getIs_self_specialty(){
		return is_self_specialty;
	}
	public void setIs_self_specialty(int is_self_specialty){
		this.is_self_specialty = is_self_specialty;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<Guide> getGuides() {
		return guides;
	}
	public void setGuides(List<Guide> guides) {
		this.guides = guides;
	}
	
	//----- DIC
	public List<Country> getCountrys() {
		return countrys;
	}
	public void setCountrys(List<Country> countrys) {
		this.countrys = countrys;
	}

	public List<Specialty> getParentSpecialtys() {
		return parentSpecialtys;
	}
	public void setParentSpecialtys(List<Specialty> parentSpecialtys) {
		this.parentSpecialtys = parentSpecialtys;
	}
	
	public List<GuideSpecialty> getParentGuideSpecialtys() {
		return parentGuideSpecialtys;
	}
	public void setParentGuideSpecialtys(List<GuideSpecialty> parentGuideSpecialtys) {
		this.parentGuideSpecialtys = parentGuideSpecialtys;
	}
	
	public List<GuideOption> getGuideOptions() {
		return guideOptions;
	}
	public void setGuideOptions(List<GuideOption> guideOptions) {
		this.guideOptions = guideOptions;
	}

	public int getDirection_specialty_id() {
		return direction_specialty_id;
	}

	public void setDirection_specialty_id(int direction_specialty_id) {
		this.direction_specialty_id = direction_specialty_id;
	}
	
	
}
