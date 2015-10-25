package net.lx.entity.crm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.lx.entity.university.University;
import net.lx.entity.user.User;

/**
 * 关注  问题
 * 收藏 院校、项目、攻略
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_e_follow")
public class Follow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3278215586306914760L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="follow_type")
	private Integer follow_type;
	@Column(name="source_id")
	private Integer source_id;
	@Column(name="follow_user_id")
	private Integer follow_user_id;
	@Column(name="remark")
	private String remark;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;

	//----
	@Transient
	transient private String source_title;		//关注对象的title		如问题的标题
	@Transient
	transient private String source_content;		//关注对象的body		如问题的内容
	@Transient
	transient private User source_user;			//关注对象的用户				如提问人
	@Transient
	transient private User source_user2;		//回答人
	//-----
	
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setFollow_type(Integer follow_type){
		this.follow_type=follow_type;
	}

	public Integer getFollow_type(){
		return follow_type;
	}

	public void setSource_id(Integer source_id){
		this.source_id=source_id;
	}

	public Integer getSource_id(){
		return source_id;
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

	public Integer getFollow_user_id() {
		return follow_user_id;
	}

	public void setFollow_user_id(Integer follow_user_id) {
		this.follow_user_id = follow_user_id;
	}
	
	//-----Transient

	public String getSource_title() {
		return source_title;
	}

	public void setSource_title(String source_title) {
		this.source_title = source_title;
	}

	public String getSource_content() {
		return source_content;
	}

	public void setSource_content(String source_content) {
		this.source_content = source_content;
	}

	public User getSource_user() {
		return source_user;
	}

	public void setSource_user(User source_user) {
		this.source_user = source_user;
	}

	public User getSource_user2() {
		return source_user2;
	}

	public void setSource_user2(User source_user2) {
		this.source_user2 = source_user2;
	}

}
