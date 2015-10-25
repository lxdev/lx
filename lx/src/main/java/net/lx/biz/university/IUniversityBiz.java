package net.lx.biz.university;

import java.util.List;

import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IUniversityBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public University findUniversityById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<University> findAll() throws Exception;
	
	public List<University> searchUniversitysByCondition(University university) throws Exception;

	public Integer searchUniversitysRecordByCondition(University condition);
}
