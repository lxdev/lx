package net.lx.entity.ask;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "tb_e_ask")
public class Ask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8116291055856032352L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "ask_type")		//1 指定回复人的提问； 2 不指定人的提问
	private Integer ask_type;
	@Column(name = "title")
	private String title;
	@Column(name = "body")
	private String body;
	@Column(name = "attachments")
	private String attachments;
	@Column(name = "order_id")
	private Integer order_id;
	@Column(name = "payed_date")
	private Date payed_date;
	@Column(name = "status")		//0:草稿/未支付  | 1:已提交/已支付 | 2:回答中  | 3:回答完成(号码过期) | 4:回答完成
	private Integer status;
	@Column(name = "ask_user_id")
	private Integer ask_user_id;
	@Column(name = "reply_user_id")
	private Integer reply_user_id;
	@Column(name = "created_date")
	private Date created_date;
	@Column(name = "created_user_id")
	private Integer created_user_id;

	//-----
	@Transient
	transient private User ask_user;			//提问人
	@Transient
	transient private User reply_user;			//回答人
	@Transient
	transient private Integer no_status;			//供查询用，除了此状态
	@Transient
	transient private String created_date_ago;
	@Transient
	transient private List<AskReply> ask_replies = new ArrayList<AskReply>();
	@Transient
	transient private AskStatis ask_statis = new AskStatis();
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setAsk_type(Integer ask_type){
		this.ask_type=ask_type;
	}

	public Integer getAsk_type(){
		return ask_type;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body=body;
	}

	public String getBody(){
		return body;
	}

	public void setAttachments(String attachments){
		this.attachments=attachments;
	}

	public String getAttachments(){
		return attachments;
	}

	public void setOrder_id(Integer order_id){
		this.order_id=order_id;
	}

	public Integer getOrder_id(){
		return order_id;
	}

	public void setPayed_date(Date payed_date){
		this.payed_date=payed_date;
	}

	public Date getPayed_date(){
		return payed_date;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
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

	//-----Transient
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

	public Integer getNo_status() {
		return no_status;
	}

	public void setNo_status(Integer no_status) {
		this.no_status = no_status;
	}

	public String getCreated_date_ago() {
		return created_date_ago;
	}

	public void setCreated_date_ago(String created_date_ago) {
		this.created_date_ago = created_date_ago;
	}

	public List<AskReply> getAsk_replies() {
		return ask_replies;
	}

	public void setAsk_replies(List<AskReply> ask_replies) {
		this.ask_replies = ask_replies;
	}

	public AskStatis getAsk_statis() {
		return ask_statis;
	}

	public void setAsk_statis(AskStatis ask_statis) {
		this.ask_statis = ask_statis;
	}


}
