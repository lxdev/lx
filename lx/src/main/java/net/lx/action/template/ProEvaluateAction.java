package net.lx.action.template;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.convert.JsonUtil;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.guide.Guide;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

@Namespace("/template")
@ParentPackage("json-default")
public class ProEvaluateAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9191284440062341268L;
	@Autowired
	private ICountryBiz countryBiz;// country业务接口
	private Country country;// country集合
	private List<Country> countryList = new ArrayList<Country>();
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	private StudyLevel studyLevel;
	private List<StudyLevel> studyLevelList = new ArrayList<StudyLevel>();
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	private Specialty specialty = new Specialty();
	private List<Specialty> specialtyList = new ArrayList<Specialty>();

	@Autowired
	private IUniversityBiz universityBiz;
	private List<University> universityList = new ArrayList<University>();
	
	@Autowired
	private IGuideBiz guideBiz;
	private List<Guide> guideList = new ArrayList<Guide>();
	
	private String term = "";
	//private String q = "";
	//private String jsonResult;

	private int countryId;
	private int unicountryStyleId;
	private int studynat;
	private int studyLevelId;

	private int show_login;
	private int show_register;
	
	
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "proevaluate", results = { 
			@Result(name = "success",  type = "dispatcher", location = "/WEB-INF/content/template/default/proevaluate.jsp")})
	public String execute() throws Exception
	{

		
		return SUCCESS;
	}

	@Action(value = "university_search", results = { 
			@Result(name = "success", type = "json", params = {"contentType", "text/json", "includeProperties", "universityList.*" }) 
	})
	public String university_search() throws Exception
	{
//		setTerm(new String(getTerm().getBytes("iso8859-1"),"utf-8"));
//		specialty.setSpecialty_name(getTerm());
//		specialtyList = specialtyBiz.searchSpecialtysByCondition(specialty);
			
		if(!getTerm().equals("")){
			//setTerm(new String(getTerm().getBytes("iso8859-1"),"utf-8"));
			University condition = new University();
			condition.setUniversity_name(getTerm());
			universityList = universityBiz.searchUniversitysByCondition(condition);

			//jsonResult = JsonUtil.array2Json(universityList);
		}
		
		return SUCCESS;
	}
	
	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	//study level
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

	//specialty
	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<Specialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<Specialty> specialtyList) {
		this.specialtyList = specialtyList;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

//	public String getQ() {
//		return q;
//	}
//
//	public void setQ(String q) {
//		this.q = q;
//	}

	public List<Guide> getGuideList() {
		return guideList;
	}

	public void setGuideList(List<Guide> guideList) {
		this.guideList = guideList;
	}

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStudyLevelId() {
		return studyLevelId;
	}

	public void setStudyLevelId(int studyLevelId) {
		this.studyLevelId = studyLevelId;
	}

	public int getUnicountryStyleId() {
		return unicountryStyleId;
	}

	public void setUnicountryStyleId(int unicountryStyleId) {
		this.unicountryStyleId = unicountryStyleId;
	}

	public int getStudynat() {
		return studynat;
	}

	public void setStudynat(int studynat) {
		this.studynat = studynat;
	}

	public int getShow_login() {
		return show_login;
	}

	public void setShow_login(int show_login) {
		this.show_login = show_login;
	}

	public int getShow_register() {
		return show_register;
	}

	public void setShow_register(int show_register) {
		this.show_register = show_register;
	}

//	public String getJsonResult() {
//		return jsonResult;
//	}
//
//	public void setJsonResult(String jsonResult) {
//		this.jsonResult = jsonResult;
//	}
}
