package net.lx.biz.ask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.ask.IAskMessageBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.ask.IAskDao;
import net.lx.dao.ask.IAskMessageDao;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskMessage;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class AskMessageBizImpl implements IAskMessageBiz {
	@Autowired
	private IAskMessageDao dao;
	@Autowired
	private UserDao userDao;
	
	private AskMessage setAskAttribute(AskMessage entity) throws Exception{
		User user = userDao.findById(entity.getSend_user_id());
		entity.setSend_user(user);
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public AskMessage findById(int id) throws Exception {
		AskMessage entity = dao.findById(id);
		return entity;
	}
	
	public List<AskMessage> searchByCondition(AskMessage entity) throws Exception {
		List<AskMessage> list = dao.searchByCondition(entity);
		List<AskMessage> result = new ArrayList<AskMessage>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			AskMessage f = MapToEntryConvertUtils.convert(map, AskMessage.class, "");
			f = setAskAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		AskMessage f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(AskMessage g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(AskMessage g) throws Exception {
		dao.update(g);
	}
	
}
