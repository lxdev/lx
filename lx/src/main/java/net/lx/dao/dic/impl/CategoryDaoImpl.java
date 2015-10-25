package net.lx.dao.dic.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICategoryDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Category;
import net.lx.entity.dic.Country;
import net.lx.entity.university.Program;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends BaseMDDaoImpl<Category> implements ICategoryDao {
	
	public List<Category> searchParentCategorys(){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_d_category WHERE parent_id = 0 ";
		List list = new ArrayList();
		
		return jt.queryForList(sql, list.toArray());
	}
	
	public List<Category> searchCategorysByParent(int id){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_d_category WHERE parent_id = ? ";
		List list = new ArrayList();
		list.add(id);
		
		return jt.queryForList(sql, list.toArray());
	}
}
