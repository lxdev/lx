package net.lx.dao.university;

import java.util.EnumMap;
import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.university.Program;
import net.lx.entity.university.University;
//import net.lx.entity.university.ProgramSearchCondition;

/**
 * 字典数据层接口
 * 
 */

public interface IProgramDao extends BaseDao<Program>
{	

	public List<Program> searchProgramsByCondition(Program program);
	
	public String searchProgramsRecordByCondition(Program program);
	
	public EnumMap<SqlAndList, Object> getSqlAndList(Program program);
	
}
