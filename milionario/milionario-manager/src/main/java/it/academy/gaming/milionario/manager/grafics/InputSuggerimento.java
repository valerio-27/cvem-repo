package it.academy.gaming.milionario.manager.grafics;

import it.academy.gaming.milionario.AccuratezzaProva;

public class InputSuggerimento {
	private String testo;
	private int minimoTempo;
	private AccuratezzaProva accuratezza;

	public InputSuggerimento(String testo, int minimoTempo, AccuratezzaProva accuratezza) {
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

	public AccuratezzaProva getAccuratezza() {
		return accuratezza;
	}

}
