package it.academy.gaming.milionario.manager.grafics.requests;

import java.util.ArrayList;
import java.util.List;

public class ModificaRisposteRequest {
	private String codiceQuesito;
	private List<ModificaRispostaRequest> nuoveRisposte = new ArrayList<>();

	public ModificaRisposteRequest(String codiceQuesito, List<ModificaRispostaRequest> risposteInserite) {
		super();
		this.nuoveRisposte.addAll(risposteInserite);

		this.codiceQuesito = codiceQuesito;
	}

	public List<ModificaRispostaRequest> getNuoveRisposte() {
		return nuoveRisposte;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

}
