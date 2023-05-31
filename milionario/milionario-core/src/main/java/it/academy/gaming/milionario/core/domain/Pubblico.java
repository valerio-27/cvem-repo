package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Pubblico {

	private Collection<PersonaPubblico> persone;
	private PercentualeFortuna percentualeFortuna;

	private Pubblico(Collection<PersonaPubblico> persone) {
		super();
		this.persone = persone;
	}

	public static Pubblico crea(RangeCulturaGenerale range) {
		Collection<PersonaPubblico> persone = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			persone.add(PersonaPubblico.genera(range));
		}
		return new Pubblico(persone);
	}

	public void vota(Quesito quesito) {

	}
}
