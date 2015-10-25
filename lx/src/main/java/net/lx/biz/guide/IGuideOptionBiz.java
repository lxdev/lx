package net.lx.biz.guide;

import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IGuideOptionBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public GuideOption findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<GuideOption> findAll() throws Exception;

	public List<GuideOption> findAllParent() throws Exception;
	
	public boolean createNew(GuideOption option) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param 
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(GuideOption option) throws Exception;
}
