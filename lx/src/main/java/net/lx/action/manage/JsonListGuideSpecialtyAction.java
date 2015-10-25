package net.lx.action.manage;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.entity.guide.GuideSpecialty;

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
public class JsonListGuideSpecialtyAction extends BaseAction implements ModelDriven<GuideSpecialty>
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3520593827149445607L;
	@Autowired
	private IGuideSpecialtyBiz guideSpecialtyBiz;
	private List<GuideSpecialty> list=new ArrayList<GuideSpecialty>();
	
	private GuideSpecialty guideSpecialty=new GuideSpecialty();
	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_guide_specialty", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"list.*.parent_id,"+
					"results,result"
					} )})
	public String execute()throws Exception
	{
		list = guideSpecialtyBiz.searchGuideSpecialtysByCondition(guideSpecialty);
		return SUCCESS;
	}

	public List<GuideSpecialty> getList() {
		return list;
	}

	public GuideSpecialty getModel() {
		return guideSpecialty;
	}

	public GuideSpecialty getGuideSpecialty() {
		return guideSpecialty;
	}

	public void setGuideSpecialty(GuideSpecialty guideSpecialty) {
		this.guideSpecialty = guideSpecialty;
	}	
}
