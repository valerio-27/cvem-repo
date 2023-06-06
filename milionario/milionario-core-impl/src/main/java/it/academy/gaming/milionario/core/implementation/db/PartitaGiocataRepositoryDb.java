package it.academy.gaming.milionario.core.implementation.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import it.academy.gaming.milionario.core.domain.Giocatore;
import it.academy.gaming.milionario.core.domain.PartitaGiocata;
import it.academy.gaming.milionario.core.domain.PartitaGiocataRepository;
import it.academy.gaming.milionario.core.domain.Valore;
import it.academy.gaming.milionario.core.domain.exceptions.NomeNonValidoException;

public class PartitaGiocataRepositoryDb implements PartitaGiocataRepository {

	private ConnectionManager connectionManager;

	public PartitaGiocataRepositoryDb(String url, String user, String password) {
		this.connectionManager = new ConnectionManager(url, user, password);
	}

	@Override
	public void save(PartitaGiocata partitaGiocata) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "INSERT  INTO PartitaGiocata (nome,euro) VALUES (?,?)";

			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, partitaGiocata.getGiocatore().getNome());
			preparedStatement.setInt(2, partitaGiocata.getValore().getEuro());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbQuesitoException(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ignored) {
				}
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	@Override
	public Collection<PartitaGiocata> getAll() {
		Collection<PartitaGiocata> partite = new ArrayList<>();

		Statement statement = null;
		ResultSet resultSet = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT * FROM PartitaGiocata";

			statement = dbConnection.createStatement();

			resultSet = statement.executeQuery(sqlScript);

			while (resultSet.next()) {
				String nome = resultSet.getString(2);
				int euro = resultSet.getInt(3);
				try {
					partite.add(new PartitaGiocata(new Giocatore(nome), Valore.parse(euro)));
				} catch (NomeNonValidoException ignored) {
				}
			}

		} catch (SQLException e) {
			throw new DbQuesitoException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignored) {
				}
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return partite;
	}

}
