package net.lx.dao.evaluate;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.evaluate.Evaluate;

/**
 * 字典数据层接口
 * 
 */

public interface IEvaluateDao extends BaseDao<Evaluate>
{	
	public List<Evaluate> searchEvaluatesByCondition(Evaluate con);
	
	
}
