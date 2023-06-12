package it.academy.gaming.milionario.screen.interfaze.view.screens;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.core.domain.exceptions.NomeNonValidoException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;
import it.academy.gaming.milionario.screen.interfaze.InputMask;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;
import it.academy.gaming.milionario.screen.interfaze.view.requests.IniziaPartitaRequest;

public class IniziaPartitaScreen extends Screen {

	private MilionarioController controller;

	private final static String OPZIONE_GIOCA = "G";
	private final static String OPZIONE_MENU = "M";

	public IniziaPartitaScreen(MilionarioController controller) {
		super("Inizia Partita");
		this.controller = controller;
	}

	public void show() {
		showTitolo();

		info("Benvenuto nel gioco del 'Chi Vuol Essere Milionario?'");
		info("\n");
		info(OPZIONE_GIOCA + ")ioca");
		info(OPZIONE_MENU + ")enu");
		String scelta = SceltaUtente.scegli(new OpzioneStringa(OPZIONE_GIOCA), new OpzioneStringa(OPZIONE_MENU));

		switch (scelta) {
		case OPZIONE_GIOCA:
			info("Per iniziare, inserisci il tuo nome:");
			String nome = InputMask.inserisciNomeGiocatore();

			IniziaPartitaRequest iniziaPartitaRequest = new IniziaPartitaRequest(nome);
			try {
				controller.iniziaPartita(iniziaPartitaRequest);
			} catch (PartitaException | NomeNonValidoException e) {
				info(e.getMessage());
				show();
			}
			break;
		case OPZIONE_MENU:
			controller.showMenu();
			break;

		}
	}
}
