package net.lx.action.manage;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.entity.university.Specialty;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Json数据返回
 * @author Jack
 *
 */ 
@ParentPackage("json-default")
public class JsonListSpecialtyAction extends BaseAction implements ModelDriven<Specialty>
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3520593827149445607L;
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	private List<Specialty> list=new ArrayList<Specialty>();
	
	private Specialty specialty=new Specialty();
	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_specialty", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"list.*.country_id,"+
					"list.*.parent_id,"+
					"results,result"
					} )})
	public String execute()throws Exception
	{
		list = specialtyBiz.searchSpecialtysByCondition(specialty);
		return SUCCESS;
	}

	public List<Specialty> getList() {
		return list;
	}

	public Specialty getModel() {
		return specialty;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}	
}
