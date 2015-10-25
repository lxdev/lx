package net.lx.dao.evaluate;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateReply;

/**
 * 字典数据层接口
 * 
 */

public interface IEvaluateReplyDao extends BaseDao<EvaluateReply>
{	
	public List<EvaluateReply> searchEvaluateRepliesByCondition(EvaluateReply con);
	
	
}
