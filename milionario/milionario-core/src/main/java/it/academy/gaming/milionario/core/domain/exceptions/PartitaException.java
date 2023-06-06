package it.academy.gaming.milionario.core.domain.exceptions;

public class PartitaException extends Exception{

	private PartitaException(String message) {
		super(message);
	}

	public static PartitaException nonContinuabile() {
		return new PartitaException("Partita non continuabile");
	}

	public static PartitaException ritiroNonConsentito() {
		return new PartitaException("ritiro non consentito");
	}

	public static PartitaException giaInCorso() {
		return new PartitaException("gia in corso");
	}

	public static PartitaException nonInCorso() {
		return new PartitaException("non in corso");
	}

}
