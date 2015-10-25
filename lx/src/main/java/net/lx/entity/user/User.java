package net.lx.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_e_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1773715079361209055L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")  					//主建ID
	private Integer user_id;
	@Column(name="user_name")
	private String user_name;
	@Column(name="password")
	private String password;
	@Column(name="user_email")
	private String user_email;
	@Column(name="user_mobile")
	private String user_mobile;
	@Column(name="full_name")
	private String full_name;
	@Column(name="display_name")
	private String display_name;
	@Column(name="user_type")
	private Integer user_type;
	@Column(name="photo_url")
	private String photo_url;
	@Column(name="status")		//1 启用  0 停用
	private Integer status;
	@Column(name="delete_flag")
	private Integer delete_flag;
	@Column(name="is_manager")
	private Integer is_manager;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="created_user_id")
	private Integer created_user_id;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="updated_user_id")
	private Integer updated_user_id;
	@Column(name="update_password_time")
	private Date update_password_time;	

	//@Transient
	//transient private Map<String, Object> objParams = null;
	
	//-------------------------------------------
	@Transient
	transient private UserExtend userExtendStudent;
	@Transient
	transient private UserExtendConsultant userExtendConsultant;

	public UserExtend getUserExtendStudent() {
		return userExtendStudent;
	}

	public void setUserExtendStudent(UserExtend userExtendStudent) {
		this.userExtendStudent = userExtendStudent;
	}

	public UserExtendConsultant getUserExtendConsultant() {
		return userExtendConsultant;
	}

	public void setUserExtendConsultant(UserExtendConsultant userExtendConsultant) {
		this.userExtendConsultant = userExtendConsultant;
	}
	
	//-------------------------------------------

	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}

	public Integer getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setUser_email(String user_email){
		this.user_email=user_email;
	}

	public String getUser_email(){
		return user_email;
	}

	public void setUser_mobile(String user_mobile){
		this.user_mobile=user_mobile;
	}

	public String getUser_mobile(){
		return user_mobile;
	}

	public String getFull_name() {
		return full_name == null ? user_name : full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public void setUser_type(Integer user_type){
		this.user_type=user_type;
	}

	public Integer getUser_type(){
		return user_type;
	}

	public void setPhoto_url(String photo_url){
		this.photo_url=photo_url;
	}

	public String getPhoto_url(){
		return photo_url;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setDelete_flag(Integer delete_flag){
		this.delete_flag=delete_flag;
	}

	public Integer getDelete_flag(){
		return delete_flag;
	}

	public void setIs_manager(Integer is_manager){
		this.is_manager=is_manager;
	}

	public Integer getIs_manager(){
		return is_manager;
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

	public void setUpdated_date(Date updated_date){
		this.updated_date=updated_date;
	}

	public Date getUpdated_date(){
		return updated_date;
	}

	public void setUpdated_user_id(Integer updated_user_id){
		this.updated_user_id=updated_user_id;
	}

	public int getUpdated_user_id(){
		return updated_user_id;
	}
	
	public Date getUpdate_password_time() {
		return update_password_time;
	}

	public void setUpdate_password_time(Date update_password_time) {
		this.update_password_time = update_password_time;
	}

//	public Map<String, Object> getObjParams() {
//		if(objParams==null){
//			objParams = new HashMap<String, Object>();
//		}
//		return objParams;
//	}
}
