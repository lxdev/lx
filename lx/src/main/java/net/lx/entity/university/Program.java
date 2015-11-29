package net.lx.entity.university;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.common.file.excel.ExcelAnnotation;
import net.lx.entity.dic.StudyLevel;

@Entity
@Table(name = "tb_e_program")
public class Program implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4172091006457013108L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private Integer id;
	
	@Column(name="program_name")
	@ExcelAnnotation(exportName = "课程名")
	private String program_name;
	
	@Column(name="program_desc")
	private String program_desc;
	
	@Column(name="university_id")
	private Integer university_id;
	
	@Column(name="college_id")
	private Integer college_id;
	@Column(name="study_level_id")
	private Integer study_level_id;
	
	@Column(name="teach_way")
	@ExcelAnnotation(exportName = "授课方式")
	private String teach_way;
	
	@Column(name="length_of_schooling")
	@ExcelAnnotation(exportName = "学制")
	private String length_of_schooling;
	
	@Column(name="length_of_schooling_desc")
	@ExcelAnnotation(exportName = "学制描述")
	private String length_of_schooling_desc;
	
	@Column(name="tuition")
	@ExcelAnnotation(exportName = "学费（最低）")
	private Integer tuition;
	
	@Column(name="tuition_top")
	@ExcelAnnotation(exportName = "学费（最高）")
	private Integer tuition_top;
	
	@Column(name="scholarship_desc")
	@ExcelAnnotation(exportName = "奖学金描述")
	private String scholarship_desc;
	
	@Column(name="time_of_enrollment")
	@ExcelAnnotation(exportName = "入学时间")
	private String time_of_enrollment;
	
	@Column(name="address")
	@ExcelAnnotation(exportName = "地址")
	private String address;
	
	@Column(name="phone")
	@ExcelAnnotation(exportName = "电话")
	private String phone;
	
	@Column(name="email")
	@ExcelAnnotation(exportName = "邮箱")
	private String email;
	
	@Column(name="is_language_score")
	@ExcelAnnotation(exportName = "是否提供语言课程")
	private Integer is_language_score;
	
	@Column(name="is_language_score_desc")
	@ExcelAnnotation(exportName = "是否提供语言课程描述")
	private String is_language_score_desc;

	@Column(name="score_totef")
	@ExcelAnnotation(exportName = "托福总分")
	private int score_totef;
	
	@Column(name="score_ietls")
	@ExcelAnnotation(exportName = "雅思总分")
	private float score_ietls;
	
	@Column(name="score_gre")
	@ExcelAnnotation(exportName = "GRE总分")
	private int score_gre;
	
	@Column(name="score_gmat")
	@ExcelAnnotation(exportName = "GMAT总分")
	private int score_gmat;
	
	@Column(name="score_gpa")
	@ExcelAnnotation(exportName = "GPA要求")
	private float score_gpa;
	
	@Column(name="totef_single")
	@ExcelAnnotation(exportName = "托福单项")
	private String totef_single;
	
	@Column(name="totef_desc")
	@ExcelAnnotation(exportName = "托福描述")
	private String totef_desc;
	
	@Column(name="ietls_single")
	@ExcelAnnotation(exportName = "雅思单项")
	private String ietls_single;
	
	@Column(name="ietls_desc")
	@ExcelAnnotation(exportName = "雅思描述")
	private String ietls_desc;
	
	@Column(name="gre_single")
	@ExcelAnnotation(exportName = "GRE单项")
	private String gre_single;
	
	@Column(name="gre_desc")
	@ExcelAnnotation(exportName = "GRE描述")
	private String gre_desc;
	
	@Column(name="gmat_single")
	@ExcelAnnotation(exportName = "GMAT单项")
	private String gmat_single;
	
	@Column(name="gmat_desc")
	@ExcelAnnotation(exportName = "GMAT描述")
	private String gmat_desc;
	
//	@Column(name="gpa_single")
//	private String gpa_single;
	@Column(name="gpa_desc")
	@ExcelAnnotation(exportName = "GPA描述")
	private String gpa_desc;
	
	@Column(name="gre_sub")
	@ExcelAnnotation(exportName = "GRESub")
	private String gre_sub;
	
	@Column(name="gre_sub_desc")
	@ExcelAnnotation(exportName = "GRESub描述")
	private String gre_sub_desc;
	
	@Column(name="lsat")
	@ExcelAnnotation(exportName = "LSAT")
	private String lsat;
	
	@Column(name="course_setting")
	@ExcelAnnotation(exportName = "课程设置")
	private String course_setting;
	
	@Column(name="specialty_require")
	@ExcelAnnotation(exportName = "专业要求")
	private Integer specialty_require;
	
	@Column(name="specialty_require_desc")
	@ExcelAnnotation(exportName = "专业要求描述")
	private String specialty_require_desc;
	
	@Column(name="work_experience_require")
	@ExcelAnnotation(exportName = "工作经验要求")
	private Integer work_experience_require;
	
	@Column(name="work_experience_desc")
	@ExcelAnnotation(exportName = "工作经验要求描述")
	private String work_experience_desc;
	
