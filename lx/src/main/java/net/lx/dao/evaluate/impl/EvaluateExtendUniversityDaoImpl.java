package net.lx.dao.evaluate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.evaluate.IEvaluateExtendUniversityDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 评价模块：针对院校  和  针对 顾问
 * @author Administrator
 *
 */
@Repository
public class EvaluateExtendUniversityDaoImpl extends BaseMDDaoImpl<EvaluateExtendUniversity> implements IEvaluateExtendUniversityDao {
	
	public EvaluateExtendUniversity searchByEvaluateId(Integer evaluateId){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_evaluate_university A WHERE 1 = 1 ";
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
			sql += " AND A.evaluate_id = ?";
			list.add(evaluateId);
		
		List<EvaluateExtendUniversity> resultList = jt.queryForList(sql, list.toArray());
		if(resultList.size() >= 1){
			Map map = (Map) resultList.get(0);
			EvaluateExtendUniversity f = MapToEntryConvertUtils.convert(map, EvaluateExtendUniversity.class, "");
			return f;
		}
		else
			return null;
	}
	
}
