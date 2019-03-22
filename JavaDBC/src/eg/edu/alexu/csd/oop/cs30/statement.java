package eg.edu.alexu.csd.oop.cs30;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.*;
import eg.edu.alexu.csd.oop.cs47.MyResultSet;
import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs30.FileBuilder;
import eg.edu.alexu.csd.oop.myDriver.MyConnection;

public class statement implements java.sql.Statement {
	private ArrayList<String> batch = new ArrayList();
	static Database database;
	static Connection connect;
	private ResultSet result;
	private int value;
	private int limit;
	private thread c;
	private static final Logger LOGGER = Logger.getLogger(MyConnection.class.getName());
	private statement() {
	}

	private static statement Statement;

	public static statement getInstance(Connection connection, Database db) {
		if (Statement == null) {
			Statement = new statement();
			database = db;
			connect = connection;
		}
		return Statement;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBatch(String arg0) throws SQLException {
		batch.add(arg0);
	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub
		batch.clear();
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		Statement = null;
		database = null;
		connect = null;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		if (arg0 != null) {
			String lower = arg0.toLowerCase();

			if (lower.contains("drop") || lower.contains("create")) {
				try {
					time();
					if (database.executeStructureQuery(arg0)) {
						value = 0;
						timeout();
						LOGGER.info("query excuted :" + lower);
						return true;
					}
				} catch (SQLException e) {
					LOGGER.warning("excution failed :" + lower);
					throw new SQLException();
				}
			} else if (lower.contains("update") || lower.contains("delete") || lower.contains("insert")) {
				try {
					time();
					value = database.executeUpdateQuery(arg0);
					timeout();
					LOGGER.info("query excuted :" + lower);
					return true;

				} catch (SQLException e) {
					LOGGER.warning("excution failed :" + lower);
					throw new SQLException();
				}
			} else if (lower.contains("select")) {
				try {
					time();
					Object[][] content = database.executeQuery(arg0);
					System.out.println("lenght" + content.length);
					ArrayList x = database.getColumnsNames(arg0);
					String[] h = new String[x.size()];
					for (int i = 0; i < x.size(); i++) {
						h[i] = (String) x.get(i);
					}
					String Table = FileBuilder.getInstance().table();
					result = new MyResultSet(content, h, this, Table);
					timeout();
					value = 0;
					LOGGER.info("query excuted :" + lower);
					if (content.length == 0)
						return false;

					return true;

				} catch (SQLException e) {
					LOGGER.warning("excution failed :" + lower);
					throw new SQLException();
				}
			} else {
				LOGGER.warning("excution failed :" + lower);
				throw new SQLException();
				
			}
		}
		return true;
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] executeBatch() throws SQLException {

		int batchStatues[] = new int[batch.size()];
		for (int j = 0; j < batch.size(); j++) {
			batchStatues[j] = executeUpdate(batch.get(j));

		}

		return batchStatues;
	}

	public void time() {
		c = new thread(limit);
		c.start();

	}

	public void timeout() throws SQLTimeoutException {

		if (!c.v) {
			c.stop();
		} else {
			throw new SQLTimeoutException();
		}
	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		execute(arg0);
		return result;
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		execute(arg0);
		return value;
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return connect;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return limit;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		limit = arg0;
	}

}
