package it.academy.gaming.milionario.core.domain;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;

//Domanda domanda 
//.String testo
//.Enum Categoria categoria
//.InformazioniDomanda informazioni NON obbligatorio

public class Domanda {
	private String testo;
	private Categoria categoria;
	private InformazioniDomanda informazione;

	public Domanda(String testo, Categoria categoria, InformazioniDomanda informazione)
			throws CreazioneDomandaException {
		if (StringUtils.isBlank(testo)) {
			throw CreazioneDomandaException.testoAssente();
		}
		if (categoria == null) {
			throw CreazioneDomandaException.categoriaAssente();
		}
		if (informazione == null) {
			throw CreazioneDomandaException.informazioniAssenti();
		}
		this.testo = testo;
		this.categoria = categoria;
		this.informazione = informazione;
	}

	public String getTesto() {
		return testo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public InformazioniDomanda getInformazioni() {
		return informazione;
	}

	public boolean haUrlImmagine() {

		if (informazione.getUrlImmagine() != null) {

			return true;
		}
		return false;
	}

	public boolean haUrlDocumentazione() {

		if (informazione.getUrlDocumentazione() != null) {

			return true;
		}
		return false;
	}

}