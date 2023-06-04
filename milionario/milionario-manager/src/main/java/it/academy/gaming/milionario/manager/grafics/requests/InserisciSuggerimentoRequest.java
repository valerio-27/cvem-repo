package it.academy.gaming.milionario.manager.grafics.requests;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.manager.grafics.InputSuggerimento;

public class InserisciSuggerimentoRequest {
	private String testoSuggerimento;
	private Accuratezza accuratezza;
	private int tempoMinimo;

	public InserisciSuggerimentoRequest(InputSuggerimento inputSuggerimento) {
		super();
		this.testoSuggerimento = inputSuggerimento.getTesto();
		this.accuratezza = inputSuggerimento.getAccuratezza();
		this.tempoMinimo = inputSuggerimento.getMinimoTempo();
	}

	public String getTestoSuggerimento() {
		return testoSuggerimento;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

	public int getTempoMinimo() {
		return tempoMinimo;
	}

}
