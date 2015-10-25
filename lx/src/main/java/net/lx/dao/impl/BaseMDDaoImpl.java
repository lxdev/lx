package net.lx.dao.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import net.lx.common.Constants;
import net.lx.common.properties.Config;
import net.lx.common.reflection.ReflectionUtil;
import net.lx.common.servlet.SingletonSession;
import net.lx.common.string.StringUtil;
import net.lx.dao.BaseDao;
import net.lx.model.base.AuthenticationTicket;
import net.lx.model.base.SessionUser;
import net.lx.model.page.PageParame;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.Assert;

import com.devsun.cache.Cache;
import com.devsun.cache.tool.CacheTool;

/**
 * Dao实现类基类
 * 
 * @param <T>
 */
public class BaseMDDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private Cache cacheClient;
	private PreparedStatement pstmt;
	private Class<T> entityClass;
	@Autowired
	private MasterMysqlDao masterMysqlDao;

	private SlaveMysqlDao slaveMysqlDao;

	@SuppressWarnings("unchecked")
	public BaseMDDaoImpl() {

		Type getType = getClass().getGenericSuperclass();
		Type[] paramsTypes = ((ParameterizedType) getType)
				.getActualTypeArguments();
		entityClass = (Class) paramsTypes[0];
	}

	/**
	 * 删除
	 */
	public T delete(T entity) {
		try {
			Assert.notNull(entity);
			masterMysqlDao.getHibernateTemplate().delete(masterMysqlDao.getHibernateTemplate().merge(entity));
			if (Config.getBoolProperty("use.cache")) {
				// 删除缓存
				cacheClient.deleteObject(CacheTool.getCachedKey(entityClass,ReflectionUtil.getId(entity, "getId")));
				//删除sql缓存
				deleteSqlTmpById(ReflectionUtil.getId(entity, "getId").toString(),entityClass.getName());
			}
			
			return entity;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 根据主键删除
	 */
	public T deleteById(Serializable id) {
		try {
			T t=delete(findById(id));
			if (Config.getBoolProperty("use.cache")) {
				// 删除缓存
				cacheClient.deleteObject(CacheTool.getCachedKey(entityClass, id));
				//删除sql缓存
				deleteSqlTmpById(id+"",entityClass.getName());
			}

			return t;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}
	/**
	 * 清空数据表
	 */
	public void deleteAll() {		
		 masterMysqlDao.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException,HibernateException {
				Query query = session.createQuery("delete from "+entityClass.getName());
				return query.executeUpdate();
			}
		});
		
		
		//清理sql缓存
		deleteSqlTmpById(-999+"",entityClass.getName());
		//清理对象缓存
		cacheClient.deleteCacheByKey(entityClass.getName());
	}

	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws Exception {
		try {
			String name = entityClass.getName();
			if (Config.getBoolProperty("use.cache")) {
				List<T> ts = new ArrayList<T>();
				List list = null;
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							"select id from " + name);
				} else {
					list = masterMysqlDao.getHibernateTemplate().find(
							"select id from " + name);
				}
				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						T t = this.findById(id);
						if (id != 0 && t != null) {
							ts.add(t);
						}
					}
				}
				return ts;
			} else {
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					return slaveMysqlDao.getHibernateTemplate().find(
							"from " + name);
				} else {
					return masterMysqlDao.getHibernateTemplate().find(
							"from " + name);
				}

			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 按主键查询
	 */
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		if(id.equals(0)){
			return null;
		}
		
		int id_=Integer.valueOf(id.toString());
		try {
			String name = entityClass.getName();
			Object o = null;
			if (Config.getBoolProperty("use.cache")) {
				// 获取缓存
				Object obj = cacheClient.getObject(CacheTool.getCachedKey(
						entityClass, id_));
				if (obj != null) {
					if (Config.getBoolProperty("use.cache.bug")) {
						System.out.println(name + "获取缓存对象...");
					}
					return (T) obj;
				}
				if (Config.getBoolProperty("use.cache.bug")) {
					System.out.println(name + "正在查库...");
				}
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					o = slaveMysqlDao.getHibernateTemplate().get(entityClass,
							id_);
				} else {
					o = masterMysqlDao.getHibernateTemplate().get(entityClass,
							id_);
				}
				cacheClient.setObject(CacheTool.getCachedKey(entityClass, id_),
						o);
			} else {
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					o = slaveMysqlDao.getHibernateTemplate().get(entityClass,
							id_);
				} else {
					o = masterMysqlDao.getHibernateTemplate().get(entityClass,
							id_);
				}
			}

			return (T) o;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 添加
	 */
	public Object save(T entity) {
		try {
			Object object=masterMysqlDao.getHibernateTemplate().save(entity);
			if (Config.getBoolProperty("use.cache")) {
				//删除sql缓存
				deleteSqlTmpById(ReflectionUtil.getId(entity, "getId").toString(),entityClass.getName());
			}
			return object;
			
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}

	}

	/**
	 * 修改
	 */
	public T update(T entity) {
		try {
			Assert.notNull(entity);
			masterMysqlDao.getHibernateTemplate().update(masterMysqlDao.getHibernateTemplate().merge(entity));
			if (Config.getBoolProperty("use.cache")) {
				// 删除缓存
				cacheClient.deleteObject(CacheTool.getCachedKey(entityClass,
						ReflectionUtil.getId(entity, "getId")));
				//删除sql缓存
				deleteSqlTmpById(ReflectionUtil.getId(entity, "getId").toString(),entityClass.getName());
			}

			// String hql = " set ";
			// Method[] ms = entity.getClass().getDeclaredMethods();
			// List list = new ArrayList();
			// for (Method method : ms) {
			// if (method != null && method.getName().startsWith("get")
			// && !method.getName().equals("getId")) {
			// if (ReflectionUtil.getId(entity, method.getName()) != null) {
			// System.out.println(method.getName());
			// if (method.getGenericReturnType().toString()
			// .endsWith("Date")) {
			// hql += (StringUtil
			// .toCase(method.getName().substring(3))
			// + "="
			// + Constants.PLACEHOLDER + ",");
			// list.add(DateUtil.getDate(
			// (Date) (ReflectionUtil.getId(entity,
			// method.getName())),
			// "yyyy-MM-dd HH:mm:ss"));
			// } else {
			// hql += (StringUtil
			// .toCase(method.getName().substring(3))
			// + "="
			// + Constants.PLACEHOLDER + ",");
			//
			// list.add(ReflectionUtil.getId(entity, method.getName()));
			// }
			//
			// }
			// }
			//
			// }
			// hql = hql.substring(0, hql.lastIndexOf(","));
			// update(hql, ReflectionUtil.getId(entity, "getId").toString(),
			// list.toArray());
			// masterMysqlDao.getHibernateTemplate().saveOrUpdate(entity);
			return entity;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 查询分页主键集合
	 */
	public Long[] getIDs(PageParame p) {
		try {
			String name = entityClass.getSimpleName();

			String hql = "select id from " + name + " where 1=1 ";
			int now = p.getCurrentPageIndex();
			int size = p.getPageSize();
			now = size * (now - 1);

			String params = "";
			if (p.getHqlConditionExpression() != null) {
				params = StringUtil.replaceSql(p.getHqlConditionExpression(),
						p.getValues(), Constants.PLACEHOLDER);
			}

			List list = null;
			String sql=null;
			// 是否从库
			if (Config.getBoolProperty("user.slave")) {
				list = slaveMysqlDao.getHibernateTemplate().executeFind(
						new MyHibernateCallback2(hql
								+ params
								+ " order by "
								+ ReflectionUtil.returnFieldName(entityClass,
										p.getOrder(), p.getSort()), size, now,
								p.isPage()));
			} else {
				if(p.isPage()){
					sql=(hql+ params+ " order by "+ ReflectionUtil.returnFieldName(entityClass,p.getOrder(), p.getSort())+" limit "+now+","+size).trim();
				}else{
					sql=(hql+ params+ " order by "+ ReflectionUtil.returnFieldName(entityClass,p.getOrder(), p.getSort())).trim();
				}
				
				Long[] ids=getIDsBySql(sql,entityClass.getName());
				if(ids!=null){
					return ids;
				}else{
					list = masterMysqlDao.getHibernateTemplate().executeFind(
							new MyHibernateCallback2(hql
									+ params
									+ " order by "
									+ ReflectionUtil.returnFieldName(entityClass,
											p.getOrder(), p.getSort()), size, now,
									p.isPage()));
				}
			}

			Long[] ids = null;
			if (list != null && list.size() != 0) {
				ids = new Long[list.size()];
				for (int i = 0; i < list.size(); i++) {
					ids[i] = Long.parseLong(list.get(i).toString());
				}
			}
			
			//增加sql缓存
			addSqlTmp(sql, ids, entityClass.getName());
			return ids;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}
	
	public List<T> getList(PageParame p) {
		Long [] ids=getIDs(p);
		List<T> list=new ArrayList<T>();
		if(ids!=null&&ids.length!=0){
			for (Long id : ids) {
				list.add(findById(Integer.parseInt(id.toString())));
			}
		}
		return list;
	}

	/**
	 * 查询总数量
	 */
	public int getCounts(PageParame p) {
		try {
			String name = entityClass.getSimpleName();
			String hql = "select count(id) from " + name + " where 1=1 ";
			String params = "";
			if (p.getHqlConditionExpression() != null) {
				params = StringUtil.replaceSql(p.getHqlConditionExpression(),
						p.getValues(), Constants.PLACEHOLDER);
			}
			// System.out.println(hql + params);
			int recordCount = 0;
			// 是否从库
			if (Config.getBoolProperty("user.slave")) {
				recordCount = Integer.parseInt(slaveMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			} else {
				recordCount = Integer.parseInt(masterMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			}

			return recordCount;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}
	}

	/**
	 * 批量删除
	 */
	public int deleteByIds(String ids) {
		try {
			if (Config.getBoolProperty("use.cache")) {
				String[] idArray=ids.split(",");
				// 删除缓存
				cacheClient.deleteObject(CacheTool.getCachedKey(entityClass,idArray));
				if(idArray!=null){
					for (String id : idArray) {
						//删除sql缓存
						deleteSqlTmpById(id,entityClass.getName());
					}
				}
				
			}

			String hql = "delete from " + entityClass.getSimpleName()
					+ " where id in(" + ids + ")";

			return this.executeHql(hql);
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}
	}

	/**
	 * 通过属性名称和值获取对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<T> getByProperty(String property, Object value) {
		try {
			String name = entityClass.getSimpleName();
			if (Config.getBoolProperty("use.cache")) {
				List<T> ts = new ArrayList<T>();
				List list = null;

				String sql=null;
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							"select id from " + name + " where " + property
									+ "='" + value + "'");
				} else {
					sql="select id from " + name + " where " + property+ "='" + value + "'";
					Long[] ids=getIDsBySql(sql,entityClass.getName());
					if(ids!=null){
						for (Long id : ids) {
							T t = this.findById(id);
							if (id != 0 && t != null) {
								ts.add(t);
							}
						}
						return ts;
					}else{
						
						list = masterMysqlDao.getHibernateTemplate().find(
								"select id from " + name + " where " + property
										+ "='" + value + "'");
					}
					
					
				}

				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						T t = this.findById(id);
						if (id != 0 && t != null) {
							ts.add(t);
						}
					}
					//增加sql缓存
					addSqlTmp(sql,list, entityClass.getName());
				}
				return ts;
			} else {
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					return slaveMysqlDao.getHibernateTemplate().find(
							"from " + name + " where " + property + "='"
									+ value + "'");
				} else {
					return masterMysqlDao.getHibernateTemplate().find(
							"from " + name + " where " + property + "='"
									+ value + "'");
				}

			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 执行SQL语句，动态生成List并返回
	 * 
	 * @param sql
	 * @param array
	 *            结果集表--字段名集合
	 * @return 最终返回结果集合List<HashMap>
	 */
	public List<Object> convertToHashList(String sql, List<String> array) {
		Connection con = null;
		try {
			con = getConn();
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Object> lst = new ArrayList<Object>();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < array.size(); i++) {
					map.put(array.get(i), rs.getString(array.get(i)));
				}
				lst.add(map);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			pstmt.close();
			return lst;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		} catch (SQLException ce) {
			ce.printStackTrace();
			return null;
		} finally {
			closeConn(con, pstmt);
		}
	}

	/**
	 * 返回Result的查询通用类
	 * 
	 * @param sql
	 * @return
	 */
	public Result excuteQuery(String sql) {
		Connection con = null;
		try {
			con = getConn();
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Result result = ResultSupport.toResult(rs);
			if (rs != null) {
				rs.close();
				rs = null;
			}
			pstmt.close();
			return result;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		} catch (Exception ce) {
			ce.printStackTrace();
			return null;
		} finally {
			closeConn(con, pstmt);
		}
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            条件
	 * @return Result 返回
	 */
	public Result excuteQuery(String sql, List<Object> values) {
		Connection con = null;
		try {
			con = getConn();
			pstmt = con.prepareStatement(sql);
			pstmt = setValues(pstmt, values);
			ResultSet rs = pstmt.executeQuery();
			Result result = ResultSupport.toResult(rs);
			if (rs != null) {
				rs.close();
				rs = null;
			}
			pstmt.close();
			return result;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		} catch (Exception ce) {
			ce.printStackTrace();
			return null;
		} finally {
			closeConn(con, pstmt);
		}
	}

	/**
	 * 向SQL语句中插入数值
	 */
	private PreparedStatement setValues(PreparedStatement pstmt,
			List<Object> values) {
		for (int i = 0; i < values.size(); i++) {
			Object v = values.get(i);
			try {
				pstmt.setObject(i + 1, v);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pstmt;
	}

	/**
	 * 关闭数据库连接,带参数的关闭（关闭具体的哪个连接）
	 */
	@SuppressWarnings("unused")
	private void closeConn(Connection connection, PreparedStatement pstmt) {
		try {
			pstmt.close();
			connection.close();
		} catch (DataAccessResourceFailureException e) {
			reServer();
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private synchronized Connection getConn() {
		try {
			Connection con = masterMysqlDao.getDataSource().getConnection();
			if (null != con && true != con.isClosed()) {
				return con;
			} else {
				return null;
			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		} catch (SQLException ce) {
			ce.printStackTrace();
			return null;
		}
	}

	/**
	 * 执行hql批量更新语句
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int executeHql(final String hql) {
		return (Integer) masterMysqlDao.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 查询数量
	 * 
	 */
	@SuppressWarnings("unchecked")
	private class MyHibernateCallback1 implements HibernateCallback {
		private String hql1;

		public MyHibernateCallback1(String hql1) {
			this.hql1 = hql1;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			// System.out.println(this.hql1);
			Query query = session.createQuery(this.hql1);
			return query.uniqueResult();
		}
	}

	/**
	 * 查询集合
	 * 
	 */
	@SuppressWarnings("unchecked")
	private class MyHibernateCallback2 implements HibernateCallback {
		private String hql2;
		private int size;
		private int first;
		private boolean isPage;

		public MyHibernateCallback2(String hql2, int size, int first,
				boolean page) {
			this.hql2 = hql2;
			this.size = size;
			this.first = first;
			this.isPage = page;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			// System.out.println(this.hql2);
			Query query = session.createQuery(this.hql2);
			if (isPage) {
				query.setMaxResults(this.size);
				query.setFirstResult(this.first);
			}
			return query.list();
		}
	}

	/*
	 * 查询集合
	 */
	public List<T> getByProperty(String hqlConditionExpression, Object... obj) {
		try {
			String name = entityClass.getSimpleName();

			if (Config.getBoolProperty("use.cache")) {
				List<T> ts = new ArrayList<T>();
				String hql = "select id from " + name + " where 1=1 ";
				String params = "";
				if (hqlConditionExpression != null) {
					params = StringUtil.replaceSql(hqlConditionExpression, obj,
							Constants.PLACEHOLDER);
				}
				List list = null;
				String sql="";
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							hql + params);
				} else {
					sql=hql + params;
					Long[] ids=getIDsBySql(sql,entityClass.getName());
					if(ids!=null){
						for (Long id : ids) {
							T t = this.findById(Integer.valueOf(id.toString()));
							if (id != 0 && t != null) {
								ts.add(t);
							}
						}
						return ts;
					}else{
						list = masterMysqlDao.getHibernateTemplate().find(hql + params);
					}
				}

				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						T t = this.findById(id);
						if (id != 0 && t != null) {
							ts.add(t);
						}
					}
					//增加sql缓存
					addSqlTmp(sql,list, entityClass.getName());
				}
				return ts;
			} else {
				String hql = "from " + name + " where 1=1 ";
				String params = "";
				if (hqlConditionExpression != null) {
					params = StringUtil.replaceSql(hqlConditionExpression, obj,
							Constants.PLACEHOLDER);
				}
				// System.out.println(hql + params);
				List<T> list = null;
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							hql + params);
				} else {
					list = masterMysqlDao.getHibernateTemplate().find(
							hql + params);
				}

				return list;
			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 查询集合
	 * 
	 */
	public List<T> getByProperty(String hqlConditionExpression, List objs) {
		try {
			return getByProperty(hqlConditionExpression, objs.toArray());
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 已删除
	 * 
	 */
	public List<T> findAllDeleted() throws Exception {
		try {
			String name = entityClass.getName();
			if (Config.getBoolProperty("use.cache")) {
				List<T> ts = new ArrayList<T>();
				List list = null;

				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							"select id from " + name + " where deleteFlag="
									+ Constants.DELETE_TRUE);
				} else {
					list = masterMysqlDao.getHibernateTemplate().find(
							"select id from " + name + " where deleteFlag="
									+ Constants.DELETE_TRUE);
				}

				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						T t = this.findById(id);
						if (id != 0 && t != null) {
							ts.add(t);
						}
					}
				}
				return ts;
			} else {
				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					return slaveMysqlDao.getHibernateTemplate().find(
							"from " + name + " where deleteFlag="
									+ Constants.DELETE_TRUE);
				} else {
					return masterMysqlDao.getHibernateTemplate().find(
							"from " + name + " where deleteFlag="
									+ Constants.DELETE_TRUE);
				}

			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}

	}

	/*
	 * 未删除
	 * 
	 */
	public List<T> findAllNotDeleted() throws Exception {
		try {
			String name = entityClass.getName();
			if (Config.getBoolProperty("use.cache")) {
				List<T> ts = new ArrayList<T>();
				List list = null;

				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							"select id from " + name + " where deleteFlag="
									+ Constants.DELETE_FALSE);
				} else {
					list = masterMysqlDao.getHibernateTemplate().find(
							"select id from " + name + " where deleteFlag="
									+ Constants.DELETE_FALSE);
				}

				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						T t = this.findById(id);
						if (id != 0 && t != null) {
							ts.add(t);
						}
					}
				}
				return ts;
			} else {
				return masterMysqlDao.getHibernateTemplate().find(
						"from " + name + " where deleteFlag="
								+ Constants.DELETE_FALSE);
			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}

	}

	/*
	 * 查询数量
	 * 
	 */
	public int findCountByProperty(String hqlConditionExpression, Object... obj) {
		try {
			String name = entityClass.getSimpleName();
			String hql = "select count(id) from " + name + " where 1=1 ";
			String params = "";
			if (hqlConditionExpression != null) {
				params = StringUtil.replaceSql(hqlConditionExpression, obj,
						Constants.PLACEHOLDER);
			}

			int recordCount = 0;
			// 是否从库
			if (Config.getBoolProperty("user.slave")) {
				recordCount = Integer.parseInt(slaveMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			} else {
				recordCount = Integer.parseInt(masterMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			}

			return recordCount;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}
	}

	/*
	 * 查询数量
	 * 
	 */
	public int findCountByProperty(String hqlConditionExpression, List objs) {
		try {
			String name = entityClass.getSimpleName();
			String hql = "select count(id) from " + name + " where 1=1 ";
			String params = "";
			if (hqlConditionExpression != null) {
				params = StringUtil.replaceSql(hqlConditionExpression,
						objs.toArray(), Constants.PLACEHOLDER);
			}

			int recordCount = 0;
			// 是否从库
			if (Config.getBoolProperty("user.slave")) {
				recordCount = Integer.parseInt(slaveMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			} else {
				recordCount = Integer.parseInt(masterMysqlDao
						.getHibernateTemplate()
						.execute(new MyHibernateCallback1(hql + params))
						.toString());
			}
			return recordCount;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}
	}

	/*
	 * 根据属性删除实体
	 * 
	 */
	public int deleteByProperty(String hqlConditionExpression, Object... obj) {
		try {
			if (Config.getBoolProperty("use.cache")) {
				String hql = "select id from " + entityClass.getSimpleName()
						+ " where 1=1 ";
				String params = "";
				if (hqlConditionExpression != null) {
					params = StringUtil.replaceSql(hqlConditionExpression, obj,
							Constants.PLACEHOLDER);
				}
				List list = null;

				// 是否从库
				if (Config.getBoolProperty("user.slave")) {
					list = slaveMysqlDao.getHibernateTemplate().find(
							hql + params);
				} else {
					list = masterMysqlDao.getHibernateTemplate().find(
							hql + params);
				}

				int count = 0;
				if (list != null && list.size() != 0) {
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						id = Integer.parseInt(list.get(i).toString());
						// this.deleteById(id);
						this.deleteConfig(id);
						count++;
					}
				}
				return count;
			} else {
				String hql = "delete from " + entityClass.getSimpleName()
						+ " where 1=1 ";
				String params = "";
				if (hqlConditionExpression != null) {
					params = StringUtil.replaceSql(hqlConditionExpression, obj,
							Constants.PLACEHOLDER);
				}
				return this.executeHql(hql + params);
			}
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}

	}

	/*
	 * 根据属性删除实体
	 * 
	 */
	public int deleteByProperty(String hqlConditionExpression, List objs) {
		try {
			return this
					.deleteByProperty(hqlConditionExpression, objs.toArray());
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return 0;
		}
	}

	/*
	 * 执行批量更新
	 * 
	 */
	public void update(String hqlConditionExpression, String ids, Object... obj) {
		try {
			if (ids == null && hqlConditionExpression == null || obj == null) {
				return;
			}
			String params = "";
			if (hqlConditionExpression != null) {
				params = StringUtil.replaceSql(hqlConditionExpression, obj,
						Constants.PLACEHOLDER);
			}
			String hql = "update " + entityClass.getSimpleName() + params
					+ " where id in(" + ids + ")";
			// System.out.println(hql);
			if (Config.getBoolProperty("use.cache")) {
				Integer[] idsInteger = StringUtil.strToIntegers(
						Constants.SPLITTER, ids);
				for (Integer id : idsInteger) {
					// 删除缓存
					cacheClient.deleteObject(CacheTool.getCachedKey(
							entityClass, id));
					//删除sql缓存
					deleteSqlTmpById(id+"",entityClass.getName());
				}
			}
			this.executeHql(hql);
		} catch (DataAccessResourceFailureException e) {
			reServer();
		}
	}

	/*
	 * 执行批量更新
	 * 
	 */
	public void update(String hqlConditionExpression, String ids, List objs) {
		try {
			update(hqlConditionExpression, ids, objs.toArray());
		} catch (DataAccessResourceFailureException e) {
			reServer();
		}
	}

	/*
	 * 可配置的删除
	 * 
	 */
	public T deleteConfig(Serializable id) {
		// try {
		// Field field = entityClass.getClass().getField("deleteFlag");
		// } catch (SecurityException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (NoSuchFieldException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
			return this.deleteConfig(findById(id));
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 可配置的删除（方法重载）
	 * 
	 */
	public T deleteConfig(T entity) {
		try {
			// 是否存在deleteFlag
			// 存在
			if (Constants.IS_TURNED_DELETE) {
				this.delete(entity);
			} else {
				if (Config.getBoolProperty("use.cache")) {
					// 删除缓存
					cacheClient
							.deleteObject(CacheTool.getCachedKey(entityClass,
									ReflectionUtil.getId(entity, "getId")));
				}
				if (ReflectionUtil.isExistField(entityClass, "deleteFlag")) {
					String hql = "update " + entityClass.getSimpleName()
							+ " set " + " deleteFlag=" + Constants.DELETE_TRUE
							+ " where id="
							+ ReflectionUtil.getId(entity, "getId");
					this.executeHql(hql);
				} else {
					this.delete(entity);
				}
			}

			return entity;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 查询单个实体
	 * 
	 */

	public T getObjByProperty(String property, Object value) {
		try {
			List<T> ts = getByProperty(property, value);
			return ts == null || ts.size() == 0 ? null : ts.get(0);
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 获取单个对象
	 * 
	 */
	public T getObjByProperty(String hqlConditionExpression, Object... obj) {
		try {
			List<T> ts = getByProperty(hqlConditionExpression, obj);
			return ts == null || ts.size() == 0 ? null : ts.get(0);
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 执行Sql语句的模板
	 * 
	 */
	public JdbcTemplate getJdbcTemplate() {
		try {
			JdbcTemplate jt = new JdbcTemplate(masterMysqlDao.getDataSource());
			return jt;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/*
	 * 执行Sql语句的模板
	 * 
	 */
	public JdbcTemplatePlus getJdbcTemplatePlus() {
		try {
			JdbcTemplatePlus jdbcTemplate = new JdbcTemplatePlus(
					masterMysqlDao.getDataSource());
			// jdbcTemplate.setUseOneConnection(true);
			return jdbcTemplate;
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 通过条件查询ID集合
	 * 
	 */
	public Long[] getIDs(String hqlConditionExpression, Object... obj) {
		try {
			String name = entityClass.getSimpleName();

			// if (Config.getBoolProperty("use.cache")) {
			String hql = "select id from " + name + " where 1=1 ";
			String params = "";
			if (hqlConditionExpression != null) {
				params = StringUtil.replaceSql(hqlConditionExpression, obj,
						Constants.PLACEHOLDER);
			}
			List list = null;

			// 是否从库
			if (Config.getBoolProperty("user.slave")) {
				list = slaveMysqlDao.getHibernateTemplate().find(hql + params);
			} else {
				list = masterMysqlDao.getHibernateTemplate().find(hql + params);
			}

			Long[] ids = null;
			if (list != null && list.size() != 0) {
				ids = new Long[list.size()];
				for (int i = 0; i < list.size(); i++) {
					ids[i] = Long.parseLong(list.get(i).toString());
				}
			}
			return ids;
			// }
		} catch (DataAccessResourceFailureException e) {
			reServer();
			return null;
		}
	}

	/**
	 * 数据库连接断了以后重新启动服务（系统自动调用）
	 */
	private void reServer() {
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(Config.getProperty("runtime.exec.reload.project"));
			runtime.gc();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 通过sql语句获取 主键IDs
	 * @param sql
	 * @return
	 */
	private Long[] getIDsBySql(String sql,String classType){
		try{      
			Map<String,String> map=getJdbcTemplatePlus().queryForMap("select ids from tb_s_system_tmp where sql_str=? and class_type=?", new Object[]{sql, classType});
			String ids=map.get("ids");
			if(ids!=null){
				List<String> idList=new ArrayList<String>();
				String [] idArray=map.get("ids").split(",");
				for (String id : idArray) {
					if(id!=null&&!id.equals("")){
						idList.add(id);
					}
				}
				
				Long[] idArray_ = null;
				if (idList != null && idList.size() != 0) {
					idArray_ = new Long[idList.size()];
					for (int i = 0; i < idList.size(); i++) {
						idArray_[i] = Long.parseLong(idList.get(i));
					}
				}
				return idArray_;
			}
		}catch (EmptyResultDataAccessException e) {  
            return null;  
        }catch(Exception e){
        	return null;
        }
		return null;
	}
	/**
	 * 通过主键ID删除对应的sql语句缓存
	 * @param id
	 */
	private void deleteSqlTmpById(String id,String classType){
//		try{
//			try{      
////				List list=getJdbcTemplatePlus().queryForList("SELECT ids FROM tb_s_system_tmp where ids like ? and class_type=?",new Object[]{"%,"+id+",%",classType});
////				if(list==null||list.size()==0){
//					 System.out.println("清理所有"+classType+"类型的sql缓存");
//		             getJdbcTemplatePlus().update("DELETE FROM tb_s_system_tmp where class_type=?",new Object[]{classType});
//		             return;
////				}
//			}catch (EmptyResultDataAccessException e) {  
//	             System.out.println("清理所有"+classType+"类型的sql缓存");
//	             getJdbcTemplatePlus().update("DELETE FROM tb_s_system_tmp where class_type=?",new Object[]{classType});
//				return;
//	        } 
//			//getJdbcTemplatePlus().update("DELETE FROM tb_s_system_tmp where ids like ? and class_type=?",new Object[]{"%,"+id+",%",classType});
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	/**
	 * 增加缓存
	 * @param sql
	 * @param ids
	 */
	private void addSqlTmp(String sql,Long[] ids,String classType){
//		if(ids!=null&&ids.length!=0){
//			StringBuffer sb=null;
//			for (Long id : ids) {
//				if(sb==null){
//					sb=new StringBuffer();
//					sb.append(id);
//				}else{
//					sb.append(","+id);
//				}
//			}
//			if(sb!=null){
//				try{
//					getJdbcTemplatePlus().update("INSERT INTO tb_s_system_tmp (id,sql_str,ids,class_type) VALUES(null,?,?,?)",new Object[]{sql,","+sb.toString()+",",classType});
//				}catch(Exception e){
//					//System.out.println("sql重复");
//				}
//			}
//			
//		}
		
	}
	private void addSqlTmp(String sql,List list,String classType){
//		Long[] idArray=null;
//		if (list != null && list.size() != 0) {
//			idArray=new Long[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				idArray[i] = Long.parseLong(list.get(i).toString());
//			}
//		}
//		addSqlTmp(sql,idArray, classType);
	}
}
