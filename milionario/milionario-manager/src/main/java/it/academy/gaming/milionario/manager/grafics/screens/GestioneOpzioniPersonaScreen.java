package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.manager.core.application.view.OpzioniPersonaView;
import it.academy.gaming.milionario.manager.core.application.view.PercentualeFortunaView;
import it.academy.gaming.milionario.manager.core.application.view.RangeCulturaGeneraleView;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.OpzioniPersonaNonValideException;
import it.academy.gaming.milionario.manager.grafics.requests.SalvaOpzioniPersonaRequest;

public class GestioneOpzioniPersonaScreen extends Screen {
	private static final String OPZIONE_MASSIMA_CULTURA_GENERALE = "MA";
	private static final String OPZIONE_MINIMA_CULTURA_GENERALE = "MI";
	private static final String OPZIONE_PERCENTUALE_FORTUNA = "P";
	private static final String OPZIONE_SALVA = "S";
	private static final String OPZIONE_ESCI_DALLE_MODIFICHE = "E";

	public GestioneOpzioniPersonaScreen(CvemController controller) {
		super("INSERISCI OPZIONI PERSONA", controller);
	}

	public void show() {
		super.showTitle();
		// TODO
		OpzioniPersonaView opzioniPersona = controller.getOpzioniPersona();
		/*
		 * nel caso della prima chiamata di questo metodo avro le impostazioni di
		 * default
		 */
		int maxCulturaGenerale = opzioniPersona.getMaxCulturaGenerale();
		int minCulturaGenerale = opzioniPersona.getMinCulturaGenerale();
		int percentualeFortuna = opzioniPersona.getPercentualeFortuna();

		mostraInfo("Livello massimo di cultura generale attuale = " + maxCulturaGenerale);
		mostraInfo("Livello minimo di cultura generale attuale= " + minCulturaGenerale);
		mostraInfo("Percentuale fortuna attuale= " + percentualeFortuna);
		mostraInfo("Cosa vuoi modificare?");
		mostraInfo("Ma)ssimo della cultura");
		mostraInfo("Mi)nimo della cultura");
		mostraInfo("P)ercentuale di fortuna");

		String scelta = scanner.next();
		scanner.nextLine();
		try {

			switch (scelta.toUpperCase()) {
			case OPZIONE_MASSIMA_CULTURA_GENERALE:
				maxCulturaGenerale = modificaMassimoCulturaGenerale();
				break;
			case OPZIONE_MINIMA_CULTURA_GENERALE:
				minCulturaGenerale = modificaMinimoCulturaGenerale();
				break;
			case OPZIONE_PERCENTUALE_FORTUNA:
				percentualeFortuna = modificaPercentualeFortuna();
				break;
			case OPZIONE_SALVA:
				SalvaOpzioniPersonaRequest request = new SalvaOpzioniPersonaRequest(maxCulturaGenerale,
						minCulturaGenerale, percentualeFortuna);
				controller.salvaOpzioniPersona(request);
				break;
			case OPZIONE_ESCI_DALLE_MODIFICHE:

				controller.showMenuScreen();

				break;
			default:
				throw new IllegalArgumentException("Opzione non valida");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private int modificaPercentualeFortuna() {
		PercentualeFortunaView percentualeFortunaView = controller.getPercentualeFortuna();
		mostraInfo("Limite di percentuale minima= " + percentualeFortunaView.getMinimoPercentuale());
		mostraInfo("Limite di percentuale massima= " + percentualeFortunaView.getMassimoPercentuale());
		mostraInfo("Inserisci il valore della percentuale di fortuna");

		int percentualeFortuna = scanner.nextInt();
		scanner.nextLine();
		try {
			if (percentualeFortuna < percentualeFortunaView.getMinimoPercentuale()
					|| percentualeFortuna > percentualeFortunaView.getMassimoPercentuale()) {
				throw OpzioniPersonaNonValideException.percentualeFortunaNonValida();
			}
		} catch (OpzioniPersonaNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaPercentualeFortuna();
		}

		return percentualeFortuna;
	}

	private int modificaMinimoCulturaGenerale() {
		RangeCulturaGeneraleView rangeConoscenza = controller.getRangeCulturaGenerale();
		mostraInfo("Limite di minima conoscenza= " + rangeConoscenza.getMinimoConoscenza());
		mostraInfo("Inserisci il minimo della conoscenza");
		int minimaConoscenza = scanner.nextInt();
		scanner.nextLine();

		try {
			if (minimaConoscenza < rangeConoscenza.getMinimoConoscenza()
					|| minimaConoscenza >= rangeConoscenza.getMassimoConoscenza()) {
				throw OpzioniPersonaNonValideException.conoscenzaMinimaNonValida();
			}
		} catch (OpzioniPersonaNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaMinimoCulturaGenerale();
		}

		return minimaConoscenza;
	}

	private int modificaMassimoCulturaGenerale() {
		RangeCulturaGeneraleView rangeConoscenza = controller.getRangeCulturaGenerale();
		mostraInfo("Limite di massima conoscenza= " + rangeConoscenza.getMassimoConoscenza());
		mostraInfo("Inserisci il massimo della conoscenza");
		int massimaConoscenza = scanner.nextInt();
		scanner.nextLine();

		try {
			if (massimaConoscenza > rangeConoscenza.getMassimoConoscenza()
					|| massimaConoscenza <= rangeConoscenza.getMinimoConoscenza()) {
				throw OpzioniPersonaNonValideException.conoscenzaMassimaNonValida();
			}

		} catch (OpzioniPersonaNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaMassimoCulturaGenerale();
		}
		return massimaConoscenza;
	}

}
