package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.PercentualeFortunaNonInRangeException;

public class PercentualeFortuna {
	private static int LIMITE_DI_FORTUNA_MINIMO = 20;
	private static int LIMITE_DI_FORTUNA_MASSIMO = 50;
	private int percentualeFortuna;

	public PercentualeFortuna(int percentualeFortuna) throws PercentualeFortunaNonInRangeException {
		if (percentualeFortuna < LIMITE_DI_FORTUNA_MINIMO || percentualeFortuna > LIMITE_DI_FORTUNA_MASSIMO) {
			throw new PercentualeFortunaNonInRangeException();
		}

		this.percentualeFortuna = percentualeFortuna;
	}

	public int getPercentualeFortuna() {
		return percentualeFortuna;
	}

	public static int getLIMITE_DI_FORTUNA_MINIMO() {
		return LIMITE_DI_FORTUNA_MINIMO;
	}

	public static int getLIMITE_DI_FORTUNA_MASSIMO() {
		return LIMITE_DI_FORTUNA_MASSIMO;
	}

}