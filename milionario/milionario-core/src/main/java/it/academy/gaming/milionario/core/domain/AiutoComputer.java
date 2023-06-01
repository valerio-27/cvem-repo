package it.academy.gaming.milionario.core.domain;

import java.util.Random;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class AiutoComputer {

	private static Random random = new Random();
	private static boolean disponibile = true;

	public static void vota(Quesito quesito) throws AiutoNonDisponibileException {
		if (!disponibile) {
			throw AiutoNonDisponibileException.aiutoComputer();
		}
		disponibile = false;

		Risposta[] risposte = quesito.getRisposte();

		int risposteEliminate = 0;

		while (risposteEliminate < 2) {

			int indice = random.nextInt(risposte.length);
			Risposta risposta = risposte[indice];

			if (risposta!=null&&!risposta.isCorretta()) {
				risposte[indice] = null;
				risposteEliminate++;
			}
		}
		disponibile = false;
	}

}
