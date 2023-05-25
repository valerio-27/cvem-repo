package it.academy.gaming.milionario.manager.grafics.requests;

public class RecuperaQuesitoRequest {
	private String testoQuesitoRicercato;

	public RecuperaQuesitoRequest(String testoQuesitoRicercato) {
		super();
		this.testoQuesitoRicercato = testoQuesitoRicercato;
	}

	public String getTestoQuesitoRicercato() {
		return testoQuesitoRicercato;
	}

}
