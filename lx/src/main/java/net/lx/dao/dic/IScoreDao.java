package net.lx.dao.dic;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.dic.Score;

/**
 * 字典数据层接口
 * 
 */

public interface IScoreDao extends BaseDao<Score>
{	

	public List<Score> searchScoresByType(int type);
}
