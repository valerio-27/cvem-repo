package it.academy.gaming.milionario.manager.grafics.screens;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDomandaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaDomandaScreen extends Screen {

	public ModificaDomandaScreen(CvemController controller) {
		super("MODIFICA DOMANDA", controller);
	}

	private static final String OPZIONE_ESCI_DALLE_MODIFICHE = "E";
	private static final String OPZIONE_SALVA = "S";
	private static final String OPZIONE_TESTO = "T";
	private static final String OPZIONE_IMMAGINE = "I";
	private static final String OPZIONE_DOCUMENTAZIONE = "D";
	private static final String OPZIONE_CATEGORIA = "C";

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

		modificaDomanda(quesitoRichiestoView);

	}

	private void modificaDomanda(QuesitoView quesitoRichiestoView) {
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
			mostraInfo("I)magine");
			mostraInfo("D)ocumentazione");
			mostraInfo("C)ategoria");
			mostraInfo("S)alva");
			mostraInfo("E)sci dalle modifiche");

			String scelta = scanner.next();
			scanner.nextLine();

			try {
				switch (scelta.toUpperCase()) {

				case OPZIONE_TESTO:
					testoDomanda = modificaTestoDomanda();
					break;

				case OPZIONE_CATEGORIA:
					categoria = modificaCategoria();
					break;
				case OPZIONE_IMMAGINE:
					urlImmagine = modificaImmagine();
					break;
				case OPZIONE_DOCUMENTAZIONE:
					urlDocumentazione = modificaDocumentazione();
					break;

				case OPZIONE_SALVA:

					ModificaDomandaRequest request = new ModificaDomandaRequest(quesitoRichiestoView.getCodice(),
							testoDomanda, categoria, urlImmagine, urlDocumentazione);

					controller.modificaDomanda(request);
					break;
				case OPZIONE_ESCI_DALLE_MODIFICHE:
					controller.showModificaQuesitoScreen();
					break;

				default:
					throw new IllegalArgumentException("Opzione inesistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());
			}

		}

	}

	private String modificaTestoDomanda() {
		mostraInfo("Inserisci il testo della domanda");
		String nuovoTesto = scanner.nextLine();
		try {
			if (StringUtils.isAllBlank(nuovoTesto)) {
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
		scanner.nextLine();
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
		scanner.nextLine();
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
		scanner.nextLine();
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
