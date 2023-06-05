package it.academy.gaming.milionario.core.views;

public class OpzioniPersonaView {
	private int maxConoscenza;
	private int minConoscenza;
	private int percentualeFortuna;

	public OpzioniPersonaView(int maxConoscenza, int minConoscenza, int percentualeFortuna) {
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
