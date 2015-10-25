package net.lx.biz.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.IUserExtendConsultantBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.common.date.DateUtil;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.dao.user.IUserExtendConsultantDao;
import net.lx.dao.user.IUserExtendDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.university.Program;
import net.lx.entity.university.University;
import net.lx.entity.user.User;
import net.lx.entity.user.UserExtend;
import net.lx.entity.user.UserExtendConsultant;
import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现类
 * 
 */
@Service
public class UserExtendConsultantBizImpl implements IUserExtendConsultantBiz {

	@Autowired
	private IUserExtendConsultantDao dao;
	@Autowired
	private UserDao userDao;

	private UserExtendConsultant setAttribute(UserExtendConsultant entity) throws Exception{
		User user = userDao.findById(entity.getUser_id());
		entity.setUser(user);
		
		return entity;
	}
	/**
	 * 根据ID查询用户
	 */
	public UserExtendConsultant findUserById(int id) throws Exception {
		UserExtendConsultant entity = dao.findById(id);
		if(entity != null) {
			entity = setAttribute(entity);
		}
		return entity;
	}

	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(UserExtendConsultant user) throws Exception 
	{
		dao.save(user);
		return true;
	}

	
	/**
	 * 根据ID删除用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.deleteById(id);

	}

	/**
	 * 修改用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void modify(UserExtendConsultant user) throws Exception {
		dao.update(user);

	}

	/**
	 * 根据用户Id查询用户扩展信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserExtendConsultant findUserConsultantByUserId(int userId) throws Exception {
		Map map = dao.findUserConsultantByUserId(userId);
		if(map == null)
			return new UserExtendConsultant();
		UserExtendConsultant entity = MapToEntryConvertUtils.convert(map, UserExtendConsultant.class, "");
		if(entity != null) {
			entity = setAttribute(entity);
		}
		return entity;
	}
	

	/**
	 * 根据扩展条件 查询 顾问扩展信息列表
	 */
	public List<UserExtendConsultant> searchUserConsultantsByCondition(UserExtendConsultant condition) throws Exception{
		List<UserExtendConsultant> list = dao.searchUserConsultantsByCondition(condition);
		List<UserExtendConsultant> result = new ArrayList<UserExtendConsultant>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			UserExtendConsultant entity = MapToEntryConvertUtils.convert(map, UserExtendConsultant.class, "");
			if(entity != null) {
				entity = setAttribute(entity);
			}
			result.add(entity);
		}
		return result;
	}
}
