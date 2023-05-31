package it.academy.gaming.milionario.core.domain;

public class PercentualeRisposta {

	private LetteraRisposta letteraRisposta;
	private int percentuale = 0;

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

}