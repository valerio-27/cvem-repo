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

	@Override
	public String toString() {

		this.urlImmagine = ((urlImmagine == null) ? "Non ci sono informazioni relative all'immagine"
				: "url immagine: " + urlImmagine);
		this.urlDocumentazione = ((urlDocumentazione == null) ? "Non ci sono informazioni relative alla documentazione"
				: "url documentazione: " + urlDocumentazione);

		return "Informazioni:\n"  + urlImmagine + "\n" + urlDocumentazione;
	}

}
