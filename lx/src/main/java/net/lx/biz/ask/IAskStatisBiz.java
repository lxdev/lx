package net.lx.biz.ask;

import java.util.List;

import net.lx.entity.ask.AskStatis;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IAskStatisBiz 
{	
	public List<AskStatis> searchAskStatisByCondition(int ask_id) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(AskStatis g) throws Exception;
	
	public void modify(AskStatis g) throws Exception;
}
