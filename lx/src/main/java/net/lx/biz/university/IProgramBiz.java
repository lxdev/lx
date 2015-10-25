package net.lx.biz.university;

import java.util.List;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IProgramBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Program findProgramById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Program> findAll() throws Exception;
	
	public List<Program> searchProgramsByCondition(Program program) throws Exception;

	public String searchProgramsRecordByCondition(Program program);
	
	public Integer createNew(Program g) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Program g) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param 
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
}
