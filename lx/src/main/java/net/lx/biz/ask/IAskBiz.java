package net.lx.biz.ask;

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
public interface IAskBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Ask findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Ask> findAll() throws Exception;
	
	public List<Ask> searchAsksByCondition(Ask entity) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(Ask g) throws Exception;
	
	public void modify(Ask g) throws Exception;
}
