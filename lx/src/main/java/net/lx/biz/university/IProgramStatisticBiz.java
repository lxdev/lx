package net.lx.biz.university;

import net.lx.entity.university.ProgramStatistic;
import net.lx.entity.university.UniversityStatistic;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IProgramStatisticBiz
{
	public ProgramStatistic findByProgramId(int program_id) throws Exception;
	
	public Integer createOrUpdate(ProgramStatistic g) throws Exception;
}
