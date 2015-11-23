package net.lx.entity.university;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tb_e_program_statistic")
public class ProgramStatistic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3682865378437560207L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="program_id")
	private Integer program_id;
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
		
	public void setProgram_id(Integer program_id){
		this.program_id=program_id;
	}

	public Integer getProgram_id(){
		return program_id;
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
