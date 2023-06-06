package it.academy.gaming.milionario.core.domain.codici;

import org.apache.commons.lang3.RandomStringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;

public class CodiceSuggerimento extends Codice {

	private CodiceSuggerimento(String codice) throws CodiceInvalidoException {
		super(codice);
	}

	public static CodiceSuggerimento crea() {
		CodiceSuggerimento codiceSuggerimento = null;
		try {
			codiceSuggerimento = new CodiceSuggerimento(RandomStringUtils.randomAlphanumeric(8));
		} catch (CodiceInvalidoException ignored) {
		}
		return codiceSuggerimento;
	}

	public static CodiceSuggerimento parse(String codice) throws CodiceInvalidoException {
		return new CodiceSuggerimento(codice);
	}

}
