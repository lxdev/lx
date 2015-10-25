package net.lx.biz.dic;

import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IStudyLevelBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public StudyLevel findStudyLevelById(int id) throws Exception;
	
	/**
	 * 字典
	 * @return
	 * @throws Exception
	 */
	public List<StudyLevel> findAll() throws Exception;
	
}
