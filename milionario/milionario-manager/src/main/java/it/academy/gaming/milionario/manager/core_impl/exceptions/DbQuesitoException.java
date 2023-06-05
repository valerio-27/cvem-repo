package it.academy.gaming.milionario.manager.core_impl.exceptions;

import java.sql.SQLException;

public class DbQuesitoException extends RuntimeException {

	public DbQuesitoException(SQLException e) {
		super("Errore nel DB " + e.getMessage());
	}

}
