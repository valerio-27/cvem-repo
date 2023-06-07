package it.academy.gaming.milionario.screen.interfaze.controller;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.core.application.commands.IniziaPartitaCommand;
import it.academy.gaming.milionario.core.domain.exceptions.NomeNonValidoException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;
import it.academy.gaming.milionario.screen.interfaze.view.requests.IniziaPartitaRequest;
import it.academy.gaming.milionario.screen.interfaze.view.screens.IniziaPartitaScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.MenuScreen;

public class MilionarioController {

	private MilionarioService service;

	private MenuScreen menuScreen = new MenuScreen(this);
	private IniziaPartitaScreen iniziaPartitaScreen = new IniziaPartitaScreen(this);

	public MilionarioController() {

	}

	public void showMenu() {
		menuScreen.show();
	}

	public void showInizioPartita() {
		iniziaPartitaScreen.show();
	}

	public void showClassifica() {
	}

	public void showPartita() {
	}

	public void showPartitaTerminata() {
	}

	public void iniziaPartita(IniziaPartitaRequest iniziaPartitaRequest)
			throws PartitaException, NomeNonValidoException {
		IniziaPartitaCommand iniziaPartitaCommand = new IniziaPartitaCommand(iniziaPartitaRequest.getNome());
		service.iniziaPartita(iniziaPartitaCommand);

		showPartita();

	}

}