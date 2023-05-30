package it.academy.gaming.milionario.core.domain;

import java.util.Random;

public abstract class Aiuto {

	protected static Random random = new Random();

	private Quesito quesito;
	protected boolean disponibile = true;

	protected Aiuto(Quesito quesito) {
		this.quesito = quesito;
	}

	protected Quesito getQuesito() {
		return quesito;
	}

	protected void aggiornaQuesito(Quesito quesito) {
		if (disponibile) {
			this.quesito = quesito;
		}
	}

}
