package net.lx.dao;

import java.io.Serializable;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import net.lx.dao.impl.JdbcTemplatePlus;
import net.lx.model.page.PageParame;

/**
 * Dao接口基类
 * 
 * @param <T>
 */
public interface BaseDao<T> {


	enum SqlAndList{
		SQL,LIST
	}
	 
	/**
	 * 添加
	 * 
	 * @param entity
	 */
	public Object save(T entity);

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	public T update(T entity);

	/**
	 * 更新
	 * 
	 * @param hqlConditionExpression
	 * @param obj
	 */
	public void update(String hqlConditionExpression, String ids, Object... obj);

	/**
	 * 更新
	 * 
	 * @param hqlConditionExpression
	 * @param obj
	 */
	public void update(String hqlConditionExpression, String ids, List objs);

	/**
	 * 可配置的删除
	 * 
	 * @param id
	 * @return
	 */
	public T deleteConfig(Serializable id);
	
	/**
	 * 通过属性查询实体
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public T getObjByProperty(String property, Object value);

	/**
	 * 可配置的删除
	 * 
	 * @param entity
	 * @return
	 */
	public T deleteConfig(T entity);

	 /**
	 * 删除
	 *
	 * @param entity
	 */
	 public T delete(T entity);
	 /**
	  * 清空
	  * @return
	  */
	 public void deleteAll();

	// /**
	// * 逻辑删除
	// *
	// * @param id
	// * @return
	// */
	// public int deleteByFlag(int id);

	/**
	 * 按主键删除
	 * 
	 * @param id
	 * @return
	 */
	public T deleteById(Serializable id);
	
	/**
	 * 获取单个对象
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public T getObjByProperty(String hqlConditionExpression, Object... obj);

	/**
	 * 按主键查找对象
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception;

	/**
	 * 已删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAllDeleted() throws Exception;

	/**
	 * 未删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAllNotDeleted() throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            主键ID集合
	 * @return
	 */
	public int deleteByIds(String ids);

	/**
	 * 通过属性查询集合
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> getByProperty(String property, Object value);
	
	/**
	 * 
	 * @功能：获取所有IDs
	 *
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public Long[] getIDs(String hqlConditionExpression, Object... obj);

	/**
	 * 查询分页主键集合
	 * 
	 * @param p
	 * @return
	 */
	public Long[] getIDs(PageParame p);
	
	/**
	 * 查询分页主键集合
	 * 
	 * @param p
	 * @return
	 */
	public List<T> getList(PageParame p);

	/**
	 * 查询集合
	 * 
	 * @param hqlConditionExpression
	 *            where 子句后面表达式，表达式 如: name=? and age>?
	 * @param obj
	 *            表达式参数：如{"张三",18}
	 * @return
	 */
	public List<T> getByProperty(String hqlConditionExpression, Object... obj);

	/**
	 * 查询集合
	 * 
	 * @param hqlConditionExpression
	 *            where 子句后面表达式，表达式 如: name=? and age>?
	 * @param obj
	 * 
	 * @return
	 */
	public List<T> getByProperty(String hqlConditionExpression, List objs);

	/**
	 * 查询数量
	 * 
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public int findCountByProperty(String hqlConditionExpression, Object... obj);

	/**
	 * 查询数量
	 * 
	 * @param hqlConditionExpression
	 * @param objs
	 * @return
	 */
	public int findCountByProperty(String hqlConditionExpression, List objs);

	/**
	 * 查询总数量
	 * 
	 * @param p
	 * @return
	 */
	public int getCounts(PageParame p);

	/**
	 * 返回Result的查询通用类
	 * 
	 * @param sql
	 * @return
	 */
	public Result excuteQuery(String sql);

	/**
	 * 通用查询方法
	 * 
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            条件
	 * @return Result 返回
	 */
	public Result excuteQuery(String sql, List<Object> values);

	/**
	 * 执行SQL语句，动态生成List并返回
	 * 
	 * @param sql
	 * @param array
	 *            结果集表--字段名集合
	 * @return 最终返回结果集合List<HashMap>
	 */
	public List<Object> convertToHashList(String sql, List<String> array);

	/**
	 * 通过条件删除
	 * 
	 * @param hqlConditionExpression
	 *            where 子句后面表达式，表达式 如: name=? and age>?
	 * @param obj
	 *            表达式参数：如{"张三",18}
	 * @return
	 */
	public int deleteByProperty(String hqlConditionExpression, Object... obj);

	/**
	 * 通过条件删除
	 * 
	 * @param hqlConditionExpression
	 *            where 子句后面表达式，表达式 如: name=? and age>?
	 * @param obj
	 * 
	 * @return
	 */
	public int deleteByProperty(String hqlConditionExpression, List objs);
	
	
	/**
	 * 
	 * @功能： 获取jdbcTemplate扩展插件对象
	 */
	public JdbcTemplatePlus getJdbcTemplatePlus();

}
