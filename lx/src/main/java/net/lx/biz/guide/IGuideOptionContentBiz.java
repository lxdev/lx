package net.lx.biz.guide;

import java.util.List;

import net.lx.entity.guide.GuideOptionContent;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IGuideOptionContentBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public GuideOptionContent findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<GuideOptionContent> findAll() throws Exception;

	public List<GuideOptionContent> findByGuideId(int guideId) throws Exception;
	
	public void saveAllByGuide(List<GuideOptionContent> contents);
	
	public boolean createNew(GuideOptionContent option) throws Exception;

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
	public void modify(GuideOptionContent option) throws Exception;
}
