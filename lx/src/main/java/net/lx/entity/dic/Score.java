package net.lx.entity.dic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.common.hibernate.SortChineseAnnotation;

/**
 * country实体
 *
 */
@Entity
@Table(name = "tb_d_score")
public class Score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7960460666841958657L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="category_type")
	private int category_type;
	
	@Column(name="category")
	@SortChineseAnnotation(sort = true)
	private String category;
	
	@Column(name="scope_lower")
	private float scope_lower;

	@Column(name="scope_higher")
	private float scope_higher;
	
	@Transient
	transient private int scope_lower_int;
	public Integer getScope_lower_int() {
		return scope_lower_int;
	}
	public void setScope_lower_int(int scope_lower_int) {
		this.scope_lower_int = scope_lower_int;
	}
	@Transient
	transient private int scope_higher_int;
	public Integer getScope_higher_int() {
		return scope_higher_int;
	}
	public void setScope_higher_int(int scope_higher_int) {
		this.scope_higher_int = scope_higher_int;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryType() {
		return category_type;
	}
	public void setCategoryType(int category_type) {
		this.category_type = category_type;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public float getScopeLower() {
		return scope_lower;
	}
	public void setScopeLower(float scope_lower) {
		this.scope_lower = scope_lower;
	}

	public float getScopeHigher() {
		return scope_higher;
	}
	public void setScopeHigher(float scope_higher) {
		this.scope_higher = scope_higher;
	}
	
}
