package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.TestoRipostaAssenteException;

public class Risposta {

	private CodiceRisposta codiceRisposta;
	private String testo;
	private boolean corretta;

	public Risposta(String testo, boolean corretta) throws TestoRipostaAssenteException {
		if (testo == null) {
			throw new TestoRipostaAssenteException();
		}
		this.codiceRisposta = CodiceRisposta.crea();
		this.testo = testo;
		this.corretta = corretta;
	}

	public String getTesto() {
		return testo;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public CodiceRisposta getCodiceRisposta() {
		return codiceRisposta;
	}

}
