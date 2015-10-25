package net.lx.action.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IScoreBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.ISpecialtyRankBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.string.StringEncode;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.Score;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.SpecialtyRank;
import net.lx.entity.university.University;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class ProgramsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2377640028578072690L;
	@Autowired
	private IProgramBiz programBiz;
	@Autowired
	private IUniversityBiz universityBiz;
	@Autowired
	private ISpecialtyRankBiz specialtyRankBiz;
	
	// 查询条件
	private int countryId;	//国家
	private String countryName;
	private int studyLevelId;
	private String studyLevelName;
	private Integer program_specialty_id;
	private String program_specialty;
	private Integer university_name_id;
	private String university_name;
	private String ranking;
	private String area;
	private String totef;
	private String ietls;
	private String gre;
	private String gmat;
	private String is_language_score;
	private String time_of_enrollment;
	private String work_experience_require;
	private String is_public_school;
	
	
	private int universityNum;
	private int programNum;
	private List<Program> resultProgram = new ArrayList<Program>();

	private List<University> resultUniversity = new ArrayList<University>();
	
	private List<SpecialtyRank> resultSpecialtyRank = new ArrayList<SpecialtyRank>();

	@Autowired
	private ICountryBiz countryBiz;// country业务接口
	private Country country;// country集合
	private Integer nationId;	//国家
	private List<Country> countryList = new ArrayList<Country>();
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	private StudyLevel studyLevel;
	private List<StudyLevel> studyLevelList = new ArrayList<StudyLevel>();

	@Autowired
	private ISpecialtyBiz specialtyBiz;

	@Autowired
	private IScoreBiz scoreBiz;
	private Score score;
	private List<Score> scoreList = new ArrayList<Score>();
	
	private String json;
	private String result= "";

	private Program condition = new Program();
	
	@Action(value = "programs", results = { 
			@Result(name = "success", type = "dispatcher", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*, countryId, studyLevelId, ctitle, specialtyId, ranking, area, totef, ietls, gre, gmat, is_language_score, time_of_enrollment, work_experience_require"
			}, location="/WEB-INF/content/template/default/programs.jsp") 
	})
	public String execute() throws Exception{
		setCurrentUsers();
		return searchAndReturn();
	}
	
	//页面查找课程
	@Action(value = "ajax_programs", results = { 
			@Result(name = "success", type = "json", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*, countryId, studyLevelId, ctitle, ranking, area, totef, ietls, gre, gmat, is_language_score, time_of_enrollment, work_experience_require"
			})
	})
	public String ajax_programs() throws Exception{
		return searchAndReturn();
	}
	
	@Action(value = "json_programs", results = { 
			@Result(name = "success", type = "json", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*,programNum.*,universityNum.*"
				}) } )
	public String json_execute() throws Exception{
		setCurrentUsers();
		
		this.setResultProgram(this.programBiz.searchProgramsByCondition(condition));
		condition.setIsSearchTotal(true);
		String numString = this.programBiz.searchProgramsRecordByCondition(condition);
		this.setProgramNum( Integer.valueOf(numString.split(",")[0]) );
		this.setUniversityNum( Integer.valueOf(numString.split(",")[1]) );
		
		for(Program item : resultProgram){
			Boolean isExist = false;
			University tempUniversity = new University();
			for(University u : resultUniversity){
				if(item.getUniversity_id() == u.getId()){
					tempUniversity = u;
					isExist = true;
					break;
				}
			}
			if(!isExist){
				University uEntity = this.universityBiz.findUniversityById(item.getUniversity_id());
				uEntity.setNumProgram(1);
				resultUniversity.add(uEntity);
			}else {
				resultUniversity.remove(tempUniversity);
				tempUniversity.setNumProgram(tempUniversity.getNumProgram() + 1);
				resultUniversity.add(tempUniversity);
			}
		}
		
		if(this.getUniversityNum() > 0){
			int rankclass_id = 0;
			if(condition.getSpecialtyId() != null && condition.getSpecialtyId() > 0){
				Specialty spe = specialtyBiz.findById(condition.getSpecialtyId());
				if(spe.getDepth() == 1){
					rankclass_id = spe.getRankclass_id() == null ? 0 : spe.getRankclass_id();
				}
				if(spe.getDepth() == 2){
					spe = specialtyBiz.findById(spe.getParent_id());
					rankclass_id = spe.getRankclass_id() == null ? 0 : spe.getRankclass_id();
				}
			}
			if(rankclass_id > 0){
				SpecialtyRank con = new SpecialtyRank();
				con.setRankclass_id(rankclass_id);
				resultSpecialtyRank = specialtyRankBiz.searchSpecialtyRanksByCondition(con);
			}
		}
		
		return SUCCESS;
	}
	
	private String searchAndReturn() throws Exception{
		Program cProgram = new Program();
		cProgram.setCountryId(getCountryId());
		cProgram.setStudy_level_id(getStudyLevelId());
		if(program_specialty != null)
			program_specialty = StringEncode.ToUTF8(program_specialty, true);
		cProgram.setSpecialtyName(program_specialty);
		if(program_specialty_id != null && program_specialty_id > 0){
			cProgram.setSpecialtyId(program_specialty_id);
			program_specialty = specialtyBiz.findById(program_specialty_id).getSpecialty_name();
		}
		if(university_name != null && !university_name.equals("")){
			university_name = StringEncode.ToUTF8(university_name, true);
			cProgram.setUniversityName(university_name);
		}
		if(university_name_id != null && university_name_id > 0){
			cProgram.setUniversity_id(getUniversity_name_id());
			university_name = universityBiz.findUniversityById(university_name_id).getName();
		}
		
		if(ranking != null){
			ranking = StringEncode.ToUTF8(ranking, true);
			if(!ranking.equalsIgnoreCase("全部")){
				String[] rankings = ranking.split("-");
				cProgram.setRankingBegin(Integer.parseInt(rankings[0]));
				if(rankings.length >= 2)
					cProgram.setRankingEnd(Integer.parseInt(rankings[1]));
			}
		}
		if(area != null){
			area = StringEncode.ToUTF8(area, true);
			if(!area.equalsIgnoreCase("全部")){
				cProgram.setAreaName(area);
			}
		}
		if(is_public_school != null){
//			if(!is_public_school.equalsIgnoreCase("-1")){
//				cProgram.setIs.setIs_language_score(Integer.parseInt(is_public_school));
//			}
		}
		cProgram.setScore_totef(-1);
		if(totef != null){
			if(!totef.equalsIgnoreCase("-1")){
				String[] totefs = totef.split("~");
				cProgram.setScore_totef(Float.parseFloat(totefs[0]));
				if(totefs.length >= 2)
					cProgram.setTotefEnd(Float.parseFloat(totefs[1]));
			}
		}
		cProgram.setScore_ietls(-1);
		if(ietls != null){
			if(!ietls.equalsIgnoreCase("-1")){
				String[] ietlss = ietls.split("~");
				cProgram.setScore_ietls(Float.parseFloat(ietlss[0]));
				if(ietlss.length >= 2)
					cProgram.setIetlsEnd(Float.parseFloat(ietlss[1]));
			}
		}
		cProgram.setScore_gre(-1);
		if(gre != null){
			if(!gre.equalsIgnoreCase("-1")){
				String[] gres = gre.split("~");
				cProgram.setScore_gre(Float.parseFloat(gres[0]));
				if(gres.length >= 2)
					cProgram.setGreEnd(Float.parseFloat(gres[1]));
			}
		}
		cProgram.setScore_gmat(-1);
		if(gmat != null){
			if(!gmat.equalsIgnoreCase("-1")){
				String[] gmats = gmat.split("~");
				cProgram.setScore_gmat(Float.parseFloat(gmats[0]));
				if(gmats.length >= 2)
					cProgram.setGmatEnd(Float.parseFloat(gmats[1]));
			}
		}
		if(is_language_score != null){
			if(!is_language_score.equalsIgnoreCase("-1")){
				cProgram.setIs_language_score(Integer.parseInt(is_language_score));
			}
		}
		if(time_of_enrollment != null){
			time_of_enrollment = java.net.URLDecoder.decode(time_of_enrollment, "UTF-8");
			if(!time_of_enrollment.equalsIgnoreCase("全部")){
				cProgram.setTime_of_enrollment(time_of_enrollment);
			}
		}
		if(work_experience_require != null){
			if(!work_experience_require.equalsIgnoreCase("-1")){
				cProgram.setWork_experience_require(Integer.parseInt(work_experience_require));
			}
		}
		
		System.out.println(countryId + "--------" + studyLevelId + "---------" + program_specialty);
		
		countryList = this.countryBiz.findAll();
		studyLevelList = this.studyLevelBiz.findAll();
		
		scoreList = this.scoreBiz.findAll();
		for(Score score: scoreList){
			if(score.getScopeLower() == 0 || score.getScopeLower() > 10)
				score.setScope_lower_int((int)score.getScopeLower());
			if(score.getScopeHigher() > 10)
				score.setScope_higher_int((int)score.getScopeHigher());
		}
		
		if(getStudyLevelId() >= 1)
			setStudyLevelName(this.studyLevelBiz.findStudyLevelById(getStudyLevelId()).getName());
		if(getCountryId() >= 1){
			setNationId(getCountryId());
			setCountryName(this.countryBiz.findCountryById(getCountryId()).getName());
		}
		
		cProgram.setPage_size(condition.getPage_size() <= 0 ? 10 : condition.getPage_size());
		cProgram.setPage(condition.getPage() == 0 ? 1 : condition.getPage());
		
		resultProgram = this.programBiz.searchProgramsByCondition(cProgram);
		cProgram.setIsSearchTotal(true);
		String numString = this.programBiz.searchProgramsRecordByCondition(cProgram);
		this.setProgramNum( Integer.valueOf(numString.split(",")[0]) );
		this.setUniversityNum( Integer.valueOf(numString.split(",")[1]) );
		
		for(Program item : resultProgram){
			Boolean isExist = false;
			University tempUniversity = new University();
			for(University u : resultUniversity){
				if(item.getUniversity_id() == u.getId()){
					tempUniversity = u;
					isExist = true;
					break;
				}
			}
			if(!isExist){
				University uEntity = this.universityBiz.findUniversityById(item.getUniversity_id());
				uEntity.setNumProgram(1);
				resultUniversity.add(uEntity);
			}else {
				resultUniversity.remove(tempUniversity);
				tempUniversity.setNumProgram(tempUniversity.getNumProgram() + 1);
				resultUniversity.add(tempUniversity);
			}
				
		}
		
		if(this.getUniversityNum() > 0){
			int rankclass_id = 0;
			if(program_specialty_id != null && program_specialty_id > 0){
				Specialty spe = specialtyBiz.findById(program_specialty_id);
				if(spe.getDepth() == 1){
					rankclass_id = spe.getRankclass_id() == null ? 0 : spe.getRankclass_id();
				}
				if(spe.getDepth() == 2){
					spe = specialtyBiz.findById(spe.getParent_id());
					rankclass_id = spe.getRankclass_id() == null ? 0 : spe.getRankclass_id();
				}
			}
			if(rankclass_id > 0){
				SpecialtyRank con = new SpecialtyRank();
				con.setRankclass_id(rankclass_id);
				resultSpecialtyRank = specialtyRankBiz.searchSpecialtyRanksByCondition(con);
			}
		}
		
		return SUCCESS;
	}
	
	public void sendMsg(String content) throws IOException{      
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(content);
    }
	
	//----------------------------- attributes get set

	public String getJson() {  
        return json;  
    }  
    public void setJson(String json) {  
        this.json = json;  
    }
	public String getResult() {  
        return result;  
    }  
    public void setResult(String result) {  
        this.result = result;  
    }
    
	public List<Program> getResultProgram() {
		return resultProgram;
	}

	public void setResultProgram(List<Program> resultProgram) {
		this.resultProgram = resultProgram;
	}

	public List<University> getResultUniversity() {
		return resultUniversity;
	}

	public void setResultUniversity(List<University> resultUniversity) {
		this.resultUniversity = resultUniversity;
	}
	
	public int getCountryId(){
		return countryId;
	}
	public void setCountryId(int countryId){
		this.countryId = countryId;
	}
	public String getCountryName(){
		return countryName;
	}
	public void setCountryName(String countryName){
		this.countryName = countryName;
	}
	
	public int getStudyLevelId(){
		return studyLevelId;
	}
	public void setStudyLevelId(int studyLevelId){
		this.studyLevelId = studyLevelId;
	}
	public void setStudyLevelName(String studyLevelName){
		this.studyLevelName = studyLevelName;
	}
	public String getStudyLevelName(){
		return studyLevelName;
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
	
	//--------------------------- Dic List -----------------------------
	//国家
	public Integer getNationId(){
		return nationId;
	}
	public void setNationId(Integer nationId){
		this.nationId = nationId;
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
	//学费
	//10000-20000 20001-30000 30001-40000
	//分数
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public List<Score> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}
	//------------------ condition 
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

	public String getTotef(){
		return totef;
	}
	public void setTotef(String totef){
		this.totef = totef;
	}
	public String getIetls(){
		return ietls;
	}
	public void setIetls(String ietls){
		this.ietls = ietls;
	}
	public String getGre(){
		return gre;
	}
	public void setGre(String gre){
		this.gre = gre;
	}
	public String getGmat(){
		return gmat;
	}
	public void setGmat(String gmat){
		this.gmat = gmat;
	}
	public String getIs_language_score(){
		return is_language_score;
	}
	public void setIs_language_score(String is_language_score){
		this.is_language_score = is_language_score;
	}
	public String getTime_of_enrollment(){
		return time_of_enrollment;
	}
	public void setTime_of_enrollment(String time_of_enrollment){
		this.time_of_enrollment = time_of_enrollment;
	}
	public String getWork_experience_require(){
		return work_experience_require;
	}
	public void setWork_experience_require(String work_experience_require){
		this.work_experience_require = work_experience_require;
	}

	public Integer getProgram_specialty_id() {
		return program_specialty_id;
	}

	public void setProgram_specialty_id(Integer program_specialty_id) {
		this.program_specialty_id = program_specialty_id;
	}

	public String getProgram_specialty() {
		return program_specialty;
	}

	public void setProgram_specialty(String program_specialty) {
		this.program_specialty = program_specialty;
	}

	public String getUniversity_name() {
		return university_name;
	}

	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}

	public Program getCondition() {
		return condition;
	}

	public void setCondition(Program condition) {
		this.condition = condition;
	}

	public Integer getUniversity_name_id() {
		return university_name_id;
	}

	public void setUniversity_name_id(Integer university_name_id) {
		this.university_name_id = university_name_id;
	}

	public List<SpecialtyRank> getResultSpecialtyRank() {
		return resultSpecialtyRank;
	}

	public void setResultSpecialtyRank(List<SpecialtyRank> resultSpecialtyRank) {
		this.resultSpecialtyRank = resultSpecialtyRank;
	}

	public String getIs_public_school() {
		return is_public_school;
	}

	public void setIs_public_school(String is_public_school) {
		this.is_public_school = is_public_school;
	}
	
	//------------------------------------------------------------------------
}
