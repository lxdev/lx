package net.lx.dao.crm.impl;

import org.springframework.stereotype.Repository;

import net.lx.dao.crm.OperationLogDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.crm.OperationLog;

/**
 * 操作日志实现类
 * 
 */
@Repository
public class OperationLogDaoImpl extends BaseMDDaoImpl<OperationLog> implements
		OperationLogDao {

}
