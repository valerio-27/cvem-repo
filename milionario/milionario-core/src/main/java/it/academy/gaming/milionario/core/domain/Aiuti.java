package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

/**
 * rimane instanziato per tutto il ciclo di vita dell'applicazione
 * 
 * @author Valerio.Crispini
 *
 */
public class Aiuti {

	private AiutoCasa aiutoCasa;
	private AiutoPubblico aiutoPubblico;

	public Aiuti(RangeCulturaGenerale rangeCulturaGenerale, PercentualeFortuna percentualeFortuna,
			Giocatore giocatore) {
		this.aiutoPubblico = new AiutoPubblico(rangeCulturaGenerale, percentualeFortuna);
		this.aiutoCasa = new AiutoCasa(rangeCulturaGenerale, giocatore);
		AiutoComputer.ripristina();
	}

	public void usaAiutoComputer(Quesito quesito) throws AiutoNonDisponibileException {
		AiutoComputer.vota(quesito);
	}

	public Votazione usaAiutoPubblico(Quesito quesito) throws AiutoNonDisponibileException {
		return aiutoPubblico.vota(quesito);
	}

	public Suggerimento usaAiutoCasa(Quesito quesito) throws AiutoNonDisponibileException {
		return aiutoCasa.vota(quesito);
	}

	public void ripristinaAiuti(Giocatore giocatore) {
		AiutoComputer.ripristina();
		this.aiutoCasa.ripristina(giocatore);
		this.aiutoPubblico.ripristina();
	}
}