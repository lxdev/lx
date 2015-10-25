package net.lx.dao.impl;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 操作从库mysql数据源
 * 
 */
public class SlaveMysqlDao extends HibernateDaoSupport {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
