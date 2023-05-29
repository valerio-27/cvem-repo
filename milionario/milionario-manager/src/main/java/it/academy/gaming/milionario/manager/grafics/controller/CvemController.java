package it.academy.gaming.milionario.manager.grafics.controller;

import java.util.ArrayList;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.manager.core.application.CvemService;
import it.academy.gaming.milionario.manager.core.commands.CancellaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaRisposteCommand;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.core.queries.RecuperaQuesitoQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerCategoriaQuery;
import it.academy.gaming.milionario.manager.core.queries.RicercaQuesitoPerDifficoltaQuery;
import it.academy.gaming.milionario.manager.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.grafics.requests.CancellaQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDomandaRequest;
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
	private InserimentoQuesitoScreen inserimentoQuesitoScreen;
	private ModificaQuesitoScreen modificaQuesitoScreen;
	private CancellazioneQuesitoScreen cancellazioneQuesitoScreen;
	private RicercaQuesitoScreen ricercaQuesitoScreen;
	private RisultatoRicercaScreen risultatoRicercaScreen;

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

	public int getMassimoDiDifficlta() {
		return service.getMaxDiDifficolta();
	}

	public void inserisci(InserisciQuesitoRequest request) throws CreazioneQuesitoException {
		InserisciDomandaCommand domandaCommand = new InserisciDomandaCommand(
				request.getDomandaRequest().getTestoDomanda(), request.getDomandaRequest().getCategoria(),
				request.getDomandaRequest().getUrlImmagne(), request.getDomandaRequest().getUrlDocumentazione());
		List<InserisciRispostaCommand> rispostaCommands = new ArrayList<>();
		for (InserisciRispostaRequest requestRisposta : request.getRispostaRequests()) {
			rispostaCommands.add(new InserisciRispostaCommand(requestRisposta.getTestoRisposta(),
					requestRisposta.isRispostaGiusta()));

		}
		InserisciRispostaCommand[] arrayRispostaRequests = rispostaCommands.toArray(new InserisciRispostaCommand[0]);
		InserisciQuesitoCommand quesitoCommand = new InserisciQuesitoCommand(domandaCommand, arrayRispostaRequests,
				getMassimoDiDifficlta());
		service.inserisci(quesitoCommand);
	}

	public void cancellaQuesito(CancellaQuesitoRequest request) throws CodiceInvalidoException {
		CancellaQuesitoCommand command = new CancellaQuesitoCommand(request.getCodiceQuesito());
		service.cancellaQuesito(command);
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

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoRequest recuperaQuesitoRequest) throws QuesitoNonTrovatoException {
		RecuperaQuesitoQuery command = new RecuperaQuesitoQuery(recuperaQuesitoRequest.getCodiceQuesitoRicercato());
		return service.getQuesitoPerRichiestaModifica(command);
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaRequest request) throws QuesitoNonTrovatoException {
		ModificaDifficoltaCommand command = new ModificaDifficoltaCommand(request.getTestoQuesito(),
				request.getLivelloDifficolta());
		QuesitoView quesitoModificato = service.modificaDifficolta(command);
		return quesitoModificato;
	}

	public QuesitoView modificaRisposte(ModificaRisposteRequest request) throws QuesitoNonTrovatoException {
		ModificaRisposteCommand command = new ModificaRisposteCommand(request.getNuoviTestiRisposte(),
				request.getIndiceRispostaGiusta(), request.getCodiceQuesito());

		return service.modificaRisposte(command);
	}

	public QuesitoView modificaDomanda(ModificaDomandaRequest request) throws QuesitoNonTrovatoException {

		ModificaDomandaCommand command = new ModificaDomandaCommand(request.getCodiceQuesito(),
				request.getTestoDomanda(), request.getCategoria(), request.getUrlImmagine(),
				request.getUrlDocumentazione());
		return service.modificaDomanda(command);
	}

}
