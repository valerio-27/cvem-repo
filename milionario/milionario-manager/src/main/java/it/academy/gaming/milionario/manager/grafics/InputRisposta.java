package it.academy.gaming.milionario.manager.grafics;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;

public class InputRisposta {

	/**
	 * Risposta .String testo .boolean corretta
	 */
	private String testo;
	private boolean corretta;

	private InputRisposta(String testo, boolean corretta) {
		super();
		this.testo = testo;
		this.corretta = corretta;
	}

	public String getTesto() {
		return testo;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public static InputRisposta crea(String testo, String rispostaGiusta) throws FormatoFraseNonCorrettoException {

		controllaFrase(testo);
		return new InputRisposta(testo, isRispostaGiusta(rispostaGiusta));
	}

	private static boolean isRispostaGiusta(String rispostaGiusta) {
		if (rispostaGiusta.equalsIgnoreCase("SI")) {
			return true;
		}
		if (rispostaGiusta.equalsIgnoreCase("NO")) {
			return false;
		}

		throw new IllegalArgumentException("Opzione nn valida , specifica se e'la risposta giusta o meno");
	}

	private static void controllaFrase(String frase) throws FormatoFraseNonCorrettoException {
		/*
		 * verifico che sia un alfanumerico
		 */
		boolean fraseAccettabile = StringUtils.isNotBlank(frase);
		if (!fraseAccettabile) {
			throw new FormatoFraseNonCorrettoException("La risposta deve essere scritta in formato alfanumerico");
		}

	}

}
