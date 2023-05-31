package it.academy.gaming.milionario.manager.core.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.core.views.DifficoltaView;
import it.academy.gaming.milionario.core.views.DomandaView;
import it.academy.gaming.milionario.core.views.InformazioniView;
import it.academy.gaming.milionario.core.views.QuesitoView;
import it.academy.gaming.milionario.core.views.RispostaView;
import it.academy.gaming.milionario.manager.core.commands.CancellaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRisposteCommand;
import it.academy.gaming.milionario.manager.core.domain.OpzioniPersonaRepository;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.core.queries.RecuperaQuesitoQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerCategoriaQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerDifficoltaQuery;

public class CvemService {

	private QuesitoRepository quesitoRepository;
	private OpzioniPersonaRepository opzioniPersonaRepository;

	public CvemService(QuesitoRepository quesitoRepository, OpzioniPersonaRepository opzioniPersonaRepository) {
		super();
		this.quesitoRepository = quesitoRepository;
		this.opzioniPersonaRepository = opzioniPersonaRepository;
	}

	public int getMinDiDifficolta() {
		return Difficolta.getMinimo();
	}

	public int getMaxDiDifficolta() {
		return Difficolta.getMassimo();
	}

	/**
	 * metodo in cui mi confronto con il builder e poi salvo il quesito nella
	 * QuesitoRepository
	 * 
	 * @param inserisciQuesitoCommand
	 * @throws CreazioneQuesitoException
	 * @throws CreazioneDomandaException
	 * @throws TestoRispostaAssenteException
	 * @throws NumeroMassimoRisposteSuperatoException
	 * @throws DifficoltaNonInRangeException
	 */
	public void inserisci(InserisciQuesitoCommand inserisciQuesitoCommand)
			throws CreazioneQuesitoException, CreazioneDomandaException, TestoRispostaAssenteException,
			NumeroMassimoRisposteSuperatoException, DifficoltaNonInRangeException {
		QuesitoBuilder builder = Quesito.builder();
		/*
		 * devo creare tutti gli oggetti fare tutti i set e poi chiamo il build()
		 * oviamente tutti metodi del builder;
		 */
		InserisciDomandaCommand domandaCommand = inserisciQuesitoCommand.getDomandaCommand();
		InformazioniDomanda informazioni = new InformazioniDomanda(domandaCommand.getUrlImmagne(),
				domandaCommand.getUrlDocumentazione());
		Domanda domanda = new Domanda(domandaCommand.getTestoDomanda(), domandaCommand.getCategoria(), informazioni);
		builder.setDomanda(domanda);
		for (InserisciRispostaCommand rispostaCommand : inserisciQuesitoCommand.getRispostaCommands()) {

			Risposta risposta = Risposta.crea(rispostaCommand.getTestoRisposta(), rispostaCommand.isRispostaGiusta());

			builder.aggiungiRisposta(risposta);
		}
		Difficolta difficolta = new Difficolta(inserisciQuesitoCommand.getLivelloDifficolta());
		builder.setDifficolta(difficolta);
		Quesito quesito = builder.build();
		quesitoRepository.add(quesito);
	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 * 
	 * @throws CodiceInvalidoException
	 */
	public void cancellaQuesito(CancellaQuesitoCommand command) throws CodiceInvalidoException {

		CodiceQuesito codiceQuesito = CodiceQuesito.parse(command.getCodiceQuesito());
		quesitoRepository.remove(codiceQuesito);
	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 * 
	 * @throws DifficoltaNonInRangeException
	 */

	public List<QuesitoView> cercaPerLivelloDifficolta(RicercaQuesitoPerDifficoltaQuery query)
			throws DifficoltaNonInRangeException {
		Collection<Quesito> quesitiTrovati = quesitoRepository
				.findByLivelloDifficolta(new Difficolta(query.getLivelloDifficolta()));
		List<QuesitoView> quesitiView = new ArrayList<>();
		for (Quesito quesito : quesitiTrovati) {
			try {
				quesitiView.add(generaQuesitoView(quesito.getCodice()));
			} catch (QuesitoNonTrovatoException ignored) {

			}
		}

		return quesitiView;

	}

	/**
	 * richiesta che va alla QuesitoRepository direttamente
	 */

	public List<QuesitoView> cercaPerCategoria(RicercaQuesitoPerCategoriaQuery query) {

		Collection<Quesito> quesitiTrovati = quesitoRepository.findByCategoria(query.getCategoriaRicercata());
		List<QuesitoView> quesitiView = new ArrayList<>();
		for (Quesito quesito : quesitiTrovati) {
			try {
				quesitiView.add(generaQuesitoView(quesito.getCodice()));
			} catch (QuesitoNonTrovatoException ignored) {

			}
		}

		return quesitiView;
	}

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoQuery query)
			throws QuesitoNonTrovatoException, CodiceInvalidoException {
		return generaQuesitoView(CodiceQuesito.parse(query.getCodiceQuesitoRicercato()));
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaCommand command)
			throws QuesitoNonTrovatoException, DifficoltaNonInRangeException, CodiceInvalidoException {

		verificaEsistenzaQuesito(CodiceQuesito.parse(command.getCodiceQuesito()));
		Difficolta difficolta = new Difficolta(command.getLivelloDifficolta());
		quesitoRepository.setDifficolta(CodiceQuesito.parse(command.getCodiceQuesito()), difficolta);
		return generaQuesitoView(CodiceQuesito.parse(command.getCodiceQuesito()));
	}

	public QuesitoView modificaRisposte(ModificaRisposteCommand command) throws QuesitoNonTrovatoException,
			TestoRispostaAssenteException, RisposteInvalideException, CodiceInvalidoException {
		verificaEsistenzaQuesito(CodiceQuesito.parse(command.getCodiceQuesito()));
		/*
		 * creo 4 risposte a partire dal commnad
		 */
		List<Risposta> nuoveRisposte = new ArrayList<>();
		for (ModificaRispostaCommand rispostaCommand : command.getNuoveRisposte()) {
			nuoveRisposte.add(Risposta.crea(rispostaCommand.getTesto(), rispostaCommand.isCorretta()));
		}
		/*
		 * questo al massimo mi soleva eccezione altrimenti vado a vanti
		 */

		Quesito.checkRisposteValide(nuoveRisposte);

		quesitoRepository.setRisposte(CodiceQuesito.parse(command.getCodiceQuesito()), nuoveRisposte);

		return generaQuesitoView(CodiceQuesito.parse(command.getCodiceQuesito()));
	}

	public QuesitoView modificaDomanda(ModificaDomandaCommand command)
			throws QuesitoNonTrovatoException, CreazioneDomandaException, CodiceInvalidoException {
		verificaEsistenzaQuesito(CodiceQuesito.parse(command.getCodiceQuesito()));
		InformazioniDomanda informazioni = new InformazioniDomanda(command.getUrlImmagine(),
				command.getUrlDocumentazione());
		Domanda domanda = new Domanda(command.getTestoDomanda(), command.getCategoria(), informazioni);

		quesitoRepository.setDomanda(CodiceQuesito.parse(command.getCodiceQuesito()), domanda);
		return generaQuesitoView(CodiceQuesito.parse(command.getCodiceQuesito()));
	}

	private QuesitoView generaQuesitoView(CodiceQuesito codiceQuesito) throws QuesitoNonTrovatoException {
		Quesito quesito = verificaEsistenzaQuesito(codiceQuesito);

		InformazioniView informazioniView = new InformazioniView(
				quesito.getDomanda().getInformazioni().getUrlImmagine(),
				quesito.getDomanda().getInformazioni().getUrlDocumentazione());
		DomandaView domandaView = new DomandaView(quesito.getDomanda().getTesto(), quesito.getDomanda().getCategoria(),
				informazioniView);
		List<RispostaView> risposteView = new ArrayList<>();
		for (Risposta risposta : quesito.getRisposte()) {
			risposteView.add(new RispostaView(risposta.getTesto(), risposta.isCorretta()));
		}
		DifficoltaView difficoltaView = new DifficoltaView(quesito.getDifficolta().getDifficolta());
		String codice = quesito.getCodice().getCodice();
		/*
		 * implementazione
		 */
		return new QuesitoView(domandaView, risposteView, difficoltaView, codice);
	}

	private Quesito verificaEsistenzaQuesito(CodiceQuesito codiceQuesito) throws QuesitoNonTrovatoException {
		Optional<Quesito> quesitoOptional = quesitoRepository.findByCodice(codiceQuesito);
		if (quesitoOptional.isPresent()) {
			return quesitoOptional.get();
		}
		throw new QuesitoNonTrovatoException(
				"Il codice che hai fornito non identifica nessun quesito " + codiceQuesito);

	}

}
