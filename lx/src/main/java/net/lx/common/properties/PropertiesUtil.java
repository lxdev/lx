package net.lx.common.properties;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Properties 公用类 默认获取classpath下的properties文件
 * 
 */
public class PropertiesUtil extends ArrayList {
	private static final long UID = 1L;

	private String code = "UTF-8"; // 设置编码方式

	private String fileName; // 文件名包括路径和后缀

	public static void main(String[] args) throws Exception {// java程序主入口处
		String path = "/java/workspace/lx/src/conf/jdbc.properties";
		PropertiesUtil proper = new PropertiesUtil(path, "UTF-8");
		proper.setProperties("datasource.master.username", "devsun");
		proper.saveFile();// 将内容写入属性文件
//		System.out.println(readFile(path, "UTF-8")); // 读取属性文件内容
//		proper.getValue();// 读取项目中的属性文件的键值
	}

	public String getFileName() { // 获得文件名字
		return fileName;
	}

	private void setFileName(String fileName) { // 设置文件名字
		this.fileName = fileName;
	}

	// 带参数的构造方法
	public PropertiesUtil(String fileName, String code) {
		try {
			this.setFileName(fileName); // 设置文件
			// 调用方法设置编码方式
			this.setCharacterEncoding(code);
			if (!isExist(fileName)) // 判断文件是否存在
				this.writeFile("");
			// 调用方法将元素放入集合中
			this.addAll(Arrays.asList(readFile(fileName, code).split("\n")));
		} catch (Exception ex) { // 捕获异常
			ex.printStackTrace();
		}
	}

	private void setCharacterEncoding(String code)
			throws UnsupportedEncodingException { // 设置编码方式
		new String("".getBytes("iso8859_1"), code);// 编码转换
		this.code = code;
	}

	public static boolean isExist(String fileName) { // 判断文件是否存在
		return new File(fileName).isFile(); // 是否是一个文件
	}

	public static String readFile(String fileName, String code)
			throws IOException { // 读取信息
		StringBuffer sb = new StringBuffer(); // 创建字符缓冲流
		BufferedReader in = new BufferedReader( // 创建缓冲读对象
				new FileReader(fileName));
		String s;
		while ((s = in.readLine()) != null) { // 循环读取文件中的信息
			sb.append(s); // 字符串拼接
			sb.append("\n"); // 换行
		}
		in.close(); // 释放资源
		return sb.toString(); // 返回读取的字符串
	}

	public void writeFile(String proper) throws IOException { // 字符串写入文件
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName))); // 创建文本输出流打印对象
		out.print(proper); // 将字符串写入指定文件
		out.close(); // 释放资源
	}

	public void saveFile() throws IOException { // 数据保存到文件中
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName))); // 创建文本输出流打印对象
		String tmp;
		for (int i = 0; i < size(); i++) { // 循环显示集合信息输出到控制台
			tmp = get(i) + "";
			out.println(tmp);
		}
		out.close();
	}

	public void setProperties(String key, String val) { // 设置Properties键值
		int pro = lookForKey(key);
		if (pro >= 0)
			this.set(pro, key + "=" + val);
		else
			this.add(key + "=" + val);
	}

	public int lookForKey(String key) { // 查找键序号
		try {
			String temp;
			for (int i = 0; i < size(); i++) { // 循环显示集合信息
				temp = get(i) + "";
				temp = new String(temp.getBytes("iso8859_1"), code);// 编码转换
				if (temp.indexOf(key) == 0) { // 没有找到键值
					return i;
				}
			}
		} catch (Exception e) { // 捕获异常
		}
		return -1;
	}

	public void setNotes(String key, String memo) { // 增加备注
		if ("".equals(key)) {
			this.add("#" + memo);
			return;
		}
		String temp;
		int result = lookForKey(key);
		if (result == -1) { // 如果没有找到
			this.add("#" + memo);
			this.add(key + "=");
		} else {
			int position = result - 1;
			if (position < 0) {
				this.add(position, "#" + memo);
			} else {
				temp = this.get(position) + " ";
				if ("#".equals(temp.substring(0, 1))) // 判断截取值是否与#相同
					this.set(position, "#" + memo);
				else {
					this.add(position + 1, "#" + memo);
				}
			}
		}
	}

	public void setTitle(String title) { // 设置注释内容
		String tmp = this.get(0) + "";
		if (tmp == null || tmp.length() == 0)
			tmp = "";
		else
			tmp = tmp.substring(0, 1); // 截取第一个元素
		if ("#".equals(tmp)) // 判断第一个元素是否是#
			this.set(0, "#" + title); // 增加注释内容
		else {
			this.add(0, "");
			this.add(0, "#" + title);
		}
	}

	public String getProperties(String key) { // 获取键对应的值
		return getProperties(key, "");
	}

	public String getProperties(String key, String defaultStr) {
		String temp, result;
		try {
			for (int i = 0; i < size(); i++) { // 循环显示集合信息
				temp = get(i) + ""; // 获得元素
				temp = new String(temp.getBytes("iso8859_1"), code);// 编码转换
				if (temp.indexOf(key) == 0) { // 找到指定的键
					result = temp.substring(key.length() + 1);// 截取获得键对应的值
					return result;
				}
			}
		} catch (Exception e) { // 捕获异常
		}
		return defaultStr;
	}

	public void getValue() {// 获得项目中的properties文根据键取得相应的值
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					"proper.properties")); // 创建输入流对象
			Properties p = new Properties(); // 创建属性对象
			p.load(in); // 加载输入流对象
			String s = (String) p.get("studentName"); // 获得键值
			if (s != null) {
				// 编码转换
				String name = new String(s.getBytes("iso8859_1"), "GBK");
				System.out.println("输出studentName的值：" + name);

				String a = (String) p.get("room");
				String room = new String(a.getBytes("iso8859_1"), "GBK");
				System.out.println("输出room的值：" + room);
			}

		} catch (IOException e) { // 捕获异常
			e.printStackTrace();
		}
	}

}
