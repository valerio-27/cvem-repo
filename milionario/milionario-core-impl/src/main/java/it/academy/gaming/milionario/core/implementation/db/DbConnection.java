package it.academy.gaming.milionario.core.implementation.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	private Connection connection;

	public DbConnection(Connection connetion) {
		super();
		this.connection = connetion;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException ignored) {
		}
	}

	public PreparedStatement prepareStatement(String sqlScript) throws SQLException {
		return connection.prepareStatement(sqlScript);
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}
}
