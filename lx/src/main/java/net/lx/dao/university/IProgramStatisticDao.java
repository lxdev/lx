package net.lx.dao.university;

import net.lx.dao.BaseDao;
import net.lx.entity.university.ProgramStatistic;

/**
 * 字典数据层接口
 * 
 */

public interface IProgramStatisticDao extends BaseDao<ProgramStatistic>
{	
	ProgramStatistic findByProgramId(int program_id);
}
