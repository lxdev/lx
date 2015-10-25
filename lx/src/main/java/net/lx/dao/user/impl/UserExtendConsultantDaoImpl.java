package net.lx.dao.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.dao.user.IUserExtendConsultantDao;
import net.lx.dao.user.IUserExtendDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.ask.Ask;
import net.lx.entity.user.UserExtend;
import net.lx.entity.user.UserExtendConsultant;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 用户数据层实现类
 * 
 */

@Repository
public class UserExtendConsultantDaoImpl extends BaseMDDaoImpl<UserExtendConsultant> implements IUserExtendConsultantDao
{	

	/**
	 * 根据用户id 查询 顾问扩展信息
	 */
	public Map findUserConsultantByUserId(int userId) throws Exception {
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT * FROM tb_e_user_extend_consultant WHERE user_id = ? Limit 0, 1 ";
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
	
	/**
	 * 根据扩展条件 查询 顾问扩展信息列表
	 */
	public List<UserExtendConsultant> searchUserConsultantsByCondition(UserExtendConsultant condition){
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql = "SELECT A.* FROM tb_e_user_extend_consultant A WHERE 1 = 1 ";
		List list = new ArrayList();
		if(condition.getGoodat_countrys() != null && !condition.getGoodat_countrys().equals("")){
			sql += " AND (A.goodat_countrys LIKE ? OR A.goodat_countrys LIKE ? OR A.goodat_countrys LIKE ?) ";
			list.add(condition.getGoodat_countrys() + ",%");
			list.add("%," + condition.getGoodat_countrys() + ",%");
			list.add("%," + condition.getGoodat_countrys());
		}
		if(condition.getGoodat_specialtys() != null && !condition.getGoodat_specialtys().equals("")){
			sql += " AND (A.goodat_specialtys LIKE ? OR A.goodat_specialtys LIKE ? OR A.goodat_specialtys LIKE ?)";
			list.add(condition.getGoodat_specialtys() + ",%");
			list.add("%," + condition.getGoodat_specialtys() + ",%");
			list.add("%," + condition.getGoodat_specialtys());
		}
		if(condition.getAddress_city() != null && !condition.getAddress_city().equals("")){
			sql += " AND A.address_city = ?";
			list.add(condition.getAddress_city());
		}
		if(condition.getUser_type() != null && condition.getUser_type() > 0){
			sql += " AND A.user_type = ?";
			list.add(condition.getUser_type());
		}
		if(condition.getService_type() != null && condition.getService_type() > 0){		//使用这个字段来保存  总服务项  和 单项服务
			sql += " AND (A.service_type = ? OR A.service_type_single = ?)";
			list.add(condition.getService_type());
		}
		
		sql += " ORDER BY created_date desc ";
		
		return jt.queryForList(sql, list.toArray());
	}
}
