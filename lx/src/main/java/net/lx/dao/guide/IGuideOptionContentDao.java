package net.lx.dao.guide;

import java.util.List;
import java.util.Map;

import net.lx.dao.BaseDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideOptionContent;

/**
 * 字典数据层接口
 * 
 */

public interface IGuideOptionContentDao extends BaseDao<GuideOptionContent>
{	
	public List<GuideOptionContent> findAllByGuideId(int guideId);
	
	public Integer findByIdentifier(int guideId, int optionId);
}
