package net.lx.biz.user;

import java.util.List;

import net.lx.entity.user.UserExtend;
import net.lx.entity.user.UserExtendConsultant;
import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;

/**
 * 用户业务层接口
 * 
 */
public interface IUserExtendConsultantBiz {

	/**
	 * 根据ID查询用户
	 */
	public UserExtendConsultant findUserById(int id) throws Exception ;

	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(UserExtendConsultant user) throws Exception;

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
	public void modify(UserExtendConsultant user) throws Exception;

	/**
	 * 根据用户Id查询用户扩展信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserExtendConsultant findUserConsultantByUserId(int userId) throws Exception;
	

	/**
	 * 根据扩展条件 查询 顾问扩展信息列表
	 */
	public List<UserExtendConsultant> searchUserConsultantsByCondition(UserExtendConsultant condition) throws Exception;
}
