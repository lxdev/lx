package net.lx.dao.university;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.SpecialtyRank;

/**
 * 字典数据层接口
 * 
 */

public interface ISpecialtyRankDao extends BaseDao<SpecialtyRank>
{	
	public List<SpecialtyRank> searchSpecialtyRanksByCondition(SpecialtyRank uni);
	
}
