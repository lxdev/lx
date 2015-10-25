package net.lx.dao.user;

import java.util.Map;

import net.lx.dao.BaseDao;
import net.lx.entity.user.UserExtend;

/**
 * 用户数据层接口
 * 
 */

public interface IUserExtendDao extends BaseDao<UserExtend>
{	

	public Map findUserByUserId(int userId) throws Exception;
}
