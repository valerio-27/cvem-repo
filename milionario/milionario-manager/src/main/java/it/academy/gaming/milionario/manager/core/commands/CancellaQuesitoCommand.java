package it.academy.gaming.milionario.manager.core.commands;

public class CancellaQuesitoCommand {
	private String testoQuesito;

	public CancellaQuesitoCommand(String testoQuesito) {
		super();
		this.testoQuesito = testoQuesito;
	}

	public String getTestoQuesito() {
		return testoQuesito;
	}

}
