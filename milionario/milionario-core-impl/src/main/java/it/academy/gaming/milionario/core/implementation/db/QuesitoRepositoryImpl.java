package it.academy.gaming.milionario.core.implementation.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.codici.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.codici.CodiceRisposta;
import it.academy.gaming.milionario.core.domain.codici.CodiceSuggerimento;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;

public class QuesitoRepositoryImpl implements QuesitoRepository {
	Random random = new Random();
	private ConnectionManager connectionManager;

	public QuesitoRepositoryImpl(String url, String user, String password) {
		this.connectionManager = new ConnectionManager(url, user, password);
	}

	// difficolta
	@Override
	public Quesito findRandomByDifficolta(Difficolta difficolta) {

		List<Quesito> quesiti = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		DbConnection dbConnection = null;
		ResultSet quesitiResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT * FROM Quesito WHERE livello_difficolta = ?";

			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setInt(1, difficolta.getDifficolta());

			quesitiResultSet = preparedStatement.executeQuery();

			while (quesitiResultSet.next()) {

				QuesitoBuilder builder = Quesito.builder();
				builder.setDifficolta(difficolta);

				CodiceQuesito codiceQuesito = null;
				try {
					codiceQuesito = CodiceQuesito.parse(quesitiResultSet.getString(1));
				} catch (CodiceInvalidoException ignored) {
				}

				String testoDomanda = quesitiResultSet.getString(2);
				Categoria categoria = Categoria.valueOf(quesitiResultSet.getString(3));

				String urlImmagine = quesitiResultSet.getString(4);
				String urlDocumentazione = quesitiResultSet.getString(5);
				InformazioniDomanda informazionDomanda = new InformazioniDomanda(urlImmagine, urlDocumentazione);
				Domanda domanda = null;
				try {
					domanda = new Domanda(testoDomanda, categoria, informazionDomanda);
				} catch (CreazioneDomandaException ignored) {
				}

				builder.setDomanda(domanda);

				leggiRisposte(builder, codiceQuesito, dbConnection);
				leggiSuggerimenti(builder, codiceQuesito, dbConnection);

				try {
					quesiti.add(builder.build(codiceQuesito));
				} catch (CreazioneQuesitoException | SuggerimentiInvalidiException ignored) {
				}

			}
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
			if (quesitiResultSet != null) {
				try {
					quesitiResultSet.close();
				} catch (SQLException ignored) {
				}
			}

		}

		return quesiti.get(random.nextInt(quesiti.size()));
	}

	private void leggiRisposte(QuesitoBuilder builder, CodiceQuesito codiceQuesito, DbConnection dbConnection)
			throws SQLException {
		String sqlScript = "SELECT r.codice ,r.testo ,r.corretta  FROM Risposta r WHERE r.codice_quesito= ?";

		PreparedStatement preparedStatement = null;
		ResultSet risposteResultSet = null;
		try {
			preparedStatement = dbConnection.prepareStatement(sqlScript);
			preparedStatement.setString(1, codiceQuesito.getCodice());

			risposteResultSet = preparedStatement.executeQuery();

			while (risposteResultSet.next()) {
				CodiceRisposta codiceRisposta = null;
				try {
					codiceRisposta = CodiceRisposta.parse(risposteResultSet.getString(1));
				} catch (CodiceInvalidoException ignored) {
				}
				String testo = risposteResultSet.getString(2);
				boolean corretta = risposteResultSet.getBoolean(3);
				try {
					builder.aggiungiRisposta(Risposta.parse(codiceRisposta, testo, corretta));
				} catch (NumeroMassimoRisposteSuperatoException ingored) {
				}
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (risposteResultSet != null) {
				risposteResultSet.close();
			}
		}
	}

	private void leggiSuggerimenti(QuesitoBuilder builder, CodiceQuesito codiceQuesito, DbConnection dbConnection)
			throws SQLException {

		PreparedStatement preparedStatement = null;
		ResultSet suggerimentiResultSet = null;
		try {
			String sqlScript = "SELECT s.codice ,s.testo ,s.accuratezza,s.tempo_minimo FROM Suggerimento s WHERE s.codice_quesito= ?";

			preparedStatement = dbConnection.prepareStatement(sqlScript);
			preparedStatement.setString(1, codiceQuesito.getCodice());

			suggerimentiResultSet = preparedStatement.executeQuery();

			while (suggerimentiResultSet.next()) {
				CodiceSuggerimento codiceSuggerimento = null;
				try {
					codiceSuggerimento = CodiceSuggerimento.parse(suggerimentiResultSet.getString(1));
				} catch (CodiceInvalidoException ignored) {
				}
				String testo = suggerimentiResultSet.getString(2);
				Accuratezza accuratezza = Accuratezza.valueOf(suggerimentiResultSet.getString(3).toUpperCase());
				int tempoMinimo = suggerimentiResultSet.getInt(4);
				builder.aggiungiSuggerimento(Suggerimento.parse(codiceSuggerimento, testo, tempoMinimo, accuratezza));
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (suggerimentiResultSet != null) {
				suggerimentiResultSet.close();
			}
		}
	}

}
