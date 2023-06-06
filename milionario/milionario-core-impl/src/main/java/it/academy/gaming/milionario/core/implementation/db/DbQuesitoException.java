package it.academy.gaming.milionario.core.implementation.db;

import java.sql.SQLException;

public class DbQuesitoException extends RuntimeException {

	public DbQuesitoException(SQLException e) {
		super("Errore nel DB " + e.getMessage());
	}

}
