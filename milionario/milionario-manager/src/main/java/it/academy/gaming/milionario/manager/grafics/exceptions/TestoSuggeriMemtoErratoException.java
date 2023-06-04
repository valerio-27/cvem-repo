package it.academy.gaming.milionario.manager.grafics.exceptions;

public class TestoSuggeriMemtoErratoException extends Exception {

	private TestoSuggeriMemtoErratoException(String message) {
		super(message);
	}

	public static TestoSuggeriMemtoErratoException testoSuggerimentoCorrettoErrato(String testo) {
		return new TestoSuggeriMemtoErratoException("Il testo non e' corretto " + testo);
	}

	public static TestoSuggeriMemtoErratoException testoSuggerimentoImprecisoErrato(String testoSuggerimento) {
		return new TestoSuggeriMemtoErratoException("Il testo non e' corretto " + testoSuggerimento);

	}

	public static TestoSuggeriMemtoErratoException testoSuggerimentoSbagliatoErrato(String testoSuggerimento) {
		return new TestoSuggeriMemtoErratoException("Il testo non e' corretto " + testoSuggerimento);

	}

	public static TestoSuggeriMemtoErratoException testoSuggerimentoAstenutoErrato(String testoSuggerimento) {
		return new TestoSuggeriMemtoErratoException("Il testo non e' corretto " + testoSuggerimento);

	}

}
