package net.lx.entity.ask;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.entity.user.User;

@Entity
@Table(name = "tb_e_ask_session")
public class AskSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8498557516253283611L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "ask_id")
	private Integer ask_id;
	@Column(name = "ask_user_id")
	private Integer ask_user_id;
	@Column(name = "reply_user_id")
	private Integer reply_user_id;
	@Column(name = "last_ask_msg_id")
	private Integer last_ask_msg_id;
	@Column(name = "last_reply_msg_id")
	private Integer last_reply_msg_id;

	//-----
	@Transient
	transient private User ask_user;			//提问人
	@Transient
	transient private User reply_user;			//提问人
	
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

	public void setAsk_user_id(Integer ask_user_id){
		this.ask_user_id=ask_user_id;
	}

	public Integer getAsk_user_id(){
		return ask_user_id;
	}

	public void setReply_user_id(Integer reply_user_id){
		this.reply_user_id=reply_user_id;
	}

	public Integer getReply_user_id(){
		return reply_user_id;
	}

	public void setLast_ask_msg_id(Integer last_ask_msg_id){
		this.last_ask_msg_id=last_ask_msg_id;
	}

	public Integer getLast_ask_msg_id(){
		return last_ask_msg_id;
	}

	public void setLast_reply_msg_id(Integer last_reply_msg_id){
		this.last_reply_msg_id=last_reply_msg_id;
	}

	public Integer getLast_reply_msg_id(){
		return last_reply_msg_id;
	}

	public User getAsk_user() {
		return ask_user;
	}

	public void setAsk_user(User ask_user) {
		this.ask_user = ask_user;
	}

	public User getReply_user() {
		return reply_user;
	}

	public void setReply_user(User reply_user) {
		this.reply_user = reply_user;
	}

}
