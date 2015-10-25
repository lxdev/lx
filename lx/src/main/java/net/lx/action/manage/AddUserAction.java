package net.lx.action.manage;

import java.io.File;
import java.text.SimpleDateFormat;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.common.file.FileUtil;
import net.lx.common.il8n.ResourcesTool;
import net.lx.entity.user.User;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 添加用户
 * @author Jack
 *
 */
public class AddUserAction extends BaseAction implements ModelDriven<User> 
{
	private static final long serialVersionUID = -6269671244358345372L;

	@Autowired
	private UserBiz userBiz;
	
	private User users=new User();
	private File files;                           //文件 
	private String filesFileName;                 //原始文件名称
	
	private String savePath;                      //服务器路径
	
	private boolean results=false;
	
	public String execute()
	{
		try
		{
			if(isGetRequest())
			{
				return INPUT;
			}
			if(null!=files)
			users.setPhoto_url(uploadFile(filesFileName));
			users.setCreated_user_id(getUser().getUserId());
			users.setUpdated_user_id(users.getCreated_user_id());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			users.setUpdate_password_time(sdf.parse("2011-01-01 00:00:00"));
			results=userBiz.createNew(users);
			if(results)
				addActionMessage(ResourcesTool.getText("admin","add.success"));
			else
				addActionMessage(ResourcesTool.getText("admin", "username.repeat.error"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	/**
	 * 附件上传_返回
	 * @param name 实际文件名
	 * @return	存储的相对路径
	 */
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("admin","uploadpath"));
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	public User getModel() {
		return users;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public boolean getResults() {
		return results;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}
}
