package it.academy.gaming.milionario.core.domain.exceptions;

import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;

public class NumeroMassimoRisposteSuperatoException extends Exception {

	public NumeroMassimoRisposteSuperatoException() {
		super("Superato il numero massimo di " + QuesitoBuilder.getNumeroMassimoRisposte() + " risposte per quesito ");
	}

}