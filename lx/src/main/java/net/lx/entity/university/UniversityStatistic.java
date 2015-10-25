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
@Table(name = "tb_e_university_statistic")
public class UniversityStatistic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3682865378437560207L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="university_id")
	private Integer university_id;
	@Column(name="total_browse")
	private Integer total_browse;
	@Column(name="total_evaluate")
	private Integer total_evaluate;
	@Column(name="total_collect")
	private Integer total_collect;

	//-----------------------------------
	
	/*
	 * 增加或减少，幅度都是 1
	 * -1 1  	browse
	 * -2 2 	evaluate
	 * -3 3		collect
	 */
	@Transient
	private int incr_or_decr;
	public int getIncr_or_decr() {
		return incr_or_decr;
	}
	public void setIncr_or_decr(int incr_or_decr) {
		this.incr_or_decr = incr_or_decr;
	}

	//-----------------------------------
		
	public void setUniversity_id(Integer university_id){
		this.university_id=university_id;
	}

	public Integer getUniversity_id(){
		return university_id;
	}

	public void setTotal_browse(Integer total_browse){
		this.total_browse=total_browse;
	}

	public Integer getTotal_browse(){
		return total_browse;
	}

	public void setTotal_evaluate(Integer total_evaluate){
		this.total_evaluate=total_evaluate;
	}

	public Integer getTotal_evaluate(){
		return total_evaluate;
	}

	public void setTotal_collect(Integer total_collect){
		this.total_collect=total_collect;
	}

	public Integer getTotal_collect(){
		return total_collect;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
