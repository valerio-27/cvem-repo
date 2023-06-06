package it.academy.gaming.milionario.core.implementation.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private String url;
	private String user;
	private String password;

	public ConnectionManager(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public DbConnection creaConnection() throws SQLException {
		return new DbConnection(DriverManager.getConnection(url, user, password));
	}

}
