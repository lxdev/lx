package net.lx.dao.ask;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;

/**
 * 字典数据层接口
 * 
 */

public interface IAskDao extends BaseDao<Ask>
{	

	public List<Ask> searchAsksByCondition(Ask condition);
}
