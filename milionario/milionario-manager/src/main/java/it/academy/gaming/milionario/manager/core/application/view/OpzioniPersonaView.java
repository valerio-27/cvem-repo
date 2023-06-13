package it.academy.gaming.milionario.manager.core.application.view;

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

	public int getMaxCulturaGenerale() {
		return maxConoscenza;
	}

	public int getMinCulturaGenerale() {
		return minConoscenza;
	}

	public int getPercentualeFortuna() {
		return percentualeFortuna;
	}

}
