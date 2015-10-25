package net.lx.dao.impl;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 操作主库mysql数据源
 * 
 */
public class MasterMysqlDao extends HibernateDaoSupport {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

}
