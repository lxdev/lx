package net.lx.common.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lx.common.Constants;
import net.lx.common.date.DateUtil;
import net.lx.common.reflection.RuntimeUtil;
import net.lx.common.servlet.SingletonServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库操作（单例模式）
 *
 */
public class SingletonDatabaseUtil {

	private final static Log log=LogFactory.getLog(SingletonDatabaseUtil.class);
	private static SingletonDatabaseUtil singletonDatabaseUtil=null;
	private static String databaseUserName;//数据库用户名
	private static String databasePassword;//数据库用户密码
	
	private static String databaseName;//需要备份的数据库
	private static String mysqldumpPath;//备份数据库命令的路径
	private static String mysqlPath;//备份数据库命令的路径
	
	private static String databaseBackupPath;//数据库备份路径
	
	
	private SingletonDatabaseUtil (){
		
	}

//	public static SingletonDatabaseUtil newInstance() {
//		if(singletonDatabaseUtil==null){
//			log.error("未初始化数据库备份, 还原参数....");
//		}
//		return singletonDatabaseUtil;
//	}
	/**
	 * 初始化备份数据库信息
	 * @param databaseUserName
	 * @param databasePassword
	 * @param databaseName
	 * @param mysqldumpPath
	 */
	public static void init(String databaseUserName,String databasePassword,String databaseName,String mysqlPath,String mysqldumpPath,String databaseBackupPath){
		if(singletonDatabaseUtil==null){
			log.info("开始初始化数据库备份，还原参数....");
			singletonDatabaseUtil=new SingletonDatabaseUtil();
			singletonDatabaseUtil.databaseUserName=databaseUserName;//数据库用户名
			singletonDatabaseUtil.databasePassword=databasePassword;//数据库用户密码
			singletonDatabaseUtil.databaseName=databaseName;//需要备份的数据库
			singletonDatabaseUtil.mysqldumpPath=mysqldumpPath;//备份数据库命令的路径
			singletonDatabaseUtil.mysqlPath=mysqlPath;
			singletonDatabaseUtil.databaseBackupPath=databaseBackupPath;//数据库备份路径
			log.info("结束初始化....");
		}
	}
	/**
	 * 备份数据库所有表
	 * @param sqlRemark
	 * @return
	 */
	public static boolean backupDatabseAllTable(String sqlRemark){
		if(singletonDatabaseUtil==null){
			log.error("未初始化数据库备份参数....");
			return false;
		}
		log.info("开始数据库备份,压缩....");
		String databaseBackupDetailPath=SingletonServletContext.newInstance().getRealPath(databaseBackupPath)+File.separator+databaseName+DateUtil.dateToStringWithTime(new Date())+".sql.gz";
		if(RuntimeUtil.executeCommand(new String[]{"sh","-c",mysqldumpPath+" -u"+databaseUserName+" -p"+databasePassword+" "+databaseName+" | gzip >"+databaseBackupDetailPath})){
			log.info("结束数据库备份,压缩....");
			File filename = new File(databaseBackupDetailPath+".txt");
			PrintWriter pw;
			try {
				pw = new PrintWriter( new FileWriter(databaseBackupDetailPath+".txt") );
				if(sqlRemark==null){
					pw.print("没有备注...");
				}else{
					pw.print(sqlRemark);
				}
				
				pw.close();
			} catch (IOException e) {
				log.error("备注失败!错误信息:"+e.getMessage());
				e.printStackTrace();
			}
			return true;
		}
		log.error("数据库备份失败....");
		return false;
	}
	/**
	 * 恢复数据库通过sql文件
	 * @param sqlFileName
	 * @return
	 */
	public boolean restoreDatabseAllTable(String sqlFileName){
		if(singletonDatabaseUtil==null){
			log.error("未初始化数据库还原参数....");
			return false;
		}
		String databaseBackupDetailPath=SingletonServletContext.newInstance().getRealPath(databaseBackupPath)+File.separator+sqlFileName;
		log.info("开始数据库还原....还原文件为："+databaseBackupDetailPath);
		if( RuntimeUtil.executeCommand(new String[]{"sh","-c","gunzip < "+databaseBackupDetailPath+" | "+mysqlPath+" -u"+databaseUserName+" -p"+databasePassword+" "+databaseName})){
			log.info("结束数据库还原....还原文件为："+databaseBackupDetailPath);
			return true;
		}else{
			log.info("数据库还原失败....还原文件为："+databaseBackupDetailPath);
			return false;
		}
	}
	/**
	 * 获取备份文件列表
	 * @return
	 */
	public static List<Map<String,String>> sqlFileList(){
		List<Map<String,String>> result=new ArrayList<Map<String,String>>(); 
		Map<String,String> object=null;
		List<String> list=getDirFileNameList(SingletonServletContext.newInstance().getRealPath(SingletonDatabaseUtil.databaseBackupPath),".sql.gz");
		if(list!=null){
			for (String sqlFileName : list) {
				 object=new HashMap<String, String>();
				 object.put("sqlFileName", sqlFileName);
				 object.put("sqlFilePath", Constants.WEB_PATH+SingletonDatabaseUtil.databaseBackupPath+sqlFileName);
				 object.put("sqlFileRemarkPath", Constants.WEB_PATH+SingletonDatabaseUtil.databaseBackupPath+sqlFileName+".txt");
				 result.add(object);
			}
		}
		return result;
	}
	/**
	 * 删除sql文件
	 * @param sqlName
	 * @return
	 */
	public static boolean deleteSqlFile(String sqlName){
		if(sqlName!=null){
			deleteFile(SingletonServletContext.newInstance().getRealPath(SingletonDatabaseUtil.databaseBackupPath)+File.separator+sqlName+".txt");
			return deleteFile(SingletonServletContext.newInstance().getRealPath(SingletonDatabaseUtil.databaseBackupPath)+File.separator+sqlName);
		}
		return false;
	}
	/**
	 * 删除文件
	 * @param sPath
	 * @return
	 */
	private static boolean deleteFile(String sPath) {
		try {
			File file = new File(sPath); // 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("file delete error!");
			return false;
		}
		return true;
	}
	
	/**
	 * 获取文件夹所有文件
	 * @param dirPath 文件夹目录
	 * @param filterStr 文件后缀过滤
	 * @return
	 */
	private static List<String> getDirFileNameList(String dirPath,String filterStr){
		List<String> fileNameList=new ArrayList<String>();
		File file = new File(dirPath);
		File[] fileArray=file.listFiles();
		if(fileArray!=null){
			String fileName="";
			for (File file_ : fileArray) {
				fileName=file_.getName();
				if(filterStr!=null&&!filterStr.equals("")){
					if(fileName.endsWith(filterStr)){
						fileNameList.add(fileName);
					}
				}else{
					fileNameList.add(fileName);
				}
			}
		}
		return fileNameList;
	}
	
}
