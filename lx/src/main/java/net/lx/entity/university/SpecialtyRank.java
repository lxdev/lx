package net.lx.entity.university;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_specialty_rank")
public class SpecialtyRank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1416697390815743978L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="rankclass_id")
	private Integer rankclass_id;
	@Column(name="ranksource")
	private Integer ranksource;
	@Column(name="university_id")
	private Integer university_id;
	@Column(name="rank")
	private Integer rank;
	@Column(name="remark")
	private String remark;

	public void setRankclass_id(Integer rankclass_id){
		this.rankclass_id=rankclass_id;
	}

	public Integer getRankclass_id(){
		return rankclass_id;
	}

	public void setRanksource(Integer ranksource){
		this.ranksource=ranksource;
	}

	public Integer getRanksource(){
		return ranksource;
	}

	public void setUniversity_id(Integer university_id){
		this.university_id=university_id;
	}

	public Integer getUniversity_id(){
		return university_id;
	}

	public void setRank(Integer rank){
		this.rank=rank;
	}

	public Integer getRank(){
		return rank;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
