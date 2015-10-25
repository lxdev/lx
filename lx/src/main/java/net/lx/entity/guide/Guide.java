package net.lx.entity.guide;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.common.hibernate.SortChineseAnnotation;
import net.lx.entity.dic.Country;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

@Entity
@Table(name = "tb_e_guide")
public class Guide implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -102504324447067834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guide_id")  					//主建ID
	private Integer guide_id;
	
	@Column(name="guide_name")
	@SortChineseAnnotation(sort = true)
	private String guide_name;
	@Column(name="country_id")
	private Integer country_id;
	@Column(name="specialty_id")
	private Integer specialty_id;
	@Column(name="is_self_specialty")
	private Integer is_self_specialty;
	
	//-----------------------
	/*向导所属国家*/
	@Transient
	transient private Country country;
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	/*向导所属系统专业*/
	@Transient
	transient private Specialty specialty;
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	/*向导自定义专业*/
	@Transient
	transient private GuideSpecialty guideSpecialty;
	public GuideSpecialty getGuideSpecialty() {
		return guideSpecialty;
	}
	public void setGuideSpecialty(GuideSpecialty guideSpecialty) {
		this.guideSpecialty = guideSpecialty;
	}
	/*向导选项内容*/
	@Transient
	transient private List<GuideOptionContent> optionContents;
	public List<GuideOptionContent> getOptionContents() {
		return optionContents;
	}
	public void setOptionContents(List<GuideOptionContent> optionContents) {
		this.optionContents = optionContents;
	}
	//-----------------------

	public void setGuide_id(Integer guideId){
		this.guide_id=guideId;
	}

	public int getGuide_id(){
		return guide_id;
	}

	public void setGuide_name(String guideName){
		this.guide_name=guideName;
	}
	public String getGuide_name(){
		return guide_name;
	}

	public void setCountry_id(Integer country_id){
		this.country_id=country_id;
	}
	public Integer getCountry_id(){
		return country_id;
	}
	
	public void setSpecialty_id(Integer specialty_id){
		this.specialty_id=specialty_id;
	}
	public Integer getSpecialty_id(){
		return specialty_id;
	}
	
	public void setIs_self_specialty(Integer is_self_specialty){
		this.is_self_specialty=is_self_specialty;
	}
	/*是否自建专业：1 是； 0 否*/
	public Integer getIs_self_specialty(){
		return is_self_specialty;
	}
}