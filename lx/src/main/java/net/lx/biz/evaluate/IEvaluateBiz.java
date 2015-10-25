package net.lx.biz.evaluate;

import java.util.List;
import net.lx.entity.evaluate.Evaluate;


/**
 * Country 业务层接口
 * @author lxl
 *
 */
public interface IEvaluateBiz 
{	
	
	/**
	 * 根据ID查询字典
	 */
	public Evaluate findById(int id) throws Exception;
	
	/**
	 * 查询字典
	 * @return
	 * @throws Exception
	 */
	public List<Evaluate> findAll() throws Exception;
	
	public List<Evaluate> searchEvaluatesByCondition(Evaluate entity) throws Exception;
	
	public Boolean deleteById(int id);
	
	public Integer createNew(Evaluate g) throws Exception;
	
	public void modify(Evaluate g) throws Exception;
}
