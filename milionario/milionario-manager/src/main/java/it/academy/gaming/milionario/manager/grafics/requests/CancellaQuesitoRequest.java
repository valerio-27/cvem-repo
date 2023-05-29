package it.academy.gaming.milionario.manager.grafics.requests;

public class CancellaQuesitoRequest {
	private String codiceQuesito;

	public CancellaQuesitoRequest(String codiceQuesito) {
		super();
		this.codiceQuesito = codiceQuesito;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

}
