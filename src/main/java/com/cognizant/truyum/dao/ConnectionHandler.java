package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
	private static Connection conn = null;

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		if (conn != null)
			return conn;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lch_marketplace", "root", "");
		return conn;
	}
}
