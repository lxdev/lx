package net.lx.dao.evaluate.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.evaluate.IEvaluateDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.evaluate.Evaluate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 评价模块：针对院校  和  针对 顾问
 * @author Administrator
 *
 */
@Repository
public class EvaluateDaoImpl extends BaseMDDaoImpl<Evaluate> implements IEvaluateDao {
	
	
	public List<Evaluate> searchEvaluatesByCondition(Evaluate con){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_evaluate A WHERE 1 = 1 ";
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		if(con.getEvaluate_type() != null && con.getEvaluate_type() > 0){
			sql += " AND A.evaluate_type = ?";
			list.add(con.getEvaluate_type());
		}
		if(con.getSource_id() != null && con.getSource_id() > 0){
			sql += " AND A.source_id = ?";
			list.add(con.getSource_id());
		}
		if(con.getEvaluate_from_user() != null){
			sql += " AND A.evaluate_from_user_id = ?";
			list.add(con.getEvaluate_from_user_id());
		}
		
		sql += " ORDER BY created_date DESC ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
