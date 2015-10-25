package net.lx.dao.user;

import java.util.List;
import java.util.Map;

import net.lx.dao.BaseDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.user.UserExtend;
import net.lx.entity.user.UserExtendConsultant;

/**
 * 用户数据层接口
 * 
 */

public interface IUserExtendConsultantDao extends BaseDao<UserExtendConsultant>
{	

	public Map findUserConsultantByUserId(int userId) throws Exception;
	
	public List<UserExtendConsultant> searchUserConsultantsByCondition(UserExtendConsultant condition);
}
