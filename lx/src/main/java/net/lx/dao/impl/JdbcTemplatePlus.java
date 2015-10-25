package net.lx.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

public class JdbcTemplatePlus extends JdbcTemplate {

	private Connection connection = null;
	private boolean useOneConnection = false;

	public JdbcTemplatePlus() {
		super();
	}

	public JdbcTemplatePlus(DataSource dataSource) {
		super(dataSource);
	}

	public JdbcTemplatePlus(DataSource dataSource, boolean lazyInit) {
		super(dataSource, lazyInit);
	}

	private Connection tryGetConnection() {
		if (useOneConnection) {
			if (connection == null) {
				connection = DataSourceUtils.getConnection(getDataSource());
			}
			return connection;
		}
		return DataSourceUtils.getConnection(getDataSource());
	}

	private void tryReleaseConnection(Connection con) {
		if (!useOneConnection) {
			DataSourceUtils.releaseConnection(con, getDataSource());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean isUseOneConnection() {
		return useOneConnection;
	}

	public void setUseOneConnection(boolean useOneConnection) {
		this.useOneConnection = useOneConnection;
	}

	public void releaseConnection() {
		DataSourceUtils.releaseConnection(connection, getDataSource());
	}

	public static String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		} else {
			return null;
		}
	}

	public Object execute(ConnectionCallback action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");

		Connection con = tryGetConnection();
		try {
			Connection conToUse = con;
			if (getNativeJdbcExtractor() != null) {
				conToUse = getNativeJdbcExtractor().getNativeConnection(con);
			} else {
				conToUse = createConnectionProxy(con);
			}
			return action.doInConnection(conToUse);
		} catch (SQLException ex) {
			tryReleaseConnection(con);
			con = null;
			throw getExceptionTranslator().translate("ConnectionCallback",
					getSql(action), ex);
		} finally {
			tryReleaseConnection(con);
		}
	}

	public Object execute(StatementCallback action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");

		Connection con = tryGetConnection();
		Statement stmt = null;
		try {
			Connection conToUse = con;
			if (getNativeJdbcExtractor() != null
					&& getNativeJdbcExtractor()
							.isNativeConnectionNecessaryForNativeStatements()) {
				conToUse = getNativeJdbcExtractor().getNativeConnection(con);
			}
			stmt = conToUse.createStatement();
			applyStatementSettings(stmt);
			Statement stmtToUse = stmt;
			if (getNativeJdbcExtractor() != null) {
				stmtToUse = getNativeJdbcExtractor().getNativeStatement(stmt);
			}
			Object result = action.doInStatement(stmtToUse);
			handleWarnings(stmt.getWarnings());
			return result;
		} catch (SQLException ex) {
			JdbcUtils.closeStatement(stmt);
			stmt = null;
			tryReleaseConnection(con);
			con = null;
			throw getExceptionTranslator().translate("StatementCallback",
					getSql(action), ex);
		} finally {
			JdbcUtils.closeStatement(stmt);
			tryReleaseConnection(con);
		}
	}

	public Object execute(PreparedStatementCreator psc,
			PreparedStatementCallback action) throws DataAccessException {

		Assert.notNull(psc, "PreparedStatementCreator must not be null");
		Assert.notNull(action, "Callback object must not be null");
		if (logger.isDebugEnabled()) {
			String sql = getSql(psc);
			logger.debug("Executing prepared SQL statement"
					+ (sql != null ? " [" + sql + "]" : ""));
		}

		Connection con = tryGetConnection();
		PreparedStatement ps = null;
		try {
			Connection conToUse = con;
			if (getNativeJdbcExtractor() != null
					&& getNativeJdbcExtractor()
							.isNativeConnectionNecessaryForNativePreparedStatements()) {
				conToUse = getNativeJdbcExtractor().getNativeConnection(con);
			}
			ps = psc.createPreparedStatement(conToUse);
			applyStatementSettings(ps);
			PreparedStatement psToUse = ps;
			if (getNativeJdbcExtractor() != null) {
				psToUse = getNativeJdbcExtractor().getNativePreparedStatement(
						ps);
			}
			Object result = action.doInPreparedStatement(psToUse);
			handleWarnings(ps.getWarnings());
			return result;
		} catch (SQLException ex) {
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			String sql = getSql(psc);
			psc = null;
			JdbcUtils.closeStatement(ps);
			ps = null;
			tryReleaseConnection(con);
			con = null;
			throw getExceptionTranslator().translate(
					"PreparedStatementCallback", sql, ex);
		} finally {
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			JdbcUtils.closeStatement(ps);
			tryReleaseConnection(con);
		}
	}

	public Object execute(CallableStatementCreator csc,
			CallableStatementCallback action) throws DataAccessException {

		Assert.notNull(csc, "CallableStatementCreator must not be null");
		Assert.notNull(action, "Callback object must not be null");
		if (logger.isDebugEnabled()) {
			String sql = getSql(csc);
			logger.debug("Calling stored procedure"
					+ (sql != null ? " [" + sql + "]" : ""));
		}

		Connection con = tryGetConnection();
		CallableStatement cs = null;
		try {
			Connection conToUse = con;
			if (getNativeJdbcExtractor() != null) {
				conToUse = getNativeJdbcExtractor().getNativeConnection(con);
			}
			cs = csc.createCallableStatement(conToUse);
			applyStatementSettings(cs);
			CallableStatement csToUse = cs;
			if (getNativeJdbcExtractor() != null) {
				csToUse = getNativeJdbcExtractor().getNativeCallableStatement(
						cs);
			}
			Object result = action.doInCallableStatement(csToUse);
			handleWarnings(cs.getWarnings());
			return result;
		} catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool
			// deadlock
			// in the case when the exception translator hasn't been initialized
			// yet.
			if (csc instanceof ParameterDisposer) {
				((ParameterDisposer) csc).cleanupParameters();
			}
			String sql = getSql(csc);
			csc = null;
			JdbcUtils.closeStatement(cs);
			cs = null;
			tryReleaseConnection(con);
			con = null;
			throw getExceptionTranslator().translate(
					"CallableStatementCallback", sql, ex);
		} finally {
			if (csc instanceof ParameterDisposer) {
				((ParameterDisposer) csc).cleanupParameters();
			}
			JdbcUtils.closeStatement(cs);
			tryReleaseConnection(con);
		}
	}

	protected void finalize() throws Throwable {
		super.finalize();
		releaseConnection();
	}

}