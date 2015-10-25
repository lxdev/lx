package net.lx.biz.university;

import java.util.List;

import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.SpecialtyRank;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface ISpecialtyRankBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public SpecialtyRank findById(int id) throws Exception;
	
	public List<SpecialtyRank> searchSpecialtyRanksByCondition(SpecialtyRank specialty) throws Exception;

}
