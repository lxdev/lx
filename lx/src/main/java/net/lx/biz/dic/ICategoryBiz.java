package net.lx.biz.dic;

import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.Category;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface ICategoryBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Category findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Category> findAll() throws Exception;
	

	public List<Category> searchParentCategorys();
	
	public List<Category> searchCategorysByParent(int id);
}
