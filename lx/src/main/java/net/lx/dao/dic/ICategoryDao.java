package net.lx.dao.dic;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Category;
import net.lx.entity.dic.Country;

/**
 * 字典数据层接口
 * 
 */

public interface ICategoryDao extends BaseDao<Category>
{	
	public List<Category> searchParentCategorys();
	
	public List<Category> searchCategorysByParent(int id);
}
