package net.lx.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.dao.user.UserDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.university.University;
import net.lx.entity.user.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 用户数据层实现类
 * 
 */

@Repository
public class UserDaoImpl extends BaseMDDaoImpl<User> implements UserDao
{	

//	public List<User> searchUserByCondition(User condition){
//		JdbcTemplate jt = this.getJdbcTemplate();
//		String sql = "SELECT DISTINCT A.* FROM tb_e_user A ";
//		List list = new ArrayList();
//		
//		sql += " AND A.status = 1 AND A.delete_flag = 0 ";
//		if(condition.getFull_name() != null && !condition.getFull_name().equals("")){
//			sql += " AND A.full_name like ?";
//			list.add("%" + condition.getFull_name() + "%");
//		}
//		
//		
//		return jt.queryForList(sql, list.toArray());
//	}
}
