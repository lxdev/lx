package net.lx.entity.dic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.lx.common.hibernate.SortChineseAnnotation;

/**
 * 学位实体
 *
 */
@Entity
@Table(name = "tb_d_category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3686342104251906982L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")  					//主建ID
	private int id;
	
	@Column(name="category_name")
	@SortChineseAnnotation(sort = true)
	private String category_name; 					//名称varchar(128)
	
	@Column(name="country_id")
	private Integer country_id;
	
	@Column(name="parent_id")
	private Integer parent_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
}
