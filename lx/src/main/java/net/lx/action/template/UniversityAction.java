package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.dic.IAreaBiz;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.university.IUniversityStatisticBiz;
import net.lx.biz.user.UserBiz;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.university.Program;
import net.lx.entity.university.University;
import net.lx.entity.university.UniversityStatistic;
import net.lx.entity.user.User;

public class UniversityAction extends BaseAction {

	@Autowired
	private IUniversityBiz universityBiz;
	@Autowired
	private ICountryBiz countryBiz;
	private List<Country> countryList = new ArrayList<Country>();
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	private StudyLevel studyLevel;
	private List<StudyLevel> studyLevelList = new ArrayList<StudyLevel>();
	@Autowired
	private IUniversityStatisticBiz universityStatisticBiz;
	private UniversityStatistic universityStatistic = new UniversityStatistic();
	
	
	private int universityId;
	private University resultUniversity = new University();
	
	@Action(value = "university", results = {
			@Result(name = "success", type = "dispatcher", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*, universityId"
			}, location="/WEB-INF/content/template/default/university.jsp") 
		}
	)
	public String execute() throws Exception{
		setCurrentUsers();
		if(this.getUniversityId() != 0){

			resultUniversity = universityBiz.findUniversityById(this.getUniversityId());
			countryList = this.countryBiz.findAll();
			studyLevelList = this.studyLevelBiz.findAll();

			universityStatistic.setUniversity_id(this.getUniversityId());
			universityStatistic.setIncr_or_decr(1);
			universityStatisticBiz.createOrUpdate(universityStatistic);
			
			return SUCCESS;
		}else
			return ERROR;
	}
	

	public int getUniversityId(){
		return universityId;
	}
	public void setUniversityId(int universityId){
		this.universityId = universityId;
	}
	
	public University getUniversity(){
		return resultUniversity;
	}
	public void setUniversity(University resultUniversity){
		this.resultUniversity = resultUniversity;
	}
	
	
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	//学历
	public StudyLevel getStudyLevel() {
		return studyLevel;
	}
	public void setStudyLevel(StudyLevel studyLevel) {
		this.studyLevel = studyLevel;
	}
	public List<StudyLevel> getStudyLevelList() {
		return studyLevelList;
	}
	public void setStudyLevelList(List<StudyLevel> studyLevelList) {
		this.studyLevelList = studyLevelList;
	}


	public UniversityStatistic getUniversityStatistic() {
		return universityStatistic;
	}


	public void setUniversityStatistic(UniversityStatistic universityStatistic) {
		this.universityStatistic = universityStatistic;
	}
}
