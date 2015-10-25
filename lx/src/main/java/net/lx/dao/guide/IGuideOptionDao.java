package net.lx.dao.guide;

import java.util.List;

import net.lx.dao.BaseDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;

/**
 * 字典数据层接口
 * 
 */

public interface IGuideOptionDao extends BaseDao<GuideOption>
{	
	public List<GuideOption> findAllParent();
}
