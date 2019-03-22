package eg.edu.alexu.csd.oop.myDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs26.DatabaseImp;
import eg.edu.alexu.csd.oop.db.cs30.FileBuilder;
import eg.edu.alexu.csd.oop.db.cs30.FileBuilding;

public class MyDriver implements Driver {

	private Database dataBase;

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		if (url.toLowerCase().trim().contains("jdbc:xmldb://localhost")) {
			return true;
		}
		return false;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		Connection connect = null;
		if (!url.toLowerCase().trim().contains("jdbc:xmldb://localhost")) {
			return null;
		}

		if (info.containsKey("path")) {
			String dir = info.get("path").toString();
			dataBase = new DatabaseImp();
			dataBase.createDatabase(dir, false);
			connect = new MyConnection(dataBase, this);
		}
		if (info.containsKey("username") && info.containsKey("password")) {
			File file = new File("register.txt");
			try {
				BufferedReader read = new BufferedReader(new FileReader(file));
				if (!read.readLine().equals(info.getProperty("username").toString())
						|| !read.readLine().equals(info.getProperty("password").toString())) {
					throw new SQLException();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return connect;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {

		return null;
	}

	/************************************************************************************/
	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}
