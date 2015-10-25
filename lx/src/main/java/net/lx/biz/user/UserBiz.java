package net.lx.biz.user;

import java.util.List;

import net.lx.entity.user.User;
import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;

/**
 * 用户业务层接口
 * 
 */
public interface UserBiz {

	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserForModel() throws Exception;

	/**
	 * 根据ID查询用户
	 */
	public User findUserById(int id) throws Exception ;

	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(User user) throws Exception;

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
	public void modify(User user) throws Exception;

	/**
	 * 按用户名查找用户
	 */
	public User findUserByUserName(String userName) throws Exception;

	/**
	 * 按mobile查找用户
	 */
	public User findUserByMobile(String mobile) throws Exception;
	/**
	 * 按email查找用户
	 */
	public User findUserByEmail(String email) throws Exception;
	/**
	 * 按条件查询用户列表
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByCondition(User user) throws Exception;
	
	/**
	 * 获取用户Ids
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public Long[]  findUserIds(PageParame p)throws Exception;
	
	/**
	 * 
	 * @功能：查询所有申请人
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 *@param name
	 * @return  
	 * @throws Exception
	 */
	
	public Long[] findUserByNames(String name)throws Exception;
	
	/**
	 * @功能：假删除用户 有验证条件
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param deleteUserId 要删除的用户id
	 * @param currentUserId 当前操作的用户id
	 * @return null:正确执行，非空则是错误消息
	 * @throws Exception
	 */
	public List<String> deleteFalseUserById(int deleteUserId,int currentUserId) throws Exception;
	
	/**
	 * @功能：还原假删除的用户
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param reductionUserId 要还原的用户id
	 * @param currentUserId 当前操作的用户id
	 * @return
	 * @throws Exception
	 */
	public boolean updateReductionUserById(int reductionUserId,int currentUserId) throws Exception;
	
	/**
	 * @功能：根据条件查询全部用户数量(分页)
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllUserCountByParams(User user,PageResult<User> pr) throws Exception;
	
	/**
	 * @功能：根据条件查询全部用户集合(分页)
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<User> findAllUserListByParams(User user,PageResult<User> pr) throws Exception;
	
}
