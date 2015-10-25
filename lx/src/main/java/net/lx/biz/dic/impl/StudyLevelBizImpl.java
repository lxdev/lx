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
import net.lx.biz.dic.IStudyLevelBiz;
import net.lx.dao.dic.ICountryDao;
import net.lx.dao.dic.IStudyLevelDao;
import net.lx.entity.dic.Country;
import net.lx.entity.dic.StudyLevel;

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
public class StudyLevelBizImpl implements IStudyLevelBiz {
	@Autowired
	private IStudyLevelDao studyLevelDao;

	/**
	 * 根据ID查询学习中心
	 */
	public StudyLevel findStudyLevelById(int id) throws Exception {
		return studyLevelDao.findById(id);
	}

	/**
	 * 
	 * @return 返回all
	 * @throws Exception
	 */
	public List<StudyLevel> findAll() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " order by id ";
		return studyLevelDao.getByProperty(hqlcon, paramList);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<StudyLevel> findListById(int id) throws Exception {
		try {
			List<StudyLevel> studyLevels = null;
			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			Long[] studyLevelIds = studyLevelDao.getIDs(p);
			if(studyLevelIds != null && studyLevelIds.length != 0){
				studyLevels = new ArrayList<StudyLevel>();
				for(int i = 0; i < studyLevelIds.length; i++) {
					StudyLevel obj = studyLevelDao.findById(Integer.valueOf(studyLevelIds[i].toString()));
					if(obj != null) {
						studyLevels.add(obj);
					}
				}
			}
			return studyLevels;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
