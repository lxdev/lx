package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.IUserExtendConsultantBiz;
import net.lx.common.Constants;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.university.Specialty;
import net.lx.entity.user.User;
import net.lx.entity.user.UserExtendConsultant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 留学顾问
 * @author Administrator
 *
 */
@Namespace("/template")
@ParentPackage("json-default")
public class UserConsultantAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -456422789209804813L;

	private Log log = LogFactory.getLog(UserConsultantAction.class);
	
	@Autowired
	private IUserExtendConsultantBiz consultantBiz;
	@Autowired
	private IEvaluateBiz evaluateBiz;
	//---------------- condition dic
	@Autowired
	private ICountryBiz countryBiz;
	private List<Country> countryList = new ArrayList<Country>();
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	private List<StudyLevel> studyLevelList = new ArrayList<StudyLevel>();
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	private List<Specialty> specialtyList = new ArrayList<Specialty>();
	//-------------- condition
	private UserExtendConsultant condition = new UserExtendConsultant();
	private int userId;
	//-------------- result 
	private List<UserExtendConsultant> consultantList = new ArrayList<UserExtendConsultant>();	//顾问列表
	private UserExtendConsultant consultantResult = new UserExtendConsultant();				//单个顾问，详情
	private List<Evaluate> consultantEvaluateList = new ArrayList<Evaluate>();					//单个顾问评价列表
	private String message;

	@Action(value = "consultants", results = {
			@Result(name = "input", location = "/WEB-INF/content/user/consultant_search.jsp"),
			@Result(name = "success", type = "json", params = {"contentType", "text/json" }) })
	@Override
	public String execute() throws Exception {
		if (isGetRequest()){
			setCountryList(this.countryBiz.findAll());
			setSpecialtyList(this.specialtyBiz.findAll());
			setStudyLevelList(this.studyLevelBiz.findAll());
			
			return INPUT;
		}
		
		setMessage("");
		setConsultantList(consultantBiz.searchUserConsultantsByCondition(condition));
		
		return SUCCESS;
	}
	
	@Action(value = "consultant", results = {
			@Result(name = "success", type = "dispatcher", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*,consultantId"
			}, location = "/WEB-INF/content/user/consultant_search_detail.jsp") 
		})
	public String consultant() throws Exception {
		setCurrentUsers();
		if(this.getUserId() != 0){
			consultantResult = consultantBiz.findUserConsultantByUserId(this.getUserId());
			Evaluate e_condition = new Evaluate();
			e_condition.setSource_id(this.getUserId());
			e_condition.setEvaluate_type(2);
			consultantEvaluateList = evaluateBiz.searchEvaluatesByCondition(e_condition);
		}
		return SUCCESS;
	}

	public List<UserExtendConsultant> getConsultantList() {
		return consultantList;
	}

	public void setConsultantList(List<UserExtendConsultant> consultantList) {
		this.consultantList = consultantList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<StudyLevel> getStudyLevelList() {
		return studyLevelList;
	}

	public void setStudyLevelList(List<StudyLevel> studyLevelList) {
		this.studyLevelList = studyLevelList;
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

	public UserExtendConsultant getConsultantResult() {
		return consultantResult;
	}

	public void setConsultantResult(UserExtendConsultant consultantResult) {
		this.consultantResult = consultantResult;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Evaluate> getConsultantEvaluateList() {
		return consultantEvaluateList;
	}

	public void setConsultantEvaluateList(List<Evaluate> consultantEvaluateList) {
		this.consultantEvaluateList = consultantEvaluateList;
	}


}
