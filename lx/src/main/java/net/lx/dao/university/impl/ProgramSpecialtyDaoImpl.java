package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IProgramSpecialtyDao;
import net.lx.entity.university.ProgramSpecialty;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramSpecialtyDaoImpl extends BaseMDDaoImpl<ProgramSpecialty> implements IProgramSpecialtyDao {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProgramSpecialty> searchProgramSpecialtyRelation(ProgramSpecialty con){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_program_specialty A WHERE A.delete_flag = 0 ";
		List list = new ArrayList();
		if(con.getProgram_id() != null && con.getProgram_id() > 0){
			sql += " AND A.program_id = ?";
			list.add(con.getProgram_id());
		}
		if(con.getSpecialty_id() != null && con.getSpecialty_id() > 0){
			sql += " AND A.specialty_id = ?";
			list.add(con.getSpecialty_id());
		}
		
		return jt.queryForList(sql, list.toArray());
	}
}
