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
@Table(name = "tb_d_country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7075826906668803849L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	@SortChineseAnnotation(sort = true)
	private String name; 					//名称varchar(128)
	
	@Column(name="abbr")
	private String abbr;				//简称varcahr(128)
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	
}
