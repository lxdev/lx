package net.lx.biz.university;

import java.util.List;

import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface ISpecialtyBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Specialty findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Specialty> findAll() throws Exception;

	public List<Specialty> findAllParent() throws Exception;
	public List<Specialty> findAllRoot() throws Exception;
	public List<Specialty> searchSpecialtysByCondition(Specialty specialty) throws Exception;

	public Integer createNew(Specialty g) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Specialty g) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param 
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	public Integer getChildCount(int id) throws Exception;
}
