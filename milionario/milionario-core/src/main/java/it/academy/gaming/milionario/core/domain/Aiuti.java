package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class Aiuti {

//	private AiutoCasa aiutoCasa;
	private AiutoPubblico aiutoPubblico;

	public Aiuti(RangeCulturaGenerale rangeCulturaGenerale, PercentualeFortuna percentualeFortuna) {
		this.aiutoPubblico = new AiutoPubblico(rangeCulturaGenerale, percentualeFortuna);
//		this.aiutoCasa = new AiutoCasa(quesito);
	}

	public void usaAiutoComputer(Quesito quesito) throws AiutoNonDisponibileException {
		AiutoComputer.vota(quesito);
	}

	public Votazione usaAiutoPubblico(Quesito quesito) throws AiutoNonDisponibileException {
		return aiutoPubblico.vota(quesito);
	}

	// TODO
	public void ripristinaAiuti() {

	}

}