package it.academy.gaming.milionario.core.application.views;

import it.academy.gaming.milionario.core.domain.LetteraRisposta;
import it.academy.gaming.milionario.core.domain.Risposta;

public class RispostaView {

	private String testo;
	private LetteraRisposta lettera;

	public RispostaView(Risposta risposta) {

		this.testo = risposta.getTesto();
		this.lettera = risposta.getLettera();
	}

	public String getTesto() {
		return testo;
	}

	public LetteraRisposta getLettera() {
		return lettera;
	}

	@Override
	public String toString() {
		return "RispostaView [testo=" + testo + ", lettera=" + lettera + "]";
	}

	
}
