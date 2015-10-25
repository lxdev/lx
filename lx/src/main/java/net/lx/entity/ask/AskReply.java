package net.lx.entity.ask;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.entity.user.User;

@Entity
@Table(name = "tb_e_ask_reply")
public class AskReply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4375549667807026159L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "ask_id")
	private Integer ask_id;
	@Column(name = "parent_reply_id")
	private Integer parent_reply_id;
	@Column(name = "reply_type")
	private Integer reply_type;
	@Column(name = "body")
	private String body;
	@Column(name = "status")
	private Integer status;
	@Column(name = "audit_status")
	private Integer audit_status;
	@Column(name = "audit_date")
	private Date audit_date;
	@Column(name = "created_date")
	private Date created_date;
	@Column(name = "created_user_id")
	private Integer created_user_id;
	
	//=====================================
	@Transient
	transient private User reply_user;			//回复/暂/关注   操作人
	
	transient private Ask ask;
	//=====================================
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setAsk_id(Integer ask_id){
		this.ask_id=ask_id;
	}

	public Integer getAsk_id(){
		return ask_id;
	}

	public void setParent_reply_id(Integer parent_reply_id){
		this.parent_reply_id=parent_reply_id;
	}

	public Integer getParent_reply_id(){
		return parent_reply_id;
	}

	public void setReply_type(Integer reply_type){
		this.reply_type=reply_type;
	}

	public Integer getReply_type(){
		return reply_type;
	}

	public void setBody(String body){
		this.body=body;
	}

	public String getBody(){
		return body;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setAudit_status(Integer audit_status){
		this.audit_status=audit_status;
	}

	public Integer getAudit_status(){
		return audit_status;
	}

	public void setAudit_date(Date audit_date){
		this.audit_date=audit_date;
	}

	public Date getAudit_date(){
		return audit_date;
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

	public User getReply_user() {
		return reply_user;
	}

	public void setReply_user(User reply_user) {
		this.reply_user = reply_user;
	}

	public Ask getAsk() {
		return ask;
	}

	public void setAsk(Ask ask) {
		this.ask = ask;
	}

}
