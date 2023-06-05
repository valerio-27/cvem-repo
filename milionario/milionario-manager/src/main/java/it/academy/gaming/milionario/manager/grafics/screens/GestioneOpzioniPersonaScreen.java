package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.core.views.OpzioniPersonaView;
import it.academy.gaming.milionario.core.views.PercentualeFortunaView;
import it.academy.gaming.milionario.core.views.RangeConoscenzaView;
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
		 * nel caso della prima chiamata di questo metodo i campi saranno tutti pari a 0
		 * ossia non impostati
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
				verificaValiditaOpzioni(maxConoscenza, minConoscenza, percentualeFortuna);
				SalvaOpzioniPersonaRequest request = new SalvaOpzioniPersonaRequest(maxConoscenza, minConoscenza,
						percentualeFortuna);
				controller.salvaOpzioniPersona(request);
				break;
			case OPZIONE_ESCI_DALLE_MODIFICHE:
				/*
				 * per quando chiamo questo metodo devo fare un controllo perche la prima volta
				 * quando vengono inseriti i dati perche' non devo permettere che rimangano non
				 * valorizzati
				 */
				verificaValiditaOpzioni(maxConoscenza, minConoscenza, percentualeFortuna);
				controller.showMenuScreen();

				break;
			default:
				throw new IllegalArgumentException("Opzione non valida");
			}
		} catch (OpzioniNonValideException e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private int modificaPercentualeFortuna() {
		PercentualeFortunaView percentualeFortunaView = controller.getPercentualeFortuna();
		mostraInfo("Limite di percentuale minima= " + percentualeFortunaView.getMinimoPercentuale());
		mostraInfo("Limite di percentuale massima= " + percentualeFortunaView.getMassimoPercentuale());
		mostraInfo("Inserisci il valore della percentuale di fortuna");
		int minimoConoscenza = scanner.nextInt();
		return minimoConoscenza;
	}

	private int modificaMinimaConoscenza() {
		RangeConoscenzaView rangeConoscenza = controller.getRangeConoscenza();
		mostraInfo("Limite di minima conoscenza= " + rangeConoscenza.getMinimoConoscenza());
		mostraInfo("Inserisci il minimo della conoscenza");
		int minimoConoscenza = scanner.nextInt();

		return minimoConoscenza;
	}

	private int modificaMassimaConoscenza() {
		RangeConoscenzaView rangeConoscenza = controller.getRangeConoscenza();
		mostraInfo("Limite di massima conoscenza= " + rangeConoscenza.getMassimoConoscenza());
		mostraInfo("Inserisci il massimo della conoscenza");
		int massimaConoscenza = scanner.nextInt();
		return massimaConoscenza;
	}

	private void verificaValiditaOpzioni(int massimaConoscenza, int minimaConoscenza, int percentualeFortuna)
			throws OpzioniNonValideException {
		RangeConoscenzaView rangeConoscenza = controller.getRangeConoscenza();
		PercentualeFortunaView percentualeFortunaView = controller.getPercentualeFortuna();

		if (minimaConoscenza < rangeConoscenza.getMinimoConoscenza() || minimaConoscenza >= massimaConoscenza) {
			throw OpzioniNonValideException.conoscenzaMinimaNonValida();
		}
		if (massimaConoscenza > rangeConoscenza.getMassimoConoscenza() || massimaConoscenza <= minimaConoscenza) {
			throw OpzioniNonValideException.conoscenzaMassimaNonValida();
		}
		if (percentualeFortuna < percentualeFortunaView.getMinimoPercentuale()
				|| percentualeFortuna > percentualeFortunaView.getMassimoPercentuale()) {
			throw OpzioniNonValideException.percentualeFortunaNonValida();
		}

	}

}
