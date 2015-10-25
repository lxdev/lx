package net.lx.biz.ask;

import java.util.List;

import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskReply;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IAskReplyBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public AskReply findById(int id) throws Exception;
	
	public List<AskReply> searchAskReplysByCondition(AskReply condition) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(AskReply g) throws Exception;
	
	public void modify(AskReply g) throws Exception;
}
