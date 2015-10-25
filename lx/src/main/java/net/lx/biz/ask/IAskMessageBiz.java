package net.lx.biz.ask;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IAskMessageBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public AskMessage findById(int id) throws Exception;
	
	
	public List<AskMessage> searchByCondition(AskMessage entity) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(AskMessage g) throws Exception;
	
	public void modify(AskMessage g) throws Exception;
}
