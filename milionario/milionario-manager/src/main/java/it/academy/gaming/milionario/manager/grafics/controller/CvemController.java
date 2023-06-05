package it.academy.gaming.milionario.manager.grafics.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.PercentualeFortunaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.core.views.OpzioniPersonaView;
import it.academy.gaming.milionario.core.views.PercentualeFortunaView;
import it.academy.gaming.milionario.core.views.QuesitoView;
import it.academy.gaming.milionario.core.views.RangeCulturaGeneraleView;
import it.academy.gaming.milionario.manager.core.application.CvemService;
import it.academy.gaming.milionario.manager.core.commands.CancellaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciSuggerimentoCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRisposteCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaSuggerimentiCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaSuggerimentoCommand;
import it.academy.gaming.milionario.manager.core.commands.SalvaOpzioniPersonaCommand;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.core.queries.RecuperaQuesitoQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerCategoriaQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerDifficoltaQuery;
import it.academy.gaming.milionario.manager.grafics.requests.CancellaQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciSuggerimentoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDomandaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRisposteRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaSuggerimentiRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaSuggerimentoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerCategoriaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.SalvaOpzioniPersonaRequest;
import it.academy.gaming.milionario.manager.grafics.screens.CancellazioneQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.GestioneOpzioniPersonaScreen;
import it.academy.gaming.milionario.manager.grafics.screens.InserimentoQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.MenuScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaDifficoltaScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaDomandaScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaRisposteScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaSuggerimentiScreen;
import it.academy.gaming.milionario.manager.grafics.screens.RicercaQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.RisultatoRicercaScreen;

public class CvemController {
	private CvemService service;
	private MenuScreen menuScreen = new MenuScreen(this);
	private InserimentoQuesitoScreen inserimentoQuesitoScreen = new InserimentoQuesitoScreen(this);
	private ModificaQuesitoScreen modificaQuesitoScreen = new ModificaQuesitoScreen(this);
	private CancellazioneQuesitoScreen cancellazioneQuesitoScreen = new CancellazioneQuesitoScreen(this);
	private RicercaQuesitoScreen ricercaQuesitoScreen = new RicercaQuesitoScreen(this);
	private RisultatoRicercaScreen risultatoRicercaScreen = new RisultatoRicercaScreen(this);
	private ModificaDomandaScreen modificaDomandaScreen = new ModificaDomandaScreen(this);
	private ModificaSuggerimentiScreen modificaSuggerimentiScreen = new ModificaSuggerimentiScreen(this);
	private ModificaDifficoltaScreen modificaDifficoltaScreen = new ModificaDifficoltaScreen(this);
	private ModificaRisposteScreen modificaRisposteScreen = new ModificaRisposteScreen(this);
	private GestioneOpzioniPersonaScreen gestioneOpzioniPersonaScreen = new GestioneOpzioniPersonaScreen(this);

	public CvemController(CvemService service) {
		super();
		this.service = service;
	}

	public void showMenuScreen() {
		menuScreen.show();
	}

	public void showInserisciQuesitoScreen() {
		inserimentoQuesitoScreen.show();

	}

	public void showModificaQuesitoScreen() {
		modificaQuesitoScreen.show();

	}

	public void showCancellazioneQuesitoScreen() {
		cancellazioneQuesitoScreen.show();

	}

	public void showRicercaQuesitoScreen() {
		ricercaQuesitoScreen.show();

	}

	public int getMinimoDiDifficolta() {
		return service.getMinDiDifficolta();
	}

	public int getMassimoDiDifficolta() {
		return service.getMaxDiDifficolta();
	}

	public void inserisci(InserisciQuesitoRequest request) throws CreazioneQuesitoException, CreazioneDomandaException,
			TestoRispostaAssenteException, NumeroMassimoRisposteSuperatoException, DifficoltaNonInRangeException,
			SuggerimentiInvalidiException, SuggerimentoInvalidoException {
		/*
		 * request domanda to command
		 */
		InserisciDomandaCommand domandaCommand = new InserisciDomandaCommand(
				request.getDomandaRequest().getTestoDomanda(), request.getDomandaRequest().getCategoria(),
				request.getDomandaRequest().getUrlImmagne(), request.getDomandaRequest().getUrlDocumentazione());
		/*
		 * requests risposta to commands
		 */
		InserisciRispostaCommand[] arrayRispostaCommands = new InserisciRispostaCommand[4];
		int i = 0;
		for (InserisciRispostaRequest requestRisposta : request.getRispostaRequests()) {
			arrayRispostaCommands[i++] = new InserisciRispostaCommand(requestRisposta.getTestoRisposta(),
					requestRisposta.isRispostaGiusta());

		}
		/*
		 * requests suggerimenti to commands
		 */
		Collection<InserisciSuggerimentoCommand> suggerimentoCommands = new ArrayList<InserisciSuggerimentoCommand>();
		for (InserisciSuggerimentoRequest suggerimentoRequest : request.getSuggerimenti()) {
			suggerimentoCommands.add(new InserisciSuggerimentoCommand(suggerimentoRequest.getTestoSuggerimento(),
					suggerimentoRequest.getAccuratezza(), suggerimentoRequest.getTempoMinimo()));

		}
		InserisciQuesitoCommand quesitoCommand = new InserisciQuesitoCommand(domandaCommand, arrayRispostaCommands,
				suggerimentoCommands, request.getLivelloDifficolta());
		service.inserisci(quesitoCommand);
		showMenuScreen();
	}

