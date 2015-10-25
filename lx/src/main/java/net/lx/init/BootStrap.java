package net.lx.init;

import java.util.ArrayList;
import java.util.List;

import net.lx.init.dao.InitCacheDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 初始化类
 * 
 */
@Component
public class BootStrap {

	@Autowired
	private InitCacheDao initCacheDao;

	private final Log log = LogFactory.getLog(BootStrap.class);

	/**
	 * 初始化系统数据
	 */
	public void init() {
		log.info("用户自定义初始化数据......");

//		JdbcTemplate jt = initCacheDao.getJdbcTemplate();
//		StringBuffer sql_=new StringBuffer("");
//
//		int step=3000;
//		int count =6000000;
//		for (int i = 0; i < count; i++) {
//			if(i%step==0&&!sql_.toString().equals("")){
//				//执行插入
//				System.out.println(sql_.toString());
//				jt.update(sql_.toString());
//				System.out.println(i);
//				//清理
//				sql_=new StringBuffer("");
//			}else{
//				if(sql_.toString().equals("")){
//					sql_.append("INSERT INTO `tb_e_student` VALUES ");
//					sql_.append("(NULL,15,NULL,0,40,3,'610102199204013122',1,0,NULL,0,3,'',183,1,25,0,0,0,13,'0',353,'15313803912','2012-04-06 14:24:35','','吴诗瑶"+i+"',13,NULL,NULL,NULL,0,NULL,'',NULL,'',NULL,NULL,NULL,10,0,425,NULL,1,1,0,16,'',0,0,0,0,3,0,0,'2012-04-13 17:39:07',0,0,0,NULL,0,NULL,0,NULL,13,0,0,0)");
//				}else{
//					sql_.append(",");
//					sql_.append("(NULL,15,NULL,0,40,3,'610102199204013122',1,0,NULL,0,3,'',183,1,25,0,0,0,13,'0',353,'15313803912','2012-04-06 14:24:35','','吴诗瑶"+i+"',13,NULL,NULL,NULL,0,NULL,'',NULL,'',NULL,NULL,NULL,10,0,425,NULL,1,1,0,16,'',0,0,0,0,3,0,0,'2012-04-13 17:39:07',0,0,0,NULL,0,NULL,0,NULL,13,0,0,0)");
//				}
//			}
//		}
//		System.out.println("初始化完成...");
	}

}
