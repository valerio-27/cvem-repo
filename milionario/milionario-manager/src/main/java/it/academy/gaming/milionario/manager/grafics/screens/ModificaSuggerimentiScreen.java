package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.ArrayList;
import java.util.List;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.manager.core.application.view.QuesitoView;
import it.academy.gaming.milionario.manager.core.application.view.SuggerimentoView;
import it.academy.gaming.milionario.manager.core.exceptions.QuesitoNonTrovatoException;
import it.academy.gaming.milionario.manager.grafics.InputSuggerimento;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.TestoSuggerimentoInvalidoException;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaSuggerimentiRequest;
import it.academy.gaming.milionario.manager.grafics.requests.ModificaSuggerimentoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RecuperaQuesitoRequest;

public class ModificaSuggerimentiScreen extends Screen {

	public ModificaSuggerimentiScreen(CvemController controller) {
		super("MODIFICA DOMANDA", controller);
	}

	private static final String OPZIONE_ESCI_DALLE_MODIFICHE = "E";
	private static final String OPZIONE_MODIFICA = "M";
	private static final String OPZIONE_MODIFICA_ASTENUTO = "A";
	private static final String OPZIONE_MODIFICA_CORRETTO = "C";
	private static final String OPZIONE_MODIFICA_IMPRECISO = "I";
	private static final String OPZIONE_MODIFICA_SBAGLIATO = "S";

	public void show() {
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

		modificaSuggerimenti(quesitoRichiestoView);
	}

	private void modificaSuggerimenti(QuesitoView quesitoRichiestoView) {
		InputSuggerimento inputSuggerimentoAstenuto = null;
		InputSuggerimento inputSuggerimentoCorretto = null;
		InputSuggerimento inputSuggerimentoImpreciso = null;
		InputSuggerimento inputSuggerimentoSbagliato = null;
		for (SuggerimentoView suggerimentoView : quesitoRichiestoView.getSuggerimentiView()) {
			Accuratezza accuratezza = suggerimentoView.getAccuratezza();
			/*
			 * mi ricostruisco gli input suggerimento ignorando l'exception perche arrivano
			 * sicuramente giusti
			 */
			try {
				switch (accuratezza) {
				case ASTENUTA:
					inputSuggerimentoAstenuto = InputSuggerimento.creaInputSuggerimentoAstenuto(
							suggerimentoView.getTestoSuggerimento(), suggerimentoView.getTempoMinimo());
					break;
				case CORRETTA:
					inputSuggerimentoCorretto = InputSuggerimento.creaInputSuggerimentoCorretto(
							suggerimentoView.getTestoSuggerimento(), suggerimentoView.getTempoMinimo());
					break;
				case IMPRECISA:
					inputSuggerimentoImpreciso = InputSuggerimento.creaInputSuggerimentoImpreciso(
							suggerimentoView.getTestoSuggerimento(), suggerimentoView.getTempoMinimo());
					break;
				case SBAGLIATA:
					inputSuggerimentoSbagliato = InputSuggerimento.creaInputSuggerimentoSbagliato(
							suggerimentoView.getTestoSuggerimento(), suggerimentoView.getTempoMinimo());
					break;

				default:
					break;
				}

			} catch (Exception ignored) {

			}
		}

		mostraInfo("Quale suggerimento vuoi modificare");
		mostraInfo("C)orretto");
		mostraInfo("I)mpreciso");
		mostraInfo("S)bagliato");
		mostraInfo("A)stenuto");
		mostraInfo("M)odifiche terminate");
		mostraInfo("E)sci dalle modifiche");
		String scelta = scanner.next();
		scanner.nextLine();
		try {
			switch (scelta.toUpperCase()) {
			case OPZIONE_ESCI_DALLE_MODIFICHE:
				controller.showModificaQuesitoScreen();
				break;
			case OPZIONE_MODIFICA:

				List<ModificaSuggerimentoRequest> modificaSuggerimentoRequests = new ArrayList<>();
				modificaSuggerimentoRequests.add(new ModificaSuggerimentoRequest(inputSuggerimentoAstenuto.getTesto(),
						inputSuggerimentoAstenuto.getAccuratezza(), inputSuggerimentoAstenuto.getMinimoTempo()));
				modificaSuggerimentoRequests.add(new ModificaSuggerimentoRequest(inputSuggerimentoCorretto.getTesto(),
						inputSuggerimentoCorretto.getAccuratezza(), inputSuggerimentoCorretto.getMinimoTempo()));
				modificaSuggerimentoRequests.add(new ModificaSuggerimentoRequest(inputSuggerimentoImpreciso.getTesto(),
						inputSuggerimentoImpreciso.getAccuratezza(), inputSuggerimentoImpreciso.getMinimoTempo()));
				modificaSuggerimentoRequests.add(new ModificaSuggerimentoRequest(inputSuggerimentoSbagliato.getTesto(),
						inputSuggerimentoSbagliato.getAccuratezza(), inputSuggerimentoSbagliato.getMinimoTempo()));
				ModificaSuggerimentiRequest request = new ModificaSuggerimentiRequest(quesitoRichiestoView.getCodice(),
						modificaSuggerimentoRequests);
				controller.modificaSuggerimenti(request);
				break;
			case OPZIONE_MODIFICA_ASTENUTO:
				inputSuggerimentoAstenuto = acquisisciSuggerimentoAstenuto();
				break;
			case OPZIONE_MODIFICA_CORRETTO:
				inputSuggerimentoCorretto = acquisisciSuggerimentoCorretto();
				break;
			case OPZIONE_MODIFICA_IMPRECISO:
				inputSuggerimentoImpreciso = acquisisciSuggerimentoImpreciso();
				break;
			case OPZIONE_MODIFICA_SBAGLIATO:
				inputSuggerimentoSbagliato = acquisisciSuggerimentoSbagliato();
				break;

			default:
				throw new IllegalArgumentException("Opzione non valida");

			}
		} catch (Exception e) {
			show();
		}

	}

