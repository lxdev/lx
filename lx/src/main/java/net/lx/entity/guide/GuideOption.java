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
import net.lx.entity.university.University;

@Entity
@Table(name = "tb_e_guide_option")
public class GuideOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name="option_name")
	@SortChineseAnnotation(sort = true)
	private String option_name;
	@Column(name="parent_id")
	private Integer parent_id;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;
	
	//---------------------------
	@Transient
	transient private GuideOption parentOption;
	public GuideOption getParentOption() {
		return parentOption;
	}
	public void setParentOption(GuideOption parentOption) {
		this.parentOption = parentOption;
	}
	//---------------------------

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setOption_name(String option_name){
		this.option_name=option_name;
	}

	public String getOption_name(){
		return option_name;
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