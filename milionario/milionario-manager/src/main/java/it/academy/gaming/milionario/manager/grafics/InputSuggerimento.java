package it.academy.gaming.milionario.manager.grafics;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.manager.grafics.exceptions.TestoSuggerimentoInvalidoException;

public class InputSuggerimento {
	private String testo;
	private int minimoTempo;
	private Accuratezza accuratezza;

	private InputSuggerimento(String testo, int minimoTempo, Accuratezza accuratezza) {
		super();
		this.testo = testo;
		this.minimoTempo = minimoTempo;
		this.accuratezza = accuratezza;
	}

	public String getTesto() {
		return testo;
	}

	public int getMinimoTempo() {
		return minimoTempo;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

	public static InputSuggerimento creaInputSuggerimentoCorretto(String testoSuggerimento, int tempoMin)
			throws TestoSuggerimentoInvalidoException {
		/*
		 * mostraInfo("Indica nel testo con \"${y}\" la posizione del riferimento alla risposta corretta (obbligatotio)"
		 * );
		 * mostraInfo("Indica nel testo con \"${x}\" la posizione del riferimento alla risposta sbagliata (obbligatotio)"
		 * );
		 * 
		 */
		if (testoSuggerimento.isBlank() || !testoSuggerimento.toUpperCase().contains("${Y}")) {
			throw TestoSuggerimentoInvalidoException.testoSuggerimentoCorrettoErrato(testoSuggerimento);
		}

		return new InputSuggerimento(testoSuggerimento, tempoMin, Accuratezza.CORRETTA);
	}

	public static InputSuggerimento creaInputSuggerimentoImpreciso(String testoSuggerimento, int tempoMin)
			throws TestoSuggerimentoInvalidoException {
		if (testoSuggerimento.isBlank() || !testoSuggerimento.toUpperCase().contains("${Y}")
				|| !testoSuggerimento.contains("${X}")) {
			throw TestoSuggerimentoInvalidoException.testoSuggerimentoImprecisoErrato(testoSuggerimento);
		}

		return new InputSuggerimento(testoSuggerimento, tempoMin, Accuratezza.IMPRECISA);
	}

	public static InputSuggerimento creaInputSuggerimentoSbagliato(String testoSuggerimento, int tempoMin)
			throws TestoSuggerimentoInvalidoException {
		if (testoSuggerimento.isBlank() || !testoSuggerimento.toUpperCase().contains("${X}")) {
			throw TestoSuggerimentoInvalidoException.testoSuggerimentoSbagliatoErrato(testoSuggerimento);
		}

		return new InputSuggerimento(testoSuggerimento, tempoMin, Accuratezza.SBAGLIATA);
	}

	public static InputSuggerimento creaInputSuggerimentoAstenuto(String testoSuggerimento, int tempoMin)
			throws TestoSuggerimentoInvalidoException {
		if (testoSuggerimento.isBlank()) {
			throw TestoSuggerimentoInvalidoException.testoSuggerimentoAstenutoErrato(testoSuggerimento);
		}

		return new InputSuggerimento(testoSuggerimento, tempoMin, Accuratezza.ASTENUTA);
	}

}
