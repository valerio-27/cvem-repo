package it.academy.gaming.milionario.manager.grafics.requests;

public class RicercaQuesitoPerDifficoltaRequest {
	private int livelloDifficolta;

	public RicercaQuesitoPerDifficoltaRequest(int livelloDifficolta) {
		super();
		this.livelloDifficolta = livelloDifficolta;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

}
