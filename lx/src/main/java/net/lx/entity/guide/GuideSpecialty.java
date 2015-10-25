package net.lx.entity.guide;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.common.hibernate.SortChineseAnnotation;

@Entity
@Table(name = "tb_e_guide_specialty")
public class GuideSpecialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="specialty_name")
	@SortChineseAnnotation(sort = true)
	private String specialty_name;
	@Column(name="parent_id")
	private Integer parent_id;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;

	//---------------------------
	@Transient
	transient private GuideSpecialty parentSpecialty;
	public GuideSpecialty getParentSpecialty() {
		return parentSpecialty;
	}
	public void setParentSpecialty(GuideSpecialty parentSpecialty) {
		this.parentSpecialty = parentSpecialty;
	}
	//---------------------------
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

	public void setParent_id(Integer parent_id){
		this.parent_id=parent_id;
	}

	public Integer getParent_id(){
		return parent_id;
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