package it.academy.gaming.milionario.core.domain;

public enum Accuratezza {
	CORRETTA, IMPRECISA, SBAGLIATA, ASTENUTA;

	public static Accuratezza parse(String accuratezzaString) {

		if (accuratezzaString.equals("CORRETTA")) {
			return CORRETTA;
		} else if (accuratezzaString.equals("SBAGLIATA")) {
			return SBAGLIATA;
		} else if (accuratezzaString.equals("IMPRECISA")) {
			return IMPRECISA;
		}
		return ASTENUTA;
	}
}