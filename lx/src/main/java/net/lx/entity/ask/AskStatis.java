package net.lx.entity.ask;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_ask_statis")
public class AskStatis implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5522162394405776955L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "ask_id")
	private Integer ask_id;
	@Column(name = "total_view")
	private Integer total_view;
	@Column(name = "total_reply")
	private Integer total_reply;
	@Column(name = "total_praise")
	private Integer total_praise;
	@Column(name = "total_quote")
	private Integer total_quote;
	@Column(name = "total_attention")
	private Integer total_attention;
	@Column(name = "post_date")
	private Date post_date;

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

	public void setTotal_view(Integer total_view){
		this.total_view=total_view;
	}

	public Integer getTotal_view(){
		return total_view;
	}

	public void setTotal_reply(Integer total_reply){
		this.total_reply=total_reply;
	}

	public Integer getTotal_reply(){
		return total_reply;
	}

	public void setTotal_praise(Integer total_praise){
		this.total_praise=total_praise;
	}

	public Integer getTotal_praise(){
		return total_praise;
	}

	public void setTotal_quote(Integer total_quote){
		this.total_quote=total_quote;
	}

	public Integer getTotal_quote(){
		return total_quote;
	}

	public void setTotal_attention(Integer total_attention){
		this.total_attention=total_attention;
	}

	public Integer getTotal_attention(){
		return total_attention;
	}

	public void setPost_date(Date post_date){
		this.post_date=post_date;
	}

	public Date getPost_date(){
		return post_date;
	}

}
