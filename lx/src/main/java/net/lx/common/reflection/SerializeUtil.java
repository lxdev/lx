package net.lx.common.reflection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	// 序列化对象到文件
	public static void serialize(String fileName, Object object) {
		try {
			// 创建一个对象输出流，讲对象输出到文件
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(fileName));
			out.writeObject(object); // 序列化一个会员对象
			out.close();
		} catch (Exception x) {
			System.out.println(x.toString());
		}

	}

	// 从文件反序列化到对象
	public static String deserialize(String fileName) {
		try {
			// 创建一个对象输入流，从文件读取对象
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					fileName));

			// 调用它的toString()方法
			Object object = in.readObject();
			in.close();
			return object.toString();

		} catch (Exception x) {
			return "";
		}

	}
	public static void main(String[] args) {
//		System.out.println(deserialize("/Users/[]/Desktop/s.txt"));
	}
}
