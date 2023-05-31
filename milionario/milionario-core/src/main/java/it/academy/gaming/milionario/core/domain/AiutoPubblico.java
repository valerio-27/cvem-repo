package it.academy.gaming.milionario.core.domain;

public class AiutoPubblico extends Aiuto {

	private RangeCulturaGenerale range;

	protected AiutoPubblico(Quesito quesito, RangeCulturaGenerale range) {
		super(quesito);
		this.range = range;
	}

	public Votazione vota(Quesito quesito) {
		Pubblico pubblico = Pubblico.crea(range);
		return null;
	}

}
