package net.lx.dao.evaluate.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.evaluate.IEvaluateDao;
import net.lx.dao.evaluate.IEvaluateReplyDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateReply;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 评价模块：针对院校  和  针对 顾问
 * @author Administrator
 *
 */
@Repository
public class EvaluateReplyDaoImpl extends BaseMDDaoImpl<EvaluateReply> implements IEvaluateReplyDao {

	public List<EvaluateReply> searchEvaluateRepliesByCondition(EvaluateReply con){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_evaluate_reply A WHERE 1 = 1 AND A.status = 1 ";
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		if(con.getEvaluate_id() != null && con.getEvaluate_id() > 0){
			sql += " AND A.evaluate_id = ?";
			list.add(con.getEvaluate_id());
		}
		if(con.getCreated_user_id() != null && con.getCreated_user_id() > 0){
			sql += " AND A.created_user_id = ?";
			list.add(con.getCreated_user_id());
		}
		
		sql += " ORDER BY created_date DESC ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
