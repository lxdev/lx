package net.lx.biz.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.biz.user.IUserExtendBiz;
import net.lx.biz.user.UserBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.common.date.DateUtil;
import net.lx.common.enums.UserEnum;
import net.lx.common.md5.PwdCoder;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.dic.IStudyLevelDao;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.user.IUserExtendDao;
import net.lx.dao.user.UserDao;
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
public class UserExtendBizImpl implements IUserExtendBiz {

	@Autowired
	private IUserExtendDao userDao;
	@Autowired
	private IStudyLevelDao studyLevelDao;
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ISpecialtyDao specialtyDao;
	@Autowired
	private IUniversityDao universityDao;
	
	private UserExtend setAttribute(UserExtend entity) throws Exception{
		if(entity != null){
			if(entity.getStudy_level_id() != null && entity.getStudy_level_id() > 0){
				entity.setStudy_level(studyLevelDao.findById(entity.getStudy_level_id()));
			}
			if(entity.getCountry_id() != null && entity.getCountry_id() > 0){
				entity.setCountry(countryDao.findById(entity.getCountry_id()));
			}
			if(entity.getSpecialty_id() != null && entity.getSpecialty_id() > 0){
				entity.setSpecialty(specialtyDao.findById(entity.getSpecialty_id()));
			}
			if(entity.getUniversity_id() != null && entity.getUniversity_id() > 0){
				entity.setUniversity(universityDao.findById(entity.getUniversity_id()));
			}
		}
			
		return entity;
	}
	
	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserExtend> findUserForModel() throws Exception {
		List<UserExtend> lst = userDao.findAll();
		List<UserExtend> result = new ArrayList<UserExtend>();
		for(UserExtend item : lst){
			item = setAttribute(item);
			result.add(item);
		}
		return result;
	}

	/**
	 * 根据ID查询用户
	 */
	public UserExtend findUserById(int id) throws Exception {
		UserExtend user=userDao.findById(id);
		user = setAttribute(user);
		return user;
	}
	
	public UserExtend findUserByUserId(int userId) throws Exception {
		Map map=userDao.findUserByUserId(userId);
		UserExtend u = MapToEntryConvertUtils.convert(map, UserExtend.class, "");
		u = setAttribute(u);
		return u;
	}

	/**
	 * 添加用户
	 * 
	 * @param AdminUser
	 * @throws Exception
	 */
	public boolean createNew(UserExtend user) throws Exception 
	{
		userDao.save(user);
		return true;
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
	public void modify(UserExtend user) throws Exception {
		userDao.update(user);

	}

	/*
	 * 获取用户Ids
	 */
	public Long[] findUserIds(PageParame p) throws Exception {
		
		return userDao.getIDs(p);
	}
	
}
