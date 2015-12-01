package net.lx.action.manage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.IProgramSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.file.FileUtil;
import net.lx.common.file.excel.ImportExcel;
import net.lx.common.il8n.ResourcesTool;
import net.lx.common.md5.Encryption;
import net.lx.common.properties.Config;
import net.lx.common.string.StringEncode;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;
import net.lx.entity.university.Program;
import net.lx.entity.university.ProgramSpecialty;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ParentPackage("json-default")
//@ParentPackage("struts-default")
@Namespace("/manage")
@Component
public class ProgramAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3222119195068437430L;

	@Autowired
	private IProgramBiz programBiz;
	
	private boolean results=false;
	
	private List<Program> programs = new ArrayList<Program>();
	
	private Program program = new Program();
	private int program_id;
	
	private String message;
	private int total_record;
	
	@Autowired
	private ICountryBiz countryBiz;
	@Autowired
	private IStudyLevelBiz studyLevelBiz;
	@Autowired
	private IUniversityBiz universityBiz;
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	@Autowired
	private IProgramSpecialtyBiz psBiz;
	
	private List<Country> countryList;
	private List<StudyLevel> studyLevelList;
	private List<University> universityList;
	private List<Specialty> specialtyList;
	
	@Action(value = "program", results = { @Result(name = "success", location = "/WEB-INF/content/manage/program.jsp") })
	public String Programs() throws Exception {
		setCountryList(this.countryBiz.findAll());
		setStudyLevelList(this.studyLevelBiz.findAll());
		if(super.isGetRequest()){
			program.setPage(1);
			return SUCCESS;
		}
		Program cProgram = new Program();
		if(program.getCountryId() > 0)
			cProgram.setCountryId(program.getCountryId());
		if(program.getStudy_level_id() > 0)
			cProgram.setStudy_level_id(program.getStudy_level_id());
		if(program.getSpecialtyId() != null && program.getSpecialtyId() > 0)
			cProgram.setSpecialtyId(program.getSpecialtyId());
		else if(program.getSpecialtyName() != null && !program.getSpecialtyName().equals(""))
			cProgram.setSpecialtyName(program.getSpecialtyName());
		if(program.getProgram_name() != null && !program.getProgram_name().equals(""))
			cProgram.setProgram_name(program.getProgram_name());
		cProgram.setScore_totef(-1);
		cProgram.setScore_ietls(-1);
		cProgram.setScore_gre(-1);
		cProgram.setScore_gmat(-1);
		if(program.getPage_size() > 0){
			cProgram.setPage_size(program.getPage_size());
			cProgram.setPage(program.getPage());
		}
		
		setPrograms(this.programBiz.searchProgramsByCondition(cProgram));

		cProgram.setIsSearchTotal(true);
		//total_record = this.programBiz.searchProgramsRecordByCondition(cProgram);
		String numString = this.programBiz.searchProgramsRecordByCondition(cProgram);
		total_record = Integer.valueOf(numString.split(",")[0]) ;
		//total_record
		//setPrograms(programBiz.findAll());
		if(message != null && !message.equals("")){
			//message = StringEncode.ToUTF8(message, false);
		}
		return SUCCESS;
	}

	@Action(value = "program_add", results = { 
			@Result(name = "input", location = "/WEB-INF/content/manage/program_add.jsp"),
			@Result(name = "success", location = "program?message=${message}", type = "redirect")
	})
	public String Add() {
		Boolean result = true;
		try
		{
			if(isGetRequest())
			{
				setMessage("新建Program");
				setCountryList(this.countryBiz.findAll());
				setStudyLevelList(this.studyLevelBiz.findAll());
				universityList = universityBiz.findAll();
				specialtyList = this.specialtyBiz.findAll();
				
				if(getProgram_id() != 0)
					setProgram(programBiz.findProgramById(getProgram_id()));
				else{
					setProgram(new Program());
					program.setId(0);
				}
				return INPUT;
			}
			results = saveProgram();
			if(results){
				setMessage(ResourcesTool.getText("admin","add.success"));
				result = true;
			}
			else{
				setMessage(ResourcesTool.getText("admin", "username.repeat.error"));
				result = false;
			}
			addActionMessage(getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
			result = false;
		}
		if(result)
			return SUCCESS;
		else{
			setMessage("新建Program");
			try {
				setCountryList(this.countryBiz.findAll());
				setStudyLevelList(this.studyLevelBiz.findAll());
				universityList = universityBiz.findAll();
				specialtyList = this.specialtyBiz.findAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return INPUT;
		}
	}
	
	private Boolean saveProgram() throws Exception{
		if(getProgram().getId() != 0){
			programBiz.modify(getProgram());
			setProgram_id(getProgram().getId());
		}
		else{
			//判断名字是否重复
			//
			setProgram_id(programBiz.createNew(getProgram()));
		}
		if(getProgram_id() == 0)
			return false;
		//option content list
		
		return true;
	}

	@Action(value = "program_update", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "program?message=${message}") })
	public String Update() {
		try {
			saveProgram();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMessage("修改program:'" + getProgram().getProgram_name() + "'成功");
		return "success";
	}

	@Action(value = "program_delete", results = { @Result(name = "success", type = "redirect", params = { "encode", "true" }, location = "program?message=${message}") })
	public String Delete() {
		try {
			setMessage("");
			if(getProgram_id() != 0){
				programBiz.deleteById(getProgram_id());
				setMessage("删除program成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMessage("删除program失败");
		}
		return "success";
	}
	
	@Action(value = "program_import", results = { 
			@Result(name = "input", location = "/WEB-INF/content/manage/program_import.jsp"),
			@Result(name = "success", location = "program?message=${message}", type = "redirect")
	})
	public String Import(){
		if(isGetRequest())
		{
			
			
			return INPUT;
		}else {
			
			return SUCCESS;
		}
	}
	

	private File file;
	private String fileFileName;
	
	/**
	 * 上传xls文件，并导入Program信息
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "program_import_upload", results = {
			@Result(name = "success", type = "json", params = { "contentType", "text/html", "includeProperties", "message" }),
			@Result(name = "error", type = "json", params = { "contentType", "text/html", "includeProperties", "message" }) })
	public String programImportUpload() throws Exception {
		// 导入纪录ID//解密
		message = super.getText("Excel导入成功");
		int import_number_insert = 0;
		int import_number_update = 0;
		//String sirId = Encryption.decryption(id);
		try {
			if (!fileFileName.endsWith(".xls")) {
				message = this.getText("Excel导入失败");
				//studentImportRecordBiz.deleteStudentImportRecordById(Integer.valueOf(sirId));
				return ERROR;
			}
//			if (!sirId.equals("")) {
//				StudentImportRecord si = studentImportRecordBiz.findStudentImportRecordById(Integer.valueOf(sirId));
//				if (si != null) {
					String path = FileUtil.FileUploads(
									ServletActionContext.getServletContext().getRealPath(
													Config.getProperty("file.path.import.student.excel")),
									fileFileName, file);
//					si.setUploadedFile(path);
//					studentImportRecordBiz.updateStudentImportRecord(si);
//				}
//			}
					
			File file = new File(ServletActionContext.getServletContext().getRealPath(path));
			// 导入program
			ImportExcel<Program> test = new ImportExcel<Program>(Program.class);
			String[] messageErr = new String[1];
			List<Program> results = test.importExcel(file, messageErr);
			if(results.size() == 0){
				message = " 数据读取失败： " + messageErr[0];
				return ERROR;
			}
			List<StudyLevel> slList = studyLevelBiz.findAll();
			for (Program item : results) {
				if(import_number_update == 83){
					String a = "b";
				}
				if(item.getStudyLevelName() != null && !item.getStudyLevelName().equals("")){
					for(StudyLevel sl : slList){
						if(sl.getName().equals(item.getStudyLevelName())){
							item.setStudy_level_id(sl.getId());
							break;
						}
					}
				}
				if(item.getUniversityName() != null && !item.getUniversityName().equals("")){
//					University uCon = new University();
//					uCon.setUniversity_name(item.getUniversityName());
//					List<University> uList = universityBiz.searchUniversitysByCondition(uCon);
//					if(uList.size() >= 1)
//						item.setUniversity_id(uList.get(0).getId());
					int uniId = universityBiz.searchUniversityIdByName(item.getUniversityName());
					item.setUniversity_id(uniId);
				}
				if(item.getUniversity_id() == null || item.getUniversity_id() == 0){
					message = String.format("Excel导入成功：【新增%1$d个】【修改%2$d】Program.", import_number_insert, import_number_update);
					if(item.getUniversityName() == null)
						message += "院校名：请检查导入Excel表头名称是否和模板中的一致！ ";
					else
						message += String.format(" 院校名：【%1$s】在系统中没有匹配到任何院系哦", item.getUniversityName());
					return ERROR;
				}
				if(item.getStudy_level_id() == null || item.getStudy_level_id() == 0){
					message = String.format("Excel导入成功：【新增%1$d个】【修改%2$d】Program.", import_number_insert, import_number_update);
					message += String.format(" 学位等级：【%1$s】在系统中没有匹配到哦", item.getStudyLevelName());
					return ERROR;
				}
				
				Integer programId = 0;
				Program condition = new Program();
				condition.setScore_gmat(-1);
				condition.setScore_gpa(-1);
				condition.setScore_gre(-1);
				condition.setScore_ietls(-1);
				condition.setScore_totef(-1);
				condition.setStudy_level_id(program.getStudy_level_id());
				condition.setProgram_name(item.getProgram_name());
				condition.setUniversity_id(item.getUniversity_id());
				List<Program> resultList = programBiz.searchProgramsByCondition(condition);
				if(resultList.size() >= 1){
					programId = resultList.get(0).getId();
					item.setId(programId);
					item.setStatus(1);
					programBiz.modify(item);
					import_number_update +=1;
				}else{
					item.setStatus(1);
					programId = programBiz.createNew(item);
					if(programId == null){
						message = String.format(" 导入失败：插入program生成id为null，所在Excel行【%1$s】，课程名为：【%2$s】。  ", item.getSerialNumber(), item.getProgram_name());
						return ERROR;
					}
					import_number_insert +=1;
				}
				if(!item.getSpecialtyName().equals("")){
					Specialty speCondition = new Specialty();
					if(item.getSpecialtyName().contains(",")){
						for(String speItem : item.getSpecialtyName().split(",")){
							speCondition.setSpecialty_english_name(speItem.trim());		//精确搜索
							List<Specialty> specialtys = specialtyBiz.searchSpecialtysByCondition(speCondition);
							
							if(specialtys.size() == 1){
								//specialtys[0]
								ProgramSpecialty ps = new ProgramSpecialty();
								ps.setProgram_id(programId);
								ps.setSpecialty_id(specialtys.get(0).getId());
								ps.setCreated_date(new Date());
								//ps.setCreated_user_id(users.getUser_id());
								ps.setCreated_user_id(-1);
								ps.setDelete_flag(0);
								psBiz.createNew(ps);
							}else {
								message = String.format(" 导入失败：课程-专业关系失败，专业不存在，所在Excel行【%1$s】，课程名为：【%2$s】，专业名为：【%3$s】。  ", item.getSerialNumber(), item.getProgram_name(), speItem);
								return ERROR;
							}
						}
					}else {
						speCondition.setSpecialty_english_name(item.getSpecialtyName().trim());		//精确搜索
						List<Specialty> specialtys = specialtyBiz.searchSpecialtysByCondition(speCondition);
						if(specialtys.size() == 1){
							//specialtys[0]
							ProgramSpecialty ps = new ProgramSpecialty();
							ps.setProgram_id(programId);
							ps.setSpecialty_id(specialtys.get(0).getId());
							ps.setCreated_date(new Date());
							//ps.setCreated_user_id(users.getUser_id());
							ps.setCreated_user_id(-1);
							ps.setDelete_flag(0);
							psBiz.createNew(ps);
						}else {
							message = String.format(" 导入失败：课程-专业关系失败，专业不存在，所在Excel行【%1$s】，课程名为：【%2$s】，专业名为：【%3$s】。  ", item.getSerialNumber(), item.getProgram_name(), item.getSpecialtyName());
							return ERROR;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//message = this.getText("message.import.excel.file.exception.error");
			message = String.format("抛异常原因：%3$s。(当前已【新增%1$d个】【修改%2$d】)", import_number_insert, import_number_update, e.getMessage());
			//studentImportRecordBiz.deleteStudentImportRecordById(Integer.valueOf(sirId));
			return ERROR;
		}
		message += String.format(" Excel导入成功：【新增%1$d个】【修改%2$d】Program", import_number_insert, import_number_update);
		return SUCCESS;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
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

	public int getTotal_record() {
		return total_record;
	}

	public void setTotal_record(int total_record) {
		this.total_record = total_record;
	}

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}

	public List<Specialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<Specialty> specialtyList) {
		this.specialtyList = specialtyList;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
