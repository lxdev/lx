package net.lx.biz.base;

import java.util.List;

import net.lx.entity.base.UserTask;
import net.lx.model.page.PageResult;

public interface TaskBiz {
	/**
	 * 查询任务列表
	 * @param pr
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public List<UserTask> findListByTask(PageResult<UserTask> pr,UserTask task) throws Exception;
	/**
	 * 查询任务总数量
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public int findCountByTask(UserTask task) throws Exception;
	/**
	 * 增加任务
	 * @param task
	 * @return -1:成功 0:空任务/添加错误 1:资源达到最大连接数 2:当前用户达到最大连接数
	 * @throws Exception
	 */
	public int addTask(UserTask task) throws Exception;
	/**
	 * 删除任务
	 * @param id
	 * @return
	 */
	public boolean deleteTask(int id) throws Exception;
	/**
	 * 修改任务
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public boolean updateTask(UserTask task) throws Exception;
	/**
	 * 根据id查任务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserTask findUserTaskById(int id) throws Exception;
	/**
	 * 根据条件查询集合
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public List<UserTask> findUserTaskByEntity(UserTask task) throws Exception;
	/**
	 * 根据当前用户id查询已完成并且未下载的任务数量（下载次数=0）
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public int findUserTaskFinishByUserId(int userId) throws Exception;
}
