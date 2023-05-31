package it.academy.gaming.milionario.core.domain.exceptions;

public class PercentualeFortunaNonInRangeException extends Exception {

	public PercentualeFortunaNonInRangeException() {
		super("La percentuale inserita non è valida, deve essere compresa tra 20 e 50 ");
	}

}
