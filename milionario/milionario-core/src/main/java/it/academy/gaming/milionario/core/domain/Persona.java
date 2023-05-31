package it.academy.gaming.milionario.core.domain;

public abstract class Persona {

	private CulturaGenerale culturaGenerale;

	protected Persona(CulturaGenerale culturaGenerale) {
		this.culturaGenerale = culturaGenerale;
	}

	
//	//TODO range cultura generale
//	public static Persona genera() {
//		return new Persona(CulturaGenerale.genera());
//	}

	protected CulturaGenerale getCulturaGenerale() {
		return culturaGenerale;
	}

}
