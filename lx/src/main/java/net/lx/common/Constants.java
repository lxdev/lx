package net.lx.common;

/**
 * 静态变量存储
 * 
 */
public class Constants {

	// 系统是否测试
	public static final boolean IS_SYSTEM_TEST = true;
	
	//锁定状态
	public static final int LOCKING_TRUE=1;
	public static final int LOCKING_FALSE=0;

	public static final int INIT_ENROLLMENTSOURCE_ID = 6;// 初始化招生途径最后一条数据的id
	public static final int INIT_BASE_DICT_ID = 24;// 初始化字典表的最后一条数据的id
	public static final int INT_FEESUBJECT_ID = 7;// 初始化费用科目的最后一条数据的id

	public static boolean IS_TURNED_DELETE = true;// 是否开启真删除
	
	// 缴费方式
	public static final int PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU = 3; // 第三方支付
	public static final int PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU = 4; // 现金汇总部
	public static final int PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO = 5; // 现金汇院校
	public static final int PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU = 6; // POS直汇总部
	public static final int PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO = 7; // POS直汇院校
	public static final int PAYMENT_METHOD_WANG_YIN_ZONG_BU = 8; // 网银总部
	public static final int PAYMENT_METHOD_WANG_YIN_YUAN_XIAO = 9; // 网银院校

	public static final int AUDIT_STATUS_INIT = -1;// 初始状态
	public static final int AUDIT_STATUS_FALSE = 0;// 未审批
	public static final int AUDIT_STATUS_TRUE = 1;// 审批通过
	public static final int AUDIT_STATUS_FAIL = 2;// 审批不通过
	public static final int AUDIT_STATUS_ALL = 3;// 全部

	public static final int Fee_STATUS_TRUE = 1;// 已缴费
	public static final int Fee_STATUS_FALSE = 0;// 未缴费


	public static final int DELETE_TRUE = 1; // 标记删除
	public static final int DELETE_FALSE = 0; // 正常

	public static final int ISNEED_REBATES_TRUE = 1; // 需要返款
	public static final int ISNEED_REBATES_FALSE = 0; // 不需要返款

	public static final int STATUS_AUTHOR_TRUE = 1;// 授权
	public static final int STATUS_AUTHOR_FALSE = 0;// 未授权

	public static final int STATUS_ENABLED = 1;// 启用
	public static final int STATUS_DISABLE = 0;// 禁用
	public static final int STATUS_FINISHED = 2;// 结束状态
	public static final int STATUS_SYS_INIT = -1;// 系统内置

	public static final int BUFFER_SIZE = 16 * 1024;// 上传文件缓存
	public static final int SEX_MALE = 1; // 男
	public static final int SEX_FAMALE = 0; // 女
	public static final String SPLITTER = ",";// 分割符
	public static final String PLACEHOLDER = "##";// 占位符
	public static final String APP = "app.properties";
	public static String PATH = "";// 系统启动,WEB-INF/classes路径=

	public static String WEB_PATH = "";// web目录
	public static String WEB_ATTACHMENT = "";// 附件服务器地址
	public static String WEB_IMAGES = "";// 图片服务器地址
	public static String WEB_PLUGINS = "";// js css插件服务器地址

	// 证件类型
	public static int CERTIFICATE_TYPE_ID = 1;// 身份证
	public static int CERTIFICATE_TYPE_DRIVER_ID = 2;// 驾照
	public static int CERTIFICATE_TYPE_NCO_ID = 3;// 士官证

	// 日期时间格式化
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// 国际化
	public final static String IL8N_RESOURCES_PREFIX = "messageResource";
	public final static String IL8N_RESOURCES_DEFAULT = "global";

	// 提醒类型
	public static final int CAL_REMIND_NONE = -1;// 无
	public static final int CAL_REMIND_MESSAGE = 1;// 信息
	public static final int CAL_REMIND_EMAIL = 2;// 邮件提醒
	// 提醒的时间类型
	public static final int CAL_REMIND_TIME_MINUTES_AGO = 1;// 几分钟前
	public static final int CAL_REMIND_TIME_HOURS_AGO = 2;// 几小时前
	public static final int CAL_REMIND_TIME_DAYS_AGO = 3;// 几天前
	public static final int CAL_REMIND_TIME_MINUTES_AFTER = 4;// 几分钟后
	public static final int CAL_REMIND_TIME_HOURS_AFTER = 5;// 几小时后
	public static final int CAL_REMIND_TIME_DAYS_AFTER = 6;// 几天后
	public static final int CAL_REMIND_TIME_DATE = 7;// 于日期

