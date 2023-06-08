package it.academy.gaming.milionario.screen.interfaze.controller;

import java.util.Collection;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.core.application.commands.IndovinaCommand;
import it.academy.gaming.milionario.core.application.commands.IniziaPartitaCommand;
import it.academy.gaming.milionario.core.application.views.PartitaGiocataView;
import it.academy.gaming.milionario.core.application.views.PartitaView;
import it.academy.gaming.milionario.core.application.views.SuggerimentoView;
import it.academy.gaming.milionario.core.application.views.VotazioneView;
import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;
import it.academy.gaming.milionario.core.domain.exceptions.NomeNonValidoException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;
import it.academy.gaming.milionario.screen.interfaze.view.requests.IndovinaRequest;
import it.academy.gaming.milionario.screen.interfaze.view.requests.IniziaPartitaRequest;
import it.academy.gaming.milionario.screen.interfaze.view.screens.ClassificaScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.IniziaPartitaScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.MenuScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.PartitaScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.PartitaTerminataScreen;

public class MilionarioController {

	private MilionarioService service;

	private MenuScreen menuScreen = new MenuScreen(this);
	private IniziaPartitaScreen iniziaPartitaScreen = new IniziaPartitaScreen(this);
	private ClassificaScreen classificaScreen = new ClassificaScreen(this);
	private PartitaTerminataScreen partitaTerminataScreen = new PartitaTerminataScreen(this);
	private PartitaScreen partitaScreen = new PartitaScreen(this);

	public MilionarioController(MilionarioService service) {
		this.service = service;
	}

	public void showMenu() {
		menuScreen.show();
	}

	public void showInizioPartita() {
		iniziaPartitaScreen.show();
	}

	public void showClassifica() {
		classificaScreen.show();
	}

	public void showPartita() {
		partitaScreen.show();
	}

	public void showPartitaTerminata() {
		partitaTerminataScreen.show();
	}

	public String getNomeGiocatore() {
		return service.getNomeGiocatore();
	}

	public int getEuroAssicurati() {
		return service.getEuroRimanenti();
	}

	public Collection<PartitaGiocataView> getPartiteGiocate() {

		return service.getPartiteGiocate();
	}

	public void iniziaPartita(IniziaPartitaRequest iniziaPartitaRequest)
			throws PartitaException, NomeNonValidoException {
		IniziaPartitaCommand iniziaPartitaCommand = new IniziaPartitaCommand(iniziaPartitaRequest.getNome());
		service.iniziaPartita(iniziaPartitaCommand);

		showPartita();

	}

	public PartitaView getPartita() {
		return service.getPartita();
	}

	public boolean indovina(IndovinaRequest request) throws PartitaException {
		return service.indovina(new IndovinaCommand(request.getLetteraRisposta()));

	}

	public void continua() throws PartitaException {
		service.continua();
	}

	public void ritirati() throws PartitaException {
		service.ritirati();
		showMenu();
	}

	public void usaAiutoComputer() throws AiutoNonDisponibileException {
		service.usaAiutoComputer();
	}

	public SuggerimentoView usaAiutoCasa() throws AiutoNonDisponibileException {
		return service.usaAiutoCasa();
	}

	public VotazioneView usaAiutoPubblico() throws AiutoNonDisponibileException {
		return service.usaAiutoPubblico();
	}

	public int getValore() {
		return service.getValore();
	}
}