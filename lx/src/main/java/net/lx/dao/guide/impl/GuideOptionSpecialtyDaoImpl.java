package net.lx.dao.guide.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.dic.ICountryDao;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionDao;
import net.lx.dao.guide.IGuideSpecialtyDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideSpecialty;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuideOptionSpecialtyDaoImpl extends BaseMDDaoImpl<GuideSpecialty> implements IGuideSpecialtyDao {
	public List<GuideSpecialty> findAllParent(){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_guide_specialty WHERE parent_id = 0 ";
		List list = new ArrayList();
		return jt.queryForList(sql, list.toArray());
	}
	
	public List<GuideSpecialty> searchGuideSpecialtysByCondition(GuideSpecialty spe){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_guide_specialty WHERE parent_id = ? ";
		List list = new ArrayList();
		list.add(spe.getParent_id());
		return jt.queryForList(sql, list.toArray());
	}
}
