package it.academy.gaming.milionario.core.domain.codici;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;

public abstract class Codice {
	private String codice;

	protected Codice(String codice) throws CodiceInvalidoException {
		if (!(StringUtils.isAlphanumeric(codice) && codice.length() == 8)) {
			throw new CodiceInvalidoException(codice);
		}
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}

	
	
}
