package it.academy.gaming.milionario.manager.grafics.controller;

import java.util.ArrayList;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.manager.core.application.CvemService;
import it.academy.gaming.milionario.manager.core.commands.CancellaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciDomandaCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.InserisciRispostaCommand;
import it.academy.gaming.milionario.manager.core.commands.ModificaDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.commands.RecuperaQuesitoCommand;
import it.academy.gaming.milionario.manager.core.commands.RicercaQuesitoPerCategoriaCommand;
import it.academy.gaming.milionario.manager.core.commands.RicercaQuesitoPerDifficoltaCommand;
import it.academy.gaming.milionario.manager.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.grafics.requests.CancellaQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
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

	public void cancellaQuesito(CancellaQuesitoRequest request) {
		CancellaQuesitoCommand command = new CancellaQuesitoCommand(request.getTestoQuesito());
		service.cancellaQuesito(command);
	}

	public void cercaPerDifficolta(RicercaQuesitoPerDifficoltaRequest requestPerDifficolta) {
		RicercaQuesitoPerDifficoltaCommand command = new RicercaQuesitoPerDifficoltaCommand(
				requestPerDifficolta.getLivelloDifficolta());

		List<QuesitoView> quesitiView = service.cercaPerLivelloDifficolta(command);
		risultatoRicercaScreen.show(quesitiView);

	}

	public void cercaPerCategoria(RicercaQuesitoPerCategoriaRequest requestPerCategoria) {
		RicercaQuesitoPerCategoriaCommand command = new RicercaQuesitoPerCategoriaCommand(
				requestPerCategoria.getCategoriaRicercata());

		List<QuesitoView> quesitiView = service.cercaPerCategoria(command);
		risultatoRicercaScreen.show(quesitiView);
	}

	public QuesitoView getQuesitoPerRichiestaModifica(RecuperaQuesitoRequest recuperaQuesitoRequest) {
		RecuperaQuesitoCommand command = new RecuperaQuesitoCommand(recuperaQuesitoRequest.getTestoQuesitoRicercato());
		return service.getQuesitoPerRichiestaModifica(command);
	}

	public QuesitoView modificaDifficolta(ModificaDifficoltaRequest request) {
		ModificaDifficoltaCommand command=new ModificaDifficoltaCommand(request.getTestoQuesito(), request.getLivelloDifficolta());
		QuesitoView quesitoModificato=service.modificaDifficolta(command);
		return quesitoModificato;
	}

}
