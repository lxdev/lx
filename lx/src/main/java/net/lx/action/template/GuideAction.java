package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

public class GuideAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5264871408312724231L;

	@Autowired
	private IGuideBiz guideBiz;
	@Autowired
	private IGuideOptionBiz optionBiz;
	
	private int guide_id;
	private Guide guideResult;
	private List<GuideOption> options;
	
	@Autowired
	private IProgramBiz programBiz;
	private List<Program> programList = new ArrayList<Program>();
	private List<University> universityList = new ArrayList<University>();
	
	@Action(value = "guide", results = {@Result(name = "success", location="/WEB-INF/content/guide/guide.jsp") })
	public String first() throws Exception{
//		if(super.isGetRequest())
//		{
//			return SUCCESS;
//		}
		setOptions(optionBiz.findAll());
		guideResult = guideBiz.findById(guide_id);
		if(guideResult == null){
			List<Guide> tempGuideList = guideBiz.findAll();
			for(Guide item : tempGuideList){
				if(item.getIs_self_specialty() == 0 && item.getSpecialty_id() == guide_id){
					guideResult = item;
					break;
				}
			}
		}
		if(guideResult.getIs_self_specialty() == 0){
			
		}
		
		return SUCCESS;
	}
	
	@Action(value="guide_relation", results = {@Result(name="success", location="/WEB-INF/content/guide/guide_relation.jsp")})
	public String guide_relation() throws Exception {
		if(guide_id > 0){
			guideResult = guideBiz.findById(guide_id);
			int specialty_id = guideResult.getSpecialty_id();

			if(guideResult.getIs_self_specialty() == 0){
				Program condition = new Program();
				condition.setSpecialtyId(specialty_id);
				programList = programBiz.searchProgramsByCondition(condition);
				for(Program p : programList){
					Boolean isNotExists = true;
					for(University u : universityList){
						if(u.getId().equals(p.getUniversity_id())){
							isNotExists = false;
							break;
						}
					}
					if(isNotExists)
						universityList.add(p.getUniversity());
				}
			}
			return SUCCESS;
		}
		return ERROR;
	}

	//@Action(value = "guide_detail", results = {@Result(name = "success", location="/WEB-INF/content/guide/guide_detail.jsp") })
	@Action(value = "guide_detail", results = {@Result(name = "success", location="/WEB-INF/content/guide/guide.jsp") })
	public String guide_detail() throws Exception{
		setOptions(optionBiz.findAll());
		guideResult = guideBiz.findById(guide_id);
		
		return SUCCESS;
	}

	public int getGuide_id() {
		return guide_id;
	}

	public void setGuide_id(int guide_id) {
		this.guide_id = guide_id;
	}

	public List<GuideOption> getOptions() {
		return options;
	}

	public void setOptions(List<GuideOption> options) {
		this.options = options;
	}

	public Guide getGuideResult() {
		return guideResult;
	}

	public void setGuideResult(Guide guideResult) {
		this.guideResult = guideResult;
	}

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}

	public List<Program> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}

}
