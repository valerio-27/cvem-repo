package it.academy.gaming.milionario.core.domain;

public class Persona {

	private CulturaGenerale culturaGenerale;

	private Persona(CulturaGenerale culturaGenerale) {
		this.culturaGenerale = culturaGenerale;
	}

	public static Persona genera() {
		return new Persona(CulturaGenerale.genera());
	}

	public CulturaGenerale getCulturaGenerale() {
		return culturaGenerale;
	}

}
