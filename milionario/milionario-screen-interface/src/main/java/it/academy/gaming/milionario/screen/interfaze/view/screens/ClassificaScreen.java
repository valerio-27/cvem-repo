package it.academy.gaming.milionario.screen.interfaze.view.screens;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.core.application.views.PartitaGiocataView;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;

public class ClassificaScreen extends Screen {

	private MilionarioController controller;

	private final static String OPZIONE_MENU = "M";

	public ClassificaScreen(MilionarioController controller) {
		super("Classifica");
		this.controller = controller;
	}

	public void show() {
		showTitolo();

		Collection<PartitaGiocataView> partiteGiocate = controller.getPartiteGiocate();
		Collections.sort((List<PartitaGiocataView>) partiteGiocate);
		for (PartitaGiocataView partitaGiocataView : partiteGiocate) {
			info(partitaGiocataView.toString());
		}

		info(OPZIONE_MENU + ")enu");

		String scelta = SceltaUtente.scegli(new OpzioneStringa(OPZIONE_MENU));

		switch (scelta) {
		case OPZIONE_MENU:
			controller.showMenu();
			break;

		}
	}

}
