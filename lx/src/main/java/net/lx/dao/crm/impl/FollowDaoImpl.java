package net.lx.dao.crm.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.crm.IFollowDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDaoImpl extends BaseMDDaoImpl<Follow> implements IFollowDao {
	
	public List<Follow> searchByCondition(Follow condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_follow A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(condition.getFollow_type() != null && condition.getFollow_type() > 0){
			sql += " AND A.follow_type = ?";
			list.add(condition.getFollow_type());
		}
		if(condition.getSource_id() != null && condition.getSource_id() > 0){
			sql += " AND A.source_id = ?";
			list.add(condition.getSource_id());
		}
		if(condition.getCreated_user_id() != null && condition.getCreated_user_id() > 0){
			sql += " AND A.created_user_id = ?";
			list.add(condition.getCreated_user_id());
		}
		if(condition.getFollow_user_id() != null && condition.getFollow_user_id() > 0){
			sql += " AND A.follow_user_id = ?";
			list.add(condition.getFollow_user_id());
		}
		sql += " ORDER BY created_date desc ";
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
