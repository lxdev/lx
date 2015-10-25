package net.lx.biz.guide.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.biz.guide.IGuideOptionContentBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionContentDao;
import net.lx.dao.guide.IGuideOptionDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
import net.lx.entity.guide.GuideOptionContent;
import net.lx.entity.university.Program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class GuideOptionContentBizImpl implements IGuideOptionContentBiz {
	@Autowired
	private IGuideOptionContentDao dao;

	/**
	 * 根据ID查询国家
	 */
	public GuideOptionContent findById(int id) throws Exception {
		return dao.findById(id);
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<GuideOptionContent> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		List<GuideOptionContent> list = dao.getByProperty(hqlcon, paramList);
		
		return list;
	}

	public List<GuideOptionContent> findByGuideId(int guideId) throws Exception{
		return dao.findAllByGuideId(guideId);
	}

	public void saveAllByGuide(List<GuideOptionContent> contents){
		for(GuideOptionContent item : contents){
//			if(item.getId() != 0)
//				dao.update(item);
//			else
//				dao.save(item);
			Integer id = dao.findByIdentifier(item.getGuide_id(), item.getOption_id());
			if(id <= 0)
				dao.save(item);
			else{
				item.setId(id);
				dao.update(item);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<GuideOptionContent> findListById(int id) throws Exception {
		try {
			List<GuideOptionContent> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<GuideOptionContent>();
				for(int i = 0; i < ids.length; i++) {
					GuideOptionContent obj = dao.findById(Integer.valueOf(ids[i].toString()));
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

	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public boolean createNew(GuideOptionContent g) throws Exception 
	{
		dao.save(g);
		return true;
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
	public void modify(GuideOptionContent g) throws Exception {
		dao.update(g);

	}
	
}
