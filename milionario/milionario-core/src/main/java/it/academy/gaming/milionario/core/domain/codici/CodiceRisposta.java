package it.academy.gaming.milionario.core.domain.codici;

import org.apache.commons.lang3.RandomStringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;

public class CodiceRisposta extends Codice {

	private CodiceRisposta(String codice) throws CodiceInvalidoException {
		super(codice);
	}

	public static CodiceRisposta crea() {
		CodiceRisposta codiceRisposta = null;
		try {
			codiceRisposta = new CodiceRisposta(RandomStringUtils.randomAlphanumeric(8));
		} catch (CodiceInvalidoException ignored) {
		}
		return codiceRisposta;
	}

	public static CodiceRisposta parse(String codice) throws CodiceInvalidoException {
		return new CodiceRisposta(codice);
	}

}
