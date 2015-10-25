package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideSpecialty;
import net.lx.entity.university.Specialty;


public class GuidesAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1169586114239665452L;
	@Autowired
	private ICountryBiz countryBiz;
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	@Autowired
	private IGuideSpecialtyBiz guideSpecialtyBiz;
	@Autowired
	private IGuideBiz guideBiz;

	private List<Country> countrys = new ArrayList<Country>();
	private List<Specialty> specialtys = new ArrayList<Specialty>();
	private List<GuideSpecialty> guideSpecialtys = new ArrayList<GuideSpecialty>();
	private String guide_specialty;
	private int guide_specialty_id;
	private List<Specialty> specialtyCategorys = new ArrayList<Specialty>();
	
	private List<Guide> resultGuide = new ArrayList<Guide>();
	
	@Action(value = "guides", results = { 
			@Result(name="input", type="dispatcher", location = "guide/guides.jsp"),
			@Result(name = "success", type = "dispatcher", params = {"contentType", "text/json" }, location="/WEB-INF/content/guide/guides.jsp"),
			@Result(name = "success_one", type="redirect", location = "guide?guide_id=${guide_specialty_id}")
	})
	public String execute() throws Exception{
		if(guide_specialty_id > 0)
			return "success_one";
		
		countrys = this.countryBiz.findAll();
		specialtyCategorys = this.specialtyBiz.findAllRoot();
		guideSpecialtys = this.guideSpecialtyBiz.findAll();
		if(guide_specialty == null || guide_specialty.equals(""))
			specialtys = this.specialtyBiz.findAll();
		else{
			Specialty condition = new Specialty();
			condition.setSpecialty_name(guide_specialty);
			specialtys = this.specialtyBiz.searchSpecialtysByCondition(condition);
		}
		
		List<Guide> allGuides = guideBiz.findAll();
		if(guide_specialty != null && !guide_specialty.equals("")){
			if(specialtys.size() > 0){
				for(Specialty s : specialtys){
					for(Guide item : allGuides){
						if(item.getIs_self_specialty() == 0){
							if(item.getSpecialty_id() == s.getId())
							{
								resultGuide.add(item);
								break;
							}
						}
					}
				}
			}
		}else
			setResultGuide(allGuides);
		
		return SUCCESS;
	}
	
	//-------------------------
	
	public List<Country> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<Country> countrys) {
		this.countrys = countrys;
	}
	
	public List<Specialty> getSpecialtys() {
		return specialtys;
	}

	public void setSpecialtys(List<Specialty> specialtys) {
		this.specialtys = specialtys;
	}
	public List<GuideSpecialty> getGuideSpecialtys() {
		return guideSpecialtys;
	}

	public void setGuideSpecialtys(List<GuideSpecialty> guideSpecialtys) {
		this.guideSpecialtys = guideSpecialtys;
	}

	public List<Guide> getResultGuide() {
		return resultGuide;
	}

	public void setResultGuide(List<Guide> resultGuide) {
		this.resultGuide = resultGuide;
	}

	public int getGuide_specialty_id() {
		return guide_specialty_id;
	}

	public void setGuide_specialty_id(int guide_specialty_id) {
		this.guide_specialty_id = guide_specialty_id;
	}

	public String getGuide_specialty() {
		return guide_specialty;
	}

	public void setGuide_specialty(String guide_specialty) {
		this.guide_specialty = guide_specialty;
	}

	public List<Specialty> getSpecialtyCategorys() {
		return specialtyCategorys;
	}

	public void setSpecialtyCategorys(List<Specialty> specialtyCategorys) {
		this.specialtyCategorys = specialtyCategorys;
	}
}
