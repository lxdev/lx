package net.lx.biz.evaluate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.ask.IAskReplyBiz;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.evaluate.IEvaluateExtendUniversityBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.common.date.DateUtil;
import net.lx.dao.ask.IAskDao;
import net.lx.dao.ask.IAskReplyDao;
import net.lx.dao.ask.IAskStatisDao;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.evaluate.IEvaluateDao;
import net.lx.dao.evaluate.IEvaluateExtendUniversityDao;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskReply;
import net.lx.entity.ask.AskStatis;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
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
public class EvaluateExtendUniversityBizImpl implements IEvaluateExtendUniversityBiz {
	@Autowired
	private IEvaluateExtendUniversityDao dao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据ID查询国家
	 */
	public EvaluateExtendUniversity findById(int id) throws Exception {
		EvaluateExtendUniversity entity = dao.findById(id);
		return entity;
	}
	

	public EvaluateExtendUniversity searchByEvaluateId(Integer evaluateId) throws Exception {
		return dao.searchByEvaluateId(evaluateId);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EvaluateExtendUniversity> findListById(int id) throws Exception {
		try {
			List<EvaluateExtendUniversity> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<EvaluateExtendUniversity>();
				for(int i = 0; i < ids.length; i++) {
					EvaluateExtendUniversity obj = dao.findById(Integer.valueOf(ids[i].toString()));
					if(obj != null) {
						results.add(obj);
					}
				}
			}
			return results;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean deleteById(int id){
		EvaluateExtendUniversity f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(EvaluateExtendUniversity g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(EvaluateExtendUniversity g) throws Exception {
		dao.update(g);
	}
	
}
