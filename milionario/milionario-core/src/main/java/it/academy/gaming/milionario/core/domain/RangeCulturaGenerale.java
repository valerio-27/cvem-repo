package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;

public class RangeCulturaGenerale {

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

}
