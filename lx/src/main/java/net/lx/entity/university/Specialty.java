package net.lx.entity.university;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_e_specialty")
public class Specialty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="specialty_name")
	private String specialty_name;
	@Column(name="specialty_attr")
	private String specialty_attr;	//缩写
	@Column(name="specialty_english_name")
	private String specialty_english_name;
	@Column(name="parent_id")
	private Integer parent_id;
	@Column(name="depth")
	private Integer depth;		//专业深度：1 专业；2是方向（属于专业）
	@Column(name="specialty_desc")
	private String specialty_desc;
	@Column(name="rankclass_id")
	private Integer rankclass_id;
	
	//----------------------------------
//	@Transient
//	private Country country;
//	public Country getCountry(){
//		return country;
//	}
//	public void setCountry(Country country){
//		this.country = country;
//	}

	@Transient
	private Specialty parentSpecialty;
	public Specialty getParentSpecialty(){
		return parentSpecialty;
	}
	public void setParentSpecialty(Specialty parentSpecialty){
		this.parentSpecialty = parentSpecialty;
	}
	
	@Transient
	private String name;	//用于下拉中的 auto-complete
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	//-----------------------------------
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setSpecialty_name(String specialty_name){
		this.specialty_name=specialty_name;
	}

	public String getSpecialty_name(){
		return specialty_name;
	}

	public void setSpecialty_attr(String specialty_attr){
		this.specialty_attr=specialty_attr;
	}

	public String getSpecialty_attr(){
		return specialty_attr;
	}

	public void setSpecialty_english_name(String specialty_english_name){
		this.specialty_english_name=specialty_english_name;
	}

	public String getSpecialty_english_name(){
		return specialty_english_name;
	}
	
	public void setParent_id(Integer parent_id){
		this.parent_id = parent_id;
	}
	public Integer getParent_id(){
		return parent_id;
	}

	public void setSpecialty_desc(String specialty_desc){
		this.specialty_desc=specialty_desc;
	}

	public String getSpecialty_desc(){
		return specialty_desc;
	}

	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Integer getRankclass_id() {
		return rankclass_id;
	}
	public void setRankclass_id(Integer rankclass_id) {
		this.rankclass_id = rankclass_id;
	}

}
