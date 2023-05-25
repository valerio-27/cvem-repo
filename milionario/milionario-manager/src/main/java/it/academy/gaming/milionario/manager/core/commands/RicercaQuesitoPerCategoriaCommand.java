package it.academy.gaming.milionario.manager.core.commands;

import it.academy.gaming.milionario.core.domain.Categoria;

public class RicercaQuesitoPerCategoriaCommand {
	private Categoria categoriaRicercata;

	public RicercaQuesitoPerCategoriaCommand(Categoria categoriaRicercata) {
		super();
		this.categoriaRicercata = categoriaRicercata;
	}

	public Categoria getCategoriaRicercata() {
		return categoriaRicercata;
	}

}
