package it.academy.gaming.milionario.manager.core_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.CodiceRisposta;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core_impl.exceptions.DbQuesitoException;

public class QuesitoRepositoryImplementation implements QuesitoRepository {

	private ConnectionManager connectionManager;

	public QuesitoRepositoryImplementation(String url, String user, String password) {
		this.connectionManager = new ConnectionManager(url, user, password);
	}

	@Override
	public void add(Quesito quesito) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "INSERT  INTO Quesito  (codice ,testo_domanda,categoria,url_immagine,url_documentazione,livello_difficolta) VALUES (?,?,?,?,?,?)";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, quesito.getCodice().getCodice());
			preparedStatement.setString(2, quesito.getDomanda().getTesto());
			preparedStatement.setString(3, quesito.getDomanda().getCategoria().toString());
			preparedStatement.setString(4, quesito.getDomanda().getInformazione().getUrlImmagine());
			preparedStatement.setString(5, quesito.getDomanda().getInformazione().getUrlDocumentazione());
			preparedStatement.setInt(6, quesito.getDifficolta().getDifficolta());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbQuesitoException();
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
	public void remove(CodiceQuesito codiceQuesito) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "REMOVE FROM Quesito WHERE codice= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, codiceQuesito.getCodice());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbQuesitoException();
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
	public Collection<Quesito> findByLivelloDifficolta(Difficolta difficolta) {
		Collection<Quesito> quesiti = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		DbConnection dbConnection = null;
		ResultSet quesitiResultSet = null;
		ResultSet risposteResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT FROM Quesito WHERE livello_difficolta= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setInt(1, difficolta.getDifficolta());

			quesitiResultSet = preparedStatement.executeQuery();

			while (quesitiResultSet.next()) {

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

				sqlScript = "SELECT r.codice ,r.testo ,r.corretta  FROM Quesito JOIN Risposta r ON q.codice =r.codice_quesito WHERE q.codice= ?";

				preparedStatement = dbConnection.prepareStatement(sqlScript);
				preparedStatement.setString(1, codiceQuesito.getCodice());

				risposteResultSet = preparedStatement.executeQuery();

				Risposta[] risposte = new Risposta[4];

				int indice = 0;
				while (risposteResultSet.next()) {
					CodiceRisposta codiceRisposta = null;
					try {
						codiceRisposta = CodiceRisposta.parse(risposteResultSet.getString(1));
					} catch (CodiceInvalidoException ignored) {
					}
					String testo = risposteResultSet.getString(2);
					boolean corretta = risposteResultSet.getBoolean(3);
					risposte[indice++] = Risposta.parse(codiceRisposta, testo, corretta);
				}
				try {
					quesiti.add(Quesito.parse(codiceQuesito, domanda, risposte, difficolta));
				} catch (CreazioneQuesitoException ignored) {
				}

			}
			return quesiti;
		} catch (SQLException e) {
			throw new DbQuesitoException();
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
			if (risposteResultSet != null) {
				try {
					risposteResultSet.close();
				} catch (SQLException ignored) {
				}
			}

		}
	}

	@Override
	public Collection<Quesito> findByCategoria(Categoria categoria) {
		Collection<Quesito> quesiti = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		DbConnection dbConnection = null;
		ResultSet quesitiResultSet = null;
		ResultSet risposteResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT FROM Quesito WHERE categoria= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, categoria.toString());

			quesitiResultSet = preparedStatement.executeQuery();

			while (quesitiResultSet.next()) {

				CodiceQuesito codiceQuesito = null;
				try {
					codiceQuesito = CodiceQuesito.parse(quesitiResultSet.getString(1));
				} catch (CodiceInvalidoException ignored) {
				}

				Difficolta difficolta = null;
				try {
					difficolta = new Difficolta(quesitiResultSet.getInt(6));
				} catch (DifficoltaNonInRangeException ignored) {
				}

				String testoDomanda = quesitiResultSet.getString(2);

				String urlImmagine = quesitiResultSet.getString(4);
				String urlDocumentazione = quesitiResultSet.getString(5);
				InformazioniDomanda informazionDomanda = new InformazioniDomanda(urlImmagine, urlDocumentazione);
				Domanda domanda = null;
				try {
					domanda = new Domanda(testoDomanda, categoria, informazionDomanda);
				} catch (CreazioneDomandaException ignored) {
				}

				sqlScript = "SELECT r.codice ,r.testo ,r.corretta  FROM Quesito JOIN Risposta r ON q.codice =r.codice_quesito WHERE q.codice= ?";

				preparedStatement = dbConnection.prepareStatement(sqlScript);
				preparedStatement.setString(1, codiceQuesito.getCodice());

				risposteResultSet = preparedStatement.executeQuery();

				Risposta[] risposte = new Risposta[4];

				int indice = 0;
				while (risposteResultSet.next()) {
					CodiceRisposta codiceRisposta = null;
					try {
						codiceRisposta = CodiceRisposta.parse(risposteResultSet.getString(1));
					} catch (CodiceInvalidoException ignored) {
					}
					String testo = risposteResultSet.getString(2);
					boolean corretta = risposteResultSet.getBoolean(3);
					risposte[indice++] = Risposta.parse(codiceRisposta, testo, corretta);
				}
				try {
					quesiti.add(Quesito.parse(codiceQuesito, domanda, risposte, difficolta));
				} catch (CreazioneQuesitoException ignored) {
				}

			}
			return quesiti;
		} catch (SQLException e) {
			throw new DbQuesitoException();
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
			if (risposteResultSet != null) {
				try {
					risposteResultSet.close();
				} catch (SQLException ignored) {
				}
			}

		}
	}

	@Override
	public Optional<Quesito> findByCodice(String codiceQuesitoRicercato) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void setDifficolta(String codiceQuesito, Difficolta difficolta) {
		// TODO Auto-generated method stub
		
	}

}
