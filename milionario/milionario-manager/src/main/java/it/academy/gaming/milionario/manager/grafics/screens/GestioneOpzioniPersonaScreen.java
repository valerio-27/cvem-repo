package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.core.views.OpzioniPersonaView;
import it.academy.gaming.milionario.core.views.PercentualeFortunaView;
import it.academy.gaming.milionario.core.views.RangeCulturaGeneraleView;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.OpzioniNonValideException;
import it.academy.gaming.milionario.manager.grafics.requests.SalvaOpzioniPersonaRequest;

public class GestioneOpzioniPersonaScreen extends Screen {
	private static final String OPZIONE_MASSIMA_CONOSCCENZA = "MA";
	private static final String OPZIONE_MINIMA_CONOSCENZA = "MI";
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
		int maxConoscenza = opzioniPersona.getMaxConoscenza();
		int minConoscenza = opzioniPersona.getMinConoscenza();
		int percentualeFortuna = opzioniPersona.getPercentualeFortuna();

		mostraInfo("Massima conoscenza attuale = " + maxConoscenza);
		mostraInfo("Minima conoscenza attuale= " + minConoscenza);
		mostraInfo("Mercentuale fortuna attuale= " + percentualeFortuna);
		mostraInfo("Cosa vuoi modificare?");
		mostraInfo("Ma)ssimo della conoscenza");
		mostraInfo("Mi)nimo della conoscenza");
		mostraInfo("P)ercentuale di fortuna");

		String scelta = scanner.next();
		scanner.nextLine();
		try {

			switch (scelta.toUpperCase()) {
			case OPZIONE_MASSIMA_CONOSCCENZA:
				maxConoscenza = modificaMassimaConoscenza();
				break;
			case OPZIONE_MINIMA_CONOSCENZA:
				minConoscenza = modificaMinimaConoscenza();
				break;
			case OPZIONE_PERCENTUALE_FORTUNA:
				percentualeFortuna = modificaPercentualeFortuna();
				break;
			case OPZIONE_SALVA:
				SalvaOpzioniPersonaRequest request = new SalvaOpzioniPersonaRequest(maxConoscenza, minConoscenza,
						percentualeFortuna);
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
				throw OpzioniNonValideException.percentualeFortunaNonValida();
			}
		} catch (OpzioniNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaPercentualeFortuna();
		}

		return percentualeFortuna;
	}

	private int modificaMinimaConoscenza() {
		RangeCulturaGeneraleView rangeConoscenza = controller.getRangeCulturaGenerale();
		mostraInfo("Limite di minima conoscenza= " + rangeConoscenza.getMinimoConoscenza());
		mostraInfo("Inserisci il minimo della conoscenza");
		int minimaConoscenza = scanner.nextInt();
		scanner.nextLine();

		try {
			if (minimaConoscenza < rangeConoscenza.getMinimoConoscenza()
					|| minimaConoscenza >= rangeConoscenza.getMassimoConoscenza()) {
				throw OpzioniNonValideException.conoscenzaMinimaNonValida();
			}
		} catch (OpzioniNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaMinimaConoscenza();
		}

		return minimaConoscenza;
	}

	private int modificaMassimaConoscenza() {
		RangeCulturaGeneraleView rangeConoscenza = controller.getRangeCulturaGenerale();
		mostraInfo("Limite di massima conoscenza= " + rangeConoscenza.getMassimoConoscenza());
		mostraInfo("Inserisci il massimo della conoscenza");
		int massimaConoscenza = scanner.nextInt();
		scanner.nextLine();

		try {
			if (massimaConoscenza > rangeConoscenza.getMassimoConoscenza()
					|| massimaConoscenza <= rangeConoscenza.getMinimoConoscenza()) {
				throw OpzioniNonValideException.conoscenzaMassimaNonValida();
			}

		} catch (OpzioniNonValideException e) {
			mostraInfo(e.getMessage());
			return modificaMassimaConoscenza();
		}
		return massimaConoscenza;
	}

}
