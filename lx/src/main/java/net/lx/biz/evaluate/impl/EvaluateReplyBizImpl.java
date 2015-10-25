package net.lx.biz.evaluate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.evaluate.IEvaluateReplyBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.evaluate.IEvaluateReplyDao;
import net.lx.dao.user.UserDao;
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
public class EvaluateReplyBizImpl implements IEvaluateReplyBiz {
	@Autowired
	private UserDao userDao;
	@Autowired
	private IEvaluateReplyDao dao;
	
	private EvaluateReply setEvaluateReplyAttribute(EvaluateReply entity) throws Exception{
		User user = userDao.findById(entity.getCreated_user_id());
		if(entity.getParent_reply_id() != null && entity.getParent_reply_id() > 0){
			EvaluateReply p = dao.findById(entity.getParent_reply_id());
			entity.setParent_reply(p);
		}
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public EvaluateReply findById(int id) throws Exception {
		EvaluateReply entity = dao.findById(id);
		if(entity != null) {
			entity = setEvaluateReplyAttribute(entity);
		}
		return entity;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<EvaluateReply> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by created_date desc ";
		List<EvaluateReply> list = dao.getByProperty(hqlcon, paramList);
		List<EvaluateReply> result = new ArrayList<EvaluateReply>();
		for(EvaluateReply entity : list){
			entity = setEvaluateReplyAttribute(entity);
			result.add(entity);
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EvaluateReply> findListById(int id) throws Exception {
		try {
			List<EvaluateReply> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<EvaluateReply>();
				for(int i = 0; i < ids.length; i++) {
					EvaluateReply obj = dao.findById(Integer.valueOf(ids[i].toString()));
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
	

	public List<EvaluateReply> searchEvaluateRepliesByCondition(EvaluateReply entity) throws Exception {
		List<EvaluateReply> list = dao.searchEvaluateRepliesByCondition(entity);
		List<EvaluateReply> result = new ArrayList<EvaluateReply>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			EvaluateReply f = MapToEntryConvertUtils.convert(map, EvaluateReply.class, "");
			f = setEvaluateReplyAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		EvaluateReply f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(EvaluateReply g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(EvaluateReply g) throws Exception {
		dao.update(g);
	}
	
}
