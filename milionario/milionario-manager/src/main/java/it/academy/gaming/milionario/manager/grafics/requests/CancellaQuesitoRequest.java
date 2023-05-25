package it.academy.gaming.milionario.manager.grafics.requests;

public class CancellaQuesitoRequest {
	private String testoQuesito;

	public CancellaQuesitoRequest(String testoQuesito) {
		super();
		this.testoQuesito = testoQuesito;
	}

	public String getTestoQuesito() {
		return testoQuesito;
	}

}
