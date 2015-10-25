package net.lx.entity.university;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_program_specialty")
public class ProgramSpecialty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -121003695570948466L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private Integer id;
	@Column(name="program_id")
	private Integer program_id;			//程课Id
	@Column(name="specialty_id")
	private Integer specialty_id;		//业专Id
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;
	@Column(name="delete_flag")
	private Integer delete_flag;		//默认0， 删除1

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setProgram_id(Integer program_id){
		this.program_id=program_id;
	}

	public Integer getProgram_id(){
		return program_id;
	}

	public void setSpecialty_id(Integer specialty_id){
		this.specialty_id=specialty_id;
	}

	public Integer getSpecialty_id(){
		return specialty_id;
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

	public void setDelete_flag(Integer delete_flag){
		this.delete_flag=delete_flag;
	}

	public Integer getDelete_flag(){
		return delete_flag;
	}

}
