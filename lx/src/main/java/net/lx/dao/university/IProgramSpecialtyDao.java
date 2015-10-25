package net.lx.dao.university;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.university.ProgramSpecialty;

/**
 * 字典数据层接口
 * 
 */

public interface IProgramSpecialtyDao extends BaseDao<ProgramSpecialty>
{	
	public List<ProgramSpecialty> searchProgramSpecialtyRelation(ProgramSpecialty uni);
}
