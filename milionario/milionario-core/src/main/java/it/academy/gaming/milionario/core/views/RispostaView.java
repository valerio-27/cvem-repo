package it.academy.gaming.milionario.core.views;

public class RispostaView {

	private String testo;
	private boolean giusta;

	public RispostaView(String testo, boolean giusta) {
		super();
		this.testo = testo;
		this.giusta = giusta;
	}

	public String getTesto() {
		return testo;
	}

	public boolean isGiusta() {
		return giusta;
	}

}
