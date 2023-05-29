package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class MenuScreen extends Screen {
	private static final String OPZIONE_INSERISCI_QUESITO = "I";
	private static final String OPZIONE_MODIFICA_QUESITO = "M";
	private static final String OPZIONE_CANCELLA_QUESITO = "C";
	private static final String OPZIONE_RICERCA_QUESITO = "R";
	private static final String OPZIONE_USCITA = "U";

	public MenuScreen(CvemController controller) {
		super("MENU", controller);
	}

	public void show() {
		super.showTitle();
		mostraInfo("Scegli l'operazione che vuoi fare");
		mostraInfo("I)nserisci quesito");
		mostraInfo("M)odifica quesito");
		mostraInfo("C)ancella quesito");
		mostraInfo("R)icerca");
		mostraInfo("U)scita");
		String scelta = scanner.next();
		try {
			switch (scelta.toUpperCase()) {
			case OPZIONE_INSERISCI_QUESITO:
				controller.showInserisciQuesitoScreen();
				break;
			case OPZIONE_MODIFICA_QUESITO:
				controller.showModificaQuesitoScreen();
				break;
			case OPZIONE_CANCELLA_QUESITO:
				controller.showCancellazioneQuesitoScreen();
				break;
			case OPZIONE_RICERCA_QUESITO:
				controller.showRicercaQuesitoScreen();
				break;
			case OPZIONE_USCITA:
				System.exit(0);
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
