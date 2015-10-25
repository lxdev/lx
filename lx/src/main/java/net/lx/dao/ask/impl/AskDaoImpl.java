package net.lx.dao.ask.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AskDaoImpl extends BaseMDDaoImpl<Ask> implements IAskDao {
	
	public List<Ask> searchAsksByCondition(Ask condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_ask A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(condition.getAsk_type() != null && condition.getAsk_type() > 0){
			sql += " AND A.ask_type = ?";
			list.add(condition.getAsk_type());
		}
		if(condition.getAsk_user_id() != null && condition.getAsk_user_id() > 0){
			sql += " AND A.ask_user_id = ?";
			list.add(condition.getAsk_user_id());
		}
		if(condition.getReply_user_id() != null && condition.getReply_user_id() > 0){
			sql += " AND A.reply_user_id = ?";
			list.add(condition.getReply_user_id());
		}
		if(condition.getNo_status() != null && condition.getNo_status() > 0){
			sql += " AND A.status != ?";
			list.add(condition.getNo_status());
		}
		sql += " ORDER BY created_date desc ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
