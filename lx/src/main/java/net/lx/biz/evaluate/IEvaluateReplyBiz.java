package net.lx.biz.evaluate;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateReply;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IEvaluateReplyBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public EvaluateReply findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<EvaluateReply> findAll() throws Exception;
	
	public List<EvaluateReply> searchEvaluateRepliesByCondition(EvaluateReply entity) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(EvaluateReply g) throws Exception;
	
	public void modify(EvaluateReply g) throws Exception;
}
