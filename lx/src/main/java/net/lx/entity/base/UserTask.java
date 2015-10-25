package net.lx.entity.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 任务管理
 * 
 */
@Entity
@Table(name = "tb_e_task")
public class UserTask implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;
	// 用户ID
	private int userId;
	// 标题
	private String title;
	// 备注
	private String remark;
	// 状态
	private int status; // 状态：-1未开始，EXPORT_EXCEL_STATUS_BEING正在进行，EXPORT_EXCEL_STATUS_FINISH已完成
	// 下载次数
	private int downloadSumCount;
	// 路径
	private String path;
	//创建时间
	private Date createTime;
	//完成时间
	private Date completeTime;
	@Transient
	transient private Map<String,String> map;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDownloadSumCount() {
		return downloadSumCount;
	}

	public void setDownloadSumCount(int downloadSumCount) {
		this.downloadSumCount = downloadSumCount;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

}
