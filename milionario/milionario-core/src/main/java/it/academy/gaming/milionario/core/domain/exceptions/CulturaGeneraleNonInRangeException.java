package it.academy.gaming.milionario.core.domain.exceptions;

import it.academy.gaming.milionario.core.domain.Difficolta;

public class CulturaGeneraleNonInRangeException extends Exception {

	public CulturaGeneraleNonInRangeException() {
		super("Il range deve essere compreso tra 1 e 100 ");
	}

}
