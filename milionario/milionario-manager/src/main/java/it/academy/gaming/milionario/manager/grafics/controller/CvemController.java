package it.academy.gaming.milionario.manager.grafics.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.core.views.QuesitoView;
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
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerCategoriaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.screens.CancellazioneQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.InserimentoQuesitoScreen;
import it.academy.gaming.milionario.manager.grafics.screens.MenuScreen;
import it.academy.gaming.milionario.manager.grafics.screens.ModificaQuesitoScreen;
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
			TestoRispostaAssenteException, NumeroMassimoRisposteSuperatoException, DifficoltaNonInRangeException, SuggerimentiInvalidiException {
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
		RicercaQuesitoPerDifficoltaQuery command = new RicercaQuesitoPerDifficoltaQuery(
				requestPerDifficolta.getLivelloDifficolta());

		List<QuesitoView> quesitiView = service.cercaPerLivelloDifficolta(command);
		risultatoRicercaScreen.show(quesitiView);

	}

	public void cercaPerCategoria(RicercaQuesitoPerCategoriaRequest requestPerCategoria) {
		RicercaQuesitoPerCategoriaQuery command = new RicercaQuesitoPerCategoriaQuery(
				requestPerCategoria.getCategoriaRicercata());

		List<QuesitoView> quesitiView = service.cercaPerCategoria(command);
		risultatoRicercaScreen.show(quesitiView);
	}

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoRequest recuperaQuesitoRequest)
			throws QuesitoNonTrovatoException, CodiceInvalidoException {
		RecuperaQuesitoQuery command = new RecuperaQuesitoQuery(recuperaQuesitoRequest.getCodiceQuesitoRicercato());
		return service.getQuesitoPerRichiestaModifica(command);
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaRequest request)
			throws QuesitoNonTrovatoException, DifficoltaNonInRangeException, CodiceInvalidoException {
		ModificaDifficoltaCommand command = new ModificaDifficoltaCommand(request.getTestoQuesito(),
				request.getLivelloDifficolta());
		QuesitoView quesitoModificato = service.modificaDifficolta(command);
		return quesitoModificato;
	}

	public QuesitoView modificaRisposte(ModificaRisposteRequest request) throws QuesitoNonTrovatoException,
			RisposteInvalideException, TestoRispostaAssenteException, CodiceInvalidoException {
		List<ModificaRispostaCommand> risposteCommand = new ArrayList<>();
		for (ModificaRispostaRequest rispostaRequest : request.getNuoveRisposte()) {
			risposteCommand.add(new ModificaRispostaCommand(rispostaRequest.getTesto(), rispostaRequest.isCorretta()));
		}

		ModificaRisposteCommand command = new ModificaRisposteCommand(risposteCommand, request.getCodiceQuesito());

		return service.modificaRisposte(command);
	}

	public QuesitoView modificaDomanda(ModificaDomandaRequest request)
			throws QuesitoNonTrovatoException, CreazioneDomandaException, CodiceInvalidoException {

		ModificaDomandaCommand command = new ModificaDomandaCommand(request.getCodiceQuesito(),
				request.getTestoDomanda(), request.getCategoria(), request.getUrlImmagine(),
				request.getUrlDocumentazione());
		return service.modificaDomanda(command);
	}

	public int getTempoMaxPerSuggerimento() {
		// TODO Auto-generated method stub
		return 0;
	}

}
