package it.academy.gaming.milionario.manager.core.application;

import java.util.List;

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
import it.academy.gaming.milionario.manager.core.commands.RecuperaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.RicercaQuesitoPerCategoriaQuery;
import it.academy.gaming.milionario.manager.core.commands.RicercaQuesitoPerDifficoltaQuery;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
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

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoCommand command) {
		return null;
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

}
