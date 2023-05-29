package it.academy.gaming.milionario.manager.grafics.requests;

public class ModificaRispostaRequest {
	private String testo;
	private boolean corretta;
	public ModificaRispostaRequest(String testo, boolean corretta) {
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
