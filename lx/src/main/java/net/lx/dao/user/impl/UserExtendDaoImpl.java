package net.lx.dao.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.dao.user.IUserExtendDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.user.UserExtend;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 用户数据层实现类
 * 
 */

@Repository
public class UserExtendDaoImpl extends BaseMDDaoImpl<UserExtend> implements IUserExtendDao
{

	public Map findUserByUserId(int userId) {
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_user_extend WHERE user_id = ? Limit 0, 1 ";
		List list = new ArrayList();
		list.add(userId);
		//return (UserExtend) jt.queryForObject(sql, list.toArray(), UserExtend.class);
		Map result = null;
		try{
			result = jt.queryForMap(sql, list.toArray());
		}
		catch(Exception e){
			
		}
		return result;
	}
}
