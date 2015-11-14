package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.ask.IAskDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialtyDaoImpl extends BaseMDDaoImpl<Specialty> implements ISpecialtyDao {
	
	
	public List<Specialty> searchSpecialtysByCondition(Specialty spe){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_specialty A WHERE 1 = 1 AND depth != 0 ";
		List list = new ArrayList();
//		sql += " AND A.country_id = ?";
//		list.add(spe.getCountry_id());
		if(spe.getSpecialty_english_name() != null && !spe.getSpecialty_english_name().equals("")){
			sql += " AND specialty_english_name = ? ";
			list.add(spe.getSpecialty_english_name());
		}else if(spe.getSpecialty_name() != null && !spe.getSpecialty_name().equals("")){
			sql += " AND (A.specialty_name like ?";
			list.add("%"+spe.getSpecialty_name()+"%");
			sql += " OR A.specialty_english_name like ?";
			list.add("%"+spe.getSpecialty_name()+"%");
			sql += " OR A.specialty_attr like ? )";
			list.add("%"+spe.getSpecialty_name()+"%");
		}
		if(spe.getParent_id() != null){
			sql += " AND A.parent_id = ?";
			list.add(spe.getParent_id());
		}
		
		return jt.queryForList(sql, list.toArray());
	}
	
	public List<Specialty> findAllParent(){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_specialty WHERE depth = 1 ";
		List list = new ArrayList();
		return jt.queryForList(sql, list.toArray());
	}
	public List<Specialty> findAllRoot(){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_specialty WHERE depth = 0 ";
		List list = new ArrayList();
		return jt.queryForList(sql, list.toArray());
	}
	
	public Integer getChildCount(int id){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT COUNT(id) FROM tb_e_specialty WHERE parent_id = ?";
		List list = new ArrayList();
		list.add(id);
		Integer result = 0;
		try{
			result = jt.queryForInt(sql, list.toArray());
		}catch(Exception ex){
			result = 0;
		}
		return result;
	}
}
