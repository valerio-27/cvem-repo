package it.academy.gaming.milionario.screen.interfaze.controller;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.screen.interfaze.view.screens.MenuScreen;
import it.academy.gaming.milionario.screen.interfaze.view.screens.PartitaTerminataScreen;

public class MilionarioController {

	private MilionarioService service;

	private MenuScreen menuScreen = new MenuScreen(this);
	private PartitaTerminataScreen partitaTerminataScreen= new PartitaTerminataScreen(this);

	public MilionarioController() {

	}

	public void showMenu() {
		menuScreen.show();
	}

	public void showInizioPartita() {
	}

	public void showClassifica() {
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

}