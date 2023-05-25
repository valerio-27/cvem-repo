package it.academy.gaming.milionario.core.domain.exceptions;

public class CreazioneQuesitoException extends Exception {

	protected CreazioneQuesitoException(String message) {
		super(message);
	}

	public static CreazioneQuesitoException testoAssente() {
		return new CreazioneQuesitoException("Il quesito necessita di un testo");
	}

	public static CreazioneQuesitoException difficolaAssente() {
		return new CreazioneQuesitoException("Il quesito necessita di una difficolta");
	}

}
