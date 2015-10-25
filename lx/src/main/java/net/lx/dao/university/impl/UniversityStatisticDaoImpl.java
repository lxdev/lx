package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityStatisticDao;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
import net.lx.entity.university.UniversityStatistic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityStatisticDaoImpl extends BaseMDDaoImpl<UniversityStatistic> implements IUniversityStatisticDao {

	public UniversityStatistic findByUniversityId(int university_id){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_university_statistic A WHERE A.university_id = ? ";
		List list = new ArrayList();
		list.add(university_id);
		List<UniversityStatistic> resultList = jt.queryForList(sql, list.toArray());
		if(resultList.size() >= 1){
			Map map = (Map) resultList.get(0);
			UniversityStatistic f = MapToEntryConvertUtils.convert(map, UniversityStatistic.class, "");
			return f;
		}
		else
			return null;
	}
	
}
