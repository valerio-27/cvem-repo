package it.academy.gaming.milionario.manager.grafics.requests;

import it.academy.gaming.milionario.core.domain.Categoria;

public class ModificaDomandaRequest {

	private String codiceQuesito;
	private String testoDomanda;
	private Categoria categoria;
	private String urlImmagine;
	private String urlDocumentazione;

	public ModificaDomandaRequest(String codiceQuesito, String testoDomanda, Categoria categoria, String urlImmagine,
			String urlDocumentazione) {
		super();
		this.codiceQuesito = codiceQuesito;
		this.testoDomanda = testoDomanda;
		this.categoria = categoria;
		this.urlImmagine = urlImmagine;
		this.urlDocumentazione = urlDocumentazione;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

	public String getTestoDomanda() {
		return testoDomanda;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getUrlImmagine() {
		return urlImmagine;
	}

	public String getUrlDocumentazione() {
		return urlDocumentazione;
	}

}
