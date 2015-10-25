package net.lx.biz.dic.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.model.page.PageParame;
import net.lx.biz.dic.IScoreBiz;
import net.lx.dao.dic.IScoreDao;
import net.lx.entity.dic.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class ScoreBizImpl implements IScoreBiz {
	@Autowired
	private IScoreDao dao;
	
	/**
	 * 根据ID查询国家
	 */
	public Score findById(int id) throws Exception {
		return dao.findById(id);
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Score> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		return dao.getByProperty(hqlcon, paramList);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Score> findListById(int id) throws Exception {
		try {
			List<Score> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Score>();
				for(int i = 0; i < ids.length; i++) {
					Score obj = dao.findById(Integer.valueOf(ids[i].toString()));
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
	

	public List<Score> searchScoresByType(int type) throws Exception {
		return dao.searchScoresByType(type);
	}

}
