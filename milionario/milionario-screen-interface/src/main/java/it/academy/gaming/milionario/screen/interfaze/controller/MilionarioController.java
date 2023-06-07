package it.academy.gaming.milionario.screen.interfaze.controller;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.screen.interfaze.view.screens.MenuScreen;

public class MilionarioController {

	private MilionarioService service;

	private MenuScreen menuScreen = new MenuScreen(this);

	public MilionarioController() {

	}

	public void showMenu() {
		menuScreen.show();
	}

	public void showInizioPartita() {

	}

	public void showClassifica() {

	}

}