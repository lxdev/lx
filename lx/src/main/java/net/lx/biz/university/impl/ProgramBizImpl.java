package net.lx.biz.university.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.university.IProgramBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.dic.IStudyLevelDao;
import net.lx.dao.university.IProgramDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.university.Program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class ProgramBizImpl implements IProgramBiz {
	@Autowired
	private IProgramDao dao;
	
	@Autowired
	private IUniversityBiz universityBiz;
	@Autowired
	private IStudyLevelDao studyLevelDao;
	
	private Program setAttribute(Program entity) throws Exception{
		if(entity != null){
			entity.setUniversity(universityBiz.findUniversityById(entity.getUniversity_id()));
			entity.setStudyLevel(studyLevelDao.findById(entity.getStudy_level_id()));
		}
		
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public Program findProgramById(int id) throws Exception {
		Program pro = dao.findById(id);
		pro = setAttribute(pro);
		return pro;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Program> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by program_name ";
		return dao.getByProperty(hqlcon, paramList);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Program> findListById(int id) throws Exception {
		try {
			List<Program> results = null;
			PageParame p = new PageParame();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Program>();
				for(int i = 0; i < ids.length; i++) {
					Program obj = dao.findById(Integer.valueOf(ids[i].toString()));
					if(obj != null) {
						obj = setAttribute(obj);
						results.add(obj);
					}
				}
			}
			return results;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public List<Program> searchProgramsByCondition(Program program) throws Exception {
		//return programDao.searchProgramsByCondition(program);
		List<Program> list = dao.searchProgramsByCondition(program);
		List<Program> result = new ArrayList<Program>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			Program p = MapToEntryConvertUtils.convert(map, Program.class, "");
			p = setAttribute(p);
			result.add(p);
		}
		
		return result;
	}
	
	public String searchProgramsRecordByCondition(Program program){
		return dao.searchProgramsRecordByCondition(program);
	}

	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Program g) throws Exception 
	{
		//g.setProgram_name(g.getProgram_name().replace("'", "''"));
		return (Integer)dao.save(g);
	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Program g) throws Exception {
		//g.setProgram_name(g.getProgram_name().replace("'", "''"));
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
