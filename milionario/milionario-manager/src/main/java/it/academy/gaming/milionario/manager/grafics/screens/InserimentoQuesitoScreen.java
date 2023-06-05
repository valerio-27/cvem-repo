package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.manager.grafics.InputDomanda;
import it.academy.gaming.milionario.manager.grafics.InputRisposta;
import it.academy.gaming.milionario.manager.grafics.InputSuggerimento;
import it.academy.gaming.milionario.manager.grafics.RangeDifficolta;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;
import it.academy.gaming.milionario.manager.grafics.exceptions.TestoSuggeriMemtoErratoException;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciDomandaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciRispostaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciSuggerimentoRequest;

public class InserimentoQuesitoScreen extends Screen {

	public InserimentoQuesitoScreen(CvemController controller) {
		super("INSERIMENTO QUESITO", controller);
	}

	public void show() {
		super.showTitle();
		/*
		 * deve essere inserita la domanda , poi le 4 risposte e poi la difficolta del
		 * quesito
		 */

		InputDomanda domanda = aquisisciDatiRelativiAllaDomanda();
		List<InputRisposta> risposte = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			risposte.add(acquisisciDatiRelativiAllaRisposta(i));

		}
		List<InserisciRispostaRequest> rispostaRequests = new ArrayList<>();
		for (InputRisposta risposta : risposte) {
			InserisciRispostaRequest rispostaRequest = new InserisciRispostaRequest(risposta.getTesto(),
					risposta.isCorretta());
			rispostaRequests.add(rispostaRequest);
		}
		/*
		 * acquisisco i suggerimenti pper il quesito
		 */
		List<InputSuggerimento> suggerimenti = acquisisciInputSuggerimenti();
		Collection<InserisciSuggerimentoRequest> requestsSuggerimenti = new ArrayList<InserisciSuggerimentoRequest>();
		for (InputSuggerimento inputSuggerimento : suggerimenti) {
			requestsSuggerimenti.add(new InserisciSuggerimentoRequest(inputSuggerimento));
		}

		int livelloDiDifficolta = acquisisciDatoRelativoLaDifficolta(controller.getMinimoDiDifficolta(),
				controller.getMassimoDiDifficolta());

		/*
		 * creo le request
		 */
		InserisciDomandaRequest domandaRequest = new InserisciDomandaRequest(domanda.getTesto(), domanda.getCategoria(),
				domanda.getInformazioni().getUrlImmagine(), domanda.getInformazioni().getUrlDocumentazione());
		InserisciRispostaRequest[] arrayRispostaRequests = rispostaRequests.toArray(new InserisciRispostaRequest[0]);

		InserisciQuesitoRequest request = new InserisciQuesitoRequest(domandaRequest, arrayRispostaRequests,
				requestsSuggerimenti, livelloDiDifficolta);

		try {
			controller.inserisci(request);
		} catch (CreazioneQuesitoException | CreazioneDomandaException | TestoRispostaAssenteException
				| NumeroMassimoRisposteSuperatoException | DifficoltaNonInRangeException
				| SuggerimentiInvalidiException | SuggerimentoInvalidoException e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private List<InputSuggerimento> acquisisciInputSuggerimenti() {
		mostraInfo("Inserisci 4 suggerimenti seguendo le istruzioni");
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
		} catch (TestoSuggeriMemtoErratoException e) {
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
		} catch (TestoSuggeriMemtoErratoException e) {
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
		} catch (TestoSuggeriMemtoErratoException e) {
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
		} catch (TestoSuggeriMemtoErratoException e) {
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

	private int acquisisciDatoRelativoLaDifficolta(int minimoDiDifficolta, int massimoDiDifficlta) {
		mostraInfo("Inserisci il livello di difficolta' del quesito compreso tra " + minimoDiDifficolta + " e "
				+ massimoDiDifficlta);

		int scelta = scanner.nextInt();
		scanner.nextLine();
		try {
			RangeDifficolta.verificaLimitiDifficolta(controller, scelta);

		} catch (DifficoltaFuoriLimitiException e) {
			mostraInfo(e.getMessage());
			return acquisisciDatoRelativoLaDifficolta(minimoDiDifficolta, massimoDiDifficlta);
		}

		return scelta;
	}

	private InputRisposta acquisisciDatiRelativiAllaRisposta(int indiceDellaRisposta) {
		mostraInfo("Inserisci la risposta numero: " + indiceDellaRisposta);

		String testo = scanner.nextLine();
		mostraInfo("E' la risposta giusta? (SI/NO)");
		String rispostaGiusta = scanner.next();
		scanner.nextLine();
		try {
			return InputRisposta.crea(testo, rispostaGiusta);
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			return acquisisciDatiRelativiAllaRisposta(indiceDellaRisposta);
		}

	}

	private InputDomanda aquisisciDatiRelativiAllaDomanda() {
		/*
		 * .String testo .Enum Categoria categoria .InformazioniDomanda informazioni NON
		 * obbligatorio
		 */
		mostraInfo("Inserisci la domanda");
		String frase = scanner.nextLine();
		mostraInfo("Inserisci la categoria ");
		String categoria = scanner.next();
		scanner.nextLine();
		String urlImmagine = null;
		String urlDocumentazione = null;
		mostraInfo("Vuoi aggiungere un'immagine? (SI/NO)");
		String aggiuntaImmagine = scanner.next();
		scanner.nextLine();
		if (aggiuntaImmagine.equalsIgnoreCase("SI")) {
			mostraInfo("Inserisci url dell'immagine");
			urlImmagine = scanner.next();
			scanner.nextLine();
		}

		mostraInfo("Vuoi aggiungere della documentazione? (SI/NO)");
		String aggiuntaDocumentazione = scanner.next();
		scanner.nextLine();
		if (aggiuntaDocumentazione.equalsIgnoreCase("SI")) {
			mostraInfo("Inserisci url della documentazione");
			urlDocumentazione = scanner.next();
			scanner.nextLine();
		}

		try {
			return InputDomanda.crea(frase, categoria, urlImmagine, urlDocumentazione);
		} catch (FormatoFraseNonCorrettoException e) {
			mostraInfo(e.getMessage());
			return aquisisciDatiRelativiAllaDomanda();
		}
	}

}
