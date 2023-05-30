package it.academy.gaming.milionario.core.domain;

public class Aiuti {

	private AiutoComputer aiutoComputer;
	private AiutoCasa aiutoCasa;
	private AiutoPubblico aiutoPubblico;

	public Aiuti(Quesito quesito) {
		this.aiutoComputer = new AiutoComputer(quesito);
		this.aiutoCasa = new AiutoCasa(quesito);
		this.aiutoPubblico = new AiutoPubblico(quesito);
	}

	/**
	 * viene chiamato all'inizio di un quesito
	 * 
	 * @param quesito
	 */
	public void aggiornaQuesito(Quesito quesito) {
		aiutoCasa.aggiornaQuesito(quesito);
		aiutoComputer.aggiornaQuesito(quesito);
		aiutoPubblico.aggiornaQuesito(quesito);
	}

}