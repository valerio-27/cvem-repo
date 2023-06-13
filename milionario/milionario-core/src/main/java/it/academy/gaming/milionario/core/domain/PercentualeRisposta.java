package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.PercentualeRispostaInvalidaExcpetion;

public class PercentualeRisposta {

	private LetteraRisposta letteraRisposta;
	private int percentuale;

	public PercentualeRisposta(LetteraRisposta letteraRisposta) throws PercentualeRispostaInvalidaExcpetion {
		if (letteraRisposta == null) {
			throw PercentualeRispostaInvalidaExcpetion.letteraRispostaAssente();
		}
		this.letteraRisposta = letteraRisposta;
	}

	public LetteraRisposta getLetteraRisposta() {
		return letteraRisposta;
	}

	public int getPercentuale() {
		return percentuale;
	}

	public void incrementaPercentuale() throws PercentualeRispostaInvalidaExcpetion {
		if (percentuale == 100) {
			throw PercentualeRispostaInvalidaExcpetion.percentualeFuoriLimite();
		}
		this.percentuale++;
	}

	@Override
	public String toString() {
		return   letteraRisposta.toString() + percentuale;
	}
}