	public static final int TRUE = 1; // 真值
	public static final int FALSE = 0; // 假值


	public static final int IS_CREATE_ACCOUNT_TRUE = 1; // 已创建账户
	public static final int IS_CREATE_ACCOUNT_FALSE = 0; // 未创建账户


	//同时导出excel最大用户数
	public static final int EXPORT_EXCEL_MAX_USER_COUNT=5;
	//一个用户同时最大完成任务数
	public static final int EXPORT_EXCEL_MAX_USERTASK_COUNT=1;
	
	//导出任务状态
	public static final int EXPORT_EXCEL_STATUS_BEING=1;//正在进行
	public static final int EXPORT_EXCEL_STATUS_FINISH=2;//已完成
	public static final int EXPORT_EXCEL_STATUS_FAILURE=3;//异常
	
	//导出任务错误编号
	public static final int EXPORT_EXCEL_FINISH=-1;//导出成功
	public static final int EXPORT_EXCEL_ERROR_NO_0=0;//空任务/添加错误
	public static final int EXPORT_EXCEL_ERROR_NO_1=1;//资源达到最大连接数
	public static final int EXPORT_EXCEL_ERROR_NO_2=2;//当前用户达到最大连接数
	public static final int EXPORT_EXCEL_ERROR_NO_3=3;//导出集合为空
	public static final int EXPORT_EXCEL_ERROR_NO_4=4;//导出数据量超过上限
	
	//导出任务默认导出数据量上限(只有app配置文件出问题才会走默认)
	public static final int EXPORT_EXCEL_MAX_COUNT=20000;
	//导出任务默认导出EXCEL每个文件最大数据量(只有app配置文件出问题才会走默认)
	public static final int EXPORT_EXCEL_MAX_PAGE_SIZE=5000;
	
	
	public static final String ASC="ASC";
	public static final String DESC="DESC";
	
	
	//用户类型	2015
	public static final int USER_STUDENT = 1;
	public static final int USER_CONSULTANT = 2;
	public static final int USER_MANAGER = 3;
	//student type		1 高中 2 本科 3 硕士 4 在职 5 家长 6 海外留学生',
	public static final int USER_STUDENT_1 = 1;
	public static final int USER_STUDENT_2 = 2;
	public static final int USER_STUDENT_3 = 3;
	public static final int USER_STUDENT_4 = 4;
	public static final int USER_STUDENT_5 = 5;
	public static final int USER_STUDENT_6 = 6;
	//1 关注问题；2 收藏院校；3 收藏项目；4 收藏攻略; 5 顾问
	public static final int FOLLOW_ASK = 1;
	public static final int FOLLOW_UNIVERSITY = 2;
	public static final int FOLLOW_PROGRAM = 3;
	public static final int FOLLOW_GUIDE = 4;
	public static final int FOLLOW_CONSULTANT = 5;
	//问问题 类型
	public static final int ASK_ASSIGN = 1;
	public static final int ASK_EVERY = 2;
	//问问题 状态
	public static final int ASK_STATUS_DRAFT = 0;
	public static final int ASK_STATUS_WAIT = 1;
	public static final int ASK_STATUS_ING = 2;
	public static final int ASK_STATUS_FINISH = 4;
	//问题 回复类型   1：赞；2 回复；3 关注; 4 quote
	public static final int REPLY_PRAISE = 1;
	public static final int REPLY_REPLY = 2;
	public static final int REPLY_ATTENTION = 3;
	public static final int REPLY_QUOTE = 4;
	//evaluate type 
	public static final int EVALUATE_TYPE_UNIVERSITY = 1;
	public static final int EVALUATE_TYPE_CONSULTANT = 2;
}
