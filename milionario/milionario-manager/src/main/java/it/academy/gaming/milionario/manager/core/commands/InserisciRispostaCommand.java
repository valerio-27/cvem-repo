package it.academy.gaming.milionario.manager.core.commands;

public class InserisciRispostaCommand {
	private String testoRisposta;
	private boolean rispostaGiusta;

	public InserisciRispostaCommand(String testoRisposta, boolean rispostaGiusta) {
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
