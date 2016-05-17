package it.polito.tdp.dizionario.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	static private final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
	static private Connection connection = null;

	public static Connection getConnection() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(jdbcURL);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot get connection " + jdbcURL, e);
		}
	}
}
