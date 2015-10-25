package net.lx.dao.dic.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.dic.IScoreDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.dic.Score;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDaoImpl extends BaseMDDaoImpl<Score> implements IScoreDao {
	
	public List<Score> searchScoresByType(int type){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_d_score WHERE 1 = 1 ";
		List list = new ArrayList();
		sql += " AND category_id = ?";
		list.add(type);
		return jt.queryForList(sql, list.toArray());
	}
	
}
