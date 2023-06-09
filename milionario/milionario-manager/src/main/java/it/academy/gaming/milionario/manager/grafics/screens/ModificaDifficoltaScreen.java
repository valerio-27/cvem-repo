package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.manager.core.application.view.QuesitoView;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.grafics.RangeDifficolta;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaDifficoltaScreen extends Screen {

	public ModificaDifficoltaScreen(CvemController controller) {
		super("MODIFICA DOMANDA", controller);
	}

	private static final String OPZIONE_ESCI_DALLE_MODIFICHE = "E";
	private static final String OPZIONE_SALVA = "S";
	private static final String OPZIONE_MODIFICA = "M";
	private static final String OPZIONE_INDIETRO = "I";

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

		modificaDifficolta(quesitoRichiestoView);

	}

	private void modificaDifficolta(QuesitoView quesitoRichiestoView) {
		mostraInfo("Questo e' il livello di difficolta' attuale: "
				+ quesitoRichiestoView.getDifficoltaView().getLivelloDifficolta());
		mostraInfo("M)odifica");
		mostraInfo("E)sci dalle modifiche");

		String scelta = scanner.next();
		scanner.nextLine();
		try {
			switch (scelta.toUpperCase()) {

			case OPZIONE_MODIFICA:
				acquisisciDatoRelativoAllaDifficolta(controller.getMinimoDiDifficolta(),
						controller.getMassimoDiDifficolta(), quesitoRichiestoView);
				break;
			case OPZIONE_ESCI_DALLE_MODIFICHE:
				controller.showModificaQuesitoScreen();
				break;

			default:
				throw new IllegalArgumentException("Opzione inesistente");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private void acquisisciDatoRelativoAllaDifficolta(int minimoDiDifficolta, int massimoDiDifficlta,
			QuesitoView quesitoView) {
		mostraInfo("Inserisci il livello di difficolta' del quesito compreso tra " + minimoDiDifficolta + " e "
				+ massimoDiDifficlta);

		int scelta = scanner.nextInt();
		scanner.nextLine();
		try {
			RangeDifficolta.verificaLimitiDifficolta(controller, scelta);

		} catch (DifficoltaFuoriLimitiException e) {
			mostraInfo(e.getMessage());
			acquisisciDatoRelativoAllaDifficolta(minimoDiDifficolta, massimoDiDifficlta, quesitoView);
		}

		mostraInfo("C)onferma");
		mostraInfo("I)ndietro");

		String sceltaFinale = scanner.next();
		scanner.nextLine();
		try {
			switch (sceltaFinale.toUpperCase()) {

			case OPZIONE_SALVA:
				ModificaDifficoltaRequest request = new ModificaDifficoltaRequest(quesitoView.getCodice(), scelta);
				/*
				 * vorrei che dopo ogni modifica rimanessi comunque in questa pagina fino a
				 * quando non vuoi uscire tu ma aggiorno costantemente i quesito ch le modifiche
				 * fatte
				 */
				controller.modificaDifficolta(request);
				break;
			case OPZIONE_INDIETRO:
				/*
				 * ritorno nel metodo che mi ha chiamato
				 */
				modificaDifficolta(quesitoView);
				break;

			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			modificaDifficolta(quesitoView);
		}

	}

}
