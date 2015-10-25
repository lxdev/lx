package net.lx.action.university;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.evaluate.IEvaluateExtendUniversityBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.CookieConstants;
import net.lx.common.enums.LoginErrorEnum;
import net.lx.common.enums.UserEnum;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.md5.PwdCoder;
import net.lx.common.properties.Config;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
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

/**
 * 登录页面
 * 
 * @author lxl
 * 
 */
@Namespace("/university")
@ParentPackage("json-default")
public class EvaluateWizardAction extends BaseAction 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3306517852740857695L;

	private Log log = LogFactory.getLog(EvaluateWizardAction.class);
	
	@Autowired
	private UserBiz userBiz;
	private User users;
	@Autowired
	private IUserExtendBiz userExtendBiz;
	private UserExtend userExtend = new UserExtend();
	@Autowired
	private IEvaluateBiz evaluateBiz;
	private Evaluate evaluate = new Evaluate();
	@Autowired
	private IEvaluateExtendUniversityBiz evaluateUniversityBiz;
	private EvaluateExtendUniversity evaluateUniversity = new EvaluateExtendUniversity();
//	@Autowired
//	private IEvaluateExtendUniversityBiz evaluateUniversityBiz;
//	private EvaluateExtendUniversity evaluateUniversity = new EvaluateExtendUniversity();
	
	private String message;
	
	@Autowired
	private ICountryBiz countryBiz;// country业务接口
	private List<Country> countryList = new ArrayList<Country>();
	private int countryid;
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	private List<StudyLevel> studyLevelList = new ArrayList<StudyLevel>();
	@Autowired
	private IUniversityBiz universityBiz;
	private List<University> universityList = new ArrayList<University>();
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	private List<Specialty> specialtyList = new ArrayList<Specialty>();
	
	private int step = 0;

	private List<Country> graduateDateList = new ArrayList<Country>();
	
	@Action(value = "evaluate_wizard", results = {
			@Result(name = "input", location = "/WEB-INF/content/university/evaluate_wizard.jsp"),
			@Result(name = "success", type = "redirect", location = "evaluate_wizard?step=${step}"),
			@Result(name = "no_permission", type = "redirect", params = { "encode", "true" }, location = "../template/no_permission") })
	@Override
	public String execute() throws Exception 
	{
		Boolean isAllowEnter = false;
		setUsers(userBiz.findUserById(getUser().getUserId()));
		if(getUsers() != null && getUsers().getUser_type() == Constants.USER_STUDENT){
			if(getUsers().getUserExtendStudent() != null && getUsers().getUserExtendStudent().getUser_type() == Constants.USER_STUDENT_6){
				isAllowEnter = true;
			}
		}
		if(!isAllowEnter){
			return "no_permission";
		}
		if (isGetRequest()) 
		{
			countryList = this.countryBiz.findAll();
			setStudyLevelList(this.studyLevelBiz.findAll());
			//specialtyList = this.specialtyBiz.findAll();
			setUserExtend(userExtendBiz.findUserByUserId(users.getUser_id()));
			if(userExtendBiz != null){
				University con = new University();
				con.setCountry_id(userExtend.getCountry_id());
				universityList = universityBiz.searchUniversitysByCondition(con);
				specialtyList = specialtyBiz.findAll();
			}
			for(Integer i = 1970; i < 2020; i++){
				Country temp = new Country();
				temp.setId(i);
				temp.setName(i.toString());
				graduateDateList.add(temp);
			}
			
			if(userExtend != null){
				Evaluate condition = new Evaluate();
				condition.setEvaluate_type(Constants.EVALUATE_TYPE_UNIVERSITY);
				condition.setSource_id(userExtend.getUniversity_id());
				condition.setEvaluate_from_user_id(getUsers().getUser_id());
				List<Evaluate> evaluates = evaluateBiz.searchEvaluatesByCondition(condition);
				if(evaluates.size() >= 1){
					evaluate = evaluates.get(0);
					evaluateUniversity = evaluate.getEvaluateExtendUniversity();
				}
				evaluate.setSource_id(userExtend.getUniversity_id());
				evaluateUniversity.setUniversity_id(userExtend.getUniversity_id());
			}
			return INPUT;
		} 
		//提交
		
		if(step == 0){
			if(userExtend != null){
				userExtendBiz.modify(userExtend);
			}else{
				return ERROR;
			}
		}else if(step == 1){
			if(evaluate != null && evaluate.getId() != null && evaluate.getId() > 0){
				evaluate = evaluateBiz.findById(evaluate.getId());
				if(evaluate.getSource_id() != evaluateUniversity.getUniversity_id()){
					evaluate.setSource_id(evaluateUniversity.getUniversity_id());
					evaluateBiz.modify(evaluate);
				}
			}else {
				evaluate.setEvaluate_type(Constants.EVALUATE_TYPE_UNIVERSITY);
				evaluate.setSource_id(evaluateUniversity.getUniversity_id());
				evaluate.setCreated_date(new Date());
				evaluate.setCreated_user_id(users.getUser_id());
				evaluate.setEvaluate_from_user_id(users.getUser_id());
				evaluate.setValid_flag(1);
				Integer evaluateId = evaluateBiz.createNew(evaluate);
				evaluate.setId(evaluateId);
			}
			evaluateUniversity.setEvaluate_id(evaluate.getId());
			evaluateUniversity.setUniversity_id(evaluate.getSource_id());
			if(evaluateUniversity != null && evaluateUniversity.getId() != null && evaluateUniversity.getId() > 0){
				evaluateUniversityBiz.modify(evaluateUniversity);
			}else {
				evaluateUniversity.setCreated_date(new Date());
				evaluateUniversity.setCreated_user_id(users.getUser_id());
				evaluateUniversityBiz.createNew(evaluateUniversity);
			}
		}else if(step == 2){
			Evaluate evaluateOld = evaluateBiz.findById(evaluate.getId());
			evaluateOld.setEvaluate_content(evaluate.getEvaluate_content());
			evaluateBiz.modify(evaluateOld);
		}
		step += 1;
		
		return SUCCESS;
	}
	
	//--------------------------------
	
	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
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

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public List<StudyLevel> getStudyLevelList() {
		return studyLevelList;
	}

	public void setStudyLevelList(List<StudyLevel> studyLevelList) {
		this.studyLevelList = studyLevelList;
	}

	public List<Country> getGraduateDateList() {
		return graduateDateList;
	}

	public void setGraduateDateList(List<Country> graduateDateList) {
		this.graduateDateList = graduateDateList;
	}

	public UserExtend getUserExtend() {
		return userExtend;
	}

	public void setUserExtend(UserExtend userExtend) {
		this.userExtend = userExtend;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public EvaluateExtendUniversity getEvaluateUniversity() {
		return evaluateUniversity;
	}

	public void setEvaluateUniversity(EvaluateExtendUniversity evaluateUniversity) {
		this.evaluateUniversity = evaluateUniversity;
	}

//	public EvaluateExtendUniversity getEvaluateUniversity() {
//		return evaluateUniversity;
//	}
//
//	public void setEvaluateUniversity(EvaluateExtendUniversity evaluateUniversity) {
//		this.evaluateUniversity = evaluateUniversity;
//	}

	
}
