package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.List;

import it.academy.gaming.milionario.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class RisultatoRicercaScreen extends Screen {

	public RisultatoRicercaScreen(CvemController controller) {
		super("RISULTATO RICERCA", controller);
	}

	public void show(List<QuesitoView> quesitiView) {
		super.showTitle();
		mostraInfo("Questo e' il risultato della tua ricerca:");

		if (quesitiView.isEmpty()) {
			mostraInfo("Non ci sono quesiti che soddisfino la tua ricerca");

		} else {
			for (QuesitoView quesitoView : quesitiView) {
				mostraInfo(quesitoView.toString());
			}
		}
		controller.showMenuScreen();

	}

}
