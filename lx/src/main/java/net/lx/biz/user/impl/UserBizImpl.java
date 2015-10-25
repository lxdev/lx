package net.lx.biz.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.common.date.DateUtil;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.dao.user.IUserExtendConsultantDao;
import net.lx.dao.user.IUserExtendDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
import net.lx.entity.evaluate.EvaluateReply;
import net.lx.entity.university.Program;
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
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDao userDao;
	@Autowired
	private IUserExtendDao extendDao;
	@Autowired
	private IUserExtendConsultantDao consultantDao;
	
	private User setUserAttribute(User entity) throws Exception{
		if(entity.getUser_type() == Constants.USER_STUDENT){
			Map extendMap = extendDao.findUserByUserId(entity.getUser_id());
			UserExtend ue = new UserExtend();
			if(extendMap != null)
				ue = MapToEntryConvertUtils.convert(extendMap, UserExtend.class, "");
			entity.setUserExtendStudent(ue);
		}
		else if(entity.getUser_type() == Constants.USER_CONSULTANT){
			Map consultantMap = consultantDao.findUserConsultantByUserId(entity.getUser_id());
			UserExtendConsultant uc = new UserExtendConsultant();
			if(consultantMap != null)
				uc = MapToEntryConvertUtils.convert(consultantMap, UserExtendConsultant.class, "");
			entity.setUserExtendConsultant(uc);
		}
			
		return entity;
	}
	
	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserForModel() throws Exception {
		List<User> list = userDao.findAll();
		List<User> result = new ArrayList<User>();
		for(User user : list){
			if(user != null)
				user = setUserAttribute(user);
			result.add(user);
		}
		return result;
	}

	/**
	 * 根据ID查询用户
	 */
	public User findUserById(int id) throws Exception {
		User user=userDao.findById(id);
//		user.setOrg(branchBiz.findBranchById(user.getOrgId()));
		if(user != null)
			user = setUserAttribute(user);
		return user;
	}

	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(User user) throws Exception 
	{
		if(null==findUserByUserName(user.getUser_name()))
		{
			user.setPassword(PwdCoder.getMD5(user.getPassword()));
			userDao.save(user);
			return true;
		}
		return false;
	}

	
	/**
	 * 根据ID删除用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		userDao.deleteConfig(id);

	}

	/**
	 * 修改用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public void modify(User user) throws Exception {
		userDao.update(user);

	}

	/**
	 * 按用户名查找用户
	 */
	public User findUserByUserName(String userName) throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(userName)) {
			hqlcon += " and user_name=" + Constants.PLACEHOLDER;
			paramList.add(userName);
		}
		if (0 < hqlcon.length())
			list = userDao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size()){
			User u = list.get(0);
			if(u != null)
				return setUserAttribute(u);
		}
		return null;
	}

	/**
	 * 按mobile查找用户
	 */
	public User findUserByMobile(String mobile) throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(mobile)) {
			hqlcon += " and user_mobile=" + Constants.PLACEHOLDER;
			paramList.add(mobile);
		}
		if (0 < hqlcon.length())
			list = userDao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size()){
			User u = list.get(0);
			if(u != null)
				return setUserAttribute(u);
		}
		return null;
	}
	/**
	 * 按email查找用户
	 */
	public User findUserByEmail(String email) throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(email)) {
			hqlcon += " and user_email=" + Constants.PLACEHOLDER;
			paramList.add(email);
		}
		if (0 < hqlcon.length())
			list = userDao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size()){
			User u = list.get(0);
			if(u != null)
				return setUserAttribute(u);
		}
		return null;
	}
	/**
	 * 按条件查询用户列表
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByCondition(User user) throws Exception 
	{
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();
		
		hqlcon+=" and status="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.StatusStart.value());
		hqlcon+=" and delete_flag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		if(user.getUser_type() != null && user.getUser_type() != 0){
			hqlcon += " AND user_type = " + Constants.PLACEHOLDER;
			paramList.add(user.getUser_type());
		}
		if(user.getFull_name() != null && !user.getFull_name().equals("")){
			hqlcon += " AND full_name like " + Constants.PLACEHOLDER;
			paramList.add("%" + user.getFull_name() + "%");
		}
		
		list = userDao.getByProperty(hqlcon, paramList);
		List<User> result = new ArrayList<User>();
		for(User u : list){
			if(u != null)
				u = setUserAttribute(u);
			result.add(u);
		}
		return result;
	}
	
	/**
	 * 查询总部用户
	 * 
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersForAdmin() throws Exception 
	{
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();
		hqlcon+=" and status="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.StatusStart.value());
		hqlcon+=" and delete_flag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		list = userDao.getByProperty(hqlcon, paramList);
		return list;
	}
	
	/*
	 * 获取用户Ids
	 */
	public Long[] findUserIds(PageParame p) throws Exception {
		
		return userDao.getIDs(p);
	}

	
	/**
	 * 查询所有申请人
	 */
	public Long[] findUserByNames(String name) throws Exception {
		List<Object> list=new ArrayList<Object>();
		Long [] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and first_name like " + Constants.PLACEHOLDER;
			list.add('%'+name+'%');
		}
		 
		
		ids = userDao.getIDs(hql, list.toArray());
		return ids;
	}

	/*
	 * 假删除用户 有验证条件
	 */
	public List<String> deleteFalseUserById(int deleteUserId,int currentUserId) throws Exception {
		// 每个错误消息最多输出条数
		int errorCount = 3;
		List<String> errorList = null;
		// 没有用户id
		if(deleteUserId==0){
			errorList = new ArrayList<String>();
			errorList.add("请选择要删除的用户！");
			return errorList;
		}
		// 用户不可删除自己
		if(deleteUserId!=0 && deleteUserId==currentUserId){
			errorList = new ArrayList<String>();
			errorList.add("用户不可删除自己！");
			return errorList;
		}
		// root等特殊用户不可删除
		if(deleteUserId<0){
			errorList = new ArrayList<String>();
			errorList.add("特殊用户不可删除！");
			return errorList;
		}
		User user = userDao.findById(deleteUserId);
		if(user!=null){
			// 如果是假删除状态则跳过
			if(user.getDelete_flag()==Constants.DELETE_TRUE){
				errorList = new ArrayList<String>();
				errorList.add(user.getFull_name()+":该用户已被删除！");
				return errorList;
			}
				// 删除相关垃圾数据，将用户设置为禁用，假删除
				try {
					// 假删除用户
					user.setStatus(1);
					user.setDelete_flag(Constants.DELETE_TRUE);
					user.setUpdated_user_id(currentUserId);
					user.setUpdated_date(DateUtil.getNow());
					this.modify(user);
					return null;
				} catch (Exception e) {
					errorList = new ArrayList<String>();
					errorList.add("数据异常！");
					return errorList;
				}
			
		}else{
			errorList = new ArrayList<String>();
			errorList.add("id:"+deleteUserId+"无此用户！");
			return errorList;
		}
	}

	/*
	 * 还原假删除的用户
	 */
	public boolean updateReductionUserById(int reductionUserId, int currentUserId)
			throws Exception {
		// 没有用户id
		if(reductionUserId==0){
			return false;
		}
		User user = userDao.findById(reductionUserId);
		// 用户存在 且是假删除状态
		if(user!=null && user.getDelete_flag()==Constants.DELETE_TRUE){
			// 默认禁用
			user.setStatus(1);
			user.setDelete_flag(Constants.DELETE_FALSE);
			user.setUpdated_user_id(currentUserId);
			user.setUpdated_date(DateUtil.getNow());
			this.modify(user);
			return true;
		}
		return false;
	}

	/*
	 * 根据条件查询全部用户数量(分页)
	 */
	public int findAllUserCountByParams(User user, PageResult<User> pr)
			throws Exception {
		PageParame p = new PageParame();
		String hqlConditionExpression = "";
		List<Object> list = null;
		if(user!=null){
			list = new ArrayList<Object>();
			// 用户名
			if(user.getUser_name()!=null&&!user.getUser_name().equals("")){
				hqlConditionExpression += " and user_name like " + Constants.PLACEHOLDER;
				list.add("%" + user.getUser_name() + "%");
			}
			// 姓名
			if(user.getFull_name()!=null&&!user.getFull_name().equals("")){
				hqlConditionExpression += " and first_name = " + Constants.PLACEHOLDER;
				list.add("%" + user.getFull_name() + "%");
			}
			// 删除状态
			if(user.getDelete_flag()!=-1){
				hqlConditionExpression += " and delete_flag = " + Constants.PLACEHOLDER;
				list.add(user.getDelete_flag());
			}
			
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			return userDao.getCounts(p);
		}
		return 0;
	}

	/*
	 * 根据条件查询全部用户集合(分页)
	 */
	public List<User> findAllUserListByParams(User user, PageResult<User> pr)
			throws Exception {
		List<User> userList = null;
		PageParame p = new PageParame(pr);
		String hqlConditionExpression = "";
		List<Object> list = null;
		if(user!=null){
			list = new ArrayList<Object>();
			// 用户名
			if(user.getUser_name()!=null&&!user.getUser_name().equals("")){
				hqlConditionExpression += " and user_name like " + Constants.PLACEHOLDER;
				list.add("%" + user.getUser_name() + "%");
			}
			// 姓名
			if(user.getFull_name()!=null&&!user.getFull_name().equals("")){
				hqlConditionExpression += " and first_name = " + Constants.PLACEHOLDER;
				list.add("%" + user.getFull_name() + "%");
			}
			// 删除状态
			if(user.getDelete_flag()!=-1){
				hqlConditionExpression += " and delete_flag = " + Constants.PLACEHOLDER;
				list.add(user.getDelete_flag());
			}
			
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			Long[] userIds = userDao.getIDs(p);
			if (userIds != null && userIds.length != 0){
				userList = new ArrayList<User>();
				User u = null;
				for (Long id : userIds) {
					u = userDao.findById(Integer.parseInt(id.toString()));
					if (u != null) {
						userList.add(u);
					}
				}
				return userList;
			}
		}
		return null;
	}

}
