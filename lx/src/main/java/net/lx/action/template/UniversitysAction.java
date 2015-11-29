package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.dic.IAreaBiz;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IScoreBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.convert.Convert;
import net.lx.common.string.StringEncode;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.Score;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.user.User;
//import net.lx.entity.university.ProgramSearchCondition;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.org.apache.xpath.internal.operations.Bool;


@ParentPackage("json-default")
public class UniversitysAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3999652567374568169L;

	@Autowired
	private IUniversityBiz universityBiz;
	
	// 查询条件
	private String university_name;		//院校名称
	private Integer university_name_id;	//院校id

	private int country_id;		//国家id

	private int unicountryStyleId;		//国家id
	private String countryName;
	private String ranking;
	private String area;
	private String tuition;
	private String is_public_school;
	private String orderBy;
	private int page;
	private int page_size;
	
	private University condition = new University();
	
	private int universityNum;
	private int programNum;

	private List<University> resultUniversity = new ArrayList<University>();

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
	private Specialty specialty;
	private List<Specialty> specialtyList = new ArrayList<Specialty>();
	
	private Boolean isFromFirst = false;
	
	@Action(value = "universitys", results = { 
//			@Result(name="input", type="dispatcher", location = "programs.jsp"),
//			@Result(name = "success", type = "dispatcher", params = {"contentType", "text/json" }, location="/WEB-INF/content/template/default/programs.jsp")
			@Result(name = "success", type = "dispatcher", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*, university_name, university_name_id, ranking, area, is_public_school, orderBy, isFromFirst, page, page_size, country_id"
				}, location="/WEB-INF/content/template/default/universitys.jsp"
			),
			@Result(name = "success_one", type="redirect", location = "university?universityId=${university_name_id}")
		}
	)
	public String execute() throws Exception{
		setCurrentUsers();
		
		if(university_name_id != null && university_name_id >= 1)
			return "success_one";
		
		University u = new University();

		if(unicountryStyleId > 0)
			u.setCountry_id(unicountryStyleId);
		else if(country_id > 0)
			u.setCountry_id(getCountry_id());
		else {
			//默认美国
			unicountryStyleId = 1;
			u.setCountry_id(1);
		}
		if(university_name != null){
			//ajax request
			university_name = StringEncode.ToUTF8(university_name, true);
			//form request
			//university_name = new String(university_name.getBytes("iso8859-1"),"utf-8");
		}
		u.setUniversity_name(getUniversity_name());
		if(ranking != null){
			//ajax request
			ranking = StringEncode.ToUTF8(ranking, true);
			//form request
			//ranking = new String(ranking.getBytes("iso8859-1"),"utf-8");
			if(!ranking.equalsIgnoreCase("全部")){
				String[] rankings = ranking.split("-");
				if(rankings.length == 1){
					rankings[0] = rankings[0].replace("以后", "");
				}
				u.setRankingBegin(Integer.parseInt(rankings[0]));
				if(rankings.length >= 2)
					u.setRankingEnd(Integer.parseInt(rankings[1]));
			}
		}
		if(area != null){
			//ajax request
			area = StringEncode.ToUTF8(area, true);
			//form request
			//area = new String(area.getBytes("iso8859-1"),"utf-8");
			if(!area.equalsIgnoreCase("全部")){
				u.setAreaName(area);
			}
		}
		if(is_public_school != null){
			if(!is_public_school.equalsIgnoreCase("-1")){
				u.setIs_public_school(Integer.parseInt(is_public_school));
			}
		}

		countryList = this.countryBiz.findAll();

		if(getCountry_id() >= 1)
			setCountryName(this.countryBiz.findCountryById(getCountry_id()).getName());
		
		//u.setPage_size(condition.getPage_size() <= 0 ? 10 : condition.getPage_size());
		//u.setPage(condition.getPage() <= 0 ? 1 : condition.getPage());
		//u.setOrderBy(" D.total_browse DESC ");
		if(page == 0)
			page = 1;
		u.setPage(page);
		if(getOrderBy() == null || getOrderBy().equals("")){
			setOrderBy(" D.total_browse DESC ");
		}
		u.setPage_size(getPage_size() == 0 ? 10 : getPage_size());
		u.setOrderBy(getOrderBy());
		
		resultUniversity = this.universityBiz.searchUniversitysByCondition(u);
		u.setIsSearchTotal(true);
		this.setUniversityNum(this.universityBiz.searchUniversitysRecordByCondition(u));
		return SUCCESS;
	}
	
	@Action(value = "json_universitys", results = { 
			@Result(name = "success", type = "json", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*,universityNum.*"
				}) } )
	public String json_execute() throws Exception{
		setCurrentUsers();
		
		this.setResultUniversity(this.universityBiz.searchUniversitysByCondition(condition));
		condition.setIsSearchTotal(true);
		this.setUniversityNum(this.universityBiz.searchUniversitysRecordByCondition(condition));
		return SUCCESS;
	}
	
	public List<University> getResultUniversity() {
		return resultUniversity;
	}

	public void setResultUniversity(List<University> resultUniversity) {
		this.resultUniversity = resultUniversity;
	}
	
	public String getCountryName(){
		return countryName;
	}
	public void setCountryName(String countryName){
		this.countryName = countryName;
	}
	
	public int getUniversityNum(){
		return universityNum;
	}
	public void setUniversityNum(int universityNum){
		this.universityNum = universityNum;
	}
	
	public int getProgramNum(){
		return programNum;
	}
	public void setProgramNum(int programNum){
		this.programNum = programNum;
	}
	
	//

	public String getRanking(){
		return ranking;
	}
	public void setRanking(String ranking){
		this.ranking = ranking;
	}
	
	public String getArea(){
		return area;
	}
	public void setArea(String area){
		this.area = area;
	}
	
	public String getTuition(){
		return tuition;
	}
	public void setTuition(String tuition){
		this.tuition = tuition;
	}
	
	public String getIs_public_school(){
		return is_public_school;
	}
	public void setIs_public_school(String is_public_school){
		this.is_public_school = is_public_school;
	}

	public String getOrderBy(){
		return orderBy;
	}
	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}
	
	
	//--------------------------- Dic List -----------------------------
	
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

//	public Area getArea() {
//		return area;
//	}
//	public void setArea(Area area) {
//		this.area = area;
//	}
//	public List<Area> getAreaList() {
//		return areaList;
//	}
//	public void setAreaList(List<Area> areaList) {
//		this.areaList = areaList;
//	}

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

	public Boolean getIsFromFirst() {
		return isFromFirst;
	}

	public void setIsFromFirst(Boolean isFromFirst) {
		this.isFromFirst = isFromFirst;
	}

	public University getCondition() {
		return condition;
	}

	public void setCondition(University condition) {
		this.condition = condition;
	}

	public String getUniversity_name() {
		return university_name;
	}

	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}

	public Integer getUniversity_name_id() {
		return university_name_id;
	}

	public void setUniversity_name_id(Integer university_name_id) {
		this.university_name_id = university_name_id;
	}


	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getUnicountryStyleId() {
		return unicountryStyleId;
	}
	public void setUnicountryStyleId(int unicountryStyleId) {
		this.unicountryStyleId = unicountryStyleId;
	}


	//------------------------------------------------------------------------
}
