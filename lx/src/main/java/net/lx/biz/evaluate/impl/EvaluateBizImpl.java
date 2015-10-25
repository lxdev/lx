package net.lx.biz.evaluate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.evaluate.IEvaluateExtendUniversityBiz;
import net.lx.biz.evaluate.IEvaluateReplyBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.evaluate.IEvaluateDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
import net.lx.entity.evaluate.EvaluateReply;
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
public class EvaluateBizImpl implements IEvaluateBiz {
	@Autowired
	private IEvaluateDao dao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IEvaluateExtendUniversityBiz eUBiz;
	@Autowired
	private IEvaluateReplyBiz replyBiz;
	
	private Evaluate setEvaluateAttribute(Evaluate entity) throws Exception{
		User user = userDao.findById(entity.getEvaluate_from_user_id());
		entity.setEvaluate_from_user(user);
		
		if(entity.getEvaluate_type() == Constants.EVALUATE_TYPE_UNIVERSITY){
			EvaluateExtendUniversity eu = eUBiz.searchByEvaluateId(entity.getId());
			entity.setEvaluateExtendUniversity(eu);
		}
		EvaluateReply replyCondition = new EvaluateReply();
		replyCondition.setEvaluate_id(entity.getId());
		entity.setEvaluateReplyList(replyBiz.searchEvaluateRepliesByCondition(replyCondition));
		
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public Evaluate findById(int id) throws Exception {
		Evaluate entity = dao.findById(id);
		if(entity != null) {
			entity = setEvaluateAttribute(entity);
		}
		return entity;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Evaluate> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by created_date desc ";
		List<Evaluate> list = dao.getByProperty(hqlcon, paramList);
		List<Evaluate> result = new ArrayList<Evaluate>();
		for(Evaluate entity : list){
			entity = setEvaluateAttribute(entity);
			result.add(entity);
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Evaluate> findListById(int id) throws Exception {
		try {
			List<Evaluate> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Evaluate>();
				for(int i = 0; i < ids.length; i++) {
					Evaluate obj = dao.findById(Integer.valueOf(ids[i].toString()));
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
	

	public List<Evaluate> searchEvaluatesByCondition(Evaluate entity) throws Exception {
		List<Evaluate> list = dao.searchEvaluatesByCondition(entity);
		List<Evaluate> result = new ArrayList<Evaluate>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			Evaluate f = MapToEntryConvertUtils.convert(map, Evaluate.class, "");
			f = setEvaluateAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		Evaluate f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Evaluate g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Evaluate g) throws Exception {
		dao.update(g);
	}
	
}
