package it.academy.gaming.milionario.manager.core.commands;

public class ModificaRispostaCommand {
	private String testo;
	private boolean corretta;

	public ModificaRispostaCommand(String testo, boolean corretta) {
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

}
