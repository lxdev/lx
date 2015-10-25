package net.lx.dao.ask;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskSession;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;

/**
 * 字典数据层接口
 * 
 */

public interface IAskSessionDao extends BaseDao<AskSession>
{	
	public List<AskSession> searchByCondition(AskSession condition);
}
