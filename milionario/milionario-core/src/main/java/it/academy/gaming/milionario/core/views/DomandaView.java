package it.academy.gaming.milionario.core.views;

import it.academy.gaming.milionario.core.domain.Categoria;

public class DomandaView {
	private String testo;
	private Categoria categoria;
	private InformazioniView informazioniView;

	public DomandaView(String testo, Categoria categoria, InformazioniView informazioniView) {
		super();
		this.testo = testo;
		this.categoria = categoria;
		this.informazioniView = informazioniView;
	}

	public String getTesto() {
		return this.testo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public InformazioniView getInformazioniView() {
		return this.informazioniView;
	}

}
