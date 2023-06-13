package it.academy.gaming.milionario.manager.grafics.exceptions;

public class TestoSuggerimentoInvalidoException extends Exception {

	private TestoSuggerimentoInvalidoException(String message) {
		super(message);
	}

	public static TestoSuggerimentoInvalidoException testoSuggerimentoCorrettoErrato(String testo) {
		return new TestoSuggerimentoInvalidoException("Il testo '" + testo + "' non è corretto");
	}

	public static TestoSuggerimentoInvalidoException testoSuggerimentoImprecisoErrato(String testoSuggerimento) {
		return new TestoSuggerimentoInvalidoException("Il testo '" + testoSuggerimento + "' non è corretto");

	}

	public static TestoSuggerimentoInvalidoException testoSuggerimentoSbagliatoErrato(String testoSuggerimento) {
		return new TestoSuggerimentoInvalidoException("Il testo '" + testoSuggerimento + "' non è corretto");

	}

	public static TestoSuggerimentoInvalidoException testoSuggerimentoAstenutoErrato(String testoSuggerimento) {
		return new TestoSuggerimentoInvalidoException("Il testo '" + testoSuggerimento + "' non è corretto");

	}

}
