package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.PercentualeFortunaNonInRangeException;

public class PercentualeFortuna {

	private int percentualeFortuna;

	public PercentualeFortuna(int percentualeFortuna) throws PercentualeFortunaNonInRangeException {
		super();

		if (percentualeFortuna < 20 || percentualeFortuna > 50) {

			throw new PercentualeFortunaNonInRangeException();

		}

		this.percentualeFortuna = percentualeFortuna;
	}

	public int getPercentualeFortuna() {
		return percentualeFortuna;
	}

}
