package net.lx.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

@Entity
@Table(name = "tb_e_user_extend")
public class UserExtend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8609968120040529766L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "user_id")  					//主建ID
	private Integer user_id;
	@Column(name="user_type")
	private Integer user_type;
	@Column(name="graduate_date")
	private String graduate_date;
	@Column(name="study_level_id")
	private Integer study_level_id;
	@Column(name="country_id")
	private Integer country_id;
	@Column(name="university_id")
	private Integer university_id;
	@Column(name="specialty_id")
	private Integer specialty_id;
	
	//-------------------------------
	@Transient
	transient private StudyLevel study_level;
	public void setStudy_level(StudyLevel study_level){
		this.study_level = study_level;
	}
	public StudyLevel getStudy_level(){
		return study_level;
	}
	@Transient
	transient private Country country;
	public void setCountry(Country country){
		this.country = country;
	}
	public Country getCountry(){
		return country;
	}
	@Transient
	transient private University university;
	public void setUniversity(University university){
		this.university = university;
	}
	public University getUniversity(){
		return university;
	}
	@Transient
	transient private Specialty specialty;
	public void setSpecialty(Specialty specialty){
		this.specialty = specialty;
	}
	public Specialty getSpecialty(){
		return specialty;
	}

	//-------------------------------
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setGraduate_date(String graduate_date){
		this.graduate_date=graduate_date;
	}

	public String getGraduate_date(){
		return graduate_date;
	}

	public void setStudy_level_id(Integer study_level_id){
		this.study_level_id=study_level_id;
	}

	public Integer getStudy_level_id(){
		return study_level_id;
	}

	public void setCountry_id(Integer country_id){
		this.country_id=country_id;
	}

	public Integer getCountry_id(){
		return country_id;
	}

	public Integer getUniversity_id() {
		return university_id;
	}

	public void setUniversity_id(Integer university_id) {
		this.university_id = university_id;
	}

	public void setSpecialty_id(Integer specialty_id){
		this.specialty_id=specialty_id;
	}

	public Integer getSpecialty_id(){
		return specialty_id;
	}

}
