package it.academy.gaming.milionario.core.domain.exceptions;

public class SuggerimentoDaCasaInvalidoException extends Exception {

	private SuggerimentoDaCasaInvalidoException(String message) {
		super(message);
	}

	public static SuggerimentoDaCasaInvalidoException astenuta() {
		return new SuggerimentoDaCasaInvalidoException(
				"Il suggerimento con accuratezza: Astenuta, non può contenere bookmarks di risposta");
	}

	public static SuggerimentoDaCasaInvalidoException corretta() {
		return new SuggerimentoDaCasaInvalidoException(
				"Il suggerimento con accuratezza: Corretta, deve contenere almeno una risposta corretta");
	}

	public static SuggerimentoDaCasaInvalidoException imprecisa() {
		return new SuggerimentoDaCasaInvalidoException(
				"Il suggerimento con accuratezza: Imprecisa, deve contenere almeno una risposta corretta e una sbagliata");
	}

	public static SuggerimentoDaCasaInvalidoException sbagliata() {
		return new SuggerimentoDaCasaInvalidoException(
				"Il suggerimento con accuratezza: Sbagliata, deve contenere almeno una risposta sbagliata e non deve contenere la risposta corretta");
	}

	public static SuggerimentoDaCasaInvalidoException parametriInvalidi() {
		return new SuggerimentoDaCasaInvalidoException("il suggerimento presenta parametri invalidi");
	}

	public static SuggerimentoDaCasaInvalidoException numeroBookmarksRispostaSbagliataInvalidi() {
		return new SuggerimentoDaCasaInvalidoException(
				"impossibile creare un suggerimento con più di un riferimento a una risposta sbagliata");
	}

}
