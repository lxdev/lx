package net.lx.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static void main(String args[]) throws IOException {
		test2();
	}

	public static void test2() throws IOException {
		File inFile = new File("/Users/[]/Documents/日报");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("/Users/[]/Documents/日报.zip"));
		//zos.setComment("");
		zipFile(inFile, zos, "");
		zos.close();
	}

	/**
	 * 压缩文件
	 * 
	 * @param inFile  要压缩的文件目录
	 * @param zos     输出的文件目录
	 * @param dir	  
	 * @throws IOException
	 */
	public static void zipFile(File inFile, ZipOutputStream zos, String dir)throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file : files)
				zipFile(file, zos, dir + "\\" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "\\" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}

	}

}
