package it.academy.gaming.milionario.core.domain;

public class CreazioneVotazioneException extends Exception {

	private CreazioneVotazioneException(String message) {
		super(message);
	}

	public static CreazioneVotazioneException numeroPercentualiInvalido(int numeroPercentualiRisposta) {
		return new CreazioneVotazioneException("numero percentuali: " + numeroPercentualiRisposta
				+ " invalido, è possibile avere solamente 4 percentualiRisposta");
	}

	public static CreazioneVotazioneException percentualeRispostaAssente() {
		return new CreazioneVotazioneException("percentuale risposta assente");
	}

	public static CreazioneVotazioneException lettereRisposteIncongruenti() {
		return new CreazioneVotazioneException("lettere risposte incongruenti");
	}

}
