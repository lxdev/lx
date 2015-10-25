package net.lx.dao.ask;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;

/**
 * 字典数据层接口
 * 
 */

public interface IAskMessageDao extends BaseDao<AskMessage>
{	
	public List<AskMessage> searchByCondition(AskMessage condition);
}
