package it.academy.gaming.milionario.manager.grafics.requests;

import it.academy.gaming.milionario.core.domain.Categoria;

public class RicercaQuesitoPerCategoriaRequest {
	private Categoria categoriaRicercata;

	public RicercaQuesitoPerCategoriaRequest(Categoria categoriaRicercata) {
		super();
		this.categoriaRicercata = categoriaRicercata;
	}

	public Categoria getCategoriaRicercata() {
		return categoriaRicercata;
	}

}
