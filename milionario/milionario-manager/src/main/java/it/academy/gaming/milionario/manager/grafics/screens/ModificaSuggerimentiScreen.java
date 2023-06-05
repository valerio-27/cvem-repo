package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaSuggerimentiScreen extends Screen {

	public ModificaSuggerimentiScreen(CvemController controller) {
		super("MODIFICA DOMANDA", controller);
	}

	private static final String OPZIONE_INDIETRO = "I";
	private static final String OPZIONE_CONFERMA = "C";

	public void show() {
		mostraInfo("Inserisci il codice del quesito che vuoi modificare");
		String codiceQuesito = scanner.next();
		scanner.nextLine();
		RecuperaQuesitoRequest recuperaQuesitoRequest = new RecuperaQuesitoRequest(codiceQuesito);

	}

}
