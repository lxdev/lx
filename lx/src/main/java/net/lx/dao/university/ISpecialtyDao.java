package net.lx.dao.university;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;

/**
 * 字典数据层接口
 * 
 */

public interface ISpecialtyDao extends BaseDao<Specialty>
{	
	public List<Specialty> searchSpecialtysByCondition(Specialty uni);

	public List<Specialty> findAllParent();
	public List<Specialty> findAllRoot();
	
	public Integer getChildCount(int id);
}
