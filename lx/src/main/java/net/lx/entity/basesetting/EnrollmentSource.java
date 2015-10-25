package net.lx.entity.basesetting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 基础设置
 *
 */
@Entity
@Table(name = "tb_e_enrollment_source")
public class EnrollmentSource implements Serializable{

	private static final long serialVersionUID = -6543818655695811159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;//主键ID
	
	@Column(name = "code")
	private String code;//编码
	
	@Column(name = "name")
	private String name;//招生途径名称
	
	@Column(name = "types")
	private int type;//类别 0-无类型  1-个人  2-公司
	
	@Column(name="isneed_rebates")
	private int isneedRebates;//是否需要渠道返款    0-否,1-是
	
	@Column(name="source_rebates_feesubject")
	private String sourceRebatesFeesubject;//渠道返款费用科目
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记   0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Transient
	transient private String subjectnames;//专业名称
	
	@Transient
	transient private double baomingNum;     //报名人数
	@Transient
	transient private double luquNum;     //录取人数
	
	@Transient
	transient private double luqulv;     //录取率
	
	@Transient
	transient private double jiaofeiNum;     //缴费人数
	
	@Transient
	transient private double jiaofeilv;     //缴费率
	
	@Transient
	transient private double jiaofeiMoney;     //缴费金额
	

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

	public int getIsneedRebates() {
		return isneedRebates;
	}

	public void setIsneedRebates(int isneedRebates) {
		this.isneedRebates = isneedRebates;
	}

	public String getSourceRebatesFeesubject() {
		return sourceRebatesFeesubject;
	}

	public void setSourceRebatesFeesubject(String sourceRebatesFeesubject) {
		this.sourceRebatesFeesubject = sourceRebatesFeesubject;
	}

	public String getSubjectnames() {
		return subjectnames;
	}

	public void setSubjectnames(String subjectnames) {
		this.subjectnames = subjectnames;
	}

	

	public double getBaomingNum() {
		return baomingNum;
	}

	public void setBaomingNum(double baomingNum) {
		this.baomingNum = baomingNum;
	}

	public double getLuquNum() {
		return luquNum;
	}

	public void setLuquNum(double luquNum) {
		this.luquNum = luquNum;
	}

	public double getJiaofeiNum() {
		return jiaofeiNum;
	}

	public void setJiaofeiNum(double jiaofeiNum) {
		this.jiaofeiNum = jiaofeiNum;
	}

	public void setJiaofeiNum(int jiaofeiNum) {
		this.jiaofeiNum = jiaofeiNum;
	}

	public double getJiaofeiMoney() {
		return jiaofeiMoney;
	}

	public void setJiaofeiMoney(double jiaofeiMoney) {
		this.jiaofeiMoney = jiaofeiMoney;
	}

	public double getLuqulv() {
		return luqulv;
	}

	public void setLuqulv(double luqulv) {
		this.luqulv = luqulv;
	}

	public double getJiaofeilv() {
		return jiaofeilv;
	}

	public void setJiaofeilv(double jiaofeilv) {
		this.jiaofeilv = jiaofeilv;
	}

	
	
}
