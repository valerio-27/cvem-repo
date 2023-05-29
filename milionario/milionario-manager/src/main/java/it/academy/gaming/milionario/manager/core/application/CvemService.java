package it.academy.gaming.milionario.manager.core.application;

import java.util.List;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.manager.core.commands.CancellaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRisposteCommand;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.core.queries.RecuperaQuesitoQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerCategoriaQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerDifficoltaQuery;
import it.academy.gaming.milionario.manager.core.views.QuesitoView;

public class CvemService {

	private QuesitoRepository quesitoRepository;

	public int getMinDiDifficolta() {
		return 0;
	}

	public int getMaxDiDifficolta() {
		return 0;
	}

	/**
	 * metodo in cui mi confronto con il builder e poi salvo il quesito nella
	 * QuesitoRepository
	 * 
	 * @param quesitoCommand
	 * @throws CreazioneQuesitoException
	 */
	public void inserisci(InserisciQuesitoCommand quesitoCommand) throws CreazioneQuesitoException {
		QuesitoBuilder builder = Quesito.builder();
		/*
		 * devo fare tutti i set e creare tutti gli oggetti poi chiamo il build();
		 */

		Quesito quesito = builder.build();
		quesitoRepository.add(quesito);
	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 * 
	 * @throws CodiceInvalidoException
	 */
	public void cancellaQuesito(CancellaQuesitoCommand command) throws CodiceInvalidoException {

		CodiceQuesito codiceQuesito = CodiceQuesito.parse(null);
		quesitoRepository.remove(codiceQuesito);
	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 * 
	 * @throws DifficoltaNonInRangeException
	 */

	public List<QuesitoView> cercaPerLivelloDifficolta(RicercaQuesitoPerDifficoltaQuery query)
			throws DifficoltaNonInRangeException {

		quesitoRepository.findByLivelloDifficolta(new Difficolta(query.getLivelloDifficolta()));
		return null;
	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 */

	public List<QuesitoView> cercaPerCategoria(RicercaQuesitoPerCategoriaQuery query) {

		quesitoRepository.findByCategoria(query.getCategoriaRicercata());
		return null;
	}

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoQuery query) throws QuesitoNonTrovatoException {
		return generaQuesitoView(query.getCodiceQuesitoRicercato());
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaCommand command) throws QuesitoNonTrovatoException {

		verificaEsistenzaQuesito(command.getCodiceQuesito());
		quesitoRepository.setDifficolta(command.getCodiceQuesito(), command.getLivelloDifficolta());
		return generaQuesitoView(command.getCodiceQuesito());
	}

	public QuesitoView modificaRisposte(ModificaRisposteCommand command) throws QuesitoNonTrovatoException {
		verificaEsistenzaQuesito(command.getCodiceQuesito());
		
		// TODO Auto-generated method stub
		return generaQuesitoView(command.getCodiceQuesito());
	}

	public QuesitoView modificaDomanda(ModificaDomandaCommand command) throws QuesitoNonTrovatoException {
		verificaEsistenzaQuesito(command.getCodiceQuesito());
		
		
		// TODO Auto-generated method stub
		return generaQuesitoView(command.getCodiceQuesito());
	}

	private QuesitoView generaQuesitoView(String codiceQuesito) throws QuesitoNonTrovatoException {
		Quesito quesito = verificaEsistenzaQuesito(codiceQuesito);

		/*
		 * implementazione
		 */
		return null;
	}

	private Quesito verificaEsistenzaQuesito(String codiceQuesito) throws QuesitoNonTrovatoException {
		Optional<Quesito> quesitoOptional = quesitoRepository.findByCodice(codiceQuesito);
		if (quesitoOptional.isPresent()) {
			return quesitoOptional.get();
		}
		throw new QuesitoNonTrovatoException(
				"Il codice che hai fornito non identifica nessun quesito " + codiceQuesito);

	}

}
