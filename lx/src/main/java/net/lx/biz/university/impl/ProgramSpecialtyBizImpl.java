package net.lx.biz.university.impl;

import java.util.List;

import net.lx.biz.university.IProgramSpecialtyBiz;
import net.lx.dao.university.IProgramSpecialtyDao;
import net.lx.entity.university.ProgramSpecialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class ProgramSpecialtyBizImpl implements IProgramSpecialtyBiz {
	@Autowired
	private IProgramSpecialtyDao dao;
	/**
	 * 根据ID查询国家
	 */
	public ProgramSpecialty findById(int id) throws Exception {
		return dao.findById(id);
	}

	public List<ProgramSpecialty> searchProgramSpecialtyRelation(ProgramSpecialty con) throws Exception {
		return dao.searchProgramSpecialtyRelation(con);
	}
	
	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(ProgramSpecialty g) throws Exception 
	{
		return (Integer)dao.save(g);
	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(ProgramSpecialty g) throws Exception {
		dao.update(g);
	}
	
	/**
	 * 根据ID删除用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.deleteById(id);
	}
}
