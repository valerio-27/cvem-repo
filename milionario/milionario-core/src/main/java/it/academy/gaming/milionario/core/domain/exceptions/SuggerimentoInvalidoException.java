package it.academy.gaming.milionario.core.domain.exceptions;

public class SuggerimentoInvalidoException extends Exception {

	private SuggerimentoInvalidoException(String message) {
		super(message);
	}

	public static SuggerimentoInvalidoException astenuta() {
		return new SuggerimentoInvalidoException(
				"Il suggerimento con accuratezza: Astenuta, non può contenere bookmarks di risposta");
	}

	public static SuggerimentoInvalidoException corretta() {
		return new SuggerimentoInvalidoException(
				"Il suggerimento con accuratezza: Corretta, deve contenere almeno una risposta corretta");
	}

	public static SuggerimentoInvalidoException imprecisa() {
		return new SuggerimentoInvalidoException(
				"Il suggerimento con accuratezza: Imprecisa, deve contenere almeno una risposta corretta e una sbagliata");
	}

	public static SuggerimentoInvalidoException sbagliata() {
		return new SuggerimentoInvalidoException(
				"Il suggerimento con accuratezza: Sbagliata, deve contenere almeno una risposta sbagliata e non deve contenere la risposta corretta");
	}

	public static SuggerimentoInvalidoException parametriInvalidi() {
		return new SuggerimentoInvalidoException("Il suggerimento presenta parametri invalidi");
	}

	public static SuggerimentoInvalidoException numeroBookmarksRispostaSbagliataInvalidi() {
		return new SuggerimentoInvalidoException(
				"Impossibile creare un suggerimento con più di un riferimento a una risposta sbagliata");
	}

}
