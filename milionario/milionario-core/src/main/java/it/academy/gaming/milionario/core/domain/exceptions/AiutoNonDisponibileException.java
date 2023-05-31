package it.academy.gaming.milionario.core.domain.exceptions;

public class AiutoNonDisponibileException extends Exception {

	private AiutoNonDisponibileException(String message) {
		super(message);
	}

	public static AiutoNonDisponibileException aiutoPubblico() {
		return new AiutoNonDisponibileException("Aiuto pubblico non disponibile");
	}

	public static AiutoNonDisponibileException aiutoCasa() {
		return new AiutoNonDisponibileException("Aiuto Casa non disponibile");
	}

	public static AiutoNonDisponibileException aiutoComputer() {
		return new AiutoNonDisponibileException("Aiuto computer non disponibile");
	}

}
