package net.lx.biz.university;

import java.util.List;

import net.lx.entity.university.ProgramSpecialty;

/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IProgramSpecialtyBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public ProgramSpecialty findById(int id) throws Exception;

	public List<ProgramSpecialty> searchProgramSpecialtyRelation(ProgramSpecialty con) throws Exception;

	public Integer createNew(ProgramSpecialty g) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(ProgramSpecialty g) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param 
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
}
