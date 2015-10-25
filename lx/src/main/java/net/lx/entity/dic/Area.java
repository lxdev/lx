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
 * country实体
 *
 */
@Entity
@Table(name = "tb_d_area")
public class Area implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4553403671724428091L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private Integer id;
	
	@Column(name="name")
	@SortChineseAnnotation(sort = true)
	private String name; 					//名称varchar(128)
	
	@Column(name="country_id")
	private Integer country_id;				//简称varcahr(128)
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCountryId() {
		return country_id;
	}
	public void setCountryId(Integer country_id) {
		this.country_id = country_id;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
