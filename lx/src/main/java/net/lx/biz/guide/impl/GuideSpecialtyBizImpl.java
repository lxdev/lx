package net.lx.biz.guide.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.biz.guide.IGuideSpecialtyBiz;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionDao;
import net.lx.dao.guide.IGuideSpecialtyDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideSpecialty;
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
public class GuideSpecialtyBizImpl implements IGuideSpecialtyBiz {
	@Autowired
	private IGuideSpecialtyDao dao;

	/**
	 * 根据ID查询国家
	 */
	public GuideSpecialty findById(int id) throws Exception {
		GuideSpecialty entity = dao.findById(id);
		if(entity != null){
			if(entity.getParent_id() != 0)
				entity.setParentSpecialty(dao.findById(entity.getParent_id()));
		}
		return entity;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<GuideSpecialty> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		List<GuideSpecialty> list = dao.getByProperty(hqlcon, paramList);
		List<GuideSpecialty> result = new ArrayList<GuideSpecialty>();
		for(GuideSpecialty item : list){
			if(item.getParent_id() > 0)
				item.setParentSpecialty(dao.findById(item.getParent_id()));
			result.add(item);
		}
		return result;
	}

	public List<GuideSpecialty> findAllParent() throws Exception {
		return dao.findAllParent();
	}

	public List<GuideSpecialty> searchGuideSpecialtysByCondition(GuideSpecialty spe) throws Exception {
		return dao.searchGuideSpecialtysByCondition(spe);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<GuideSpecialty> findListById(int id) throws Exception {
		try {
			List<GuideSpecialty> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<GuideSpecialty>();
				for(int i = 0; i < ids.length; i++) {
					GuideSpecialty obj = dao.findById(Integer.valueOf(ids[i].toString()));
					if(obj != null) {
						if(obj.getParent_id() != 0)
							obj.setParentSpecialty(dao.findById(obj.getParent_id()));
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
	public boolean createNew(GuideSpecialty g) throws Exception 
	{
//		if(null==findById(g.getId()))
//		{
			dao.save(g);
			return true;
//		}
//		return false;
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

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(GuideSpecialty g) throws Exception {
		dao.update(g);

	}
}
