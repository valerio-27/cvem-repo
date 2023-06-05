package it.academy.gaming.milionario.manager.grafics.requests;

import java.util.List;

public class ModificaSuggerimentiRequest {
	private String codiceQuesito;
	private List<ModificaSuggerimentoRequest> modificaSuggerimentiRequests;

	public ModificaSuggerimentiRequest(String codiceQuesito,
			List<ModificaSuggerimentoRequest> modificaSuggerimentiRequests) {
		super();
		this.codiceQuesito = codiceQuesito;
		this.modificaSuggerimentiRequests = modificaSuggerimentiRequests;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

	public List<ModificaSuggerimentoRequest> getModificaSuggerimentiRequests() {
		return modificaSuggerimentiRequests;
	}

}
