package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.ISpecialtyRankDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.SpecialtyRank;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialtyRankDaoImpl extends BaseMDDaoImpl<SpecialtyRank> implements ISpecialtyRankDao {
	
	public List<SpecialtyRank> searchSpecialtyRanksByCondition(SpecialtyRank spe){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_specialty_rank A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(spe.getRankclass_id() != null && spe.getRankclass_id() != 0){
			sql += " AND A.rankclass_id = ?";
			list.add(spe.getRankclass_id());
		}
		
		return jt.queryForList(sql, list.toArray());
	}
	
}
