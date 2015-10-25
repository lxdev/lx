package net.lx.dao.ask.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.ask.IAskSessionDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskSession;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AskSessionDaoImpl extends BaseMDDaoImpl<AskSession> implements IAskSessionDao {
	
	public List<AskSession> searchByCondition(AskSession condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_ask_session A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(condition.getAsk_id() != null && condition.getAsk_id() > 0){
			sql += " AND A.ask_id = ?";
			list.add(condition.getAsk_id());
		}
		if(condition.getAsk_user_id() != null && condition.getAsk_user_id() > 0){
			sql += " AND A.ask_user_id = ?";
			list.add(condition.getAsk_user_id());
		}
		if(condition.getReply_user_id() != null && condition.getReply_user_id() > 0){
			sql += " AND A.reply_user_id = ?";
			list.add(condition.getReply_user_id());
		}
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
