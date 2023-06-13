package it.academy.gaming.milionario.core.domain.exceptions;

import it.academy.gaming.milionario.core.domain.Difficolta;

public class DifficoltaNonInRangeException extends Exception {

	public DifficoltaNonInRangeException(int livelloDifficolta) {
		super("Il livello di difficolta: " + livelloDifficolta + " deve essere compreso tra " + Difficolta.getMinimo()
				+ " e " + Difficolta.getMassimo());
	}

}