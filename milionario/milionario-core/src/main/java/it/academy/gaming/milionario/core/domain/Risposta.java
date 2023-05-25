package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.TestoRipostaAssenteException;

public class Risposta {

	private String testo;
	private boolean corretta;

	public Risposta(String testo, boolean corretta) throws TestoRipostaAssenteException {
		if (testo == null) {
			throw new TestoRipostaAssenteException();
		}
		this.testo = testo;
		this.corretta = corretta;
	}

	public String getTesto() {
		return testo;
	}

	public boolean isCorretta() {
		return corretta;
	}

}
