package it.academy.gaming.milionario.manager.grafics;

public class InputInformazioniDomanda {
	private String urlImmagine;
	private String urlDocumentazione;

	public InputInformazioniDomanda(String urlImmagine, String urlDocumentazione) {
		super();
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
