package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.List;

import it.academy.gaming.milionario.manager.core.views.QuesitoView;
import it.academy.gaming.milionario.manager.core.views.RispostaView;
import it.academy.gaming.milionario.manager.grafics.InputRisposta;
import it.academy.gaming.milionario.manager.grafics.RangeDifficolta;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaDifficoltaRequest;
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
		mostraInfo("Inserisci il testo del quesito che vuoi modificare");
		String testoQuesito = scanner.nextLine();
		RecuperaQuesitoRequest recuperaQuesitoRequest = new RecuperaQuesitoRequest(testoQuesito);
		this.quesitoRichiestoView = controller.getQuesitoPerRichiestaModifica(recuperaQuesitoRequest);
		proponiModifiche();

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
			if(scelta.equalsIgnoreCase(OPZIONE_CONFERMA)) {
				 
//				ModificaRisposteRequest request = new ModificaRisposteRequest(
//						quesitoRichiestoView.getDomandaView().getTesto(), scelta);
				/*
				 * vorrei che dopo ogni modifica rimanessi comunque in questa pagina fino a
				 * quando non vuoi uscire tu ma aggiorno costantemente i quesito ch le modifiche
				 * fatte
				 */
//				this.quesitoRichiestoView = controller.modificaDifficolta(request);
				proponiModifiche();
				
			}
			try {
				switch (Integer.parseInt(scelta)) {

				case 1:
					List<RispostaView> risposteInModifica1 = modificaRispostaIndicata(0);
					risposteAttuali.removeAll(risposteInModifica1);
					break;
				case 2:
					List<RispostaView> risposteInModifica2 = modificaRispostaIndicata(1);
					risposteAttuali.removeAll(risposteInModifica2);
					break;
				case 3:
					List<RispostaView> risposteInModifica3 = modificaRispostaIndicata(2);
					risposteAttuali.removeAll(risposteInModifica3);
					break;
				case 4:
					List<RispostaView> risposteInModifica4 = modificaRispostaIndicata(3);
					risposteAttuali.removeAll(risposteInModifica4);
					break;

				default:
					throw new IllegalArgumentException("Opzione insistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());
				modificaDifficolta();
			}

			modificaRispostaIndicata(Integer.parseInt(scelta));
		}
		// TODO Auto-generated method stub

	}

	private List<RispostaView> modificaRispostaIndicata(int indiceRisposta) {
		List<RispostaView> risposteAttuali = quesitoRichiestoView.getRisposteView();
		RispostaView rispostaDaModificare = risposteAttuali.get(indiceRisposta);

		String testoRisposta = rispostaDaModificare.getTesto();
		String rispostaGiusta = rispostaDaModificare.isGiusta() ? "Sì" : "No";
		while (true) {
			mostraInfo("Cosa vuoi modificare?");
			mostraInfo("T)esto");
			mostraInfo("G)iusta/Sbagliata");
			mostraInfo("C)onferma fine modifiche");
			String scelta = scanner.next();
			try {
				switch (scelta.toUpperCase()) {

				case "T":
					mostraInfo("Inserisci testo");
					testoRisposta = scanner.nextLine();

					break;
				case "G":
					mostraInfo("Risposta giusta (Si/NO)");
					rispostaGiusta = scanner.next();
					break;
				case "C":
					InputRisposta inputNuovaRisposta = InputRisposta.crea(testoRisposta, rispostaGiusta);
					boolean isGiusta = rispostaGiusta.toUpperCase() == "SI" ? true : false;
					RispostaView rispostaInSostituzione = new RispostaView(testoRisposta, isGiusta);
					risposteAttuali.set(indiceRisposta, rispostaInSostituzione);

					return risposteAttuali;
				default:
					throw new IllegalArgumentException("Opzione insistete");
				}
			} catch (Exception e) {
				mostraInfo(e.getMessage());

			}
		}

	}

	private void modificaDomanda() {

		// TODO Auto-generated method stub

	}

}
