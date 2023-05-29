package it.academy.gaming.milionario.manager.core.commands;

public class ModificaDifficoltaCommand {
	private String codiceQuesito;
	private int livelloDifficolta;

	public ModificaDifficoltaCommand(String codiceQuesito, int livelloDifficolta) {
		super();
		this.codiceQuesito = codiceQuesito;
		this.livelloDifficolta = livelloDifficolta;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

}
