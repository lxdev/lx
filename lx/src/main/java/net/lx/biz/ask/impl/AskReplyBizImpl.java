package net.lx.biz.ask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.biz.ask.IAskReplyBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.ask.IAskDao;
import net.lx.dao.ask.IAskReplyDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.AskReply;
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
public class AskReplyBizImpl implements IAskReplyBiz {
	@Autowired
	private UserDao userDao;
	@Autowired
	private IAskDao askDao;
	@Autowired
	private IAskReplyDao dao;
	
	private AskReply setAskAttribute(AskReply entity) throws Exception{
		entity.setReply_user(userDao.findById(entity.getCreated_user_id()));
		entity.setAsk(askDao.findById(entity.getAsk_id()));
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public AskReply findById(int id) throws Exception {
		AskReply entity = dao.findById(id);
		if(entity != null) {
			entity = setAskAttribute(entity);
		}
		return entity;
	}

	public List<AskReply> searchAskReplysByCondition(AskReply entity) throws Exception {
		List<AskReply> list = dao.searchAskReplysByCondition(entity);
		List<AskReply> result = new ArrayList<AskReply>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			AskReply f = MapToEntryConvertUtils.convert(map, AskReply.class, "");
			f = setAskAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		AskReply f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(AskReply g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(AskReply g) throws Exception {
		dao.update(g);
	}
	
}
