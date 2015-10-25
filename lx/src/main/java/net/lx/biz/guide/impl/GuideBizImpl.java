package net.lx.biz.guide.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionContentDao;
import net.lx.dao.guide.IGuideSpecialtyDao;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class GuideBizImpl implements IGuideBiz {
	@Autowired
	private IGuideDao dao;

	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ISpecialtyBiz specialtyBiz;
	@Autowired
	private IGuideSpecialtyBiz guideSpecialtyBiz;
	@Autowired
	private IGuideOptionContentDao guideOptionContentDao;
	
	private Guide setAttribute(Guide entity) throws Exception{
		if(entity != null){
			entity.setCountry(countryDao.findById(entity.getCountry_id()));
			if(entity.getIs_self_specialty() == 1)
				entity.setGuideSpecialty(guideSpecialtyBiz.findById(entity.getSpecialty_id()));
			else
				entity.setSpecialty(specialtyBiz.findById(entity.getSpecialty_id()));
			entity.setOptionContents(guideOptionContentDao.findAllByGuideId(entity.getGuide_id()));
		}
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public Guide findById(int id) throws Exception {
		Guide guide = dao.findById(id);
		guide = setAttribute(guide);
		return guide;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Guide> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by guide_name ";
		List<Guide> list = dao.getByProperty(hqlcon, paramList);
		List<Guide> result = new ArrayList<Guide>();
		for(Guide guide : list){
			guide = setAttribute(guide);
			result.add(guide);
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Guide> findListById(int id) throws Exception {
		try {
			List<Guide> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Guide>();
				for(int i = 0; i < ids.length; i++) {
					Guide obj = dao.findById(Integer.valueOf(ids[i].toString()));
					if(obj != null) {
						obj = setAttribute(obj);
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

	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Guide g) throws Exception 
	{
		return (Integer)dao.save(g);
	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Guide g) throws Exception {
		dao.update(g);
	}
	
	/**
	 * 根据ID删除用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.deleteById(id);
	}
}
