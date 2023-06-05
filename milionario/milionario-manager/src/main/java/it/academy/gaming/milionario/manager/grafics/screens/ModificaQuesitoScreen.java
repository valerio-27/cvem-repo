package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class ModificaQuesitoScreen extends Screen {
	private static final String OPZIONE_MODIFICA_DOMANDA = "D";
	private static final String OPZIONE_MODIFICA_RISPOSTE = "R";
	private static final String OPZIONE_MODIFICA_LIVELLO_DIFFICOLTA = "L";
	private static final String OPZIONE_MODIFICA_SUGGERIMENTI = "S";
	private static final String OPZIONE_MENU = "M";

	public ModificaQuesitoScreen(CvemController controller) {
		super("MODIFICA QUESITO", controller);
	}

	public void show() {
		super.showTitle();
		while (true) {
			mostraInfo("Cosa vuoi modificare?");
			mostraInfo("D)omanda");
			mostraInfo("R)isposte");
			mostraInfo("S)uggerimenti");
			mostraInfo("L)ivello di difficolta'");
			mostraInfo("M)enu");
			String scelta = scanner.next();
			scanner.nextLine();
			try {
				switch (scelta.toUpperCase()) {
				case OPZIONE_MODIFICA_DOMANDA:
					controller.modificaDomanda();
					break;
				case OPZIONE_MODIFICA_RISPOSTE:
					controller.modificaRisposte();
					break;
				case OPZIONE_MODIFICA_LIVELLO_DIFFICOLTA:
					controller.modificaDifficolta();
					break;
				case OPZIONE_MODIFICA_SUGGERIMENTI:
					controller.modificaSuggerimenti();
					break;
				case OPZIONE_MENU:
					controller.showMenuScreen();
					break;

				default:
					throw new IllegalArgumentException("Opzione insistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());
				show();
			}

		}
	}

}
