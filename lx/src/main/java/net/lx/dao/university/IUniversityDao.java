package net.lx.dao.university;

import java.util.EnumMap;
import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.university.University;

/**
 * 字典数据层接口
 * 
 */

public interface IUniversityDao extends BaseDao<University>
{	

	public List<University> searchUniversitysByCondition(University uni);
	
	public Integer searchUniversitysRecordByCondition(University uni);

	public EnumMap<SqlAndList, Object> getSqlAndList(University uni);
}
