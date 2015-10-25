package net.lx.init.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 系统初始化接口
 * 
 */
public interface InitCacheDao {

	/**
	 * 查询所有信息
	 * 
	 * @param entityName
	 * @return
	 */
	public List<Object> findAll(String entityName);

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public Object save(Object entity);
	/**
	 * 执行更新语句
	 * @param sql
	 * @return返回更新条数
	 */
	public int update(String sql);
	
	public JdbcTemplate getJdbcTemplate();
}
