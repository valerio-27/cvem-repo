package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class AiutoPubblico {

	private Pubblico pubblico;
	private boolean disponibile = true;

	public AiutoPubblico(RangeCulturaGenerale range, PercentualeFortuna percentualeFortuna) {
		this.pubblico = Pubblico.crea(range, percentualeFortuna);
	}

	public Votazione vota(Quesito quesito) throws AiutoNonDisponibileException {
		if (!disponibile) {
			throw AiutoNonDisponibileException.aiutoPubblico();
		}
		disponibile = false;
		return pubblico.vota(quesito);
	}

	public void ripristina() {
		this.disponibile=true;
	}

}
