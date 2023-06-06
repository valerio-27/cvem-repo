package it.academy.gaming.milionario.core.application.commands;

public class IniziaPartitaCommand {

	private String nome;

	public IniziaPartitaCommand(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}