	private List<InputSuggerimento> acquisisciInputSuggerimenti() {
		/*
		 * mi prendo i suggerimenti che ho adesso
		 */

		List<InputSuggerimento> suggerimenti = new ArrayList<>();
		/*
		 * ho acquisito quello corretto
		 */
		suggerimenti.add(acquisisciSuggerimentoCorretto());
		/*
		 * ho acquisito quello impreciso
		 */
		suggerimenti.add(acquisisciSuggerimentoImpreciso());
		/*
		 * ho acquisito quello sbagliato
		 */
		suggerimenti.add(acquisisciSuggerimentoSbagliato());
		/*
		 * ho acquisito quello astenuto
		 */
		suggerimenti.add(acquisisciSuggerimentoAstenuto());

		return suggerimenti;
	}

	private InputSuggerimento acquisisciSuggerimentoAstenuto() {
		mostraInfo("Inserisci il testo del suggerimento astenuto seguendo le seguenti regole:");
		mostraInfo("Indica nel testo con \"${N}\" la posizione del riferimento al nome del giocatore(opzionale)");
		String testoSuggerimento = scanner.nextLine();

		int tempoMin = acquisisciTempoMinSuggerimento();
		InputSuggerimento suggerimento = null;
		try {
			suggerimento = InputSuggerimento.creaInputSuggerimentoAstenuto(testoSuggerimento, tempoMin);
		} catch (TestoSuggerimentoInvalidoException e) {
			mostraInfo(e.getMessage());
			return acquisisciSuggerimentoAstenuto();
		}
		return suggerimento;
	}

	private InputSuggerimento acquisisciSuggerimentoSbagliato() {
		mostraInfo("Inserisci il testo del suggerimento sbagliato seguendo le seguenti regole:");
		mostraInfo("Indica nel testo con \"${N}\" la posizione del riferimento al nome del giocatore(opzionale)");
		mostraInfo("Indica nel testo con \"${X}\" la posizione del riferimento alla risposta sbagliata(obbligatoria)");
		String testoSuggerimento = scanner.nextLine();

		int tempoMin = acquisisciTempoMinSuggerimento();
		InputSuggerimento suggerimento = null;
		try {
			suggerimento = InputSuggerimento.creaInputSuggerimentoSbagliato(testoSuggerimento, tempoMin);
		} catch (TestoSuggerimentoInvalidoException e) {
			mostraInfo(e.getMessage());
			return acquisisciSuggerimentoSbagliato();
		}
		return suggerimento;
	}

	private InputSuggerimento acquisisciSuggerimentoCorretto() {
		mostraInfo("Inserisci il testo del suggerimento corretto seguendo le seguenti regole:");
		mostraInfo("Indica nel testo con \"${N}\" la posizione del riferimento al nome del giocatore (opzionale)");
		mostraInfo("Indica nel testo con \"${y}\" la posizione del riferimento alla risposta corretta (obbligatorio)");
		String testoSuggerimento = scanner.nextLine();

		int tempoMin = acquisisciTempoMinSuggerimento();
		InputSuggerimento suggerimento = null;
		try {
			suggerimento = InputSuggerimento.creaInputSuggerimentoCorretto(testoSuggerimento, tempoMin);
		} catch (TestoSuggerimentoInvalidoException e) {
			mostraInfo(e.getMessage());
			return acquisisciSuggerimentoCorretto();
		}
		return suggerimento;
	}

	private InputSuggerimento acquisisciSuggerimentoImpreciso() {
		mostraInfo("Inserisci il testo del suggerimento impreciso seguendo le seguenti regole:");
		mostraInfo("Indica nel testo con \"${N}\" la posizione del riferimento al nome del giocatore (opzionale)");
		mostraInfo("Indica nel testo con \"${y}\" la posizione del riferimento alla risposta corretta (obbligatorio)");
		mostraInfo("Indica nel testo con \"${x}\" la posizione del riferimento alla risposta sbagliata (obbligatorio)");
		String testoSuggerimento = scanner.nextLine();

		int tempoMin = acquisisciTempoMinSuggerimento();
		InputSuggerimento suggerimento = null;
		try {
			suggerimento = InputSuggerimento.creaInputSuggerimentoImpreciso(testoSuggerimento, tempoMin);
		} catch (TestoSuggerimentoInvalidoException e) {
			mostraInfo(e.getMessage());
			return acquisisciSuggerimentoImpreciso();
		}
		return suggerimento;
	}

	private int acquisisciTempoMinSuggerimento() {
		int tempoMax = controller.getTempoMaxPerSuggerimento();
		mostraInfo("Inserisci il minimo di tempo di esposizione compreso tra 0 e " + tempoMax);

		int tempoMinimo = 0;

		try {
			tempoMinimo = scanner.nextInt();
			scanner.nextLine();
			if (tempoMinimo < 0 || tempoMinimo > tempoMax) {
				throw new IllegalArgumentException("Il tempo che hai inserito non rientra nei limiti dati");

			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return acquisisciTempoMinSuggerimento();
		}

		return tempoMinimo;
	}

}
