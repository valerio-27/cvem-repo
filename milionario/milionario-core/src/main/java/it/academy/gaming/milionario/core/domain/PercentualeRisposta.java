package it.academy.gaming.milionario.core.domain;

public class PercentualeRisposta {

	private LetteraRisposta letteraRisposta;
	private int percentuale;

	public PercentualeRisposta(LetteraRisposta letteraRisposta, int percentuale)
			throws PercentualeRispostaInvalidaExcpetion {
		if (letteraRisposta == null) {
			throw PercentualeRispostaInvalidaExcpetion.letteraRispostaAssente();
		}
		if (percentuale < 0 || percentuale > 100) {
			throw PercentualeRispostaInvalidaExcpetion.percentualeNonInRange();
		}
		this.letteraRisposta = letteraRisposta;
		this.percentuale = percentuale;
	}

	public LetteraRisposta getLetteraRisposta() {
		return letteraRisposta;
	}

	public int getPercentuale() {
		return percentuale;
	}

}