package it.academy.gaming.milionario.core.domain.exceptions;

public class AiutoNonDisponibileException extends Exception {

	private AiutoNonDisponibileException(String message) {
		super(message);
	}

	public static AiutoNonDisponibileException aiutoPubblico() {
		return new AiutoNonDisponibileException("Aiuto del pubblico non disponibile");
	}

	public static AiutoNonDisponibileException aiutoCasa() {
		return new AiutoNonDisponibileException("Aiuto da Casa non disponibile");
	}

	public static AiutoNonDisponibileException aiutoComputer() {
		return new AiutoNonDisponibileException("Aiuto del computer non disponibile");
	}

}
