package net.lx.biz.dic.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;
import net.lx.biz.dic.ICountryBiz;
import net.lx.dao.dic.ICountryDao;
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
public class CountryBizImpl implements ICountryBiz {
	@Autowired
	private ICountryDao countryDao;

	/**
	 * 根据ID查询国家
	 */
	public Country findCountryById(int id) throws Exception {
		return countryDao.findById(id);
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<Country> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		return countryDao.getByProperty(hqlcon, paramList);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Country> findListById(int id) throws Exception {
		try {
			List<Country> countrys = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] countryIds = countryDao.getIDs(p);
			if(countryIds != null && countryIds.length != 0){
				countrys = new ArrayList<Country>();
				for(int i = 0; i < countryIds.length; i++) {
					Country countryObj = countryDao.findById(Integer.valueOf(countryIds[i].toString()));
					if(countryObj != null) {
						countrys.add(countryObj);
					}
				}
			}
			return countrys;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
