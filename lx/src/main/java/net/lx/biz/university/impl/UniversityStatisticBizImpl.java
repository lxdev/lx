package net.lx.biz.university.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.biz.university.IUniversityStatisticBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.dao.university.IUniversityStatisticDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.evaluate.EvaluateExtendUniversity;
import net.lx.entity.evaluate.EvaluateReply;
import net.lx.entity.university.Program;
import net.lx.entity.university.University;
import net.lx.entity.university.UniversityStatistic;
import net.lx.entity.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * university业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class UniversityStatisticBizImpl implements IUniversityStatisticBiz {
	
	@Autowired
	private IUniversityStatisticDao dao;

	/**
	 * 根据ID查询国家
	 */
	public UniversityStatistic findByUniversityId(int university_id) throws Exception {
		UniversityStatistic pro = dao.findByUniversityId(university_id);
		return pro;
	}
	
	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createOrUpdate(UniversityStatistic g) throws Exception 
	{
		UniversityStatistic us = dao.findByUniversityId(g.getUniversity_id());
		if(us == null){
			g.setTotal_browse(0);
			g.setTotal_evaluate(0);
			g.setTotal_collect(0);
			switch(Math.abs(g.getIncr_or_decr())){
			case 1:
				g.setTotal_browse(1);
				break;
			case 2:
				g.setTotal_evaluate(1);
				break;
			case 3:
				g.setTotal_collect(1);
				break;
			}
			dao.save(g);
		}else {
			g.setId(us.getId());
			g.setTotal_browse(us.getTotal_browse());
			g.setTotal_evaluate(us.getTotal_evaluate());
			g.setTotal_collect(us.getTotal_collect());
			switch(Math.abs(g.getIncr_or_decr())){
			case 1:
				g.setTotal_browse(g.getTotal_browse() + 1);
				break;
			case 2:
				g.setTotal_evaluate(g.getTotal_evaluate() + 1);
				break;
			case 3:
				g.setTotal_collect(g.getTotal_collect() + 1);
				break;
			}
			dao.update(g);
		}
		return 1;
	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
//	public void modify(UniversityStatistic g) throws Exception {
//		dao.update(g);
//	}
}
