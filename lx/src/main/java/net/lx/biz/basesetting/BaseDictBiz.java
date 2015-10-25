package net.lx.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.lx.entity.basesetting.BaseDict;

/**
 * 基础字典
 * 
 * @author HXJ
 */
public interface BaseDictBiz {

	/**
	 * 增加基础字典
	 * 
	 * @param basedict
	 * @return
	 */
	public boolean addBaseDict(BaseDict basedict) throws Exception;

	/**
	 * 修改基础字典
	 * 
	 * @param basedict
	 * @return
	 */
	public boolean modifyBaseDict(BaseDict basedict) throws Exception;

	// /**
	// * 按主键删除(物理删除)
	// * @param id
	// * @return
	// */
	// public BaseDict deleteBaseDictById(Serializable id);
	//
	// /**
	// * 按主键删除(逻辑删除)
	// * @param id
	// * @return
	// */
	// public int deleteBaseDictByFlag(int id);

	/**
	 * 按type查询基础字典列表(delete_flag=0)
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<BaseDict> findAllBaseDictsByTypeAndFlag(int type)
			throws Exception;

	/**
	 * 按type查询基础字典列表
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<BaseDict> findAllBaseDictsByType(int type) throws Exception;
	

	/**
	 * 按主键id查询基础字典
	 * 
	 * @param id
	 * @return
	 */
	public BaseDict findBaseDictById(Serializable id);

	/**
	 * 删除(读取配置文件)
	 * 
	 * @param id
	 * @return
	 */
	public BaseDict deleteConfigBaseDict(int id) throws Exception;

	/**
	 * 根据字典类型&父级
	 * 
	 * @param type
	 *            字典类型
	 * @param parentNode
	 *            父级
	 * @return
	 * @throws Exception
	 */
	public List<BaseDict> findBaseDictsByTypeAndParentNodeAndDeleteFlag(
			int type, int parentNode) throws Exception;

	/**
	 * 通过类型查询树形结构
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<BaseDict> findBaseDictsByType(int type) throws Exception;
	
	/**
	 * 按类型和学习中心ID查询字典树形结构
	 * 
	 * 
	 */
	public List<BaseDict> findBaseDictsByType(int type,int branchId) throws Exception;
	
	/**
	 * 按typeId & 名称查询基础字典
	 * @param type
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public BaseDict findBaseDictsByTypeAndName(int type,String name) throws Exception;
	

}
