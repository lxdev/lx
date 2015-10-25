package net.lx.dao.ask.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskStatisDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.ask.AskStatis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AskStatisDaoImpl extends BaseMDDaoImpl<AskStatis> implements IAskStatisDao {
	
	public List<AskStatis> searchAskStatisByCondition(int ask_id){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_ask_statis A WHERE 1 = 1 ";
		List list = new ArrayList();
			sql += " AND A.ask_id = ?";
			list.add(ask_id);
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
