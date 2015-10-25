package net.lx.biz.ask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.biz.ask.IAskReplyBiz;
import net.lx.biz.ask.IAskStatisBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.ask.IAskReplyDao;
import net.lx.dao.ask.IAskStatisDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.AskReply;
import net.lx.entity.ask.AskStatis;
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
public class AskStatisBizImpl implements IAskStatisBiz {
	@Autowired
	private UserDao userDao;
	@Autowired
	private IAskStatisDao dao;

	public List<AskStatis> searchAskStatisByCondition(int id) throws Exception {
		return dao.searchAskStatisByCondition(id);
	}

	public Boolean deleteById(int id){
		AskStatis f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(AskStatis g) throws Exception 
	{
		return (Integer)dao.save(g);
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(AskStatis g) throws Exception {
		dao.update(g);
	}
	
}
