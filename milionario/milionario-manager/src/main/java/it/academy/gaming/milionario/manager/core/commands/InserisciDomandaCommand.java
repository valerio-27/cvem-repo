package it.academy.gaming.milionario.manager.core.commands;

import it.academy.gaming.milionario.core.domain.Categoria;

public class InserisciDomandaCommand {

	private String testoDomanda;
	private Categoria categoria;
	private String urlImmagne;
	private String urlDocumentazione;

	public InserisciDomandaCommand(String testoDomanda, Categoria categoria, String urlImmagne,
			String urlDocumentazione) {
		super();
		this.testoDomanda = testoDomanda;
		this.categoria = categoria;
		this.urlImmagne = urlImmagne;
		this.urlDocumentazione = urlDocumentazione;
	}

	public String getTestoDomanda() {
		return testoDomanda;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getUrlImmagne() {
		return urlImmagne;
	}

	public String getUrlDocumentazione() {
		return urlDocumentazione;
	}

}
