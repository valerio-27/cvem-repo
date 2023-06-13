package it.academy.gaming.milionario.core.domain.exceptions;

public class PartitaException extends Exception {

	private PartitaException(String message) {
		super(message);
	}

	public static PartitaException nonContinuabile() {
		return new PartitaException("La partita non è continuabile");
	}

	public static PartitaException ritiroNonConsentito() {
		return new PartitaException("Il ritiro non è consentito");
	}

	public static PartitaException giaInCorso() {
		return new PartitaException("La partita è gia in corso");
	}

	public static PartitaException nonInCorso() {
		return new PartitaException("La partita non è in corso");
	}

	public static PartitaException nonInAttesa() {
		return new PartitaException("non in attesa");
	}

	public static PartitaException rispostaNonPresente() {
		return new PartitaException("La risposta non è presente");
	}

}
