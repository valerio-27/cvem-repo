package it.academy.gaming.milionario.screen.interfaze.view.screens;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.screen.interfaze.TestoUtil;
import it.academy.gaming.milionario.screen.interfaze.TestoUtil.Colore;
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

		int valore = controller.getValore();

		if (valore != 1000000) {
			info("Risposta sbagliata, quella corretta era: "
					+ TestoUtil.colora(controller.getTestoRispostaCorretta(), Colore.VERDE));
		}

		info(controller.getNomeGiocatore() + " si è portato a casa " + valore + "€");

		info(OPZIONE_MENU + ")enu");

		SceltaUtente.scegli(new OpzioneStringa(OPZIONE_MENU));
		controller.showMenu();
	}
}
