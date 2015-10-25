package net.lx.biz.university;

import net.lx.entity.university.UniversityStatistic;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IUniversityStatisticBiz 
{
	public UniversityStatistic findByUniversityId(int university_id) throws Exception;
	
	public Integer createOrUpdate(UniversityStatistic g) throws Exception;

	//public void modify(UniversityStatistic g) throws Exception;
}
