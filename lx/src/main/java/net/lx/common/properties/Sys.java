package net.lx.common.properties;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 公用事业与交互的底层OS/ JVM中
 *
 */
public class Sys {

	/**
	 * 记载类路径下的配置文件
	 * @param aResourcePath
	 * @return
	 * @throws IOException
	 */
	static public Properties loadPropertiesResource(String aResourcePath) throws IOException {
		try {
			
			ClassLoader classLoader = Sys.class.getClassLoader();
			Properties properties = new Properties();
			properties.load(classLoader.getResourceAsStream(aResourcePath));
			return properties;
		} catch (Throwable t) {
			throw new IOException("failed loading Properties resource from " + aResourcePath);
		}
	}
	static public Properties loadPropertiesFile(String aFilePath) throws IOException {
		try {

			Properties properties = new Properties();

			// Try loading it.
			properties.load(new FileInputStream(aFilePath));
			return properties;
		} catch (Throwable t) {
			throw new IOException("failed loading Properties file from " + aFilePath);
		}
	}
	
}
