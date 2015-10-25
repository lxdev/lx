package net.lx.dao.university;

import net.lx.dao.BaseDao;
import net.lx.entity.university.UniversityStatistic;

/**
 * 字典数据层接口
 * 
 */

public interface IUniversityStatisticDao extends BaseDao<UniversityStatistic>
{	
	UniversityStatistic findByUniversityId(int university_id);
}
