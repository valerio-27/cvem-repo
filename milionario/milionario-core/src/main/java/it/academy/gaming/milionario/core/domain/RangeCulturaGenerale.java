package it.academy.gaming.milionario.core.domain;

import java.util.Random;

import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;

public class RangeCulturaGenerale {

	private static Random random = new Random();
	private static int LIMITE_MINIMA_CONOSCENZA = 1;
	private static int LIMITE_MASSIMA_CONOSCENZA = 100;
	private int min;
	private int max;

	public RangeCulturaGenerale(int min, int max) throws CulturaGeneraleNonInRangeException {
		if (min < LIMITE_MINIMA_CONOSCENZA || max > LIMITE_MASSIMA_CONOSCENZA || min >= max) {
			throw new CulturaGeneraleNonInRangeException();
		}
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getValore() {
		return random.nextInt(max + 1 - min) + min;
	}

	public static int getLIMITE_MINIMA_CONOSCENZA() {
		return LIMITE_MINIMA_CONOSCENZA;
	}

	public static int getLIMITE_MASSIMA_CONOSCENZA() {
		return LIMITE_MASSIMA_CONOSCENZA;
	}

}
