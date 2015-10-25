package net.lx.dao.ask.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.ask.IAskMessageDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.ask.AskSession;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AskMessageDaoImpl extends BaseMDDaoImpl<AskMessage> implements IAskMessageDao {
	
	public List<AskMessage> searchByCondition(AskMessage condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_ask_message A WHERE send_status = 1 ";
		List list = new ArrayList();
		sql += " AND A.ask_id = ?";
		list.add(condition.getAsk_id());
		if(condition.getSession_id() != null && condition.getSession_id() > 0){
			sql += " AND A.session_id = ?";
			list.add(condition.getSession_id());
		}
		sql += " ORDER BY send_date desc ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
