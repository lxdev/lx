package net.lx.common.il8n;

import java.util.Locale;
import java.util.ResourceBundle;

import net.lx.common.Constants;
import net.lx.common.string.StringUtil;

/**
 * 资源文件操作
 * 
 */
public class ResourcesTool {

	/**
	 * 获取资源文件值
	 * 
	 * @param il8nName
	 * @param key
	 * @param local
	 * @return
	 */
	private static String getTextTool(String il8nName, String key, Locale local) {
		ResourceBundle rb = null;
		try {
			rb = ResourceBundle
					.getBundle(
							Constants.IL8N_RESOURCES_PREFIX
									+ "_"
									+ ((il8nName == null || il8nName.equals("")) ? Constants.IL8N_RESOURCES_DEFAULT
											: il8nName),
							local == null ? Locale.CHINA : local);
			// return actionSupport.getText(str.toLowerCase());
			return rb.getString(key.toLowerCase());
		} catch (Exception e) {
			try {
				rb = ResourceBundle.getBundle(Constants.IL8N_RESOURCES_PREFIX
						+ "_" + Constants.IL8N_RESOURCES_DEFAULT,
						local == null ? Locale.CHINA : local);
				return rb.getString(key.toLowerCase());
			} catch (Exception e2) {
				return key.toLowerCase();
			}

		}

	}

	/**
	 * 获取资源文件值
	 * 
	 * @param il8nName
	 * @param key
	 * @param local
	 * @return
	 */
	public static String getText(String il8nName, String key, Locale local) {
		return getTextTool(il8nName, key, local);
	}

	/**
	 * 获取资源文件值
	 * 
	 * @param il8nName
	 * @param key
	 * @return
	 */
	public static String getText(String il8nName, String key) {
		return getTextTool(il8nName, key, null);
	}

	/**
	 * 获取资源文件值,带参数
	 * 
	 * @param il8nName
	 * @param key
	 * @param local
	 * @param values
	 * @return
	 */
	public static String getText(String il8nName, String key, Locale local,
			Object[] values) {

		String str = getTextTool(il8nName, key, local);
		if (str != null) {
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					str = StringUtil.replace(str, "{" + i + "}", values[i]
							.toString());
				}
			}
		}
		return str;
	}

	/**
	 * 获取资源文件值,带参数
	 * 
	 * @param il8nName
	 * @param key
	 * @param values
	 * @return
	 */
	public static String getText(String il8nName, String key, Object[] values) {
		String str = getTextTool(il8nName, key, null);
		if (str != null) {
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					str = StringUtil.replace(str, "{" + i + "}", values[i]
							.toString());
				}
			}
		}
		return str;
	}

}
