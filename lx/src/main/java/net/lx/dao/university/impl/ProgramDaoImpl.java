package net.lx.dao.university.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.dao.university.IProgramDao;
import net.lx.entity.university.Program;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramDaoImpl extends BaseMDDaoImpl<Program> implements IProgramDao {
	
	@Override
	public EnumMap<SqlAndList, Object> getSqlAndList(Program program){
		EnumMap<SqlAndList, Object> retMap = new EnumMap<SqlAndList, Object>(SqlAndList.class);
		
		String sql = " SELECT A.* FROM ( "
			+ " SELECT B.id, COUNT(A.id) program_num "
			+ " FROM tb_e_program A INNER JOIN tb_e_university B ON B.id = A.university_id LEFT JOIN tb_d_area E ON E.id = B.area_id "
			+ " LEFT JOIN (SELECT university_id, count(*) evaluate_number FROM tb_e_evaluate_university GROUP BY university_id) C ON C.university_id = B.id "
			+ " LEFT JOIN tb_e_university_statistic D ON D.university_id = B.id "
			+ " WHERE A.status = 1 ";
		String sql_part2 = " GROUP BY B.id "
			+ " LIMIT " + program.getPage_size() + " OFFSET " + (program.getPage() >= 1 ? (program.getPage()-1)*program.getPage_size() : 0) 
			//+ " UNION SELECT B.id, 0 program_num FROM tb_e_university B LIMIT 10 OFFSET 0 "
			+ " ) T INNER JOIN tb_e_program A ON A.university_id = T.id INNER JOIN tb_e_university B ON B.id = A.university_id LEFT JOIN tb_d_area E ON E.id = B.area_id "
			+ " WHERE 1 = 1 ";
		
		if(program.getIsSearchTotal() != null && program.getIsSearchTotal())
			sql = "SELECT concat( COUNT(A.id), ',',  count(Distinct B.id) ) FROM tb_e_program A INNER JOIN tb_e_university B ON B.id = A.university_id " 
				+ " LEFT JOIN tb_d_area E ON E.id = B.area_id "
				+ " LEFT JOIN (SELECT university_id, count(*) evaluate_number FROM tb_e_evaluate_university GROUP BY university_id) C ON C.university_id = B.id "
				+ " LEFT JOIN tb_e_university_statistic D ON D.university_id = B.id "
				+ " WHERE A.status = 1 ";

		String condition = "";
		List listInner = new ArrayList();
		List listOuter = new ArrayList();
		if(program.getCountryId() != null && program.getCountryId() > 0){
			condition += " AND B.country_id = " + program.getCountryId();
		}
		if(program.getStudy_level_id() != null && program.getStudy_level_id() > 0){
			condition += " AND A.study_level_id = " + program.getStudy_level_id();
		}
		if(program.getSpecialtyId() != null && program.getSpecialtyId() != 0){
			/* 输入专业是 2级的，则直接到 关系中找即可；
			 * 输入专业是1级的，则将其对于的子专业都找到，然后和关系中对应 */
			condition += " AND EXISTS (SELECT * FROM tb_e_program_specialty PS INNER JOIN tb_e_specialty S ON S.id = PS.specialty_id WHERE A.id = PS.program_id "
				//查询的是方向：只要方向满足
					+ " AND ((S.depth = 2 AND S.id = " + program.getSpecialtyId() + ") "
				//查询的是专业： 专业 (depth=1) 满足，或 其下方向(dpeth=2)满足
				+ "		OR (S.depth = 1 AND S.id = " + program.getSpecialtyId() + ") "
				+ "		OR (S.depth = 2 AND EXISTS (SELECT * FROM tb_e_specialty S1 WHERE S1.id = S.id AND S1.parent_id = " + program.getSpecialtyId() + "))))";
		}
		else if(program.getSpecialtyName() != null && !program.getSpecialtyName().equals("")){
			condition += " AND EXISTS (SELECT * FROM tb_e_program_specialty PS INNER JOIN tb_e_specialty S ON S.id = PS.specialty_id WHERE A.id = PS.program_id "
				+ " AND ((S.depth = 2 AND S.specialty_name LIKE ? OR S.specialty_english_name LIKE ?) "
				+ "		OR (S.depth = 1 AND (S.specialty_name LIKE ? OR S.specialty_english_name LIKE ?)) "	
				+ " 	OR (S.depth = 2 AND EXISTS (SELECT * FROM tb_e_specialty S1 INNER JOIN tb_e_specialty S2 ON S2.id = S1.parent_id WHERE S1.id = S.id AND S2.specialty_name LIKE ? OR S2.specialty_english_name LIKE ? )))) ";
			listInner.add("%"+program.getSpecialtyName()+"%");
			listInner.add("%"+program.getSpecialtyName()+"%");
			listInner.add("%"+program.getSpecialtyName()+"%");
			listInner.add("%"+program.getSpecialtyName()+"%");
			listInner.add("%"+program.getSpecialtyName()+"%");
			listInner.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
			listOuter.add("%"+program.getSpecialtyName()+"%");
		} 
		
		if(program.getRankingBegin() != null && program.getRankingBegin() != 0){
			condition += " AND B.ranking_comprehensive >= " + program.getRankingBegin();
		}
		if(program.getRankingEnd() != null && program.getRankingEnd() != 0){
			condition += " AND B.ranking_comprehensive < " + program.getRankingEnd();
		}
		if(program.getAreaName() != null && !program.getAreaName().equals("")){
			condition += " AND E.name = '" + program.getAreaName() + "'";
		}
		if(program.getScore_totef() != -1){
			condition += " AND (A.score_totef = 0 OR (A.score_totef >= " + program.getScore_totef();
			if(program.getTotefEnd() != -1){
				condition += " AND A.score_totef < " + program.getTotefEnd();
			}
			condition += "))";
		}
		if(program.getScore_ietls() != -1){
			condition += " AND (A.score_ietls = 0 OR (A.score_ietls >= " + program.getScore_ietls();
			if(program.getIetlsEnd() != -1){
				condition += " AND A.score_ietls < " + program.getIetlsEnd();
			}
			condition += "))";
		}
		if(program.getScore_gre() != -1){
			condition += " AND (A.score_gre = 0 OR (A.score_gre >= " + program.getScore_gre();
			if(program.getGreEnd() != -1){
				condition += " AND A.score_gre < " + program.getGreEnd();
			}
			condition += "))";
		}
		if(program.getScore_gmat() != -1){
			condition += " AND (A.score_gmat = 0 OR (A.score_gmat >= " + program.getScore_totef();
			if(program.getTotefEnd() != -1){
				condition += " AND A.score_gmat < " + program.getGmatEnd();
			}
			condition += "))";
		}
		if(program.getIs_language_score() != null && program.getIs_language_score() != -1){
			condition += " AND A.is_language_score = " + program.getIs_language_score();
		}
		if(program.getTime_of_enrollment() != null){
			condition += " AND A.time_of_enrollment LIKE '%" + program.getTime_of_enrollment() + "%'";
		}
		if(program.getWork_experience_require() != null && program.getWork_experience_require() != -1){
			condition += " AND A.work_experience_require = " + program.getWork_experience_require();
		}
		if(program.getProgram_name() != null && !program.getProgram_name().equals("")){
//			condition += " AND A.program_name LIKE ? ";
//			listInner.add("%"+program.getProgram_name()+"%");
//			listOuter.add("%"+program.getProgram_name()+"%");
			condition += " AND A.program_name = ? ";
			listInner.add(program.getProgram_name());
			listOuter.add(program.getProgram_name());
		}
		if(program.getUniversity_id() != null && program.getUniversity_id() > 0){
			condition += " AND A.university_id = " + program.getUniversity_id();
		}else if(program.getUniversityName() != null && !program.getUniversityName().equals("")){
			if(program.getIsSearchTotal() != null && program.getIsSearchTotal()){
				condition += " AND (B.university_name like '%" + program.getUniversityName() + "%' OR B.english_name like '%" + program.getUniversityName() + "%' OR B.abbr = '" + program.getUniversityName() + "')";
			}else {
				condition += " AND (B.university_name like ? OR B.english_name like ? OR B.abbr = ?)";
				listInner.add("%"+program.getUniversityName()+"%");
				listInner.add("%"+program.getUniversityName()+"%");
				listInner.add(program.getUniversityName());
				listOuter.add("%"+program.getUniversityName()+"%");
				listOuter.add("%"+program.getUniversityName()+"%");
				listOuter.add(program.getUniversityName());
			}
		}
		if(program.getIsSearchTotal() != null && program.getIsSearchTotal()){
			sql += condition;
		}else{
			sql += condition + sql_part2 + condition;
		}

		List list = listInner;
		for(Object o : listOuter){
			list.add(o);
		}
		
		retMap.put(SqlAndList.SQL, sql);
		retMap.put(SqlAndList.LIST, list);
		return retMap;
	}
	
	public List<Program> searchProgramsByCondition(Program program){
		JdbcTemplate jt = this.getJdbcTemplate();
		
		EnumMap<SqlAndList, Object> retMap = getSqlAndList(program);
		
		String sql = (String) retMap.get(IProgramDao.SqlAndList.SQL);
		List list = (List)retMap.get(IProgramDao.SqlAndList.LIST);
		
		return jt.queryForList(sql, list.toArray());
	}
	
	public String searchProgramsRecordByCondition(Program program){
		JdbcTemplate jt = this.getJdbcTemplate();
		
		EnumMap<SqlAndList, Object> retMap = getSqlAndList(program);
		
		String sql = (String) retMap.get(IProgramDao.SqlAndList.SQL);
		List list = (List)retMap.get(IProgramDao.SqlAndList.LIST);
		
		//return jt.queryForInt(sql, list.toArray());
		return (String)jt.queryForObject(sql, new Object[]{}, java.lang.String.class);
	}
	
}
