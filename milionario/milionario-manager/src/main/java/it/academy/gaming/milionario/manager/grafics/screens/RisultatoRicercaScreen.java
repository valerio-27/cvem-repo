package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.List;

import it.academy.gaming.milionario.manager.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class RisultatoRicercaScreen extends Screen {

	public RisultatoRicercaScreen(CvemController controller) {
		super("RISULTATO RICERCA", controller);
	}

	public void show(List<QuesitoView> quesitiView) {
		mostraInfo("Questo e' il risultato della tua ricerca:");

		for (QuesitoView quesitoView : quesitiView) {
			mostraInfo(quesitiView.toString());
		}
	}

}
