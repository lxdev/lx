package net.lx.dao.guide.impl;

import net.lx.dao.dic.ICountryDao;
import net.lx.dao.guide.IGuideDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.dic.Country;
import net.lx.entity.guide.Guide;

import org.springframework.stereotype.Repository;

@Repository
public class GuideDaoImpl extends BaseMDDaoImpl<Guide> implements IGuideDao {
	
}