//	@Column(name="time_of_enrollment_end")
//	private String time_of_enrollment_end;
	@Column(name="time_of_autumn_end")
	@ExcelAnnotation(exportName = "秋季入学截止日期")
	private String time_of_autumn_end;
	
	@Column(name="time_of_spring_end")
	@ExcelAnnotation(exportName = "春季入学截止日期")
	private String time_of_spring_end;
	
	@Column(name="time_of_summer_end")
	@ExcelAnnotation(exportName = "夏季入学截止日期")
	private String time_of_summer_end;
	
	@Column(name="time_of_winter_end")
	@ExcelAnnotation(exportName = "冬季入学截止日期")
	private String time_of_winter_end;
	
	@Column(name="student_profile")
	@ExcelAnnotation(exportName = "studentprofile")
	private String student_profile;
	
	@Column(name="specialty_link")
	@ExcelAnnotation(exportName = "专业链接")
	private String specialty_link;
	
	@Column(name="apply_link")
	@ExcelAnnotation(exportName = "申请链接")
	private String apply_link;
	
	@Column(name="logo_url")
	private String logo_url;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="enroll_require")
	private String enroll_require;
	
	@Column(name="official_websie")
	private String official_websie;
	
	@Column(name="status")
	private Integer status;

	//-----------------------------------------------------

	// Excel序号
	transient private String serialNumber;// 序号
	
	@Transient
	transient private University university;
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	@ExcelAnnotation(exportName = "学校名")
	@Transient
	transient private String universityName;
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	@Transient
	transient private Integer countryId;
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@ExcelAnnotation(exportName = "专业")
	@Transient
	transient private String specialtyName;
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	@Transient
	transient private Integer specialtyId;
	public Integer getSpecialtyId(){
		return specialtyId;
	}
	public void setSpecialtyId(Integer specialtyId){
		this.specialtyId = specialtyId;
	}

	@Transient
	transient private Specialty specialty;
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	
	@Transient
	private StudyLevel studyLevel;
	public StudyLevel getStudyLevel(){
		return studyLevel;
	}
	public void setStudyLevel(StudyLevel studyLevel){
		this.studyLevel = studyLevel;
	}
	@ExcelAnnotation(exportName = "学位等级")
	@Transient
	transient private String studyLevelName;
	public String getStudyLevelName() {
		return studyLevelName;
	}
	public void setStudyLevelName(String studyLevelName) {
		this.studyLevelName = studyLevelName;
	}
	
//		@Transient
//		private College college;
//		public College getCollege(){
//			return college;
//		}
//		public void setCollege(College college){
//			this.college = college;
//		}
	
	@Transient
	private Integer rankingBegin;
	public void setRankingBegin(Integer rankingBegin){
		this.rankingBegin=rankingBegin;
	}
	public Integer getRankingBegin(){
		return rankingBegin;
	}
	@Transient
	private Integer rankingEnd;
	public void setRankingEnd(Integer rankingEnd){
		this.rankingEnd=rankingEnd;
	}
	public Integer getRankingEnd(){
		return rankingEnd;
	}

	@Transient
	private String areaName;
	public String getAreaName(){
		return areaName;
	}
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	@Transient
	private Integer is_public_school;
	public Integer getIs_public_school(){
		return is_public_school;
	}
	public void setIs_public_school(Integer is_public_school){ this.is_public_school = is_public_school; }

	@Transient
	private float totefEnd;
	public void setTotefEnd(float totefEnd){
		this.totefEnd=totefEnd;
	}
	public float getTotefEnd(){
		return totefEnd;
	}
	@Transient
	private float ietlsEnd;
	public void setIetlsEnd(float ietlsEnd){
		this.ietlsEnd=ietlsEnd;
	}
	public float getIetlsEnd(){
		return ietlsEnd;
	}
	@Transient
	private float greEnd;
	public void setGreEnd(float greEnd){
		this.greEnd=greEnd;
	}
	public float getGreEnd(){
		return greEnd;
	}
	@Transient
	private float gmatEnd;
	public void setGmatEnd(float gmatEnd){
		this.gmatEnd=gmatEnd;
	}
	public float getGmatEnd(){
		return gmatEnd;
	}

	@Transient
	private String orderBy;
	public String getOrderBy(){
		return orderBy;
	}
	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}


	@Transient
	private int evaluate_number;		//评价数
	public int getEvaluate_number(){
		return evaluate_number;
	}
	public void setEvaluate_number(int evaluate_number){
		this.evaluate_number = evaluate_number;
	}

	@Transient
	private int browse_number;		//浏览数
	public int getBrowse_number(){
		return browse_number;
	}
	public void setBrowse_number(int browse_number){
		this.browse_number = browse_number;
	}


	@Transient
	private int page;
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page = page;
	}
	@Transient
	private int page_size;
	public int getPage_size(){
		return page_size;
	}
	public void setPage_size(int page_size){
		this.page_size = page_size;
	}
	
	@Transient
	private Boolean isSearchTotal;		//true 则查询符合条件的总记录条数
	public Boolean getIsSearchTotal(){
		return isSearchTotal;
	}
	public void setIsSearchTotal(Boolean isSearchTotal){
		this.isSearchTotal = isSearchTotal;
	}
	
	//------------------------------ Transient End ----------
	
	
	
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setProgram_name(String program_name){
		this.program_name=program_name;
	}

	public String getProgram_name(){
		return program_name;
	}

	public void setProgram_desc(String program_desc){
		this.program_desc=program_desc;
	}

	public String getProgram_desc(){
		return program_desc;
	}

	public void setUniversity_id(Integer university_id){
		this.university_id=university_id;
	}

	public Integer getUniversity_id(){
		return university_id;
	}

	public void setCollege_id(Integer college_id){
		this.college_id=college_id;
	}

	public Integer getCollege_id(){
		return college_id;
	}

