package it.academy.gaming.milionario.core.implementation.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.codici.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;

/**
 * finisce Matteo
 * 
 * @author Valerio.Crispini
 *
 */
public class QuesitoReader {

	private List<Quesito> quesiti = new ArrayList();

	public void leggiQuesiti(File cartellaQuesiti)
			throws it.academy.gaming.milionario.core.implementation.file.CartellaNonValidaException, IOException,
			CreazioneDomandaException, TestoRispostaAssenteException, DifficoltaNonInRangeException,
			CreazioneQuesitoException, NumeroMassimoRisposteSuperatoException, SuggerimentiInvalidiException,
			SuggerimentoInvalidoException, CodiceInvalidoException {

		if (!cartellaQuesiti.exists() || !cartellaQuesiti.isDirectory()) {

			throw CartellaNonValidaException.cartellaInvalida(cartellaQuesiti);

		}

		File[] files = cartellaQuesiti.listFiles();

		for (File fileQuesiti : files) {

			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(fileQuesiti));

				String line = null;

				while ((line = reader.readLine()) != null) {

					String[] componentiQuesito = line.split("\\|");

					Quesito quesito = parseQuesito(componentiQuesito);

					quesiti.add(quesito);

				}

			} finally {

				if (reader != null) {

					reader.close();
				}
			}

		}

	}

	private Quesito parseQuesito(String[] componentiQuesito)
			throws CreazioneDomandaException, TestoRispostaAssenteException, DifficoltaNonInRangeException,
			CreazioneQuesitoException, NumeroMassimoRisposteSuperatoException, SuggerimentiInvalidiException,
			SuggerimentoInvalidoException, CodiceInvalidoException {

		Domanda domanda = null;
		List<Risposta> risposte = new ArrayList<Risposta>();
		Difficolta difficolta = null;
		QuesitoBuilder builder = Quesito.builder();
		String urlImmagine = null;
		String urlDocumentazione = null;

		if (componentiQuesito.length == 7) {
			String[] jsonInformazione = componentiQuesito[5].split("=");
			if (jsonInformazione[0].contains("Immagine")) {
				urlImmagine = jsonInformazione[1];
			} else if (jsonInformazione[0].contains("Documentazione")) {
				urlDocumentazione = jsonInformazione[1];
			}

		} else if (componentiQuesito.length == 8) {
			String[] jsonImmagineUrl = componentiQuesito[5].split("=");
			String[] jsonDocumentazioneUrl = componentiQuesito[5].split("=");
			urlImmagine = jsonImmagineUrl[1];

			urlDocumentazione = jsonDocumentazioneUrl[1];
		}

		String codiceQuesitoString = componentiQuesito[0].split("=")[1];
		CodiceQuesito codiceQuesito = CodiceQuesito.parse(codiceQuesitoString);

		String categoriaString = componentiQuesito[1].split("=")[1];
		Categoria categoria = Categoria.valueOf(categoriaString.toUpperCase().trim());

		String domandaString = componentiQuesito[2].split("=")[1];
		String risposteString = componentiQuesito[3].split("=")[1];
		int difficoltaInt = Integer.valueOf(componentiQuesito[4].split("=")[1].substring(0, 1));
		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(urlImmagine, urlDocumentazione);
		domanda = new Domanda(domandaString, categoria, informazioniDomanda);
		builder.setDomanda(domanda);
		risposte = parseRisposte(risposteString);

		for (Risposta risposta : risposte) {
			builder.aggiungiRisposta(risposta);
		}
		difficolta = new Difficolta(difficoltaInt);
		builder.setDifficolta(difficolta);

		String suggerimentiString = componentiQuesito[componentiQuesito.length - 1];

		String[] arraySuggerimenti = suggerimentiString.split(";");

		for (String suggerimentoString : arraySuggerimenti) {
			Suggerimento suggerimento = parseSuggerimento(suggerimentoString);
			builder.aggiungiSuggerimento(suggerimento);
		}

		return builder.build(codiceQuesito);
	}

	private List<Risposta> parseRisposte(String risposteString) throws TestoRispostaAssenteException {

		String[] risposte = risposteString.split(",");
		List<Risposta> listaRisposte = new ArrayList<Risposta>();

		String testo;
		boolean corretta;

		for (String rispostaString : risposte) {
			if (rispostaString.contains("&")) {
				corretta = true;
				testo = rispostaString.replace("&", "");
			} else {
				corretta = false;
				testo = rispostaString;
			}
			Risposta risposta = Risposta.crea(testo, corretta);
			listaRisposte.add(risposta);
		}
		return listaRisposte;
	}

	private Suggerimento parseSuggerimento(String suggerimentoString)
			throws SuggerimentoInvalidoException, CodiceInvalidoException {

		String[] arraySuggerimento = suggerimentoString.split(",");
		String testo = arraySuggerimento[1];
		int tempo = Integer.valueOf(arraySuggerimento[2].substring(0, 1));
		Accuratezza accuratezza = Accuratezza.parse(arraySuggerimento[3].trim());

		Suggerimento suggerimento = Suggerimento.crea(testo, tempo, accuratezza);
		return suggerimento;

	}

	public List<Quesito> getQuesiti() {
		return quesiti;
	}

	public static void main(String[] args)
			throws CartellaNonValidaException, IOException, CreazioneDomandaException, TestoRispostaAssenteException,
			DifficoltaNonInRangeException, CreazioneQuesitoException, NumeroMassimoRisposteSuperatoException,
			SuggerimentiInvalidiException, SuggerimentoInvalidoException, CodiceInvalidoException {

		File file = new File("C:/ChiVuolEssereMilionarioQuesiti/FilesQuesiti");

		QuesitoReader reader = new QuesitoReader();

		reader.leggiQuesiti(file);

		reader.getQuesiti();

		System.out.println();

	}

}
