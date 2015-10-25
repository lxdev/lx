package net.lx.dao.base.impl;

import net.lx.dao.base.TaskDao;
import net.lx.dao.impl.BaseMDDaoImpl;
import net.lx.entity.base.UserTask;

import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl extends BaseMDDaoImpl<UserTask> implements TaskDao {

}
