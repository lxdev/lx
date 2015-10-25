package net.lx.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.CookieConstants;
import net.lx.common.enums.LoginErrorEnum;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.common.properties.Config;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.GuideSpecialty;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.user.User;
import net.lx.entity.user.UserExtend;
import net.lx.model.base.AuthenticationTicket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 登录页面
 * 
 * @author lxl
 * 
 */
@Namespace("/user")
@ParentPackage("json-default")
public class RegisterNextAction extends BaseAction implements ModelDriven<UserExtend> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4708609171250801301L;

	private Log log = LogFactory.getLog(RegisterNextAction.class);
	
	private String message;
	
	private List<Country> graduateDateList = new ArrayList<Country>();
	@Autowired
	private ICountryBiz countryBiz;// country业务接口
	private List<Country> countryList = new ArrayList<Country>();
	private int countryid;
	@Autowired
	private IUniversityBiz universityBiz;
	private List<University> universityList = new ArrayList<University>();
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	private List<Specialty> specialtyList = new ArrayList<Specialty>();
	
	private User currentUser;

	private int userId = 0;		//注册成功后的用户Id
	
	private UserExtend user_extend = new UserExtend();
	@Autowired
	private IUserExtendBiz userExtendBiz;
	
	@Action(value = "register_next", results = {
			@Result(name = "input", location = "/WEB-INF/content/user/register_next.jsp"),
			@Result(name = "success", location = "login", type = "redirect")})
	@Override
	public String execute() throws Exception 
	{
		if(userId == 0)
			return SUCCESS;
		User user = userBiz.findUserById(userId);
		if(user == null || user.getUser_id() == 0)
			return SUCCESS;
		currentUser = user;
		
		if (isGetRequest()) 
		{
			for(Integer i = 1970; i < 2020; i++){
				Country temp = new Country();
				temp.setId(i);
				temp.setName(i.toString());
				graduateDateList.add(temp);
			}
			setCountryList(this.countryBiz.findAll());
			
			return INPUT;
		} 
		
		//保存数据到 UserExtend中
		if(!user_extend.getGraduate_date().equals("")){
			user_extend.setUser_id(userId);
			Boolean resultNew = userExtendBiz.createNew(user_extend);
		}
		
		return SUCCESS;
	}
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "register_university_list_by_country", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String getUniversitys() throws Exception
	{
		setMessage("");
		if(countryid == 0){
			setMessage("请先选一个国家！");
			return SUCCESS;
		}
		University tempU = new University();
		tempU.setCountry_id(countryid);
		setUniversityList(universityBiz.searchUniversitysByCondition(tempU));
		
		return SUCCESS;
	}
	
	/*
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "register_specialty_list_by_country", results = { @Result(name = "success", type = "json", 
			params = {"contentType", "text/json" }) })
	public String getSpecialtys() throws Exception
	{
		setMessage("");
		if(countryid == 0){
			setMessage("请先选一个国家！");
			return SUCCESS;
		}
		Specialty tempU = new Specialty();
		//tempU.setCountry_id(countryid);
		setSpecialtyList(specialtyBiz.searchSpecialtysByCondition(tempU));
		
		return SUCCESS;
	}

	//--------------------------------
	
	public UserExtend getModel() {
		return user_extend;
	}
	
	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	
	public List<Country> getGraduateDateList(){
		return graduateDateList;
	}
	
	public void setGraduateDateList(List<Country> graduateDateList){
		this.graduateDateList = graduateDateList;
	}

	public List<Specialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<Specialty> specialtyList) {
		this.specialtyList = specialtyList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}

	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
}
