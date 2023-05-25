package it.academy.gaming.milionario.manager.grafics.requests;

public class InserisciQuesitoRequest {
	private InserisciDomandaRequest domandaRequest;
	private InserisciRispostaRequest[] rispostaRequests ;
	private int livelloDifficolta;
	public InserisciQuesitoRequest(InserisciDomandaRequest domandaRequest, InserisciRispostaRequest[] rispostaRequests,
			int livelloDifficolta) {
		super();
		this.domandaRequest = domandaRequest;
		this.rispostaRequests = rispostaRequests;
		this.livelloDifficolta = livelloDifficolta;
	}
	public InserisciDomandaRequest getDomandaRequest() {
		return domandaRequest;
	}
	public InserisciRispostaRequest[] getRispostaRequests() {
		return rispostaRequests;
	}
	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

	

}
