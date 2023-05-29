package it.academy.gaming.milionario.manager.core.commands;

public class CancellaQuesitoCommand {
	private String codiceQuesito;

	public CancellaQuesitoCommand(String codiceQuesito) {
		super();
		this.codiceQuesito = codiceQuesito;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

}
