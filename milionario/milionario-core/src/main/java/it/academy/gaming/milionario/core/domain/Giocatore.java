package it.academy.gaming.milionario.core.domain;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.exceptions.NomeNonValidoException;

public class Giocatore {

	private String nome;

	public Giocatore(String nome) throws NomeNonValidoException {
		if (!StringUtils.isAlpha(nome)) {
			throw new NomeNonValidoException(nome);
		}
		this.nome = StringUtils.capitalize(nome);
	}

	public String getNome() {
		return nome;
	}

}