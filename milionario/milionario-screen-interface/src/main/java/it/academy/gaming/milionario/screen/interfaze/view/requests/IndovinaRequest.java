package it.academy.gaming.milionario.screen.interfaze.view.requests;

import it.academy.gaming.milionario.core.domain.LetteraRisposta;

public class IndovinaRequest {

	private LetteraRisposta letteraRisposta;

	public IndovinaRequest(LetteraRisposta letteraRisposta) {
		this.letteraRisposta = letteraRisposta;
	}

	public LetteraRisposta getLetteraRisposta() {
		return letteraRisposta;
	}

}
