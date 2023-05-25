package it.academy.gaming.milionario.core.domain;

public class InformazioniDomanda {

	private String urlImmagine;
	private String urlDocumentazione;

	public InformazioniDomanda(String urlImmagine, String urlDocumentazione) {
		this.urlImmagine = urlImmagine;
		this.urlDocumentazione = urlDocumentazione;
	}

	public String getUrlImmagine() {
		return urlImmagine;
	}

	public String getUrlDocumentazione() {
		return urlDocumentazione;
	}

}