package it.academy.gaming.milionario.core.application;

import it.academy.gaming.milionario.core.domain.LetteraRisposta;

public class IndovinaRispostaCommand {

	private LetteraRisposta letteraRisposta;

	public IndovinaRispostaCommand(LetteraRisposta letteraRisposta) {
		this.letteraRisposta = letteraRisposta;
	}

	public LetteraRisposta getLetteraRisposta() {
		return letteraRisposta;
	}

}
