package net.lx.dao.ask.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskReplyDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.ask.AskReply;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AskReplyDaoImpl extends BaseMDDaoImpl<AskReply> implements IAskReplyDao {
	
	public List<AskReply> searchAskReplysByCondition(AskReply condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_ask_reply A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(condition.getAsk_id() != null && condition.getAsk_id() > 0){
			sql += " AND A.ask_id = ?";
			list.add(condition.getAsk_id());
		}
		
		sql += " ORDER BY created_date desc ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
