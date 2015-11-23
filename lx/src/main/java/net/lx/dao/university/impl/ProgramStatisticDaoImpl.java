package net.lx.dao.university.impl;

import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IProgramStatisticDao;
import net.lx.entity.university.ProgramStatistic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProgramStatisticDaoImpl extends BaseMDDaoImpl<ProgramStatistic> implements IProgramStatisticDao {

	public ProgramStatistic findByProgramId(int program_id){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_program_statistic A WHERE A.program_id = ? ";
		List list = new ArrayList();
		list.add(program_id);
		List<ProgramStatistic> resultList = jt.queryForList(sql, list.toArray());
		if(resultList.size() >= 1){
			Map map = (Map) resultList.get(0);
			ProgramStatistic f = MapToEntryConvertUtils.convert(map, ProgramStatistic.class, "");
			return f;
		}
		else
			return null;
	}
	
}
