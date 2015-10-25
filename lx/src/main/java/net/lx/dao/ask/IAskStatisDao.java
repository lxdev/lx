package net.lx.dao.ask;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.AskStatis;

/**
 * 字典数据层接口
 * 
 */

public interface IAskStatisDao extends BaseDao<AskStatis>
{	

	public List<AskStatis> searchAskStatisByCondition(int ask_id);
}
