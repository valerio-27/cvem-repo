package it.academy.gaming.milionario.core.domain;

public class AiutoPubblico extends Aiuto {

	protected AiutoPubblico(Quesito quesito) {
		super(quesito);
	}

	public Votazione votazione vota(Quesito quesito) {
		Pubblico pubblico = Pubblico.crea();

	}

}
