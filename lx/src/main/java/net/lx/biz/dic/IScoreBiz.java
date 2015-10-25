package net.lx.biz.dic;

import java.util.List;

import net.lx.entity.dic.Score;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IScoreBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Score findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Score> findAll() throws Exception;
	
	public List<Score> searchScoresByType(int type) throws Exception;
}
