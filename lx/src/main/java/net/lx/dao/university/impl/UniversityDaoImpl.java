package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityDaoImpl extends BaseMDDaoImpl<University> implements IUniversityDao {

	@Override
	public EnumMap<SqlAndList, Object> getSqlAndList(University condition){
		EnumMap<SqlAndList, Object> retMap = new EnumMap<SqlAndList, Object>(SqlAndList.class);
		
		String sql = "SELECT DISTINCT A.*, C.evaluate_number, D.total_browse browse_number FROM tb_e_university A LEFT JOIN tb_d_area B ON B.id = A.area_id ";
		sql += " LEFT JOIN (SELECT university_id, count(*) evaluate_number FROM tb_e_evaluate_university GROUP BY university_id) C ON C.university_id = A.id ";
		sql += "  LEFT JOIN tb_e_university_statistic D ON D.university_id = A.id ";
		
		if(condition.getIsSearchTotal() != null && condition.getIsSearchTotal())
			sql = "SELECT COUNT(A.id) FROM tb_e_university A LEFT JOIN tb_d_area B ON B.id = A.area_id ";
		
		sql += " WHERE 1 = 1";// A.status = 1 ";
		List list = new ArrayList();
//		if(condition.getStatus() != null && condition.getStatus() != -1){
//			sql += " AND A.status = ? ";
//			list.add(condition.getStatus());
//		} 
			
		if(condition.getCountry_id() != null && condition.getCountry_id() > 0){
			sql += " AND A.country_id = ?";
			list.add(condition.getCountry_id());
		}
		if(condition.getId() != null && condition.getId() > 0){
			sql += " AND A.id = ? ";
			list.add(condition.getId());
		}
		else if(condition.getUniversity_name() != null && !condition.getUniversity_name().equals("")){
			sql += " AND (A.university_name like ? OR A.english_name like ? OR abbr like ?)";
			list.add("%"+condition.getUniversity_name()+"%");
			list.add("%"+condition.getUniversity_name()+"%");
			list.add("%"+condition.getUniversity_name()+"%");
		}
		if(condition.getRankingBegin() != null && condition.getRankingBegin() != 0){
			sql += " AND A.ranking_comprehensive >= ?";
			list.add(condition.getRankingBegin());
		}
		if(condition.getRankingEnd() != null && condition.getRankingEnd() != 0){
			sql += " AND A.ranking_comprehensive <= ?";
			list.add(condition.getRankingEnd());
		}
		if(condition.getAreaName() != null && !condition.getAreaName().equals("")){
			sql += " AND B.name = ?";
			list.add(condition.getAreaName());
		}
		if(condition.getIs_public_school() != null && condition.getIs_public_school() != -1){
			sql += " AND A.is_public_school = ? ";
			list.add(condition.getIs_public_school());
		}
		if(condition.getIsSearchTotal() != null && condition.getIsSearchTotal()){
			
		}else {
			if(condition.getPage_size() > 0){
				//sql_total += condition;
				if(condition.getOrderBy() != null && !condition.getOrderBy().equalsIgnoreCase("")){
					//sql += " ORDER BY ? ";
					//list.add(condition.getOrderBy());
					sql += " ORDER BY " + condition.getOrderBy();
				}else
					sql += " ORDER BY A.ID DESC ";
				sql += " LIMIT ? ";
				list.add(condition.getPage_size());
				sql += " OFFSET ? ";
				list.add(condition.getPage() >= 1 ? (condition.getPage()-1)*condition.getPage_size() : 0);
			}
		}
		
		retMap.put(SqlAndList.SQL, sql);
		retMap.put(SqlAndList.LIST, list);
		return retMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<University> searchUniversitysByCondition(University uni){
		
		JdbcTemplate jt = this.getJdbcTemplate();
		
		EnumMap<SqlAndList, Object> retMap = getSqlAndList(uni);
		
		String sql = (String) retMap.get(IUniversityDao.SqlAndList.SQL);
		List list = (List)retMap.get(IUniversityDao.SqlAndList.LIST);
		
		return jt.queryForList(sql, list.toArray());
	}
	
	public Integer searchUniversitysRecordByCondition(University condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		
		EnumMap<SqlAndList, Object> retMap = getSqlAndList(condition);
		
		String sql = (String) retMap.get(IUniversityDao.SqlAndList.SQL);
		List list = (List)retMap.get(IUniversityDao.SqlAndList.LIST);
		
		return jt.queryForInt(sql, list.toArray());
	}
	
	public int searchUniversityIdByName(String name){
		
		JdbcTemplate jt = this.getJdbcTemplate();
		List list = new ArrayList();
		
		String sql = "SELECT id FROM tb_e_university WHERE english_name = ? ";
		list.add(name);
		
		return jt.queryForInt(sql, list.toArray());
	}
}
