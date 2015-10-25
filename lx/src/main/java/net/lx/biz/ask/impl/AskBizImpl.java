package net.lx.biz.ask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.ask.IAskReplyBiz;
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
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.ask.AskReply;
import net.lx.entity.ask.AskStatis;
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
public class AskBizImpl implements IAskBiz {
	@Autowired
	private IAskDao dao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IAskReplyBiz replyBiz;
	@Autowired
	private IAskStatisDao statisDao;
	
	private Ask setAskAttribute(Ask entity) throws Exception{
		User user = userDao.findById(entity.getAsk_user_id());
		entity.setAsk_user(user);
		if(entity.getReply_user_id() != null && entity.getReply_user_id() > 0){
			User user2 = userDao.findById(entity.getReply_user_id());
			entity.setReply_user(user2);
		}
		entity.setCreated_date_ago(DateUtil.getDateTimeAgo(entity.getCreated_date()));
		List<AskStatis> statis = statisDao.searchAskStatisByCondition(entity.getId());
		if(statis.size() >= 1)
			entity.setAsk_statis(statis.get(0));
		else{
			AskStatis conS = new AskStatis();
			conS.setTotal_attention(0);
			conS.setTotal_praise(0);
			conS.setTotal_quote(0);
			conS.setTotal_reply(0);
			conS.setTotal_view(0);
			entity.setAsk_statis(conS);
		}
		AskReply conR = new AskReply();
		conR.setAsk_id(entity.getId());
		List<AskReply> replies = replyBiz.searchAskReplysByCondition(conR);
		entity.setAsk_replies(replies);
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public Ask findById(int id) throws Exception {
		Ask entity = dao.findById(id);
		if(entity != null) {
			entity = setAskAttribute(entity);
		}
		return entity;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Ask> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by created_date desc ";
		List<Ask> list = dao.getByProperty(hqlcon, paramList);
		List<Ask> result = new ArrayList<Ask>();
		for(Ask entity : list){
			entity = setAskAttribute(entity);
			result.add(entity);
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Ask> findListById(int id) throws Exception {
		try {
			List<Ask> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Ask>();
				for(int i = 0; i < ids.length; i++) {
					Ask obj = dao.findById(Integer.valueOf(ids[i].toString()));
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
	

	public List<Ask> searchAsksByCondition(Ask entity) throws Exception {
		List<Ask> list = dao.searchAsksByCondition(entity);
		List<Ask> result = new ArrayList<Ask>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			Ask f = MapToEntryConvertUtils.convert(map, Ask.class, "");
			f = setAskAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		Ask f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Ask g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Ask g) throws Exception {
		dao.update(g);
	}
	
}
