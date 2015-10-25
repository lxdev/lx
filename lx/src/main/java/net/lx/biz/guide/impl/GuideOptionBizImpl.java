package net.lx.biz.guide.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.guide.IGuideBiz;
import net.lx.biz.guide.IGuideOptionBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.guide.IGuideOptionDao;
import net.lx.entity.guide.Guide;
import net.lx.entity.guide.GuideOption;
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
public class GuideOptionBizImpl implements IGuideOptionBiz {
	@Autowired
	private IGuideOptionDao dao;

	/**
	 * 根据ID查询国家
	 */
	public GuideOption findById(int id) throws Exception {
		GuideOption option = dao.findById(id);
		if(option != null){
			if(option.getParent_id() != 0)
				option.setParentOption(dao.findById(option.getParent_id()));
		}
		return option;
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<GuideOption> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		List<GuideOption> list = dao.getByProperty(hqlcon, paramList);
		List<GuideOption> result = new ArrayList<GuideOption>();
		for(GuideOption item : list){
			if(item.getParent_id() > 0)
				item.setParentOption(dao.findById(item.getParent_id()));
			result.add(item);
		}
//		for(int i = 0; i < list.size(); i++){
//			Map map = (Map) list.get(i);
//			GuideOption p = MapToEntryConvertUtils.convert(map, GuideOption.class, "");
//			if(p.getParent_id() > 0)
//				p.setParentOption(dao.findById(p.getParent_id()));
//			result.add(p);
//		}
		return result;
	}

	public List<GuideOption> findAllParent() throws Exception {
		return dao.findAllParent();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<GuideOption> findListById(int id) throws Exception {
		try {
			List<GuideOption> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<GuideOption>();
				for(int i = 0; i < ids.length; i++) {
					GuideOption obj = dao.findById(Integer.valueOf(ids[i].toString()));
					if(obj != null) {
						if(obj.getParent_id() != 0)
							obj.setParentOption(dao.findById(obj.getParent_id()));
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
	public boolean createNew(GuideOption g) throws Exception 
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
	public void modify(GuideOption g) throws Exception {
		dao.update(g);

	}
	
}
