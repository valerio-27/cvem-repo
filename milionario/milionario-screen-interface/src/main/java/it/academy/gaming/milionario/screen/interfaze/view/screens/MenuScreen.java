package it.academy.gaming.milionario.screen.interfaze.view.screens;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;

public class MenuScreen extends Screen{
	
	
	private MilionarioController controller;

	private final static String OPZIONE_INZIA = "I";
	private final static String OPZIONE_CLASSIFICA = "C";
	private final static String OPZIONE_ESCI = "E";

	public MenuScreen(MilionarioController controller) {
		super("Menu");
		this.controller = controller;
	}

	public void show() {
		showTitolo();

		info(OPZIONE_INZIA + ")nizia");
		info(OPZIONE_CLASSIFICA + ")lassifica");
		info(OPZIONE_ESCI + ")sci");

		String scelta = SceltaUtente.scegli(new OpzioneStringa(OPZIONE_INZIA), new OpzioneStringa(OPZIONE_CLASSIFICA),
				new OpzioneStringa(OPZIONE_ESCI));

		switch (scelta) {
		case OPZIONE_INZIA:
			controller.showInizioPartita();
			break;
		case OPZIONE_CLASSIFICA:
			controller.showClassifica();
			break;
		case OPZIONE_ESCI:
			System.exit(0);
		}

	}

}
