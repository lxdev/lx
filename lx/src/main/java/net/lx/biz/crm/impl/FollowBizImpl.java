package net.lx.biz.crm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.ask.IAskBiz;
import net.lx.biz.crm.IFollowBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.university.IUniversityStatisticBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.ask.IAskDao;
import net.lx.dao.crm.IFollowDao;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.university.IUniversityStatisticDao;
import net.lx.dao.user.UserDao;
import net.lx.entity.ask.Ask;
import net.lx.entity.crm.Follow;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.university.Program;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.University;
import net.lx.entity.university.UniversityStatistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 关注、收藏
 * 
 * @author lxl
 * 
 */
@Service
public class FollowBizImpl implements IFollowBiz {
	@Autowired
	private IFollowDao dao;
	@Autowired
	private IAskBiz askBiz;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IUniversityStatisticBiz universityStatisticBiz;
	/**
	 * 根据ID查询国家
	 */
	public Follow findById(int id) throws Exception {
		Follow entity = dao.findById(id);
		if(entity != null) {
			entity = setFollowAttribute(entity);
		}
		return entity;
	}
	
	private Follow setFollowAttribute(Follow f) throws Exception{
		if(f.getFollow_type() == Constants.FOLLOW_ASK){
			Ask ask = askBiz.findById(f.getSource_id());
			f.setSource_title(ask.getTitle());
			f.setSource_content(ask.getBody());
			f.setSource_user(ask.getAsk_user());
			f.setSource_user2(ask.getReply_user());
		}else if(f.getFollow_type() == Constants.FOLLOW_UNIVERSITY){
			//...
		}
		
		return f;
	}

	public List<Follow> searchByCondition(Follow entity) throws Exception {
		List<Follow> list = dao.searchByCondition(entity);
		List<Follow> result = new ArrayList<Follow>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			Follow f = MapToEntryConvertUtils.convert(map, Follow.class, "");
			f = setFollowAttribute(f);
			result.add(f);
		}
		return result;
	}

	public Boolean deleteById(int id){
		Follow f = dao.deleteById(id);
		return true;
	}
	
	/**
	 * 添加Follow
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Follow g) throws Exception 
	{
		Integer result = (Integer)dao.save(g);
		if(result > 0){
			if(g.getFollow_type() == 2){	//收藏院校
				UniversityStatistic us = new UniversityStatistic();
				us.setUniversity_id(g.getSource_id());
				us.setTotal_collect(1);
				us.setIncr_or_decr(3);
				universityStatisticBiz.createOrUpdate(us);
			}
		}
		return result;
	}
	

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Follow g) throws Exception {
		dao.update(g);
	}
	
}
