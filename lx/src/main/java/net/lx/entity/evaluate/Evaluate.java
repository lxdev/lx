package net.lx.entity.evaluate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.entity.user.User;

@Entity
@Table(name = "tb_e_evaluate")
public class Evaluate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -96180443485915398L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "evaluate_type")
	private Integer evaluate_type;
	@Column(name = "evaluate_title")
	private String evaluate_title;
	@Column(name = "evaluate_content")
	private String evaluate_content;
	@Column(name = "valid_flag")
	private Integer valid_flag;
	@Column(name = "source_id")
	private Integer source_id;
	@Column(name = "evaluate_from_user_id")
	private Integer evaluate_from_user_id;
	@Column(name = "remark")
	private String remark;
	@Column(name = "created_date")
	private Date created_date;
	@Column(name = "created_user_id")
	private Integer created_user_id;

	//--------------------------------

	@Transient
	private User evaluate_from_user;
	public void setEvaluate_from_user(User evaluate_from_user){
		this.evaluate_from_user=evaluate_from_user;
	}
	public User getEvaluate_from_user(){
		return evaluate_from_user;
	}
	
	@Transient
	private EvaluateExtendUniversity evaluateExtendUniversity;
	public EvaluateExtendUniversity getEvaluateExtendUniversity() {
		return evaluateExtendUniversity;
	}
	public void setEvaluateExtendUniversity(EvaluateExtendUniversity evaluateExtendUniversity) {
		this.evaluateExtendUniversity = evaluateExtendUniversity;
	} 
	
	@Transient
	private List<EvaluateReply> evaluateReplyList;
	public List<EvaluateReply> getEvaluateReplyList() {
		return evaluateReplyList;
	}
	public void setEvaluateReplyList(List<EvaluateReply> evaluateReplyList) {
		this.evaluateReplyList = evaluateReplyList;
	}
	
	//--------------------------------
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setEvaluate_type(Integer evaluate_type){
		this.evaluate_type=evaluate_type;
	}

	public Integer getEvaluate_type(){
		return evaluate_type;
	}

	public void setEvaluate_title(String evaluate_title){
		this.evaluate_title=evaluate_title;
	}

	public String getEvaluate_title(){
		return evaluate_title;
	}

	public void setEvaluate_content(String evaluate_content){
		this.evaluate_content=evaluate_content;
	}

	public String getEvaluate_content(){
		return evaluate_content;
	}

	public void setValid_flag(Integer valid_flag){
		this.valid_flag=valid_flag;
	}

	public Integer getValid_flag(){
		return valid_flag;
	}

	public void setSource_id(Integer source_id){
		this.source_id=source_id;
	}

	public Integer getSource_id(){
		return source_id;
	}

	public void setEvaluate_from_user_id(Integer evaluate_from_user_id){
		this.evaluate_from_user_id=evaluate_from_user_id;
	}

	public Integer getEvaluate_from_user_id(){
		return evaluate_from_user_id;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
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
