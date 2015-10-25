package net.lx.common.string;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作
 * 
 */
public class StringUtil {

	static public String forSql(String strFragment) {
		final StringBuffer result = new StringBuffer();

		// StringCharacterIterator 类对整个 String 进行迭代。
		// 构造初始索引为 0 的迭代器。
		final StringCharacterIterator iterator = new StringCharacterIterator(
				strFragment);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '"' || character == '\'') {
				result.append("");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		
		String [] array=result.toString().split(",");
		StringBuffer r=null;
		for (String string : array) {
			if(r==null){
				r=new StringBuffer("'"+string+"'");
			}else{
				r.append((",'"+string+"'"));
			}
		}
		if(r==null){
			r.append("0");
		}
		return r.toString();
	}

	/**
	 * 替换字符串
	 * 
	 * @param source
	 *            源字符串
	 * @param target
	 *            目标字符串
	 * @param placeholder
	 *            占位符
	 * @return
	 */
	static public String replaceSql(String source, Object[] values,
			String placeholder) {
		try {
			String[] sources = source.trim().split(placeholder);
			//String returnStr = " ";
			StringBuffer returnStr=new StringBuffer(" ");
			Object[] newObjectsValues = null;
			newObjectsValues = new Object[sources.length];
			for (int i = 0; i < values.length; i++) {
				newObjectsValues[i] = values[i];
			}
			for (int i = 0; i < sources.length; i++) {
				StringBuffer value=new StringBuffer(" ");
				if (newObjectsValues[i] != null) {
					if (newObjectsValues[i].getClass().getSimpleName()
							.equals("String")
							&& !newObjectsValues[i].toString().startsWith("$")) {
						value.append(forSql(newObjectsValues[i] + ""));
					} else if (newObjectsValues[i].getClass().getSimpleName().equals("String")&& newObjectsValues[i].toString().startsWith("$")) {
						value.append(forSql(newObjectsValues[i].toString().substring(1) + ""));
					} else {

						value.append(newObjectsValues[i] + "");
					}
					returnStr.append(" " + sources[i] + " " + value + " ");
				} else {
					returnStr.append(" " + sources[i]);
				}
			}

			return returnStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 处理树逻辑节点用
	 * 
	 * @param logicNode
	 *            父节点logciNode
	 * @param id
	 * @return
	 */
	public static String ChangeLogicNode(String logicNode, int id) {
		logicNode = logicNode.replace("_" + id, "");
		if ("_".equals(logicNode.substring(logicNode.length())))
			logicNode += "_";
		return logicNode + id + "_";
	}

	/**
	 * 替换字符串
	 * 
	 * @param strSource
	 *            字符串
	 * @param strFrom
	 *            要替换的字符串（已存在）
	 * @param strTo
	 *            (不存在)
	 * @return
	 */
	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/**
	 * 把数组用下划线"_"连接成字符串
	 * 
	 * @param strSource
	 *            字符串
	 * @param strFrom
	 * 
	 * 
	 * @return
	 */
	public static String strObjects(Object[] obj) {
		String str = "";
		for (int i = 0; i < obj.length; i++) {
			if (i == obj.length - 1) {
				str += "_" + obj[i] + "_";
				break;

			} else {
				str += "_" + obj[i];
			}
		}
		return str;
	}

	/**
	 * 把"_"字符串转换数组
	 * 
	 * @param strSource
	 *            字符串
	 * @param strFrom
	 * 
	 * 
	 * @return
	 */
	public static Object[] strToObject(String str) {

		Object[] obj = str.substring(1, str.length()).split("_");
		return obj;
	}
	
	/**
	 * 把"_"字符串转换数组
	 * 
	 * @param strSource
	 *            字符串
	 * @param strFrom
	 * 
	 * 
	 * @return
	 */
	public static Object[] strToObject(String str,String str2) {

		Object[] obj = str.substring(1, str.length()).split(str2);
		return obj;
	}

	/**
	 * 把"_"字符串转换成集合
	 * 
	 * @param strSource字符串
	 * @return
	 */
	public static List<String> strToList(String str) {
		Object[] obj = strToObject(str);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < obj.length; i++) {
			list.add(obj[i].toString());
		}
		return list;
	}
	
	/**
	 * 把"_"字符串转换成集合
	 * 
	 * @param strSource字符串
	 * @return
	 */
	public static List<String> strToList(String str,String str2) {
		Object[] obj = strToObject(str,str2);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < obj.length; i++) {
			list.add(obj[i].toString());
		}
		return list;
	}

	/**
	 * 把集合用下划线"_"连接成字符串
	 * 
	 * @param strSource
	 *            字符串
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String strlists(List list) {

		String str = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				str += "_" + list.get(i) + "_";
				break;
			} else {
				str += "_" + list.get(i);
			}

		}

		return str;
	}

	/**
	 * 把集合用下划线"_"连接成字符串
	 * 
	 * @param strSource
	 *            字符串
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List strTolists(String str) {

		List list = new ArrayList();
		//Object[] obj = str.substring(1, str.length()).split("_");
		String  strArray[]=str.split("_");
		for (int i = 0; i < strArray.length; i++) {
			if(strArray[i]!=null&&!strArray[i].equals("")){
				list.add(strArray[i]);
			}
			
		}
		return list;
	}

	/**
	 * 把string字符串转换为int数组
	 * 
	 * @param placeholder
	 *            分隔符
	 * @param ids
	 *            id集合
	 * @return
	 */
	public static Integer[] strToIntegers(String placeholder, String ids) {
		if (ids != null && !ids.equals("")) {
			String[] strings = ids.split(placeholder);
			if (strings.length == 0) {
				return null;
			}
			Integer[] integers = new Integer[strings.length];
			for (int i = 0; i < strings.length; i++) {
				integers[i] = Integer.parseInt(strings[i]);
			}
			return integers;
		} else {
			return null;
		}
	}

	/**
	 * 第一个字母小写转换
	 * 
	 * @param str
	 * @return
	 */

	public static String toCase(final String str) {
		String first=str.substring(0,1);
		String end=str.substring(1);
		return first.toLowerCase()+end;
	}
	/**
	 * 去掉字符串两边的空格
	 * @param str
	 * @return
	 */
	public static String toTrim(String str){
		if(str==null){
			return null;
		}
		if(str.equals("")){
			return "";
		}
		return str.trim();
	}

	public static void main(String[] args) {
		//System.out.println(toCase("QQQ"));
		String sql = "select * from tb_e_user where 1=1";
		System.out.println(sql
				+ replaceSql("and cname in (#) and age=#", new Object[] { "$1,2",111 }, "#"));
	}

}
