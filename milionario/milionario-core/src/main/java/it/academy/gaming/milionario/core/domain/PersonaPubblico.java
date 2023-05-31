package it.academy.gaming.milionario.core.domain;

public class PersonaPubblico extends Persona {

	private PersonaPubblico(CulturaGenerale culturaGenerale) {
		super(culturaGenerale);
	}

	// TODO range cultura generale
	public static PersonaPubblico genera() {
		return new PersonaPubblico(null);
	}

}
