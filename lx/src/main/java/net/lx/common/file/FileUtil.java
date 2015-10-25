package net.lx.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.lx.common.Constants;
import net.lx.common.properties.Config;

public class FileUtil {
	public static String ReadFile(File file, String encodin) throws Exception {
		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(file), encodin);
		StringBuffer buffer = new StringBuffer();
		Reader in = new BufferedReader(isr);
		int ch;
		while ((ch = in.read()) > -1) {
			buffer.append((char) ch);
		}
		in.close();
		return buffer.toString();
	}

	/**
	 * 检查文件夹是否存在，不存在则创建
	 * 
	 * @param path
	 */
	public static void CheckCreateDR(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
		} else {
			file.mkdirs();
		}
	}

	/**
	 * 检查并处理文件上传路径
	 * 
	 * @param path
	 * @param date
	 */
	public static String GetFileUploadPath(String path, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		path += File.separator + year;
		CheckCreateDR(path);
		sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		path += File.separator + month;
		CheckCreateDR(path);
		return path + File.separator;
	}

	/**
	 * 检查并处理文件上传路径
	 * 
	 * @param path
	 */
	public static String GetFileUploadPath(String path, String idpath) {
		path += "/" + idpath.substring(0, 2);
		CheckCreateDR(path);
		// System.out.println(idpath.substring(2));
		path += "/" + idpath.substring(2);
		CheckCreateDR(path);
		return path + "/";
	}

	/**
	 * 文件复制
	 * 
	 * @param src
	 * @param dst
	 */
	public static void Copy(File src, File dst, final int BUFFER_SIZE) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static boolean deleteFile(String sPath) {
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
	 * 文件上传方法
	 * 
	 * @param path
	 *            从配置文件读取的路径 String
	 *            path=ServletActionContext.getServletContext()
	 *            .getRealPath(getText("msg.user.notice.uploadpath"));
	 * @param filename
	 *            服务器存放文件名（自定义处理后的）
	 * @param uploadFileName
	 *            上传的原始文件名
	 * @param file
	 *            文件
	 * @return 返回服务器存放相对路径
	 */
	@SuppressWarnings("unused")
	private static String FileUpload(String path, String filename,
			String uploadFileName, File file) throws Exception {
		String path_t = path.replace("\\", "/");
		return copy(file, FileUtil.GetFileUploadPath(path_t, new Date())
				+ filename, path, uploadFileName);
	}

	/**
	 * 文件上传
	 * 
	 * @param savePath
	 *            物理保存路径
	 * @param fileName
	 *            原始文件名称
	 * @param file
	 *            文件
	 * @param returnPath
	 *            服务器相对路径
	 * @return
	 * @throws Exception
	 */
	public static String FileUploads(String savePath, String fileName, File file)
			throws Exception {
		String returnPath = savePath.substring(
				savePath.indexOf(Config.getProperty("system.upload")) - 1,
				savePath.length()).replace('\\', '/');
		returnPath = copy(file, GetFileUploadPath(savePath, new Date())
				+ new Date().getTime() + "", returnPath, fileName);
		try {
			File delfile = new File(file.getPath());
			delfile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPath;
	}

	public static String copy(File src, String savePath, String returnPath,
			String UploadFileName) {
		int pos = UploadFileName.lastIndexOf(".");

		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		String dstPath = savePath + UploadFileName.substring(pos).toLowerCase();// 获得后缀名
		InputStream in = null;
		OutputStream out = null;
		File dstFile = new File(dstPath);

		System.out.println("上传的文件的类型：");
		System.out.println("上传保存路径是：" + savePath);
		System.out.println(dstPath);
		try {
			in = new BufferedInputStream(new FileInputStream(src));
			out = new BufferedOutputStream(new FileOutputStream(dstFile));
			byte[] buffer = new byte[Constants.BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			dstPath = null;
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		dstPath = dstPath.replace("//", "/").replace("\\", "/");
		returnPath = returnPath.replace("//", "/");
		dstPath = dstPath.substring((dstPath.indexOf(returnPath)));
		dstPath = dstPath.substring(1);
		return dstPath;
	}
	
	/**
	* 删除文件夹(文件夹下的内容全部删除)
	* @param folderPath 文件夹完整绝对路径
	* @return
	*/
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹
		}
		catch (Exception e) {
			//删除文件夹操作出错
		}
	}
	
	/**
	* 删除指定文件夹下所有文件
	* @param path 文件夹完整绝对路径
	* @return
	* @return
	*/
	public static boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!file.exists()) {
			return bea;
		}
		if (!file.isDirectory()) {
			return bea;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			}else{
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件
				delFolder(path+"/"+ tempList[i]);//再删除空文件夹
				bea = true;
			}
		}
		return bea;
	}
	
	/**
	 * 将文件改名(或后缀名)
	 * 例如：renamed("c:\my\","1.txt","2.txt");
	 * 或者：renamed("c:\my\1",".txt",".doc");
	 * 注：此方法必须在释放文件资源之后才可调用！
	 * @param filePath 文件路径
	 * @param oldName 原文件名(或后缀名)
	 * @param newName 新文件名(或后缀名)
	 * @return
	 */
	public static boolean renamed(String filePath,String oldName,String newName){
		File oldFile = new File(filePath+oldName);
		if(oldFile.exists()){
			File newFile = new File(filePath+newName);
			if(!newFile.exists()){
				return oldFile.renameTo(newFile);
			}
		}
		return false;
	}
}
