package it.academy.gaming.milionario.core.domain.exceptions;

public class CodiceInvalidoException extends Exception {

	public CodiceInvalidoException(String codice) {
		super("Il codice quesito " + codice + " è invalido");
	}

}
