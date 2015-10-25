package net.lx.common;

import java.util.Properties;

import net.lx.common.db.SingletonDatabaseUtil;
import net.lx.common.md5.Encryption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 数据连接字符串加密
 * 
 */
public class Configurer extends PropertyPlaceholderConfigurer {
	private final Log log=LogFactory.getLog(Configurer.class);
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		
		log.info("数据库密码解密...");
		String master_username = props.getProperty("datasource.master.username");
		String matser_password = props.getProperty("datasource.master.password");
		//String slave_username = props.getProperty("datasource.slave.username");
		//String slave_password = props.getProperty("datasource.slave.password");
		props.setProperty("datasource.master.username", Encryption.decryptionString(Hex.decode(master_username)));
		props.setProperty("datasource.master.password", Encryption.decryptionString(Hex.decode(matser_password)));
		//props.setProperty("datasource.slave.username", 	Encryption.decryptionString(Hex.decode(slave_username)));
		//props.setProperty("datasource.slave.password",  Encryption.decryptionString(Hex.decode(slave_password)));
		log.info("解密完成...");
		SingletonDatabaseUtil.init( Encryption.decryptionString(Hex.decode(master_username)), Encryption.decryptionString(Hex.decode(matser_password)), props.getProperty("datasource.master.database.name"),  props.getProperty("datasource.master.mysql.path"),props.getProperty("datasource.master.mysqldump.path"),  props.getProperty("datasource.master.database.backup.path"));
		super.processProperties(beanFactory, props);

	}
}
