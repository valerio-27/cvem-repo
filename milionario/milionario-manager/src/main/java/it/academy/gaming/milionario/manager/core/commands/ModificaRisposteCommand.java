package it.academy.gaming.milionario.manager.core.commands;

import java.util.ArrayList;
import java.util.List;

public class ModificaRisposteCommand {

	private String codiceQuesito;
	private List<String> nuoviTestiRisposte = new ArrayList<>();
	private int indiceRispostaGiusta;

	public ModificaRisposteCommand(List<String> nuoviTestiRisposte, int indiceRispostaGiusta, String codiceQuesito) {
		super();
		this.nuoviTestiRisposte.addAll(nuoviTestiRisposte);
		this.indiceRispostaGiusta = indiceRispostaGiusta;
		this.codiceQuesito = codiceQuesito;
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
