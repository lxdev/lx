package net.lx.biz.evaluate;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IEvaluateExtendUniversityBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public EvaluateExtendUniversity findById(int id) throws Exception;
	
	public EvaluateExtendUniversity searchByEvaluateId(Integer evaluateId) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(EvaluateExtendUniversity g) throws Exception;
	
	public void modify(EvaluateExtendUniversity g) throws Exception;
}
