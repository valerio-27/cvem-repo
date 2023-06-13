package it.academy.gaming.milionario.core.domain;

public class PersonaDaCasa extends Persona {

	private PersonaDaCasa(CulturaGenerale culturaGenerale) {
		super(culturaGenerale);
	}

	public static PersonaDaCasa genera(RangeCulturaGenerale range) {
		return new PersonaDaCasa(CulturaGenerale.genera(range));
	}

}