package net.lx.dao.crm;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;

/**
 * 字典数据层接口
 * 
 */

public interface IFollowDao extends BaseDao<Follow>
{	

	public List<Follow> searchByCondition(Follow condition);
}