//	public void setSpecialty_id(Integer specialty_id){
//		this.specialty_id=specialty_id;
//	}
//
//	public Integer getSpecialty_id(){
//		return specialty_id;
//	}
//
//	public void setSpecialty_desc(String specialty_desc){
//		this.specialty_desc=specialty_desc;
//	}
//
//	public String getSpecialty_desc(){
//		return specialty_desc;
//	}

	public void setStudy_level_id(Integer study_level_id){
		this.study_level_id=study_level_id;
	}

	public Integer getStudy_level_id(){
		return study_level_id;
	}

//	public void setRanking_specialty(Integer ranking_specialty){
//		this.ranking_specialty=ranking_specialty;
//	}
//
//	public Integer getRanking_specialty(){
//		return ranking_specialty;
//	}

	public void setTeach_way(String teach_way){
		this.teach_way=teach_way;
	}

	public String getTeach_way(){
		return teach_way;
	}

	public void setLength_of_schooling(String length_of_schooling){
		this.length_of_schooling=length_of_schooling;
	}

	public String getLength_of_schooling(){
		return length_of_schooling;
	}

	public void setLength_of_schooling_desc(String length_of_schooling_desc){
		this.length_of_schooling_desc=length_of_schooling_desc;
	}

	public String getLength_of_schooling_desc(){
		return length_of_schooling_desc;
	}

	public void setTuition(Integer tuition){
		this.tuition=tuition;
	}

	public Integer getTuition(){
		return tuition;
	}

	public void setTuition_top(Integer tuition_top){
		this.tuition_top=tuition_top;
	}

	public Integer getTuition_top(){
		return tuition_top;
	}

	public void setTime_of_enrollment(String time_of_enrollment){
		this.time_of_enrollment=time_of_enrollment;
	}

	public String getTime_of_enrollment(){
		return time_of_enrollment;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return email;
	}

	public void setScore_totef(int score_totef){
		this.score_totef=score_totef;
	}

	public int getScore_totef(){
		return score_totef;
	}

	public void setScore_ietls(float score_ietls){
		this.score_ietls=score_ietls;
	}

	public float getScore_ietls(){
		return score_ietls;
	}

	public void setIs_language_score(Integer is_language_score){
		this.is_language_score=is_language_score;
	}

	public Integer getIs_language_score(){
		return is_language_score;
	}

	public void setIs_language_score_desc(String is_language_score_desc){
		this.is_language_score_desc=is_language_score_desc;
	}

	public String getIs_language_score_desc(){
		return is_language_score_desc;
	}

	public void setScore_gre(int score_gre){
		this.score_gre=score_gre;
	}

	public int getScore_gre(){
		return score_gre;
	}

	public void setScore_gmat(int score_gmat){
		this.score_gmat=score_gmat;
	}

	public int getScore_gmat(){
		return score_gmat;
	}

	public void setScore_gpa(float score_gpa){
		this.score_gpa=score_gpa;
	}

	public float getScore_gpa(){
		return score_gpa;
	}

	public void setTotef_single(String totef_single){
		this.totef_single=totef_single;
	}

	public String getTotef_single(){
		return totef_single;
	}

	public void setTotef_desc(String totef_desc){
		this.totef_desc=totef_desc;
	}

	public String getTotef_desc(){
		return totef_desc;
	}

	public void setIetls_single(String ietls_single){
		this.ietls_single=ietls_single;
	}

	public String getIetls_single(){
		return ietls_single;
	}

	public void setIetls_desc(String ietls_desc){
		this.ietls_desc=ietls_desc;
	}

	public String getIetls_desc(){
		return ietls_desc;
	}

	public void setGre_single(String gre_single){
		this.gre_single=gre_single;
	}

	public String getGre_single(){
		return gre_single;
	}

	public void setGre_desc(String gre_desc){
		this.gre_desc=gre_desc;
	}

	public String getGre_desc(){
		return gre_desc;
	}

	public void setGmat_single(String gmat_single){
		this.gmat_single=gmat_single;
	}

	public String getGmat_single(){
		return gmat_single;
	}

	public void setGmat_desc(String gmat_desc){
		this.gmat_desc=gmat_desc;
	}

	public String getGmat_desc(){
		return gmat_desc;
	}

