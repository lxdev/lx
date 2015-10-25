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
 * 学位实体
 *
 */
@Entity
@Table(name = "tb_d_study_level")
public class StudyLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 404530396947738475L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	@SortChineseAnnotation(sort = true)
	private String name; 					//名称varchar(128)
	
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
	
}
