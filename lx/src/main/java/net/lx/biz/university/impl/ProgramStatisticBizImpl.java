package net.lx.biz.university.impl;

import net.lx.biz.university.IProgramStatisticBiz;
import net.lx.dao.university.IProgramStatisticDao;
import net.lx.entity.university.ProgramStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * program业务实现类
 * 
 * @author lxl
 * 
 */
@Service
public class ProgramStatisticBizImpl implements IProgramStatisticBiz{
	
	@Autowired
	private IProgramStatisticDao dao;

	/**
	 * 根据ID查询国家
	 */
	public ProgramStatistic findByProgramId(int program_id) throws Exception {
		ProgramStatistic pro = dao.findByProgramId(program_id);
		return pro;
	}
	
	/**
	 * 添加用户
	 * 
	 * @param g
	 * @throws Exception
	 */
	public Integer createOrUpdate(ProgramStatistic g) throws Exception
	{
		ProgramStatistic us = dao.findByProgramId(g.getProgram_id());
		if(us == null){
			g.setTotal_browse(0);
			g.setTotal_evaluate(0);
			g.setTotal_collect(0);
			switch(Math.abs(g.getIncr_or_decr())){
			case 1:
				g.setTotal_browse(1);
				break;
			case 2:
				g.setTotal_evaluate(1);
				break;
			case 3:
				g.setTotal_collect(1);
				break;
			}
			dao.save(g);
		}else {
			g.setId(us.getId());
			g.setTotal_browse(us.getTotal_browse());
			g.setTotal_evaluate(us.getTotal_evaluate());
			g.setTotal_collect(us.getTotal_collect());
			switch(Math.abs(g.getIncr_or_decr())){
			case 1:
				g.setTotal_browse(g.getTotal_browse() + 1);
				break;
			case 2:
				g.setTotal_evaluate(g.getTotal_evaluate() + 1);
				break;
			case 3:
				g.setTotal_collect(g.getTotal_collect() + 1);
				break;
			}
			dao.update(g);
		}
		return 1;
	}
}
