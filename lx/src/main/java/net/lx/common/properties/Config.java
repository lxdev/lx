package net.lx.common.properties;

import java.io.File;
import java.util.Properties;

import net.lx.common.Constants;

/**
 * 加载和维护整体配置
 * 
 */
public class Config {
	private static Properties properties;

	/**
	 * 工厂方法：通过配置文件创建对象
	 * 
	 * @param aClassNameProp
	 * @param aDefault
	 * @return
	 */
	public static Object createObject(String aClassNameProp, String aDefault) {
		Class clazz = getClass(aClassNameProp, aDefault);
		try {
			return clazz.newInstance();
		} catch (Throwable t) {
			// Usually a misconfiguration
			return null;
		}
	}

	public static Class getClass(String aClassNameProp, String aDefault) {
		String clazz = (aDefault == null ? getProperty(aClassNameProp)
				: getProperty(aClassNameProp, aDefault));

		try {
			return Class.forName(clazz);
		} catch (ClassNotFoundException t) {
			return null;
		}
	}

	/**
	 * 加载事件源从配置文件
	 */
	public static void load() {
		try {
			if (properties != null) {
				properties.clear();
				properties = null;
			}
			// windows是\，unix是/
			// 加载配置（从类路径或WEB-INF文件根路径）
			String filePath = Constants.PATH + File.separator + Constants.APP;
			try {

				properties = Sys.loadPropertiesResource(filePath);
			} catch (Throwable t2) {
				properties = Sys.loadPropertiesFile(filePath);
				return;
			}

		} catch (Throwable t) {

		}

	}

	public static String getProperty(String aName, String aDefault) {

		return properties.getProperty(aName, aDefault);
	}

	public static String getProperty(String aName) {
		try{
			String value = properties.getProperty(aName);
			if (value == null) {
				load();
				value = properties.getProperty(aName);
				//throw new IllegalArgumentException("Unknown property: " + aName);
			}
			return value;
		}catch(Exception e){
			load();
			return properties.getProperty(aName);
		}
	}

	public static boolean getBoolProperty(String aName) {
		String value = getProperty(aName);
		try {
			return value.equals("true");
		} catch (Throwable t) {
			throw new IllegalArgumentException("Illegal property value: "
					+ aName + " val=" + value);
		}
	}

	public static int getIntProperty(String aName) {
		String value = getProperty(aName);
		try {
			return Integer.parseInt(value);
		} catch (Throwable t) {
			throw new IllegalArgumentException("Illegal property value: "
					+ aName + " val=" + value);
		}
	}

	public static long getLongProperty(String aName) {
		String value = getProperty(aName);
		try {
			return Long.parseLong(value);
		} catch (Throwable t) {
			throw new IllegalArgumentException("Illegal property value: "
					+ aName + " val=" + value);
		}
	}

	public static boolean hasProperty(String aName) {
		return properties.containsKey(aName);
	}

}
