package it.academy.gaming.milionario.core.domain.exceptions;

public class RisposteInvalideException extends CreazioneQuesitoException {

	private RisposteInvalideException(String message) {
		super(message);
	}

	public static RisposteInvalideException presenteRispostaNulla() {
		return new RisposteInvalideException("Il quesito deve avere esattamente 4 risposte");
	}

	public static RisposteInvalideException rispostaEsattaUnivocaAssente() {
		return new RisposteInvalideException("Il quesito può avere solamente 1 risposta esatta");
	}

}
