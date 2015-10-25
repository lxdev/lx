package net.lx.biz.dic;

import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Country;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IAreaBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Area findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Area> findAll() throws Exception;
	
}
