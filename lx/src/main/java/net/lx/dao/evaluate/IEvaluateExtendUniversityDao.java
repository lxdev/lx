package net.lx.dao.evaluate;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;

/**
 * 字典数据层接口
 * 
 */

public interface IEvaluateExtendUniversityDao extends BaseDao<EvaluateExtendUniversity>
{
	public EvaluateExtendUniversity searchByEvaluateId(Integer evaluateId);
}
