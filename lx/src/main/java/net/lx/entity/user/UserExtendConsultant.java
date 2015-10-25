package net.lx.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_e_user_extend_consultant")
public class UserExtendConsultant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9063953276794621418L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id")  					//主建ID
	private Integer user_id;
	@Column(name = "user_type")
	private Integer user_type;		//顾问类型：1 老师； 2 在校生； 3 其他
	@Column(name = "introduce")
	private String introduce;		//一句话简介
	@Column(name = "personal_homepage")
	private String personal_homepage;	//个人主页地址
	@Column(name = "university_name")
	private String university_name;		//毕业院校
	@Column(name = "specialty_name")
	private String specialty_name;		//学所专业
	@Column(name = "teach_year")
	private Integer teach_year;			//教龄
	@Column(name = "teach_feature")
	private String teach_feature;		//教学特点
	@Column(name = "experience")
	private String experience;		//过往经历
	@Column(name = "cases")
	private String cases;			//案例
	@Column(name = "self_info")
	private String self_info;		//个人生活信息
	@Column(name = "self_photos")
	private String self_photos;		//照片id，按逗号分隔
	@Column(name = "description")
	private String description;		//更多详细描述
	@Column(name = "auth")
	private Integer auth;			//0 未认证；1 已认证通过； 2 不通过。    认证后方可在前端显示
	@Column(name = "auth_number")
	private String auth_number;		//证件号码 身份证护照号
	@Column(name = "auth_photo")
	private String auth_photo;		//手持身份证照片
	@Column(name = "auth_graduate")
	private String auth_graduate;	//毕业证照片的id，按逗号分隔
	@Column(name = "auth_teacher")
	private String auth_teacher;	//认证教师照片ids
	@Column(name = "auth_exam")
	private String auth_exam;		//认证考级证书ids
	@Column(name = "auth_offer")
	private String auth_offer;		//认证学生录取offer照片ids
	@Column(name = "goodat_countrys")
	private String goodat_countrys;			//擅长的国家不超过三个  id 按逗号分隔
	@Column(name = "goodat_specialtys")
	private String goodat_specialtys;		//擅长的专业不超过三个  id 按逗号分隔
	@Column(name = "service_type")
	private Integer service_type;			//1 全程服务/ 2 Diy辅导/ 3 WORK SHOP/ 4 单项服务
	@Column(name = "service_type_single")
	private Integer service_type_single;	//对于单项服务：41 文书写作/ 42 选校服务/ 43 面试辅导/ 44 签证服务
	@Column(name = "service_price")
	private String service_price;		//服务费
	@Column(name = "service_desc")
	private String service_desc;		//服务描述
	@Column(name = "address_province")
	private String address_province;	//省
	@Column(name = "address_city")
	private String address_city;		//市
	@Column(name = "address_district")
	private String address_district;	//区
	@Column(name = "address_detail")
	private String address_detail;		//址地详细信息
	@Column(name = "created_date")
	private Date created_date;
	@Column(name = "created_user_id")
	private Integer created_user_id;
	
	//-------------
	@Transient
	transient private User user;
	@Transient
	transient private int conCountryId;
	@Transient
	transient private int conSpecialtyId;
	@Transient
	transient private int conStudyLevelId;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getConCountryId() {
		return conCountryId;
	}

	public void setConCountryId(int conCountryId) {
		this.conCountryId = conCountryId;
	}

	public int getConSpecialtyId() {
		return conSpecialtyId;
	}

	public void setConSpecialtyId(int conSpecialtyId) {
		this.conSpecialtyId = conSpecialtyId;
	}

	public int getConStudyLevelId() {
		return conStudyLevelId;
	}

	public void setConStudyLevelId(int conStudyLevelId) {
		this.conStudyLevelId = conStudyLevelId;
	}

	//-------------
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}

	public Integer getUser_id(){
		return user_id;
	}

	public void setUser_type(Integer user_type){
		this.user_type=user_type;
	}

	public Integer getUser_type(){
		return user_type;
	}

	public void setIntroduce(String introduce){
		this.introduce=introduce;
	}

	public String getIntroduce(){
		return introduce;
	}

	public void setPersonal_homepage(String personal_homepage){
		this.personal_homepage=personal_homepage;
	}

	public String getPersonal_homepage(){
		return personal_homepage;
	}

	public void setUniversity_name(String university_name){
		this.university_name=university_name;
	}

	public String getUniversity_name(){
		return university_name;
	}

	public void setSpecialty_name(String specialty_name){
		this.specialty_name=specialty_name;
	}

	public String getSpecialty_name(){
		return specialty_name;
	}

	public void setTeach_year(Integer teach_year){
		this.teach_year=teach_year;
	}

	public Integer getTeach_year(){
		return teach_year;
	}

	public void setTeach_feature(String teach_feature){
		this.teach_feature=teach_feature;
	}

	public String getTeach_feature(){
		return teach_feature;
	}

	public void setExperience(String experience){
		this.experience=experience;
	}

	public String getExperience(){
		return experience;
	}

	public void setCases(String cases){
		this.cases=cases;
	}

	public String getCases(){
		return cases;
	}

	public void setSelf_info(String self_info){
		this.self_info=self_info;
	}

	public String getSelf_info(){
		return self_info;
	}

	public void setSelf_photos(String self_photos){
		this.self_photos=self_photos;
	}

	public String getSelf_photos(){
		return self_photos;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public String getDescription(){
		return description;
	}

	public void setAuth(Integer auth){
		this.auth=auth;
	}

	public Integer getAuth(){
		return auth;
	}

	public void setAuth_number(String auth_number){
		this.auth_number=auth_number;
	}

	public String getAuth_number(){
		return auth_number;
	}

	public void setAuth_photo(String auth_photo){
		this.auth_photo=auth_photo;
	}

	public String getAuth_photo(){
		return auth_photo;
	}

	public void setAuth_graduate(String auth_graduate){
		this.auth_graduate=auth_graduate;
	}

	public String getAuth_graduate(){
		return auth_graduate;
	}

	public void setAuth_teacher(String auth_teacher){
		this.auth_teacher=auth_teacher;
	}

	public String getAuth_teacher(){
		return auth_teacher;
	}

	public void setAuth_exam(String auth_exam){
		this.auth_exam=auth_exam;
	}

	public String getAuth_exam(){
		return auth_exam;
	}

	public void setAuth_offer(String auth_offer){
		this.auth_offer=auth_offer;
	}

	public String getAuth_offer(){
		return auth_offer;
	}

	public void setGoodat_countrys(String goodat_countrys){
		this.goodat_countrys=goodat_countrys;
	}

	public String getGoodat_countrys(){
		return goodat_countrys;
	}

	public void setGoodat_specialtys(String goodat_specialtys){
		this.goodat_specialtys=goodat_specialtys;
	}

	public String getGoodat_specialtys(){
		return goodat_specialtys;
	}

	public void setService_type(Integer service_type){
		this.service_type=service_type;
	}

	public Integer getService_type(){
		return service_type;
	}

	public void setService_type_single(Integer service_type_single){
		this.service_type_single=service_type_single;
	}

	public Integer getService_type_single(){
		return service_type_single;
	}

	public void setService_price(String service_price){
		this.service_price=service_price;
	}

	public String getService_price(){
		return service_price;
	}

	public void setService_desc(String service_desc){
		this.service_desc=service_desc;
	}

	public String getService_desc(){
		return service_desc;
	}

	public void setAddress_province(String address_province){
		this.address_province=address_province;
	}

	public String getAddress_province(){
		return address_province;
	}

	public void setAddress_city(String address_city){
		this.address_city=address_city;
	}

	public String getAddress_city(){
		return address_city;
	}

	public void setAddress_district(String address_district){
		this.address_district=address_district;
	}

	public String getAddress_district(){
		return address_district;
	}

	public void setAddress_detail(String address_detail){
		this.address_detail=address_detail;
	}

	public String getAddress_detail(){
		return address_detail;
	}

	public void setCreated_date(Date created_date){
		this.created_date=created_date;
	}

	public Date getCreated_date(){
		return created_date;
	}

	public void setCreated_user_id(Integer created_user_id){
		this.created_user_id=created_user_id;
	}

	public Integer getCreated_user_id(){
		return created_user_id;
	}

}
