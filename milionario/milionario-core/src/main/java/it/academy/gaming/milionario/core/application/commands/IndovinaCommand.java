package it.academy.gaming.milionario.core.application.commands;

import it.academy.gaming.milionario.core.domain.LetteraRisposta;

public class IndovinaCommand {

	private LetteraRisposta letteraRisposta;

	public IndovinaCommand(LetteraRisposta letteraRisposta) {
		this.letteraRisposta = letteraRisposta;
	}

	public LetteraRisposta getLetteraRisposta() {
		return letteraRisposta;
	}

}
