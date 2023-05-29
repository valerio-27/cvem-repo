package it.academy.gaming.milionario.manager.core.commands;

import java.util.ArrayList;
import java.util.List;

public class ModificaRisposteCommand {

	private String codiceQuesito;
	private List<ModificaRispostaCommand> nuoveRisposte = new ArrayList<>();

	public ModificaRisposteCommand(List<ModificaRispostaCommand> nuoveRisposte, 
			String codiceQuesito) {
		super();
		this.nuoveRisposte.addAll(nuoveRisposte);
		this.codiceQuesito = codiceQuesito;
	}

	public List<ModificaRispostaCommand> getNuoveRisposte() {
		return nuoveRisposte;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}
}
