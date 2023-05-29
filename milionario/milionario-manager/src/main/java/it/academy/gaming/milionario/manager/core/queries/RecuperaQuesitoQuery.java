package it.academy.gaming.milionario.manager.core.queries;

public class RecuperaQuesitoQuery {
	private String codiceQuesitoRicercato;

	public RecuperaQuesitoQuery(String codiceQuesitoRicercato) {
		super();
		this.codiceQuesitoRicercato = codiceQuesitoRicercato;
	}

	public String getCodiceQuesitoRicercato() {
		return codiceQuesitoRicercato;
	}

}
