package it.academy.gaming.milionario.core.application.views;

import it.academy.gaming.milionario.core.domain.Suggerimento;

public class SuggerimentoView {

	private String testo;
	private int tempoEsposizione;

	public SuggerimentoView(Suggerimento suggerimento) {

		this.testo = suggerimento.getTesto();
		this.tempoEsposizione = suggerimento.getTempoEsposizione();
	}

	public String getTesto() {
		return testo;
	}

	public int getTempoEsposizione() {
		return tempoEsposizione;
	}

	@Override
	public String toString() {
		return "SuggerimentoView [testo=" + testo + ", tempoEsposizione=" + tempoEsposizione + "]";
	}

	
	
}