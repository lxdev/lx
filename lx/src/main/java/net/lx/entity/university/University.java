package net.lx.entity.university;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.common.hibernate.SortChineseAnnotation;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Country;
import net.lx.entity.evaluate.Evaluate;

@Entity
@Table(name = "tb_e_university")
public class University implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274276798115981368L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="university_name")
	@SortChineseAnnotation(sort = true)
	private String university_name;
	@Column(name="english_name")
	private String english_name;
	@Column(name="abbr")
	private String abbr;
	@Column(name="university_desc")
	private String university_desc;
	@Column(name="country_id")
	private Integer country_id;
	@Column(name="area_id")
	private Integer area_id;
	@Column(name="ranking_comprehensive")
	private Integer ranking_comprehensive;
	@Column(name="establishing")
	private String establishing;
	@Column(name="is_public_school")
	private Integer is_public_school;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="website")
	private String website;
	@Column(name="scale")
	private String scale;
	@Column(name="num_undergraduate")
	private Integer num_undergraduate;
	@Column(name="num_graduate")
	private Integer num_graduate;
	@Column(name="rate_enrollment")
	private String rate_enrollment;
	@Column(name="rate_scholarship")
	private String rate_scholarship;
	@Column(name="rate_boy")
	private String rate_boy;
	@Column(name="rate_girl")
	private String rate_girl;
	@Column(name="rate_international")
	private String rate_international;
	@Column(name="num_professor")
	private Integer num_professor;
	@Column(name="rate_professor")
	private String rate_professor;
	@Column(name="rate_student")
	private String rate_student;
//	@Column(name="is_enable")
//	private Integer is_enable;
	@Column(name="status")
	private Integer status;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="creator_id")
	private Integer creator_id;
	@Column(name="other")
	private String other;
	@Column(name="other2")
	private String other2;
	@Column(name="logo_url")
	private String logo_url;

	//-----------------------------------
	
	@Transient
	private Integer numProgram;
	public Integer getNumProgram() {
		return numProgram;
	}
	public void setNumProgram(Integer numProgram) {
		this.numProgram = numProgram;
	}
	
	@Transient
	private Country country;
	public Country getCountry(){
		return country;
	}
	public void setCountry(Country country){
		this.country = country;
	}

	@Transient
	private Area area;
	public Area getArea(){
		return area;
	}
	public void setArea(Area area){
		this.area = area;
	}

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
	private String orderBy;
	public String getOrderBy(){
		return orderBy;
	}
	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}
	// ASC / DESC
	@Transient
	private String order;
	public String getOrder(){
		return order;
	}
	public void setOrder(String order){
		this.order = order;
	}

	@Transient
	private List<Evaluate> evaluateList;
	public List<Evaluate> getEvaluateList() {
		return evaluateList;
	}
	public void setEvaluateList(List<Evaluate> evaluateList) {
		this.evaluateList = evaluateList;
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
	
	@Transient
	private String name;	//用于下拉中的 auto-complete
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	//-------------------------
	
	
	
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setUniversity_name(String university_name){
		this.university_name=university_name;
	}

	public String getUniversity_name(){
		return university_name;
	}

	public void setEnglish_name(String english_name){
		this.english_name=english_name;
	}

	public String getEnglish_name(){
		return english_name;
	}

	public void setAbbr(String abbr){
		this.abbr=abbr;
	}

	public String getAbbr(){
		return abbr;
	}

	public void setUniversity_desc(String universityDesc){
		this.university_desc=universityDesc;
	}

	public String getUniversity_desc(){
		return university_desc;
	}

	public void setCountry_id(Integer countryId){
		this.country_id=countryId;
	}

	public Integer getCountry_id(){
		return country_id;
	}

	public void setArea_id(Integer areaId){
		this.area_id=areaId;
	}

	public Integer getArea_id(){
		return area_id;
	}

	public void setRanking_comprehensive(Integer rankingComprehensive){
		this.ranking_comprehensive=rankingComprehensive;
	}

	public Integer getRanking_comprehensive(){
		return ranking_comprehensive;
	}

	public void setEstablishing(String establishing){
		this.establishing=establishing;
	}

	public String getEstablishing(){
		return establishing;
	}

	public void setIs_public_school(Integer isPublicSchool){
		this.is_public_school=isPublicSchool;
	}

	public Integer getIs_public_school(){
		return is_public_school;
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

	public void setWebsite(String website){
		this.website=website;
	}

	public String getWebsite(){
		return website;
	}

	public void setScale(String scale){
		this.scale=scale;
	}

	public String getScale(){
		return scale;
	}

	public void setNum_undergraduate(Integer numUndergraduate){
		this.num_undergraduate=numUndergraduate;
	}

	public Integer getNum_undergraduate(){
		return num_undergraduate;
	}

	public void setNum_graduate(Integer numGraduate){
		this.num_graduate=numGraduate;
	}

	public Integer getNum_graduate(){
		return num_graduate;
	}

	public void setRate_enrollment(String rateEnrollment){
		this.rate_enrollment=rateEnrollment;
	}

	public String getRate_enrollment(){
		return rate_enrollment;
	}

	public void setRate_scholarship(String rateScholarship){
		this.rate_scholarship=rateScholarship;
	}

	public String getRate_scholarship(){
		return rate_scholarship;
	}

	public void setRate_boy(String rateBoy){
		this.rate_boy=rateBoy;
	}

	public String getRate_boy(){
		return rate_boy;
	}

	public void setRate_girl(String rateGirl){
		this.rate_girl=rateGirl;
	}

	public String getRate_girl(){
		return rate_girl;
	}

	public void setRate_international(String rateInternational){
		this.rate_international=rateInternational;
	}

	public String getRate_international(){
		return rate_international;
	}

	public void setNum_professor(Integer numProfessor){
		this.num_professor=numProfessor;
	}

	public Integer getNum_professor(){
		return num_professor;
	}

	public void setRate_professor(String rateProfessor){
		this.rate_professor=rateProfessor;
	}

	public String getRate_professor(){
		return rate_professor;
	}

	public void setRate_student(String rateStudent){
		this.rate_student=rateStudent;
	}

	public String getRate_student(){
		return rate_student;
	}

//	public void setIs_enable(Integer isEnable){
//		this.is_enable=isEnable;
//	}
//
//	public Integer getIs_enable(){
//		return is_enable;
//	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCreated_date(Date createdDate){
		this.created_date=createdDate;
	}

	public Date getCreated_date(){
		return created_date;
	}

	public void setCreator_id(Integer creatorId){
		this.creator_id=creatorId;
	}

	public Integer getCreator_id(){
		return creator_id;
	}

	public void setOther(String other){
		this.other=other;
	}

	public String getOther(){
		return other;
	}

	public void setOther2(String other2){
		this.other2=other2;
	}

	public String getOther2(){
		return other2;
	}

	public void setLogo_url(String logoUrl){
		this.logo_url=logoUrl;
	}

	public String getLogo_url(){
		return logo_url;
	}

}
