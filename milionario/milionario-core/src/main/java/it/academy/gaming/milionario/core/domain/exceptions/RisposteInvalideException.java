package it.academy.gaming.milionario.core.domain.exceptions;

public class RisposteInvalideException extends CreazioneQuesitoException {

	private RisposteInvalideException(String message) {
		super(message);
	}

	public static RisposteInvalideException presenteRispostaNulla() {
		return new RisposteInvalideException("Il quesito necessita di 4 risposte");
	}

	public static RisposteInvalideException rispostaEsattaUnivocaAssente() {
		return new RisposteInvalideException("Il quesito necessita di 1 risposta esatta");
	}

}
