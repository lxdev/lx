package net.lx.action.template;

import java.util.ArrayList;
import java.util.List;

import net.lx.biz.university.IUniversityStatisticBiz;
import net.lx.entity.university.UniversityStatistic;
import net.lx.biz.university.IProgramStatisticBiz;
import net.lx.entity.university.ProgramStatistic;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.user.UserBiz;
import net.lx.entity.dic.Country;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.user.User;

public class ProgramAction extends BaseAction {

	@Autowired
	private IProgramBiz programBiz;
	@Autowired
	private IUniversityBiz universityBiz;

	private int programId;
	private Program resultProgram = new Program();	//相似 推荐课程
	
	private List<Program> similarPrograms = new ArrayList<Program>();

	@Autowired
	private IProgramStatisticBiz programStatisticBiz;
	private ProgramStatistic programStatistic = new ProgramStatistic();
	@Autowired
	private IUniversityStatisticBiz universityStatisticBiz;
	private UniversityStatistic universityStatistic = new UniversityStatistic();

	@Action(value = "program", results = {
			@Result(name = "success", type = "dispatcher", params = {
					"contentType", "text/json",
					"includeProperties",
					"result.*, programId"
			}, location="/WEB-INF/content/template/default/program.jsp") 
		}
	)
	public String execute() throws Exception{
		setCurrentUsers();
		if(this.getProgramId() != 0){

			resultProgram = programBiz.findProgramById(this.getProgramId());

			programStatistic.setProgram_id(this.getProgramId());
			programStatistic.setIncr_or_decr(1);
			programStatisticBiz.createOrUpdate(programStatistic);

			universityStatistic.setUniversity_id(resultProgram.getUniversity_id());
			universityStatistic.setIncr_or_decr(1);
			universityStatisticBiz.createOrUpdate(universityStatistic);

			similarPrograms.add(resultProgram);
			
			return SUCCESS;
		}else
			return ERROR;
	}
	

	public int getProgramId(){
		return programId;
	}
	public void setProgramId(int programId){
		this.programId = programId;
	}
	
	public Program getResultProgram(){
		return resultProgram;
	}
	public void setResultProgram(Program resultProgram){
		this.resultProgram = resultProgram;
	}

	public List<Program> getSimilarPrograms(){
		return similarPrograms;
	}
	public void setSimilarPrograms(List<Program> similarPrograms){
		this.similarPrograms = similarPrograms;
	}


	public ProgramStatistic getProgramStatistic() { return programStatistic; }

	public void setProgramStatistic(ProgramStatistic programStatistic) {
		this.programStatistic = programStatistic;
	}

	public UniversityStatistic getUniversityStatistic() {
		return universityStatistic;
	}

	public void setUniversityStatistic(UniversityStatistic universityStatistic) {
		this.universityStatistic = universityStatistic;
	}
}
