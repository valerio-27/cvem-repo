package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class Aiuti {

	private AiutoCasa aiutoCasa;
	private AiutoPubblico aiutoPubblico;

	public Aiuti(RangeCulturaGenerale rangeCulturaGenerale, PercentualeFortuna percentualeFortuna) {
		this.aiutoPubblico = new AiutoPubblico(rangeCulturaGenerale, percentualeFortuna);
		this.aiutoCasa = new AiutoCasa(rangeCulturaGenerale);
	}

	public void usaAiutoComputer(Quesito quesito) throws AiutoNonDisponibileException {
		AiutoComputer.vota(quesito);
	}

	public Votazione usaAiutoPubblico(Quesito quesito) throws AiutoNonDisponibileException {
		return aiutoPubblico.vota(quesito);
	}

	public Suggerimento usaAiutoCasa(Quesito quesito, Giocatore giocatore) throws AiutoNonDisponibileException {
		return aiutoCasa.vota(quesito, giocatore);
	}

	public boolean isAiutoCasaDisponibile() {
		return this.aiutoCasa.isDisponibile();
	}

	public boolean isAiutoPubblicoDisponibile() {
		return this.aiutoPubblico.isDisponibile();
	}

	public boolean isAiutoComputerDisponibile() {
		return AiutoComputer.isDisponibile();
	}

	public void ripristinaAiuti() {
		AiutoComputer.ripristina();
		this.aiutoCasa.ripristina();
		this.aiutoPubblico.ripristina();
	}
}