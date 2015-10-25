package net.lx.biz.dic;

import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.entity.dic.Country;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface ICountryBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Country findCountryById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Country> findAll() throws Exception;
	
}
