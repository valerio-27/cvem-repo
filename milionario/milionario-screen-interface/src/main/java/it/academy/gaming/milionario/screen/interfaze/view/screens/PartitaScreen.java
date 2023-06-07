package it.academy.gaming.milionario.screen.interfaze.view.screens;

import java.util.ArrayList;
import java.util.List;

import it.academy.games.interface_common.OpzioneStringa;
import it.academy.games.interface_common.SceltaUtente;
import it.academy.games.interface_common.Screen;
import it.academy.gaming.milionario.core.application.views.DomandaView;
import it.academy.gaming.milionario.core.application.views.PartitaView;
import it.academy.gaming.milionario.core.application.views.PercentualeRispostaView;
import it.academy.gaming.milionario.core.application.views.RispostaView;
import it.academy.gaming.milionario.core.application.views.SuggerimentoView;
import it.academy.gaming.milionario.core.application.views.VotazioneView;
import it.academy.gaming.milionario.core.domain.LetteraRisposta;
import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;
import it.academy.gaming.milionario.screen.interfaze.TestoUtil;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;
import it.academy.gaming.milionario.screen.interfaze.view.requests.IndovinaRequest;

public class PartitaScreen extends Screen {

	private MilionarioController controller;

	private final static String OPZIONE_A = "A";
	private final static String OPZIONE_B = "B";
	private final static String OPZIONE_C = "C";
	private final static String OPZIONE_D = "D";

	private final static String OPZIONE_AIUTO_COMPUTER = "1";
	private final static String OPZIONE_AIUTO_CASA = "2";
	private final static String OPZIONE_AIUTO_PUBBLICO = "3";

	private final static String OPZIONE_CONTINUA = "CO";
	private final static String OPZIONE_RITIRATI = "R";

	public PartitaScreen(MilionarioController controller) {
		super("Partita");
		this.controller = controller;
	}

	private OpzioneStringa[] generaOpzioni(PartitaView partitaView) {
		List<OpzioneStringa> opzioni = new ArrayList<>();
		for (RispostaView rispostaView : partitaView.getRisposteView()) {
			opzioni.add(new OpzioneStringa(rispostaView.getLettera().toString()));
		}
		if (partitaView.isAiutoComputerDisponibile()) {
			opzioni.add(new OpzioneStringa(OPZIONE_AIUTO_COMPUTER));
		}
		if (partitaView.isAiutoCasaDisponibile()) {
			opzioni.add(new OpzioneStringa(OPZIONE_AIUTO_CASA));
		}
		if (partitaView.isAiutoPubblicoDisponibile()) {
			opzioni.add(new OpzioneStringa(OPZIONE_AIUTO_PUBBLICO));
		}

		return opzioni.toArray(new OpzioneStringa[0]);
	}

	private void showContinua() {
		PartitaView partitaView = controller.getPartita();
		info("\nDomanda dal valore di: " + partitaView.getEuro() + "€\n");

		DomandaView domandaView = partitaView.getDomandaView();
		TestoUtil.cadenzaPerCarattere(domandaView.getTesto());

		RispostaView[] risposte = partitaView.getRisposteView();

		String risposteString = "\n";

		boolean aiutoCasaUsato = risposte.length == 2;

		if (aiutoCasaUsato) {
			risposteString += risposte[0].getLettera() + "): " + risposte[0].getTesto() + "			"
					+ risposte[1].getLettera() + "): " + risposte[1].getTesto();
		} else {
			risposteString += risposte[0].getLettera() + "): " + risposte[0].getTesto() + "			"
					+ risposte[1].getLettera() + "): " + risposte[1].getTesto() + "\n" + risposte[2].getLettera()
					+ "): " + risposte[2].getTesto() + "			" + risposte[3].getLettera() + "): "
					+ risposte[3].getTesto();

		}

		TestoUtil.cadenzaPerCarattere(risposteString);

		String aiuti = "\n\n" + checkAiuto(partitaView.isAiutoComputerDisponibile(), "1) [50:50]   ");
		aiuti += checkAiuto(partitaView.isAiutoCasaDisponibile(), "2) [Telefona casa]   ");
		aiuti += checkAiuto(partitaView.isAiutoPubblicoDisponibile(), "3) [Aiuto pubblico]");

		info(aiuti);

		String scelta = SceltaUtente.scegli(generaOpzioni(partitaView));

		try {
			switch (scelta) {
			case OPZIONE_A:
			case OPZIONE_B:
			case OPZIONE_C:
			case OPZIONE_D:

				IndovinaRequest request = new IndovinaRequest(LetteraRisposta.valueOf(scelta));

				if (!controller.indovina(request)) {
					info("\nhai raggiunto il premio di: " + partitaView.getEuro() + "€");
					info("disponi di una vincita assicurata di : " + controller.getEuroAssicurati() + "€");

					info("Cosa vuoi fare?\n");

					info(OPZIONE_CONTINUA + ")ntinua");
					info(OPZIONE_RITIRATI + ")itirati");

					scelta = SceltaUtente.scegli(new OpzioneStringa(OPZIONE_CONTINUA),
							new OpzioneStringa(OPZIONE_RITIRATI));

					switch (scelta) {
					case OPZIONE_CONTINUA:
						controller.continua();
						showContinua();
						break;
					case OPZIONE_RITIRATI:
						controller.ritirati();
						break;
					}
				} else {
					controller.showPartitaTerminata();
				}
				break;
			case OPZIONE_AIUTO_COMPUTER:
				controller.usaAiutoComputer();
				showContinua();
				break;
			case OPZIONE_AIUTO_CASA:
				SuggerimentoView suggerimentoView = controller.usaAiutoCasa();
				TestoUtil.cadenzaFrase(suggerimentoView.getTesto(), suggerimentoView.getTempoEsposizione());
				info("");
				showContinua();
				break;
			case OPZIONE_AIUTO_PUBBLICO:
				VotazioneView votazioneView = controller.usaAiutoPubblico();
				PercentualeRispostaView[] percentualiRispostaView = votazioneView.getPercentualiRispostaView();

				String votazione = percentualiRispostaView[0].toString() + "	"
						+ percentualiRispostaView[1].toString() + "\n" + percentualiRispostaView[2].toString() + "	"
						+ percentualiRispostaView[3].toString();
				info(votazione);
				showContinua();
				break;
			}
		} catch (PartitaException | AiutoNonDisponibileException ignored) {
		}

	}

	public void show() {
		showTitolo();
		showContinua();
	}

	private String checkAiuto(boolean isAiutoDisponibile, String showAiuto) {
		return ((isAiutoDisponibile) ? showAiuto : TestoUtil.coloraRosso(showAiuto));
	}
}
