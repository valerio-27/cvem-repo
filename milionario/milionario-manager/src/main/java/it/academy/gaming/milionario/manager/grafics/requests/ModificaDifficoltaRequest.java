package it.academy.gaming.milionario.manager.grafics.requests;

public class ModificaDifficoltaRequest {
	private String testoQuesito;
	private int livelloDifficolta;

	public ModificaDifficoltaRequest(String testoQuesito, int livelloDifficolta) {
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
