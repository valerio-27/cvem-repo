package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRipostaAssenteException;
import it.academy.gaming.milionario.core.views.QuesitoView;
import it.academy.gaming.milionario.core.views.RispostaView;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.grafics.RangeDifficolta;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDomandaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaRisposteRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaQuesitoScreen extends Screen {
	private static final String OPZIONE_MODIFICA_DOMANDA = "D";
	private static final String OPZIONE_MODIFICA_RISPOSTE = "R";
	private static final String OPZIONE_MODIFICA_LIVELLO_DIFFICOLTA = "L";
	private static final String OPZIONE_MENU = "M";
	private static final String OPZIONE_INDIETRO = "I";
	private static final String OPZIONE_CONFERMA = "C";
	private QuesitoView quesitoRichiestoView;

	public ModificaQuesitoScreen(CvemController controller) {
		super("MODIFICA QUESITO", controller);
	}

	public void show() {
		/*
		 * prima di tutto chiedo quale quesito vuole modificare e mi faccio tornare un
		 * QuesitoView
		 */
		mostraInfo("Inserisci il codice del quesito che vuoi modificare");
		String codiceQuesito = scanner.nextLine();
		RecuperaQuesitoRequest recuperaQuesitoRequest = new RecuperaQuesitoRequest(codiceQuesito);
		try {
			this.quesitoRichiestoView = controller.getQuesitoPerRichiestaModifica(recuperaQuesitoRequest);
			proponiModifiche();
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private void proponiModifiche() {
		mostraInfo("Cosa vuoi modificare?");
		mostraInfo("D)omanda");
		mostraInfo("R)isposte");
		mostraInfo("L)ivello di difficolta'");
		mostraInfo("M)enu");
		String scelta = scanner.next();
		try {
			switch (scelta) {
			case OPZIONE_MODIFICA_DOMANDA:
				modificaDomanda();
				break;
			case OPZIONE_MODIFICA_RISPOSTE:
				modificaRisposte();
				break;
			case OPZIONE_MODIFICA_LIVELLO_DIFFICOLTA:
				modificaDifficolta();
				break;
			case OPZIONE_MENU:
				controller.showMenuScreen();
				break;

			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}
	}

	private void modificaDifficolta() {
		mostraInfo("Questo e' il livello di difficolta' attuale: "
				+ quesitoRichiestoView.getDifficoltaView().getLivelloDifficolta());
		mostraInfo("M)odifica");
		mostraInfo("I)ndietro");

		String scelta = scanner.next();
		try {
			switch (scelta) {

			case OPZIONE_MODIFICA_RISPOSTE:
				acquisisciDato(controller.getMinimoDiDifficolta(), controller.getMassimoDiDifficlta());
				break;
			case OPZIONE_INDIETRO:
				/*
				 * ritorno nel metodo che mi ha chiamato
				 */
				proponiModifiche();
				break;

			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			modificaDifficolta();
		}

	}

	private void acquisisciDato(int minimoDiDifficolta, int massimoDiDifficlta) {
		mostraInfo("Inserisci il livello di difficolta' del quesito compreso tra " + minimoDiDifficolta + " e "
				+ massimoDiDifficlta);

		int scelta = scanner.nextInt();
		try {
			RangeDifficolta.verificaLimitiDifficolta(controller, scelta);

		} catch (DifficoltaFuoriLimitiException e) {
			mostraInfo(e.getMessage());
			acquisisciDato(minimoDiDifficolta, massimoDiDifficlta);
		}

		mostraInfo("C)onferma");
		mostraInfo("I)ndietro");

		String sceltaFinale = scanner.next();
		try {
			switch (sceltaFinale) {

			case OPZIONE_CONFERMA:
				ModificaDifficoltaRequest request = new ModificaDifficoltaRequest(
						quesitoRichiestoView.getDomandaView().getTesto(), scelta);
				/*
				 * vorrei che dopo ogni modifica rimanessi comunque in questa pagina fino a
				 * quando non vuoi uscire tu ma aggiorno costantemente i quesito ch le modifiche
				 * fatte
				 */
				this.quesitoRichiestoView = controller.modificaDifficolta(request);
				proponiModifiche();
				break;
			case OPZIONE_INDIETRO:
				/*
				 * ritorno nel metodo che mi ha chiamato
				 */
				modificaDifficolta();
				break;

			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			modificaDifficolta();
		}

	}

	private void modificaRisposte() {
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
			String rispostaGiusta = rispostaView.isGiusta() ? "Sì" : "No";
			mostraInfo(rispostaView.getTesto() + ", risposta giusta = " + rispostaGiusta);
		}

		while (true) {
			mostraInfo("Quale risposta vuoi modificare?");
			mostraInfo("1");
			mostraInfo("2");
			mostraInfo("3");
			mostraInfo("4");
			mostraInfo("I)ndietro");
			mostraInfo("C)onferma fine modifiche");

			String scelta = scanner.next();
			if (scelta.equalsIgnoreCase(OPZIONE_INDIETRO)) {
				/*
				 * ritorno al metodo che mi ha chiamato
				 */
				proponiModifiche();
			}
			if (scelta.equalsIgnoreCase(OPZIONE_CONFERMA)) {
				/*
				 * devo far vedere tutte le risposte e poi chiedere quale sia quella giusta
				 */
				int indiceRispostaGiusta = acquisisciIndiceRispostaGiusta(testiRisposteAttuali);

				/*
				 * creare 4 request modificaRisposta
				 */

				List<ModificaRispostaRequest> risposteRequest = new ArrayList<>();

				for (int i = 0; i < 4; i++) {
					boolean corretta = i == indiceRispostaGiusta;
					risposteRequest.add(new ModificaRispostaRequest(testiRisposteAttuali.get(i), corretta));
				}

				ModificaRisposteRequest request = new ModificaRisposteRequest(quesitoRichiestoView.getCodice(),
						risposteRequest);

				try {
					this.quesitoRichiestoView = controller.modificaRisposte(request);
				} catch (QuesitoNonTrovatoException | RisposteInvalideException | TestoRipostaAssenteException | CodiceInvalidoException e) {
					mostraInfo(e.getMessage());
					proponiModifiche();

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
				modificaRisposte();
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

		while (true) {
			mostraInfo("Inserisci il testo sostitutivo");
			String scelta = scanner.nextLine();
			try {
				if (!StringUtils.isAlphanumericSpace(scelta)) {
					throw new IllegalArgumentException("Il testo dellla risposta deve essere un alpha numerico ");
				}

				testiRisposteAttuali.set(indiceRisposta, scelta);

			} catch (Exception e) {
				mostraInfo(e.getMessage());
				modificaRispostaIndicata(indiceRisposta, testiRisposteAttuali);

			}
		}

	}

	private void modificaDomanda() {

		/*
		 * .String testo .Enum Categoria categoria .InformazioniDomanda informazioni NON
		 * obbligatorio
		 */

		String testoDomanda = quesitoRichiestoView.getDomandaView().getTesto();
		Categoria categoria = quesitoRichiestoView.getDomandaView().getCategoria();
		String urlImmagine = quesitoRichiestoView.getDomandaView().getInformazioniView().getUrlImmagine();
		String urlDocumentazione = quesitoRichiestoView.getDomandaView().getInformazioniView().getUrlDocumentazione();

		mostraInfo("Questa e' la domanda attuale:");
		mostraInfo("Testo: " + testoDomanda);
		mostraInfo("Categoria: " + categoria.toString());
		mostraInfo("Informazioni: ");

		if (urlDocumentazione != null) {
			mostraInfo("url documentazione: " + urlDocumentazione);
		} else {
			mostraInfo("Al momento non c'e' un url documentazione");
		}
		if (urlImmagine != null) {
			mostraInfo("url immagine: " + urlImmagine);

		} else {
			mostraInfo("Al momento non c'e' un url immagine");

		}

		while (true) {
			mostraInfo("Cosa vuoi modificare?");
			mostraInfo("T)esto");
			mostraInfo("Im)magine");
			mostraInfo("D)ocumentazione");
			mostraInfo("C)ategoria");
			mostraInfo("S)alva");
			mostraInfo("I)ndietro");

			String scelta = scanner.next();

			try {
				switch (scelta.toUpperCase()) {

				case "T":
					testoDomanda = modificaTestoDomanda();
					break;

				case "C":
					categoria = modificaCategoria();
					break;
				case "IM":
					urlImmagine = modificaImmagine();
					break;
				case "D":
					urlDocumentazione = modificaImmagine();
					break;

				case "S":

					ModificaDomandaRequest request = new ModificaDomandaRequest(quesitoRichiestoView.getCodice(),
							testoDomanda, categoria, urlImmagine, urlDocumentazione);

					this.quesitoRichiestoView = controller.modificaDomanda(request);
					proponiModifiche();
					break;
				case OPZIONE_INDIETRO:
					proponiModifiche();
					break;

				default:
					throw new IllegalArgumentException("Opzione inesistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());
				modificaRisposte();
			}

		}

		// TODO Auto-generated method stub

	}

	private String modificaTestoDomanda() {
		mostraInfo("Inserisci il testo della domanda");
		String nuovoTesto = scanner.nextLine();
		try {
			if (StringUtils.isAlphanumericSpace(nuovoTesto)) {
				throw new FormatoFraseNonCorrettoException(
						"Il testo della domanda che hai inserito non e' nel formato corretto");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return modificaTestoDomanda();
		}

		return nuovoTesto;

	}

	private String modificaImmagine() {

		mostraInfo("Inserisci url immagine");
		String urlImmagine = scanner.next();
		try {
			if (StringUtils.isAlphanumeric(urlImmagine)) {
				throw new IllegalArgumentException("L'url che hai inserito non e' nel formato corretto");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return modificaImmagine();
		}
		return urlImmagine;

	}

	private String modificaDocumentazione() {

		mostraInfo("Inserisci url documentazione");
		String urlDocumentazione = scanner.next();
		try {
			if (StringUtils.isAlphanumeric(urlDocumentazione)) {
				throw new IllegalArgumentException("L'url che hai inserito non e' nel formato corretto");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return modificaDocumentazione();
		}
		return urlDocumentazione;

	}

	private Categoria modificaCategoria() {
		mostraInfo("Inserisci la categoria della domanda");
		String categoria = scanner.next();
		Categoria nuovaCategoria = null;
		try {
			nuovaCategoria = Categoria.valueOf(categoria.toUpperCase());
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return modificaCategoria();
		}

		return nuovaCategoria;
	}

}
