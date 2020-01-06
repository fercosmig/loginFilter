/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file util.ConnectionFactory.java
 */
public class ConnectionFactory {

	private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class.getName());

	/**
	 * Methods
	 */

	public static Connection getConnection() throws Exception {
		LOGGER.info("method getConnection");

		String url = "jdbc:mysql://localhost:3306/dbtestes";
		String username = "dbatestes";
		String password = "12345";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}

	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		close(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		LOGGER.info("method close");
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
