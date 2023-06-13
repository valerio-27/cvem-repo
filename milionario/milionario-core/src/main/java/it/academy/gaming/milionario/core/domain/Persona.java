package it.academy.gaming.milionario.core.domain;

public abstract class Persona {

	private CulturaGenerale culturaGenerale;

	protected Persona(CulturaGenerale culturaGenerale) {
		this.culturaGenerale = culturaGenerale;
	}

	protected CulturaGenerale getCulturaGenerale() {
		return culturaGenerale;
	}

}