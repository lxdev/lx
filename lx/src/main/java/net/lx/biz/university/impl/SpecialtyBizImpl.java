package net.lx.biz.university.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.entity.university.Specialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class SpecialtyBizImpl implements ISpecialtyBiz {
	@Autowired
	private ISpecialtyDao dao;
	
	private Specialty setAttribute(Specialty entity) throws Exception{
		if(entity != null){
			if(entity.getParent_id() != 0){
				entity.setParentSpecialty(dao.findById(entity.getParent_id()));
				entity.setName(entity.getSpecialty_name() + "/" + entity.getSpecialty_english_name());
			}
		}
		
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public Specialty findById(int id) throws Exception {
		Specialty spe = dao.findById(id);
		spe = setAttribute(spe);
		return spe;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Specialty> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by depth, specialty_name ";
		List<Specialty> list = dao.getByProperty(hqlcon, paramList);
		List<Specialty> result = new ArrayList<Specialty>();
		for(Specialty item : list){
			item = setAttribute(item);
			result.add(item);
		}
		return result;
	}

	public List<Specialty> findAllParent() throws Exception {
		return dao.findAllParent();
	}
	public List<Specialty> findAllRoot() throws Exception {
		return dao.findAllRoot();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Specialty> findListById(int id) throws Exception {
		try {
			List<Specialty> results = null;
			PageParame p = new PageParame();
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Specialty>();
				for(int i = 0; i < ids.length; i++) {
					Specialty obj = dao.findById(Integer.valueOf(ids[i].toString()));
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
	

	public List<Specialty> searchSpecialtysByCondition(Specialty con) throws Exception {
		List<Specialty> list = dao.searchSpecialtysByCondition(con);
		List<Specialty> result = new ArrayList<Specialty>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			Specialty p = MapToEntryConvertUtils.convert(map, Specialty.class, "");
			p = setAttribute(p);
			result.add(p);
		}
		return result;
	}
	
	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public Integer createNew(Specialty g) throws Exception 
	{
		return (Integer)dao.save(g);
	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(Specialty g) throws Exception {
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

	public Integer getChildCount(int id) throws Exception{
		return dao.getChildCount(id);
	}
}
