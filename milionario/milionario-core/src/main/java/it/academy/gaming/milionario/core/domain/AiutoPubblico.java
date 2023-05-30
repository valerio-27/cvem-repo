package it.academy.gaming.milionario.core.domain;

public class AiutoPubblico extends Aiuto {

	protected AiutoPubblico(Quesito quesito) {
		super(quesito);
	}

	public void usa() {
		Pubblico pubblico = Pubblico.crea();

	}

}
