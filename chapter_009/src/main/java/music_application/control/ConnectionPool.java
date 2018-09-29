package music_application.control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	private ConnectionPool(){
	}

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance(){
		if (instance==null)
			instance = new ConnectionPool();
		return instance;
	}

	public Connection getConnection(){
		Connection c = null;
		try {
			String url = "jdbc:postgresql://localhost:5432/userstore";
			String user = "postgres";
			String password = "1234";
			c = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}


}
