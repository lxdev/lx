package net.lx.entity.evaluate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_evaluate_university")
public class EvaluateExtendUniversity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8168411746669245449L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="evaluate_id")
	private Integer evaluate_id;
	//院校id 
	@Column(name="university_id")
	private Integer university_id;
	//以下都是针对 院校 的评价分值 1 ～ 10
	@Column(name="option_quality")
	private Integer option_quality;
	@Column(name="option_professor")
	private Integer option_professor;
	@Column(name="option_reputation")
	private Integer option_reputation;
	@Column(name="option_environment")
	private Integer option_environment;
	@Column(name="option_outclass")
	private Integer option_outclass;
	@Column(name="option_security")
	private Integer option_security;
	@Column(name="option_job")
	private Integer option_job;
	@Column(name="option_cost")
	private Integer option_cost;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setEvaluate_id(Integer evaluate_id){
		this.evaluate_id=evaluate_id;
	}

	public Integer getEvaluate_id(){
		return evaluate_id;
	}

	public Integer getUniversity_id() {
		return university_id;
	}

	public void setUniversity_id(Integer university_id) {
		this.university_id = university_id;
	}

	public void setOption_quality(Integer option_quality){
		this.option_quality=option_quality;
	}

	public Integer getOption_quality(){
		return option_quality;
	}

	public void setOption_professor(Integer option_professor){
		this.option_professor=option_professor;
	}

	public Integer getOption_professor(){
		return option_professor;
	}

	public void setOption_reputation(Integer option_reputation){
		this.option_reputation=option_reputation;
	}

	public Integer getOption_reputation(){
		return option_reputation;
	}

	public void setOption_environment(Integer option_environment){
		this.option_environment=option_environment;
	}

	public Integer getOption_environment(){
		return option_environment;
	}

	public void setOption_outclass(Integer option_outclass){
		this.option_outclass=option_outclass;
	}

	public Integer getOption_outclass(){
		return option_outclass;
	}

	public void setOption_security(Integer option_security){
		this.option_security=option_security;
	}

	public Integer getOption_security(){
		return option_security;
	}

	public void setOption_job(Integer option_job){
		this.option_job=option_job;
	}

	public Integer getOption_job(){
		return option_job;
	}

	public void setOption_cost(Integer option_cost){
		this.option_cost=option_cost;
	}

	public Integer getOption_cost(){
		return option_cost;
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
