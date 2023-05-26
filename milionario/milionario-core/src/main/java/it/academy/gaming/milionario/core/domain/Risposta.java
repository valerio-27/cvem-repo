package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.TestoRipostaAssenteException;

public class Risposta {

	private CodiceRisposta codiceRisposta;
	private String testo;
	private boolean corretta;

	private Risposta(CodiceRisposta codiceRisposta, String testo, boolean corretta) {
		this.codiceRisposta = codiceRisposta;
		this.testo = testo;
		this.corretta = corretta;
	}

	public static Risposta crea(String testo, boolean corretta) throws TestoRipostaAssenteException {
		if (testo == null) {
			throw new TestoRipostaAssenteException();
		}
		return new Risposta(CodiceRisposta.crea(), testo, corretta);
	}

	public static Risposta parse(CodiceRisposta codiceRisposta, String testo, boolean corretta) {
		return new Risposta(codiceRisposta, testo, corretta);
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
