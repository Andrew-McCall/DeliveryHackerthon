package am.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private final String dbUrl = "jdbc:h2:file:./database;INIT=RUNSCRIPT FROM './src/main/resources/schema.sql'";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl);
	}

	private static DBUtils instance;

	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

}