	public void cancellaQuesito(CancellaQuesitoRequest request) throws CodiceInvalidoException {
		CancellaQuesitoCommand command = new CancellaQuesitoCommand(request.getCodiceQuesito());
		service.cancellaQuesito(command);
		showMenuScreen();
	}

	public void cercaPerDifficolta(RicercaQuesitoPerDifficoltaRequest requestPerDifficolta)
			throws DifficoltaNonInRangeException {
		RicercaQuesitoPerDifficoltaQuery query = new RicercaQuesitoPerDifficoltaQuery(
				requestPerDifficolta.getLivelloDifficolta());

		List<QuesitoView> quesitiView = service.cercaPerLivelloDifficolta(query);
		risultatoRicercaScreen.show(quesitiView);

	}

	public void cercaPerCategoria(RicercaQuesitoPerCategoriaRequest requestPerCategoria) {
		RicercaQuesitoPerCategoriaQuery query = new RicercaQuesitoPerCategoriaQuery(
				requestPerCategoria.getCategoriaRicercata());

		List<QuesitoView> quesitiView = service.cercaPerCategoria(query);
		risultatoRicercaScreen.show(quesitiView);
	}

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoRequest recuperaQuesitoRequest)
			throws QuesitoNonTrovatoException, CodiceInvalidoException {
		RecuperaQuesitoQuery query = new RecuperaQuesitoQuery(recuperaQuesitoRequest.getCodiceQuesitoRicercato());
		return service.getQuesitoPerRichiestaModifica(query);
	}

	public void modificaDifficolta(ModificaDifficoltaRequest request)
			throws QuesitoNonTrovatoException, DifficoltaNonInRangeException, CodiceInvalidoException {
		ModificaDifficoltaCommand command = new ModificaDifficoltaCommand(request.getTestoQuesito(),
				request.getLivelloDifficolta());
		service.modificaDifficolta(command);
	}

	public void modificaRisposte(ModificaRisposteRequest request) throws QuesitoNonTrovatoException,
			RisposteInvalideException, TestoRispostaAssenteException, CodiceInvalidoException {
		List<ModificaRispostaCommand> risposteCommand = new ArrayList<>();
		for (ModificaRispostaRequest rispostaRequest : request.getNuoveRisposte()) {
			risposteCommand.add(new ModificaRispostaCommand(rispostaRequest.getTesto(), rispostaRequest.isCorretta()));
		}

		ModificaRisposteCommand command = new ModificaRisposteCommand(risposteCommand, request.getCodiceQuesito());

		service.modificaRisposte(command);
	}

	public void modificaDomanda(ModificaDomandaRequest request)
			throws QuesitoNonTrovatoException, CreazioneDomandaException, CodiceInvalidoException {

		ModificaDomandaCommand command = new ModificaDomandaCommand(request.getCodiceQuesito(),
				request.getTestoDomanda(), request.getCategoria(), request.getUrlImmagine(),
				request.getUrlDocumentazione());
		service.modificaDomanda(command);
	}

	public int getTempoMaxPerSuggerimento() {

		return service.getTempoMassimoPerSuggerimento();
	}

	public void modificaDomanda() {
		modificaDomandaScreen.show();

	}

	public void modificaRisposte() {
		modificaRisposteScreen.show();

	}

	public void modificaDifficolta() {
		modificaDifficoltaScreen.show();

	}

	public void modificaSuggerimenti() {
		modificaSuggerimentiScreen.show();

	}

	public void showInserisciOpzioniPersonaScreen() {
		gestioneOpzioniPersonaScreen.show();

	}

	public OpzioniPersonaView getOpzioniPersona() {

		return service.getOpzioniPersona();
	}

	public RangeCulturaGeneraleView getRangeCulturaGenerale() {
		return service.getRangeCulturaGenerale();
	}

	public PercentualeFortunaView getPercentualeFortuna() {
		return service.getPercentualeFortuna();
	}

	public void salvaOpzioniPersona(SalvaOpzioniPersonaRequest request)
			throws CulturaGeneraleNonInRangeException, PercentualeFortunaNonInRangeException {
		SalvaOpzioniPersonaCommand command = new SalvaOpzioniPersonaCommand(request.getMaxConoscenza(),
				request.getMinConoscenza(), request.getPercentualeFortuna());
		service.salvaOpzioniPersona(command);

	}

	public void modificaSuggerimenti(ModificaSuggerimentiRequest request) throws SuggerimentoInvalidoException, CodiceInvalidoException {
		List<ModificaSuggerimentoCommand> suggerimentoCommands = new ArrayList<ModificaSuggerimentoCommand>();
		for (ModificaSuggerimentoRequest suggerimentoRequest : request.getModificaSuggerimentiRequests()) {
			suggerimentoCommands.add(new ModificaSuggerimentoCommand(suggerimentoRequest.getTestoSuggerimento(),
					suggerimentoRequest.getAccuratezza(), suggerimentoRequest.getTempoMinimo()));

		}
		ModificaSuggerimentiCommand command = new ModificaSuggerimentiCommand(request.getCodiceQuesito(),
				suggerimentoCommands);
		service.modificaSuggerimenti(command);
	}

}
