package net.lx.entity.guide;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_guide_option_content")
public class GuideOptionContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "guide_id")
	private Integer guide_id;
	@Column(name = "option_id")
	private Integer option_id;
	@Column(name = "option_content")
	private String option_content;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setGuide_id(Integer guide_id){
		this.guide_id=guide_id;
	}

	public Integer getGuide_id(){
		return guide_id;
	}

	public void setOption_id(Integer option_id){
		this.option_id=option_id;
	}

	public Integer getOption_id(){
		return option_id;
	}

	public void setOption_content(String option_content){
		this.option_content=option_content;
	}

	public String getOption_content(){
		return option_content;
	}

}