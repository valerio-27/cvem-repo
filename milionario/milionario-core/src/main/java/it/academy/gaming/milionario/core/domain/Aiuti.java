package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class Aiuti {

	public static void main(String[] args) throws Exception {
		QuesitoBuilder builder = Quesito.builder();

		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(
				"https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRBKouHO57uTjWH-ImFXgHxryC617ISal9NYFkomALCV2o0EgG5DqT1myoE1D5Ehi7a",
				null);

		Domanda domanda = new Domanda("Quanti pianeti ci sono nel sistema solare?", Categoria.SCIENZA,
				informazioniDomanda);
		builder.setDomanda(domanda);

		builder.setDifficolta(new Difficolta(13));

		Risposta risposta = Risposta.crea("9", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("7", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("10", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("8", true);
		builder.aggiungiRisposta(risposta);

		Quesito quesito = builder.build();

		Aiuti aiuti = new Aiuti(new RangeCulturaGenerale(30, 70), new PercentualeFortuna(30));

//		aiuti.usaAiutoComputer(quesito);

		System.out.println(aiuti.usaAiutoPubblico(quesito));

	}

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