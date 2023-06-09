package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.manager.core.application.view.QuesitoView;
import it.academy.gaming.milionario.manager.core.application.view.RispostaView;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRisposteRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaRisposteScreen extends Screen {

	public ModificaRisposteScreen(CvemController controller) {
		super("MODIFICA DOMANDA", controller);
	}

	private static final String OPZIONE_ESCI_DALLE_MODIFICHE = "E";
	private static final String OPZIONE_SALVA = "S";

	public void show() {
		super.showTitle();
		mostraInfo("Inserisci il codice del quesito che vuoi modificare");
		String codiceQuesito = scanner.next();
		scanner.nextLine();
		RecuperaQuesitoRequest recuperaQuesitoRequest = new RecuperaQuesitoRequest(codiceQuesito);
		QuesitoView quesitoRichiestoView = null;
		try {
			quesitoRichiestoView = controller.getQuesitoPerRichiestaModifica(recuperaQuesitoRequest);
		} catch (QuesitoNonTrovatoException | CodiceInvalidoException e) {
			mostraInfo(e.getMessage());
			show();
		}
		modificaRisposte(quesitoRichiestoView);

	}

	private void modificaRisposte(QuesitoView quesitoRichiestoView) {
		mostraInfo("Queste sono le risposte attuali:");
		List<RispostaView> risposteAttuali = quesitoRichiestoView.getRisposteView();

		/*
		 * mi prendo tutti i testi e li metto in una list di stringhe nello stesso
		 * ordine
		 */
		List<String> testiRisposteAttuali = new ArrayList<>();
		for (RispostaView rispostaView : risposteAttuali) {
			testiRisposteAttuali.add(rispostaView.getTesto());
		}
		/*
		 * mostro le info iniziali
		 */

		for (RispostaView rispostaView : risposteAttuali) {
			String rispostaGiusta = rispostaView.isGiusta() ? "SI" : "NO";
			mostraInfo(rispostaView.getTesto() + ", risposta giusta = " + rispostaGiusta);
		}

		while (true) {
			mostraInfo("Quale risposta vuoi modificare?");
			mostraInfo("1");
			mostraInfo("2");
			mostraInfo("3");
			mostraInfo("4");
			mostraInfo("E)sci dalle modifiche");
			mostraInfo("S)alva modifiche");

			String scelta = scanner.next();
			scanner.nextLine();
			if (scelta.equalsIgnoreCase(OPZIONE_ESCI_DALLE_MODIFICHE)) {
				/*
				 * ritorno al metodo che mi ha chiamato
				 */
				controller.showModificaQuesitoScreen();
			}
			if (scelta.equalsIgnoreCase(OPZIONE_SALVA)) {
				/*
				 * devo far vedere tutte le risposte e poi chiedere quale sia quella giusta
				 */
				int indiceRispostaGiusta = acquisisciIndiceRispostaGiusta(testiRisposteAttuali);

				/*
				 * creare 4 request modificaRisposta
				 */

				List<ModificaRispostaRequest> risposteRequest = new ArrayList<>();

				for (int i = 0; i < 4; i++) {
					boolean corretta = i == indiceRispostaGiusta - 1;
					risposteRequest.add(new ModificaRispostaRequest(testiRisposteAttuali.get(i), corretta));
				}

				ModificaRisposteRequest request = new ModificaRisposteRequest(quesitoRichiestoView.getCodice(),
						risposteRequest);

				try {
					controller.modificaRisposte(request);
					break;
				} catch (QuesitoNonTrovatoException | RisposteInvalideException | TestoRispostaAssenteException
						| CodiceInvalidoException e) {
					mostraInfo(e.getMessage());
					show();
				}

			}
			try {
				switch (Integer.parseInt(scelta)) {

				case 1:
					modificaRispostaIndicata(0, testiRisposteAttuali);
					break;
				case 2:
					modificaRispostaIndicata(1, testiRisposteAttuali);
					break;
				case 3:
					modificaRispostaIndicata(2, testiRisposteAttuali);
					break;
				case 4:
					modificaRispostaIndicata(3, testiRisposteAttuali);
					break;

				default:
					throw new IllegalArgumentException("Opzione inesistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());
				modificaRisposte(quesitoRichiestoView);
			}

		}

	}

	private int acquisisciIndiceRispostaGiusta(List<String> testiRisposteAttuali) {

		int indiceRisposta = 0;
		mostraInfo("Queste sono le risposte:");
		for (String string : testiRisposteAttuali) {
			mostraInfo(++indiceRisposta + ")" + string);
		}
		mostraInfo("Inserisci l'indice della risposta corretta");
		int indiceRispostaCorretta = scanner.nextInt();
		scanner.nextLine();
		try {
			if (indiceRispostaCorretta < 1 || indiceRispostaCorretta > 4) {
				throw new IllegalArgumentException("L'indice deve essere compreso tra 1 e 4 ");
			}

		} catch (Exception e) {
			mostraInfo(e.getMessage());
			acquisisciIndiceRispostaGiusta(testiRisposteAttuali);
		}

		return indiceRispostaCorretta;
	}

	private void modificaRispostaIndicata(int indiceRisposta, List<String> testiRisposteAttuali) {

		mostraInfo("Inserisci il testo sostitutivo");
		String scelta = scanner.nextLine();
		try {
			if (StringUtils.isBlank(scelta)) {
				throw new IllegalArgumentException("Il testo dellla risposta deve essere un alpha numerico ");
			}

			testiRisposteAttuali.set(indiceRisposta, scelta);

		} catch (Exception e) {
			mostraInfo(e.getMessage());
			modificaRispostaIndicata(indiceRisposta, testiRisposteAttuali);

		}
	}

}
