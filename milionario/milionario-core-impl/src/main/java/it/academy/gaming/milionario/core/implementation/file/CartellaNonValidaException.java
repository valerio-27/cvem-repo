package it.academy.gaming.milionario.core.implementation.file;

import java.io.File;

public class CartellaNonValidaException extends Exception {

	private CartellaNonValidaException(String message) {
		super(message);
	}

	public static CartellaNonValidaException cartellaInvalida(File cartella) {
		return new CartellaNonValidaException("La cartella" + cartella + " non è valida");
	}

}
