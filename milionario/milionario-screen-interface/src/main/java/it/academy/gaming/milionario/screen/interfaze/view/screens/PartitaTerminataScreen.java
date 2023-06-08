package it.academy.gaming.milionario.screen.interfaze.view.screens;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;

public class PartitaTerminataScreen extends Screen {

	private MilionarioController controller;

	private final static String OPZIONE_MENU = "M";

	public PartitaTerminataScreen(MilionarioController controller) {
		super("PartitaTerminata");
		this.controller = controller;
	}

	public void show() {
		showTitolo();

		info(controller.getNomeGiocatore() + " si è portato a casa " + controller.getValore() + "€");

		info(OPZIONE_MENU + ")enu");

		SceltaUtente.scegli(new OpzioneStringa(OPZIONE_MENU));
		controller.showMenu();
	}
}
