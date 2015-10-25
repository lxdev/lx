package net.lx.entity.basesetting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 基础字典
 *
 */
@Entity
@Table(name = "tb_e_base_dict")
public class BaseDict implements Serializable{

	private static final long serialVersionUID = 2706412774932434653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;//主键ID
	
	@Column(name = "code")
	private String code;//编码
	
	@Column(name = "name")
	private String name;//名称
	
	/**
	 *  1：学制                       2：缴费类型            3：缴费方式    4：缴费次数(缴费期)  5：费用科目     6：费用性质
	 *	7：其他应收类型   8：学籍异动类别  9：招生途径  10：市场途径                       11：数据来源 
	 */
	@Column(name = "types")
	private int type;//类型
	
	@Column(name="status")
	private int status;//状况 
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记  0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Column(name = "logic_node")
	private String logicNode;//逻辑节点
	
	@Column(name = "parent_node")
	private int parentNode;//逻辑节点
	
	@Column(name = "order_number")
	private int orderNumber;//排序编号

	@Transient
	transient private List<BaseDict> baseDictList=null;  //保存二级数据
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.replaceAll("[\\s]", "");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.replaceAll("[\\s]", "");
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getLogicNode() {
		return logicNode;
	}

	public void setLogicNode(String logicNode) {
		this.logicNode = logicNode;
	}

	public int getParentNode() {
		return parentNode;
	}

	public void setParentNode(int parentNode) {
		this.parentNode = parentNode;
	}

	public List<BaseDict> getBaseDictList() {
		return baseDictList;
	}

	public void setBaseDictList(List<BaseDict> baseDictList) {
		this.baseDictList = baseDictList;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	
}
