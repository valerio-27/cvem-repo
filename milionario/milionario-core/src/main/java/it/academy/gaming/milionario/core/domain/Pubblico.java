package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Pubblico {

	Collection<Persona> persone;

	private Pubblico(Collection<Persona> persone) {
		super();
		this.persone = persone;
	}

	public static Pubblico crea() {
		Collection<Persona> persone = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			persone.add(Persona.genera());
		}
		return new Pubblico(persone);
	}
}
