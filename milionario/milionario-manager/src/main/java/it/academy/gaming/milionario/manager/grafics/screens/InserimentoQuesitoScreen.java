package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.ArrayList;
import java.util.List;

import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.manager.grafics.InputDomanda;
import it.academy.gaming.milionario.manager.grafics.InputRisposta;
import it.academy.gaming.milionario.manager.grafics.RangeDifficolta;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciDomandaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciQuesitoRequest;
import it.academy.gaming.milionario.manager.grafics.requests.InserisciRispostaRequest;

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

		int livelloDiDifficolta = acquisisciDato(controller.getMinimoDiDifficolta(),
				controller.getMassimoDiDifficolta());
		
		List<InserisciRispostaRequest> rispostaRequests = new ArrayList<>();
		for (InputRisposta risposta : risposte) {
			InserisciRispostaRequest rispostaRequest = new InserisciRispostaRequest(risposta.getTesto(),
					risposta.isCorretta());
			rispostaRequests.add(rispostaRequest);
		}

		InserisciDomandaRequest domandaRequest = new InserisciDomandaRequest(domanda.getTesto(), domanda.getCategoria(),
				domanda.getInformazioni().getUrlImmagine(), domanda.getInformazioni().getUrlDocumentazione());
		InserisciRispostaRequest[] arrayRispostaRequests = rispostaRequests.toArray(new InserisciRispostaRequest[0]);

		InserisciQuesitoRequest request = new InserisciQuesitoRequest(domandaRequest, arrayRispostaRequests,
				livelloDiDifficolta);

		try {
			controller.inserisci(request);
		} catch (CreazioneQuesitoException | CreazioneDomandaException | TestoRispostaAssenteException | NumeroMassimoRisposteSuperatoException | DifficoltaNonInRangeException e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private int acquisisciDato(int minimoDiDifficolta, int massimoDiDifficlta) {
		mostraInfo("Inserisci il livello di difficolta' del quesito compreso tra " + minimoDiDifficolta + " e "
				+ massimoDiDifficlta);

		int scelta = scanner.nextInt();
		scanner.nextLine();
		try {
			RangeDifficolta.verificaLimitiDifficolta(controller, scelta);

		} catch (DifficoltaFuoriLimitiException e) {
			mostraInfo(e.getMessage());
			return acquisisciDato(minimoDiDifficolta, massimoDiDifficlta);
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
