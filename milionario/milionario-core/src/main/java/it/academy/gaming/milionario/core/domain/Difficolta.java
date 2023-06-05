package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;

public class Difficolta {

	private static final int MINIMO = 1;
	private static final int MASSIMO = 15;

	private int livello;

	/**
	 * il livello di difficolta deve essere compreso tra il minimo e massimo
	 * 
	 * @param difficolta
	 * @throws DifficoltaNonInRangeException
	 */
	public Difficolta(int difficolta) throws DifficoltaNonInRangeException {
		if (difficolta < MINIMO || difficolta > MASSIMO) {
			throw new DifficoltaNonInRangeException(livello);
		}
		this.livello = difficolta;
	}

	public int getDifficolta() {
		return livello;
	}

	public static int getMinimo() {
		return MINIMO;
	}

	public static int getMassimo() {
		return MASSIMO;
	}

	 void incrementa() {
		 livello++;
	}
}