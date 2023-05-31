package it.academy.gaming.milionario.core.domain;

public class SuccessoDomanda {

	// TODO indipendente dal numero delle risposte
	public static int calcola(Quesito quesito) {

		int difficoltaQuesito = quesito.getDifficolta().getDifficolta();

		int percentualeSuccesso = 0;

		if (difficoltaQuesito <= 7) {

			percentualeSuccesso = (7 - difficoltaQuesito) * 10;

			return percentualeSuccesso;
		}
		percentualeSuccesso = -((difficoltaQuesito - 7) * 5);

		return percentualeSuccesso;
	}

}