package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.requests.CancellaQuesitoRequest;

public class CancellazioneQuesitoScreen extends Screen {
	private static final String OPZIONE_CANCELLA_QUESITO = "C";
	private static final String OPZIONE_MENU = "M";

	public CancellazioneQuesitoScreen(CvemController controller) {
		super("CANCELLAZIONE QUESITO", controller);
	}

	public void show() {
		mostraInfo("Inserisci il testo del quesito che vuoi cancellare");
		String testoQuisito = scanner.nextLine();
		mostraInfo("C)ancella");
		mostraInfo("M)enu");
		String scelta = scanner.next();
		try {
			switch (scelta) {
			case OPZIONE_CANCELLA_QUESITO:
				CancellaQuesitoRequest request = new CancellaQuesitoRequest(testoQuisito);
				controller.cancellaQuesito(request);
				break;
			case OPZIONE_MENU:
				controller.showMenuScreen();
			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

}
