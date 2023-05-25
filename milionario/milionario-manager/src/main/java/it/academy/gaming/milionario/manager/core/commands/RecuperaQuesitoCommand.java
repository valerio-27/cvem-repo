package it.academy.gaming.milionario.manager.core.commands;

public class RecuperaQuesitoCommand {
	private String testoQuesitoRicercato;

	public RecuperaQuesitoCommand(String testoQuesitoRicercato) {
		super();
		this.testoQuesitoRicercato = testoQuesitoRicercato;
	}

	public String getTestoQuesitoRicercato() {
		return testoQuesitoRicercato;
	}

}
