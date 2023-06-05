package it.academy.gaming.milionario.core.domain.codici;

import org.apache.commons.lang3.RandomStringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;

public class CodiceQuesito extends Codice {

	private CodiceQuesito(String codice) throws CodiceInvalidoException {
		super(codice);
	}

	public static CodiceQuesito crea() {
		CodiceQuesito codiceQuesito = null;
		try {
			codiceQuesito = new CodiceQuesito(RandomStringUtils.randomAlphanumeric(8));
		} catch (CodiceInvalidoException ignored) {
		}
		return codiceQuesito;
	}

	public static CodiceQuesito parse(String codice) throws CodiceInvalidoException {
		return new CodiceQuesito(codice);
	}

}
