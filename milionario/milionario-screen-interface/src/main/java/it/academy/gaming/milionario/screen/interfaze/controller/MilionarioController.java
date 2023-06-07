package it.academy.gaming.milionario.screen.interfaze.controller;

import java.util.Collection;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.core.application.views.PartitaGiocataView;
import it.academy.gaming.milionario.screen.interfaze.view.screens.ClassificaScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.MenuScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.PartitaTerminataScreen;

public class MilionarioController {

	private MilionarioService service;

	private MenuScreen menuScreen = new MenuScreen(this);
	private ClassificaScreen classificaScreen = new ClassificaScreen(this);
	private PartitaTerminataScreen partitaTerminataScreen= new PartitaTerminataScreen(this);

	public MilionarioController(MilionarioService service) {
		this.service = service;
	}

	public void showMenu() {
		menuScreen.show();
	}

	public void showInizioPartita() {
	}

	public void showClassifica() {
		classificaScreen.show();
	}

	public void showPartita() {
	}

	public void showPartitaTerminata() {
		partitaTerminataScreen.show();
	}

	public String getNomeGiocatore() {
		return service.getNomeGiocatore();
	}

	public int getEuroRimanenti() {
		return service.getEuroRimanenti();
	}

	public Collection<PartitaGiocataView> getPartiteGiocate() {

		return service.getPartiteGiocate();
	}

}