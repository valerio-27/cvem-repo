package it.academy.gaming.milionario.core.views;

public class InformazioniView {
	private String urlImmagine;
	private String urlDocumentazione;

	public InformazioniView(String urlImmagine, String urlDocumentazione) {
		super();
		this.urlImmagine = urlImmagine;
		this.urlDocumentazione = urlDocumentazione;
	}

	public String getUrlImmagine() {
		return this.urlImmagine;
	}

	public String getUrlDocumentazione() {
		return this.urlDocumentazione;
	}

}
