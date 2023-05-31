package it.academy.gaming.milionario.core.domain;

import java.util.Random;

import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;

public class RangeCulturaGenerale {

	private static Random random = new Random();

	private int min;
	private int max;

	public RangeCulturaGenerale(int min, int max) throws CulturaGeneraleNonInRangeException {
		super();

		if (min < 1 || max > 100) {

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

}
