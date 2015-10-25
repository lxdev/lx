package net.lx.action.base;

import java.io.File;

import net.lx.action.BaseAction;
import net.lx.biz.base.TaskBiz;
import net.lx.common.file.FileUtil;
import net.lx.common.properties.Config;
import net.lx.common.servlet.SingletonServletContext;
import net.lx.entity.base.UserTask;
import net.lx.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonIndexAction extends BaseAction {

	@Autowired
	private TaskBiz taskBiz;
	// 分页结果
	private PageResult<UserTask> result = new PageResult<UserTask>();
	
	private UserTask userTask = new UserTask();
	
	//删除参数
	private int taskId = 0;
	private boolean isback = false;//删除标记
	
	private String downLoadPath="error";
	
	//已完成未下载数
	private int finishCount = 0;
	

	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "down_load_excel_file", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String downLoadExcelFile() throws Exception {
		if(taskId!=0){
			UserTask userTask=taskBiz.findUserTaskById(taskId);
			if(userTask!=null){
				
				downLoadPath = Config.getProperty("export.excel.tmp")+ userTask.getPath()+".zip";
				
				//更新下载数
				userTask.setDownloadSumCount(userTask.getDownloadSumCount()+1);
				taskBiz.updateTask(userTask);
				
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询任务总数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "base_task_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String baseTaskCount() throws Exception {
		// 查询数量
		try {
			userTask.setUserId(super.getUser().getUserId());
			result.setRecordCount(taskBiz.findCountByTask(userTask));
			System.out.println(super.getUser().getFullName()+"定时请求服务器,获取下载任务数量....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查询任务集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "base_task_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String baseTaskList() throws Exception {
		// 查询集合
		try {
			userTask.setUserId(super.getUser().getUserId());
			result.setList(taskBiz.findListByTask(result, userTask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除任务
	 * @return
	 * @throws Exception
	 */
	@Action(value = "delete_base_task", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String deleteBaseTask() throws Exception {
		// 删除任务
		if(taskId!=0)
		{
			UserTask old = taskBiz.findUserTaskById(taskId);
			if(old!=null)
			{
				isback = taskBiz.deleteTask(taskId);
				String path = Config.getProperty("export.excel.tmp")+ old.getPath();
				File file = new File(SingletonServletContext.newInstance().getRealPath(path).trim()+".zip");
				if (file.isFile()) {
					//删除压缩文件
					file.delete();
					//删除目录
					FileUtil.delFolder(SingletonServletContext.newInstance().getRealPath(path).trim());
				}
				
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询当前用户已完成并且未下载的任务数
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finish_base_task_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"finishCount.*"
	}) })
	public String finishBaseTaskCount() throws Exception {
		finishCount = taskBiz.findUserTaskFinishByUserId(super.getUser().getUserId());
		return SUCCESS;
	}

	public PageResult<UserTask> getResult() {
		return result;
	}

	public void setResult(PageResult<UserTask> result) {
		this.result = result;
	}
	
	public UserTask getUserTask() {
		return userTask;
	}

	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDownLoadPath() {
		return downLoadPath;
	}

	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}
	
}
