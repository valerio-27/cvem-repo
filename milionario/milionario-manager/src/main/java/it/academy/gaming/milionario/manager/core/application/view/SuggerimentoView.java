package it.academy.gaming.milionario.manager.core.application.view;

import it.academy.gaming.milionario.core.domain.Accuratezza;

public class SuggerimentoView {
	private String testo;
	private Accuratezza accuratezza;
	private int tempoMinimo;

	public SuggerimentoView(String testoSuggerimento, Accuratezza accuratezza, int tempoMinimo) {
		super();
		this.testo = testoSuggerimento;
		this.accuratezza = accuratezza;
		this.tempoMinimo = tempoMinimo;
	}

	public String getTestoSuggerimento() {
		return testo;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

	public int getTempoMinimo() {
		return tempoMinimo;
	}

	@Override
	public String toString() {
		return "\"" + testo + "\", " + ", accuratezza= " + accuratezza.toString() + ", tempoMinimo= " + tempoMinimo
				+ ";";
	}

}
