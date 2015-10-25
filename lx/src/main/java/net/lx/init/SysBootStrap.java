package net.lx.init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.lx.common.Constants;
import net.lx.common.properties.Config;
import net.lx.common.servlet.SingletonServletContext;
import net.lx.init.dao.InitCacheDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsun.cache.Cache;
import com.devsun.cache.tool.CacheTool;

@Transactional
@Component
public class SysBootStrap {
	@Autowired
	private Cache cacheClient;
	@Autowired
	private InitCacheDao initCacheDao;
	
	private final Log log = LogFactory.getLog(SysBootStrap.class);
	
	public void init(){
		//系统初始化
		log.info("系统初始化完毕.....");
		// 设置全局的classpath路径
		Constants.PATH = SingletonServletContext.newInstance().getRealPath(File.separator)+"WEB-INF" + File.separator + "classes";
		log.info("calsspath:" + Constants.PATH);
		// 初始化加载配置文件
		log.info("loading app.properties...");
		Config.load();
		log.info("init cache and data...");
		// 生成全局的JS
		createGlobalJs(SingletonServletContext.newInstance().getRealPath("/plugin/constants/synchronous_constants.js"));
		log.info("create js global variables...");

		// 加载服务器地址
		log.info("load server address...");
		Constants.WEB_PATH = Config.getProperty("web.host.web");
		log.info("web host:" + Constants.WEB_PATH);
		Constants.WEB_ATTACHMENT = Config.getProperty("web.host.attachment");
		log.info("web attachment:" + Constants.WEB_ATTACHMENT);
		Constants.WEB_IMAGES = Config.getProperty("web.host.images");
		log.info("web images:" + Constants.WEB_IMAGES);
		Constants.WEB_PLUGINS = Config.getProperty("web.host.plugin");
		log.info("web plugins:" + Constants.WEB_PLUGINS);
		
		//删除sql缓存数据
//		log.info("start delete sql cache...");
//		int count=initCacheDao.update("delete from tb_s_system_tmp");
//		log.info("end delete sql cache...");
//		log.info("删除sql缓存"+count+"条");
		
		log.info("系统初始化完毕.....");
	}
	/**
	 * 生成动态的全局变量
	 * 
	 * @param path
	 */
	public void createGlobalJs(String path) {
		Field[] fs = Constants.class.getFields();
		String jsField = "";

		for (Field field : fs) {
			try {
				if (!isFound(field.getName())) {
					if (field.getType().getSimpleName().equals("String")) {
						jsField += "var" + " " + field.getName() + "=\""
								+ field.get(field.getName().toString())
								+ "\";\n";
					} else {
						jsField += "var" + " " + field.getName() + "="
								+ field.get(field.getName().toString()) + ";\n";
					}
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(path, false);

			PrintWriter pw = new PrintWriter(fw);
			jsField += "var WEB_PATH='" + Config.getProperty("web.host.web")
					+ "';\n";
			jsField += "var WEB_ATTACHMENT='"
					+ Config.getProperty("web.host.attachment") + "';\n";
			jsField += "var WEB_IMAGES='"
					+ Config.getProperty("web.host.images") + "';\n";
			jsField += "var WEB_PLUGINS='"
					+ Config.getProperty("web.host.plugin") + "';\n";

			pw.println(jsField);
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否存在该变量
	 * 
	 * @param name
	 * @return
	 */
	private boolean isFound(String name) {
		String fieldsStr = Config.getProperty("disable.loading.fields");
		if (fieldsStr != null && !fieldsStr.equals("")) {

			for (String f : fieldsStr.split(";")) {
				if (f.equals(name)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 加载缓存
	 */
	private void cache() {
		// 是否启用缓存
		if (Config.getBoolProperty("use.cache")) {
			System.out.println("memcached缓存已启动...");
			// 清空缓存
			cacheClient.clear();
			String entityNames = Config
					.getProperty("bootstrap.load.entity.name");
			if (entityNames != null && !entityNames.equals("")) {
				for (String name : entityNames.split(";")) {
					for (Object obj : initCacheDao.findAll(name)) {
						if (obj != null) {
							if (obj instanceof Serializable) {
								Method getMethod;
								try {
									getMethod = obj.getClass().getMethod(
											"getId");

									cacheClient.setObject(CacheTool
											.getCachedKey(obj.getClass(),
													(Object) getMethod
															.invoke(obj)), obj);
								} catch (SecurityException e) {
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}

							}
						}
					}
				}
			}
		}
	}
}
