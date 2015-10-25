package net.lx.dao.guide.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.dao.dic.ICountryDao;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionContentDao;
import net.lx.dao.guide.IGuideOptionDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideOptionContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuideOptionContentDaoImpl extends BaseMDDaoImpl<GuideOptionContent> implements IGuideOptionContentDao {
	
	public List<GuideOptionContent> findAllByGuideId(int guideId){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_guide_option_content WHERE guide_id = ? ";
		List list = new ArrayList();
		list.add(guideId);
		return jt.queryForList(sql, list.toArray());
	}
	
	public Integer findByIdentifier(int guideId, int optionId){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT id FROM tb_e_guide_option_content WHERE guide_id = ? AND option_id = ? ";
		List list = new ArrayList();
		list.add(guideId);
		list.add(optionId);
		Integer result = 0;
		try{
			result = jt.queryForInt(sql, list.toArray());
		}catch(Exception ex){
			result = 0;
		}
		return result;
	}
}
