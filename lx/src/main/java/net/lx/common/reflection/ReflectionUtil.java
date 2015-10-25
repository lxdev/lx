package net.lx.common.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.lx.common.hibernate.SortChineseAnnotation;

/**
 * 反射方法
 * 
 */
public class ReflectionUtil {
	/**
	 * 获取主键ID
	 * 
	 * @param obj
	 * @return
	 */
	public static Object getId(Object obj, String methodName) {
		Method getMethod = null;
		try {
			getMethod = obj.getClass().getMethod(methodName);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return getMethod.invoke(obj);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断是否存在该字段
	 * 
	 * @param cls
	 * @param fieldName
	 * @return
	 */
	public static boolean isExistField(Class obj, String fieldName) {
		try {
			obj.getDeclaredField(fieldName);
			return true;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取中文排序字段
	 * 
	 * @param entityClass
	 * @param orderName
	 * @return
	 */
	public static String returnFieldName(Class entityClass, String orderName,
			String sortName) {

		String returnOrderName = ",";
		if (orderName != null) {
			if (orderName.indexOf(",") > 0) {
				int i = 0;
				String[] orders = orderName.split(",");
				// 创建一个与orderName数量相等的数组
				List<String> sorts = new ArrayList<String>();
				for (int j = 0; j < orders.length; j++) {
					sorts.add("");
				}
				// 填充集合
				if (sortName != null) {
					String[] sortArray = sortName.split(",");
					for (int j = 0; j < sortArray.length; j++) {
						sorts.set(j, sortArray[i]);
					}
				}
				for (String o : orders) {
					for (Field f : entityClass.getDeclaredFields()) {
						SortChineseAnnotation excelAnnotation = f
								.getAnnotation(SortChineseAnnotation.class);
						
						if (f.getName().equals(o)&&excelAnnotation != null && excelAnnotation.sort()) {
							if (returnOrderName.endsWith(",")) {
								returnOrderName = ("convert(" + o + ",'gbk')")
										+ "  " + sorts.get(i);
							} else {
								returnOrderName += ","
										+ ("convert(" + o + ",'gbk')") + "  "
										+ sorts.get(i);
							}
							break;
						} else {
							// 没有要中文排序的列
							if (returnOrderName.endsWith(",")) {
								returnOrderName = o + "  " + sorts.get(i);
							} else {
								returnOrderName += "," + o + "  "
										+ sorts.get(i);
							}
							break;
						}
					}
					i++;
				}
				//System.out.println(returnOrderName);
				return returnOrderName;
			}
			for (Field f : entityClass.getDeclaredFields()) {
				SortChineseAnnotation excelAnnotation = f
						.getAnnotation(SortChineseAnnotation.class);
				//System.out.println(f.getName()+"__"+orderName);
				if (f.getName().equals(orderName)&&excelAnnotation != null && excelAnnotation.sort()) {
					orderName = ("convert(" + orderName + ",'gbk')");
					break;
				}
			}
		}
		return orderName + "  " + sortName;
	}
	//
	// public static void main(String[] args) {
	//
	// System.out.println(returnFieldName(User.class,
	// "fullName,userName","asc,desc"));
	// }
}
