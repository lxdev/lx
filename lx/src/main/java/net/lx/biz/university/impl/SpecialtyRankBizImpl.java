package net.lx.biz.university.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lx.model.page.PageParame;
import net.lx.biz.university.ISpecialtyBiz;
import net.lx.biz.university.ISpecialtyRankBiz;
import net.lx.common.convert.MapToEntryConvertUtils;
import net.lx.dao.university.ISpecialtyDao;
import net.lx.dao.university.ISpecialtyRankDao;
import net.lx.entity.university.Specialty;
import net.lx.entity.university.SpecialtyRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class SpecialtyRankBizImpl implements ISpecialtyRankBiz {
	@Autowired
	private ISpecialtyRankDao dao;
	
	private SpecialtyRank setAttribute(SpecialtyRank entity) throws Exception{
		if(entity != null){
			
		}
		
		return entity;
	}
	
	/**
	 * 根据ID查询国家
	 */
	public SpecialtyRank findById(int id) throws Exception {
		SpecialtyRank spe = dao.findById(id);
		spe = setAttribute(spe);
		return spe;
	}

	public List<SpecialtyRank> searchSpecialtyRanksByCondition(SpecialtyRank con) throws Exception {
		List<SpecialtyRank> list = dao.searchSpecialtyRanksByCondition(con);
		List<SpecialtyRank> result = new ArrayList<SpecialtyRank>();
		for(int i = 0; i < list.size(); i++){
			Map map = (Map) list.get(i);
			SpecialtyRank p = MapToEntryConvertUtils.convert(map, SpecialtyRank.class, "");
			p = setAttribute(p);
			result.add(p);
		}
		return result;
	}
	
}
