package it.academy.gaming.milionario.core.domain.exceptions;

public class PartitaException extends Exception{

	private PartitaException(String message) {
		super(message);
	}

	public static PartitaException nonContinuabile() {
		return null;
	}

	public static PartitaException ritiroNonConsentito() {
		return null;
	}

}
