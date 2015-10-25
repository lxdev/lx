package net.lx.biz.base.impl;

import java.util.ArrayList;
import java.util.List;

import net.lx.biz.base.TaskBiz;
import net.lx.common.Constants;
import net.lx.dao.base.TaskDao;
import net.lx.entity.base.UserTask;
import net.lx.model.page.PageParame;
import net.lx.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskBizImpl implements TaskBiz {

	@Autowired
	private TaskDao taskDao;

	/*
	 * 任务列表
	 */
	public List<UserTask> findListByTask(PageResult<UserTask> pr, UserTask task) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (task!= null) 
		{
			//用户id
			if(0!=task.getUserId())
			{
				hql+=" and userId=" + Constants.PLACEHOLDER;
				list.add(task.getUserId());
			}
			//标题
			if(StringUtils.isNotBlank(task.getTitle()))
			{
				hql+=" and title like " + Constants.PLACEHOLDER;
				list.add("%"+task.getTitle()+"%");
			}
			//备注
			if(StringUtils.isNotBlank(task.getRemark()))
			{
				hql+=" and remark like " + Constants.PLACEHOLDER;
				list.add("%"+task.getRemark()+"%");
			}
			//状态
			if(0!=task.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				list.add(task.getStatus());
			}
			//下载次数
			if(0!=task.getDownloadSumCount())
			{
				hql+=" and downloadSumCount=" + Constants.PLACEHOLDER;
				list.add(task.getDownloadSumCount());
			}
			//路径
			if(StringUtils.isNotBlank(task.getPath()))
			{
				hql+=" and path like " + Constants.PLACEHOLDER;
				list.add("%"+task.getPath()+"%");
			}
			hql+=" order by createTime desc ,downloadSumCount asc ";
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
			return taskDao.getList(p);
		}
		return null;
	}
	/*
	 * 任务总和
	 */
	public int findCountByTask(UserTask task) throws Exception {
		PageParame p = new PageParame();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (task!= null) 
		{
			//用户id
			if(0!=task.getUserId())
			{
				hql+=" and userId=" + Constants.PLACEHOLDER;
				list.add(task.getUserId());
			}
			//标题
			if(StringUtils.isNotBlank(task.getTitle()))
			{
				hql+=" and title like " + Constants.PLACEHOLDER;
				list.add("%"+task.getTitle()+"%");
			}
			//备注
			if(StringUtils.isNotBlank(task.getRemark()))
			{
				hql+=" and remark like " + Constants.PLACEHOLDER;
				list.add("%"+task.getRemark()+"%");
			}
			//状态
			if(0!=task.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				list.add(task.getStatus());
			}
			//下载次数
			if(0!=task.getDownloadSumCount())
			{
				hql+=" and downloadSumCount=" + Constants.PLACEHOLDER;
				list.add(task.getDownloadSumCount());
			}
			//路径
			if(StringUtils.isNotBlank(task.getPath()))
			{
				hql+=" and path like " + Constants.PLACEHOLDER;
				list.add("%"+task.getPath()+"%");
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
			return taskDao.getCounts(p);
		}
		return 0;
	}
	/*
	 * 增加任务（return -1:成功 0:空任务/添加错误 1:资源达到最大连接数 2:当前用户达到最大连接数 ）
	 */
	public int addTask(UserTask task) throws Exception {
		if(task!=null)
		{
			//检测当前正在进行的连接数
			UserTask tempTask1 = new UserTask();
			tempTask1.setStatus(Constants.EXPORT_EXCEL_STATUS_BEING);
			int count1 = this.findCountByTask(tempTask1);
			if(count1>=Constants.EXPORT_EXCEL_MAX_USER_COUNT)
			{
				return Constants.EXPORT_EXCEL_ERROR_NO_1;
			}
			//检测当前用户正在进行的连接数
			UserTask tempTask2 = new UserTask();
			tempTask2.setUserId(task.getUserId());
			tempTask2.setStatus(Constants.EXPORT_EXCEL_STATUS_BEING);
			int count2 = this.findCountByTask(tempTask2);
			if(count2>=Constants.EXPORT_EXCEL_MAX_USERTASK_COUNT)
			{
				return Constants.EXPORT_EXCEL_ERROR_NO_2;
			}
			//插入任务
			task.setStatus(Constants.EXPORT_EXCEL_STATUS_BEING);
			Object obj=taskDao.save(task);
			if(obj!=null)
			{
				return Constants.EXPORT_EXCEL_FINISH;
			}
		}
		return Constants.EXPORT_EXCEL_ERROR_NO_0;
	}
	/*
	 * 删除任务
	 */
	public boolean deleteTask(int id) throws Exception {
		if(id!=0)
		{
			//数据存在且不是进行中的任务 才可以删除
			UserTask task = this.findUserTaskById(id);
			if(task!=null && task.getStatus()!=Constants.EXPORT_EXCEL_STATUS_BEING)
			{
				taskDao.deleteById(id);
				return true;
			}
		}
		return false;
	}
	/*
	 * 修改任务
	 */
	public boolean updateTask(UserTask task) throws Exception {
		if(task!=null)
		{
			taskDao.update(task);
			return true;
		}
		return false;
	}
	/*
	 * 根据id查任务
	 */
	public UserTask findUserTaskById(int id) throws Exception {
		if(id!=0)
		{
			return taskDao.findById(id);
		}
		return null;
	}
	/*
	 * 根据条件查询集合
	 */
	public List<UserTask> findUserTaskByEntity(UserTask task) throws Exception {
		PageParame p = new PageParame();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (task!= null) 
		{
			//用户id
			if(0!=task.getUserId())
			{
				hql+=" and userId=" + Constants.PLACEHOLDER;
				list.add(task.getUserId());
			}
			//标题
			if(StringUtils.isNotBlank(task.getTitle()))
			{
				hql+=" and title like " + Constants.PLACEHOLDER;
				list.add("%"+task.getTitle()+"%");
			}
			//备注
			if(StringUtils.isNotBlank(task.getRemark()))
			{
				hql+=" and remark like " + Constants.PLACEHOLDER;
				list.add("%"+task.getRemark()+"%");
			}
			//状态
			if(0!=task.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				list.add(task.getStatus());
			}
			//下载次数
			if(0!=task.getDownloadSumCount())
			{
				hql+=" and downloadSumCount=" + Constants.PLACEHOLDER;
				list.add(task.getDownloadSumCount());
			}
			//路径
			if(StringUtils.isNotBlank(task.getPath()))
			{
				hql+=" and path like " + Constants.PLACEHOLDER;
				list.add("%"+task.getPath()+"%");
			}
			hql+=" order by createTime desc ,downloadSumCount asc ";
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
			return taskDao.getList(p);
		}
		return null;
	}
	/*
	 * 根据当前用户id查询已完成并且未下载的任务数量（下载次数=0）
	 */
	public int findUserTaskFinishByUserId(int userId) throws Exception {
		PageParame p = new PageParame();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (userId > 0) 
		{
			//用户id
			hql+=" and userId=" + Constants.PLACEHOLDER;
			list.add(userId);
			//状态
			hql+=" and status=" + Constants.PLACEHOLDER;
			list.add(Constants.EXPORT_EXCEL_STATUS_FINISH);
			//下载次数
			hql+=" and downloadSumCount=" + Constants.PLACEHOLDER;
			list.add(0);
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
			return taskDao.getCounts(p);
		}
		return 0;
	}
	
}
