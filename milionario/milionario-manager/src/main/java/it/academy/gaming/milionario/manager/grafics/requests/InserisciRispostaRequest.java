package it.academy.gaming.milionario.manager.grafics.requests;

public class InserisciRispostaRequest {
	private String testoRisposta;
	private boolean rispostaGiusta;

	public InserisciRispostaRequest(String testoRisposta, boolean rispostaGiusta) {
		super();
		this.testoRisposta = testoRisposta;
		this.rispostaGiusta = rispostaGiusta;
	}

	public String getTestoRisposta() {
		return testoRisposta;
	}

	public boolean isRispostaGiusta() {
		return rispostaGiusta;
	}

}
