package it.academy.gaming.milionario.core.domain;

import java.util.Collection;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;

public class Suggerimento {

	private static Random random = new Random();
	private String testo;
	private int tempoMinimo;
	private int tempoEsposizione;
	private final static int TEMPO_MASSIMO = 30;
	private Accuratezza accuratezza;

	private static String bookmarkNome = "${N}";
	private static String bookmarkRispostaCorretta = "${Y}";
	private static String bookmarkRispostaSbagliata = "${X}";

	private Suggerimento(String testo, int tempoMinimo, Accuratezza accuratezza) {
		this.testo = testo;
		this.tempoMinimo = tempoMinimo;
		this.accuratezza = accuratezza;
	}

	public static Suggerimento crea(String testo, int tempoMinimo, Accuratezza accuratezza)
			throws SuggerimentoInvalidoException {

		if (StringUtils.isBlank(testo) || tempoMinimo < 1 || tempoMinimo > 30 || accuratezza == null) {
			throw SuggerimentoInvalidoException.parametriInvalidi();
		}

		if (accuratezza != Accuratezza.ASTENUTA) {
			int ricorrenzeBookmarkRispostaSbagliata = StringUtils.countMatches(testo, bookmarkRispostaSbagliata);
			if (ricorrenzeBookmarkRispostaSbagliata > 1) {
				throw SuggerimentoInvalidoException.numeroBookmarksRispostaSbagliataInvalidi();
			}
		}
		switch (accuratezza) {
		case ASTENUTA:
			if (testo.contains(bookmarkRispostaCorretta) || testo.contains(bookmarkRispostaSbagliata))
				throw SuggerimentoInvalidoException.astenuta();
			break;
		case CORRETTA:
			if (!testo.contains(bookmarkRispostaCorretta)) {
				throw SuggerimentoInvalidoException.corretta();
			}
			break;
		case IMPRECISA:
			if (!(testo.contains(bookmarkRispostaCorretta) && testo.contains(bookmarkRispostaSbagliata))) {
				throw SuggerimentoInvalidoException.imprecisa();
			}
			break;
		case SBAGLIATA:
			// deve contenere almeno una risposta sbagliata e non deve contenere la risposta
			// corretta"
			if (testo.contains(bookmarkRispostaCorretta) || !testo.contains(bookmarkRispostaSbagliata)) {
				throw SuggerimentoInvalidoException.sbagliata();
			}
			break;
		}
		return new Suggerimento(testo, tempoMinimo, accuratezza);
	}

//	public SuggerimentoDaCasa(String testo, int tempoMinimo, Accuratezza accuratezza)
//			throws SuggerimentoInvalidoException {
//		if (StringUtils.isBlank(testo) || tempoMinimo < 1 || tempoMinimo > 30 || accuratezza == null) {
//			throw new SuggerimentoInvalidoException();
//		}
//		this.testo = testo;
//		this.tempoMinimo = tempoMinimo;
//		this.accuratezza = accuratezza;
//	}

	private void generaTempoEsposizione() {
		tempoEsposizione = tempoMinimo + random.nextInt(TEMPO_MASSIMO) + 1;
	}

	public String getTesto() {
		return testo;
	}

	public int getTempoMinimo() {
		return tempoMinimo;
	}

	public int getTempoEsposizione() {
		return tempoEsposizione;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

	public void valorizzaBookmarks(Giocatore giocatore, Collection<Risposta> risposteDisponibili) {


		String testoRispostaCorretta = getTestoRispostaCorretta(risposteDisponibili);
		String testoRispostaSbagliata = getTestoRispostaSbagliata(risposteDisponibili);

		testo = testo.replaceAll(bookmarkNome, giocatore.getNome());
		testo = testo.replaceAll(bookmarkRispostaCorretta, testoRispostaCorretta);

		testo = testo.replace(bookmarkRispostaSbagliata, testoRispostaSbagliata);
		
		generaTempoEsposizione();
		
	}

	private String getTestoRispostaSbagliata(Collection<Risposta> risposteDisponibili) {
		String testo = "";
		for (Risposta risposta : risposteDisponibili) {
			if (!risposta.isCorretta()) {
				testo = risposta.getTesto();
			}
		}
		return testo;
	}

	private String getTestoRispostaCorretta(Collection<Risposta> risposteDisponibili) {
		String testo = "";
		for (Risposta risposta : risposteDisponibili) {
			if (risposta.isCorretta()) {
				testo = risposta.getTesto();
			}
		}
		return testo;
	}

	public String replaceBookmark(String input, String bookmarkName, String replacement1, String replacement2) {
		// Creazione del pattern per il bookmark da sostituire
		String patternString = "\\$\\{" + bookmarkName + "\\}";

		// Sostituzione del bookmark con le due stringhe fornite
		String output = input.replaceAll(patternString, replacement1 + " " + replacement2);

		return output;
	}

}