//	public void setGpa_single(String gpa_single){
//		this.gpa_single=gpa_single;
//	}
//
//	public String getGpa_single(){
//		return gpa_single;
//	}

	public void setGpa_desc(String gpa_desc){
		this.gpa_desc=gpa_desc;
	}

	public String getGpa_desc(){
		return gpa_desc;
	}

	public void setCourse_setting(String course_setting){
		this.course_setting=course_setting;
	}

	public String getCourse_setting(){
		return course_setting;
	}

	public void setSpecialty_require(Integer specialty_require){
		this.specialty_require=specialty_require;
	}

	public Integer getSpecialty_require(){
		return specialty_require;
	}

	public void setSpecialty_require_desc(String specialty_require_desc){
		this.specialty_require_desc=specialty_require_desc;
	}

	public String getSpecialty_require_desc(){
		return specialty_require_desc;
	}

	public void setWork_experience_require(Integer work_experience_require){
		this.work_experience_require=work_experience_require;
	}

	public Integer getWork_experience_require(){
		return work_experience_require;
	}

	public void setWork_experience_desc(String work_experience_desc){
		this.work_experience_desc=work_experience_desc;
	}

	public String getWork_experience_desc(){
		return work_experience_desc;
	}

//	public void setTime_of_enrollment_end(String time_of_enrollment_end){
//		this.time_of_enrollment_end=time_of_enrollment_end;
//	}
//
//	public String getTime_of_enrollment_end(){
//		return time_of_enrollment_end;
//	}

	public void setStudent_profile(String student_profile){
		this.student_profile=student_profile;
	}

	public String getStudent_profile(){
		return student_profile;
	}

	public void setSpecialty_link(String specialty_link){
		this.specialty_link=specialty_link;
	}

	public String getSpecialty_link(){
		return specialty_link;
	}

	public void setApply_link(String apply_link){
		this.apply_link=apply_link;
	}

	public String getApply_link(){
		return apply_link;
	}

	public void setLogo_url(String logo_url){
		this.logo_url=logo_url;
	}

	public String getLogo_url(){
		return logo_url;
	}
	public String getScholarship_desc() {
		return scholarship_desc;
	}
	public void setScholarship_desc(String scholarship_desc) {
		this.scholarship_desc = scholarship_desc;
	}
	public String getGre_sub() {
		return gre_sub;
	}
	public void setGre_sub(String gre_sub) {
		this.gre_sub = gre_sub;
	}
	public String getGre_sub_desc() {
		return gre_sub_desc;
	}
	public void setGre_sub_desc(String gre_sub_desc) {
		this.gre_sub_desc = gre_sub_desc;
	}
	public String getLsat() {
		return lsat;
	}
	public void setLsat(String lsat) {
		this.lsat = lsat;
	}
	public String getTime_of_autumn_end() {
		return time_of_autumn_end;
	}
	public void setTime_of_autumn_end(String time_of_autumn_end) {
		this.time_of_autumn_end = time_of_autumn_end;
	}
	public String getTime_of_spring_end() {
		return time_of_spring_end;
	}
	public void setTime_of_spring_end(String time_of_spring_end) {
		this.time_of_spring_end = time_of_spring_end;
	}
	public String getTime_of_summer_end() {
		return time_of_summer_end;
	}
	public void setTime_of_summer_end(String time_of_summer_end) {
		this.time_of_summer_end = time_of_summer_end;
	}
	public String getTime_of_winter_end() {
		return time_of_winter_end;
	}
	public void setTime_of_winter_end(String time_of_winter_end) {
		this.time_of_winter_end = time_of_winter_end;
	}
	public String getEnroll_require() {
		return enroll_require;
	}
	public void setEnroll_require(String enroll_require) {
		this.enroll_require = enroll_require;
	}
	public String getOfficial_websie() {
		return official_websie;
	}
	public void setOfficial_websie(String official_websie) {
		this.official_websie = official_websie;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
