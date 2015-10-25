package net.lx.biz.university.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.evaluate.IEvaluateBiz;
import net.lx.biz.university.IUniversityBiz;
import net.lx.common.Constants;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.university.IUniversityDao;
import net.lx.entity.evaluate.Evaluate;
import net.lx.entity.university.University;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * university业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class UniversityBizImpl implements IUniversityBiz {
	@Autowired
	private IUniversityDao universityDao;
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private IAreaDao areaDao;
	@Autowired
	private IEvaluateBiz evaluateBiz;
	

	private University setUniversityAttribute(University entity) throws Exception{

		entity.setCountry(countryDao.findById(entity.getCountry_id()));
		entity.setArea(areaDao.findById(entity.getArea_id()));
		
		Evaluate evaluateCon = new Evaluate();
		evaluateCon.setEvaluate_type(Constants.EVALUATE_TYPE_UNIVERSITY);
		evaluateCon.setSource_id(entity.getId());
		entity.setEvaluateList(evaluateBiz.searchEvaluatesByCondition(evaluateCon));
		entity.setName(entity.getUniversity_name() + "/" + entity.getEnglish_name());
		if(entity.getLogo_url() != null && !entity.getLogo_url().equals(""))
			entity.setLogo_url(Constants.WEB_ATTACHMENT + entity.getLogo_url());
		
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public University findUniversityById(int id) throws Exception {
		University entity = universityDao.findById(id);
		if(entity != null) {
			entity = setUniversityAttribute(entity);
		}
		return entity;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<University> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		List<University> list = universityDao.getByProperty(hqlcon, paramList);
		List<University> result = new ArrayList<University>();
		for(University entity : list){
			entity = setUniversityAttribute(entity);
			result.add(entity);
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<University> findListById(int id) throws Exception {
		try {
			List<University> universitys = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] universityIds = universityDao.getIDs(p);
			if(universityIds != null && universityIds.length != 0){
				universitys = new ArrayList<University>();
				for(int i = 0; i < universityIds.length; i++) {
					University universityObj = universityDao.findById(Integer.valueOf(universityIds[i].toString()));
					if(universityObj != null) {
						universitys.add(universityObj);
					}
				}
			}
			return universitys;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public List<University> searchUniversitysByCondition(University uni) throws Exception {
		//return universityDao.searchUniversitysByCondition(uni);
		List<University> list = universityDao.searchUniversitysByCondition(uni);
		List<University> result = new ArrayList<University>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			University u = MapToEntryConvertUtils.convert(map, University.class, "");
			for (Iterator j = map.keySet().iterator(); j.hasNext();) {
			   Object obj = j.next();
			   //System.out.println(obj);// 循环输出key
			   if(obj.toString().equals("evaluate_number"))
				   u.setEvaluate_number(Integer.parseInt(map.get(obj) == null ? "0" : map.get(obj).toString()));
			   if(obj.toString().equals("browse_number"))
				   u.setBrowse_number(Integer.parseInt(map.get(obj) == null ? "0" : map.get(obj).toString()));
			}
			u = setUniversityAttribute(u);
			result.add(u);
			
		}
		return result;
	}

	public Integer searchUniversitysRecordByCondition(University condition){
		return universityDao.searchUniversitysRecordByCondition(condition);
	}
}
