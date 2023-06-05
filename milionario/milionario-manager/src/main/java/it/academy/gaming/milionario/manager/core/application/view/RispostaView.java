package it.academy.gaming.milionario.manager.core.application.view;

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

	@Override
	public String toString() {
		String rispostaGiusta = this.giusta ? "giusta" : "non giusta";
		return "\"" +testo +"\", "  + rispostaGiusta + ";";
	}
	

}
