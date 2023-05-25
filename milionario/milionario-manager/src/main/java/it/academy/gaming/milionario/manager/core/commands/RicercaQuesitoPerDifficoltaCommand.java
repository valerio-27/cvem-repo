package it.academy.gaming.milionario.manager.core.commands;

public class RicercaQuesitoPerDifficoltaCommand {

	private int livelloDifficolta;

	public RicercaQuesitoPerDifficoltaCommand(int livelloDifficolta) {
		super();
		this.livelloDifficolta = livelloDifficolta;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

}
