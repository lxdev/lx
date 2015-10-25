package net.lx.biz.crm;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IFollowBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Follow findById(int id) throws Exception;
	
	public List<Follow> searchByCondition(Follow entity) throws Exception;

	public Boolean deleteById(int id);
	
	public Integer createNew(Follow g) throws Exception;
	
	public void modify(Follow g) throws Exception;
}
