package net.lx.biz.user;

import java.util.List;

import net.lx.entity.user.UserExtend;
import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;

/**
 * 用户业务层接口
 * 
 */
public interface IUserExtendBiz {

	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserExtend> findUserForModel() throws Exception;

	/**
	 * 根据ID查询用户
	 */
	public UserExtend findUserById(int id) throws Exception ;

	/**
	 * 根据用户Id查询用户扩展信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserExtend findUserByUserId(int userId) throws Exception;
	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(UserExtend user) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void modify(UserExtend user) throws Exception;

	/**
	 * 获取用户Ids
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public Long[]  findUserIds(PageParame p)throws Exception;
	
}
