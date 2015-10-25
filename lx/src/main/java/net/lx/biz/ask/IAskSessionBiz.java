package net.lx.biz.ask;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.ask.AskSession;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IAskSessionBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public AskSession findById(int id) throws Exception;
	
	
	public List<AskSession> searchByCondition(AskSession entity) throws Exception;
	
	public Integer createNew(AskSession g) throws Exception;
	
	public void modify(AskSession g) throws Exception;
}
