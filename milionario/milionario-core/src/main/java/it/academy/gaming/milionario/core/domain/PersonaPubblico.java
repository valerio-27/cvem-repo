package it.academy.gaming.milionario.core.domain;

public class PersonaPubblico extends Persona {

	private PersonaPubblico(CulturaGenerale culturaGenerale) {
		super(culturaGenerale);
	}

	public static PersonaPubblico genera(RangeCulturaGenerale range) {
		return new PersonaPubblico(CulturaGenerale.genera(range));
	}

}
