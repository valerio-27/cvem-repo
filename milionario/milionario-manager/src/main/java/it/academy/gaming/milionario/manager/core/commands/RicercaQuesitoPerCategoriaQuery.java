package it.academy.gaming.milionario.manager.core.commands;

import it.academy.gaming.milionario.core.domain.Categoria;

public class RicercaQuesitoPerCategoriaQuery {
	private Categoria categoriaRicercata;

	public RicercaQuesitoPerCategoriaQuery(Categoria categoriaRicercata) {
		super();
		this.categoriaRicercata = categoriaRicercata;
	}

	public Categoria getCategoriaRicercata() {
		return categoriaRicercata;
	}

}
