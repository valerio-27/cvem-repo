package it.academy.gaming.milionario.manager.core.commands;

public class ModificaDifficoltaCommand {
	private String testoQuesito;
	private int livelloDifficolta;

	public ModificaDifficoltaCommand(String testoQuesito, int livelloDifficolta) {
		super();
		this.testoQuesito = testoQuesito;
		this.livelloDifficolta = livelloDifficolta;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

	public String getTestoQuesito() {
		return testoQuesito;
	}

}
