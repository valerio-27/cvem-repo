package it.academy.gaming.milionario.manager.grafics.requests;

import java.util.ArrayList;
import java.util.List;

public class ModificaRisposteRequest {
	private String codiceQuesito;
	private List<String> nuoviTestiRisposte = new ArrayList<>();
	private int indiceRispostaGiusta;

	public ModificaRisposteRequest(List<String> nuoviTestiRisposte, int indiceRispostaGiusta,String codiceQuesito) {
		super();
		this.nuoviTestiRisposte.addAll(nuoviTestiRisposte);
		this.indiceRispostaGiusta = indiceRispostaGiusta;
		this.codiceQuesito=codiceQuesito;
	}

	public List<String> getNuoviTestiRisposte() {
		return nuoviTestiRisposte;
	}

	public int getIndiceRispostaGiusta() {
		return indiceRispostaGiusta;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}
	

}
