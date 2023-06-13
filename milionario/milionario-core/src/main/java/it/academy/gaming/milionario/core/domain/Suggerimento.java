package it.academy.gaming.milionario.core.domain;

import java.util.Collection;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import it.academy.gaming.milionario.core.domain.codici.CodiceSuggerimento;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;

public class Suggerimento {

	private static Random random = new Random();

	private CodiceSuggerimento codice;
	private String testo;
	private int tempoMinimo;
	private int tempoEsposizione;
	private final static int TEMPO_MASSIMO = 30;
	private Accuratezza accuratezza;

	private static String bookmarkRispostaCorretta = "${Y}";
	private static String bookmarkRispostaSbagliata = "${X}";

	private static String bookmarkValorizzazioneNome = "\\$\\{N\\}";
	private static String bookmarkValorizzazioneRispostaCorretta = "\\$\\{Y\\}";
	private static String bookmarkValorizzazioneRispostaSbagliata = "\\$\\{X\\}";

	private Suggerimento(String testo, int tempoMinimo, Accuratezza accuratezza, CodiceSuggerimento codice) {
		this.testo = testo;
		this.tempoMinimo = tempoMinimo;
		this.accuratezza = accuratezza;
		this.codice = codice;
	}

	public static Suggerimento parse(CodiceSuggerimento codiceSuggerimento, String testo, int tempoMinimo,
			Accuratezza accuratezza) {
		return new Suggerimento(testo, tempoMinimo, accuratezza, codiceSuggerimento);
	}

	public static Suggerimento crea(String testo, int tempoMinimo, Accuratezza accuratezza)
			throws SuggerimentoInvalidoException {

		if (StringUtils.isBlank(testo) || tempoMinimo <= 0 || tempoMinimo > TEMPO_MASSIMO || accuratezza == null) {
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
			/*
			 * non deve contenere risposte
			 */
			if (testo.contains(bookmarkRispostaCorretta) || testo.contains(bookmarkRispostaSbagliata))
				throw SuggerimentoInvalidoException.astenuta();
			break;
		case CORRETTA:
			/*
			 * deve contenere una risposta corretta e non deve contenere la risposta
			 * corretta
			 */
			if (!testo.contains(bookmarkRispostaCorretta)) {
				throw SuggerimentoInvalidoException.corretta();
			}
			break;
		case IMPRECISA:
			/*
			 * deve contenere una risposta sbagliata e una risposta corretta
			 */
			if (!(testo.contains(bookmarkRispostaCorretta) && testo.contains(bookmarkRispostaSbagliata))) {
				throw SuggerimentoInvalidoException.imprecisa();
			}
			break;
		case SBAGLIATA:
			/*
			 * deve contenere una risposta sbagliata e non deve contenere la risposta
			 * corretta
			 */
			if (testo.contains(bookmarkRispostaCorretta) || !testo.contains(bookmarkRispostaSbagliata)) {
				throw SuggerimentoInvalidoException.sbagliata();
			}
			break;
		}
		return new Suggerimento(testo, tempoMinimo, accuratezza, CodiceSuggerimento.crea());
	}

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

	public static int getTempoMax() {
		return TEMPO_MASSIMO;
	}

	public void valorizzaBookmarks(Giocatore giocatore, Collection<Risposta> risposteDisponibili) {
		String testoRispostaCorretta = getTestoRispostaCorretta(risposteDisponibili);
		String testoRispostaSbagliata = getTestoRispostaSbagliata(risposteDisponibili);

		testo = testo.replaceAll(bookmarkValorizzazioneNome, giocatore.getNome());
		testo = testo.replaceAll(bookmarkValorizzazioneRispostaCorretta, testoRispostaCorretta);
		testo = testo.replaceAll(bookmarkValorizzazioneRispostaSbagliata, testoRispostaSbagliata);

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

	public CodiceSuggerimento getCodice() {
		return codice;
	}

}