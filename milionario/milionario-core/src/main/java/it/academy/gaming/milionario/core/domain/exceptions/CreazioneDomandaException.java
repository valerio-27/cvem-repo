package it.academy.gaming.milionario.core.domain.exceptions;

public class CreazioneDomandaException extends Exception {

	private CreazioneDomandaException(String string) {
		super(string);
	}

	public static CreazioneDomandaException testoAssente() {
		return new CreazioneDomandaException("La domanda necessita di un testo");
	}

	public static CreazioneDomandaException categoriaAssente() {
		return new CreazioneDomandaException("La domanda necessita di una categoria");
	}

	public static CreazioneDomandaException informazioniAssenti() {
		return new CreazioneDomandaException("La domanda necessita di informazioni");
	}
}
