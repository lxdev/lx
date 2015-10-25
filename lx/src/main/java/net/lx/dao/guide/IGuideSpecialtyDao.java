package net.lx.dao.guide;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideSpecialty;

/**
 * 字典数据层接口
 * 
 */

public interface IGuideSpecialtyDao extends BaseDao<GuideSpecialty>
{	
	public List<GuideSpecialty> findAllParent();
	
	public List<GuideSpecialty> searchGuideSpecialtysByCondition(GuideSpecialty spe);
}
