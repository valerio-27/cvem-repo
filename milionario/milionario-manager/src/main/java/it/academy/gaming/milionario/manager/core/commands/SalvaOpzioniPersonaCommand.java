package it.academy.gaming.milionario.manager.core.commands;

public class SalvaOpzioniPersonaCommand {
	private int maxConoscenza;
	private int minConoscenza;
	private int percentualeFortuna;

	public SalvaOpzioniPersonaCommand(int maxConoscenza, int minConoscenza, int percentualeFortuna) {
		super();
		this.maxConoscenza = maxConoscenza;
		this.minConoscenza = minConoscenza;
		this.percentualeFortuna = percentualeFortuna;
	}

	public int getMaxConoscenza() {
		return maxConoscenza;
	}

	public int getMinConoscenza() {
		return minConoscenza;
	}

	public int getPercentualeFortuna() {
		return percentualeFortuna;
	}

}
