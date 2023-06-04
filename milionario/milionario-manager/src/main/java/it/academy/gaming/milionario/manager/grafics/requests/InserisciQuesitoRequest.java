package it.academy.gaming.milionario.manager.grafics.requests;

import java.util.Collection;

public class InserisciQuesitoRequest {
	private InserisciDomandaRequest domandaRequest;
	private InserisciRispostaRequest[] rispostaRequests;
	private Collection<InserisciSuggerimentoRequest> suggerimenti;
	private int livelloDifficolta;

	public InserisciQuesitoRequest(InserisciDomandaRequest domandaRequest, InserisciRispostaRequest[] rispostaRequests,
			Collection<InserisciSuggerimentoRequest> suggerimenti, int livelloDifficolta) {
		super();
		this.domandaRequest = domandaRequest;
		this.rispostaRequests = rispostaRequests;
		this.suggerimenti = suggerimenti;
		this.livelloDifficolta = livelloDifficolta;
	}

	public InserisciDomandaRequest getDomandaRequest() {
		return domandaRequest;
	}

	public InserisciRispostaRequest[] getRispostaRequests() {
		return rispostaRequests;
	}

	public Collection<InserisciSuggerimentoRequest> getSuggerimenti() {
		return suggerimenti;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

}
