package it.academy.gaming.milionario.manager.core_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.codici.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.codici.CodiceRisposta;
import it.academy.gaming.milionario.core.domain.codici.CodiceSuggerimento;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
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
			preparedStatement.setString(4, quesito.getDomanda().getInformazioni().getUrlImmagine());
			preparedStatement.setString(5, quesito.getDomanda().getInformazioni().getUrlDocumentazione());
			preparedStatement.setInt(6, quesito.getDifficolta().getDifficolta());

			preparedStatement.executeUpdate();

			aggiungiRisposte(quesito.getCodice(), Arrays.asList(quesito.getRisposte()));
			aggiungiSuggerimenti(quesito.getCodice(), quesito.getSuggerimentiPerAccuratezza());

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

	private void aggiungiSuggerimenti(CodiceQuesito codice,
			Map<Accuratezza, List<Suggerimento>> suggerimentiPerAccuratezza) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			List<Suggerimento> suggerimenti = new ArrayList<>();

			for (List<Suggerimento> suggerimentiSelezionati : suggerimentiPerAccuratezza.values()) {
				suggerimenti.addAll(suggerimentiSelezionati);
			}

			String sqlScript = "INSERT INTO Suggerimento (codice,codice_quesito,accuratezza,testo,tempo_minimo) VALUES (?,?,?,?,?)";
			for (Suggerimento suggerimento : suggerimenti) {
				preparedStatement = dbConnection.prepareStatement(sqlScript);

				preparedStatement.setString(1, suggerimento.getCodice().getCodice());
				preparedStatement.setString(2, codice.getCodice());
				preparedStatement.setString(3, suggerimento.getAccuratezza().toString());
				preparedStatement.setString(4, suggerimento.getTesto());
				preparedStatement.setInt(5, suggerimento.getTempoMinimo());

				preparedStatement.executeUpdate();

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
		}

	}

	private void aggiungiRisposte(CodiceQuesito codice, Collection<Risposta> risposte) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "INSERT INTO Risposta (codice,codice_quesito,testo,corretta) VALUES (?,?,?,?)";
			for (Risposta risposta : risposte) {
				preparedStatement = dbConnection.prepareStatement(sqlScript);

				preparedStatement.setString(1, risposta.getCodiceRisposta().getCodice());
				preparedStatement.setString(2, codice.getCodice());
				preparedStatement.setString(3, risposta.getTesto());
				preparedStatement.setBoolean(4, risposta.isCorretta());

				preparedStatement.executeUpdate();

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
		}
	}

	@Override
	public void remove(CodiceQuesito codiceQuesito) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "DELETE FROM Quesito WHERE codice= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, codiceQuesito.getCodice());

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
	public Collection<Quesito> findByLivelloDifficolta(Difficolta difficolta) {
		Collection<Quesito> quesiti = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		DbConnection dbConnection = null;
		ResultSet quesitiResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT * FROM Quesito WHERE livello_difficolta= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setInt(1, difficolta.getDifficolta());

			quesitiResultSet = preparedStatement.executeQuery();

			QuesitoBuilder builder = Quesito.builder();
			builder.setDifficolta(difficolta);

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

				builder.setDomanda(domanda);

				leggiRisposte(builder, codiceQuesito, dbConnection);
				leggiSuggerimenti(builder, codiceQuesito,dbConnection);

				try {
					quesiti.add(builder.build(codiceQuesito));
				} catch (CreazioneQuesitoException | SuggerimentiInvalidiException ignored) {
				}

			}
			return quesiti;
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
	}

	@Override
	public Collection<Quesito> findByCategoria(Categoria categoria) {
		Collection<Quesito> quesiti = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		DbConnection dbConnection = null;
		ResultSet quesitiResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT * FROM Quesito WHERE categoria= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, categoria.toString());

			quesitiResultSet = preparedStatement.executeQuery();

			QuesitoBuilder builder = Quesito.builder();

			while (quesitiResultSet.next()) {

				CodiceQuesito codiceQuesito = null;
				try {
					codiceQuesito = CodiceQuesito.parse(quesitiResultSet.getString(1));
				} catch (CodiceInvalidoException ignored) {
				}

				String testoDomanda = quesitiResultSet.getString(2);
				String urlImmagine = quesitiResultSet.getString(4);
				String urlDocumentazione = quesitiResultSet.getString(5);
				Difficolta difficolta = null;
				try {
					difficolta = new Difficolta(quesitiResultSet.getInt(6));
				} catch (DifficoltaNonInRangeException ignored) {
				}
				InformazioniDomanda informazionDomanda = new InformazioniDomanda(urlImmagine, urlDocumentazione);
				Domanda domanda = null;
				try {
					domanda = new Domanda(testoDomanda, categoria, informazionDomanda);
				} catch (CreazioneDomandaException ignored) {
				}
				builder.setDifficolta(difficolta);
				builder.setDomanda(domanda);

				leggiRisposte(builder, codiceQuesito, dbConnection);
				leggiSuggerimenti(builder, codiceQuesito,dbConnection);

				try {
					quesiti.add(builder.build(codiceQuesito));
				} catch (CreazioneQuesitoException | SuggerimentiInvalidiException ignored) {
				}

			}
			return quesiti;
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
	}

	@Override
	public Optional<Quesito> findByCodice(CodiceQuesito codiceQuesito) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;
		ResultSet quesitoResultSet = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "SELECT * FROM Quesito WHERE codice= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, codiceQuesito.getCodice());

			quesitoResultSet = preparedStatement.executeQuery();

			QuesitoBuilder builder = Quesito.builder();

			if (quesitoResultSet.next()) {

				Difficolta difficolta = null;
				try {
					difficolta = new Difficolta(quesitoResultSet.getInt(6));
				} catch (DifficoltaNonInRangeException ignored) {
				}

				builder.setDifficolta(difficolta);
				String testoDomanda = quesitoResultSet.getString(2);

				String urlImmagine = quesitoResultSet.getString(4);
				String urlDocumentazione = quesitoResultSet.getString(5);
				InformazioniDomanda informazionDomanda = new InformazioniDomanda(urlImmagine, urlDocumentazione);
				Categoria categoria = Categoria.valueOf(quesitoResultSet.getString(3).toUpperCase());
				Domanda domanda = null;
				try {
					domanda = new Domanda(testoDomanda, categoria, informazionDomanda);
				} catch (CreazioneDomandaException ignored) {
				}
				builder.setDomanda(domanda);

				leggiRisposte(builder, codiceQuesito, dbConnection);
				leggiSuggerimenti(builder, codiceQuesito,dbConnection);

				try {
					return Optional.of(builder.build(codiceQuesito));
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
			if (quesitoResultSet != null) {
				try {
					quesitoResultSet.close();
				} catch (SQLException ignored) {
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public void setDifficolta(CodiceQuesito codiceQuesito, Difficolta difficolta) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;
		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "UPDATE Quesito SET livello_difficolta= ? WHERE codice= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setInt(1, difficolta.getDifficolta());
			preparedStatement.setString(2, codiceQuesito.getCodice());

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

	/**
	 * cancello prima tutte le risposte associate al quesito e poi inserisco le
	 * nuove
	 */
	@Override
	public void setRisposte(CodiceQuesito codiceQuesito, List<Risposta> nuoveRisposte) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;

		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "DELETE FROM Risposta WHERE codice_quesito= ?";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, codiceQuesito.getCodice());

			preparedStatement.executeUpdate();

			aggiungiRisposte(codiceQuesito, nuoveRisposte);

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
	public void setDomanda(CodiceQuesito codiceQuesito, Domanda domanda) {
		PreparedStatement preparedStatement = null;

		DbConnection dbConnection = null;
		try {
			dbConnection = connectionManager.creaConnection();

			String sqlScript = "UPDATE Quesito SET testo_domanda=? , categoria=? ,url_immagine=? ,url_documentazione=? WHERE codice= ? ";
			preparedStatement = dbConnection.prepareStatement(sqlScript);

			preparedStatement.setString(1, domanda.getTesto());
			preparedStatement.setString(2, domanda.getCategoria().toString());

			InformazioniDomanda informazioni = domanda.getInformazioni();

			preparedStatement.setString(3, informazioni.getUrlImmagine());
			preparedStatement.setString(4, informazioni.getUrlDocumentazione());

			preparedStatement.setString(5, codiceQuesito.getCodice());

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

	private void leggiRisposte(QuesitoBuilder builder, CodiceQuesito codiceQuesito, DbConnection dbConnection)
			throws SQLException {
		String sqlScript = "SELECT r.codice ,r.testo ,r.corretta  FROM Quesito q JOIN Risposta r ON q.codice =r.codice_quesito WHERE q.codice= ?";

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
	
	private void leggiSuggerimenti(QuesitoBuilder builder, CodiceQuesito codiceQuesito, DbConnection dbConnection) throws SQLException {
		
		PreparedStatement preparedStatement=null;
		ResultSet suggerimentiResultSet=null;
		try {
			String sqlScript = "SELECT s.codice ,s.testo ,s.accuratezza,s.tempo_minimo FROM Quesito q JOIN Suggerimento s ON q.codice =s.codice_quesito WHERE q.codice= ?";

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
				builder.aggiungiSuggerimento(
						Suggerimento.parse(codiceSuggerimento, testo, tempoMinimo, accuratezza));
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


	@Override
	public void setSuggerimenti(CodiceQuesito codice, List<Suggerimento> nuoviSuggerimenti) {
		// TODO Auto-generated method stub
		
	}
}