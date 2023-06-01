package it.academy.gaming.milionario.core.domain.exceptions;

public class SuggerimentiInvalidiException extends Exception {

	public SuggerimentiInvalidiException() {
		super("Deve essere presente almeno un suggerimento per Accuratezza");
	}

}