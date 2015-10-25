package net.lx.biz.dic.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.biz.dic.IAreaBiz;
import net.lx.biz.dic.ICountryBiz;
import net.lx.dao.dic.IAreaDao;
import net.lx.dao.dic.ICountryDao;
import net.lx.entity.dic.Area;
import net.lx.entity.dic.Country;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Constants;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class AreaBizImpl implements IAreaBiz {
	@Autowired
	private IAreaDao dao;

	/**
	 * 根据ID查询国家
	 */
	public Area findById(int id) throws Exception {
		return dao.findById(id);
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Area> findAll() throws Exception {
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
	public List<Area> findListById(int id) throws Exception {
		try {
			List<Area> results = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] ids = dao.getIDs(p);
			if(ids != null && ids.length != 0){
				results = new ArrayList<Area>();
				for(int i = 0; i < ids.length; i++) {
					Area obj = dao.findById(Integer.valueOf(ids[i].toString()));
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

}
