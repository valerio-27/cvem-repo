package it.academy.gaming.milionario.core.domain;

public class PercentualeRispostaInvalidaExcpetion extends Exception {

	private PercentualeRispostaInvalidaExcpetion(String message) {
		super(message);
	}

	public static PercentualeRispostaInvalidaExcpetion letteraRispostaAssente() {
		return new PercentualeRispostaInvalidaExcpetion("Lettera risposta assente");
	}

	public static PercentualeRispostaInvalidaExcpetion percentualeNonInRange() {
		return new PercentualeRispostaInvalidaExcpetion("La percentuale deve essere compresa tra 0 e 100 inclusivi");
	}

}
