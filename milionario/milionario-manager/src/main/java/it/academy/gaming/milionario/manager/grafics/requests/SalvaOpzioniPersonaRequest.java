package it.academy.gaming.milionario.manager.grafics.requests;

public class SalvaOpzioniPersonaRequest {
	private int maxConoscenza;
	private int minConoscenza;
	private int percentualeFortuna;

	public SalvaOpzioniPersonaRequest(int maxConoscenza, int minConoscenza, int percentualeFortuna) {
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
