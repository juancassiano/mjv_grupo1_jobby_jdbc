package com.grupoo1.grupo1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection criarConexao() {

		final String URL = "jdbc:mysql://localhost:3306/mjv_grupo1?useTimezone=true&serverTimezone=UTC";
		final String USER = "root";
		final String PASSWORD = "111093";

		Connection connection;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			return connection;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}