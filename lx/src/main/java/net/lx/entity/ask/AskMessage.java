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
@Table(name = "tb_e_ask_message")
public class AskMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2040898701818956099L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "ask_id")
	private Integer ask_id;
	@Column(name = "session_id")
	private Integer session_id;
	@Column(name = "send_user_id")
	private Integer send_user_id;
	@Column(name = "send_date")
	private Date send_date;
	@Column(name = "send_status")
	private Integer send_status;
	@Column(name = "receive_date")
	private Date receive_date;
	@Column(name = "receive_status")
	private Integer receive_status;
	@Column(name = "receive_updated_date")
	private Date receive_updated_date;
	@Column(name = "message_type")
	private Integer message_type;
	@Column(name = "body")
	private String body;
	@Column(name = "body_length")
	private Integer body_length;
	@Column(name = "proxy")
	private Integer proxy;
	
	//-----
	@Transient
	transient private User send_user;			//提问人
	
	//-----
	
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

	public void setSession_id(Integer session_id){
		this.session_id=session_id;
	}

	public Integer getSession_id(){
		return session_id;
	}

	public void setSend_user_id(Integer send_user_id){
		this.send_user_id=send_user_id;
	}

	public Integer getSend_user_id(){
		return send_user_id;
	}

	public void setSend_date(Date send_date){
		this.send_date=send_date;
	}

	public Date getSend_date(){
		return send_date;
	}

	public void setSend_status(Integer send_status){
		this.send_status=send_status;
	}

	public Integer getSend_status(){
		return send_status;
	}

	public void setReceive_date(Date receive_date){
		this.receive_date=receive_date;
	}

	public Date getReceive_date(){
		return receive_date;
	}

	public void setReceive_status(Integer receive_status){
		this.receive_status=receive_status;
	}

	public Integer getReceive_status(){
		return receive_status;
	}

	public void setReceive_updated_date(Date receive_updated_date){
		this.receive_updated_date=receive_updated_date;
	}

	public Date getReceive_updated_date(){
		return receive_updated_date;
	}

	public void setMessage_type(Integer message_type){
		this.message_type=message_type;
	}

	public Integer getMessage_type(){
		return message_type;
	}

	public void setBody(String body){
		this.body=body;
	}

	public String getBody(){
		return body;
	}

	public void setBody_length(Integer body_length){
		this.body_length=body_length;
	}

	public Integer getBody_length(){
		return body_length;
	}

	public void setProxy(Integer proxy){
		this.proxy=proxy;
	}

	public Integer getProxy(){
		return proxy;
	}

	public User getSend_user() {
		return send_user;
	}

	public void setSend_user(User send_user) {
		this.send_user = send_user;
	}

}
