package it.academy.gaming.milionario.core.domain;

import org.apache.commons.lang3.RandomStringUtils;

public class CodiceQuesito {

	private String codice;

	private CodiceQuesito(String codice) {
		this.codice = codice;
	}

	public static CodiceQuesito crea() {
		return new CodiceQuesito(RandomStringUtils.randomAlphanumeric(8));
	}

	public String getCodice() {
		return codice;
	}

}
